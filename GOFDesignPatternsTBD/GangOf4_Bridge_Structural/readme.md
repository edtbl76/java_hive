#Bridge Pattern

    STRUCTURAL PATTERN
    
    - used when we need to decouple an abstraction from its implementation so 
    that the two can vary independently
        - bridge between implementation class and abstrac class. 
        
    - uses an interface which provides the "bridge", such that functionality of
    concreate classes are independent from interface implementer classes. 
        - both types of classes can be altered structurally w/o affectring each other.
        
        
### Implementation

    - DrawAPI interface acts as Bridge Implementer
    - concrete classes (RedCircle, GreenCircle) implement DrawAPI interface
    
    - Shape is abstract class that uses object of DrawAPI. 
    - BridgePatternDemo is client that will use Shape class to use the bridge
    implementer in order to access the concrete classes (independently) 