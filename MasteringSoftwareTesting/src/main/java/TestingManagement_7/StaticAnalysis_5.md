# Static Analysis


## Linters
- automated software analysis tools

### Checkstyle
- analyzes Java code following different rules
    - missing Javadoc comments
    - use of magic numbers
    - nonstandard naming conventions for vars/methods
    - method arg length
    - line length
    - import hygene
    - spacing
    - class construction
    - code duplication
---
### FindBugs
- scans Java code for 3 basic types of errors

#### Correctness Defects
- apparent coding mistake
    - i.e. equal(Object) instead of equals(Object)
  
#### Bad Practices
- violations of recommended best practices
  - dropped exceptions
  - misuse of finalize 
  - etc. 
  
#### "Dodgy Errors"
- Confusing code, or written in a way that leads to error. 
---

### PMD
- polyglot static code analyzer
  - Java, Js, C++, C#, Go
  - Maven, Gradle, Eclipse, IJ, Jenkins etc. 
  
### SonarQube
- web-based open source "continuous quality assessment dashboard"
- polyglot
- reports on 
  - duplicated code
  - code smells
  - code coverage
  - complexity
  - security vulnerabilities
  
  
#### SonarCloud
- distributed version of SonarQube
- free for open source projects
- "seamless integration w/ Travis CI"
---

## Peer Review Tools

### Collaborator (SmartBear) 

### Crucible (Atlassian)

### Gerrit
- supports GH

### GH Pull Request Reviews
- Git Hub Pull Requests
