# Switch Statement

This is like an if-else statement on steroids. 
Unlike the if-else statement, which only executes sections of code based on the result of a condition, 


    the switch statement can have multiple execution paths.
    
Switch statements work with:
- primitive data types
- type wrappers
- enumerated types
- String class

## Syntax

switch (switch-expression) {

    case label-1:
        statements;
        break;
        
    case label-N:
        statements;
        break;
        
    default:
        statements:
        
 NOTE:
  - label-1 -> label-N are COMPILE-TIME constant expressions 
  - (i.e. they must be known at compile time)\
 
## Execution Flow

1. switch-expression is evaluated
2. if value of switch-expression matches a case label
    - the execution starts from the matched case label
    - ALL STATEMENTS ARE EXECUTED UNTIL the BREAK STATEMENT IS REACHED.
3. if the value of the switch-expression doesn't match a case label:
    - execution starts at the DEFAULT label and continues until the end of the 
    switch statement or a BREAK statement is reached. 
    - NOTE: the break statement isn't required after the default label, because this is the
    end of the switch statement anyway, however I prefer to include it for posterity and 
    cleanliness of code.
    
### EXPRESSIONS VALUES MUST BE VALUE

The case statements must represent possible cases. If the switch-expression is a
boolean, then the only two cases are true or false. If the switch-expression is a byte, then a
value of 890 isn't valid. 

This follows the general rule

    Don't be stupid.
    
### Labels Must Be Unique

Code will not compile all of the case statements aren't unique.
          