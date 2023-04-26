# Collections Framework

    - designed to meet following goals
    
        - high performance
        - had to extend/adapt a collection easily
        - allow different type of collections to work in a similar manner
        w/ a HIGH degree of interoperatability
        
    - designed to fix shortcomings of ad hoc data structures
        - (Dictionary, Vector, Stack, Properties) 
        
        - no central/unifying theme
        
# All Collections Frameworks contain the following

    Interfaces 
        - abstract data types that represent collections
        - allow collections to be manipulated independently of the 
        details of their representation
        - in OOP languages, interfaces generally form a hierarchy
        
    Implementations (i.e. Classes) 
        - concrete implementations of the collection interfaces
        - reusable data structures
        
    Algorithms
        - methods that perform useful computations (searching/sorting) on 
        objects that implement collection interfaces. 
        - polymorphic
            - the same method can be used on MANY different implementations
            of the appropriate collection interface
            
# INTERFACES
    
    COLLECTION          enables you to work w/ groups of objects
                        top of collections hierarchy
                        
    LIST                extends 'Collection'
                        - stores ordered collection of elements
                        
    SET                 extends 'Collection'
                        - stores a collection that must contain unique elements
                        
    SORTEDSET           extends 'Set'           
                        - stores a sorted Set
                        
    MAP                 maps unique keys to values
    
    MAP.ENTRY           inner class of Map
                        - describles an element (KV pair) in a map
                        
    SORTED MAP          extends 'Map'
                        - keys are maintained in ascending order
                        
    ENUMERATION         legacy interface defines methods by which you can
                        enumerate (obtain one at a time) the elements in a
                        collection of objects
                        (OBSOLETE - superceded by ITERATOR) 
                        
                        
### CLASSES

    set of standard collection classes that implement Collection interfaces
        - some are as-is full implementations
        - same are abstract classes providing skeleton for other concretes
        
    
    AbstractCollection          implements (most of) Collection
    
    AbstractList                extends AbstractCollection
                                implements (most of) List
                                
    AbstractSequentialList      extends AbstractList
                                ( for use by collection that uses
                                sequential rather than random access of
                                its elements) 
                                
    LinkedList                  extends AbstractSequentialList
                                (standard LinkedList)
    
    Arraylist                   extends AbstractList
                                (standard dynamic Array) 
                                
    AbstractSet                 extends AbstractCollection
                                implements (most of) Set
                                
    HashSet                     extends AbstractSet
                                (for use w/ a hash table) 
                                
    LinkedHashSet               extends HashSet
                                (allows insertion-order iterations)
                            
    TreeSet                     extends AbstractSet
                                (implements a set stored in a tree) 
                                
    AbstractMap                 implements (most of) Map
    
    HashMap                     extends AbstractMap 
                                (for use w/ a hash table) 
                                
    TreeMap                     extends AbstractMap
                                (for use w/ a tree) 
                                
    WeakHashMap                 extends AbstractMap
                                (use hash table w/ weak keys) 
                                
    LinkedHashMap               extends HashMap
                                (allows insertion-order iterations) 
                                
    IdentityHashMap             extends AbstractMap
                                - uses reference equality when 
                                comparing documents
                                
### COLLECTION ALGORITHMS

    STATIC VARIABLES    (these are all immutable) 
    
            EMPTY_SET
            EMPTY_LIST
            EMPTY_MAP
            
    ALGORITHMS
    ITERATOR
    COMPARATOR
                                
                               
         
         