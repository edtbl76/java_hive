# HOW JIT OPTIMIZES CODE

## OVERVIEW 

Java bytecode(s) are reformulated into TREEs

    (an internal representation which is more like machine code than bytecode)
    
Analysis/Optimization is performed against the TREEs of a method, and then the TREEs are translated 
into native code. 

JIT compiler can use multiple compilation threads to perform compilation tasks. 
- multiple threads helps java programs start faster, however for the most part they only demonstrate 
performance improvements when there are unused CORES available to take advantage of multi-threaded
compilation.

- default number of threads is ID'd by JVM, and dependent upon system configuration. 
(It can be configured/overriden w/ -XcompilationThreads option)

## PHASE 1 - INLINING

TREEs of smaller methods are merged (inlined) into the trees of their callers. 

- speeds up frequently use method calls
- supports 2 different inlining algorithms w/ differing "aggressiveness" depending upon current 
optimization levels
- phase 1 optimizations
    - trivial inlining
    - call graph inlining
    - Tail Recursion Elimination
    - Virtual Call Guard Optimizations
    
## Phase 2 - Local Optimizations

Small sections of code are analyzed/optimized at a time. 
(Most of these optimizations are well-traveled optimizations from static compilers

- Local Data Flow Analyses/Optimizations
- Register Usage Optimization
- Simplification of Java Idioms

These techniques are tried repeatedly (especially after global optimizations) where new opportunities
for improvement are unearthed

## Phase 3 - Control Flow Optimizations

Analyze Control Flow inside a method (or subsections of a method), rearranging code paths to 
improve their efficiency

- Code Reordering, Splitting, Removal
- Loop Reduction and Inversion
- Loop Striding + Loop-Invariant Code Motion
- Loop Unrolling and Peeling
- Loop Versioning and Specialization
- Exception-directed optimization
- Switch analysis

## Phase 4 - Global Optimizations

These are more "expensive" optimizations that work against the entire method at once. (This is a case of
tradeoff, where we require more amounts of compile time due to the breadth of the code being analyzed, 
however, the rewards are also greater as the increased view of the code may result in larger increases
in performance)

- Global Data Flow Analyses and Optimizations
- Partial Redundancy Elimination
- Escape Analysis
- GC and Memory Allocation Optimizations
- Synchronization Optimizations


## Phase 5 - Native Code Generation

This process of JIT varies from arch to arch. TREES of a method are usually translated into the native 
machine code instructions here. 

THere are small optimizations that can be performed based on the platform architecture. 

CODE CACHE
    - part of JVM process space where the compiled native machine code is stored. 

Location of Code Cache is recorded, so that future calls to the code cache are forwarded to the
compiled code. 


