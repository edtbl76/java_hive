# 13.2 Performance Considerations

### Notes
- Reentrant Lock introduced in JDK 5
    - "far better contended performance than intrinsic locking"
- JDK 6
    - intrinsic lock performance was much better
![comparison](/Users/Edward/IdeaProjects/edu/ConcurrencyPractices/src/main/java/AdvancedTopics_4/ExplicitLocks_13/Images/Screen Shot 2021-01-06 at 4.39.09 PM.png)


    Performance is not just a moving target, it can be a fast moving
    target. 
    
    Yesterday's benchmark showing X is faster than Y might
    already be out of date today. 

- contended performance is the key to scalability for synchronization primitives
    - if:  more resources are spent on lock management and scheduling
    - then: fewer are available for app
    
    
- Lock Implementation BEST PRACTICES
    - fewer system calls (i.e. native shit)
        - https://man7.org/linux/man-pages/man2/syscalls.2.html
    - forces fewer context switches
    - initiates less memory-synchronization traffic on the shared memory bus 
    
    
    In general, these are all optimizations that prevent a system from being overrun 
    by operations that are
        - time consuming
        - divert compute resources away from the app
        
        
