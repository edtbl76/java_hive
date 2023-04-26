# POLYMORPHISM
- the ability by which
    - We can create functions or reference variables which behave differently in
    different programmatic context. 
    
## Java POLYMORPHISM

    ReferenceClass <variable> = new ActualClass();
    
    Object o = new Object();
    Object o = new String();
    Object o = new Integer();
    
## COMPILE TIME POLYMORPHISM (AKA Static Binding or Method Overloading)
- the flow of control is decided in compile time itself. 

- METHOD OVERLOADING
    - an object can have two or more methods w/ same name, but method parameters must be different
    
### PARAMETERS TYPE

    EX:
        public static double Math.max(double a, double b){...}
        public static float Math.max(float a, float b){...}
        public static int Math.max(int a, int b){...}
        public static long Math.max(long a, long b){...}
        
- the actual method to be called is decided at COMPILE TIME based on the parameters
that are passed to the function in the program. 

### PARAMETERS COUNT

    EX: 
    
        ObjectFactory.create(String parameter1, String parameter2){...}
        ObjectFactory.create(Integer id, String parameter1, String parameter2){...}
        
- Both methods have the same name 'create()', but the actual method invoked is 
based on the parameters that are passed to the program.

## RUNTIME POLYMORPHISM (a.k.a dynamic binding or Method Overriding)
- this is a feature that occurs when implementing inheritance. 

METHOD OVERRIDING
- subclasses/derived classes will override "default" behavior inherited by
the parent class. 


    EX:
        
        public class Animal {
            public void makeNoise() {
                System.out.println("Some sound");
            }
        }
        
        class Dog extends Animal {
            @Override
            public void makeNoise() {
                System.out.println("Bark!");
            }
        }
        
        class Cat extends Animal {
            @Override
            public void makeNoise() {
                System.out.println("Meow!");
            }
        }    
        
    