# Java 9 Notes

## Java Platform Module System

### Modularity
- PROBLEMS pre JDK 9
    - every public class can be accessed by any other class in the classpath
    which leads to inadvertent (and poor) use of classes
    - classpath doesn't have a way to detect duplication of JAR files.

### Solution: Java Platform Module System
- allows the creation of modular JAR files
  - contains an additional descriptor called *module-info.java*
    - declares dependencies to other modules using the 
  keyword *requires*
    - exports its own packages w/ the keyword *exports*
    - all nonexported pkgs are encapsulated in the module by default
  

    EXAMPLE:
      
      module mymodule {
          exports io.github.timberbacklabs;
      }
      
      requires mydependency

![ModuleRelationship](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/ModuleRelationship.png)

### Other Capabilities of JDK 9
- *jlink* tool
  - this allows the "use" modules to create a minimal runtime JDK
  thats optimized for a given app instead of a full JDK installation


- **Jshell**
  - REPL (Read-Eval-Print-Loop) utility that provides an interactive
  env to exec Java code.
  

- Collection factory methods
  - creation and population one-liners
  

    EXAMPLE

        Set<Integer> ints = Set.of(1, 2, 3);
        List<String> strings = List.of("first", "second");

- Stream API improvements
  - added 3 new methods
      - **dropWhile**
      - **takeWhile**
      - **ofNullable**
  

- Private interface methods
  - prior to JDK 9, default methods (which were intro'd in JDK8) had 
  to be public
  - in JDK 9, they can be private. 
  
- HTTP/2 and WebSockets Support
  - out-of-box support

- Multi-Release JARs
  - supports the creation of alternative versions of classes (depending
    on the version of the JRE exec'ing the JAR)
  - in *META-INF/versions/<java-version>* we can specify 
  different versions of compiled classes that are only used when the
    JRE version matches the version
    
- Better Javadoc
  - HTML5 support w/ integreated search




