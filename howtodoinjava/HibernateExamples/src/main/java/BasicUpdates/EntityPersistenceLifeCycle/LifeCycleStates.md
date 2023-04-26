# Transients
![alt-text](/Users/emangini/IdeaProjects/HowToDoInJava/HibernateExamples/src/main/resources/Transient-objects-are-independent-of-Hibernate.jpg)

Objects that exist in heap memory. 
- Hibernate doesn't manage these or persist changes to them. 

Persisting Transient Objects
- save transient obj to database
    - results in obj being assigned an identifier and the object becoming a Persistent Object. 

# Persistent Object
![alt-text](/Users/emangini/IdeaProjects/HowToDoInJava/HibernateExamples/src/main/resources/Persistent-objects-are-maintained-by-Hibernate.jpg)

Objects that exist in the database. 
- Hibernate manages persistence for persistent objects. 

If fields/properties of a persistent object change, Hibernate will keep the DB representation up
to date when the app marks the changes to be committed. 

# Detached Object
![alt-text](/Users/emangini/IdeaProjects/HowToDoInJava/HibernateExamples/src/main/resources/Detached-objects-exist-in-the-database-but-are-not-maintained-by-Hibernate.jpg)

These objects have a representation in the DB, but changes to the object will NOT be 
reflected in the database (and vice versa)
- this is a TEMPORARY separation of Object and Database.

Detached Objects are created by;
- closing the session it was associated with
- evicting it from the session w/ the session's evict() method.

USE CASE:
- read object out oof the db
- then modify properties of the object in memory
- store the results some OTHER place than the DB. 
- (this is an alternative to doing a deep copy).

Persisting Changes to Detached Objects
- app must "reattach" the object to a valid Hibernate session. 
- detached instances can be associated w/ a new Hibernate session when app calls
load(), refresh(), merge() , update() or save() on the new session w/ a reference to
the detached object. 

- after those calls, the object becomes a persistent object managed by the new Hibernate
session.

# Removed Object

Objects being managed by Hibernate that have been passed to the session's remove() method. 
- when app marks changes held in session to be committed, any DB entries corresponding to 
removed objects are DELETED. 


# IMPORTANT NOTES
1. Newly created POJO is in a transient state. 
    - not persisted in the database. 
    - not associated w/ session object. 
1. persistent object represents ONE ROW of the DB
    - always associated w/ a unique Hibernate Session. 
    - changes to persistent objects are tracked by Hibernate and saved into the database when
    commit() occurs
1. Detached objects are those who were previously persistent.
    - to persist these objects, they must be reattached to a Hibernate session
1. Removed objects are persistent objects that have been passed to the session's remove() 
method that will be deleted as soon as the session is committed. 