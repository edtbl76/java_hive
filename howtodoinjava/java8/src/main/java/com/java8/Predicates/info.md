# PREDICATES
- Java 8 predicate is a functional interface
    - can be used as assignment target for Lambda Expression or Method Reference.
- can be used in REALTIME use cases
    - find all events occurring after a certain date
    - find events occurring at a specific time
    - find all values less/greater/equal to certain value
    
## BENEFITS
- Predicates help centralize conditions (and maybe business logic)
    - makes unit-testing easier

- improves code maintenance by preventing the need to duplicate (DRY) code/changes in 
multiple places

- the first-class function, dependency injection style is more readable than if-else blocks