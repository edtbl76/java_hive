# Testing Frameworks for the JVM

## JUnit
- 1995 Kent Beck and Erich Gamma

### JUnit 3
- REQUIREMENTS
    - easy to define tests that will run
    - tests should be capable of running indepedently of
    other tests
    - detect/report errors on a per-test basis
    
![Core JUnit 3 Classes](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/Junit3CoreClasses.png)

#### Standard Tests
- **junit.framework.TestCase**
    - base class from which custom classes are extended
    - **setup()**
        - calls this before test is run
    -  **teardown()**
        - calls this after test is complete
        

#### Assertion Table

| Method | Description |
| --- | --- |
| assertTrue | Asserts that a condition is true. | 
| assertFalse | Asserts that a condition is false. |
| assertEquals | Asserts that two objects are not equal.| 
| assertNotNull | Asserts that an object isn't null | 
| assertNull | Asserts than an object is null | 
| assertSame | Asserts that two objects refer to the same object | 
| assertNotSame | Asserts that two objects do not refer to the same object. |
| fail | Fails a test explicitly, throwing an **AssertionFailedError** with the optional given message |

- In each case above, if the assertion is incorrect, the method throws
an **AssertionFailedError** w/ an optional given message.


#### Example

    public class TestSimple extends TestCase {
    
        // Phase 1: Setup (for each test) 
        protected void setUp() throws Exception {
            System.out.println("<Setup>");
        }
        
        // Test 1: This test is going to succeed.
        public void testSuccess() {
        
            // Phase 2: Exercise/Simulate
            int expected = 60;
            int real = 60;
            System.out.println("** Test 1 **");
            
            // Phase 3: Verify
            assertEquals(expected + " should be equals to " + real, 
                expected, real);
        }
        
        // Test 2: This test is going to fail
        public void testFailure() {
            
            // Phase 2: Exercise/Simulate
            int expected = 60;
            int real = 20;
            System.out.println("** Test 2 **");

            // Phase 3: Verify
            assertEquals(expected + " should be equals to "
                + real, expected, real);
        }
       
        // Phase 4: Teardown (per test) 
        protected void tearDown() throws Exception {
            System.out.println("</Ending>");
        }
    }

#### JUnit 3 Test Execution
- *test runners*
    - Java apps that run test cases
    - Junit 3 provides 3 by default
        - GUI based
            - Swing
            - AWT
        - textual
    - IDEs usually impl their own JUnit test runner

- JUnit Framework provides separate class loaders for each test in
order to prevent side effects from interfering w/ each test. 
  
##### Failure vs. Error
- Test FAILURES are typically encountered when an assertion condition
isn't met.
- ERRORS are unexpected conditions not accounted for in the test code. 
    - conventional software exceptions.
    
##### Test Suite
- implemented using **junit.framework.TestSuite**
    - provides convenient way to group related tests.
    - similar to **TestCase**, impls the JUnit Framework interface
    **junit.framework.Test**

    
    EXAMPLE

        public class TestAll {

            public static Test suite() {
                TestSuite suite = new TestSuite("All tests");
                suite.addTestSuite(TestSimple.class);
                suite.addTestSuite(TestMinimal.class);
                return suite;
            }
        }

---
### JUnit 4
- released in 2006
- changed license from CPL to EPL (Eclipse Public License)
- source code
    - https://github.com/junit-team/junit4
    


    
#### Improvements from Junit 3
- changes the way in which tests are defined by using java annotations


    The architecture of JUnit 4.0 is a substantial departure from 
    that of earlier releases. 
    
    Instead of tagging test classes by subclassing 
    junit.framework.TestCase and tagging test methods by starting
    their name with 'test', you now tag test methods with the @Test
    annotation.

#### JUnit 3 vs. JUnit 4

