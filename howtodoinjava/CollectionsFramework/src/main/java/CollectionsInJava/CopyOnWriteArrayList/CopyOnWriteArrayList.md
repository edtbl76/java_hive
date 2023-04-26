# CopyOnWriteArrayList

Check out ArrayList SynchronizedList. 
- as we know, ArrayLists aren't synchronized (i.e. not thread-safe).
- a SynchronizedList is a way of making it thread-safe. 

BUT

CopyOnWriteArrayList = implicitly thread-safe. 
- all mutative operations (add, set etc.) are implemented by
making a fresh copy of the underlying array. 

- it uses a SNAPSHOT style iterator method. 
- it uses a reference to the state of the backing array at the point that the
iterator is created. 
    - ARRAY NEVER CHANGES DURING LIFETIME OF THE ITERATOR
    - doesn't support element-changing operations on the iterator
    - doesn't reflect additions, removals or changes to list since iterator was created.
    
## DEFINITION
THREAD-SAFE variant of ArrayList
- all MUTATIVE operations (add, set, etc.) are impl by making a fresh copy of
the BACKING ARRAY

## IMMUTABLE SNAPSHOT ITERATOR
- this is a method of iterator that uses a reference to the state of the array at the
point the iterator was created.

- use case:
    - when TRAVERSAL operations vastly outnumber list update operations and we
    don't want to synchronize the traversals and still want THREAD SAFETY while
    updating the list.
    
## HIERARCHY

    public class CopyOnWriteArrayList<E>
        implements  List<E>,
                    RandomAccess,
                    Cloneable,
                    Serializable
    {
        private transient volatile Object[] array;
    
        // impl code
    }
    
## FEATURES
- implements List and RandomAccess
    - provides ALL features/functionality available in ArrayList class
- COSTLY for update operations
    - each mutation creates a CLONED COPY of the BACKING ARRAY and adds/updates
    elements to it.
- THREAD SAFE impl of ArrayList
    - each thread accessing the list sees its OWN version of the snapshot of
    backing  array created while initializing iterator for the list.
- does NOT throw ConcurrentModificationException
- does NOT support mutation operators on iterators (remove, set, add)
    - throws UnsupportedOperationException
- concurrent REPLACEMENT for synchronizedList
    - offers BETTER concurrency when iterations > mutations
- supports duplicate elements
- supports heterogeneous Objects
- performance is slower than ArrayList due to cloning of backing array

## CONSTRUCTORS
CopyOnWriteArrayList()
- empty list

CopyOnWriteArrayList(Collection c)
- initializes list based on elements of given collection in the order they 
would be returned by that collection's iterator

CopyOnWriteArraylist(Object[] array);
- same as above, but w/ an array. 

## METHODS
Supports ALL methods from ArrayList
- behavior varies
    - iterators are snapshotted
    - backing array is cloned during mutations
    
boolean addIfAbsent(Object o)
- append element if not present

int addAllAbsent(Collection c)
- appends ALL elements of specified collection that aren't already in the list
in the order that they are returned by collection's iterator

## USE CASES
- when there is a need to use a LIST in a concurrent environment
- when ITERATIONS outnumber MUTATIONS
- when iterators need to have a snapshotted version of the list at creation time.
- we don't want to SYNCHRONIZE thread access programmatically

## PERFORMANCE
- slower than ArrayList
