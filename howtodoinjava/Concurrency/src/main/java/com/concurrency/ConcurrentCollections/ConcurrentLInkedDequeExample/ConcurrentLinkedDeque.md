# ConcurrentLinkedDeque
aka Non-Blocking Thread-Safe List

LISTS
- undetermined # of elements you can add/read/remove randomly from the data structure

CONCURRENT LISTS
- allow various threads to add/remove elements in the structure at a single moment in time
w/o producing any data inconsistency

NON-BLOCKING LIST
- provides operations that throw an exception/return a null value if the operation can't
be performed immediately

## USEFUL METHODS
getFirst(), getLast()
- returns first/last element from list
    - doesn't remove returned values
    - throws NoSuchElementException if list is empty

peek(), peekFirst(), peelLast()
- return first and last element of list
    - don't remove returned element
- return null if list is empty

remove(), removeFirst(), removeLast()
- return first and last element of list
    - they DO remove returned element
    
## COMMON USE CASE
- ConcurrentLinkedDeque is a good choice when MANY threads will share access to a 
COMMON COLLECTION
- NO NULL ELEMENTS


    