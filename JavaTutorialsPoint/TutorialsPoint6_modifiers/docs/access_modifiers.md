# Modifiers

    special keywords that are added to definitions of a class, method or variable 
    to change their meanings
  
    Modifiers ALWAYS precede the rest of the statememnt
    
    
# Java Access Modifiers

    FOUR ACCESS LEVELS:
    
        - (default) visible to the package/module. (No Modifiers needed) 
            - aka package-private
            - any variable/method declared w/o a method is available to ANY other class
            in the same package. 
            
            The fields in an interface are implicitly 'public static final'
            methods in an interface are public by default. 
                
            
        - PRIVATE       visible to the class only
                (Most restrictive) 
                - Classes and interfaces can NOT be private.
                - Private variables can be accessed through public getter/setters. 
                
                - NOTE: This is the MAIN way that object provides (and enforces) 
                encapsulation and hides data from the outside world. 
        
        - PUBLIC        visible to the entire world
        
                - NOTE: this isn't AS intuitive, but if the public class we are
                        trying to access is in a different package, then the public
                        class still needs to be imported. 
                        
                        ALL public methods + variables of a class are available to 
                        subclasses (this is the basic concept of inheritance)
        
        - PROTECTED     visible to any classes within the package of the protected
                        member's class and by subclasses OUTSIDE of the package. 
                        (also not entirely intuitive) 
                        
                        
                        - methods and fields of a class can be protected
                        - methods and fields of an interface can NOT be protected
                        
                        - purpose is to give subclasses a chance to use helper methods or
                        variables while preventing unrelated classes from using it. 
                        (creates additional isolation and encapsulation). 
        
