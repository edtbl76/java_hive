# DATA TYPE
![alt-test](/Users/emangini/IdeaProjects/edtbl76/HowToDoInJava/JavaBasics/src/com/basics/DataTypes/Java-Data-Type.jpg)
Typically associated w/ variables, and they have three important properties

1. an IDENTIFIER (variable name), which refers to the memory location that stores the variable's
value
2. the TYPE OF DATA stored at the memory location
    - determines the RANGE OF VALUES that the memory holder can hold. 
    - the AMOUNT of allocated memory depends on the DATA TYPE
3. MEMORY LOCATION to hold the value

Java is a STATICALLY TYPED LANGUAGE. 
- all variables MUST be declared before they can be used.

## TYPES OF DATA

### Primitive Data Types
- DIRECTLY holds a value in memory. 
    - not objects or references to objects
- Values stored in PRIMITIVES are LITERALS
    - a literal is the source code representation of a fixed value.
    
    
Java has 8 PRIMITIVE DATA TYPES

| Type | Description | Default Value | Memory Size | 
| ---  | --- | --- | --- |
| boolean   | binary value of true or false     | false             | 1 bit                         |
| char      | any unicode character             | \u0000 (0)        | 16 bit unicode character      |
| byte      | values from -128 to 127           | 0                 | 8 bit signed value            |
| short     | values from -2^15 to (2^15) -1    | 0                 | 16 bit signed value           |
| int       | values from -2^31 to (2^31) -1    | 0                 | 32 bit signed value           |
| long      | values from -2^63 to (2^63) -1    | 0                 | 64 bit signed value           |
| float     | IEEE 754 floating point           | 0.0               | 32 bit floating point value   |
| double    | IEEE 754 floating point           | 0.0               | 64 bit floating point value   |

#### Type Conversion (Primitives)
- all primitives may be assigned to another primitive type (other than boolean)
- NARROWING may result in data loss.                                

### Reference Data Types (Non-Primitive)

Reference types hold the REFERENCE to an object in memory.
- Using that reference, you can access the fields/methods of the referenced object. 

Multiple references can refer to same object. 
- You can also assign the reference of an object stored in one ref variable to another reference variable, 
which allows both reference vars to point to the same object in memory.

#### NULL

NULL is a reference constant (a.k.a reference literal) that may be assigned to any reference varaible. 
- if it is assigned to a ref var, it means that the reference var is NOT referring to any object in memory.

#### WRAPPER CLASSES

Wrapper class is a class whose object WRAPS OR CONTAINS A PRIMITIVE data type. 

    - Java has 8 wrapper classes, 1 associated with each primitive data type
        - java.lang.Boolean
        - java.lang.Byte
        - java.lang.Character
        - java.lang.Double
        - java.lang.Float
        - java.lang.Integer
        - java.lang.Long
        - java.lang.Short
        
    - all wrapper class instances are immutable
    - they maintain an INTERNAL CACHE FOR PERFORMANCE REASONS
        
#### AUTO-BOXING

Java allows a primitive type value to be assigned directly to a Wrapper class


    EX:
        - Integer counter = 20;
        - static Float PI = 3.14f; 


