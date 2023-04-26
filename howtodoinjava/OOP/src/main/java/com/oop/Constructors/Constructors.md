# CONSTRUCTORS
- special methods that allow you to fully initialize object state before it can be used
by other classes inside the application

- no return type

- invoked using the new <constructor_name> keyword
    - this allocates a memory area inside the heap. 
    - then JVM invokes the called constructor
    - then statements in constructor are executed to initialize object state. 
    
## THE RULES
- Constructors must have the same name as the class
- there can't be a return type  (this would make it a method)
- there can't be a return statement inside
    - this makes sense.. the constructor is for initialization
- Constructors can be overloaded by different signatures/parameter lists
- if you want to use super() (i.e. super class constructor)
    - it must be the FIRST STATEMENT in the constructor
    
## THIS and SUPER
- this is a keyword that refers to the CURRENT instance
- super is a keyword that refers to the BASE instance.