# 10.3 Other Liveness Hazards

## 10.3.1 Starvation
- when a thread is perpetually denied access to resources it needs to 
make progress
    - CPU cycles are the most commonly starved resource


- common causes of starvation in Java Applications
    - inappropriate use of thread priorities
    - executing nonterminating constructs while holding a lock
        - (infinite loops, resources waits that don't terminate)
        
### Thread Priority
- Thread API defines 10 priority levels that the JVM can map to the same OS priority as it sees fit
    - some OS' have < 10 priority levels, so multiple JVM priorities map to same OS priority
- these are "scheduling hints", not HARD boundaries.
    - unclear what a change will do
        - maybe nothing?
        - maybe cause one thread to be scheduled in preference to another causing starvation
- Most Java apps use same priority, **Thread.NORM_PRIORITY**

    
#### OS Schedulers 
- great effort put into scheduling fairness and liveness
    - MUCH more effort/scrutiny than required by Java
    
### Best Practices
- resist temptation to tweaking thread priorities
    - it isn't a consistent behavior
    - by tweaking priorities, we induce platform-specificity into the app, making it less portable
    - we create risk of thread starvation
    
    
    Thread priority tweaking is more likely to CAUSE liveness problems than solve them. 
    
    Most concurrent applications work fine with the out-of-box default priority
    
#### Identifiying Side Effects of Thread Priority/Changes
- programs trying to recover from priority tweaks (or other responsiveness problems) have 
**Thread.sleep** or **Thread.yield** calls in strang places
    - this is the system's attempt at giving more time to low-priority threads.

    
        "The semantics of Thread.yeild (and Thread-sleep(0)) are undefined; the JVM is free 
        to implement them as no-ops or treat them as scheduling hints. In particular, they are not
        required to have the semantics of sleep(0) on Unix systems -- put the current thread at the
        end of the run queue for priority, yielding to other threads of the same priority -- though
        some JVMs implement yield in this way"

    
## 10.3.2 Poor Responsiveness
- Starvation was defined as a thread that gets NO resources. (Literal starvation, it waits forever)
- Poor responsiveness is the next rung up on the "shitty things that happen in concurrent applications"
ladder.

### Example 1 - Event Dispatch Thread
- poor responsiveness is common in GUI apps that use background threads
    - BENEFIT: dispatching long-running tasks to background threads prevents the UI from freezing outright
    - PROBLEM: CPU-intensive background tasks running on the SAME system can still affect responsiveness,
               because it can compete with the EDT for CPU cycles
               
#### Remediation
- Change thread priorities (Really?)
    - this is one of the very few cases where this actually does make sense.
   
    
    NOTE: This is depends on the nature of the task being performed on the worker 
          thread. 
    
        One of the reasons that naming convention has been altered to worker thread vs. 
        background thread is because of the connections of the work "background"
        
        A "Background" thread suggests that it isn't as important so it may be 
        deprioritized in favor of foreground tasks. 
        
            - This is mostly true in terms of UI apps. 
            
        Some applications (like the IDE I'm working in) allow the user to choose to push a task 
        into the background. 
        
            For Instance, If I'm launching my IDE, and I'm doing a git pull on a large repo, I might
            want it to remain prioritized in the foreground, because I can't really do anything until
            the code is available. 
            
                i.e. I HAVE to wait because my work depends on the work being done. 
                
            However, maybe I update some plugins or other things I'm not using right now. I can kick this
            to the background, because leaving it in the foreground prevents me from doing the other
            work I'm doing right now. 
   
### Example 2 - LOCK MANAGEMENT
- poor lock management is another common cause of poor responsiveness
    - synchronized method vs. synchronized block
        - solved via finer grained concurrency
    - iterating large collections and performing a lot of work for each element. 
        

## 10.3.3 - Livelock
- This is a liveness failure where the thread isn't blocked, but it can't move forward because it
is in a retry loop that will always fail. 

### "Poison Message" in Transactional Message Apps
- The Poison message is a case in Transactional Message Apps that causes livelocks
    -  STEPS
        - message infrastructure rolls back txn that can't be processed successfully
        - message is placed at head of queue
        - rinse and repeat
    - if the message handler has a bug that prevents the message from being processed, then
    despite the fact that there is nothing actually blocking the message handling thread, it
    can't make any forward progress due to the circumstances
    
        
        Generally this kind of livelock comes from
        
            OVEREAGER recovery code that accidentally treats unrecoverable errors as though they
            can recover
            

### Polite Passerby (a.k.a Synchronized Collisions)
- This scenario happens when multiple cooperating threads change their state in response to others
in such a manner that no thread is able to move forward. 


        Two polite people walking in opposite directions down a hallway
        
            - Each person steps out of the other's way in the same direction, still stuck. 
            - rinse and repeat.

#### Solving
- This is the same concept as colliding network packets. 
- The solution to problems like this is to introduce randomness in the retry/backoff algorithm in 
order to ensure that the collisions don't become synchronized to each other.