# Thread Synchronization

    - when 2 or more threads are started, there may be a situation when 
    multiple threads attempt to access the same resource. 
    
        - may corrupt data, because one thread can override data or while one
        thread is opening the same file at the same time, another file
        might be closing the same file. 
        
        
    MONITORS
        - each object in Java is associated w/ a monitor, which a thread can
        lock or unlock
        
        - only one thread at a time may hold a lock on amonitor. 
        
        
    SYNCHRONIZED
        - keyword used w/ code blocks that provides a 'very handy way' of 
        creating thrads and synchronizing their tasks.
        
        - block keeps shared resources. 
        
        EX: 
        
            synchronized(objectIdentifier) {
                // Access shared variables and other shared resources.
            
            }
            
            where 'objectIdentifier' is a reference to an object whose lock
            associates w/ the MONITOR that the synchronized statement
            represents. 