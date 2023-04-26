# Java String (java.lang.String)

an IMMUTABLE sequence of characters that can't be changed once created. 

## String Literals

- easiest and RECOMMENDED way to create strings. 

        EX:
        String literalExample = "Hello World!";
        System.out.println(literalExample);
        
### String Pool
- literals are stored in STRING POOL (a special memory area created by the JVM)
- there can be only ONE instance of a string
    - any second String w/ the same character sequence will have the reference of the
    first string stored in the string pool
    - SUPER efficient (saves a lot of physical memory)

    
    EX:
        String me1 = "Ed";
        String me2 = "Ed";
        String me3 = "Ed";
        String me4 = "Ed";


- The first instance above will have an instance inside the String pool. 
    - the other three instances will share the reference of the string literal that
    was created for the first literal.
    
    
## STRING OBJECT

- Sometimes we don't WANT to have references to the same instance. We can create one string
per object by using the NEW keyword. (which forces allocation of memory on the heap)

    
    EX:
        // 3 separate instanceof String w/ the same value in heap memory. 
        // Not as space efficient.
        String me1 = new String("Ed");
        String me2 = new String("Ed");
        String me3 = new String("Ed");
        


        

    
        
    

        