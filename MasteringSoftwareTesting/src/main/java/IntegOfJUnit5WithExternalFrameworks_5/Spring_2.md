# Spring
- Rod Johnson 
    - "Expert One-on-one J2EE Design and Development" -2002
- light-weight infrastructure designed to avoid complexity of J2EE
    - POJOs used as building blocks to simplify the creation/construction
    of enterprise applications.
      
---
## Spring in a Nutshell

#### IoC (Inversion of Control)
- (core of Spring is IoC)
- DEFINED as the process of instantiating objects outside the class 
in which these objects are actually used. 
    

- Objects are "beans" or components
    - *singletons* by default.
    - beans are created by the *Spring IoC Container*
    
    
#### DI (Dependency Injection)
- DEFINED as the process of providing dependencies of one object
  instead of constructing them itself
- (methodology by which *Spring IoC Container* creates beans)


    NOTE:
      IoC and DI are often used interchangeably. Not true. 

      IoC is acheived through DI

#### spring-context module
- provides core functionality of spring (IoC)
- provides the ability of creating *application context*
  - the *application context* is Spring's DI container.
  
##### AnnotationConfigApplicationContext
- *application context* that accepts annotated classes to ID the Spring
beans executed in the DI container. 
  

![SpringStereotypes](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/SpringStereotypes.png)
- Stereotype Annotations
  - **@Component** 
    - generic Spring bean
    - Application Layers
      - **@Controller**
        - stereotype for presentation layer used in MVC design pattern/web module
      - **@Repository**
        - persistence layer used in the data access module (Spring Data)
      - **@Service**
        - used in service layer
  - **@Configuration**
    - classes annotated with this allow defining Spring beans by
    annotating METHODS w/ **@Bean**
        - (The object returned by these methods will be Spring beans
          living inside the DI container.)
    

##### ClassPathXmlApplicationContext
- *application context* that accepts bean definitions declared in an XML
file located in the project's classpath


- Most devs use annotation-based configuration rather than XML based

---
### Injection Types for Spring Components
![AutowiredDIOptions](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/AutowiredDIOptions.png)

#### Field Injection
- **@Autowired**
  - annotation placed on a class's field we want to inject
  - removes clutter code such as setter methods/constructor parameters.
  
#### Setter Injection
- injected component is declared as a field in the class
  - setter for field is created and annotated w/ **@Autowired**
  
#### Constructor Injection
- dependency is injected in class constructor which is annotated by
  .... you guessed it...it depends
  - prior to Spring 4.3, you had to use **@Autowired**
  - Spring 4.3+ No annotation is required to carry  out the injection
  

- Benefits of the new version
  - promotion of testability w/o the need for reflection
    - impl'd by mocking library
  - forces devs/archs to think over the design of the class, since
  many injected dependencies suppose many constructor params which can
  lead to the God object antipattern
    

---
### Examples
- Dependency Management


    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>${spring-context.version}</version>
    </dependency>



- **@ComponentScan**
  - annotation provided on an executable java class (i.e. where *main* lives)
      - also where the *applicationcontext* is defined
      - usually  **ApplicationConfigApplicationContext**
  - declares the package in which Spring looks for beans
  definitions in terms of annotations.
  - If specific packages aren't defined, then bean scanning
  occurs from the package of the clas that declares this annotation
    - (This is how the example is set up.)
  

- **@Service** 
  - Spring component
  - This is functionally equivalent to **@Component**, however it provides 
  a remark as to its "service" oriented nature.
    

#### Execution Flow
- 1.) We execute the **MySpringApplication** 
  - main method.
  - this launches an *annotation-based application context*
  - try-with-resources syntax ensures that the *application context* is
  closed at the end.
- 2.) The Spring IoC Container (part of the *application context*) creates
  2 beans
    - **MessageService**
    - **MessageComponent**
- 3.) app logic invokes  **MessageComponent.getMessage**

