# JUnit 5 Architecture
- Designed based on the programmatic clients that will consume it
    - (1.) Java Tests
        - legacy (JUnit 4)
        - modern (JUnit 5)
        - third party (not JUnit)
    - (2.) Tools
        - build tools (Maven/Gradle)
        - IDEs (Intellij/Eclipse)


### Components of JUnit 5
![JUnit 5 Architecture](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/JUnit5Architecture.png)


#### Jupiter
- novel programming and extension model of JUnit 5 Framework

#### Platform
- core of JUnit 5
- foundation for any test framework executed in the JVM
    - stepping off point for mechanisms to run
        - Jupiter tests
        - legacy JUnit4 tests
        - 3rd party tests (FitNesse, Spock etc.)

#### Vintage
- supports out-of-box execution of legacy JUnit Tests

---
### Modules
![JUnit5 Module Architecture](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/JUnit5Architecture.png)

#### Test APIs
- user facing modules (testers/devs etc.)
- provide programming model for a particular Test Engine
    - **junit-jupiter-api** for JUnit 5
    - **junit** Junit 4
    
#### Test Engine
- allow to execute a given kind of test inside JUnit Platform
    - Jupiter tests, legacy JUnit4 , other
- Test Engines are created by extending the generic Platform Engine
    - **junit-platform-engine**
    

#### Test Launcher
- provide test discovery capabilities inside JUnit for
external build tools, IDEs
    - consumed by Maven, Gradle, Intellij, et al.
    - uses **junit-platform-launcher**
   
--- 
### Interfaces exposed by JUnit framework
- API vs. SPI
    - API = Application Programming Interface
    - SPI = Service Provider Interface
    - APIs are CALLED, SPIs are EXTENDED

#### Jupiter API
- "Jupiter programming model"
    - see Chapter 3 - JUnit 5 Standard Tests
    - see Chapter 4 - Simplifying Testing with Advanced JUnit Features
    
#### Engine SPI
- used to discover and execute tests
- extended by test engines which provide the programming model to
write tests
  
#### Launcher API
- specifically for test discovery and execution
- consumed by programmatic clients
    - build tools/IDE

---
## Test Engine SPI
- provides capability to create test executors on top of the JVM
- provided Test Engines
    - **junit-vintage-engine** (JUnit3/JUnit4)
    - **junit-jupiter-engine** (JUnit5)
    
### DIY/Customization
- supports 3rd party test libs (Spock, TestNG, etc.)


##### Requirements
- extend **org.junit.platform.engine.TestEngine**
- mandatory method overrides
    - **getId** 
        - (unique identifier for the test engine)
    - **discover**
        - (logic to find/filter tests)
    - **execute**
        - (logic to run discovered tests)
    
##### Example

    public class MyCustomTestEngine implements TestEngine {
        
        public static final String ENGINE_ID = "my-custom-test-engine";
        
        @Override
        public String getId() {
            return ENGINE_ID;
        }

        @Override
        public TestDescriptor discover(
                EngineDiscoveryRequest discoveryRequest, UniqueId uniqueId) {
        
            // Discover test(s) and return a TestDescriptor object
            TestDescriptor testDescriptor = new EngineDescriptor(uniqueId, "My Test");
            return testDescriptor;
        }

        @Override
        public void execute(ExecutionRequest request) {
        
            // Use ExecutionRequest to execute the TestDescriptor
            TestDescriptor rootTestDescriptor = request.getRootTestDescriptor();    
            request.getEngineExecutionListener()
                    .executionStarted(rootTestDescriptor);
        } 
    }


## Test Launcher API
- make a more stable/powerful interface between JUnit platform and programmatic clients
- used by build tools/IDEs for
    - discovery
    - filtering
    - execution
    
### Discovery And Filtering Example

    
    LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder
                .request()
                .selectors(
                    selectPackage("io.github.bonigarcia"),
                    selectClass(Mytest.class)
                .filters(includeClassNamePatterns(".*Test"))
                .build();
    Launcher launcher = LauncherFactory.create();
    TestPlan plan = launcher.discover(request);

### Execution Example

    TestExecutionListener listener = new SummaryGeneratingListener();
    launcher.registerTestExecutionListeners(listener);
    launcher.execute.(request);