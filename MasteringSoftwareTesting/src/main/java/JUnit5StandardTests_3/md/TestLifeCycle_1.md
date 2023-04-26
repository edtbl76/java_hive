# 1 Test Lifecycle

    Talk is cheap. Show me the code. 
        - Linus Torvalds


### 4 Stages of Unit Test Case

#### Setup (optional)
- test initializes the test fixture

#### Exercise
- test interacts w/ SUT
    - SUT produces outcome as result

#### Verify
- outcome from SUT compared to expected value
    - uses one or more assertions/predicates
- comparison results in a "test verdict"

#### Teardown (optional)
- test releases test fixture to put SUT back to 
initial state
  
### Annotations
- **org.junit.jupiter.api**
  - package containing JUnit 5 annotations used for implementing
  unit test case life cycle.
    
![JupiterAnnotationLifecycle](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/JupiterAnnotationTestLifecycle.png)

![AnnotationToTestCaseStages](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/UnitTestStateToJupiterAnnotations.png)

#### @Test
- IDs methods that have to be executed as Tests.

    
    class SimpleJUnit5Test {
        
        @Test
        void mySimpleTest() {
            // my test logic here.
        }
    }

##### JUnit4
- can take an optional **long** attribute that represents a timeout in milliseconds
- test classes and methods must be *public*

##### JUnit5
- no attributes
- test classes and methods aren't required to be *public*


#### Setup/Teardown Annotations

| JUnit 5 | Description | JUnit 4 | Phase |
| --- | --- | --- | --- |
| @BeforeEach | Method exec'd before EACH @Test in current class | @Before | Setup |
| @AfterEach | Method exec'd after EACH @Test in current class | @After | Teardown |
| @BeforeAll | Method exec'd before ALL @Test in current class | @BeforeClass | Setup |
| @AfterAll | Method exec'd after ALL @test in current class  | @AfterClass | Teardown |
- Methods annotated w/ these annotations are ALWAYS inherited.

#### Example Code

    
    class LifeCycleJUnit5Test {
        
        @BeforeAll
        static void setupAll() {
            System.out.println("Setup ALL TESTS in the class");  
        }

        @BeforeEach
        static void setup() {
            System.out.println("Setup EACH TEST in the class");  
        }

        @Test
        void testOne() {
            System.out.println("TEST 1");
        }

        @Test
        void testTwo() {
            System.out.println("TEST 2");
        }

        @AfterEach
        static void teardown() {
            System.out.println("Teardown EACH TEST in the class");  
        }

        @AfterAll
        static void teardownAll() {
            System.out.println("Teardown ALL TESTS in the class");  
        }
    }

##### Result
![ExampleJupiterTestThatControlsLifeCycle](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/ExampleJupiterTestLifeCycle.png)
---
## Test Instance Lifecycle

### Test Instance
- JUnit5 Framework creates a new test instance before executing actual test
- PER-METHOD instance life cycle
  - behavior in Jupiter and JUnit 4/3
  - provides execution isolation
  
- PER-CLASS can be configured

  
    Replace @Test with 
    
      @TestInstance(Lifecycle.PER_CLASS)

  - implies that it is possible to declare **@BeforeAll/@AfterAll** as
  non-static
      - has benefits when using
        - nested tests
        - default test interfaces
  

### Relative Execution Order of User Code and Extensions
![RelativeExecutionOrder](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/RelativeExecutionOrderOfUserCodeAndExtensions.png)

---
## Skipping Tests
- **@Disabled** annotation allows tests to be skipped at the class or
method level.
  

### Example 1: Skipping Tests (Method) 

        class DisabledTest {
        
            @Disabled
            @Test
            void skippedTest() {

            }
        }


### Example 2: Skipping Tests (Class)
- a message can be provided as an argument to the annotation

    
    @Disabled("All tests in this class will be skipped")
    class AllDisabledTest {

        @Test
        void skippedTestOne() { }

        @Test
        void skippedTestTwo() { }
    }

---
## Display Names

### JUnit 4 Limitations
- JUnit4 identified tests w/ the name of the method 
annotated by **@Test**
    - this limits the way that tests are named since this
    identification method is constrained by the way methods are
    declared in Java
      
### JUnit 5
- JUnit5 provides an annotation (**@DisplayName**) to declare a
custom name for a class/method
    - displayed by test runners and reporting tools
    - can contain spaces, special characters and even emojis
    

    EXAMPLE

        @DisplayName("A special test case")
        class DisplayNameTest {
            
            @Test
            @DisplayName("Custom test name containing spaces")
            void testWithDisplayNameContainingSpaces() { }

            @Test
            @DisplayName("(`°°)`")
            void testWithDisplayNameContainingSpecialChars() { } 

            @Test
            @DisplayName(":)")
            void testWithDisplayNameContainingEmoji() { }
        }

---