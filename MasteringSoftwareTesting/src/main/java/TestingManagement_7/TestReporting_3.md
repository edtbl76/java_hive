# Test Reporting

### XML Reporting
- JUnit used XML to report execution of test suites. 
    - this was adopted as the standard for test reporting in the xUnit family
- XML sucks to read, so programs were written to translate it into
a human-friendly read out
    - EX:
        - Jenkins uses **JunitResultArchiver** to parse XML files to HTML
    
#### No Universal XML Definition
- JUnit test executors use its own XSD (XML Schema Definition) that may
  very from executor to executor.
    - Maven, Gradle,etc. 


## Maven Surefire
![MavenSurefireSchema](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/MavenSurefireSchema.png)

### Dependency 

#### pom.xml (Maven)

    <reporting>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-report-plugin</artifactId>
                <version>${maven-surefire-report-plugin.version}</version>
            </plugin>
        </plugins>
    </reporting>

## Allure
- "lightweight open source framework for generating test reports
  in different languages"
- can be run from command line or built in to Maven
- (see pom.xml)
