# CREATING A THREAD BY...

### Implemnting Runnable Interface

    - can be implemented by classes that intent to execute as a thread. 
    
    
    STEPS
    
        1 - implement a run() method provided by a Runnable interface
        
            - provides entry point for the thread
            - this is where business logic (i.e. what the thread should do) 
            lives
            
            EX: 
                public void run()
            
    
        2 - instantiate Thread object
        
            EX: Thread(Runnable threadObj, String threadName) 
            
                - threadObj is instance of a class that implements the 
                Runnable interface
                
                - threadName is the name given to the new thread. 
                
        3 - a created thread can be started by calling start() method
        
            - this makes a call to run()
            
            EX: 
            
                void start()
                

### by EXTENDING A THREAD CLASS

    - create a new class that extends 'Thread' by 2 simple steps
    (this approach is more flexible when handling multiple threads
    created using available methods in Thread class) 
    
        1.  override run() method in Thread class
        
            - provides entry point for the thread 
            - home for all business logic
            
            EX: public void run()
            
        2.  created Thread objects can be started by calling start(), 
            which makes a call to run()
            
            EX: void start()
        
        