# Prototype

    - CREATIONAL PATTERN
    
    - creating duplicate object while keeping performance in mind. 
    
    - provides a 'prototype' interface which creates a clone of the current object
    - this is a useful pattern when the creation of an object is costly. 
        - cache the object, returns its clone on next request, and then update
        a db as/when needed reducing the db calls. 
        
        
    PrototypePatternDemo
        +main():void
        
        (asks) 
        
    ShapeCache
        -shapeMap:HashMap
        +getShape():Shape
        +loadCache():void
        
        (clones)
        
    Shape
        -id:String
        +type:String
        +getType():void
        +getId():String
        +setId():void
        +clone():Object
        
        Circle (extends Shape)
            -type:String
            +getType():void
            +getId():String
            +setId():void
            +clone():Objecg
            
        Rectangle (extends Shape)
            -type:String
            +getType():void
            +getId():String
            +setId():void
            +clone():Objecg
            
        Square (extends Shape)
            -type:String
            +getType():void
            +getId():String
            +setId():void
            +clone():Objecg