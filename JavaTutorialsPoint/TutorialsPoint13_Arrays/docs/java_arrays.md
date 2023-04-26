# Array

    - data structure which stores a fixed-size sequential collection of elements of the
    szme type. 
    
    - stores a collection of data, but is easier (for some) to think of a collection of
    variables of the SAME TYPE
    
    - declare a single array variable and its type, and then use the name of the array,
    and the location (called indexes) to refer to the individual variables and the
    data that they contain.
    
    - data in an array is accessed through the index.
    
    - arrays use a 0-based index system such that the range is
        0 - arrayRefVar.length - 1
    
    
### Creating Arrays

    arrayRefVar = new dataType[arraySize];
    
        - this creates an array using the new keyword
        - this defines the data type of the array 
        - this defines the SIZE of the array
        - this assigns the reference object of the newly created array to the
        variable 'arrayRefVar'
        
        declaraing an array variable,
        creating an array, 
        assigning reference of array to variable can be combined into one
        statement:
        
            dataType[] arrayRefVar = new dataType[arraySize]  OR
            dataType[] arrayRefVar = {value0, value1, ..., valuek}
    
    RIGHT WAY
        - dataType[] arrayRefVar;
    
    
    WRONG WAY
        - dataType arrayRefVar[];
        
        
# java.util.Arrays

    1   public static int       binarySearch(Object[] a, Object key)
    
            - Searches specified array of Object (Byte, int, double, etc.) for
            specified value using a binary search algorithm. 
            
            - NOTE: array must be sorted prior to making this call. 
            
            - returns INDEX of search key if it is contained in the list, otherwise
            it returns ( - (insertion point + 1))
            
    2   public static boolean   equals(long[] a, long[] a2)
    
            - Returns true if 2 specified arrays of longs are equal to one another
            - arrays are considered equal if both arrays:
                - have same # of elements
                - all corresponding pairs of elements in the 2 arrays are equal.
            - same method can support ALL PRIMITIVE DATA TYPES
            
    3   public static void      fill(int[] a, int val)
    
            - Assigns specified 'int' value to each element of the specified array
            of ints
            - same method can support ALL PRIMITIVE DATA TYPES
            
    4   public static void      sort(Object[] a)
        
            - Sorts specified array of objects into an ASCENDING ORDER, 
            according to the naturla ordering of its elements. 
            - same method can support ALL PRIMITIVE DATA TYPES
            
              