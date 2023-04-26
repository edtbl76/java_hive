# CONSUMER PRODUCER CONTENTION

- CONSUMER
    - threads that process data. 
    - there is no guarantee that when a consumer receives a notification that it hasn't been
    already processed by another consumer. 
        (see wait.md and the notes about how to handle condition checks prior to 
        calling or returning from wait())
        
- When consumer wakes up, it can't assume that the state it was waiting for is still valid 
    - it MUST recheck the condition to get a lay of the land. 
    - if the notification was already handled, then they return to wait()
    - this is why wait() method is in a loop!