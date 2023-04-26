# STACK

    - implements a LIFO (Last In First Out) Stack of Elements
        - last thing you push on to the top is the first thing you pop back 
        off
        
    - subclass of Vector
    
# CONSTRUCTOR

    Stack()         - default constructor which created an empty stack
    
    
#   METHODS

    Since Stack() extends Vector, it includes ALL OF THE METHODS 
    DEFINED BY VECTOR (see vector.md) 
    
    1   boolean             empty()
                            - returns true if 'this' Stack is empty.
                            
    2   Object              peek()
                            - returns element on the top of the stack
                                (Does NOT remove it) 
                                
    3   Object              pop()
                            - return element on top of the stack
                                (REMOVES IT FROM STACK)
                                
    4   Object              push(Object element) 
                            - pushes 'element' onto the stack
                                (ELEMENT IS ALSO RETURNED) 
                                
    5   int                 search(Object element) 
                            - traverses stack to find 'element'
                                - if found, returns offset from top of
                                stack
                                - if not found, returns -1
                                
    
    
    