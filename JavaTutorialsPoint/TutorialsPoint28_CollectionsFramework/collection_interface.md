# COLLECTION INTERFACE

    - foundation on which Collections Framework is built
    
    - declares core methods that all collections will have
    
    
### METHODS

    1   boolean                 add(object obj) 
    
                                - adds 'obj' to 'this' collection'
                                    - returns true if 'obj' was added
                                    - returns false if 'obj' already exists or
                                    if collection doed not allow duplicates
                                    
    2   boolean                 addAll(Collection c) 
    
                                - adds all elements in 'c' to 'this' 
                                collection
                                    - returns true if operation exceeds, else false
                                    
    3   void                    clear()
                        
                                - removes all elements from 'this' collection
                                
    4   boolean                 contains(Object obj)
                            
                                - returns true if 'obj' exists in 'this'
                                collection
                                
    5   boolean                 containsAll(Collection c) 
                                
                                - returns true if 'this' collections contains ALL
                                elements from 'c'
                                
    6   boolean                 equals(Object obj) 
    
                                - returns true if 'this' collection is equal to 
                                'obj'
                                
    7   int                     hashCode()
    
                                - returns the hash code for 'this' collection
                                
    8   boolean                 isEmpty()
                
                                - returns true if 'this' collection is empty
                                
    9   Iterator                iterator()
                                
                                - returns an iterator for 'this' collection
                                
    10  boolean                 remove(Object obj) 
                            
                                - removes one instance of 'obj' from 'this' collection
                                    - returns true if 'obj' was removed
                                    
    11  boolean                 removeAll(Collection c) 
    
                                - removes all elements of 'c' from 'this' collection. 
                                    - returns true if some elements were removed
                                    
    12  boolean                 retainAll(Collection c) 
            
                                - removes all elements from 'this' collection that
                                aren't also in 'c'
                                    - returns true if the collection changed (i.e. 
                                        elems were removed) 
                                        
    13  int                     size()
    
                                - returns the # of elements in 'this' collection
                                
    14  Object[]                toArray()
    
                                - copies elements from 'this' collection to an array, and
                                returns that array.
                                
    15  Object[]                toarray(Object array[])
            
                                - copies elements from 'this' collection that are also in
                                'array[]' to a new array and returns that array. 
                                
                              
                                
    