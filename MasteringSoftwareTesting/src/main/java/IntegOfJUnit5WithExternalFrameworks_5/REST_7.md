# REST
- Roy Fielding
    - one of the creators of HTTP
    - co-author of Apache Web Server
    - coined the term REST in 2000
        - REpresentational State Transfer
        - part of doctoral dissertation
            - "Architrectural Styles and the Design of Network-based Software
              Architecture"
              

- REST
    - architecture device for designing distributed systems. 
    - NOT a standard --> a "set of constraints"
    - **RESTful** implementations are considered those that strictly
    follow the principles of REST
    - **RESTlike** implementations are considered those that
    loosely adhere to the principles of REST
---
## REST in a nutshell 
- client/server arch
    - server handles a set of services, listening for client requests
    
    
### Stateless Communication
- server doesn't store any records from the clients
- each client request must contain ALL of the information for the server to process it.

### Named Resources
- building blocks of REST
- 1.) resources define the TYPE of information that is going to be transferred. 
    - IDENTIFIED in a unique way
    - EX: in HTTP, a resource is a full URL (aka API endpoint)
    

- 2.) each resource has a REPRESENTATION 
    - (a machine-readable explanation of the current state of a resource)
    - EX:
        - JSON (JavaScript Object Notation)  **<-- most common these days**
        - YAML (Yet another Markup Language)
        - XML (eXtensible Markup Language)
    

- 3.) define the resource's ACTIONS
    - capabilities (i.e. what can be done with them)
    - CRUD 
        - set of common actions that any resource-oriented system should provide
        as plausible
            - CREATE
            - READ
            - UPDATE
            - DELETE
    

#### Mapping Rest Actions to HTTP methods/vderbs

| Verb | Action | Idempotent |
| --- | --- | --- |
| **GET** | Reads a resource | YES |
| **POST** | Sends a NEW resource to the server |
| **PUT** | Updates a given resource | YES |
| **DELETE** | deletes a resource | YES |
| **PATCH** | partially update a resource |
| **HEAD** | asks if a given resource exists w/o returning any of its representations |
| **OPTIONS** | gets list of available verbs on a given resource |

### Idempotency
- (**GET, DELETE, PUT**)
- an action where the effect of the said action should be the same whether the command is
sent once or several times. 
  

- **POST**
    - not idempotent, because it creates a new resource every time.


### HTTP STATUS CODES

| Code | Message | Description |
| --- | --- | --- |
| 200 | OK | Request went fine and the content requested was returned. Normally used on **GET** requests |
| 201 | Created | Resource was created. Useful on **POST/PUT** requests. |
| 204 | No Content | The action was successful, but there is no content returned. Useful for actions that don't require a response body (**DELETE**) |
| 301 | Moved Permanently | resource was moved to another location, and that location is returned |
| 400 | Bad Request | request issued has problems (ex: missing parameters) | 
| 401 | Unauthorized | Useful for AuthN when requested resources is not accessible to the user owning the request |
| 403 | Forbidden | Resource isn't accessible, but unlike 401, authN doesn't affect the response |
| 404 | Not found | URL provided doesn't id any resource |
| 405 | Method not allowed | HTTP verb used on a resource isn't allowed (ex: trying to **PUT** on a read-only resource) |
| 500 | Internal Server Error | generic error code when an unexpected condition happens on the server side |

![RESTSequenceExample](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/RESTSequenceExample.png)


---
## Using REST test libraries with Jupiter 

### Rest Assured
- allows the "validation of REST services by means of a fluent API inspired in 
  dynamic languages such as SHITTY Ruby or Groovy"
  
#### Dependency Management

##### pom.xml (Maven)

    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>${rest-asssured.version}</version>
        <scope>test</scope>
    </dependency>

##### build.gradle (Gradle)

    
    dependencies {
        testCompile("io.rest-assured:restu-assured:${restAssuredVersion}")
    }

---
### Example 1 - Using Free Rest Services
- see example.junit5REST.PublicRestServicesTest


- Here I'm just using two public services (jsontest.com, reqbin.com (ipinfo))
    - i'm making a call to the service and verifying that
    the response is what I expect it to be
        - we check that the status code is 200
        - we also check that the data is correct. 
    
---
### Example 2 - REST testing w/ Spring MVC and Spring Boot
- **@RestController**
    - class level annotation for Spring


- **@RequestMapping**
    - method-level annotations used to listen to different URL/endpoints
    impl'd in the REST API
    - ELEMENTS
        - *value*
            - path mapping URL
        - *method*
            - finds HTTP request methods to map to
        - *params*
            - finds parameters of the mapped request
            - narrows the primary mapping
        - *headers*
            - finds the headers of the mapped request
        - *consumes*
            - finds consumable meade types of the mapped request
        - *produces*
            - finds producible media types of the mapped request
    

#### Classes
- Book
    - Basic POJO

    
- SpringBootRestApplication
    - this is the Spring launcher/runner class (@SpringBootApplication)
    

- SpringRestController
    - this is the **@WebController** that provides the API
    - these are the API endpoints (**@RequestMapping**s) that wire
    The java code to the Http Verbs. 
        - GET /books (reads all books)
        - GET /book/{index} (reads a book by its id)
        - POST /book (adds a book)
    

- LibraryService
    - this is the **@Service** 
        - this is the implementation of the API methods (in Java Code)
    

- SpringBootRestTest
    - This is the test code.
    
##### TestRestTemplate
- this is a test component provided by *spring-test* that we 
inject into our test. 
    - wrapper of the Spring **RestTemplate** object
        - (impls REST clients in a seamless way)
    - in the test, the template is used to make requests to our
    service (LibraryService) and the responses are used to
    verify the outcome.
      

##### TestRestTemplate vs. MockMvc
- we could use MockMvc instead, BUT
    - **MockMvc** tests the client side
        - response code, body, content type etc. 
        - responses are serialized as JSON
    - **TestRestTemplate** tests the service from the service side.
        - the responses are Java objects

---
### Example 3 - WireMock
- WireMock is a library used to mock REST services
    - it is essentially a mocked HTTP server. 
        - captures incoming requests, providing "stubbed"
        responses
    - useful for testing a SUT that consumes REST service(s), but
    the service isn't available during testing.
      

- Retrofit2
    - This is the client we use for the service. 
    - "highly configurable HTTP client for Java"
    

#### Classes
- RemoteFileApi
    - this is the Api that "consumes" the service
    - it exposes 3 endpoints to read a remote file
        - openFile, readStream, closeStream
    

- RemoteFileService
    - connects to remote service given an URL passed to the constructor
    

- RemoteFileTest
    - exercises the service
    - creates a mock server (new WireMockServer)
    - stubs the REST calls using the static stubFor() methods
        - (this happens during test setup. )