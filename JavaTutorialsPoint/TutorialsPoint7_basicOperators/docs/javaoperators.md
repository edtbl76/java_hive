# JavaOperators

### Arithmetic Operators


    If A = 10, B = 20:
    
    + (Addition)        Adds values on either side of operator
                        A + B = 30
                        
    - (Subtraction)     Subtracts right-hand operand from left-hand operand
                        B - A = 10
                        
    * (Multiplication)  Multiplies values on either side of operator
                        A * B = 200
                
    / (Division)        Divides left-hand operand by right-hand operand
                        (Divides right-hand operand INTO left-hand operand)
                        B / A = 2
                        
    % (Modulus)         (same as Division, but returns the remainder)
                        B % A = 0
                        
    ++ (Increment)      Increases value of operand by 1 
                        B++ = 21
                       
    "--" (Decrement)    Decreases value of operand by 1
                        B-- = 19
                        

### Relational Operators

    ==  (equal to)      True if both operands are equal
                        (A == B) is not true
                        
    !=  (not equal to)  True if both operands are not equal
                        (A != B) is true
                        
    >   (greater than)  True if left operand is greater than the right operand
                        (A > B) is not true
                        
    <   (less than)     True if left operand is less than the right operand
                        (A < B) is true
                        
    >=  (greater than or equal to)
                        True if left operand is greater than (or equal to) the
                        right operand
                        ( A >= B ) is not true
                        
    <= (less than or equal to) 
                        True if left operand is less than (or equal to) the 
                        right operand 
                        ( A <= B ) is true

### Bitwise Operators

    Bitwise operators support int, long, short, char and byte. 
    
    They work on bits and perform BIT-BY-BIT operation
    
    a = 60, b = 13
    
    a = 0011 1100
    b = 0000 1101
    
    &   (bitwise and)   Bit is "true" (on or 1) if it exists in 
                        both operand
                        
                            a & b = 0000 1100  (12) 
                        
    |   (bitwise or)    Bit is "true" (on or 1) if it exists in
                        either operand
                        
                            a | b = 0011 1101   (61) 
                            
    ^   (bitwise xor)   Exclusive or is "true" (on or 1) if it exists
                        in one operand or the other, but NOT both
                        
                            a ^ b = 0011 0001   (49)
                            
    ~   (bitwise complement) 
                        Binary "One's Complement" operator which
                        has the effect of "flipping the bits"
                        
                        NOTE: this is a "unary" operator, such that it
                        only operates on a single operand.
                        
                            ~a  = 1100 0011     ( -61 )
                            
                            (shown in 2's complement form due to 
                            signed binary number) 
                            
    <<  (shift left)    Left operands value is moved left by the # of
                        bits specified by right operand
                        
                            a << 2 = 1111 0000  (240)
            
    >>  (shift right)   Left operands value is moved right by the # of 
                        bits specified by the right operand
                        
                            a >> 2 = 1111 (15)
                            
    >>> (zero fill shift right) 
                        left operands value is moved right by the # of 
                        bits specified by the right operand, and shifted
                        values are filled up w/ 0s
                            
                            a >>> 2 = 0000 1111 (15) 

### Logical Operators

    (assume A is true and B is false) 

    &&  (Logical and)   True if both operands are non-zero/true
                        ( A && B ) is false
                        
    ||  (Logical or)    True if any of the operands are non-zero/true
                        ( A || B ) is true
                        
    !   (Logical Not)   Reverses logical state of operand
                        !( A && B) is true
    
                        

### Assignment Operators

    =   (Simple Assignment)     Assigns value from right side operands to left
                                side operand
                                A + B = C 
                                
    +=  (Add AND Assignment)    
                                Adds right operand to left operand and assigns
                                the result to the left operand
                                C += A is equivalent to C = C + A
                                
    -=  (Subtract AND Assignment)   
                                Subtracts right operand from left operand and
                                assigns the result to the left operand
                                C -= A is equivalent to C = C - A
                                
    
    *=  (Multiply AND Assignment) 
                                Multiplies right operand by left operand and
                                assigns the result to the left operand
                                C *= A is equivalent to C = C * A
                                
    /=  (Divide AND Assignment) 
                                Divides left operand w/ right operand and 
                                assigns the result to the left operand
                                C /= A is equivalent to C = C / A
                                
    %= (Modulus AND Assignment) 
                                Takes modulus using two operands and assings the
                                result to the left operand
                                C %= A is equivalent to C = C % A
                                
    <<= (Left Shift AND Assignment) 
                                C <<= 2 is equivalent to C = C << 2
                                
    >>= (Right Shift AND Assignment) 
                                C >>= 2 is equivalent to C = C >> 2
                                
    $=  (Bitwise AND Assignment) 
                                C &= 2 is equivlaent to C = C & 2
                                
    |=  (Bitwise OR Assignment) 
                                C |= 2 is equivlaent to C = C | 2
                                
    ^=  (Bitwise XOR Assignment) 
                                C ^= 2 is equivlaent to C = C ^ 2
                                

### Misc Operators

    Conditional Operator (TERNARY OPERATOR) 
    
        Consists of 3 operands for the purposes of evaluating Boolean expressions
    
    
        variable X = (expression) ? value if true : value if false
        
        
    instanceof Operator
    
        This is used only for object reference variables, where the operator
        checks whether or not a an object is of a particular type
        
        ( Object reference variable )  instanceof (class/interface type) 
        
        (This is also true if object being compared is the assignment 
        compatible w/ the type on the right) 
        
# Operator Precedence

    Postfix         expression++, expression--          Left to Right
    Unary           ++expression, -- expression,        Right to Left
                    +expression, -expression, ~ , !
    Multiplicative  *, /, %                             Left to Right
    Additive        +, -                                Left to Right
    Shift           <<, >>, >>>                         Left to Right
    Relational      <, >, <=, >=, instanceof            Left to Right
    Equality        ==, !=                              Left to Right
    Bitwise AND     &                                   Left to Right
    Bitwise XOR     ^                                   Left to Right
    Bitwise OR      |                                   Left to Right
    Logical AND     &&                                  Left to Right
    Logical OR      ||                                  Left to Right
    Conditional     ?:                                  Right to Left
    Assignment      =, +=, -=, *=, /=, %=, ^=, |=,      Right to Left
                    <<=, >>=, >>>=
