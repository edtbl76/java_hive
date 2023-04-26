# Arraylist vs LinkedList
Similarities
- both implements java.util.List interface
- provides capabilities to store/get objects as in ordered collections
- NON SYNCHRONIZED CLASSES (i.e. not Thread Safe)

- both allow duplicate elements
- both maintain INSERTION ORDER of the elements

## INTERNALS
- LinkedList = DOUBLY LINKED LIST
- ArrayList = Dynamically Resizing Array

## PERFORMANCE
- ADD/INSERT
    - ArrayList
        - O(1)          (if no resize)
        - O(log(n))     (if resize)
    - LinkedList
        - O(1)
        
- REMOVE
    - ArrayList
        - O(n)  (Worst Case)    - removing the first element
        - O(1)  (Best Case)     - removing the last element
        - (when removing an element, all of the other elements have ot
        move to the left)
        
    - LinkedList
        - O(1)
            - it only has to reset the pointers of prev/next nodes
            - no data copy/movement
     
- ITERATION
    - O(n) for ArrayList and LinkedList
    
- GET
    - ArrayList
        - O(1)  (random access via index)
    - LinkedList
        - O(1)  (best case, first element)
        - O(n)  (worst case, last element)
            - LInked Lists have to traverse the structure 
