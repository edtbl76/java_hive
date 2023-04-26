# Source File Declaration Rules 

- Class Limits
    - One public class per source file
    - multiple non-public classes per source file
    - source file should be the same name of the 
    public class (case sensitive) w/ the ".java" extension
    - If class is defined in a package, then the "package" 
    statement should be the first statement in the source file.
    
    The source file should be the name of the public class
    (case sensitive)  w/ the ".java" extension

- Imports
    - if present, they must be written 
        - Below package statement (if present)
            - (Imports are first if there is no package
            statement)
        - Above class declaration
    - import & package statements imply to all classes 
    present in source file. 
        - it is NOT possible to declare different import/package
        statements to different classes in the source file. 
        
# Java Package

    way of categorize classes + interfaces for organization
    and ease of management. 
    
# Import Statements

    if a fully qualified name (includes package and class
    name) , the compiler can easily locate the source code
    or classes. 
    
    Import statements are instructions to the compiler
    to find & load classes that are required by the class.
      
 