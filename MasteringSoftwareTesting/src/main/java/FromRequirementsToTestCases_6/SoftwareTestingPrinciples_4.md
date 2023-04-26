# Software Testing Principles

### Exhaustive Testing
- all possible combinations of test inputs to verify a system
    - impractical for all but the smallest systems or components w/ a very small
    finite number of possible operations and allowed data. 
      

    "The absence of defects in a software system cannot be proved"

- testing is "sampling at best"
    - it isn't about perfection, it's about reducing the risk of system failures or
    increasing confidence in the quality of software
      


### Best Practices
- tests should be simple
- avoid boilerplate
- tests must be readable
- use meaningful names
  - Junit5 **@DisplayName**
- "Single Responsibility Principle"
  - every class should have responsibility of a single piece of functionality
  - "metric of cohesion"
  - a "single test" should only refer to a given system requirement
- use well defined,thoughtful test data
- Unit tests should execute very quickly
  - general rule of thumb is 1 unit test should last 1 second
- SUT should be properly isolated
- DOCs should use test doubles
- tests should be repeatable
- NO HEISENBUGS!!!!
  - non-deterministic defects (i.e. we can't root cause them)
- test positive nd negative scenarios
  - positives = testing that input conditions yield the expected outcome
  - negatives = verify what the program SHOULDNT do 
- don't test just to achieve coverage 


