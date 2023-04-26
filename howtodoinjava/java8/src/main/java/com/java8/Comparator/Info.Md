# COMPARATOR
- Comparator(s) are used when we want to sort a COLLECTION of objects that can be compared w/ each other. 
    - This can also be done using COMPARABLE interface, but it restricts you to compare those objects
    in a single specific manner. 
    - COMPARATOR creates greater flexibility by allowing multiple criteria/fields
    
# Syntax Reference

    //Compare By Id
    Comparator<Object> compareById1 = Comparator.comparing(o -> o.getId());
    
    Comparator<Object> compareById2 = (Object o1, Object o2) -> o1.getId().compareTo(o2.getId());
    
    // Compare by firstname
    Comparator<Object> compareByName = Comparator.comparing(o -> o.getFirstName());
    
    // HOw to use comparator
    Collections.sort(employees, compareById);
   