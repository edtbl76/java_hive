# POLYMORPHISM
- this is a way to create functions/reference variables that can behave differently in 
different contexts. 
    - I prefer to think of it as different versions of the same basic function, w/ a variation
    in implementation or the data being managed. 
    
# METHOD OVERLOADING
- This is COMPILE TIME POLYMORPHISM
- I consider this HORIZONTAL POLYMORPHISM or PARAMETER-BASED POLYMORPHISM
    - the reason I think of it this was is that the variation between multiple methods for
    this kind of polymorphism is within the parameter list of the method's signature. 

# METHOD OVERRIDING
- This is RUNTIME POLYMORPHISM
- I consider this VERTICAL/HIERARCHICAL POLYMORPHISM. 
    - the reason I think of it this way is that the parameter lists don't necessarily have
    to vary (and in many cases they don't!)
    - the implementation varies here. 
        - a good example is the exception hierarchy where many derived exceptions are 
        overriding the same methods with different messages based on the context intended
        by the derived exceptions representation.