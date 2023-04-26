# FOUR JAVA ACCESS MODIFIERS
The four access modifiers in order of least -> most restrictive.

- PUBLIC
    - accessible everywhere
- PROTECTED
    - accessible in the same package and in derived classes
- DEFAULT (AKA PACKAGE-PRIVATE)
    - accessible in the same package. 
- PRIVATE
    - accessible only in the same class.
    
    
    public -> protected -> package-private/default -> private
    
## EFFECTS OF ACCESS LEVELS
- when using classes from another source, access levels determine which
members of the classes that can be used. 
- when writing our own classes, we can control the level of access external
consumers will have w/ respect to  the members  of our class(es)

NOTE: LOCAL VARIABLES  and FORMAL PARAMETERS cannot take access specifiers. 
- since they are limited inherently by their closed scope, they are "effectively"
private. 

## LEVELS of ACCESS CONTROL
CLASS LEVEL
- public
    - accessible by all
- default(package-private)
    - accessible only to classes within the same package.

METHOD LEVEL
- public
    - accessible by all
- protected
    - accessible within its own package
    - accessible by a subclass of its class in ANOTHER PACKAGE
- package-private (default)
    - accessible only within the same package
- private
    -  accessible only within the same class.
