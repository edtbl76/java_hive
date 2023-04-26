# THIS keyword

- this keyword holds the reference to the CURRENT INSTANCE OF A CLASS. 
    - useful in scenarios where we are inheriting a method from a parent class into a child class and 
    want to invoke the method fromm the CHILD class specifically. 
    
    - can be used to access STATIC fields in a class as well
        - but recommended approach as always is to use CLASS REFERENCE
        - ex: MyClass.STATIC_FIELD
        
# SUPER keyword
- super is like THIS, but i always holds a reference to the PARENT class of any given class. 
- this allows us to access the FIELDs and METHODS of a parent class in any child class
