# Collections Framework
consists of interfaces and classes which helps working w/ different types of collections
- lists
- sets
- maps
- stacks
- queues

## HIERARCHY
![alt-text](/Users/emangini/IdeaProjects/HowToDoInJava/CollectionsFramework/src/main/java/CollectionsInJava/Java-collections-interfaces.gif)

### COLLECTION
root of the hierarchy
- provides the general purpose  methods which all classes must support
- (Or throw, like UnsupportedOperationException)

- extends Iterable interface
    - adds support for iterating over collection elements using forEach.
    
### LISTS
represent ORDERED COLLECTION OF ELEMENTS
- zero-based index
- random access by accessing elements by their int index. 

Useful classes which implement List interface
- ArrayList
- CopyOnWriteArrayList
- LinkedList
- Stack
- Vector 

### SET
represents a COLLECTION OF ELEMENTS
- no duplicates allowed
- provides no guarantee to return elements in any predictable order.    
    - SOME Set implementations CAN store elements in their NATURAL ORDERING and
    guarantee that this order is retained.

Useful classes
- ConcurrentSkipListSet
- CopyOnWriteArraySet
- EnumSet
- HashSet
- LinkedHashSet
- TreeSet

### MAP
interface allowing us to store data in KV pairs
- keys must be immutable
- keys are also required to be unique 

interface provides three collection views, which allow contents to be viewed as
- set of keys
- collection of values
- set of key-value mappings
- NOTE: some implementations (i.e. TreeMap) may make guarantees about ordering, 
while others do not.

USEFUL classes which implement Map interface
- ConcurrentHashMap
- ConcurrentSkipListMap
- EnumMap
- HashMap
- Hashtable
- IdentityHashMap
- LinkedHashMap
- Properties
- TreeMap
- WeakHashMap

### STACK
represents a classic LIFO (Last In First Out) data structure
- push() to top of stack
- pop() from top of stack

### QUEUE
data structure intended to hold elements created by PRODUCER threads prior to 
processing by CONSUMER threads

provide additional operations besides basic Collection operations
- insertion, extraction, inspection

FIFO(First in First Out)
- queues are typically FIFO, but not always
    - EXAMPLE: PriorityQueue where elements are ordered 
        - according to a supplied Comparator
        - OR the element's NATURAL ORDERING
        
BlockingQueue is a special type of queue that supports blocking insertion/retrieval
operations
- this is NOT part of collections.Queue

USEFUL METHODS
- ArrayBlockingQueue()
- ArrayDeque
- ConcurrentLinkedDeque()
- ConcurrentLinkedQueue()
- DelayQueue()
- LinkedBlockingDeque()
- LinkedBlockingQueue()
- LinkedList
- LinkedTransferQueue()
- PriorityBlockingQueue
- PriorityQueue
- SynchronousQueue

### DEQUE
Double Ended Queue 
- supports element insertion/retrieval at both ends. 
- can be used as a queue (FIFO) 
- can be used as a stack (LIFO)

THIS interface should be PREFERRED to the Stack class (considered legacy)
- when using Deque as a stack, elements are pushed/popped from beginning of
the deque

USEFUL CLASSES THAT IMPLEMENT A DEQUE
- ArrayDeque
- ConcurrentLinkedDeque
- LinkedBlockingQueue
- LinkedList

## BENEFITS OF JAVA COLLECTIONS

## CONSISTENT and REUSABLE APIS
this is the advantage of any (well-written) framework
- you can solve similar set of problems repeatedly w/o getting unpredictable results
- solves common problems related to a GROUP OF OBJECTS in a consistent manner. 

regardless of data structure in use, all collection classes have consistent 
implementation and provide some common methods:
- add()
- put()
- get()
- remove()
- etc.

## LESS DEVELOPMENT TIME
a common and predictable (i.e. well-written!) framework always decreases the 
development time 
- increases the velocity at which applications can be created. 
- removes boilerplate code 

## PERFORMANCE
- many of these caliber of APIs/frameworks are written by advanced experts, 
and are highly-scrutinized to provide the best performance. 

## CLEAN CODE
- code was written in good coding practices and documented very well 
- they follow a consistent standard (and actually represent a good 
template for learning to develop)