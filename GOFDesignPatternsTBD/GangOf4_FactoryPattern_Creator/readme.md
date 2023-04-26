# Factory Pattern

    - CREATIONAL  PATTERN
    
        - create objects w/o exposing creation logic to client
        - refer to newly created objects using a common interface. 
        
        
        
    FactoryPatternDemo
        - main():void
        
        asks
        
    ShapeFactory
        getShape():Shape
        
        
        CREATES
        
    Shape(Interface)
        - draw():void
        
        Circle (implements Shape) 
            draw():void
            
        Square (implemments Shape)
            draw():void
            
        Rectangle (implements Shape) 
            draw():void