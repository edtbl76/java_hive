# PriorityBlockingQueue
- concurrent blocking version of PriorityQueue
- blocking means
    - "thread will block, waiting until there is an item available on the queue to
    process"
    
## FEATURES
Everything is the same as PriorityQueue AND
- supplies blocking retrieval operations
- thread safe. 
- iterator() doesn't guarantee traversal order
    - use Arrays.sort(pbq.toArray())
- drainTo()
    - method that is used to remove some/all elements in priority order and
    place then into another collection.
    
## CONSTRUCTORS
PriorityBlockingQueue()
- empty Q, default initial capacity (11), using natural ordering

PriorityBlockingQueue(Collection c)
- initializes Q w/ elements from given collectiono

PriorityBlockingQueue(int initialCapacity)
- empty Q, given initial capacity, using natural ordering

PriorityBlockingQueue(int initialCapacity, Comparator comparator)
- empty Q, given initial capacity, using custom ordering given my comparator.

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

Object poll(timeout, TimeUnit)
- same as a above, but w/ a timeout.

Object take()
- blocking retrieval mechanism. 
- retrieves and removes head (like poll), but it will wait until an
element becomes available

NOTE:
poll() doesn't wait before working... it is NON BLOCKING
take() waits before working... it is BLOCKING. 

void put(Object o)
- inserts specified element into priority queue, this will wait/block

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

int drainTo(Collection c)
- removes ALL available elements from queue and puts them in given collection

int drainTo(Collection c, int MaxElements)
- same as above, but only removes UP TO MaxElements

int remainingCapacity()
- always returns Integer.MAX_VALUE, because PBQ is unbounded (i.e. not
capacity constraied)

Object[] toArray()
- returns an array containing all of the elements in the queue
