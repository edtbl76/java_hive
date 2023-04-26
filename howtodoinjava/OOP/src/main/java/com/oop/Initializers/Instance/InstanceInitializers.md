# Instance Initializers
- Code blocks that are executed BEFORE CONSTRUCTOR code. 
- They are initialized every time a new object is created. 
- created w/ curly braces '{}'


    EX: 
    
        public class DemoClass {
        
            // initializer block 
            {
                // initialization code here
            }
        
        }
        
        
## Instance  Initializer Features 
- multiple initializers can be defined per class
- initializers execute in the same sequence they appear in the class body
- initializers execute:
    - AFTER parent class constructor
        - if none is specified, Java automatically inserts the default
        constructor of parent class super()
    - BEFORE child class constructor
    - BEFORE "this" class constructor. 
        - "this" class constructor executes after ALL initializers are completed
- constructors of THIS class and parent class may be called inside initializers

## INSTANCE INITIALIZATION SEQUENCE FLOW
- CHILD CLASS constructor is invoked
- CHILD CLASS CONSTRUCTOR's first statement is either super() or an explicitly provided constructor
    - parent class constructor is invoked as a result
- PARENT CLASS initializers are executed in sequence of their appearance
- PARENT CLASS constructor statements are executed
- CHILD CLASS' INITIALIZERs executed in sequence of their apperance
- CHILD CLASS constructor statements are executed