| Feature | JUnit 3 | JUnit 4 |
| --- | --- | --- |
| Test definition | testXXX pattern | @Test annotation |
| Run before first test | Not Supported | @BeforeClass | 
| Run after all tests | Not Supported | @AfterClass |
| Run before each test | Override setUp() | @Before |
| Run after each test | Override tearDown() | @After |
| Ignore tests | Not Supported | @Ignore | 

#### Standard Tests
- uses **@Test** annotation. 
    - any public method can use this annotation to become a test method
    
##### Fixture Setup
![JUnit4 Test Life Cycle](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/JUnit4LifeCycle.png)
- **@Before/@After** annotations are used to decorate public methods that 
  should be executed before/after EACH test method execution
    - if there are 10 @Test methods in a class, then methods annotated
      with **@Before** will execute once before each (for a total of 10 
      times)
  

- **@BeforeClass/@AfterClass** these are executed before/after all
of the test methods within a class. 
  
#### Assertions

| Method | Description |
| --- | --- |
| assertTrue | Asserts that a condition is true. | 
| assertFalse | Asserts that a condition is false. |
| assertNull | Asserts than an argument is null |
| assertNotNull | Asserts that an argument isn't null | 
| assertEquals | Asserts that two objects or primitive types are not equal.| 
| assertSame | Asserts that an object is == to an object reference (Objects Only)  | 
| assertNotSame | opposite of **assertSame** 

- In each case above, if the assertion is incorrect, the method throws
  an **AssertionError** 
  
#### Example

        public class TestSimple {

            // Phase 1.1: Setup for all tests
            @BeforeClass
            public static void setupAll() {
                System.out.println("<Setup Class>");
            }

            // Phase 1.2: Setup for each test 
            @Before
            public static void setupTest() {
                System.out.println("<Setup Test>");
            }

            // Test 1: Will Succeed
            @Test
            public void testSuccess() {
                
                // Phase 2: Simulation
                int expected = 60;
                int real = 60;
                System.out.println("** Test 1 **");

                // Phase 3: Verify
                assertEquals(expected + " should be equals to "
                    + real, expected, real);
            }


            // Test 2: Will Fail 
            @Test
            public void testFailure() {
                
                // Phase 2: Simulation
                int expected = 60;
                int real = 20;
                System.out.println("** Test 2 **");

                // Phase 3: Verify
                assertEquals(expected + " should be equals to "
                    + real, expected, real);
            } 

            // Phase 4.1: Teardown for each
            @After
            public void teardownTest() {
                System.out.println("</Ending Test>");
            }

            // Phase 4.2: Teardown for all 
            @AfterClass
            public void teardownTest() {
                System.out.println("</Ending Class>");
            } 
        
        }

#### Test Execution in JUnit 4
- *test runner* responsibility in JUnit 4
    - instantiation
    - calling setup/teardown
    - executing test
    - handling exceptions
    - sending notifications


- **BlockJUnit4ClassRunner**
    - default JUnit test running
    - impls JUnit4 standard test case class model. 


- **@RunWith**
    - used to change test runner
    
#### TestRunners

##### Suite
- used to run a group of tests
- **Suite.SuiteClasses**
    - allows to define individual tests belong to the suite
    

    @RunWith(Suite.class)
    @Suite.SuiteClasses({TestMinimal1.class, TestMinimal2.class})
    public class MySuite {}

##### Parameterized
- parameterized tests are used to specify different input data
that will be supplied to the same core test logic
    
- parameters are defined by annotating a static method w/
  **@Parameters**
    - this method should return a Collection of the 2D array
    providing input parameters for the test. 
      

