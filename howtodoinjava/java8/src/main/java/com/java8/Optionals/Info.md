# OPTIONALS

## NULL TYPE
- null references are used to imply the absence of a value when setting up objects. 
    - this is often done to declare a variable so as to create its scope before a loop
    or other type of block will assign/reassign values to it.
    
## RETURNING NULL
- some APIs allow for you to return a null. However if it isn't handled.. boom NPE. app no workey

It is easy to say, "FIne, initialize your object references with some value!"
- Yes, smart ass... that would in fact prevent the nasty NPE, however it isn't feasible to
always have a value. 

# OPTIONALS! :)
- java.util.Optional is a feature from Java 8 that allows you to replace a "nullable T reference
" with a non-null value. 
    - it's kind of like a Maybe (it allows 0 or more items)
    
## INTERNALS

    
    /**
     * if non-null, the value; if null, indicates no value is present
     */
    private final T  value;
    

static keyword ensures that generally only ONE empty instance should  exist per VM

    /**
     * Common instance for {@code empty()}.
     */
    private static final Optional<?> EMPTY = new Optional<>();
    
    
## WHY OPTIONALS?
- trying to reduce the occurrence of NPE
    - by allowing more expressive APIIs considering the absence of return values. 
-  forces you to actively think about the absent case if you want the program to work.

- NOT a mechanism to avoid all types of NPEs.
    - doesn't help w/ conveying the MEANING of an absent value. 
    
## HOW TO USE
    - should be used almost all the time as THE RETURN TYPE OF FUNCTIONS THAT 
    MIGHT NOT RETURN A VALUE