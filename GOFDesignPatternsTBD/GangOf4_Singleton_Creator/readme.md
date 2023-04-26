# SINGLETON

    - CREATIONAL PATTERN
    
    - involves a single class which is responsible to create an object
    while making sure that only a SINGLE INSTANCE gets created. 
    
    - the purpose of this is to allowthe class to provide a way to access 
    a singleton'so only object (getInstance() in the case of this example) 
    which can be accessed directly w/o the need to instantiate the object of
    the class. 
    
    
    SingletonPatternDemo
        main():void
        
        asks
        
    SingleObject
        - instance: SingleObject
        SingleObject()
            getInstance():SingleObject
            showMessage():void
            
        returns SingleObject (itself)