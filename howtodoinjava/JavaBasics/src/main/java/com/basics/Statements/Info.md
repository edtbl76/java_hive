# Statements

A statement specifies an action in a Java program. 

There are 3 types of statements:
1. Declaration Statements
2. Expression Statements
3. Control Flow Statements

## DECLARATION STATEMENT

These are used to DECLARE A VARIABLE]

    EX:
        int num;
        int num2 = 100;     // initialization
        String str;
        
## EXPRESSION STATEMENT

An EXPRESSION that has a SEMICOLON AT THE END is called an expression statement. 

    EX:
    
        // Increment/Decrement Expressions
        num++;
        ++num;
        num--;
        --num;
        
        // Assignment Expressions.  
        num = 100;  
        num *= 10;
        (NOTE: Assignment Expressions occur after declaration statements. Combining
        Assignment and Declaration at the same time is an INITIALIZATION statement) 
        
        // Method invocation expressions
        System.out.println("This is a statement");
        someMethod(param1, param2);
        
## CONTROL FLOW STATEMENT

Statements like if, while, for, etc. that allow repeated execution of other statements for a specific
number of attempts or as a particular condition remains true. 
 