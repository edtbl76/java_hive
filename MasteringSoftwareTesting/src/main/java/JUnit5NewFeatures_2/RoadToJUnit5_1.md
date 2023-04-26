# Road to JUnit 5

## JUnit 5 Motivation
- Despite popularity of JUnit 4, it was over 10 years old. 
- need for improvement based on 3 major challenges.

### Modularity
![JUnit 4 Architecture](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/JUnit4Architecture.png)
- JUnit 4 is monolithic
    - all capabilities are derived from junit.jar
    - tightly coupled
    

    Johannes Link (Core team member of JUnit 5)

        The success of JUnit as a platform prevents the
        development of JUnit as a test tool. 

        The basic problem we want to solve is executing test
        cases by separating a sufficiently powerful and stable 
        API


### JUnit 4 Runners
- API is limited to a single runner at a time
  - non-composable
  
  
    EX:
      
      we couldn't run a parameterized test w/ Spring test
      support because they both need their own runner.

        @RunWith(Parameterized.class)
        public class MyParameterizedTest {
          
            @Test
            public void myFirstTest() {
                // brilliantly written test code
            }
        }

        @RunWith(SpringJUnit4ClassRunner.class) 
        public class MySpringTest {

            @Test
            public void anotherTest() {
                // brilliantly written test code 
            }
        }
      

### JUnit 4 Rules

#### History
- 4.7 introduced **@Rule**
  - allow addition/redefinition of test behavior by exec'ing
  some code before/after the execution of the test
    
- 4.9 adds **@ClassRule** annotation for static fields
  - allows addition/redefinition of test behavior by exec'ing
    some code before/after all tests in class
    
    
    public class MyRuleTest {
      
        @ClassRule
        public static TemporaryFolder temporaryFolder = 
            new TemporaryFolder();

        @Test
        public void anotherTest() {
            // brilliantly written test code
        }
    }

#### Drawbacks
- for complex tests, we can't use a single rule entity for
method-level tests or class-level tests
    - presents limitations to the customization of test life-cycle
    management (i.e. before/after behavior)

---
## JUnit 5 Inception

### Design Principles
- Modularization
  - modular architecture, allowing devs to use the specific parts
  of the framework that they require


- Extension Model w/ a focus on composability
  - extensibility is a first class citizen of modern testing frameworks
    - (or any framework for that matter)
  - provide seamless integration w/ 3rd party frameworks
    - Mockito, Spring, et al.
  
- API Segregation
  - decouple test discovery and execution from test definition
  
  
- Backward Compatibility
  - Support execution of legacy versions of JUnit
  
  
- Modern programming model for writing tests
  - support for Java8+
    - lambda expressions, etc.


---
## JUnit 5 Community
- https://github.com/junit-team/junit5
- EPL v1.0 license
  - *junit-platform-surefire-provider* released under Apache v2.0
