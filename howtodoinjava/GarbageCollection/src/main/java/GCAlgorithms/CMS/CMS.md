# ConcurrentMarkSweep
- upgraded form of mark-and-sweep
- scans Heap Memory using MULTIPLE THREADS
- minimizes STOP-THE-WORLD-PAUSE by doing GC work concurrently

## METHOD
Mark-copy (Stop-the-world)
- YOUNG GENERATION

Mark-Sweep
- OLD GENERATION

## ENABLING

    -XX:+UseCongMarkSweepGC
    

