# Flyweight Design Pattern

    - STRUCTURAL DESIGN PATTERN
    
    - primarily used to reduce the # of objects created
        - decrease memory footprint/increase performance
        
        - accomplishes this by trying to reuse alredy existing SIMILAR objects
        by storing them and creating a new object when no matching object is found. 
        
        
### Implementation
    - create a Shape interface w/ a concrete Circle class implementing Shape
    
    - uses a ShapeFactory w/ a hashMap of Circle, having the key as the color of the
    Circle object. 
        - when we need a circle, we check to see if one of the specified color 
        exists and return that. If not, we create a new one and store it in 
        the hashMap for future use. 
    