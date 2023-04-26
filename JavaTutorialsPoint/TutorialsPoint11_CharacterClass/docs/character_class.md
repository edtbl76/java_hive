# Character Class

    Normally we use the 'char' primitive to deal w/ specific characters, but, like you do,
    there are times in development that you have needs that aren't fulfilled by primitives.
    
    Java gives us a wrapper class Character for the primitive data type 'char'
       
        (you create an object by invoking its constructor:
        
            EX: Character ch = new Character('a');
           
           
### AUTOBOXING
    
    - Conveniently, if you pass a primitive character into a method that expects an
    object, the compiler will automatically converts (boxes) the char to a Character
    for you. 
    
    - It automatically unboxes the data when delivering it as a char. 
    
### Escape Sequences


    \t      tab
    \b      backspace
    \n      newline
    \r      carriage return (CR)
    \f      form feed (FF) 
    \'      single quotes
    \"      double quotes
    \\      backslash
    

### Methods of the java.lang.Character class


    1   isLetter()          returns true if char value arg is a letter
    2   isDigit()           returns true if char value arg is a digit
    3   isWhitespace()      returns true if char value arg is white space
    4   isUpperCase()       returns true if char value arg is upper case
    5   isLowerCase()       returns true if char value arg is lower case
    6   toUpperCase()       returns uppercase form of specified char value
    7   toLowerCase()       returns lowercase form of specified char value
    8   toString()          returns a 'String' object representing specified
                            char value (i.e.a one-character 'String') 