# TREESET CLASS

    - implements Set interface 
    
    - uses a Tree for storage
    - objects are stored and immediately sorted in ascending order. 
    
    - ACCESS/RETREIVAL is very fast. 
        - popular choise for storing large amounts of sorted info that must be
        retrieved quickly
        
### CONSTRUCTORS

    TreeSet()                   - builds empty tree sorted in ascending order
            `                   according to the 'natural order' of its elements
            
    TreeSet(Collection c) 
                                - builds tree initialized by the elements of 'c'
                                
    TreeSet(Comparator comp)
                                - constructs empty TreeSet (TS) that will be 
                                sorted according to the given comparator
                                
    TreeSet(SortedSet ss) 
                                - builds TS that contains elements of 'ss'
                                
### METHODS

    void    add()               - adds specified element to 'this' TreeSet (TS) 
                                if not already present
                                
    boolean addAll(Collection c)           
                                - adds elements of 'c' to 'this' TS
                                
    void    clear()             - removes all elements from 'this' TS
    
    Object  clone()             - returns shallow copy of 'this' TS
    
    Comparator  comparator()    - returns comparator used to order 'this' TS
                                - returns null if 'this' TS uses thje 'natural 
                                ordering' of its elements
                                
    boolean contains(Object o)  - returns true if 'this' TS contains 'o'
    
    Object  first()             - returns the first/lowest element from 'this' TS
    
    SortedSet   headSet(Object toElement)
                                - returns a set-view of the elements from 'this' TS
                                that are < 'toElement'
                                
    boolean isEmpty()           - returns true if 'this' TS is empty
    
    Iterator    iterator()      - returns an iterator over the elements of 'this' TS
    
    Object  last()              - returns last/highest element currently in 
                                'this' TS
                                
    boolean remove(Object o)    - removes 'o' from 'this' TS if it is present
    
    int size()                  - returns cardinality(# of elements) in 'this' TS
    
    SortedSet subSet(Object fromElement, Object toElement)
    
                                - returns a set-view of the elements from 'this' TS
                                that are >= 'fromElement' and < 'toElement'
                                
    SortedSet   tailSet(Object fromElement) 
        
                                - returns a set-view of the elements from 'this' TS
                                that are >= 'fromElement'
                                
                                
                        