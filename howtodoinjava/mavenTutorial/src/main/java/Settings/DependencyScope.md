# DEPENDENCY SCOPES

# PROVIDED
    <scope>provided<scope>
- used during BUILD and TEST
- required for RUN
- should NOT BE EXPORTED

# RUNTIME
    <scope>runtime</scope>
- not needed for BUILD
- required for TEST and RUN

# TEST
    <scope>test</scope>
- not needed for BUILD and RUN
- needed to COMPILE and RUN UNIT TESTS

# SYSTEM
    <scope>system</scope>
- similar to PROVIDED, but they are NOT retrieved from remote repository
- these are external dependencies.

# IMPORT
    <scope>import</scope>
- only supported on a dependency of type POM in the dependencyManagement section
- indicates the dep to be replaced w/ the effective list of deps in the specified pom's 
dependencyManagement section

# Maven Dependency Transivity Resolution
If a dependency is set to the scope in the LEFT column, transitive dependencies in the TOP ROW
will result in dependency w/ the scope listed at their intersection

| Dependency | compile | provided | runtime | test |
| --- | --- | --- | --- | --- |
| compile | compile | - | runtime | - |
| provided | provided | - | provided | - |
| runtime | runtime | - | runtime | - |
| test | test | - | test | - |