- 2 options to inject input data
    - using the class constructor
    - Annotating class attributes w/ the annotation **@Parameter**
    

    @RunWith(Parameterized.class) 
    public class TestParameterized {
        
        @Parameter(0)
        public int input1;

        @Parameter(1)
        public int input2;

        @Parameter(2)
        public int sum;

        @Parameters(name = "{index}: input1={0} input2={1} sum={2}?"
        public static Collection<Object[]> data() {
          return Arrays.asList(
            new Object[][]{{1, 1, 2}, {2, 2, 4}, {3, 3, 9}}
          );
        }
            
        @Test
        public void testSum() {
          assertTrue(input1 + "+" + input2 + " is not " + sum, input + input2 == sum);
        }
    }

##### Theory
- alternative to parameterized tests
- expected to be true for ALL data sets

- a method provides data points/input values
- another method is annotated w/ **@Theory**, which takes parameters. 
  - these are executed w/ every possible combination of the provided
  data points.
    

      @RunWith(Theories.class) 
      public class MyTheoryTest {
        
          @DataPoints
          public static int[] positiveIntegers() {
            return new int[] { 1, 10, 100}; 
          }

          @Theory
          public void testSum(int a, int b) {
              System.out.println("Checking " + a + " + " + b);
              assertTrue(a + b > a);
              assertTrue(a + b > b);
          }
      }
    
#### Advanced Features (JUnit 4)

##### Rules
- allow flexible addition-to/redefinition-of behavior of each test method in a test class
  - included in test cases by annotating the class attribute w/ **@Rule**
  - the type of the attribute should inherit interface **org.junit.rulesTestRule**
  
| Rule | Description |
| --- | --- |
| ErrorCollector | allows execution of a test to continue after the first problem is found |
| ExpectedException | allows to verify that a test throws a specific exception |
| ExternalResource | provides a base class for Rules that set up an external resource for a test (file, socket, server, db, etc.) |
| TestName | makes current test name available inside test methods |
| TemporaryFolder | allows creation of files/folders that should be deleted when test method finishes |
| Timeout | applies same timeout to all test methods in a class | 
| TestWatcher | base class for rules that will keep a log of each passing and failing test |

#### FixMethodOrder
- **@FixMethodOrder** annotation allows tests to be executed in a given order

#### Assume
- class that offers static methods which represent assumptions which are treated as test
  preconditions.
  - EX:
    - **assumeTrue(condition)**
    - **assumeFalse(condition)**
    - **assumeNotNull(condition)**
    - **assumeThat(condition)**


- before a test is executed, JUnit checks to ensure that these preconditions are true
  - if the preconditions aren't true, then the test is ignored w/ failing assumptions.
  
#### Test Timeout
- **@Test** annotation supports a timeout value (in ms) that automatically fails if the test runs 
longer than the specified value. 
    - prevents tests from running infinitely or too long.


#### Categories
- tests can be categorized by **Categories** rest runner.
  - test methods are annotated w/ **Category**
  

## JUnit Ecosystem

### Enhancers

#### Mockito
- http://site.mockito.org
- a mock framework used in conjuntion w/ JUnit

#### AssertJ
- http://joel-costigliola.github.io/assertj/
- fluent assertions library for Java

#### Hamcrest
- http://hamcrest.org
- library w/ matchers that can be combined to create flexible/readable assertions

#### Cucumber
- https://cucumber.io
- BDD (Behavior Driven Development) acceptance test framework

#### FitNesse
- http://www.fitnesse.org
- framework designed to support acceptance testing by facilitating detailed
readable descriptions of system functions

### Alternative Frameworks

#### TestNG
- http://testng.org
- inspired by JUnit/NUnit

#### Spock
- http://spockframework.org
- testing/spec framework for Java and Groovy

#### JTest
- https://www.parasoft.com/product/jtest
- automated testing and SCA framework

#### Scalatest
- http://www.scalatest.org
- testing framework for Scala, Scala.js and Java


### Notable Derivatives

#### GoogleTest
- https://github.com/google/googletest
- Google's C++ test framework

#### JSUnit
- http://www.jsunit.net
- JavaScript

#### Mocha
- https://mochajs.org
- Unit test framework for Node.js

#### Unittest
- https://docs.python.org/3/library/unittest.html
- Unit testing framework for Python