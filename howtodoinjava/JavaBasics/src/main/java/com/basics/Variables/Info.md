# VARIABLES
![alt-test](/Users/emangini/IdeaProjects/edtbl76/HowToDoInJava/JavaBasics/src/com/basics/Variables/variable-example.jpg)

    Fields: variables declared outside Methods
    
    Variables: variables declared inside methods, including method arguments.
    
A named reference to a memory area where the VALUE of the variable is stored.

    [data_type] [variable_name] = [variable_value];
    - data_type refers to the TYPE of information stored in memory area
    - variable_name refers to the NAME of the variable
    - variable_value refers to the actual VALUE to be stored in the memory area
    
    EX:
    
    // Int
    int i = 10;
    
    // String
    String str = "this string";
    
    // Object
    Object obj = new Object();
    
    // array of ints
    int[] nums = [1, 2, 3, 4, 5];
    
## WIDENING

When a small PRIMITIVE type value is automatically "accommodated" in a bigger/wider
PRIMITIVE type value. 

## NARROWING

When a larger PRIMITIVE type value is assigned in a a SMALLER size data type. 
- This can cause data loss/truncation due to fewer bits available to store the data
- EXPLICIT TYPE-CASTING REQUIRED

## VARIABLE TYPES

(based on scope)
1. INSTANCE VARIABLES
2. CLASS VARIABLES
3. LOCAL VARIABLES
4. METHOD ARGUMENTS

(based on category)
1. PRIMITIVE TYPES
2. CLASS TYPES
3. ARRAY TYPES

## SCOPE OF VARIABLES

INSTANCE VARIABLES (a.k.a. STATE VARIABLES)
- variables declared (in class) w/o the STATIC keyword
- non-static fields are also known as instance vars, because they are UNIQUE TO 
EACH INSTANCE OF THE CLASS. 


    EX:
    public class InstanceVariableExample {
        
        int counter = 20;
    }
    
STATIC VARIABLES (a.k.a CLASS VARIABLES)
- any field declared with the STATIC modifier. 
- there is exactly ONE COPY of this variable that exists
    - (regardless of how many times the class is instantiated)
- variables declared as "PUBLIC STATIC" are treated as GLOBAL VARIABLES in Java

    
    EX: 
        public class ClassVariableExample {
            
            static float PI = 3.14f;
        
        }
        
LOCAL VARIABLES
- used inside methods as TEMPORARY VARIABLES that exist only during method execution.
- ONLY VISIBLE TO THE METHODS in which they are declared. 
    - (provides isolation from the rest of the class, usually by design)
    
    
    EX:
        public class LocalVariableExample {
        
            public static void main(String[] args) {
            
                int age = 30;       // Var is local to the main() method.
            
            }   
        }
            
METHOD ARGUMENT
- arguments are variables that are passed to a method when the method is called. 
- ONLY ACCESSIBLE IN THE METHOD THAT DECLARES THEM


    EX:
        public class MethodArgumentExample {
        
            public static void main(String[] args) {
            
                print(40);
            }
            
            public static void print(int param) {       // Method Argument.
            
                System.out.println(param);
            }
        }
        
## INSTANCE vs. CLASS VARIABLES

- instance (non-static) are unique to each instance of the class
    - to access instance vars, you MUST instantiate the class
    - 
    
- class (static) must be declared w/ the STATIC modifier. 
    - there is only 1 copy of a class variable
    - can be accessed by REFERENCING the class
    - don't required the class to be instantiated. 
    

    EX:
        (Data.java)
        public class Data {
        
            // Instance Variable
            int counter = 20;               
            
            // Class Variable
            static float PI = 3.14f;
        }
        
        (Main.java)
        public class Main {
        
            public static void main(String[] args) {
            
                Data dataInstance = new Data();
                
                // Instance Var requires the instance
                System.out.println( dataInstance.counter );
                
                // Class Var can just reference the class
                System.out.println( Data.PI );
                
            }
        }
