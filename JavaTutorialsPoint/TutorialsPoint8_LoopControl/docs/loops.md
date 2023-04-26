# Loop Statement

    allows execution of a statement or group of statements multiple times
    
    
### While Loop

    - repeats statement(s) while a given condition is true. 
    - condition is tested BEFORE the loop body is executed. 
    (If condition is false, loop body is never executed) 
    
### For Loop

    - executes statements(s) multiple times, 
    - abbreviates code that manages loop variable
    
### Enhanced For Loop 
    
    for(declaration : expression) {
      // loop body
    }
    
        DECLARATION: 
            - newly declared block var is of type compatible w/ the elements
            of the array being accessed. 
            - var is available in the for block and the value would be the same
            as the current array element
            
        EXPRESSION
            - evaluates to the array that needs to be looped through
            - can be an array variable OR a method call that returns an array
    
### Do...While Loop

    - same as While loop, but the condition is tested AFTER the loop
    body is executed. (This has the effect of running the loop at 
    least once) 
    
# Loop Control Statements

    BREAK
        - terminates loop/switch statement and transfers execution to the 
        statement immediately following the loop/switch
        
    CONTINUE
        - causes the loop to skip the remainder of its body and immediately
        retests its condition prior to reiterating. 