---
### Spring Modules
| Module | Description | Logo |
| --- | --- | --- |
| Spring Framework | core support for DI, transaction management, web applications (Spring MVC), data access, messaging, et al. | ![SpringFrameworkLogo](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/SpringFrameworkLogo.png) |
| Spring IO Platform | combines the core Spring APIs into a cohesive and versioned foundational platform for modern applications | ![SpringIOPlatformLogo](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/SpringIOPlatformLogo.png) |
| Spring Boot | Simplifies creation of standalone, production grade Spring-based apps w/ minimal config. Uses *convention-over-configuration* approach | ![SpringBootLogo](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/SpringBootLogo.png) |
| Spring Data | Simplifies data access by means of comprehensive APIs to work w/ RDBMS, NOSQL, EMR algos, et al. | ![SpringDataLogo](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/SpringDataLogo.png) | 
| Spring Cloud | Libs/patterns for building/deploying distributed systems and microservices | ![SpringCloudLogo](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/SpringCloudLogo.png) | 
| Spring Security | Customizable authN/authZ capabilities for Spring-based apps | ![SpringSecurityLogo](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/SpringSecurityLogo.png)
| Spring Integration | Lightweight, POJO-based message for Spring based apps to integrate w/ external systems | ![SpringIntegrationLogo](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/SpringIntegrationLogo.png) |
| Spring Batch | Lightweight franework to enable dev of robust batch apps for operations of enterprise systems | ![SpringBatchLogo](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/SpringBatchLogo.png) |


---
### Introduction to Spring Test
- *spring-test* module
  - supports unit/integ testing of Spring components
  - provides ability to create 
      - Spring *application context* for testing purposes
      - mock objects that support testing code in isolation
  
#### Significant spring-test annotations
- **@ContextConfiguration**
  - determines how to load/configure an **ApplicationContext** for integ tests
    - supports annotation-based AND XML based bean definitions


- **@ActiveProfiles**
  - instructs container about which definition profiles should be active during *application context* loading
    - ex. there can be development and test profiles. 


- **@TestPropertySource**
  - configures location of properties files and inline properties to be added
  
- **@WebAppConfiguration**
  - used to instruct the Spring context that the **ApplicationContext** loaded is **WebApplicationContext**
  

#### Special Packages/modules in spring-test
- *org.springframework.mock.web*
  - contains set of Servlet API mock objects for testing web contexts
    - EX: **MockMvc**
      - performs HTTP requests (POST, GET, PUT, DELETE, etc)
      - verifies response (status code, content type, response body, et al.)
  

- *org.springframework.mock.jndi*
  - contains impl of JNDI SPI (Java Naming and Directory Interface)
  - used to set up a simple JNDI evnironment for tests
    - EX: **SimpleNamingContextBuilder** can be make a JNDI data source available in tests
  

- *org.springframework.test.jdbc*
  - contains **JdbcTestUtils**
    - (collection of JDBC utility functions that simplify standard db access)
  

- *org.springframework.test.util*
  - contains **ReflectionTestUtils**
    - collection of utility methods to set a non-public field or invoke private/protected setter methods when
    testing application code.
      

---
### Testing Spring Boot Applications

#### Benefits of Spring Boot
- Spring Boot apps are just **ApplicationContext** that uses the *convention over configuration* principal. 
  - accelerates development
  


- **@SpringBootApplication** annotation id's the main class in SpringBoot
  

- provides a number of out of box NFR features
  - embedded servlet containers
    - Tomcat, Jetty, Undertow
  - security
  - metrics
  - health checks
  - externalized configuration
  

- supports the creation of standalone running apps(and web apps)
  - can be executed running "java -jar"
  

- provides CLI that allows running Groovy scripts for fast prototyping


- works in the same way as any Java standard library
  - add appropriate jar in classpath (via Maven/Gradle)
  

- Starter libraries to simplify/streamline the addition of useful libs to classpath

