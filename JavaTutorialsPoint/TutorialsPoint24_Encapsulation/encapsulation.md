# ENCAPSULATION (AKA Data Hiding)

    - One of the 4 fundamental OOP concepts. 
    
    - in Java, it is the mechanism of wraping the data (variables) and
    code acting on the data (methods) together as a single unit. 
    
        - ex: 
            - variables of a class are hidden from other classes, 
            and it can be accessed ONLY through the methods of their
            current class. 
            
### RULES

    - declare the variables of a class as private
    - use public getter/setter methods to access/mutate the data values
    accordingly
    
### Benefits

    - security/access management
        - fields of a class can be made read-only or write-only by omitting
        the getter or setter respectively
        
    - a class has TOTAL control over what is stored in its field. 
            