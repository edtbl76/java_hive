# Generics
Introduced in JDK5
- "a set of language features related o the definition and use of generic types and methos"

Generic types are instantiated to form parameterized types by providing actual type args that 
replace the formal type parameters
- LinkedList<E> is a generic type, w/ type parameter E
- LinkedList<Integer> is a parameterized type w/ type argument Integer

## WHY?
- by allowing methods to take any java type as an argument and return THAT type, creates heterogeneous
typing. 
    - this provides flexibility within the language without sacrificing type safety. 
    
## How Generics Work
Type Safety
- "guarantee" from the compiler that if correct typing is used in the correct places, 
there shouldn't be a ClassCastException at runtime. 

Type Erasure
- all of the extra information added using generics into source code is  removed from the bytecode
that was generated from it. 

## WHAT YOU CAN'T DO WITH GENERICS
- you can't define a static generic parameterized member in your class

    
    private static T member;        <-- NO
    
- You can't create an instance of T


    public class GenericExample<T> {
        public GenericExample {
            new T();            <-- NO
        }
    }
    
- Generics are incompatible w/ primitives in declarations

    
    final List<int> ints = new ArrayList<>();       <-- NO
    final List<Integer> ints = new ArrayList<>();   <-- YES
    
- Generics can't be exceptions


    public class GenericException<T> extends Exception {} <-- NO