# Migration from JUnit4 to JUnit 5


| Feature | JUnit4 | JUnit 5 |
| --- | --- | --- |
| Annotations package | org.junit | org.junit.jupiter.api |
| Declaring a test | @Test | @Test | 
| Setup for all tests | @BeforeClass | @BeforeAll |
| Setup per test | @Before | @BeforeEach |
| Teardown for all tests | @AfterClass | @AfterAll |
| Teardown per test | @After | @AfterEach |
| Tagging and Filtering | @Category | @Tag | 
| Disable a test method or class | @Ignore | @Disabled |
| Nested Tests | N/A | @Nested | 
| Repeated Tests | Required a custom rule | @Repeated |
| Dynamic Tests | N/A | @TestFactory |
| Test templates | N/A | @TestTemplate |
| Runners | @RunWith | (Runners are superseded by the extension model) @ExtendWith |
| Rules | @Rule, @ClassRule | (Rules are superseded by the extension model) @ExtendWith |


### Rule Support in Jupiter
- There is no native support for JUnit 4 rules in the JUnit 5 platform
- however, since the feature was so widely adopted (for such a looooong
  time), there is a module for non-native support
    - *junit-jupiter-migrationsupport*
    
    
    pom.xml (Maven) 

        <dependency> 
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-migrationsupport</artifactId>
            <version>${junit.jupiter.version}</version>
            <scope>test</scope>
        </dependency>

    build.gradle (Gradle)

        dependencies {
            testCompile("org.junit.jupiter:junit-jupiter-migrationsupport:${junitJupiterVersion}")

        }

#### Limitations to Rule Support
- limited to the rules that are "semantically compatible" w/ Jupiter extension model
  - *junit.rules.ExternalResource* (includes *junit.rules.TemporaryFolder*)
  - *junit.rules.Verifier* (includes *junit.rules.ErrorCollector*)
  - *junit.rules.ExpectedException*
  

#### Enabling JUnit 4 Rules in Jupiter Tests.
- test class requires class level annotation
  - **@EnableRuleMigrationSupport**
    - (*org.junit.jupiter.migrationsupport.rules*)
  

#### Example 1: Temporary Folder

    @EnableRuleMigrationSupport
    class TemporaryFolderRuleTest {
        
        @Rule
        TemporaryFolder tempFolder = new TemporaryFolder();

        @BeforeEach
        void setup() throws IOException {
            tempFolder.create();
        }

        @Test
        void test() {
            System.out.println("Temporary Folder: " + tempFolder.getRoot());
        }

        @AfterEach
        void teardown() {
            tempFolder.delete();
        }
    }
![TemporyFolderRuleResult](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/TemporyFolderRuleResult.png)

#### Example 2: ErrorCollector

    @EnableRuleMigrationSupport
    class ErrorCollectorRuleTest {

        @Rule
        ErrorCollector collector = new ErrorCollector();

        @Test
        void test() {
            collector.checkThat("a", equalTo("b"));
            collector.checkThat(1, equalTo(2));
            collector.checkThat("c", equalTo("c"));
        }
    }
![ErrorCollectorRuleResult](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/ErrorCollectorRuleResult.png)
  
#### Example 3: Expected Exception

    
    @EnableRuleMigrationSupport
    class ExpectedExceptionRuleTest {

        @Rule
        ExpectedException thrown = ExpectedException.none();      
  
        @Test
        void throwsNothing() { }

        @Test
        void throwsNullPointerException() {
            throw.expect(NullPointerException.class);
            throw new NullPointerException;
        }
    } 
![ExpectedExceptionRuleResult](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/ErrorCollectorRuleResult.png)