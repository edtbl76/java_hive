# Comparator
Interface used to sort Array/List of objects based on CUSTOM order. 
    - ordering is imposed by implementation of Comparator.compare() method. 
    
## INTERFACE
- basically the interface gives you TOTAL ORDERING on objects which either may not
have natural ordering, or where natural ordering is sufficient for the use case

## USE CASES
- sorting arrays/lists in an order other than natural ordering
- sorting arrays/lists of objects where the object's source can't be modified to
implement Comparable interface
    - EX: 3rd party code
- sort the same list/array of objects, but on different fields
- group-by-sort on lists/arrays of objects on different fields. 

## compare()
- compares 2 arguments for order
        
| comparison | result | 
| --- | --- |
| 1st arg < 2nd arg | negative integer |
| 1st arg == 2nd arg | 0 |
| 1st arg > 2nd arg | positive integer | 

- relation must be TRANSITIVE
    - (compare(x,y) > 0) && (compare(y,z) >0) 
        - implies (compare(x,z) > 0)
        
## sort()
Collections.sort(list, Comparator)
- sorts list of objects imposed by given Comparator

Arrays.sort(array, Comparator)
- sorts array of objects imposed by given Comparator

## Collections.comparing()
- accepts a function that extracts a sort key for the class. 
- this is essentially a field on which the class objects will be sorted

    
    EX:
        // Ordering by name
        Comparator.comparing(ComparablePerson::getName);
        
        // Order by name (reversed)
        Comparator.comparing(ComparablePerson::getName).reversed();
        
        // id
        Comparator.comparing(ComparablePerson::getId);
        
        // DOB (although I didn't implement!) 
        Comparator.comparing(ComparablePerson::getAge);
        
## thenComparing();
- this is used for GROUP-BY-SORT
    - this chains multiple comparators to allow sorting on multiple fields
    - (kinda like SQL GROUP BY)
    
    
    EX:
        // name then age
        Comparator.comparing(ComparablePerson::getName)
            .thenComparing(ComparablePerson::getAge);
            
        // name -> age -> ID
        Comparator.comparing(ComparablePerson::getName)
            .thenComparing(ComparablePerson::getAge)
            .thenComparing(ComparablePerson::getId);
            
## reverseOrder()
returns a comparator that imposes the reverse of NATURAL or TOTAL ordering on 
a collection of objects that implements COMPARABLE interface.


    EX:
        // Natural
        Comparator.reversed();
        
        // TOTAL
        Comparator.comparing(ComparablePerson::getName).reversed();
       



