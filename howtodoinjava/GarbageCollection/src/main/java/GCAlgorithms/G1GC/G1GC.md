# G1 (Garbage First) Garbage Collection
parallel, concurrent and incrementally compacting low-pause garbage collector

## METHOD
![alt-text](Memory-regions-marked-G1.png)
- segments memory heap into small regions (usually 2048)
    - this allows GC to solve problem incrementally rather than having to collect entire heap 
    at once.
- regions are marked as 
    - Young Generation (further divided into "eden" or "survivor" regions)
    - Old Generation
- Still uses STOP-THE-WORLD pause approach to GC, but it is more configurable, and due to the
nature of breaking it down into an incremental problem, the pause periods are shorter
    - this is also broken down by being a multithreaded operation

## Tracking ("Garbage First")
- G1 tracks the amount of "live data" per region
- this allows GC to determine which regions contain the most garbage, and therefore allows those
regions to be collected first.


## USE CASES
- default GC for Java 9 and up.

## ENABLE

    -XX:+UseG1GC

## OPTIMIZATION TABLE
| Flag | Description |
| --- | --- |
| -XX:G1HeapRegionSize=16m | Size of Heap Region: (Value is power of 2, between 1MB - 21 MB) Goal is to have 2048 regions based on minimum Java Heap Size |
| -XX:MaxGCPauseMillis=200 | Set a target for desired max pause time. Default is 1/5 of a second. This does NOT adapt to heap size |
| -XX:G1ReservePercent=5 | Determines minimum reserve in heap |
| -XX:G1ConfidencePercent=75 | confidence coefficient pause prediction heuristics |
| -XX:GCPauseIntervalMillis=200 | pause interval time slice per MMU in millis |