| Starter | Description |
| --- | --- | 
| spring-boot-starter | core starter (includes auto-config support and logging) |
| spring-boot-starter-batch | Starter for Spring Batch |
| spring-boot-starter-cloud-connectors | Starer for Spring Cloud Connectors (simplifies connections to services like Cloud Foundry, Heroku) |
| spring-boot-starter-data-jpa | Starter for using Spring Data JPA w/ Hibernate |
| spring-boot-starter-integration | Starter for using Spring Integration |
| spring-boot-starter-jdbc | Starter for using JDBC w/ Tomcat JDBC connection pool | 
| spring-boot-test | Starter for testing Spring boot apps w/ test libs like JUnit, Jamcrest, Mockito |
| spring-boot-starter-thymeleaf | Starter for building MVC web apps using Thymeleaf views |
| spring-boot-starter-web | Starter for building web apps (including REST) w/ Spring MVC. (Uses Tomcat as embedded container ) |
| spring-boot-starter-websocket | Starter for building WebSocket apps using Spring Framework's WebSocket support | 

#### Spring Boot Capabilities (For simplification of tests)
- **@SpringBootTest**
  - class level annotation for test classes
  - creates **ApplicationContext**
    - (similar to **@ContextConfiguration**, but specifically for Spring Boot based apps)
    - In the *spring-test* module, the **@ContextConfiguration(classes=...)** annotation is used to 
    specify which bean definition (Spring **@Configuration**) is loaded.
    - This is usually not required for Spring Boot apps, because Spring Boots' test annotations search
    primary configuration automatically if not explicitly defining one. 
      - The search algo works UP from the pkg that contains the test until it finds **@SpringBootApplication**
      annotated class


- **@MockBean**
  - allows defining a Mockito mock for a bean inside the **ApplicationContext**
    - can be a new bean or replace a single existing bean def. 
    - Mocked beans are auto reset after each test method

---
## JUnit 5 Extension for Spring

### SpringExtension
- We don't have to develop our own! It's part of the spring-test module.  

### Testing Classes (For Examples)
- see example.junit5Spring
  - MySpringApplication, MessageComponent, MessageService
  

#### Dependency Requirements For examples (Spring Only) 
- spring-context is required to implement the actual app
- sprint-test and junit-jupiter are required to test it.



    pom.xml (Maven)

      <dependencies>
          <dependency>
              <groupId>org.springframework</groupId>
              <artifactId>spring-context</artifactid>
              <version>${spring.version}</version>
          </dependency>
          <dependency>
              <groupId>org.springframework</groupId>
              <artifactId>spring-test</artifactid>
              <version>${spring.version}</version>
              <scope>test</scope>
          </dependency>
          <dependency>
              <groupId>org.junit.jupiter</groupId>
              <artifactId>junit-jupiter-api</artifactid>
              <version>${junit.jupiter.version}</version>
              <scope>test</scope>
          </dependency>
      </dependencies>

    build.gradle (Gradle)

      dependencies {
          compile("org.springframework:spring-context:${springVersion}")
          testCompile("org.springframework:sprint-test:${springVersion}")
          testCompile("org.junit.jupiter:junit-jupiter-api:${junitJupiterVersion}")
          testRuntime("org.junit.jupiter:junit-jupiter-engine:${junitJupiterVersion}")
      }

#### SpringBoot Dependencies

