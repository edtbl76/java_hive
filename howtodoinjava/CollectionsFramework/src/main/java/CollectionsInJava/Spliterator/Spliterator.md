# Spliterator Interface
- internal iterator that breaks a STREAM into smaller parts. 
    - the individual streams can be processed in parallel. 
    
## Collection Classes
provide stream() and parallelStream() methods which internally use
the Spliterator interface through the spliterator() call. 


    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
    
    default Stream<E> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }
    
## FEATURES
- since JDK 1.8
- provides parallel processing support for a stream of elements for ANY collection
- tryAdvance()
    - iterates elements individually in different threads
- forEachRemaining()
    - iterates elements sequentially in a single Thread.
- trySplit()
    - used to partition the spliterator, if possible
- combines hasNext() and next() operations into a single method.


## METHODS
int characteristics()
- returns list of characteristics of spliterator
    - ORDERED, DISTINCT, SORTED, SIZED, NONNULL, IMMUTABLE, CONCURRENT or SUBSIZED

long estimateSize()
- returns an estimate of the no. of elements that would be encountered by a 
forEachRemaining() traversal
    - returns Long.MAX_VALUE if infinite, unknown or too $$$$ to compute.

default void forEachRemaining(Consumer action)
- performs given action for each remaining element, sequentially in the current thread
    - until all elems processed or an exception is encountered.

default long getExactSizeIfKnown()
- returns estimateSize() if spliterator is SIZED, else -1

default boolean hasCharacteristics(int characteristics)
- returns true if ALL given characteristics are set for spliterator

boolean tryAdvance(Consumer action)
- if a remaining element exists, performs given action and returns true
- else false

Spliterator trySplit()
- if spliterator can be partitioned, returns a Spliterator covering elements
that will (upon return from this method) NOT be covered by THIS spliterator