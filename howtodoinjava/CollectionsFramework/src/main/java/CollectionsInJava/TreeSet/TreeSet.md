# TreeSet
Similar to a HashSet, but it stores elements in SORTED ORDER
- the sorted order is either natural order (Default) or determined by a Comparator 
provided at treeset creation time. 

## HIERARCHY

    class TreeSet<E>
        extends AbstractSet<E>
        implements NavigableSet<E>, Cloneable, Serializable
    {
        impl code
    }
    
## FEATURES
- extends AbstractSet, which extends AbstractCollection
- implements NavigableSet which extends SortedSet interface. 
- NO duplicate values
- NO NULL
- ordered collection
- offers constant time 
    - add(), remove(), contains() and size()
- doesnt' allow insertion of heterogeneous objects
    - because it has to compare objects to determine sort order
- Not Synchronized
    - use Collections.synchronizedSet(new TreeSet())
- iterators
    - fail-fast
    - throws ConcurrentModificationException if the set is modified after creation
        - exception to the rule is the iterator's remove() method.
        
## CONSTRUCTORS
TreeSet()
- new empty set, sorted by natural ordering of its elements

TreeSet(Comparator c)
- new empty set, sorted according to given comparator

TreeSet(SortedSet s)
- new tree set, initialized by elements of s, with the ordering of s.

TreeSet(Collection c)
- new tree set initialized by the elements of c, sorted according to natural
order of those elements

## METHODS
boolean add(E e)
- adds specified element to Set if not already present
    - internally uses equals() to check for dupes. 
    - rejects dupes
    - returns boolean result of add
    
Comparator comparator()
- returns comparator used to order elements
    - returns null if set is naturally ordered.
    
Object first()
    - returns lowest/first element of set
    
Object last()
    - returns largest/last element of set

void clear()
- removes all elements from set

boolean contains(Object o)
- true if set contains given object

boolean isEmpty()
- true if collection is empty

int size()
- returns no. of elements in collection

Iterator<E> iterator()
- returns iterator over elements in collection in ascending order

Iterator<E> descendingIterator()
- returns iterator over collection elements ini descending order

boolean remove(Object o)
- removes element, and returns true if it was present
    
Object clone()
- returns shallow clone of hashset

Spliterator<E> spliterator()
- creates late-binding and fail-fast Spliterator over HS elements.