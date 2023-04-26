# Chapter 10 - Avoiding Liveness Hazards

## Safety vs. Liceness
- Locking is used to ensure safety
    - poor use of locking causes "lock-ordering deadlocks"


- Thread pools / semaphores are used to bound resource consumption
    - failure to understand actions being bounded can cause "resource deadlocks"
    

    Java Applications do not recover from deadlock :)