##### pom.xml (Maven)

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.4.2</version>
    </parent>

    <properties>
        <junit.jupiter.version>5.7.0</junit.jupiter.version>
        <junit.platform.version>1.7.0</junit.platform.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>${junit.jupiter.version}</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>


    <build>
        <plugins>
            <plugin>
                <artifactId>maven-surefire-plugin</artifactid>
                <dependencies>
                    <dependency>
                        <groupId>org.junit.platform</groupId>
                        <artifactId>junit-platform-surefire-provider</artifactId>
                        <version>${junit.platform.version}</artifactId> 
                    </dependency>
                    <dependency>
                        <groupId>org.junit.jupiter</groupId>
                        <artifactId>junit-jupiter-engine</artifactId>
                        <version>${junit.jupiter.version}</artifactId> 
                    </dependency>
                </dependencies>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactid>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
  
    <repositories>
        <repository>
            <id>spring-milestones</id>
            <url>https://repo.spring.io/libs-milestone</url>
        </repository>
    </repositories>

    <pluginRepositories>
        <pluginRepository>
            <id>spring-milestones</id>
            <url>https://repo.spring.io/libs-milestone</url>
        </pluginRepository>
    </pluginRepositories>

##### build.gradle (Gradle Sucks) 

    
    buildscript {
        ext {
            springBootVersion = '2.4.2
            junitPlatformVersion = '1.0.0'
        }

        repositories {
            mavenCentral()
            maven {
                url 'https://repo.spring.io/milestone'
            }
        }

        dependencies {
            classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
            classpath("org.junit.platform:junit-platform-gradle-plugin:${junitPlatformVersion}")
        }
    }

    repositories {
        mavenCentral()
            maven {
                url 'https://repo.spring.io/milestone'
            }
    }

    apply plugin: 'java'
    apply plugin: 'eclipse'
    apply plugin: 'idea'
    apply plugin: 'org.springframework.boot'
    apply plugin: 'io.spring.dependency-management'
    apply plugin: 'org.junit.platform.gradle.plugin'

    jar {
        basename = "my-project-name"
        version = '1.0.0'
    }

    compileTestJava {
        sourceCompatibility = 11
        targetCompatibility = 11
        options.compilerArgs += '-parameters'
    }

    dependencies {
        compile('org.springframework.boot:spring-boot-starter')
        testCompile('org.springframework.boot:spring-boot-starter-test')
        testCompile('org.junit.jupiter:junit-jupiter-api:${junitJupiterVersion}')
        testRuntime('org.junit.jupiter:junit-jupiter-engine:${junitJupiterVersion}')
    }


### Steps To Impl Jupiter Test 
- 1.) annotate test class w/ **@ContextConfiguration** to specify which
**ApplicationContext** needs to be loaded. 


- 2.) Annotate test class w/ **@ExtendWith(SpringExtension.class)** to 
enable *spring-test* into Jupiter platform
  

- 3.) Inject Spring component we want to assess in our test class


- 4.) Impl the @Test!

#### Creating The Tests for Spring and SpringBoot
- see example.junit5Spring.SimpleSpringTest
    - uses **@ContextConfiguration** to define the test, as well as
    explicit specification of the *application context* class. 
      

- see example.junit5Spring.SimpleSpringBootTest
    - uses **@SpringBootTest** to annotate the test. 
        - remember, that this searches the same package as the @Test, 
        and it works it's way up the pkg hierarchy until it finds
        the **@SpringBootApplication** 
    - the logic is identical to the "vanilla" Spring Test. 

##### SimpleSpringTest Execution
- The output is ridiculous and hard to parse. (see example.junit5Spring.simpleSpringTestOutput.txt)
- The test output line of interest is mentioned below.


    17:09:29.521 [main] INFO IntegOfJUnit5WithExternalFrameworks_5.example.junit5Spring.MySpringBootApplication - *** Hello world! ***
 
##### SimpleSpringBootTest Execution
- Result is slightly less verbose, and much easier to parse. 
![SpringBootTestOutputExample](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/SpringBootTestOutputExample.png)
---
### Testing a Web App w/ Spring Boot.
- dependency changes
  - we just need to make sure that the artifact in the pom.xml or
  build.gradle is swapped from *spring-boot-starter* to *spring-boot-starter-web*

#### Classes
- **@SpringBootApplication**
  - **example.junit5SpringWeb.SpringBootWebApp**
  - basic runtime launcher
  

