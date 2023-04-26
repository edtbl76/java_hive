# Java Packages

    - used in java for the following reasons
        - prevent naming conflicts
        - control access
        - make searching/locating and usage of classes, interfacts, enums, and
        annotations easier. 
        
        
    EX:
        java.lang   (fundamental java classes) 
        java.io     (classes for input, output, functions are bundled in this
                    pkg)
        

### CREATING A PACKAGE

    - while creating a package a name must be chosen
    - a 'package' statement should be included along w/ the package name 
    at the top of EVERY SOURCE FILE that contains stuff 
    (i.e. classes, interfaces, enums, annotation types) you want to include
    in the package
    
    - the package line MUST BE THE FIRST LINE OF A SOURCE FILE
    
    - THERE CAN BE ONLY ONE (package statement) 
        - and it applies to everything in the file. 
        
    - IF THERE IS NO PACKAGE STATEMENT THAN ALL OF THE STUFF (see above) 
    WILL BE PLACED IN THE CURRENT DEFAULT PACKAGE.
    
    - compiling java programs w/ package statements requires the '-d' flag
        
        EX: javac -d <destination> <file>.java
        
### DIRECTORY STRUCTURE

    - TWO RESULTS OF PLACING A CLASS IN A PACKAGE
    
        - name of package becomes a part of the name of the class
        - name of the package must match the dir structure where the
        corresponding bytecode resides.
        
        