# VECTOR

    - similar to traditional array
    
        DIFFERENCES
        - it can grow as necessary to accommodate
    new elements. 
        - it is 'synchronized'
            (This means that it will block threads, i.e. it is 
            thread safe). Arrays are NOT thread safe.       
    
        
    - dynamically shrinks/grows. 
        - double-edge sword. 
            - more convenient, but can lead to memory pressure
            
    - has many legacy methods not available in the Collections framework. 
            
            
### CONSTRUCTORS

    1   Vector()                creates a default vector (initial size of 10)
                                with default increment of 1
                                
    2   Vector(int size)        creates a vector with initial size of 'size' with
                                default increment of 1
                                
    3   Vector(int size, int incr)
                                creates a vector with initizl size of 'size'
                                that creates 'incr' # of 'slots' each time
                                it needs to be resized upward. 
                                
    4   Vector(collection c) 
                                creates a vector that contains the elements of
                                collection c
                                
### Methods

    1   void                    add(int index, Object element) 
                                - inserts specified element at specified 'index'
                                
    2   boolean                 add(Object o) 
                                - appends specified element at end of 'this'
                                vector
                                
    3   boolean                 addAll(Collection c) 
                                - appends all elements of 'c' to end of
                                'this' vector in the order that they are
                                returned by 'c''s Iterator
                                
    4   boolean                 addAll(int index, Collection c) 
    
                                - inserts all elements of 'c' into 'this' 
                                vector at 'index', in the order that they are
                                returned by 'c''s Iterator
                                
    5   void                    addElement(Object obj)
                                - adds 'obj' to end of this vector, 
                                increasing its size by one. 
                                
    6   int                     capacity()
                                - returns current capacity of 'this' vector
                                
    7   void                    clear()
                                - removes all elements from 'this' vector
                                
    8   Object                  clone()
                                - returns a clone of 'this' vector
                                
    9   boolean                 contains(Object elem)
                                - returns true if 'elem' exists in 'this' 
                                vector
                                
    10  boolean                 contains(Collection c) 
                                - returns true if all elements of 'c' also
                                exist in 'this' vector
                                
    11  void                    copyInto(Object[] anArray)
                                - copies components of 'this' vector into
                                'anArray'
                                
    12  Object                  elementAt(int index)
                                - returns element at 'index'
                                
    13  Enumeration             elements()
                                - returns an enumeration of the components
                                of 'this' vector
                                
    14  void                    ensureCapacity(int minCapacity) 
                                - increases capacity of 'this' vector to 
                                ensure that it can support the number of 
                                components specified by 'minCapacity'
                                
    15  boolean                 equals(Object o)
                                - returns true if 'o' is equal to 'this' 
                                vector
                                
    16  Object                  firstElement()
                                - returns component at index 0 of 'this' 
                                vector
                                
    17  Object                  get(int index) 
                                - returns component at 'index' of 'this' 
                                vector
                                
    18  int                     hashCode()
                                - returns hashcode value for 'this' vector
                                
    19  int                     indexOf(Object elem) 
                                - returns index of the first occurence of
                                'elem' in 'this' vector
                                    (NOTE: uses equals() method against
                                    each index to find 'elem')
                                    
    20  int                     indexOf(Object elem, int index) 
                                 - returns index of the first occurence of
                                 'elem' in 'this' vector, beginning at
                                 'index'
                                    (NOTE: uses equals() method against
                                    each index to find 'elem')
                                    
    21  void                    insertElementAt(Object obj, int index) 
                                - inserts 'obj' at 'index' in 'this'
                                vector
                                
    22  boolean                 isEmpty()
                                - returns true if there are no components
                                in 'this' vector
                                
    23  Object                  lastElement()
                                - returns last component of 'this' vector
                                
    24  int                     lastIndexOf(Object elem)
                                - returns index of  the last occurrence of 
                                'elem' in 'this' vector
                            
    25  int                     lastIndexOf(Object elem, int index) 
                                - returns index of last occurrence of
                                'elem' in 'this vector, searching backwards
                                from 'index'
                                
    26  Object                  remove(int index) 
                                - removes element at 'index' from 'this' vector
                                
    27  boolean                 remove(Object o)
                                - removes first occurence of 'o' in 'this'
                                vector
                                    (NOTE: if it isn't there, nothing happens!)
                                    
    28  boolean                 removeAll(Collection c) 
                                - removes any element from 'c' that exists
                                in 'this' vector
                                
    29  void                    removeAllElements()
                                - removes all components from 'this' vector
                                and sets the size to 0
                                
    30  boolean                 removeElement(Object obj) 
                                - removes the first(lowest indexed) occurrence
                                of 'obj' from 'this' vector
                                
    31  void                    removeElementAt(int index) 
                                -  removes component at 'index' from 'this'
                                vector
                                
    32  protected void          removeRange(int fromIndex, int toIndex)
                                - removes from 'this' vector all of the elements
                                are from 'fromIndex' (inclusive) to
                                'toIndex' (exclusive)  
                                
    33  boolean                 retainAll(Collection c) 
                                - remove all elements from 'this' vector that do
                                NOT exist in 'c'
                                
    34  Object                  set(int index, Object element)
                                - replaces component at 'index' w/ 'element' in
                                'this' vector
                                
    35  void                    setElementAt(Object obj, int index) 
                                - sets component at 'index' to be 'obj' in
                                'this' vector
                                
    36  void                    setSize(int newSize)
                                - sets size of vector to 'newSize'
                                
    37  int                     size()
                                - returns no. of components in 'this' vector
                                
    38  List                    subList(int fromIndex, int toIndex)
                                - returns a List containing components of
                                'this' vector beginning at 'fromIndex' (inclusive)
                                and ending at the element before 'toIndex' (i.e
                                not exclusive) 
                                
    39  Object[]                toArray()
                                - returns an array containing all elements
                                in 'this'vector, retaining the order. 
                                
    40  Object[]                toArray(Object[] a)
                                - returns an array containing all elements
                                in 'this' vector, retaining the order. 
                                - the runtime type of the returned array
                                is that of 'a'
                                
    41  String                  toString()
                                - returns String representation of 'this'
                                Vector, containing String representation
                                of each element
                                
    42  void                    trimToSize()
                                - trims capacity of 'this' vector to be 
                                the vector's "current" size. (not the length!) 
                                    - i.e. this removes unused allocated
                                    slots in memory
                                    
                                    
                                
                                
                      
                               
            
    
            
    