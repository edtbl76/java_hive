#Execution of Java Programs
![alt-test](/Users/emangini/IdeaProjects/edtbl76/HowToDoInJava/Intro/src/com/jdk_jre_jvm/Java-Execution-Flow.png)

    1.) Java Source Code written in *.java file using IDE
    2.) java compiler (e.g javac) compiles source code into bytecode (*.class file)
    3.) Class file maybe executed in any platform/OS that has a JVM
    4.) JVM translates platform INDEPENDENT bytecode into platform DEPENDENT native machine code 
    (which can be executed by the local system)

# THE JVM (Java Virtual Machine)

- Provides a RUNTIME ENVIRONMENT in which bytecode (*.class files) can be executed. 
- many different vendors 
- optimizes java performance
    - garbage collection
    - adaptive optimizer

## HotSpot

most popular implementation of JVM. This is the Oracle flavor

## Client v. Server JVM

These are similar, but the Server VM has been tuned to maximize peak operating speed. 
The Server version is specifically intended for execution of long-running server applications.

# JVM Architecture
![alt-text](/Users/emangini/IdeaProjects/edtbl76/HowToDoInJava/Intro/src/com/jdk_jre_jvm/JVM-Architecture.png)

## Class Loader

    Responsible for 3 major functions
    1.) Class Loading
    2.) Linking
    3.) Initialization
    
### Class Loading

    3 Types of class Loaders
        - Bootstrap
        - Extension
        - Application
        
    Process
    1.) JVM determines dependencies for some class Example.class
    2.) BOOTSTRAP loader tries to find the class
        - rt.jar file
        - JRE lib folder
    3.) If class isn't found, then EXTENSION class loader searches in
        - jre\lib\ext folder
    4.) if the class STILL isn't found, then the APPLICATION classloader searches all the jar files
    and classes in CLASSPATH EV of the system. 
    5.) if the class still isn't found, then ClassNotFoundException is thrown. 
    
### Linking

    After a class is loaded, linking is performed. 
    
    BYTECODE VERIFIER
        - verifies that the bytecode is proper, else Verification error occurs. 
        
    Memory Allocation for static variables and methods inside the class is performed here.
    
### Initialization

    HOME STRETCH PHASE
    - all static variables are ASSIGNED with the original values
    - static blocks are EXECUTED. 
    
    (Note the difference...)
    
--
## JVM MEMORY AREAS

    Method Area:  
        stores class structures like METADATA, constant runtime pools and METHOD CODE
        
    Heap:
        all objects created curing application execution
        
    Stacks:
        local variables, intermediate results
        
        Each thread has its own JVM Stack which is created simultaneously with the thread. 
        - all of the stack variables are called THREAD-LOCAL VARIABLES, because they are specific
        to the thread that creates hem.
        
    PC Register:
        stores PHYsical memory addy of statements currently being exec'd
        (Each thread in Java is assigned its own separate PC register) 
        
    NATIVE Method Stacks
        - Java supports AND USES NATIVE CODE
        - much of the low level code is written in C/C++
        - This code is held in its own special set of stacks. 
        
## JVM Execution Engine

![alt-test](/Users/emangini/IdeaProjects/edtbl76/HowToDoInJava/Intro/src/com/jdk_jre_jvm/OS-Specific-Interpreters.png)
    
This is the actual component of the JVM responsible for using either the INTERPRETER or JIT COMPILER for
reading the byte code, converting it into native machine code and executing the instructions 
ONE AT A TIME.  

### INTERPRETER

This directly executes bytecode w/o performing any optimizations. 

    1.) Reads byte-code instruction
    2.) looks up a predefined JVM-instruction <--> machine code mapping
    3.) executes the machine code. 
    
### JIT (JustInTime Compiler)

This improves performance over the INTERPRETER
This is the default method of execution. 

    Interacts w/ JVM at runtime
    - Reads entire sequences of ByteCode (typically one code block at a time)
    - calculates the most optimized translation of that bytecode into machine code
    - executes the improved machine code representation.
    
It is NOT recommended to disable this unless you are debugging the compiler itself. 

--
# JRE (Java Runtime Environment)

The JRE is the minimum requirement on systems that will be used to execute Java applications. 
This is a software package which bundles
- Java libs (jar files)
- Native libraries required for both the Client and Server JVMs
- the JVM itself
- property settings, resource files 
    - rt.jar, charsets.jar
- security management files
    - java.policy (sec policy), java.security (sec props)
- TrueType font files used by Java platform
- applet/servlet support clases
- java extensions

# JDK (Java Development Kit)

THE JDK is a superset of the JRE. It contains everything in the JRE in addition to 
tools required for developing, debugging and monitoring Java applications. 

A non-exhaustive list of additional components of JDK


