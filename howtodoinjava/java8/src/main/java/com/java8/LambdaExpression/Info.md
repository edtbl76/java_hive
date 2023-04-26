# Lambda Expressions
- an anonymous function
    - it has no name
    - not bound to an identifier. 
    
- "nameless functions given as constant values and written exactly in the place
where it is needed, typically as a parameter to some other function"

- CRITICAL: they execute in CONTEXT of their appearan ce. 

## OOP vs FUNCTIONAL
- Lambdas provide a "functional" structure within in OOP. 
- Java revolves around objects + instances. 

- Lambdas provide java w/ the ability to define a function, give it a ref variable and 
pass them back as method arguments etc. 

## SYNTAX

1

    (x, y) -> x + y
    
    This takes 2 params and returns their sum.
    

2
    
    (parameters) -> expression
    
3

    (parameters) -> { statements; }
    
4

    () -> expression
    

## LAMBDA EXAMPLES
Take 2 integers and return their product

    (int a, int b) -> a * b
    
Take 2 ints and return their difference

    (a, b) -> a - b
    
Takes no values and returns 99

    () -> 99
    
takes a string, prints its value and returns nothing

    (String a) -> System.out.println(a)
    
takes a number and returns the result of doubling it
    
    a -> 2 * a
    
takes a collection and does some processing

    c -> { // lots of complex shit )
    
## Features of Lambda Expression

- it may have 0 or more parameters
- the type of the parameters can be explicitly declared or INFERRED FROM CONTEXT
- multiple parameters are enclosed in MANDATORY parentheses and separated by commas
- empty parentheses are used to represent an empty set of parameters
- if there is only 1 param, if its type is inferred, you don't have to use parentheses
    - EX: a -> return a*a
- the BODY of Lambda Expressions can contain 0, 1 or more statements
- if the body of a Lambda has a single statement
    - curly brackets aren't mandatory 
    - return type of the anonymous function is the same as that of the body expression
- if the body of a Lambda has multiple statements
    - curly brackets are required.
    
# LAMBDAS and Java 8 Functional Interface
- SAM Interfaces (Single Abstract Method interfaces)
    - This means "interfaces w/ only one single method"
    - Java8 enforces the "rule of single responsibility" by marking these
    interfaces with a new annotation @FunctionalInerface
    
    
    EX:
    
        @FunctionalInterface
        public interface Runnable {
            public abstract void run();
        }
        
- This relates to Lambdas, because LambdaExpressions are converted as a FunctionalInterface type.


    EX:
        new Thread(new Runnable() {
            @Override
            public void run() { 
                System.out.println("Lambdas");
            }
        }).start();
        
        
    Lambda Version:
    
        new Thread(
            () -> {
                System.out.println("My Runnable");
            }
        ).start();