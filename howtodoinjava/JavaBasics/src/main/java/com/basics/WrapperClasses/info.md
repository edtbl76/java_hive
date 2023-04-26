# Java Wrapper Classes

These are special classes in the java.lang.* PACKAGE called TYPE WRAPPERS. Their purpose is to 
ENCAPSULATE a PRIMITIVE TYPE within an object. As their are 8 primitive data types, there are also
8 TYPE WRAPPERS. 
- this "wraps" around a data type giving it the appearance/accessibility and properties of an object
- this allows flexibility in circumstances where we want to use the properties of a PRIMITIVE DATA TYPE
but an OBJECT is required. 
    - (the wrapper classes include internal methods to unwrap the object and give back the data type)
 
## WHEN TO USE
1. when 2 methods want to refer to the same instance of a PRIMITIVE TYPE, pass a WRAPPER CLASS as a
method argument instead.
2. Java "GENERICS" only support Objects
    - to store primitive types, they must be wrapped in their associated Type Wrapper
3. Collections in Java only support Objects
    - to store primitive types, they must be wrapped in their associated Type Wrapper
4. When you want to refer NULL from a data type, you need an object. Primitives can't have NULL as a value

## CONVERSION 

### Primitive type to Wrapper

    
    EX:
    1. //using constructor
    Integer object = new Integer(10);
    
    2. // using static factory method
    Integer object2 = Integer.valueOf(10);
    

### Wrapper to Primitive Type

    Ex:
    
    Integer object = new Integer(10);
    Long object2 = new Long(10L);
    
    int value = object.intValue();  // wrapper to primitive
    long value2 = object.longValue(); // wrapper to primitive
    
        - the pattern should be clear at this point.
        
        
## AUTOBOXING

AUTOBOXING is the automatic conversion of a primitive type into its corresponding object wrapper classes. 

    EX:
    
        Character ch = 'a';
        
        List<Integer> integerList = new ArrayList<>();
        for (int i=1; i < 10; i++) {
          integerList.add(i); //int to Integer
        }
        
        // integerList is a list of Integer objects, not a list of primitive type ints. 
        // this means that the command inside the loop behaves like the following at runtime.
        
            integerList.add(Integer.valueOf(i));  
            
                // If you recall, Integer.valueOf(i); converts an int to an Integer, 
                // which is what the "autoboxing" example above is doing.
                
## UNBOXING

This is the process when the conversion goes from Wrapper to corresponding primitive type. 
This means that we can pass/assign a WRAPPER object to an argument/reference that accepts the
primitive type

    
    EX:
        public static int sumOfEven(List<Integer> integerList) {
            
            int sum = 0;
            for (Integer i: integerList) {
                if(i % 2 == 0)
                    sum += i        // Integer to int
            }
            return sum;
        }
        
    // Note: Modulus and Unary Plus only support primitive data types. The compiler automagically
    converts the Integer to int at runtime by invoking the intValue() method under the covers to 
    make the code work. 
    
### Autoboxing and Unboxing Pros/Cons

    - while this does make code easier to read, it also demonstrates less control over what is happening
    at runtime. 
    
    When performance is required, it is better to be explicit. 
    

