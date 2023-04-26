# Java Comparator

    - TreeSet and TreeMap data structures store their elements by default in 'SORTED 
    ORDER'
    
        Comparator structure determines what 'sorted order' actually means. 
        
    
    Comparator Interface defines TWO METHODS
    
        int compare(Object obj1, Object obj2)
        
            returns 0 if objects are equal
            returns positive if obj1 > obj2
            returns negative if obj1 < obj2
            
            - by overriding this method, the manner in which objects are ordered
            can be changed. 
                - EX: to create a reverse order sort, we create a comparator taht
                reverses the outcome of a comparison
            
        
        boolean equals(Object obj)
            
            - returns true if obj and 'this' object are:
                - BOTH comparator objects
                - and use the same ordering. 
                
            - no need to override this. This is just used by the comparator for
            tests of equality in the compare() method