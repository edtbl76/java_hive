# LinkedHashSet
Similar to HashSet, but it offers PREDICTABLE ITERATION ORDER

## HIERARCHY

    public class LinkedHashSet<E> 
        extends HashSet<E>
        implements Set<E>, Cloneable, Serializable
    {
        impl code
    }
    
## FEATURES
- extends HashSet (which extends AbstractSet)
- implements Set, Cloneable, and Serializable interfaces
- NO DUPLICATES ALLOWED
- Allows ONE null element (no dupes)!
- ORDERED COLLECTION 
    - supports insertion order
- Constant Time Performance 
    - add(), remove(), contains(), size()
- NOT Synchronized (not thread safe)
    - Use Collections.synchronizedSet(new LinkedHashSet())
- iterators are fail-fast
    - throw ConcurrentModificationException if the set is modified at any time
    and in any way after iterator is created
        - (unless modified by iterator.remove())
        
## Constructors

LinkedHashSet()
- initialized default LinkedHashSet w/ default capacity (16) and load factor (0.75)

LinkedHashSet(int capacity)
- inits LinkedHashSet w/ given capacity and default load factor (0.75)

LinkedHashSet(int capacity, float loadFactor)
- inits LinkedHashSet w/ given capacity and loadFactor

LinkedHashSet(Collection c)
- inits LinkedHashSet w/ same elements as given collection

## METHODS
boolean add(E e)
- adds specified element to Set if not already present
    - internally uses equals() to check for dupes. 
    - rejects dupes
    - returns boolean result of add

void clear()
- removes all elements from set

boolean contains(Object o)
- true if set contains given object

boolean isEmpty()
- true if collection is empty

int size()
- returns no. of elements in collection

Iterator<E> iterator()
- returns iterator over elements in collection
    - iterator order is NOT guaranteed for HashSets

boolean remove(Object o)
- removes element, and returns true if it was present
    
boolean removeAll(Collection<?> c)
- remove all elements in set that are part of specifed collection

Object clone()
- returns shallow clone of hashset

Spliterator<E> spliterator()
- creates late-binding and fail-fast Spliterator over HS elements.

## USE CASES
- This is basically a HashSet that guarantees iteration order. 

## PERFORMANCE
- slightly less performant than a HashSet, because it has the added overhead of
maintaining the linked list

## USE CASES
- TreeSet uses RED-BLACK TREE (RBT) for ordering. 
- this is very useful when you need a structure which is r/w frequently and needs
to stay ordered. 