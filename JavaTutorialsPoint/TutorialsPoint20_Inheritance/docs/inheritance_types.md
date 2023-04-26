# SINGLE INHERITANCE

    public class A {}
    public class B extends A {}
    
    - one base class
    - one subclass
    
# MULTILEVEL INHERITANCE  (DEPTH) 

    public class A {}
    public class B extends A {}
    public class C extends B {}
    
    - one base class
    - subclass of base class
    - subclass of subclass of base class. 
    
    
# HIERARCHICAL INHERITANCE (BREADTH) 

    public class A {}
    public class B extends A{}
    public class C extends A{}
    
    - one base class
    - multiple subclasses of base class
    
# MULTIPLE INHERITANCE 

    public class A {}
    public class B {}
    public class C extends A, B {} // illegal Java code
    
    - multiple base classes
    - one subclass pointing to multiple base classes .
    
    NOTE: JAVA DOES NOT SUPPORT MULTIPLE INHERITANCE