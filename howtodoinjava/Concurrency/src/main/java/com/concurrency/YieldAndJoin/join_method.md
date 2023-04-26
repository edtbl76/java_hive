# join() method
- used to join the START of a thread's execution to the end of another thread's execution
    - the NEW thread won't start executing until  the original thread has quit. 
    - if join() is called on a Thread instance, that currently running thread will BLOCK
    until the thread instance has finished executing
    
## timeouts within join()
- creating a timeout inside a join() will make the join() effect to be NULLIFIED after
the timeout. 
    - i.e. when the timeout is reached, the main Thread and task Thread are equally
    probably to execute
    
    - NOTE: sleep() and join() are dependent on OS for timing, so you can't assume that
    the join will wait EXACTLY as long as you specify
    
    
    EX:
    
        publiic final void join() throws InterruptedException {}
        
        