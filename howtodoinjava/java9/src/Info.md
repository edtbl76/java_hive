# JAVA 9 Features and Enhancements

## JPMS (Java Platform Module System)
- main highlighted feature of Java 9
- a.k.a Project Jigshaw

- a module is a new construct

    
    APPLICATION is redefined under new modular programming
        - "a collection of interacting modules wih a well-defined boundaries and 
        dependencies between those modules"
        
    MODULE
        - a jar file that has a module-info.class file at root
        - using modules
            - use modulepath instead of classpath
            
    NOTE: a modular jar added to classpath will not behave like a module. 
        - it will behave like a "normal" jar.
        
### java.base Module (a.k.a the Base Module) 
- independent module w/o any dependencies on other modules

## Interface Private Methods
- previously not allowed. 

USE CASE
- if two default methods need to share code, a private interface method would allow this w/o
exposing that private method to its implementing classes

FOUR RULES FOR "PRIVATE METHODS IN INTERFACES"
- They cannot be abstract
- can be used ONLY INSIDE THE INTERFACE
- PRIVATE STATIC METHOD can be used inside other STATIC and NON-STATIC interface methods
- PRIVATE NON-STATIC METHODS can NOT be used inside private STATIC methods.

## HTTP/2 Client
- HTTP/1.1 was released in 1997
- new HTTP/2 client introduces use cases for 3 major classes
    - HttpClient
    - HttpRequest
    - HttpResponse
    
## JShell - REPL Tool
- new CLI interactive tool to evaluate declarations, statements and expressions written in 
java
    - allows us to execute Java code snippets and get immediate results w/o having
    to create a solution or project.
    - launch inbuilt code editor in separate window
    - launch code editor of your choice in separate window
    - exec dode when save operation happens in external editors
    - load pre-written classes from the file system.
    
## PLATFORM and JVM Logging
- improved logging in platform (JDK) classes and JVM Components. 
- allows you to specify logging framework of your joice as logging backend for logging
messages from JDK classes
    - API is meant to be used by classes in the JDK
    - NOT MEANT TO BE USED BY APPLICATION CLASSES
        - app code should continue to use other logging apis. 
    - API DOES NOT LET YOU CONFIGURE THE LOGGER PROGRAMMATICALLY
    
### LOGGING API EXTENSIONS 
- java.lang.System.LoggerFinder
    - abstract static class as a service interface
- java.lang.System.Logger
    - interface that provides logging API
- getLogger()
    - overloaded method in java.lang.System that returns a Logger instance
    
- -Xlog CLI option
    - gives a single point of access to all messages logged from all classes of JVM
    
    
        EX: 
        -Xlog[:][:[][:[][:]]]

## PROCESS UPDATES
- Prior to Java 5
    - Runtime.getRuntime().exec() was the only way to spawn a new process inJava. 
- Java5+
    - ProcessBuilder API was introduced to support a cleaner interface for 
    spawning new processes. 
    
- Java9
    - java.lang.ProcessHandle.Info
        - interface that provides a way to get additional information about processes
        that were created w/ ProcessBuilder API
    - attributes it collects:
        - command used to start the process
        - arguments provided to the command
        - time instant when process was started
        - total time spent running process
        - user who created process. 
        
    - process.toHandle()
        - used to collect information on a newly spawned process
    - ProcessHandle.allProcesses()
        - gets a stream of ProcessHandle instances of all processes available to the system
    - children() and descendants()
        - methods for getting child processes
        
## Collection API Updates
- Java9 introduces Immutable Collections
    - immutable list
    - immutable set
    - immutable map
    
## Stream API Updates
- takeWhile()
    - for ORDERED STREAMS
        - returns the longest prefix of elements taken from the stream that match the 
        given predicate, from the beginning of the stream
    - for UNORDERED STREAMS
        - returns a subset of the stream's elements that match the given predicate
        (but not all), from the beginning of the stream
- dropWhile()
    - returns the remaining items were not matched by takeWhile()
    
- Stream.ofNullable()
    - allows the creation of single element stream which wraps a value if not NULL
    
## MULTI-RELEASE JAR FILES
- JAR can contain different versions of a class, compatible to different JDK releases. 
    - version matrix for class leaders is stored in MANIFEST.MF
        - MANIFEST.MF must include "Multi-Release: true" in main section
    - META-INF contains a versions subdirectory whose int-named subdirectories store
    version-specific class and resource files. 
        - starts w/ 9 (for version 9)
        
## Deprecated Tag Changes

- @Deprecated adds two new attributes
    - forRemoval
        - indicates whether the annotated element is subject to removal in a future
        version
    - since
        - returns the version in which the annotated element became deprecated
        
- NOTE: it is considered best practices to justify/explain the reasons for feature
deprecation using the @deprecated javadoc tag
    - even better... suggest a suitable replacement.
    
## STACK WALKING
- stack = LIFO
- At JVM level, stack stores frames
    - a new frame is created and PUSHed to the top of the stack each time a method is invoked.
    - a frame is POPed (destroyed) when the invocation completes. 
    - each frame on a stack contains
        - its own array of local variables
        - its own operand stack
        - its own return value
        - and a reference to the runtime constant pool of the current method's class
- in a given thread, only ONE FRAME is active at any point
    - active frame = CURRENT frame
    - active frame's method is CURRENT METHOD
    
- PRE-Java9
    - StackTraceElement = stack frame
    - Thread.getStackTrace() or Throwable.getStackTrace()
        - methods to get complete stack.
        - returned an array of StackTraceElement objects
        
- Java9
    - StackWalker
        - class that provides easy and efficient stack walking using sequential 
        stream of stack frames for current thread. 
        - evaluates frames lazily
        
## Java Doc Updates
- javadoc generates HTML5 markup (HTML 4.01)
    - requires -html5 parameter to go in the CLI args. 
    
## Potpourri of other changes
- Reactive Streams API
- GC (Garbage Collector) Improvements
- Filter Incoming Serialization Data
- Deprecation of Applet API
- Indify String Concatenation
- Enhance Method Handles
- Compact Strings
- Parser API for Nashorn
        