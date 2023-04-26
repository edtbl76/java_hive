# The extension model of JUnit 5

### JUnit 4 to 5
- JUnit 4 used extension POINTS
    - test runners, rules etc.
    
#### JUnit 5: Extension API
- supports extension of core JUnit 5 functionality by any 3rd party

##### Extension
- marker interface used for new extensions to implement


    /**
        Marker Interface for all extensions.

        @since 5.0
    */
    @API(status = STABLE, since = "5.0")
    public interface Extension {}

### Jupiter Extension Points
- Extensions that allows the execution of code at different stages
of the test life cycle

| Extension | Impl'd by extensions that want to... |
| --- | --- |
| TestInstancePostProcessor | provide addt'l behavior just after test instantiation |
| BeforeAllCallback | Provide addt'l behavior before all tests are invoked in a test container |
| BeforeEachCallback | Provide addt'l behavior to tests before each test is invoked |
| BeforeTestExecutionCallback | Provide addt'l behavior to tests immediately before each test is exec'd |
| TestExecutionExceptionHandler | Handle exceptions during test exec |
| AfterAllCallback | Provide addt'l behavior to test containers after all tests have been invoked |
| AfterEachCallback | Provide addt'l behavior to tests after each test has been invoked |
| AfterTestExecutionCallback | Provide addt'l behavior to tests immediately after each test is exec'd |
| ExecutionCondition | Conditionate the test exec at runtime |
| ParameterResolver | Resolve parameters at runtime |

### Using Extensions
- implemented w/ **@ExtendsWith** annotation

    
        public class MyTest {
            
            @ExtendsWith(MyExtension.class)
            @Test
            public void test() {
                // Brilliant test code.
            }
        }


---
## Test Lifecycle
![Test LifeCycle Extension Callbacks](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/ExtensionCallbackLifecycleForTests.png)

### TestInstancePostProcessor
- this is an extension point used to execute logic immediately after a test is instantiated

### Pre-Test Extensions
- **BeforeAllCallback**
  - logic before ALL tests
- **BeforeEachCallback**
  - logic executed before a test method
- **BeforeTestExecutionCallback**
  - logic executed IMMEDIATELY before a test method
  
### Post-Test Extensions
- **AfterAllCallback**
  - logic afterALL tests
- **AfterEachCallback**
  - logic executed after a test method
- **AfterTestExecutionCallback**
  - logic executed IMMEDIATELY After a test method

### "In The Middle"
- **@Test**
  - annotation describing the test 
- **TestExecutionExceptionHandler**
  - extension that provides a way to collect exceptions at runtime
  
### Example 

#### Extension

    public class IgnoreIOExceptionExtension 
            implements TestExecutionExceptionHandler {
  
        @Override 
        public void handleTestExecutionException(ExtensionContext context, Throwable throwable) 
                    throws Throwable {
  
            if (throwable instanceof IOException) {
                return;
            }
            throw throwable;
        }
    }

#### Test That Uses It

    public class ExceptionTest {
    
        @ExtendWith(IgnoreIOExceptionExtension.class) 
        @Test
        public void firstTest() throws IOException {
            throw new IOException("IO Exception");
        }

        @Test
        public void secondTest() throws IOException {
            throw new IOException("My IO Exception");
        }
    }

##### Results
![ExtensionTestExample](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/ExtensionTestExample.png)
- the first test will succeed, because the Exception is handled by our Extension
- the second test fails, because it is NOT handled by our Extension


---
## Conditional Extension Points
- **ExecutionCondition**
  - conditional execution point that can be extended to create extensions that
  activate/deactivate tests based on a given condition
  - supports deactivation of
    - all tests in a container (likely a class)
    - individual tests (likely a method)
    
    
    @FunctionalInterface
    @API(status = STABLE, since = "5.0")
    public interface ExecutionCondition extends Extension {
        
        ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context);
    }

---
## Dependency Injection
- **ParameterResolver**
  - provides DI at the method level
  
#### Dependency Injection Definition
- in software, a technique in which an object receives other objects that it depends on
  - "other objects" are called dependencies
  - the receiving object is called "the client"
  - the passed("injected") object is called a service.
    

### Extension Example

    public class MyParameterResolver implements ParameterResolver {
    
        @Override
        public boolean supportsParameter(
                    ParameterContext parameterContext, ExtensionContext extensionContext)
                throws ParameterResolutionException {

            return true;
        }

        @Override
        public boolean resolveParameter(
                    ParameterContext parameterContext, ExtensionContext extensionContext)
                throws ParameterResolutionException {

            return "my parameter";
        }
    }

### Example of Test Using It

    public class DependencyInjectionTest {
        
        @ExtendWith(MyParameterResolver.class)
        @Test
        public void test(Object parameter) {
            System.out.println("My parameter " + parameter);
        }
    }

### Result
![DI Extension](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/DIExtensionOutput.png)
- injected parameter is shown in the standard output

__
## Third-party Extensions

### Quick Review of previous sections
- practically speaking, most extensions will impl more than one extension point. 

### Example: SpringExtension
- implements multiple extension points
  - **BeforeAllCallback**
  - **TestInstancePostProcessor**
  - **ParameterResolver**
  

        public class SpringExtension implements BeforeAllCallback, AfterAllCallback, 
                TestInstancePostProcessor, BeforeEachCallback, AfterEachCallback,
                BeforeTestExecutionCallback, AfterTestExecutionCalllback, ParameterResolver {

            
            @Override
            public void afterTestExecution(TestExtensionContext context)
                    throws Exception {
                // amazing test code
            }

            // more methods
        }