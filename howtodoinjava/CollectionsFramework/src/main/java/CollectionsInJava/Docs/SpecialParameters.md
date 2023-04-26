# Special Parameters

## INITIAL CAPACITY
The number of buckets in (backing structure) when complex collections are created
- i.e. the linked list in a HashMap
- i.e. doubly linked list in a LinkedHashMap
- i.e. HashMap in a HashSet

The number of buckets are automatically increased if the initial capacity is reached

## LOAD FACTOR
A measure of how full a Collection is allowed to get before its capacity is
automatically increased. 
- default is usually 0.75

THRESHOLD
- DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY
- when no. of elements exceeds threshold, the Collection will be resized, and the
new capacity is 2x initial capacity. 

EXAMPLE:
    w/ defaults (16 and 0.75), the Collection is resized when it reaches 12
    elements, and the new size will be 32. 

