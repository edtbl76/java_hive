# HashSet
- implements Set interface, backed by an instance of HashMap
- provides no guarantees for iteration order
- allows null element

## HIERARCHY


    public class HashSet<E> 
        extends AbstractSet<E>
        implements Set<E>, Cloneable, Serializable
    {
        // impl code
    }
    
## FEATURES
- implements Set, Cloneable, Serializable interface
- does NOT allow duplicate values
- allows ONE null element (no dupes)
- UN-ORDERED
    - no guarantee as to iteration order
- O(1) performance for basic ops
    - add(), remove(), contains() and size()
- NOT synchronized
    - use Collections.synchronizedSet(new HashSet())
- iterators returned by class's iterator() method are fail-fast
    - throws ConcurrentModificationException if set is modified at ANY time in 
    ANY way (other than iterator.remove() method) after iterator is created. 

## Constructors

HashSet()
- initialized default HashSet w/ default capacity (16) and load factor (0.75)

HashSet(int capacity)
- inits HashSet w/ given capacity and default load factor (0.75)

HashSet(int capacity, float loadFactor)
- inits HashSet w/ given capacity and loadFactor

HashSet(Collection c)
- inits HashSet w/ same elements as given collection

## METHODS
boolean add(E e)
- adds specified element to Set if not already present
    - internally uses equals() to check for dupes. 
    - rejects dupes
    - returns boolean result of add

void clear()
- removes all elements from HashSet

boolean contains(Object o)
- true if hashset contains given object

boolean isEmpty()
- true if collection is empty

int size()
- returns no. of elements in collection

Iterator<E> iterator()
- returns iterator over elements in collection
    - iterator order is NOT guaranteed for HashSets
    
boolean removeAll(Collection<?> c)
- remove all elements in hashset that are part of specifed collection

Object clone()
- returns shallow clone of hashset

Spliterator<E> spliterator()
- creates late-binding and fail-fast Spliterator over HS elements.

## USE CASES
- most common use case is to store distinct (unique) elements
- similar to ArrayList

## PERFORMANCE
- constant time for basic operations
    - add(), remove(), contains() and size()
    - (assumes hash function properly disperses elements amongst the buckets)
- iteration is proportional to the size of the collection + the capacity of the
backing HashMap instance

NOTE: 
    - setting initial capacity too high or load factor too low will have a negative impact on
    iteration performance.