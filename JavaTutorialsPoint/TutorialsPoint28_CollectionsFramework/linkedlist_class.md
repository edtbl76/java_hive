# LinkedList

    extends AbstractSequentialList, implements List (and subsequently Collections)
    
    provides a standard linked-list data structure
    
### CONSTRUCTOR

    LinkedList()                - constructor builds an empty linked list
    LinkedList(Collection c)    - constructor builds a linked list that is initialized w/ 
                                elements of 'c'
                                
                                
### METHODS

    - inherits methods from AbstractSequentialList, List and Collections
    
    AND
    
    1   void                    add(int index, Object element)]
    
                                - inserts 'element' at 'index' in 'this' LinkedList
                                    throws 'IndexOutOfBoundsException' if 'index' is
                                    out of range
                                        (index < 0 || index > size())
                                        
    2   boolean                 add(Object o) 
    
                                - appends 'o' to end of 'this' LinkedList
                                        
    3   boolean                 addAll(Collection c) 
    
                                - appends all of elements from 'c' to end of 'this' 
                                LinkedList in the order that they are returned by
                                'c''s iterator. 
                                    - throws 'NPE' if 'c' is null
                                    
    4   boolean                 addAll(int index, Collection c)
                                
                                - inserts all elements from 'c' into 'this' LinkedList
                                at 'index' in the order that they are returned by
                                'c''s iterator
                                    - throws 'NPE' if 'c' is null
                                    
    5   void                    addFirst(Object o)
                            
                                - inserts 'o' at the beginning of 'this' LinkedList
                                
    6   void                    addLast(Object o)
                    
                                - appends 'o' to end of 'this' LinkedList
                                
    7   void                    clear()
                    
                                - removes all elements from 'this' LinkedList
                                
    8   Object                  clone()
    
                                - returns a SHALLOW copy of 'this' LinkedList
                                
    9   boolean                 contains(Object o) 
    
                                - returns true if 'this' LinkedList contains 'o'.
                                
    10  Object                  get(int index) 
        
                                - returns element at 'index' from 'this' LinkedList
                                    - throws IndexOutOfBoundsException if 'index' is
                                    out of range
                                        (index < 0 || index > size())
                                        
    11  Object                  getFirst() 
                                 
                                - returns first element from 'this' LinkedList
                                    - throws NoSuchElementException if 'this' LL is 
                                    empty
                                    
    12  Object                  getLast()
    
                                - returns last element from 'this' LL 
                                    - throws NoSuchElementException if 'this' LL is
                                    empty
                                    
    13  int                     indexOf(Object o)
                                
                                - returns 'index' of the first occurence of 'o' in
                                'this' LL
                                - returns '-1' if 'o' isn't in 'this' LL
                                
    14  int                     lastIndexOf(Object o) 
        
                                - returns 'index' of the last occurence of 'o' in
                                'this' LL
                                - returns '-1' if 'o' isn't in 'this' LL
                                
    15  ListIterator            listIterator(int index) 
                                
                                - returns list-iterator of elements in 'this' LL
                                (in proper sequent) starting at 'index"
                                    - throws IndexOutOfBoundsException if 'index' is
                                    out of range
                                        (index < 0 || index >= size())
                                        
    16  Object                  remove(int index) 
                        
                                - removes element at 'index' from 'this' LL
                                    - throws NoSuchElementException if 'this' LL
                                    is empty
                                    
    17  boolean                 remove(object o) 
                            
                                - removes first occurence of 'o' from 'this' LL
                                    - throws NoSuchElementException if 'this' LL
                                    is empty. 
                                    - throws IndexOutOfBoundsException if 'index' is
                                    out of range
                                        (index < 0 || index >= size())
                                        
    18  Object                  removeFirst()
    
                                - removes + returns first element from 'this' LL
                                    - thows NoSuchElementException if 'this' LL
                                    is empty
                                    
    19  Object                  removeLast()
        
                                - removes + returns last eleme3nt from 'this' LL
                                    - throws NoSuchElementException if 'this' LL
                                    is empty
                                    
    20  Object                  set(int index, Object element) 
                                
                                - replaces element at 'index' from 'this' LL with
                                'element'
                                    - throws IndexOutOfBoundsException if 'index'
                                    is out of range
                                        (index < 0 || index >= size())
                                        
    21  int                     size()
    
                                - returns # of elements in 'this' LL
                                
    22  Object[]                toArray()
    
                                - returns an array containing all of the elements
                                of 'this' LL (in the correct order)
                                    - throws 'NPE" if 'this' array is null
                                    
    23  Object[]                toArray(Object[] a)
    
                                - returns an array containing all of the elements
                                of 'this' LL (in the correct order) 
                                    - the runtime of the returned array is 'a'
                                    

                                  
                            
                               
                                
                                