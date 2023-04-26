
NOTE
- precedence rules can be overidden by explicit parantheses. 

| Precedence      |  Operator     | Type  | Associativity |
| :-------------: |:-------------:| ----- | :-----------: |
| 15 | ()           | Parentheses                               | Left to Right |
|    | []           | Array Subscript                           |               |
|    | .            | Member Selection                          |               |
| 14 | ++           | Unary post-increment                      | Right to Left |
|    | --           | Unary post-decrement                      |               |
| 13 | ++           | Unary pre-increment                       | Right to Left |
|    | --           | Unary pre-decrement                       |               |
|    | +            | Unary plus                                |               |
|    | -            | Unary minus                               |               |
|    | !            | Unary logical negation                    |               |
|    | ~            | Unary bitwise complement                  |               |
|    | (type)       | Unary type cast                           |               |
| 12 | *            | Multiplication                            | Left to Right |
|    | /            | Division                                  |               |
|    | %            | Modulus                                   |               |
| 11 | +            | Addition                                  | Left to Right |
|    | -            | Subtraction                               |               |
| 10 | <<           | Bitwise Left shift                        | Left to Right |
|    | >>           | Bitwise Right Shift w/ sign extension     |               |
|    | >>>          | Bitwise Right Shift w/ zero extension     |               |
| 9  | <=           | Relational Less than or equal             | Left to Right |
|    | >            | Relational greater than                   |               |
|    | >=           | Relational greater than or equal          |               |
|    | instanceof   | Type comparison (objects only)            |               |
| 8  | ==           | Relational is equal to                    | Left to Right |
|    | !=           | Relational is not equal to                |               | 
| 7  | &            | Bitwise AND                               | Left to Right |
| 6  | ^            | Bitwise exclusive OR                      | Left to Right |
| 5  |  (pipe)      | Bitwise inclusive OR                      | Left to Right |
| 4  | &&           | Logical AND                               | Left to Right |
| 3  | (double pipe)| Logical OR                                | Left to Right |
| 2  | ?:           | Ternary Conditional                       | Right to Left |
| 1  | =            | Assignment                                | Right to Left |
|    | +=           | Addition Assignment                       |               |
|    | -=           | Subtraction Assignment                    |               |
|    | *=           | Multiplication Assignment                 |               |
|    | /=           | Division Assignment                       |               |
|    | %=           | Modulus Assignment                        |               |


