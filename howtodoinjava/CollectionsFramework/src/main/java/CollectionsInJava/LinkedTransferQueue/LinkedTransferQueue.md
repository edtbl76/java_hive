# LinkedTransferQueue
concurrent blocking queue impl
- PRODUCERS may wait for receipt of messages by consumers. 

LTQ is an impl of TransferQueue in Java

## TRANSFER QUEUE
- useful for messaging apps when producers use transfer() to wait for a receipt
of elements by consumers invoking take() or poll()

OR

- enqueueing elements (via put()) w/o waiting for receipt. 

## FEATURES
- unbounded queue on linked nodes
- FIFO ordering w/ respect to any given producer
- insertions at TAIL (shortest lived elements)
- retrievals from HEAD (longest lived elements)
- supports BLOCKING INSERTION and RETRIEVAL
- NO NULL
- THREAD SAFE
- size() is NOT a constant-time operationo
    - could report inaccurate/inconsistent results if collection is modified
    during traversal. 
    
- bulk operations do NOT GUARANTEE ATOMICITY
    - addAll()
    - removeAll()
    - retainAll()
    - containsAll()
    - equals()
    - toArray()
    - EX:
        - an iterator operating concurrently w/ an addAll() operation might
        not view ALL of the added elements
        
## CONSTRUCTORS
LinkedTransferQueue()
- constructs empty LTQ

LinkedTransferQueue(Collection c)
- initializes LTQ w/ elements of specified collection. 

## METHODS
Object take()
- retrieves + removes HEAD of queue
    - waits indefinitely until element becomes available

void transfer(Object o)
- transfers element to a consumer
    - waits indefinitely to do so
    
boolean tryTransfer(Object o)
- transfers element to a waiting consumer
    - NON BLOCKING, does it immediately if possible
    
boolean tryTransfer(Object o, long timeout, TimeUnit unit)
- transfers given element to waiting consumer
    - waits up to timeout to perform operation. 
    
int getWaitingConsumerCount()
- returns estimate of no. of consumers waiting to RCV elements via 
BlockingQueue.take() or a timed poll()

boolean hasWaitingConsumer()
- returns true if at least ONE consumer is waiting to RCV an element via
BlockingQueue.take() or a timed poll()

void put(Object o)
- inserts specified element at TAIL of queue
    - BLOCKING (it will wait)
    
boolean add(Object)
- inserts specified element at tail of queue.
    - throws IllegalStateException if Q is full
    
boolean offer(Object)
- inserts specified element at TAIL of queue
    - returns FALSE if queue is full. 
    
boolean remove(Object o)
- removes a single instance of specified element from queue
    - returns true if present
    - returns false if not present
    
Object peek()
- returns (w/o remove) HEAD of queue
    - returns null if queue is empty
    
Object poll()
- retrieves + removes HEAD of queue
    - returns null if queue is empty (NON-BLOCKING)
    
Object poll(timeout, TimeUnit)
- retrieves + removes HEAD of queue
    - waits up to timeout (SEMI-BLOCKING)
    
void clear()
- removes all elements from queue

boolean contains(Object o)
- true if object is in queue

Iterator iterator()
- returns iterator over elements of queue in FIFO order

int size()
- returns no. of elements in this queue

int drainTo(Collection c)
- removes all elements from queue and adds them to given collection

int drainTo(Collection c, int maxElements)
- removes up to maxElements from queue and adds them to given collection

int remainingCapacity()
- returns no of elements the queue can "ideally" accept w/o blocking
    - not counting memory/resource constraints
    
Object[] toArray()
- returns array containing all of the elements in this queue in FIFO order.
