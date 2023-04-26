# IMPORT STATEMENT/KEYWORD

    - if a class wants to use another class in the same package, the 
    package name doesn't need to be used. 
    
    
        EX:
        
            package payroll;
            public class Boss {
                public void youreFired(Employee e) {
                        e.seeYa();   
                }
            }
            
        - if employee class isn't in the payroll package then we have to 
        do one of the following:
        
            - FULLY QUALIFIED NAME
                - refer to Employee by its fully qualified name
                    i.e. <parent package>.Employee
                    
            -  WILDCARD import
            
                - import <parent package>.*;
                    - note this a bit ambiguous w/ respect to memory usage
                    
            - CLASS import
            
                - import <parent package>.Employee
                - (NOTE: this is different from the first case as here we
                are importing the class, whereas in the first case, we
                are REFERRING to the entire fully qualified name inside the
                code)
                

            
            