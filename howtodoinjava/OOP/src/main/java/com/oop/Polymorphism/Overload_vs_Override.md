# METHOD OVERLOADING vs. METHOD OVERRDING

## RULES FOR METHOD OVERLOADING
- this changes the METHOD SIGNATURE
    - which is determined by
        - the NUMBER of argument
        - the TYPE of arguments
        - the ORDER of arguments (if they are of different types)
- return type of method is NOT A PART OF METHOD SIGNATURE
    - changing return type doesn't equal method overloading.
- thrown exceptions aren't considered when overloading a method.

## RULES FOR METHOD OVERRIDING
- this PRESERVES the Method Signature
    - argument list must be identical
        - same NUMBER Of arguments
        - same TYPE of arguments
        - same ORDER of arguments (if they are of different types)
- if method signature isn't preserved then method is OVERLOADED
- the RETURN TYPE of an OVERRIDING METHOD can be the CHILD CLASS of the RETURN TYPE
declared in the OVERRIDEN METHOD

- PRIVATE, STATIC and FINAL METHODS
    - CAN NEVER BE OVERRIDEN IN ANY WAY IN JAVA. 
    - EVER
    - I"M NOT KIDDING. 
    - REALLY.
- OVERRIDING METHODS CAN'T THROW CHECKED EXCEPTION HIGHER IN HIERARCHY than THROWN
by overriden method. 

    
    EX:
        if a Base Class throws FileNotFoundException, 
        then Derived Class can't throw IOException or Exception, because those are
            higher in the exception hierarchy than FileNotFoundException.
- If the overridden method throws an exception, the overRIDING method doesn't necessaryily
have to.

- OVERRIDING METHOD cannot REDUCE access scope of overridden method. 
    - same access, or WIDER only.