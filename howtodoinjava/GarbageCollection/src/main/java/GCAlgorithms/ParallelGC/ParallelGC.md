#Parallel GarbageCollection
Multiple concurrent threads are used for marking and copy/compact phases. 

## METHODS
(similar to Serial GC)
- uses MARK-COPY in YOUNG GENERATION
- uses MARK-SWEEP-COMPACT in OLD GENERATION

## ENABLE/CONFIG
- set N number of threads


    -XX:ParallelGCThreads=N
    
- enable


    -XX:+ParallelGC
    
## USE CASE
- multi-core machines when the primary tuning goal is to increase throughput by efficient use
of system resources. 

- NOTE this was the default GC mechanism for java 8. 
