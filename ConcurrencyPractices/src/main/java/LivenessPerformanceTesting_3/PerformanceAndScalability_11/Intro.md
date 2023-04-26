# Chapter 11: Performance and Scalability

## Why are we using threads to begin with?
- using threads improves resource utilization by letting apps 
more easily exploit available processing capacity


- improves responsiveness by letting apps begin processing new
tasks immediately, while other tasks are still running.


    
    It is widely debated that the only viable reason to 
    introduce the complexity of concurrency (and threads)
    
    ...is because it improves performances and provides the
    flexibility to increase the scale of applications to
    meet modern consumption demand. 
    
## What This Section Involves
- Techniques for analyzing, monitoring and improving the
performance of concurrent apps


- navigating the increased complexity induced by the forementioned 
techniques
    - includes avoiding/remediating safety + liveness failures that
    become more likely due to the increased complexity
    
    
- avoiding costly tradeoffs ("Don't Kill 'em with the cure")
    - increasing performance in one desirable area, may reduce it
    in other areas in a manner that results in a worse overall 
    experience than the original problem 
    
## Remember...

1.) Make your program RIGHT
2.) IF and only if performance requirements and measurements tell us
it needs to be faster... make it faster. 