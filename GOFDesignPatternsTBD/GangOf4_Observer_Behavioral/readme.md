# OBSERVER PATTERN

    - BEHAVIORAL PATTERN
    
        - used when there is a one-to-many relationship between objects such that
        if one object is modified, its dependent objects are to be notified
        automatically. 
        
### IMPLEMENTATION

    3 ACTOR PATTERN (similar to Memento/Originator/CareTaker)
    
    - Subject is an object that has methods to attach/detach observers to a client
    - Observer is an abstract class that is extended by Subject
    - clients are the dependent objects that need to be detached/attached