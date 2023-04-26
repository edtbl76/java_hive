# Interpreter Pattern

    BEHAVIORAL PATTERN
    
        - provides a way to evaluate language grammar or epxression
        - impement expression interface which tells to interpret a 
        particular context. 
            - used in SQL processing, symbol processing engines, etc. 
            
### IMPLEMENTATION

    - Expression interface w/ concrete classes that implement it
    - TerminalExpression acts as main interpreter of context in question
    - other classes used to create combinatorial expressions
    
    