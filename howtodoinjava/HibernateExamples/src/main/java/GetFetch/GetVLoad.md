# Get V Load Messages

# Hibernate load entity - session.load()
- several overloaded load() methods for loading entities from DB
    - each load() method REQUIRES object's PK as identifier
    - needs to know which class/entity_name to use to find object w/ PK as id.
    - after load() returns, we MUST cast returned object to proper class type in 
    order to use it.
    
## EXAMPLES

public Object load(Class theClass, Serializable id) throws HibernateException
- uses class type + PK

public Object load(String entityName, Serializable id) throws HibernateException
- uses entityName _ PK

public void load(Object object, Serializable id) throws HibernateException
- uses an empty object (of correct type)
- Hibernate populates object w/ what has been requested to be loaded.

# Hibernate Get entity by id - session.get();
- similar to load, but get()  takes an id and either entity name or class

## EXAMPLES
public Object get(Class clazz, Serializable id) throws HibernateException
public Object get(String entityName, Serializable id) throws HibernateException


# load() vs. get()
- get() returns values as NULL if identifier is absent
- load() throws RuntimeException if identifier is absent