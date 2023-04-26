# HQL Syntax
HQL is an ANTLR style grammer
- ANTLR = tool for building language parsers

## HQL CRUD

### HQL Update Statement
Alters details of existing objects in DB
- in mem entities (managed or not) are NOT updated to reflect changes resulting 
from issuing UPDATE statements


    UPDATE [VERSIONED]
        [FROM] path [[AS] ALIAS] [, ...]
        SET property = value [, ...]
        [WHERE logicalExpression]
        
- path
    - fully qualified name of entity/entities
- alias
    - used to abbrev. refs to specific entities (or their properties)
    - must be used when property names in the query would otherwise be ambiguous
- VERSIONED
    - update will update time stamps (if any) that are part of entity being updated
- property
    - names of properties of entities listed in the FROM path.
- logicalExpression
    - a "where" clause
    
    
    EXAMPLE
    
    Query query = session.createQuery("update Employee set age=:age where name=:name");
    query.setInteger("age", 43);
    query.setString("name", "Ed");
    int modififications = query.executeUpdate();
    
### HQL Delete Statement
Removes the details of existing objects from the DB
- in mem entities are NOT updated to reflect changes resulting from DELETE statements
- NOTE: JPA Cascade rules are ALSO not followed for deletions carried out using HQL
    - if cascading deletes have been configured in the DB (directly or via Hibernate)
    then using @OnDelete annotation will result in the DB removing the child rows. 
    
    
    DELETE
        [FROM] path
        [WHERE logicalExpression]
        

- path
    - fully qualified name of entity/entities
- logicalExpression
    - a "where" clause
    
    EXAMPLE
    
    Query query = session.createQuery("delete from Account where accountstatus=:status");
    query.setString("status", "markedForDelete");
    int rowsDeleted = query.executeUpdate();
    

### HQL Insert Statement
NOTE: Unlike Ordinary SQL, Inserts can't be used to directly insert arbitrary entities. 
- it can ONLY  be used to insert entities constructed from info obtained from SELECT statements

    
    INSERT
        INTO path (property [, ...])
        select
        
- path
    - fully qualified name of entity/entities
- property
    - names of properties of entities listed in the FROM path of the
    incorporated SELECT query (This is an HQL select query)
    
    
    EXAMPLE
    
    Query query = session.Query("insert into purged_accounts(id, code, status) " +
        "select id, code, status from Account where accountstatus=:status");
    query.setString("status", "markedForDelete");
    int rowsCopied = query.executeUpdate();
    
### HQL Select Statement
Read operation. Used to query DB for classes and their properties. 

    [SELECT [DISTINCT] property [, ...]]
        FROM Path [[AS] alias] [, ...] [FETCH ALL PROPERTIES]
        WHERE logicalExpression
        GROUP BY property [, ...]
        HAVING logicalExpression
        ORDER BY property [ ASC | DESC ] [, ...]

- path
    - fully qualified name of the entity/entities
- property
    - names of properties of entities listed in the FROM path 
- FETCH ALL PROPERTIES
    - ignores lazy loading semantics
    - all of the immediate properties of retrieved object(s) will be eager
    loaded (non-recursive) 
- SELECT
    - note, when the properties listed consists only of the names of aliases
    in the FROM clause, the SELECT clause can be omitted in HQL. 
    
## HQL - FROM Clause & Aliases

From Clause
- basic
- useful for working directly w/ objects. 


    EXAMPLE 1: w/ AS 
    
        from Product as p
        
        from Product as product
        
    EXAMPLE 2: w/o AS
    
        from Product product
        
    EXAMPLE 3: Fully Qualified
    
        from com.project.package.subpackage.Product product
        

## HQL - Select clause and PROJECTION

Select Clause
- provides specialized control over result set
- allows access to properties of objects in result set w/o loading the
entire object into memory.


    EX 1:
        
        Select product.name from Product product;
        
    
    EX 2: (Multi-Select) 
    
        Select product.name, product.price from Product product;
        
## HQL - Named Parameters

Simplifies writing queries that require user input
    - removes the requirement to defend against SQL Injection Attacks.

NOTE: if using JDBC query params
   - any time you add/change/delete parts of statement, the java code that
    sets params must be updated. 
   - this is because params are indexed based on the order in which they
   appear in the statements
   
   
    EXAMPLE:
    
        String hql = "from Product where prive > : price";
        Query query = session.createQuery(hql);
        query.setDouble("price", 25.0);
        List results = query.list();
        
## HQL - Paging through Result Sets. 

Pagination through result set of a db query is very common
- Typical use cases
    - in web apps for breaking up a query that returns a very large data set. 
    - web app "pages" through db query result, building the appropriate      
    page for the user. 
    - (This increases the perf. of the web app so it doesn't have to 
    load the entire result set all at once)
    
TWO METHODS

1. setFirstResult()
    - takes an int that represents the first row in the result set (starting at 0)
