# Hibernate Architecture
![alt-text](/Users/emangini/IdeaProjects/HowToDoInJava/HibernateExamples/src/main/resources/Hibernate-Architecture.png)

## Configuration 
- written in 
    - hibernate.properties
    - hibernate.cfg.xml
- annotation
    - @Configuration

Used by SessionFactory to work w/ Java Application and DB. 
- represents entire set of mappings of 
    - application Java Types -> SQL Database
    
## Session Factory
creates session objects per user requests
- uses configuration info (see Configuration) to instantiate session object

## Session
representation of interaction between App and DB at any point in time.
- org.hibernate.Sessions
- instances of a Session can be retrieved from SessionFactory bean

## Query
allows apps to query DB for 1+ stored objects
- different techniques
    - EX: named query, Criteria API
    
## First-Level Cache (aka Session Cache)
default cache for session object while interacting w/ DB. 
- caches objects within current session
- ALL requests from Session object to DB MUST pass through this cache. 
- this cache is available w/ session object until Session object is "live"

## Transaction
-- enables data consistency, manages rollback

## Persistent Objects
POJOS that are persisted as one of the rows in the related table of a DB by hibernate
- can be configured in configurations (see Configuration)
- may be annotated w/ @Entity

## Second Level Cache
Stores objects ACROSS sessions
- needs to be explicitly enabled.
- a cache provider (i.e. EhCache) must be specified if enabled.

# Salient Features

## ORM (Object/Relational Mapping)
ORM Frameworks 
- map Java Domain Object <--> DB table
- allows biz logic to access/manipulate DB entities via Java Objects. 
    - faster dev process by simplifying
        - transaction management
        - automatic primary key generation
        - managing DB connections
        
## JPA Provider
JPA = Java Persistence API
- set of specifications for accessing, persisting and managing data between POJOs and
relational DB entities.

## Idiomatic Persistence
- any class that follows the four horseman of OOP, can be used as a persistance class
    - encapsulation
    - abstraction
    - inheritance
    - polymorphism
    
## High Performance and Scalability
Hibernate supports following features to achieve these traits:
- different fetching strategies
- lazy loading
- optimistic locking

## Easy To Maintain
- no special DB tables/fields
- generates SQL at system init time. 
- much quicker/easier to maintain compared to JDBC

    
# How It Works
metadata driven 
- annotations tell hibernate how to use objects when mapping them w/ db.
- at runtime, annotations are used to build queries to send to a relational DB.

Hibernate is a JPA implementation
- JPA is the guidelines/spec that must be followed
- Hibernate JPA Impl code is the code that meets the API as defined by JPA
    - provides under the hood functionality
    
