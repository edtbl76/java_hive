# Serial Garbage Collection
Works no a single thread. All other threads are "frozen" until GC operations are complete. 
- uses Mark-Copy for Young Generation
- uses Mark-Sweep-Compact for Old Generation

This is only useful for small apps due to the thread-freezing


## Enabling

    -XX:+UseSerialGC