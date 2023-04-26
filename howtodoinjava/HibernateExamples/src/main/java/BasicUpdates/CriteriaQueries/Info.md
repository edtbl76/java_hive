# Hibernate Criteria Queries.
Hibernate has three methods for retrieving data from a database
1. HQL (been there done that)
1. Native SQL (we did that when we covered HQL)
1. Hibernate Criteria Queries

The third option here is a way to build nested, structured query expressions in Java
- provides compile-time syntax checking that isn't possible in other SQL dialects

## Hibernate Criteria Example
uses org.hibernate.Criteria (interface from which methods are defined to
manage Criteria queries)

How To Use
- pass a persistent object's class/entity name to createCriteria() method
- hibernate creates a Criteria object that returns instances of the persistence object's
class when the app executes the criteria query.

    EX:
    
        Criteria criteria = session.createCriteria(Product.class);
        
        List<Product> list = crit.list();
        

- this simple example returns every object that corresponds to the class
    - there are no parameters/constraints applied to whittle down the result set. 
    
    
## Restrictions
Allows for selective retrieval of objects/properties. 
- uses add() method that takes org.hibernate.criterion.Criterion as a parameter
- multiple restrictions can be added to a query


### Restrictions.eq()
matches exactly
    
    EXAMPLE 1: "equals"
    
        Criteria crit = session.createCriteria(Product.class);
        crit.add(Restrictions.eq("description", "Painting");
        List<Product> results = crit.list();
        

### Restrictions.ne()
make sure it doesn't match (exactly)
        
    EXAMPLE 2: "not equals"
    
        Criteria crit = session.createCriteria(Product.class);
        crit.add(Restrictions.ne("description", "Pump");
        List<Product> results = crit.list();
        
        
NOTE: Restrictions.ne() can NOT be used to retrieve records w/ a NULL value in the DB
for the requested property. 
- NULL represents the ABSENCE of data, and therefore cannot be compared w/ data.
- if you want to retrieve objects w/ NULL properties use:
    - isNull()
    
    
        
### Restrictions.like() & Restrictions.ilike() 
partial match (case specific and insensitive respectfully)


        Example 3: "like"
        
        Criteria crit = session.createCriteria(Product.class);
        crit.add(Restrictions.like("name", "Pai%, MatchMode.ANYWHERE));
        List<Product> results = query.list();
        

MatchMode objects (Type-safe enumerations)
- ANYWHERE
    - matches any position in the string
- END
    - end of the string
- EXACT
    - exact match (almost the same as equals)
- START
    - beginning of the string
        

### Restrictions.isNull() and Restrictions.isNotNull()
If I have to explain this you need to consider a new direction in life

        EXAMPLE 4: nully wully
        
            Criteria crit = session.createCriteria(Product.class);
            crit.add(Restrictions.isNull("name"));
            List<Product> results = crit.list();
            
### Restrictions.gt(), Restrictions.ge(), Restrictions.lt(), Restrictions.le()
Again... these are comparison operators. You should know this by now :)

        EXAMPLE 5: biggah
        
            Criteria crit = session.createCritera(Product.class);
            crit.add(Restrictions.gt("price", 100.0));
            List<Product> results = crit.list();
            
            
### Combining multiple Criteria
When multiple Criteria are chained together, they are automatically treated like an AND

        EXAMPLE 1: AND
        
            Criteria crit = session.createCritera(Product.class);
            crit.add(Restrictions.lt("price", 50.0));
            crit.add(Restrictions.ilike("description", "whatever", MatchMode.ANYWHERE));
            List<Product> results = crit.list();
            
To use an OR, we have to instantiate multiple individual Criterion and compare them
using Restrictions.or(), then return that to the Criteria query as a logicalExpression
            
        EXAMPLE 2: OR
        
            Criteria crit = session.createCriteria(Product.class);
            
            Criterion priceLessThan = Restrictions.lt("price", 50.0);
            Criterion whatever = Restrictions.ilike("description, "whatever",
                MatchMode.ANYWHERE);
            
            LogicalExpression orExp = Restrictions.or(priceLessThan, whatever);
            crit.add(orExp);
            List<Product> results = crit.list();
          
            
            
ADD and OR

        EXAMPLE 3: OR + ADD
            
            Criteria crit = session.createCriteria(Product.class);
            
            Criterion price = Restrictions.gt("price", 100,0);
            Criterion name = Restrictions.like("name", "Pogo%");
            LogicalExpression orExp = Restrictions.or(price, name);
            
            // This is anding together the or and a new Criterion.
            crit.add(orExp);
            crit.add(Restrictions.ilike("description", "round%",
                MatchMode.ANYWHERE));
            List results = crit.list();
            
            
## Disjunction and Conjunction Objects
These are used to create an AND/OR expression w/ more than 2 different criteria. 
- Restrictions class provides disjunction() factory method (OR)
- Restrictions class provides conjunction() factory method (AND)

Since OR syntax is a little bit more verbose than AND syntax, it's more common to see
disjunctions(), but not necessarily conjunctions(). 
- I disagree with this. Despite the fact that there isn't a clear advantage over add() and
conjunction(), there is a transient benefit of consistency. 


        EXAMPLE 4 :  Disjunction
        
            Criteria crit = session.createCriteria(Product.class);
            
            Criterion price = Restrictions.gt("price", 100.0);
            Criterion name = Restrictions.like("name", "Pogo%");
            Criterion desc = Restrictions.ilike("description, "round",
                MatchMode.ANYWHERE);
                
            Disjunction disj = Restrictions.disjunction();
            disj.add(price);
            disj.add(name);
            disj.add(desc);
            
            crit.add(disj);
            List results = crit.list();
       
## Restrictions.sqlRestriction()
allows specification of SQL in the Criteria API
(useful when you needs something that Crit API can't do)


    EXAMPLE
    
        Criteria crit = session.createCriteria(Product.class);
        crit.add(Restrictions.sqlRestriction("{alias}.description like 'Pogo%'"));
        List<Product> = crit.list();
        
## Paging through Result Sets
This works the same way as query. There are two methods
1. setFirstResult()
    - takes an int that represents first row in result set (starts at 0)
1. setMaxResults()
    - fixed number of results.
    
    
        EXAMPLE for posterity
        
            Criteria crit = session.createCriteria(Product.class);
            crit.setFirstResult(1);
            crit.setMaxResults(20);
            List<Product> results = crit.list();
            
## Unique Results
- this is likewise the same as query. 
- if we know we are going to ONLY return 1 or none of a given query. 
    - calculating aggregate
    - restrictions/constraints guarantee this circumstance. 
    
NOTE: uniqueResult() throws a HibernateException if you get more than one. 
- specifically  a "NonUniqueResultException"


    EXAMPLE
    
        Criteria crit = session.createCriteria(Product.class);
        Criterion price = Restrictions.gt("price", new Double(25.0));
        crit.setMaxResults(1);
        Product product = (Product) crit.uniqueResult();
           
## Distinct Results
Hibernate provides a "result transformer" for distinct entities
- org.hibernate.transform.DistinctRootEntityResultTransformer
- This guarantees that no duplicates will be in the query's result set. 

NOTE: This does NOT use SELECT DISTINCT in ordinary SQL
- the distinct transformer compares each of the results in the results set
using their default hashCode() methods. 
- only those entities that have unique hashCodes() are added to the result 
set. 

1. this may NOT be the behavior you would expect (i.e. when using SELECT DISTINCT)
1. If you are building your own classes, MAKE SURE YOU ARE OVERRIDING HASHCODE()
and EQUALS()!!!!


    EXAMPLE
    
        Critiera crit = session.createCriteria(Product.class);
        Criterion price = Restrictions.gt("price", new Double(25.0));
        crit.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE);
        List<Product> results = crit.list();
        
PERFORMANCE NOTE: 
- comparisons are done in Hibernate's Java code (not in the DB), so 
non-unique results are still transported across the network. 

## Sort Query Results
This works the same way it would w/ HQL or SQL. 
- uses org.hibernate.criterion.Order class. 
- supports asc/desc 


    EXAMPLE
    
        Criteria crit = session.createCritera(Product.class);
        crit.add(Restrictions.gt("price, new Double(10.0));
        crit.addOrder(Order.desc("price"));
        List<Product> results = crit.list();
        
Hibernate supports adding more than one "Order" object to the Criteria obj.
- these are passed through to the underlying SQL query. 
- results are sorted by first order, then any identical matches within that 
sort are sorted by the second order, etc. 

NOTE: Hibernate passes this to "SQL ORDER BY" clause AFTER subbing proper
db col name for the property

## Assocations/Joins
ONE-TO-MANY or MANY-TO-ONE

    
    EX 1 (OneToMany)
    
        Criteria crit = session.createCriteria(Supplier.class);
        Criteria productCrit = crit.createCriteria("products");
        productCrit.add(Restrictions.gt("price", new Double(25.0));
        List results = crit.list();
        
    
    EX 2 (ManyToOne)
    
        Critiera crit = session.createCriteria(Product.class);
        Criteria supplierCrit = crit.createCriteria("supplier");
        supplierCrit.add(Restrictions.eq("name", "MyStore"));
        List results = crit.list();
        
## Projections and Aggregates
Instead of working w/ objects from result set, you can treat results from 
result set as:
- a Projection (a set of rows and columns)
    - (similar to how you use data from a JDBC based SELECT query)
    
- org.hibernate.criterion.Projection object
- org.hibernate.criterion.Projections factory class. 

(Projections are similar to Restrictions.)

### Single Aggregate (Row Count)

    EX:  
        Criteria crit = session.createCriteria(Product.class);
        crit.setProjectioni(Projections.rowCount());
        List<Long> results = crit.list();
        
Aggregates available from Projections Factory class

1. avg(String propertyName) 
    - average of a prop's value
1. count(String propertyName)
    - counts # of times a prop. occurs
1. countDistinct(String property Name)
    - no. of unique  values the property contains
1. max(String propertyName)
    - max value of prop. values
1. min(String propertyName)
    - min value of prop. values
1. sum(String propertyName)
    - sum total of prop. values
    
### Multiple Aggregates
multiple projects are added via
- Projections.projectionsList() method. 
- ProjectionList object has add() method that takes Projection object

Since ProjectionList implements Projection interface, we can pass
projectionsList to setProjection()

        EXAMPLE
        
            Criteria crit = session.createCriteria(Product.class);
            Projectionlist projList = Projections.projectionList();
            prodList.add(Projections.max("price"));
            prodList.add(Projections.min("price"));
            prodList.add(Projections.avg("price"));
            prodList.add(Projections.countDistinct("description"));
            crit.setProjection(projList);
            List<Object[]> results = crit.list();
            
### Getting Selected Columns
Another use of projections is to get individual properties rather than 
entire entities
- this has the same value as SELECT does over FROM. We only have to load
the desired attrs into mem as opposed to the entire obj. 


    EXAMPLE.
    
        Criteria crit = session.createCriteria(Product.class);
        ProjectionList pList = Projections.projectionList();
        pList.add(Projections.property("name"));
        pList.add(Projections.property("description"));
        crit.setProjection(pList);
        crit.addOrder(Order.asc("price"));
        List<Object[]> results = crit.list();
        
## QBE (Query By Example)
Instead of programmatically "building" Criteria obj w/ Criterion objects 
and logical expressions:
- partially populate an instance of the object. 
- the partially build instance becomes a Template for Hibernate to use 
to construct the criteria based on the object's fields. 


    EXAMPLE
    
        Criteria crit = session.createCriteria(Supplier.class);
        
        Supplier supplier = new Supplier();
        supplier.setName("Dude!");
        crit.add(Example.create(supplier));
        List results = crit.list();
        
        
   
    
        
            

 
    