1. setMaxResults()
    - tells Hibernate to retrieve only a fixed number of objects
    
    
        EXAMPLE:
        
            Query query = session.createQuery("from Product");
            query.setFirstResult(1);
            query.setMaxResults(2);
            List results = query.list();
            displayProductsList(results);
            
## HQL Get Unique Result
uniqueResult() method is used for obtaining a single object from an HQL query. 
- NOTE: you can use setMaxResults(1) to force it to only return a single.

The method works like a Maybe/Optional. (It returns 1 or 0)

If there is MORE THAN ONE RESULT:
- it throws NonUniqueResultException


    EXAMPLE
    
        String hql = "from Product where price > 25.0";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        Product product = (Product) query.uniqueResult();
       

## HQL Sorting Results w/ 'ORDER BY' clause

Allows sorting on any property within the result set. 
- asc/desc ordering.
- can sort on multiple properties. 


    EX 1:
        from Product p where p.price > 25.0 order by p.price DESC;
        
    EX 2: (multi sort)
    
        from Product p order by p.supplier asc, p.price asc;
        
## HQL - 'JOIN Clause' - Associations
Allows you to use more than one class in an HQL query. 
- similar to using cross joins between tables/relations in a relational DB
- add association to HQL query w/ a JOIN clause 

Hibernate supports 5 types of join clauses
1. inner join
1. cross join
1. left outer join
1. right outer join
1. full outer join


Cross Joins don't require a Join Clause, you just have to specify both 
classes in the FROM statement
    
    EX 1: Cross Join
    
        (from Product p, Supplier s)
        
        
For other joins
- use a JOIN clause after the FROM clause
- specify the following
    - type of join
    - object property to join on
    - an alias for the other class
    
    
    EX 2: Inner Join
    
        select s.name, p.name, p.prive from Product p inner join p.supplier as s;
        
    
    EX 3: (retrieving objects using similar syntax) 
    
        from Product p inner join p.supplier as s;
        
## HQL Aggregate Methods

Hibernate's aggregates work the same way as they do in SQL

1. avg(property name)
    - the average of a property's value
1. count(property name or *)
    - the # of times a property occurs in the results
1. max(property name) 
    - max value of property values
1. min(property name)
    - min value of property values
1. sum(property name)
    - sum total of property values
    
    
    EX:
        select count(*) from Product p;
        

## HQL - Names Queries
Named queries are created via class-level annotations on entities. 
- usually they apply to the entity in whose source file they occur
- (but this isn't required)


    EXAMPLE CREATION
    
        @NamedQueries({
            @NamedQuery(name = "supplier.findAll", 
                query = "from Supplier s"),
            @NamedQuery(name = "supplier.findByName", 
                query = "from Suppler s where s.name=:name"),
        })

    EXAMPLE USAGE
    
        Query query = session.getNamedQuery("supplier.findAll");
        List<Supplier> suppliers = query.list();
        
    
## HQL Native SQL
Sometimes HQL doesn't get the job done, and you just have to rely on good ole'
fashioned SQL. 
- many databases support special features through its specific dialect of SQL, that HQL would be insane to try and support.
- HQL doesn't support stored procedures

Modifying SQL Statements to make them work w/ Hibernate ORM layer
- modify SQL to include Hibernate aliases that correspond to objects/object properties. 
    - {objectname.*} = wildcard = "ALL PROPERTIES"
    - {objectname.property} = direct specification of an alias
    
Hibernate translates object property names into underlying SQL columns (that has a domain!)
- uses org.hibernate.SQLQuery (extends org.hibernateQuery)


    EXAMPLE
        
        public SQLQuery createSQLQuery(String queryString) throws HibernateException
        
        
Once the string has been passed to createSQLQuery() method the SQL result should be associated w/ an 
existing Hibernate entity, join or scalar result. 
- using the following methods
    - addEntity()
    - addJoin()
    - addScalar()
   
   
    FULL EXAMPLE
   
        String sql = "select avg(product.price) as avgPrice from Product produce";
        SQLQuery query = session.createSQLQuery(sql);
        query.addScalar("avgPrice", Hibernate.DOUBLE);
        List results = query.list();
        
        
    ADVANCED EXAMPLE
    
        String sql = "select { supplier.*} from Supplier supplier";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity("supplier", Supplier.class);
        List results = query.list();
        
        // Hibernate mods the SQL and execs following cmd against DB
        
            select Supplier.id as id0_, Supplier.name as name2_0_ from Supplier supplier;
            
## HQL - Enable Logs and Comments
Useful for debugging and feedback to admins when "shit done broke"
- probably a bad idea to keep it on all the time though. 

### LOGS
- easiest way to enable
    - set "show_sql" property to true in hibernate.cfg.xml

Log entries are prefixed w/ "Hibernate:"

Debug
- setting log level to Debug will inject SQL statements into your log files as well as provide information about how
the query was parsed by Hibernate and translated into SQL

### COMMENTS

    EXAMPLE
    
        public Query setComment(String comment);
        
- Hibernate only adds comments to SQL statements if "hibernate.use_sql_comments" is set to true in the Hibernate 
configuration.
