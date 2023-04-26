#   List Interface

    - extends Collection
    
    BEHAVIOR
        - elements can be inserted/accessed by their position using a 0-based index
        - elements don't have to be unique
        - inherits methods from Collection and adds some of its own to reinforce 
            its additional behavior
        
    EXCEPTIONS
    
        UnsupportedOperationException
            - means that a collection can't be modified
            
        ClassCastException
            - generated when one object is incompatible w/ another
            
            
### METHODS

    1   void                    add(int index, Object obj)
                                - inserts 'obj' at 'index' of 'this' List. 
                                - existing elements at/beyond 'index' are shifted upward
                                
    2   boolean                 addAll(int index, Collection c)
                                - inserts all elements of 'c' at 'index' of 'this' List
                                - existing elements at/beyond 'index' are shifted upward
                                - returns true if 'this' List changes
                                
    3   Object                  get(int index) 
                                - returns element at 'index' of 'this' List
                                
    4   int                     indexOf(Object obj) 
                                - returns index of first instance of 'obj' from 
                                'this' List. 
                                - returns '.1' if 'obj' is not in 'this' List
                                
    5   int                     lastIndexOf(Object obj) 
                                - returns index of last instance of 'obj' from 'this'
                                List
                                - returns '.1' if 'obj' is not in 'this' List
                                
    6   ListIterator            listIterator()
                                - returns an iterator to the start of 'this' List
                                
    7   ListIterator            listIterator(int index) 
                                - returns an iterator to 'this' list that starts at 'index'
                                
    8   Object                  remove(int index)
                                - removes and returns element at 'index' from 'this' list. 
                                - resulting list is compacted (the index of all elements above 
                                'index' are decremented by one) 
                                
    9   Object                  set(int index, Object obj) 
                                - assigns 'obj' to 'index' in 'this' list.
                                - OVERWRITES DATA
                                
    10  List                    subList(int start, int end) 
                                - returns a new List that includes elements from 'start' to
                                'end' from 'this' List. 
                                - elements in new List are also referenced by 'this' List
                                
                                
                               
                                
                                