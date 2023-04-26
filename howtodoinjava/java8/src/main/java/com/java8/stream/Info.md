# Java 8 Stream API
- A java stream is defined as a sequence of elements from a source that supports
AGGREGATE operations on them. 
    - the SOURCE is either a COLLECTION or ARRAY that provides data to the STREAM
    - STREAM retains ordering from source
    - AGGREGATE/BULK OPERATIONS allow common transformation of stream elements EASILY and CLEARLY
    
- MOST stream operations return a stream type
    - helps/enables PIPELINING (i.e. chaining of stream operations)
    
# JAVA STREAM vs. COLLECTION

## COLLECTION
- in-memory data structure that holds all of the values that the data structure currently has. 
    - every element in the collection must be processed/computed before it can be added to the 
    collection
    
## STREAM
- "CONCEPTUALLY FIXED DATA STRUCTURE" in which elements are computed on demand. 
    - users only extract the values that they need from a stream
    - elements are only produced "invisibly" on-demand/when required
    - PRODUCER <--> CONSUMER relationship
    
## java.util.Stream
- represents a stream on which 1+ operations
    - TERMINAL operations return a result of a certain type
    - INTERMEDIATE operations return the stream itself. 
        (for pipelining)
- created on a source (i.e. Collection, list or sets)
    - MAPs aren't supported
- can be executed in PARALLEL or in SEQUENCE

- CHARACTERISTICS
    - not a data structure
    - designed explicitly for Lambdas
    - don't support indexed access
    - can easily be output as Arrays or Lists
    - supports Lazy access
    - parallelizable