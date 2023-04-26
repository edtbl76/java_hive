# ConcurrentHashMap
similar to hashMap, but offers internally maintained concurrency. 
- doesn't require the use of synchronized block when accessing ConcurrentHashMap in a
multithreaded applications

## PARAMETERS
initialCapacity

loadFactor

concurrencyLevel
- "number of shards"
- used internally to divide the structure into THIS number of partitions and equal number of
threads are created to maintain thread safety maintained at the shard level.
(default value is 16)