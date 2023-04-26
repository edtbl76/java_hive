# THREAD CONTROL

    - "Core Java' provides complete control over a multithreaded program. 
    
        - ca be suspended, resumed, or stopped completely based on your
        requirements. 
        
        
### METHODS

    public void suspend()
    
                    - puts 'this' Thread in a suspended state
                    (resumed using resume())
                    - DEPRECATED
                    
    public void stop()
    
                    - stops 'this' Thread completely
                    - DEPRECATED
                    
    public void resume()
    
                    - resumes a thread that had been suspended
                    - DEPRECATED
                    
    public void wait()
    
                    - causes a thread to wait until another thread invokes
                    notify()
                    
    public void notify()
    
                    - wakes up a single thread that is waiting on this object's
                    monitor
                    
                    