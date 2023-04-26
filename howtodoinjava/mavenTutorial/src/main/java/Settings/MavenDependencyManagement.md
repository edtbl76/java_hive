# Maven Dependency Management
Example of a dependency


    EX:
    
        <dependency> 
            <groupid>junit</groupid>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        
If pom.xml points ot many artifacts of same groupId, use properties to make it easier

    
    EX:
            <properties>
                <junit.version>4.12</junit.version>
            </properties> 
            <dependency> 
                <groupid>junit</groupid>
                <artifactId>junit</artifactId>
                <version>${junit.version}</version>
                <scope>test</scope>
            </dependency> 
    
## EXTERNAL DEPENDENCY
Sometimes we have to refer to jar files which aren't in the Maven repository (local, central 
or otherwise)
- these can be placed in the project's lib folder and configured as follows. 

    EX
    
        <dependency>
            <groupId>extDependency</groupId>
            <artifactId>extDependency</artifactId>
            <scope>system</scope>
            <version>1.0</version>
            <systemPath>${basedir}\war\WEB-INF\lib\extDependency.jar</systemPath>
        </dependency>
        

## DEPENDENCY EXCLUSIONS
This breaks transitive dependencies that cause VERSION MISMATCH between a project artifact and the
artifacts from the deployment platform. 



    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>${junit.version}</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.dbunit</groupId>
        <artifactId>dbunit</artifactId>
        <version>${dbunit.version}</version>
        <scope>test</scope>
        <exclusions>
            <!--Exclude transitive dependency to JUnit-3.8.2 -->
            <exclusion>
                <artifactId>junit</artifactId>
                <groupId>junit</groupId>
             </exclusion>
        </exclusions>
    </dependency>
    
