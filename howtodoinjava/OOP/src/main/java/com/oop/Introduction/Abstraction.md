# Abstraction
- Abstraction allows objects to only show relevant implementation and to simplify it. 

## DATA ABSTRACTION (INFORMATION HIDING)

    public class Employee {
    
        private firstName;
        private lastName;
        
    }
    
- Abstraction is how we create complex data structures from smaller pieces of data. 
- when we want information containing an object, we get the entire object and then ask it
questions about its properties as needed. 

## CONTROL ABSTRACTION (IMPLEMENTATION HIDING)
- In most cases, developers who want to interact w/ objects are interested in the
behavior provided by the object rather than the way that it is achieved (unless the 
objective is tuning.)
 
- Control abstraction is a means for separating the definition of behavior from the 
implementation so that developers can simply call a behavior w/o needing to understand
the internal logic of accomplishing the complex tasks that achieve the end behavior. 