# IMMUTABLE CLASS
- This is a class whose state cannot be changed once it has been created

## RULES FOR CREATING IMMUTABLE CLASSES
1. Don't provide SETTERS/MUTATORS
    - don't provide ANY  methods that modify fields/or objects referred to by fields. 
2. All fields should include the modifiers FINAL and PRIVATE
3. Don't allow SUBCLASSES to OVERRIDE methods
    - (NOTE: this happens when you declare the CLASS as final.)
        - final classes in java can't be overridden
4. There must be SPECIAL ATTENTION when having MUTABLE INSTANCE VARIABLES
    - Instance Vars will be either MUTABLE or IMMUTABLE
        - ALL mutable objects should be IDENTIFIED
        - they should return NEW OBJECTS w/ copied content (so only the copies are changed)
        - IMMUTABLE VARS can be returned safely w/o extra effort
    - recommended (sophisticated) approach
        - make the CONSTRUCTOR private
        - forces instances to be constructed by FACTORY METHODS

## IMMUTABLE CLASSES IN THE JDK
- String
- TypeWrappers  (Long, Integer, Double, etc)
- Immutable collection classes such as Collections.singletonMap() etc.
- java.lang.StackTraceElement;
- java enums
- java.util.Locale
- java.util.UUID

## BENEFITS OF MAKING CLASSES IMMUTABLE

### ADVANTAGES
- simple to construct, test and use
- automatically thread-safe
    - have NO synchronization issues
- don't need a copy constructor
- don't need an implementation of "CLONE"
- allow hashCode() to use lazy initialization (and to cache the return value)
- don't need to be copied defensively when used as a field
- make good MAP KEYS or SET ELEMENTS
    - (note, these objects must not change state while in the collection)
- have their class INVARIANT established once upon construction
    - it never needs to be checked again. 
- always have FAILURE ATOMICITY
    - if an immutable object throws an exception, it is NEVER left in an undesirable or
    indeterminate state. 
    