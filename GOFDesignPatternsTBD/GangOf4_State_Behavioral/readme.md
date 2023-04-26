# STATE PATTERN

    BEHAVIORAL PATTERN
    
        - class behavior changes based on its state. 
        - objects are created that represent various states, w/ a context 
        object whose behavior varies as the state changes. 
        
### IMPLEMENTATION

    - State interface created that defines an action and concreate state classes
    that implement the interface. 
    - Context is a class that 'carries' a state.