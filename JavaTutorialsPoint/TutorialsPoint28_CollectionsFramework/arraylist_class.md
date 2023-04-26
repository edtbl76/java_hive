# ArrayList

    - extends AbstractList and implements List interface
    - supports dynamic arrays that can grow as needed
    
    - standard Java arrays are of fixed length
        - can't grow/shrink, so you have to know in advance how many elements the
        array will hold
        
    - ArrayLists are created w/ inital size. 
        - when size is exceeded, the collection is automatically enlarged. 
        - when objects are removed, the array may be shrunken
        
### CONSTRUCTORS

    1   ArrayList()                 - constructor builds an empty array list
    2   ArrayList(Collection c)     - builds an arraylist initialized w/ elements
                                    from 'c'
                                    
    3   ArrayList(int capacity)     - builds empty arraylist w/ specified
                                    initial capacity.
                                        - capacity is the size of the underlying arrya that is 
                                        used to store elements. Capacity grows automatically 
                                        as elements are added to any array list.
                                        
### METHODS

    1   void                    add(int index, Object element) 
    
                                - inserts 'element' at 'index' in 'this' ArrayList
                                
                                    - throws IndexOutOfBoundsException if 'index' is
                                    out of range
                                        (index < 0 || index >= size())
                                        
    2   boolean                 add(Object o) 
        
                                - appends 'o' to 'this' AL
                                
    3   boolean                 addAll(Collection c) 
        
                                 - appends all elements of 'c' to end of 'this' AL, in
                                 the order that they are returned by 'c''s iterator
                                    - 'throws' NPE if c is null
                                    
    4   boolean                 addAll(int index, Collection c)
    
                                - inserts all elements of 'c' into 'this' AL at
                                'index' in the order that they are returned by 'c''s
                                iterator
                                    - throws NPE if c is null
                                    
    5   void                    clear()
    
                                - removes all elements from 'this' AL
                                
    6   Object                  clone()
    
                                - returns shallow copy of 'this' AL
                                
    7   boolean                 contains(Object o)
    
                                - returns true if 'this' AL contains at least one 
                                instance of 'o'
                                
    8   void                    ensureCapacity(int minCapacity) 
    
                                - increases capacity of 'this' AL, to ensure it
                                can hold at least 'minCapacity' no. of elements
                                
    9   Object                  get(int index) 
            
                                - returns element at 'index' from 'this' AL
                                    throws 'IndexOutOfBoundsException' if
                                    'index' is out of range
                                        ('index' < 0 || 'index' >= size())
                                        
    10  int                     indexOf(Object o) 
                    
                                - returns index from 'this' AL of the first 
                                occurence of 'o'
                                - returns '-1' if 'o' is not in 'this' AL
                                
    11  int                     lastIndexOf(Object o) 
            
                                - returns index from 'this' AL of the last
                                occurence of 'o'. 
                                - returns '-1' if 'o' is not in 'this' AL
                                
    12  Object                  remove(int index) 
    
                                - removes element from 'this' AL at 'index'
                                    throws 'IndexOutOfBoundsException' if
                                    index is out of range
                                        (index < 0 || index >= size())
                                        
    13  protected void          removeRange(int fromIndex, int toIndex) 
    
                                - removes elements from 'this' AL between 
                                'fromIndex' (inclusive) and 'toIndex' (exclusive) 
                                
    14  Object                  set(int index, Object element)
    
                                - replaces element in 'this' AL at 'index' w/ 
                                'element'
                                    - throws 'IndexOutOfBoundsException if
                                    'index' is out of range
                                        (index < 0 || index >= size())
                                        
    15  int                     size()
    
                                - returns # of elements in 'this' AL
                                
    16  Object[]                toArray()
    
                                - returns an array containing all of the elements
                                in 'this' AL in the correct order
                                    - throws NPE if 'this' AL is null
                                    
    17  Object[]                toArray(Object[] a)
    
                                 -returns an array containing all of the elements
                                 in 'this' AL in the correct order
                                    - the runtime type of the returned array is
                                    'a'
                                    
    18  void                    trimToSize()
    
                                - trims capacity of 'this' AL to the its current
                                size (i.e. de-allocates unused indices'
                                
        
                             