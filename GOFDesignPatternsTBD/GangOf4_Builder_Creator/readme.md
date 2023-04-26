# Builder Pattern

    CREATIONAL PATTERN
    
    - builds a complex object using simple objects and a step x step apprach. 
    - builds a final complex object and is independent of other objects. 
    
    
    
    BuilderPatternDemo
        +main():void
        
        (asks)
        
    MealBuilder
        +prepareVegMeal():Meal
        +prepareNonVegMeal():Meal
        
        (builds)
        
    Meal
        -items:ArrayList<Item>
        +addItem(Item item):void
        +getCost():float
        +showItems():void
        
        (uses) 
        
    Item
        +name():String
        +packing():Packing
        +price():float
        
    
        Burger (Implement Item)
            
            (Uses) 
            
            Wrapper (implements Packing)

            VegBurger (extends Burger) 
            
            ChickenBurger (extends Burger)
            
            
        ColdDrink (Implement Item) 
        
            (Uses) 
            
            Bottle (implements Packing) 
            
            Coke (extends ColdDrink)
            
            Pepsi (extends ColdDrink)
            
        
        Packing            
    

            
        
        
    