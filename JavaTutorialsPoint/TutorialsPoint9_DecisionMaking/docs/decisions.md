# Decision Making Stuctures

    - decision making structures have one or more conditions to be evaluated or 
    tested by a program. 
    
    - usually this decision results in different instructions/statements
    to be executed pending the result of the decision. 
    
    FILTERING:
        - (This is really my own terminology for using decision making to 
        cut inputs down for easier processing, validation etc.)
        - By determining if a condition is true or false, in most cases we 
        cut our options in half. Successive "logic gates" of this nature 
        allow for a logarithmic reduction in possibilities, thus reducing actual
        "compute" code. 
        
### If Statement

    - boolean expression followed by one or more statements
    
### If... else 

    - same as an if statement, w/ a following optional 'else' statement which
    executes a separate branch of code if the if statement evaluates to false. 
    
### Nesting Ifs

    - this creates a decision tree. (look at my filtering explanation above) 
    - if, if/else and if/else if statements can be nested in other if type blocks, 
    allowing complex conditions to be whittled down into inputs that support 
    simpler computational code.
    
### Switch Statement

    - Where where you in Python??@!?!?!?!?
    - allows a variable to be tested for equality against a list of values. 
    - this has the advantage of simplifying a large series of if statements for 
    less verbose code. 
    
### Revisiting the Conditional Operator

    Exp1 ? Exp2 : Exp 3
    
    If exp1 is true, then value of expression is exp2
    If exp1 is NOT true, then value of expression is exp3
    