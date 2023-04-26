# PROJECT JIGSAW - AKA JPMS (Java Platform Module System)

## MODULES
- package-like artifacts containing code
    - usually includes metadata describing the module and its relationship w/ other moduules
    - IDEALLY, artifacts are recognizable from compile time through runtime

- a single module should represent a SPECIFIC business capability
    - module should be SELF-SUFFICIENT for that capability
    - should only expose interface to use the module functionalituy. 
    - if it depends on other  modules to complete tasks
        - EXPLICITLY DECLARE DEPENDENCY

## APPLICATION (in terms of modules)
- a combination or collection of modules which collaborate to perform business objectives

## THREE CORE PRINCIPLES OF JAVA MODULES

Strong Encapsulation
- hide implementation details, which shouldn't be required to know how to use the module
properly
    - encapsulated code should be able to change freely w/o impacting users of module
    
Stable Abstraction
- helps expose module functionality using INTERFACES (public APIs) 
    - changes to business logic or underlying implementation should be transparent to 
    users
    
Explicit Dependencies
- external dependencies must be part of the module definition
- dependencies between modules are often represented as graphs. 

# EXAMPLES
 see modules
 - com.greetings
 - org.astro
