# Test Interfaces

## Annotations on Java Interfaces
- several annotations can be declared on interface default methods
    - @Test
    - @TestFactory
    - @BeforeEach/@AfterEach
    

    Interface "Default Methods"
    - JDK 8 feature
    - these are declared using the 'default' keyword
    
        - allows the defining of a default impl
        for a given method within a Java interface
        
    - can be useful for backward compatibility w/ existing
    interfaces


- the following annotations can be declared on *static* methods
in a test interface.
    - @BeforeAll/@AfterAll
    - (NOTE: these can be *default* instead of *static if a test class that impls a given interface is 
    annotated w/ @TestInterface(Lifecycle.PER_CLASS)) 
      

- @ExtendWith and @Tag can be declared on test interfaces to configure extensions and tags 
respectively
  

    
### Example 1: LifeCycle Annotations

    public interface TestLifecycleLogger {
    
        static final Logger LOGGER = LoggerFactory.getLogger(TestLifecycleLogger.class.getName());

        @BeforeAll
        static void beforeAllTests() {
            LOGGER.info("beforeAllTests");
        }

        @AfterAll 
        static void afterAllTests() {
            LOGGER.info("afterAllTests");
        }

        @BeforeEach
        default void beforeEachTest(TestInfo testInfo) {
            LOGGER.info("About to execute {}", testInfo.getDisplayName());
        }

        @AfterEach
        default void afterEachTest(TestInfo testInfo) {
            LOGGER.info("Finished executing {}", testInfo.getDisplayName());
        }

        
    }


### Example 2: Test Interfaces for Dynamic Tests

    interface TestInterfaceDynamicTestsDemo {
    
        @TestFactory
        default Collection<DynamicTest> dynamicTestsFromCollection() {
            return Arrays.asList(
                dynamicTest(
                  "First Dynamic Test in Test Interface",
                  () -> assertTrue(true)),
                dynamicTest(
                  "Second Dynamic Test in Test Interface",
                  () -> assertTrue(true))
            );
        }
    }

### Example 3: Tags and Extensions w/ Test Interface

    @Tag("timed")
    @ExtendWith(TimingExtension.class)
    public interface TimeExecutionLogger {

    }


### Example 4: Use Them!


    class TestInterfaceTest implements
                TestLifecycleLogger, TimeExecutionLogger, TestInterfaceDynamicTestsDemo {

        @Test
        void isEqualValue() {
            assertEquals(1, 1);
        }
    } 

#### Results: 
![TestInterfacesExample](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/TestInterfacesExample.png)
