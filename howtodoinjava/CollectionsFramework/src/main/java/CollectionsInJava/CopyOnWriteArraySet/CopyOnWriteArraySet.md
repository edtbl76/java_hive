#CopyOnWriteArraySet
Thread-Safe variant of HASHSET
- uses underlying CopyOnWriteArrayList for all of its operations

## IMMUTABLE SNAPSHOT ITERATOR
- this is a method of iterator that uses a reference to the state of the array at the
point the iterator was created.

- use case:
    - when TRAVERSAL operations vastly outnumber list update operations and we
    don't want to synchronize the traversals and still want THREAD SAFETY while
    updating the list.
    
## HIERARCHY

    public class CopyOnWriteArraySet<E>
        extends AbstractSet<E>
        implements Serializable
    {
        private final CopyOnWriteArrayList<E> al;
        
        // impl code
    }
    
## FEATURES
- no duplicates (consistent w/ a standard SET data structure)
- has same performance/cost hit associated w/ CopyOnWriteArrayList
    - cloned backing array for mutative operations
    - snapshot of backing array for iterations
- does NOT throw ConcurrentModificationException
    - this is one of the tradeoffs for implementing the IMMUTABLE SNAPSHOT
- does NOT support mutative operations on iterators
    - throws UnsupportedOperationException
- concurrent replacement for synchronizedSet
    - offers BETTER concurrency when ITERATIONS > MUTATIONS

## CONSTRUCTORS
CopyOnWriteArraySet()
- empty set

CopyOnWriteArraySet(Collection c)
- initializes set w/ elements from given collection, in the order in which they
would be returned by that collection's iterator

## METHODS
boolean add(Object o)
- true if it was successfully able to add element to set. 

boolean addAll(Collection c)
- true if can successfully add all elements from given collectioin

void clear()
- removes all elements from set

boolean contains(Object o)
- true if element is present in set

boolean isEmpty()
- true if set is empty

Iterator iterator()
- returns an iterator over set's elements in insertion order

boolean remove(Object o)
- removes specified element from this set if present

int size()
- returns no. of elements in this set. 

## USE CASES
- used in apps where set sizes are "relatively" small
- read-only operations VASTLY OUTNUMBER mutative operations
- need to prevent interference amongst threads during traversal. 

MINIMIZES CONTROLLED SYNCHRONIZATION STEPS. 

## PERFORMANCE
- worse than HashSet. 