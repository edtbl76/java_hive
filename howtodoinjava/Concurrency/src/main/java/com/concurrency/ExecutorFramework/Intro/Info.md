# Java Executor Framework
- java.util.concurrent.Executor (released in JDK 5)
- used to execute or run() the Runnable objects w/o creating new threads every time. 
    - predominantly re-uses already created threads
    - process is VERY expensive, so having a pool of threads "warmed" we can avoid the
    cost of latency of creating threads on demand. 
    
## BEST PRACTICES
- ALWAYS run java code against static tools (i.e. check for bugs. I love my IDE)
- Always test for deadlock/livelock conditions.
    - health monitoring and instrumentation for applications is crucial. 
- it is recommended to catch both Exceptions AND Errors in MT applications
- handling problems. 
    - retry logic can cause problems in multi-threaded or reactive environments. 
    - it is extremely important to use back-off timer/algorithms that perform a check
    to determine that the system has acquiesced before beating the shit out of it again.