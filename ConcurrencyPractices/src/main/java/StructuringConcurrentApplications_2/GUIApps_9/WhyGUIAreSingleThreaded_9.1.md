# Why are GUIs Single-threaded

### Mechanism
- Old GUI apps were processed from a single main event loop
- Modern frameworks have "slightly" different approach
    - EDT (Event Dispatch Thread) for handling GUI Events

### Not Java-Specific
- Qt
- NextStep
- MacOS Cocoa
- X Windows
- and many more!!!

### ITS FUCKING HARD
- many attempts to write multithreaded GUI frameworks

#### Challenges
- persistent problems w/ 
    - race conditions
    - deadlock
    
### Result
- single-threaded event queue model
    - dedicated thread fetches events off of a queue
    - thread then dispatches to app-defined event handlers
        - (handlers are app/task specific -- usually)
        
### Deadlock Problems
- most common problem w/ multi-threaded GUI frameworks
    - due to interaction between input event processing and object
    oriented modeling of GUI components. 
    
- Below we describe tendency for actions to access the same GUI
components in the opposite order
    - EX: 
        - User (OS -> App)
        - App (App -> OS)
    - in a multi-threaded framework, this adds the requirement
    that each object being accessed must be thread-safe
    - This leads to "inconsistent lock ordering"
    - DEADLOCK
---    
#### User-Initiated Actions
- "Bubble Up" from OS to Application

    
    Example: 
        
        - mouse click detected by OS
        - becomes "mouse click" event in GUI toolkit
        - eventually delivered to an app listener
            - (as a higher level event, like "button pressed")

#### App-Initiated Actions
- "Bubble Down" from App to OS


    Example:
    
        - change bg color of a component starts in the app
        - that change is dispatched to a specific component class
        - eventually the task works its way to OS for rendering
        
---

#### MVC (Model View Control) Pattern and Deadlocks.
- Advantage of MVC
    - factors user interactions into cooperating model, view and
    controller objects
        - (Simplifies implementation of GUI objects)
- Disadvantage
    - Lock ordering strikes again!
    
    
    Example:
    
        - Controller calls model
        - model notifies view that a change occurs. 
        
        BUT
        
        - Controller can call view
        - view calls back into model to get model state. 
        
        Inconsistent Lock Ordering -> Deadlock.
    
### Advantages of Single Threaded GUI Frameworks
- If you can't beat 'em, Join 'em
    - single threaded => thread confinement => thread safety
    - All GUI objects are accessed exclusively from the event thread
        - visual components, data models, etc. 
- TRADEOFF
    - pushes some of the thread safety burden back on the app dev...
    
## 9.1.1 Sequential Event Processing
- GUI apps are oriented around processing fine grained events

### Events
- similiar to a task
- Event Handlers in AWT and Swing are "structurally similar" to an **Executor**

### Side Effects of Being Single Threaded
- GUI tasks processed sequentially (one after another)
    - no two tasks overlap
    - since task code can't interfere, it makes writing the tasks easier. 
    
#### Downsides
- tasks submitted after a long running task have to wait a long time to be processed
    - if those tasks are interactive, they app will appear to "freeze"
        - (i.e. response to user input, or visual feedback)
    - user can't click cancel, because the cancel button listener wouldn't be called until the
    long running task is done. 
    
#### Solution
- tasks that execute in the event thread must return control to the event thread quickly
    - long running tasks should be dispatched to another thread, so that control can be returned
    to the main thread quickly
        - EX:
            - spell-checking a large document
            - running file system search
            - fetching resources over a network
- providing visual feedback (like a progress bar?) requires that code be executed in the event thread
while long running tasks are executed in worker threads


    This gets complex in a hurry
    
## 9.1.2 Thread Confinement in Swing
- All Swing Components/Data Model Objects are confined to event thread, so any code that accesses
them must also run in the event thread
    - Swing Component Example
        - **JButton** and **JTable**
    - Data Model Object Example
        - **TableModel** an **TreeModel**
        
        
        Consistency of GUI objects is managed via thread confinement rather than 
        synchronization
        
### Upside
- event thread is easier to manage because there is no need to worry about synchronization when
accessing presentation objects.

### Downside
- there is no way to access presentation objects outside of the event thread. 

### "Swing Single-Thread Rule"

    Swing components and models should be created, modified and queried only from 
    the event dispatching thread. 

#### Exceptions
- a small number of threads (that are CLEARLY marked as thread-safe within in the JavaDoc) can be
called safely from any thread. 
- Other Exceptions
    - methods to enqueue a repaint/revalidation request on event queue
        (callable from any thread)
    - methods for adding/removing listeners
        - may be called from ANY thread
        - BUT, listeners are ALWAYS invoked in event thread
    - The following **SwingUtilities** methods...


        SwingUtilities.isEventDispatchThread:   determines if current thread is 
                                                the event thread
                                                
        SwingUtilities.invokeLater:             schedules a Runnable for execution
                                                on the event thread
                                                (callable on any thread)
                                                
        SwingUtilities.invokeAndWait:           schedules a Runnable task for 
                                                execution on the event thread
                                                - blocks current thread until 
                                                it completes
                                                (callable ONLY from a non-GUI
                                                thread)
                                                

- **invokeLater()** and **invokeAndWait()** act like an **Executor**
    - **SwingUtlities** predates the Executor Framework, so it would not have been impl'd 
    like the example below
    - This example is to demonstrate how trivial it would be to perform such an impl.
        - (demonstrates how useful Executor Framework is!)

    
        Implementing SwingUtilities with an Executor
        
            public class SwingUtilities {
                
                private static final ExecutorService executor = 
                    Executors.newSingleThreadExecutor(new SwingThreadFactory());
                private static volatile Thread swingThread;
                
                private static class SwingThreadFactory implements ThreadFactory {
                    public Thread newThread(Runnable runnable) {
                        swingThread = new Thread(runnable);
                        return swingThread;
                    } 
                }
                
                public static boolean isEventDispatchThread() {
                    return Thread.currentThread() == swingThread;
                }
                
                public static void invokeLater(Runnable task) {
                    executor.execute(task);
                }
                
                public static void invokeAndWait(Runnable task) 
                        throws InterruptedException, InvocationTargetException  {
                    
                    Future future = executor.submit(task);
                    try {
                        future.get();
                    } catch (ExecutionException e) {
                        throw new InvocationTargetException(e);
                    }
                    
                }
            
            }
    
### Swing Event Thread
- Basically a single-threaded **Executor** that processes tasks submitted from an event queue.
    - similar to a thread pool in that sometimes, worker threads die and are respawned. 
    - thread respawning should be transparent to the tasks. 
    
### Use Cases

    Sequential, Single Threaded execution policy works when:
        - tasks are short lived
        - tasks must NOT execute concurrently
        - scheduling predictability isn't important
        
        
#### Example Code 
- This is an example of an **Executor** that delegates tasks to **SwingUtilities** for execution

    Executor built on TOP of SwingUtilities
    
    
        public class GuiExecutor extends AbstractExecutorService {
            /*
                NOTE: Singletons have a private constructor and a public factory
            */
            private static final GuiExector INSTANCE = new GuiExecutor();
            
            private GuiExecutor() { }
            
            public static GuiExecutor instance() {
                return INSTANCE;
            }
            
            public void execute(Runnable runnable) {
                if (SwingUtilities.isEventDispatchThread()) {
                    runnable.run();
                } else {
                    SwingUtilities.invokeLater(runnabel);
                }
            }
            
            // Trivial Implementations of LifeCycle Methods.
            
        }



