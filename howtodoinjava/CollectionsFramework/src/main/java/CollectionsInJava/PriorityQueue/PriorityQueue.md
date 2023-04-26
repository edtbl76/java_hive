# Priority Queue
queue data structure implementation 
- objects processed based on order of priority
    - objects are added according to their priority
        - priority is determined by
            - natural ordering (default)
            - Comparator provided at queue construction time

## FEATURES
- unbounded queue and grows dynamically
    - default initial capacity = 11
- no NULL objects
- Objects add to PQ MUST be comparable
    - throws  ClassCastException if they aren't.
- default ordering is natural order
    - can be overriden by Comparator
- HEAD
    - "least" element based on the natural ordering/comparator based ordering
    - polling the queue returns the head object
    - if multiple objects of equal priority exist, ahy one of them may be
    polled randomly
- Not Thread Safe
    - Use PriorityBlockingQueue
- O(log(n)) time for add() and poll()

## CONSTRUCTORS

PriorityQueue()
- empty queue with default initial capacity(11) and natural ordering

PriorityQueue(Collection c)
- initializes queue w/ elements of collection

PriorityQueue(int initialCapacity)
- empty queue w/ given initial capacity and natural ordering

PriorityQueue(int initialCapacity, Comparator comparator)
- empty queue w/ given initial capacity  and comparator ordering

PriorityQueue(PriorityQueue c)
- initializes queue from another PriorityQueue

PriorityQueue(SortedSet c)
- initializes queue w/ contents of SortedSet

## METHODS
boolean add(Object o)
- inserts specified element 

boolean offer(Object o)
- inserts specified element

boolean remove(Object o)
- removes single instance of specified element, if it is present

Object poll()
- retrieves and removes HEAD 
- returns null if queue is empty

Object element()
- retrieves HEAD (but doesn't remove it)
- returns null if queue is empty

void clear()
- removes all elements

Comparator comparator()
- returns comparator used to order elements
- returns null if queue is sorted according to natural ordering

boolean contains(Object o)
- returns true if element exists in queue

iterator iterator()
- returns an iterator over the elements in the queue

int size()
- returns no. of elements in queue

Object[] toArray()
- returns an array containing all of the elements in the queue
