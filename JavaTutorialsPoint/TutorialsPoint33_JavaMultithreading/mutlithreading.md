# MULTITHREADING

    - Java is a multi-threaded programming language. 
    
    
            
    MULTITASKING
    
        - when multiple processes share common processing resources 
        (such as CPU) 
        
        
    MULTI-THREADED PROGRAM
            - contains 2 or more parts that can run concurrently
            
                - each part can handle a different task at the same time
                making optimal use of available resources
                (especially when the computer has multiple CPUs) 
                
            - extends MULTITASKING into apps where specific operations from
            that app can be subdivided into individual threads. 
            
                - each thread can run in parallel
                
                - the OS divides processing time among different apps as well
                as different threads within the same app. 
                
                
### LIFE CYCLE OF A THREAD


    NEW (Born Thread) 
    
        - a new thread begins in this state. 
        - remains in this state until the program starts the thread.
        
    RUNNABLE
    
        - born thread is started. 
        - threads in this state are considered to be executing their tasks
        
    WAITING
        
        - thread transitions to a 'waiting' state while the thr3ead waits for 
        another thread to perform its task. 
        
        - thread transitions back to RUNNABLE state only when another
        thread signals the waiting thread to continue executing
        
    TIMED WAITING
    
        - RUNNABLE thread enters this state for a specific interval of time
        
        - thread transitions back to RUNNABLE when the specified interval of
        time expires OR when the event it is waiting/polling for expires
        
    TERMINATED (DEAD) 
    
        - a RUNNABLE thread enters the terminated state either when it 
        completes its task or some other event forces it to terminate
        
        
### THREAD PRIORITIES

    - every thread has a priority that helps OS determine the order in 
    which the threads are scheduled
    
        - priorities between 
            MIN_PRIORITY    (constant of 1) 
            MAX_PRIORITY    (constant of 10)
            
        - threads given default priority
            NORM_PRIORITY   (constant of 5) 
            
    - higher priority are considered more important to the program, and therefore
    are allocated processor time before lower-priority threads. 
    
    - NOTE: thread priorities do NOT guarantee the order threads execute and are
    platform independent
    
    
    
    