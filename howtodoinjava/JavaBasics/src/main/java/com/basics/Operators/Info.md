# OPERATORS

A symbol that performs a special kind of operation on 1, 2 or 3 operands, and then produces a result. 

## CLASSIFICATION OF JAVA OPERATORS

1. Number of Operands
- unary operator (1)
- binary operator (2)
- ternary operator (3)

2. Type of Operation performed
- arithmetic oeprator (does math)
- relational operator (relates values)
- logical operator
- bitwise operator

### ASSIGNMENT OPERATOR
- "=", used to assign a value to a variable
- BINARY (requires 2 operands)
- RVALUE is assigned to LVALUE
- LVALUE MUST be a variable


    EX:
        int counter = 26
            
                RVALUE = 26  (int literal)
                LVALUE = counter, the name.
                

- COMPILE-TIME ERROR OCCURS when right-hand operand isn't compatible w/ left hand operand


## ARITHMETIC OPERATORS

- plus (+), minus (-), multiple (*), divide (/)
- only supports NUMERIC type operands
    - byte, short, char, int, long, float and double
    - doesn't support boolean PRIMITIVE or REFERENCE type
    
### UNARY ARITHMETIC OPERATORS

- UNARY PLUS  ('+'): indicates a positive value
- UNARY MINUS ('-'): negates an expression value
- INCREMENT ('++'): increments value by 1
- DECREMENT ('--'): decrements value by 1
- LOGICAL COMPLEMENT ('!'): inverts value of a boolean (i.e. true if false, false is true)


#### NOTE ON PRE and POST INCREMENT/DECREMENT

### BINARY ARITHMETIC OPERATORS

- ADDITION ('+'): adds values on either side of operator
- SUBTRACTION ('-'): subtracts RIGHT OPERAND from LEFT OPERAND
- MULTIPLICATION ('*'): multiplies values on either side of operator
- DIVISION ('/'): divides RIGHT operand FROM LEFT OPERAND
- MODULUS ('%'): divides RIGHT operand FROM LEFT OPERAND, returns REMAINDER

### CONCATENATION OPERATOR ('+')

'+' is an overloaded operator (remember this from C++???, yeah, Java kind of does most of that for you
so you don't have to know how it works.)

## RELATIONAL OPERATORS
- all relational operators are binary operators (so they require 2 OPERANDS)
- result produced by relational operators is ALWAYS a BOOLEAN (true/false)
- most commonly used in CONTROL statements (i.e. if, while, etc.)

Types
- EQUALS ('==')
    - returns true if the values of 2 operands are equal
- NOT EQUAL ('!=')
    - returns true if the values of 2 operands are not equal
- GREATER THAN ('>')
    - returns true if the value of the LEFT operand is GREATER THAN the value of the RIGHT operand
- LESS THAN ('<')
    - returns true if the value of the LEFT operand is LESS THAN the value of the RIGHT operand
- GREATER THAN OR EQUAL TO ('>=')
    - returns true if value of the LEFT operand is GREATER THAN OR EQUAL TO the value of the RIGHT
    operand
- LESS THAN OR EQUAL TO ('<=')
    - returns true if value of the LEFT operand is LESS THAN OR EQUAL TO the value of the RIGHT 
    operand
    
## BOOLEAN LOGICAL OPERATORS
- can only be used w/ boolean operands
- mostly used in CONTROL statements to compare 2+ conditions

TYPES:
- NEGATION ('!')
    - returns true if operand is false
    - returns false if operand is true
- AND ('&&'), ('&')
    - returns true only if both operands are true
    - NOTE: the only difference between the two is that '&' evaluates the RIGHT operand even if
    the left hand evaluates to false. (THis is why '&&' is referred to as a SHORT CIRCUIT operator)
- OR ('||'), ('|')
    - returns true if either operand is true
    - returns false only if BOTH operands are false
    - NOTE: the only difference between the two is that '|' evaluates the RIGHT operand even if
      the left hand evaluates to false. (THis is why '||' is referred to as a SHORT CIRCUIT operator)
- XOR ('^')
    - returns true only if ONE operand is true
    - returns false if BOTH are false or if BOTH are true
- '&='
    - if both operands are true, &= returns true
- '|='
    - if either operand is true, |= returns true
- '^='
    - returns true if 1 operand is true, and 1 operand is false
    
## BIWISE OPERATORS

- bitwise operators manipulates the INDIVIDUAL BITS of its operands. 
- supports INTEGER types (long, int, short, char and byte) 

TYPES
- BINARY AND ('&')
    - copies a bit to the result if it exists in BOTH operands
- BINARY OR ('|')
    - copies a bit to the result if it exists in EITHER operand
- BINARY XOR ('^')
    - copies a bit to the result if it exits in one operand but not other
- BINARY ONES COMPLEMENT ('~')
    - UNARY operator that has the effect of 'flipping' the bits
- BINARY LEFT SHIFT OPERATOR ('<<')
    - LEFT OPERAND value is moved left by the number of bits specified by the RIGHT OPERAND
- BINARY RIGHT SHIFT OPERATOR ('>>')
    - LEFT OPERAND value is moved right by the number of bits specified by the RIGHT OPERAND
- BINARY RIGHT SHIFT (ZERO FILL) OPERATOR ('>>>')
    - LEFT OPERAND value is moved right by the number of bits specified by the RIGHT OPERAND
    - shifted values are filled up with zeroes
    
## TERNARY OPERATOR (?:)
- takes THREE operands
- syntax
    - boolean-expression ? true-expression : false-expression
    - where:
        - boolean expression is an expression being evaluated
        - true-expression is what happens if the expression evaluates to true
        - false-expression is what happens if the expression evaluates to false
