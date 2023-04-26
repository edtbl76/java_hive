# Thread Deadlocks

    - describes a situation where 2 or more threads are blocked forever waiting
    for each other.
    
        - occurs when multiple threads need the same locks but obtain them in
        different order. 
        
        - Java programs are susceptible to deadlocks because 'synchronized' 
        keyword causes executing thread to BLOCK while waiting for the lock (or
        monitor) associated w/ the specified object. 
        
        
