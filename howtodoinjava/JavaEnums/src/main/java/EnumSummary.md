# ENUMS
- implicitly final subclasses of java.lang.Enum
- implicitly STATIC when a member of a class. 
- NEW keyword CAN NOT be used to initialize an enum 
    - even within the enum type itself
- name() and valueOf() methods
    - use the TEXT of the enum CONSTANTS
        - EX: Direction.EAST, name("EAST"), valueOf("EAST")
- toString() can be overridden
- ENUM CONSTANTS
    - equals() and == evaluate to the same result
    - may be used interchangeable, but for readability and consistency, pick one already
    - implicitly PUBLIC STATIC FINAL
- constructors should be declared PRIVATE
    - this makes sense, because we can't ever use NEW with an ENUM
- enum instances are all effectively SINGLETONS
    - can be compared for equality using ==

NATURAL ORDER
    - order of appearance in a list of enum constants
    - this is the order used by
        - compareTo()
        - EnumSet
        - EnumSet.range(0
        )