# Part 1 Summary - Fundamentals of Concurrency
- It's the mutable state, stupid
- Make fields final unless they need to be mutable
- immutable objects are automatically thread-safe
- encapsulation make sit practical to manage the complexity
- guard each mutable variable with a lock
- guard all variables in an invariant with the same lock
- hold locks for the duration of compound actions
- a program that accesses a mutable variable from multiple threads without
synchronization is a broken program
- don't rely on celver reasoning about why you don't need to synchronize
- include thread safety in the design process
    - or explicitly document that your class isn't thread-safe
- document your synchronization policy