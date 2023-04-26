# ArrayBlockingQueue
- Java concurrent and bounded blocking queue implementation
- FIFO ordering
- backed by an Array

HEAD
- element that has been on the queue the longest time
    - (Queue retrievals are obtained here.)

TAIL
- element that has been on the queue the shortest time
    - (New elements are inserted at the tail)
    
## FEATURE
- bounded queue
    - backed by fixed sized array
    - ONCE CREATED, the queue capacity CAN NOT be changed. 
- FIFO ordering
- insertions at TAIL, retrievals from HEAD
    - supports blocking INSERTION AND RETRIEVAL
- NO NULL
- THREAD SAFE
- iterator() traverses elements from HEAD to TAIL
    - retrieval order
- FAIRNESS POLICY
    - optional attribute for ordering waiting PRODUCER and CONSUMER threads
    - if true, queue grants these threads access in FIFO order
    
## CONSTRUCTORS
ArrayBlockingQueue(int capacity)
- empty queue w/ fixed capacity and default access policy

ArrayBlockingQueue(int capacity, boolean fair)
- empty queue, w/ fixed capacity, and specified access policy
    - if FAIR is true, then queue accesses for threads blocked on insert/removal
    are processed in FIFO order
    - otherwise access order is unspecified

ArrayBlockingQueue(int capacity, boolean fair, Collection c) 
- queue initialized w/ elements of Collection C, w/ fixed capacity and 
specified access policy

## METHODS
void put(Object o)
- inserts specified element at TAIL of queue
    - waits for space to be available if queue is full
    - BLOCKING

boolean add(Object o)
- inserts specified element at TAIL of queue 
    - NON-BLOCKING
    - rather than wait, it throws IllegalStateException if queue is full
    - returns true on success

boolean offer(Object o)
- same as add?

boolean remove(Object o)
- if present, removes single instance of specified element from queue. 
    - returns true on success
    
Object peek()
- retrieves, w/o removing, the HEAD of the queue
    - returns null for empty queue
    
Object poll()
- retrieves AND removes HEAD of the queue
    - returns null for empty queue
    
Object poll(timeout, TimeUnit)
- same as above, but it is semi-BLOCKING (waits up to specified timeout
for an element to be available)

object take()
- same as poll(), but it will wait indefinitely (fully BLOCKING) for an
element to become available

void clear()
- removes all elements from this queue

boolean contains(Object o)
- returns true if the given element is a member of the queue

iterator iterator()
- returns iterator over elements in queue (FIFO)

int size()
- returns no of elements in queue

int drainTo(Collection c)
- removes all available elements from queue and adds them to specified collection

int drainTo(Collection c, int maxElement)
- removes up to maxElement elements from queue and adds them to specified collection

int remainingCapacity()
- returns no. of additional elements that queue can ideally accept w/o blocking
    - not counting memory/resource constrains
    
Object[] toArray()
- returns array containing all of the elements within the queue in proper FIFO
order.


    