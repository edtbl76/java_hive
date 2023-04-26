# LinkedList
- extends AbstractSequentialList class
- implements List, Deque interfaces
- implements Cloneable, Serializable interfaces
- sequential access only 
    - doesn't implement RandomAccess interface


## FEATURES
- Doubly Linked List
    - Can be used as:
        - Queue
        - Deque
        - Stack
- Permits all elements
    - includes DUPLICATES and NULL  
- maintains insertion order
- NOT SYNCHRONIZED
    - use Collections.synchronizedList(new LinkedList()) for synchronization
- Iterators
    - fail-fast
    - throw ConocurrentModificationoException
    - supports ListIterator
    
## CONSTRUCTORS
LinkedList()                - Empty LinkedList
LinkedList(Collection c)    - initializes LinkedList /w contents of Collection c

## USE CASES
- LinkedList + HashTable are very useful for LRU Caches. 
- Undo/Redo
- Next/Previous
- anything that requires sequential/positional access. 

- preferred when there aren't any large numbers of RANDOM ACCESSES of 
elements. 

- preferred when there are a lot of add/remove operations (and no random access :)

## PERFORMANCE
- O(1) OPERATIONS
    - add(E element)
    - iterator.remove()
    - ListIterator.add(E element)
    
- O(n) OPERATIONS
    - NOTE
        - in most of these cases, the BEST case is O(1) such that the first
        element accessed is the one we want. 
        - the worst case, is when it is the last element. 
    - get (int index)               (worst case)
    - add (int index, E Element)    (worst case)
    - remove (int index)            (worst case)