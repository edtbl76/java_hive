# Abstract Factory

    CREATIONAL PATTERN
    
    - a super factory or a factory of factories.
    - this creates new factories. 
    
    - interface(s) are responsible for creating a factory of related objects
    w/o explicitly specifying their classes. 
    
    - each generated factory can give objects per the "psuedo nested" factory
    pattern. 
    
    
    
    
    AbstractFactoryPatternDemo
        main():void
        
        (uses)
        
    FactoryProducer
        getFactory():AbstractFactory
        
        (uses)
        
    AbstractFactory
        getShape():Shape
        getColor():Color
        
    
        ShapeFactory extends AbstractFactory
            getShape():Shape
            
            (creates)
            
             
            Shape(interface)
                draw():void 
                
                
                Circle implements Shape
                    draw():void 
                    
                Rectangle implements Shape
                    draw():void 
                    
                Square implements Shape
                    draw():void 
        
        ColorFactory extends AbstractFactory
            getColor():Color
            
            (creates) 
            
            Color(interface) 
                fill():void 
                
                Red implements Color
                    fill():void
                    
                Green implements Color
                    fill():void
                    
                Blue implements Color
                    fill():void
                    
                
                
                    
    