- **@Controller**
  - **example.junit5SpringWeb.WebController** 
  - basic Spring bean which handles incoming requests from
  web browers
      - **@RequestMapping** annotation maps the default 
  resource ("/") to the controller.
      - **@Autowired** injects the **PageService** which
      returns the actual page to be loaded in response to
      the request to "/"
  

- **@Service**
  - **example.junit5SpringWeb.PageService
  - injected by the **WebController**

---  
#### Test 1 - Index Test
- verify direct call to the page "/index.html"


- Test Setup
  - Spring is wired to Jupiter via **@ExtendWith(SpringExtension.class)**
  - Test is declared as **@SpringBootTest**
  

- **MockMvc**
  - used to verify the response to a web request in a number of ways
    - HTTP response code
    - content-type
    - response content body
  - auto configured w/ **@AutoConfigureMockMvc**
    - **MockMvc** is part of *sprint-test*, and is external to Spring Boot.
    - **AutoConfigureMockMvc** is part of Spring Boot. 
      - if you want ot use **MockMvc** outside of Spring Boot, you
      have to configure/construct it using **MockMvcBuilders** builder class,
      passing the *application context* as a param.
        
##### Result

    2021-02-06 21:15:18.584  INFO 43123 --- [           main] I.example.junit5SpringWeb.IndexTest      : Starting IndexTest using Java 11.0.9 on EMANGINI0ML with PID 43123 (started by Edward in /Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting)
    2021-02-06 21:15:18.590  INFO 43123 --- [           main] I.example.junit5SpringWeb.IndexTest      : No active profile set, falling back to default profiles: default
    2021-02-06 21:15:19.411  INFO 43123 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
    2021-02-06 21:15:19.484  INFO 43123 --- [           main] o.s.b.a.w.s.WelcomePageHandlerMapping    : Adding welcome page: class path resource [static/index.html]
    2021-02-06 21:15:19.610  INFO 43123 --- [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
    2021-02-06 21:15:19.610  INFO 43123 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
    2021-02-06 21:15:19.610  INFO 43123 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 0 ms
    2021-02-06 21:15:19.627  INFO 43123 --- [           main] I.example.junit5SpringWeb.IndexTest      : Started IndexTest in 1.327 seconds (JVM running for 2.182)
    2021-02-06 21:15:19.739  INFO 43123 --- [extShutdownHook] o.s.s.concurrent.ThreadPoolTaskExecutor  : Shutting down ExecutorService 'applicationTaskExecutor'
---

#### Test 2 - Redirect Test
- **example.junit5SpringWeb.RedirectTest**
- We use **@MockBean** to override a Spring bean/component by a mock
  - (**PageService** in this case.)
  - we stub the method "getPage()" (see class for notes) in order
  to ensure we get the proper redirect response (HTTP 302)
    
##### Result

    NOTE:

      There is an incompatibility between Spring Boot and Mockito
      That makes this whole kit and caboodle fall over. 

      Spring Boot's test framework uses it's own internal version
      of mockito to handle MockBean. 

      This means that if you have mockito-all specified in your pom.xml
      you are likely to encounter this:

        https://stackoverflow.com/questions/60068969/java-lang-nosuchmethoderror-org-mockito-mockingdetails-getmockcreationsettings

      For the purposes of this exercise... our pom.xml is sitting
      across every project... not a great choice. 

      If you want to run RedirectTest, comment out the 
      Mockito-all dependency in pom.xml

      I know... it's ghetto AF, but I don't feel like re-moduling this. 


- Back to our regularly scheduled test results (which look identical
  to the other results, but w/ the test name "RedirectTest")
  
---
#### Test 3 - Out-of-Container Test
- An out of container test, in spring/spring boot, is when we
test the logic without using a Spring-based **application context**
  

- We're using Mockito again
  - MockitoExtension
  - **WebController** is configured as SUT (**@InjectMocks**)
  - **PageService** is configured as the DOC (**@Mock**)
  

    
