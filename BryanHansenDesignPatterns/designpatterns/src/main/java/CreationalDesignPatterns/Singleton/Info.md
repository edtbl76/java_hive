# CreationalDesignPatterns.Singleton Pattern

## Concepts
- "Creational" Pattern
- guarantees that only one instance is created
- guarantees control of a resource
- "usually" lazily loaded

## Examples
- Runtime
- Logger (depends on library)
- Spring Beans
- Many Graphic Managers

## Design Considerations
![alt-text](/Users/emangini/IdeaProjects/pluralsight/designpatterns/src/main/java/Singleton/Screen Shot 2020-04-06 at 1.06.11 PM.png)
- responsible for instantiating itself and responsible for its lifecycle
- static by nature
- needs to be thread safe
- private instance and private constructor
    - only the singleton is allowed to call the constructor
- no parameters (which is typically used as part of the Factory pattern)

## Pitfalls
- often overused
    - unchecked proliferation
    - if everything is a CreationalDesignPatterns.Singleton 
        - application will slow down 
- Difficult to unit test
    - lack of public methods
- very often ends up non-thread-safe
- Sometimes confused for Factory
    - if arguments are in the method then it is a Factory

## Contrast to Other Patterns
| FACTORY | SINGLETON |
| --- | --- |
| Returns various instances <br> multiple constructors | Returns same instance <br> one constructor method - no args |
| Interface Driven | No interface |
| Adapts to environment more easily | |


## Summary
- guarantee ONE INSTANCE
- easy to implement
- easy to make thread safe
- solves a well defined problem 
    - where the app only needs one instance of an object
- don't abuse it