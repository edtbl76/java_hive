# Iterator

    - object that implements either Iterator or ListIterator interfaces
    - used for the purposes of cycling through a collection and obtaining/removing
    elements.
    
    - ListItertor extends Iterator to allow bidirectional traversal of list and 
    modification of its elements
    
        ListIterator = bidirectional
        Iterator = unidirectional
        
    - iterator must be 'obtained' before it can be accessed. 
    
    USING AN ITERATOR
    
    - obtain an iterator to the start (or specified start) of the collection by
    calling the collection's 'iterator()' method
    
    - create a loop that makes a call to 'hasNext(). 
        - loop should iterate as long as 'hasNext()' is true
        
    - inside the loop, manage/handle each element by calling 'next()' method
    
    
### METHODS OF ITERATOR

    boolean     hasNext()           - returns true if there are more elements
                                    in 'this' Iterator
                                    
    Object      next()              - returns the next element of 'this' Iterator
                                        - throws 'NoSuchElementException' if
                                        there isn't another element
                                        
    void        remove()            - removes current element of 'this' Iterator
    
                                        - throws 'IllegalStateException' if
                                        an attempt to remove() is made that isn't
                                        preceded by 'next()'
                                        
### METHODS OF LIST ITERATOR

    void        add(Object obj)     - inserts 'obj' into 'this' list in front
                                    of the element that will be returned by the next
                                    'next()' call
                                    
    boolean     hasNext()           - returns true if there are more elements
                                        in 'this' Iterator
                                        
    boolean     hasPrevious()       - returns true if there are previous
                                    elements in 'this' Iterator
                                        
    Object      next()              - returns the next element of 'this' Iterator
                                        - throws 'NoSuchElementException' if
                                        there isn't another element
                                        
    int         nextIndex()         - returns index of next element
                                    - returns 'size of the list' if there isn't
                                    another element
                                    
    Object      previous()          - returns previous eleement of 'this' Iterator
                                        - throws 'NoSuchElementException' if
                                        there isnt' another element
                                    
    int         previousIndex()     - returns index of previous element
                                    - returns '-1' if there isn't a previous element
                                            
    void        remove()            - removes current element of 'this' Iterator
        
                                        - throws 'IllegalStateException' if
                                        an attempt to remove() is made that isn't
                                        preceded by 'next()'
                                        
    void        set(Object obj)     - assigns 'obj' to current element
                                    - the current element is the element last
                                    returned by either a call to next() or
                                    previous()
                                    
    
     

    