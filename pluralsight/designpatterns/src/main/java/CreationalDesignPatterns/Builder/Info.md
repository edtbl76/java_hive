# CreationalDesignPatterns.Builder Pattern

## Introduction
CreationalDesignPatterns.Builder is used to construct objects that have many steps associated with setup. 
We also tend to use CreationalDesignPatterns.Builder when we want to make the objects immutable after they
are constructed

## Concepts
- Creational Pattern
- Handles complex constructors
    - large number of paramters/lots of setters
- Immutability can be forced on an object

EXAMPLES<Br>
- StringBuilder
- DocumentBuilder
- Locale.CreationalDesignPatterns.Builder
 

## Design Considerations
![alt-text](/Users/emangini/IdeaProjects/pluralsight/designpatterns/src/main/java/Builder/Screen Shot 2020-04-06 at 1.04.07 PM.png)
- solves problem of "what constructor to use"
- solves "telescoping constructors" problem
    - telescoping constructors is when we have one constructor for every possible
    parameter permutation. 
- usually written w/ Static inner class
    - returns instance of object that it is building
- calls appropriate constructor
- negates need for exposed setters 
    - considered an anti-pattern to have a setter for every possible parameter. 
- can use Generics for greater flexibility

## Pitfalls
- typically used to generate immutable objects
- typically implemented w/ a static inner class.
- not refactored in after the fact (i.e. like Protoype)
- slightly more complex
    - without the features the want

## Contrast to Other Patterns
| BUILDER | PROTOTYPE |
| --- | --- |
| | implemented around a clone | 
| handles complex constructors | avoids calling complex constructors |
| no interface required |  | 
| can be implemented w/ separate class | | 
| easily works w/ legacy code | difficult to impl in legacy code |

BUILDER gravitates TOWARDS working w/ complex constructors
PROTOTYPE tries to AVOID them. 

## Summary
- creative way to deal w/ complexity 
    - specifically surrounding constructors and building objects
- easy to implement
- few drawbacks
- can refactor it in w/ a separate class
    - usually a static inner class, but it can be an external class. 

