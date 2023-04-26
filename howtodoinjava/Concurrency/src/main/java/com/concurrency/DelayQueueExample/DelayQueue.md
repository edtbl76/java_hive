# DelayQueue
unbounded blocking queue of delayed elements
- elements can only be taken when the delay has expired. 

## DELAYED ELEMENT
- an element is considered "delayed" if it implements java.util.concurrent.Delayed interface AND 
it's getDelay() method returns o or a negative value (which indicates the delay has expired)

- elements store "ACTIVATION DATE/TIME"
    - once this timestamp is reached, the element is "ripe" to be picked. 
    - getDelay() returns the time remaining until element can be activated. 
    
- any implementation of Delayed interface requires a compareTo() method that provides an
ordering consistent w/ its getDelay() method.

- compareTo(Delayed o)
    - doesn't return an actual timestamp
    - returns < 0 
        - if executing method has a delay < object passed as parameter
    - returns > 0
        - if executing method has a delay > object passed as parameter
    - returns 0
        - if objects have same delay.
        
## DelayQueue
specialized PriorityQueue that orders elements based on their delay time. 
- head of the queue is element whose delay expired furthest in past
    - if no element has an expired delay, there is no HEAD element in the queue
        - poll() returns null
    - unexpired elements can't be removed w/ take() or poll() but they are otherwise
    treated like any other element (i.e. they show up in size() etc.)
- doesn't support NULL elements (as they can't have a delay)