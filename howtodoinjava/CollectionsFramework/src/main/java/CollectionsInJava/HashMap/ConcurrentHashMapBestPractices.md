# Java ConcurrentHashMap Best Practices
Implementation of Java HashMap that offers internally maintained concurrency

Maintaining concurrency typically comes w/ a performance reduction. Java provided
concurrent versions of libraries in order to deliver a "best tradeoff" 
such that concurrency is maintained in a manner that offers optimal performance
given the circumstances. 


    EX:
        
        ConcurrentHashMap<String, Integer> chm = new ConcurrentHashMap<>();
        
        chm.forEach((key, value) -> {
            System.out.println(key, value);
        });

## PARAMETERS
initialCapacity
- no. of elements allocated before resizing happens

loadFactor

concurrencyLevel
- denotes number of shares
- used to divide ConcurrentHashMap into the number of partitions specified here.
    - equal # fo threads are created to maintain thread safety at shard level
- default is 16
    - these shared are created when the constructor is called. 


1:50 ratio of extra/superfluous objects created
    - use constructor parameters wisely.