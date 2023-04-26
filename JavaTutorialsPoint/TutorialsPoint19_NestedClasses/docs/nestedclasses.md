# Nested Classes

    - variables of a class too can have another class as its member. 
    
    - NESTED CLASS (Inner class)
    
    - OUTER CLASS (class that holds the inner class) 
    

## Hierarchy

                    Nested Classes
          __________________|___________________________
          |                                             |
     Static Nested Classes                       Inner Classes
                         ______________________________|_______________________
                        |                   |                                  |
              Inner Classes        Anonymous Inner Classes          Method Local
                                                                    Inner Classes
                                                                    
                                                                    
### What they are used for

    - Security
        - a class cannot be associated w/ access modifier private, 
        but if a class is a member of another class, then the inner class
        CAN be made private. 
        
    - Inner Classes are used to access private members of a class. 
        - write an inner class in a class
        - return private members of outer class from a method within the inner class. 
        - from the class you want to access private members, 
        call the method of the inner class. 
        
        
            
            

### TYPES

    Inner Class
        
            - just write a class within a class. 
            - inner class can be private
                - prevents the class from being accessed from obj
                outside the class. 
                

    Method Local Inner Class
        
            - writing a class within a method
            - this gives the class 'local' scope, so it is treated like
            local variablesm such that scope is restricted within the
            method.
            
    Anonymous Inner Classes
        
            - inner class declared w/o a class name. 
            - these are DECLARED and INSTANTIATED at the same time. 
            
            - these are used whenever you need to overide the method of 
            a class or an interface. 
            
            EX: 
            
                AnonymousInner an_inner = new AnonymousInner() {
                  public void my_method() {
                    // blah
                  }
                }
                
                
              
                                                                                