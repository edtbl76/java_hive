#  Thread Priorities
- The default priority (when not specified) is NORMAL priority (5)
- The appropriate RANGE of priorities is 1 - 10 (10 being the highest)
- the thread with the HIGHEST priority is given preferential execution
    - There is NO GUARANTEE that it will be in a running state when it starts
- If setting the thread priority, this MUST BE DONE before the start() method executes.
    (priority will NEVER change in a running thread.)
- the THREAD SCHEDULER decides which thread should be executed

SETTING PRIORITY

    EX: 
    
        t.setPriority(), where t = a thead. 
        
            three constants can be used
            - MIN_PRIORITY
            - MAX_PRIORITY
            - NORM_PRIORITY. 
            
            Using these exclusively is equivalent to 
            HIGH, MEDIUM, LOW