# 9.3 Long Running GUI Tasks

"Sophisticated" GUI apps execute tasks that make take a while
- spell checking
- background compilation
- fetching remote resources

Long Running Tasks can prevent the app from being responsive, so 
we have to dispatch these tasks to another thread. 


### Dispatching Threads
- Many GUI Frameworks can't do this very well
- Alternative is to create our own **Executor**
    - Cached Thread Pool is a good choice
        - long running tasks are fairly rare in GUI apps so
        its unlikely that the pool will grow outlandishly
        
#### Example 1 - Simple "Fire and Forget"
    
    Binding Long-Running Task to a Visual Component
    
        ExecutorService bgExecutor = Executors.newCachedThreadPool();
        ...
        
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                backgroundExecutor.execute(new Runnable() {
                    public void run() {
                        meLoveYouLongTime();
                    }
                });
            }
        });

- description
    - action listener, that is bound to a button, submits a long-running task
    to an **Executor**.

- basic task
    - no support for cancellation
    - no progress indication
    - doesn't update the GUI on completion
    

    This isn't that useful, because there isn't any visual feedback when the long
    running task
    
PROBLEM TO SOLVE
- visual feedback isn't possible in this example, because presentation objects aren't accessible
from the background thread. 
    - They MUST be accessed from the event thread. 
    
#### Example 2 - Visual Feedback 
In order to provide visual feedback
- when a task completes, it should submit another task to run in the event thread to perform the
visual feedback/update


        Long Running Task with User Feedback
        
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    button.setEnabled(false);
                    label.setText("busy");
                    backgroundExecutor.execute(new Runnable() {
                        public void run() {
                            try {
                                loveMeLongTime();
                            } finally {
                                GuiExecutor.instance().execute(new Runnable() {
                                    public void run() {
                                        button.setEnabled(true);
                                        lable.setText("idle");
                                    }
                                });
                            }
                        }
                    });
                }
            });
- NOT basic task
    - adding ONE of the features mentioned above (visual feedback) turns a basic task into this ugly
    monstrosity
    - THREE layers of inner classes.   

- description
    - The Task is composed of 3 sequential subtasks that do a bit of "thread-hopping" 
        - Subtask 1 (On EDT) 
            - action listener dims button and sets label indicating that a computation is happening
                - this happens on the EDT, so that we can immediately provide feedback to the user that we're going 
                to go do some work based on their request. 
            - (NOTE: This is a common indicator that work is going off to a worker thread/bg executor)
            - action listener submits the task associated with the button to be run on the worker thread (i.e. 
            background executor)
        - Subtask 2 (on Worker Thread)
            - This is the computation subtask that is performed in the background
            - when requested task completes, onComplete, it queues another task to run on the EDT
        - Subtask 3 (back on the EDT)
            - the task that runs on the EDT is responsible for restoring the state of the button
                - un-dim & restore label text
        

        Thread Hopping with long-running tasks in GUI apps is normal based on the boundaries and thread
        safety rules 
        

## 9.3.1 Cancellation
- Any task that takes a while to complete might be something that a user wants to cancel

### Futures

    It is recommended to use a Future to implement cancellation (rather than using thread
    interruption), because Futures were explicit designed to manage cancellable tasks. 
    
#### How to cancel a thread w/ a Future
- when calling **cancel()** on a **Future** w/ **mayInterruptIfRunning == True**
    - Future impl interrupts the thread executing the task as long as it is running. 
- if the task is written to be responsive to interruption, it can then return early if cancelled

### Example
    Cancelling a long-running task
    
        /*
            The task is thread confined
            - no synchronization is required when setting/checking
        */
        Future<?> runningTask = null;
        
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (runningTask == null) {
                    runningTask = backgroundExecutor.submit(new Runnable() {
                        public void run() {
                            
                            /*
                               POLLER
                               - This while loop keep schecking to see if there is work to be done
                               on the EDT
                               - If there is, we enter the loop, else the task completes naturally
                               - Before attempting to do the work, we poll the interrupted state of the
                               thread. 
                                - if interrupted, clean up to prevent breakage and then return early
                                - else do the work. 
                            */ 
                            while (moreWork()) {
                                if (Thread.currentThread().isInterrupted()) {
                                    cleanUpPartialWork();
                                    break;
                                }
                                doYourJob();
                            }
                        }         
                    });
                }
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (runningTask != null) {
                    runningTask.cancel(true);
                }
            }
        });

- The startButton task above has a poller designed to return early if the interruption state is TRUE

#### Benefits
- The task is thread confined
    - this means that we don't have to use synchronization when setting/checking the task
- start button listener ensures that only one worker thread is running at a time

#### Downsides
- Pollers are crappy, because they make it possible to miss the event. 
- it is better to be notified when the task completes. 
    - allows us to disable the button 

## 9.3.2 Progress & Completion Indiciation
- using Futures to represent long running tasks makes it easier to implement cancellation
- See Examples.BackgroundTask for implementation and explanation of completion
and progress notification impls
    - it likewise uses a FutureTask to simplify cancellation
    
### Polling Fix. 
- instead of polling the thread's interrupted status, our **compute** method 
can call **Future.isCancelled** 


    EX:
        
        Initiating Long Running Cancellable task w/ BackgroundTask
        
            public void runInBackground(final Runnable task) {
                startButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        
                        class CancelListener implements ActionListener {
                            Background<?> task;
                            public void actionPerformed(ActionEvent event) {
                                if (task != null) {
                                    task.cancel(true);
                                }
                            }
                        }
                        
                        final CancelListener listener = new CancelListener();
                        listener.task = new BackgroundTask<Void>() {
                            public Void compute() {
                                
                                while (moreWork() && !isCancelled()) {
                                    doSomework();
                                }
                                return null;
                            }
                            
                            public void onCompletion(boolean cancelled, String s, Throwable throwable) {
                                cancelButton.removeActionListener(listener);
                                label.setText("done");
                            }
                        };
                        
                        cancelButton.addActionListener(listener);
                        backgroundExecutor.execute(listener.task);     
                    }
                });
            }
                
    


    