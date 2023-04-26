# 9.2 Short-running GUI Tasks

### GUI App
- events originate in event/UI thread
- they "bubble up" to app-provided listeners
    - they perform computation that affects presentation objects
    
    
    Short Running Tasks: 
        - entire action can stay in the event thread
        
    Long Running Tasks:
        - some of the processing should be offloaded to another
        thread.
        

#### Example Code - Simple Event Listener

    
    final Random random = new Random();
    final JButton button = new JButton("Change Color");
    
    button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            button.setBackground(new Color(random.nextInt()));
        }
    });
    
This is an example of a very simple task.
- user clicks button
- toolkit delivers an **ActionEvent** in the event thread
    - (Sent to all registered action listeners)
- (RESPONSE) 
    - action listener picks a new color and changes the button's
    bg color 

![Flow of Example](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/StructuringConcurrentApplications_2/GUIApps_9/Images/Screen Shot 2020-12-08 at 10.50.03 PM.png)
- (EDT represents the Event Dispatch Thread)
- control never leaves the EDT

    
    NOTE: This kind of example is actually a representation of a majority of the 
    interactions between GUI Toolkits/Frameworks and a GUI Application

### Benefits of Short Lived GUI Tasks
- as long as you only access GUI objects*
    - you can do almost everything from the event thread, without worrying about threading concerns, 
    and you'll get the right result. 
    
    
    *   You can also access any other thread-confined or thread-safe 
    application objects in this manner

#### Example Two (More Complicated Flow)
![More Complicated Flow](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/StructuringConcurrentApplications_2/GUIApps_9/Images/Screen Shot 2020-12-08 at 11.31.51 PM.png)

- Uses a formal data model
    - (i.e. **TableModel** or **TreeModel**)
    
    
    Swing divides visual components into 2 objects
    
        MODEL   the data to be displayed lives here
        
        VIEW    the rules govering HOW data is displayed
                live here.
    
    - Model objects generate events indicating that model data
    has been changed.
    
    - Views subscribe to the events. 
        - on receipt of event, the view queries the model to 
        get the updated data. 
        - view updates the display with that new data
    
- Even in this model, control never has to leave the event thread

The flow above represents a button listener that modifies the contents of a table.
- action listener
    - updates the data model with the new data
    - calls a fireXxx method to invoke the view's table model listeners
- table model listener
    -  updates the view. 
    
    
    fireXXX methods in Swing data models always call model listeners
    directly 
        1.) this means that they don't submit a new event onto
        the event queue
        
        2.) these methods MUST be called ONLY from the EDT