# Tagging and Filtering Tests

### @Tag
- the **@Tag** annotation can be applied to tests at the class/method
level to be used in test filtering/discovery
  
    
    EXAMPLE

        @Tag("simple")
        class SimpleTaggingTest {
            
            @Test
            @Tag("fun")
            void testingFun() { }
        }

#### Limitations for Tag Syntax
- can't be null or blank
- trimmed tags can't contain any white space
    - (trimmed, means that leading/trailing whitespace has 
      been removed)
- trimmed tag must not contain ISO control characters or reserved
characters
    - comma, parenthesis, ampersand, pipe and bang

---
## Filtering tests (Maven)
- Jupiter tests require *maven-surefire-plugin*
- *maven-surefire-plugin* includes inclusion/exclusion support
which can be used to filter tagged tests
    - **includeTags/excludeTags** properties of *maven-surefire-plugin*
    

    EXAMPLE

        <build>
            <plugins>
                <plugin>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <version>${maven-surefire-plugin.version}</version>
                    <configuration>
                        <properties>
                            <includeTags>functional</includeTags>
                            <excludeTags>non-functional</excludeTags>
                        </properties>
                    </configuration>
                    <dependencies>
                        <dependency>
                            <groupId>org.junit.platform</groupId>
                            <artifactId>junit-platform-surefire-provider</artifactId>
                            <version>${junit.platform.version}</version>
                        </dependency>
                        <dependency>
                            <groupId>org.junit.jupiter</groupId>
                            <artifactId>junit-jupter-engine</artifactId>
                            <version>${junit.jupiter.version}</version>
                        </dependency>
                    </dependencies>
                </plugin>
            </plugins>
        </build>
- This will only execute Example 1 below


### Example 1 - Simple (Class Only)

    EXAMPLE

        @Tag("functional")
        class FunctionalTest {
            
            @Test
            void testOne() {
                System.out.println("Functional Test 1");
            }

            @Test
            void testTwo() {
                System.out.println("Functional Test 2");
            }
        }


### Example 2 - Class and Method

    EXAMPLE

        @Tag("non-functional")
        class NonFunctionalTest {
            
            @Test
            @Tag("performance")
            @Tag("load")
            void testOne() {
                System.out.println("Non-Functional Test 1");
                System.out.println("Performance");
                System.out.println("Load");
            }

            @Test
            @Tag("performance")
            @Tag("stress")
            void testTwo() {
                System.out.println("Non-Functional Test 2");
                System.out.println("Performance");
                System.out.println("Stress");
            }

            @Test
            @Tag("security")
            void testThree() {
                System.out.println("Non-Functional Test 3");
                System.out.println("Security");
            }

            @Test
            @Tag("usability")
            void testFour() {
                System.out.println("Non-Functional Test 4");
                System.out.println("Usability");
            }
        }

#### Results (Based on Config)
![MavenSurefireFilteringExample](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/RelativeExecutionOrderOfUserCodeAndExtensions.png)


### Maven Regular Support
- this is the standard Maven support for test filtering with
*maven-surefire-plugin*


    <configuration>
        <includes>
            <include>**/Test*.java</include>
            <include>**/*Test*.java</include>
            <include>**/*TestCase.java</include>
        </includes>
    </configuration>
    <configuration>
        <excludes>
            <exclude>**/*TestCircle.java</exclude>
            <exclude>**/*TestSquare.java</exclude>
        </excludes>
    </configuration>

- The initial three patterns are included by default when using
the *maven-surefire-plugin*
    - Any java file that begins/ends with the word **Test**
    - Any java file that ends w/ the word **TestCase**

---
## Filtering tests (Gradle)
- Gradle can select tests for execution based on the following
- by default, all engines and tags are included in the test plan
  - only the classname containing the word "Tests" is applied
  
#### Test Engine
- the keyword **engines** can be used to include/exclude 
  the engine to be used
  - EX:
    - *junit-jupiter*
    - *junit-vintage*
  
#### Jupiter Tags
- using keyword **tags**

#### Java packages
- using keyword **packages**

#### class name patterns
- uses keyword **includeClassNamePattern**
    

    EXAMPLE

      junitPlatform {
          filters {
              engines {
                include 'junit-jupiter'
                exclude 'junit-vintage'
              }
              tags {
                include 'non-functional'
                exclude 'functional'
              }
              packages {
                include 'io.github.bonigarcia'
                exclude 'com.others', 'org.others'
              }
              includeClassNamePattern '.*Spec'
              includeClassNamePatterns '.*Test','.*Tests'
          }
      }
- NOTE: (using the tests from the Maven Example Above)
![GradleFilteringTaggingExample](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/GradleFilteringTaggingExample.png)


---
## Meta-annotations
- a meta-annotation is an annotation that is used in the definition
of other annotations
    - this is supported by JUnit Jupiter
    - allows the definition of composed annotations that
  inherit the semantics of the meta-annotations

![ExampleTestTaxonomy](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/ExampleTestTaxonomy.png)
- see examples.* for the Annotations we created based on this hierarchy


### Test Example 1
- this uses our **@Functional** test annotation at the class level


    EXAMPLE

        @Functional
        class FunctionalTest {

            @Test
            void testOne() {
                System.out.println("Test 1");
            }

            @Test
            void testTwo() {
                System.out.println("Test 2");
            }

        }


### Test Example 2
- uses are meta-annotations at the method level


        class NonFunctionalTest {

            @Test
            @Load
            void testOne() {
                System.out.println("Test 1");
            }

            @Test
            @Stress
            void testTwo() {
                System.out.println("Test 2");
            }

            @Test
            @Security
            void testThree() {
                System.out.println("Test 3");
            }

            @Test
            @Usability
            void testFour() {
                System.out.println("Test 4");
            }

        }

### Running Tests

#### Filtering Tests by tags (functional) Maven
![MavenMetaAnnotationFiltering](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/MavenMetaAnnotationFiltering.png)

#### Filtering Tests by tags (non-functional) Gradle
![GradleMetaAnnotationFiltering](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/GradleMetaAnnotationFiltering.png)
  