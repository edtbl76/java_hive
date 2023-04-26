# Try-With-Resources

The magic of this feature comes from a new interface

    java.lang.AutoCloseable
    
which includes a method

    void close() throws Exception;
    
The recommendation is to implement this interface on ANY RESOURCE THAT MUST BE CLOSED WHEN IT
IS NO LONGER NEEDED. 

AutoCloseable results in the JVM calling that close() method on all resources initialized in the try() 
block, immediately after the block finishes.

