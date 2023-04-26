# ASSOCIATION IN JAVA
- those relationships whose objects have an INDEPENDENT LIFECYCLE
- there is NO OWNERSHIP BETWEEN THE OBJECTS

- EXAMPLE: Teacher and Student
    - multiple students may ASSOCIATE w/ a single teacher. 
    - a single student may ASSOCIATE w/ multiple teachers. 
    
    - they have independent lifecycles
        - create/delete independent of each other. 


# AGGREGATION IN JAVA
- those relationships whose OBJECTS have an INDEPENDENT LIFECYCLE
- there IS OWNERSHIP BETWEEN THE OBJECTS

- EXAMPLE: Cell Phone and Cell Phone Battery
    - single battery may belong to a phone
    - if phone dies, battery might still work, so it has its own lifecycle
        
       
# COMPOSITION IN JAVA
- those relationships where OBJECT do NOT have an INDEPENDENT LIFECYCLE
- if parent object is deleted, then the child objects go with it. 


- EXAMPLE: questions and answers
    - single questions can have multiple answers
    - answers can't belong to multiple questions (well.. not specifically)
    - if we delete questions, answers are automatically deleted. 
     