# Data Types Best Practices

1.  use java variable naming conventions
2.  use primitives for variables which are local in scope
    - EX: 
        - inside methods
        - loop counters
        - intermediate results
3.  When data is transferred among method/class, it is recommended to use Object, because only their
references will be copied
    - no memory overhead is added. 
4.  when dealing w/ collections, you have to use OBjects
5.  when sending data over network, use objects and make them SERIALIZABLE
    - (Wrapper Classes are automatically Serializable)
6.  ALWAYS know the size of data type you will need. 
    - use appropriate data sizes
    - using int to store single bit boolean values is a waste (just one example)
7.  USE underscores in numbers
    - it makes them more readable. 