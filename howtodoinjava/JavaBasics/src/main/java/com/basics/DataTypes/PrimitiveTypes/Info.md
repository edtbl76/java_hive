# Primitive Data Types
![alt-text](/Users/emangini/IdeaProjects/edtbl76/HowToDoInJava/JavaBasics/src/com/basics/DataTypes/PrimitiveTypes/Primitive-data-types-in-java.jpg)

## INTEGRAL DATA TYPES

A numeric data type whose values are integers. 

    Five Integral Data Types
    - byte
    - short
    - int
    - long
    - char
    
### INT
- 32-bit signed primitive data type
    - a var of type 'int' takes up 32 bits (4 bytes) of memory
- valid range of values (-2^31) to ((2^31) - 1)
- all whole numbers in this range are known as INTEGER LITERALS/INTEGER CONSTANTS


    EX:
        int counter = 21;
        
### Integer Wrapper Class
- 2 important CONSTANTS
    - Integer.MAX_VALUE and Integer.MIN_VALUE (represents max/min values for int data type)
    
    
    EX:
        int max = Integer.MAX_VALUE; 
        int min = Integer.MIN_VALUE;
        
        Usually used for validation and bounds checking
  
        
### LONG
- 64-bit (8 byte) signed Java primitive data type
- used when the results of calculations on whole nums may exceed range of an int
- range is (-2^63) to ((2^63) - 1)
- all whole numbers in this range are called INTEGER LITERALS OF LONG TYPE
- all integer literal of type long ends w/ L or l


    EX:
        long num1 = 0L;
        long num2 = 401L;
        
#### Type Casting Note:
- Even if the value stored in a long variable is within the range of an int, the assignment of LONG narrowed
down to int isn't allowed w/o explicit type casting. 
        
### LONG Wrapper Class
- like Int, it defines two important constants for max and min
    - Long.MAX_VALUE, Long.MIN_VALUE
    
    
    Ex:
        long max = Long.MAX_VALUE;
        long min = Long.MIN_VALUE;
        
### Byte Primitive Data Type
- 8-bit (1 byte) signed Java primitive integer data type
- ranges from (-128 - 127)
    - smallest integer data type in Java
- NO BYTE LITERALS,but you CAN assign an int literal that falls in byte range to a byte variable

#### Type Casting Note:
- if you assign an int literal that falls out of tbe byte range, a compile error will be thrown. 
- for this reason, it is recommended to explicitly typecast narrowing conversions (even if you
"know" the value will never exceed the range)

### Byte Wrapper Class
- just like Int and Long, this provides 2 special constants for min and max values
    - Byte.MIN_VALUE, Byte.MAX_VALUE
    
    
    EX:
        byte max = Byte.MAX_VALUE;
        byte min = Byte.MIN_VALUE;
        
### Short Primitive Data Type
- 16- bit (2 byte) signed Java primitive data type
- has a range of (-2^15) to ((2^15) - 1)
- like a byte, there are no "short literals", however you can assign any int literal that
falls in a SHORT range to a SHORT variable.

### Short Wrapper Class
- just like all of the previous examples there are constants for max and min values
    - Short.MAX_VALUE, Short.MIN_VALUE;
    
    
    EX:
        short max = Short.MAX_VALUE;
        short min = Short.MIN_VALUE;
        
### Char Primitive Data Type
- 16 bit (2 byte) unsigned Java primitive data type.
    - doesn't support negative values
- represents a Unicode character
    - range is from 0 - 65_535 (the length of the Unicode Character Set)
    - uses a CHARACTER LITERAL
     
## Floating-Point Data Types
![alt-text](/Users/emangini/IdeaProjects/edtbl76/HowToDoInJava/JavaBasics/src/com/basics/DataTypes/PrimitiveTypes/IEEE-754-32-bit-Single-Precision-Floating-Point-Numbers.gif)
REAL NUMBERS are floating point numbers that contain a FRACTIONAL PART 

When real numbers are converted into binary, the computer must also store the position of
the decimal point within that number. 

    
    FIXED POINT FORMAT
    - only binary representation of the number is stored
    - assumes a fixed number of digits before and after the point
        - decimal point (decimal representation)
        - binary point (binary representation)
        
-

    FLOATING POINT FORMAT
    - stores binary representatino of real number
    - also stores position of the POINT in the real number
    - allows number of digits before/after the point to vary. 
    
    - slower and less accurate than fixed point
    - BUT, they can handle a MUCH larger range of numbers 
    

#### Type Casting Note:
- all integral types can be assigned to a variable of a float data type w/o explicit cast
- float types MUST be EXPLICITLY cast to integral types due to the possibility of data loss.

### Float Data Type
- 32 bit (4 byte) IEEE 754 standard format (single precision floating-point number)
- range of 1.4 x 10^(-45) to 3.4 x 10^38. 
    - positive or negative
- all real numbers that end w/ f or F are called FLOAT LITERALS
    
    
    EX:
        float f1 = 8F;
        float f2 = 8.F;
        float f3 = 8.0F;
        
### Float Wrapper Class
- defines several constants
    - Float.POSITIVE_INFINITY   (Positive infinity of type float)
    - Float.NEGATIVE_INFINITY   (Negative infinity of type float)
    - Float.Nan                 (undefined Not a Number of type float)
    - Float.MAX_VALUE (largest positive value that can be represented in a float)
    - Float.MIN_VALUE (smallest positive value > 0, that can be represented in a float)

### Double Data Type
- 64 bit (8 byte) IEEE 754 standard format (double precision floating-point number)
    - this is how it gets its name. 
- all REAL NUMBERS that end w/ d or D are called DOUBLE LITERALS
    - NOTE: the suffix is optional as double is the default type for floating-point
    numbers in Java

    
    EX:
        double d1 = 8D;
        double d2 = 8.;
        double d3 = 8.0;
        double d4 = 8.D;
        
### Double Wrapper Class
- defines several constants
    - Double.POSITIVE_INFINITY   (Positive infinity of type double)
    - Double.NEGATIVE_INFINITY   (Negative infinity of type double)
    - Double.Nan                 (undefined Not a Number of type double)
    - Double.MAX_VALUE (largest positive value that can be represented in a double)
    - Double.MIN_VALUE (smallest positive value > 0, that can be represented in a double)
    
### Boolean Data Type
- two valid values (true and false), known as BOOLEAN LITERALS
- booleans may not be cast to any other data type and vice versa
- NOTE: java does NOT specify the size of a boolean data type. The size is left up to the
JVM implementation, because it is often architecture specific.

    - in most cases, a boolean is stored in a byte
    (In C++ you can further compact this using bit compaction, but Java blows)