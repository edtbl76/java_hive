# Mockito
- mock unit testing framework for Java
- probably still the most popular

### Alternatives
- EasyMock (http://easymock.org)
- JMock (http://jmock.org)
- PowerMock (http://powermock.github.io)
- JMockit (http://jmockit.org)

## Mockito in a Nutshell

### SUT vs. DOCs
- We want to test SUT (System Under Test)
    - without having to test DOCs (Depended On Components)
    

- Test Doubles are lightweight structures that provide the
desired isolation mentioned above
    - mock objects are a type of test double
    

### Mockito Steps

#### 1. Mocking Objects
- Mockito API creates mocks of the SUTs DOCs
    - provides isolation
    - ensures unit test is focused on SUT
    
#### 2. Setting Expectations
- mocks can be programmed w/ custom expectations according to the needs of the
unit tests
    - "stubbing methods"
        - Mockito methods owned by mocks


- mock objects "mimic" the behavior of real objects by default
    - can provide "dummy values"
    - "stubbing methods" allow us to return a specific value when a
    method is called
      
#### 3. Verification
- evaluation of the SUT and DOCs
- verify invocation w/ a mock
- capture/verify argument(s) passed to a stubbed method


- can also be complemented w/ 
    - JUnit built-in assertions
    - 3rd party assertions (Hamcrest, AssertJ, Truth, etc.)
    

### Mockito API Table

| Mockito API | Desc. | Phase |
| --- | --- | --- | 
| @Mock | ids a mock object to be created by Mockito (usually used for DOCs) | 1 Mocking Objects |
| @InjectMocks | ids the object in which the mocks are going to be injected. Used to the unit we want to test (SUT) | 1 Mocking Objects |
| @Spy | Partial mock impl, that will use the real impl in non-*stubbed methods* | 1 Mocking Objects |
| Mockito.when(x).thenReturn(y), Mockito.doReturn(y).when(x) | allows dev to specify value (y) that should be returned by stubbed method(x) of given mock object | 2. *stubbing methods* |
| Mockito.when(x).thenThrow(e), Mockito.doThrow(e).when(x) | allows dev to specify exception (e) that should be raised when calling a stubbed method(x) of a given mock object | 2. *stubbing methods* |
| Mockito.when(x).thenAnswer(a), Mockito.doAnswer(a).when(x) | Instead of returning a "hard-coded value", Dynamic user-defined logic(Answer a) is exec'd when given method (x) of mock is invoked | 2. *stubbing methods* |
| Mockito.when(x).thenCallRealMethod(), Mockito.doCallRealMethod.when(x) | allows the use of the real impl instead of the mocked one | 2. *stubbed methods* | 
| Mockito.doNothing().when(x) | When using a spy, the default behavior is calling the real methods of the object. In order to avoid the execution of a *void* method, this method is used | 2. *stubbing methods* |
| BDDMockito.given(x).willReturn(y), BDDMockito.given(x).willThrow(e), BDDMockito.given(x).willAnswer(a), BDDMockito.given(x).willCallRealMethod() | BDD versions of Mockito's previous methods. The behavior of the stubbed methods (X) is equiv. to Mockito.when(x) | 2. *stubbing methods* |
| Mockito.verify() | verifies invocation of mock objects (see enhancements below) | 3. verification |
| Mockito.verifyZeroInteractions(), Mockito.verifyNoMoreInteractions() | verify that a stubbed method has no interactions. They both use same intermal impl | 3. verification 
| @Captor | allows us to define an ArgumentCaptor object for the purpose of verify arguments passed to a stubbed method | 3. verification | 
| Mockito.inOrder | facilitates verifying whether interactions with a mock wer performed in a given order | 3. verification |


#### Verification Enhancement Methods
- *times(n)*
  - stubbed method is invoked exactly "n" times
- *never()*
  - stubbed method is never called
- *atLeastOnce()*
  - stubbed method is invoked at least once
- *atLeast(n)*
  - stubbed method is invoked at least "n" times
- *atMost(n)*
  - stubbed method is invoked at most "n" times
- *only*
  - mock fails if any other method is called on the mock object
- *timeout(m)*
  - method is called in "m" milliseconds at the most
  

### Mock Creation Example

    Example 1: Using Annotation

      @Mock
      MyDoc docMock;

    
    Example 2: Mockito.mock()

      MyDoc docMock = Mockito.mock(MyDoc.class);

--- 
## JUnit5 Extension for Mockito

### MockitoExtension
- see example.junit5Mockito.MockitoExtension
  - see http://junit-pioneer.org
  

- leverages JUnit extension model
  - impls extension callbacks:
    - **TestInstancePostProcessor**
    - **ParameterResolver**
  

#### TestInstancePostProcessor
- after a test case is instantiated, **postProcessTestInstance** is 
invoked.
    - initializes mocks 
      - **MockitoAnnotations.initMocks(testInstance)**
    - (This is the same effect of using the JUnit 4 Mockito Runner)
      - **@RunWith(MockitoJUnitRunner.class)**
  

#### ParameterResolver
- allows DI at method level in the tests, which registers the extension
  - (**@ExtendWith(MockitoExtension.class)**)
  
---
### Test Project 
see example.junit5Mockito.*

#### Classes
- **LoginController**
  - recvs request from user
  - returns response as result
  - request is dispatched to **LoginService**
    - **LoginController** uses **LoginService** by *composition*
- **LoginService**
  - impls functionality of use case. 
    - checks whether/not user is authN'd in system
    - reads persistence layer impl'd in **LoginRepository**
      - **LoginService** uses **LoginRepository** via *composition*
- **LoginRepository**
  - allows access to persistence layer
    - usually impl'd by db
    - my impl uses **HashMap** instead of a db. 
  - class is also called a DAO (*Data Access Object*)
 
  

- **UserForm**
  - POJO w/ 2 properties
    - username
    - password
  - (my code uses **Lombok** to get rid of the boilerplate)
  

##### Component Diagram
![ComponentDiagramMockito](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/ComponentDiagramMockito.png)
- This is a compositional hierarchy
##### Sequence Diagram
![SequenceDiagramMockito](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/SequenceDiagramMockito.png)

---
#### LoginControllerLoginTest
- isolate **LoginController** (SUT) from the rest of the system
- mock the dependencies
  - **LoginService** is our DOC
- see notes in the class for impl explanation

##### Execution Result
![LoginControllerLoginTestResult](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/LoginControllerLoginTestResult.png)

---
#### LoginControllerErrorTest
- tests "negative scenarios" (errors!)
- first test (**testLoginError**) 
  - ensures that the system responds w/ ERROR when a null form is submitted for authN. 
- the second test (**testLoginException**)
  - ensures that the mock raises an exception when any form is used
  first. 
  - then we assess that it responds with an ERROR.

##### Execution Result
![LoginControllerErrorTestResult](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/LoginControllerErrorTestResult.png)

---
#### LoginControllerBBDTest
- BDD version of the previous 4 test cases

#### Execution Result
![LoginControllerBDDTestResult](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/LoginControllerBDDTestResult.png)

---
#### LoginServiceTest
- similar scenario as the LoginController tests, but
now the LoginService represents the SUT instead of the DOC
  - LoginRepository represents the DOC
  - we test against true/false AuthN
  - we also test that an exception is thrown when a login attempt occurs for
  a logged in user. 
    
##### Exec Result
![LoginServiceTestResult](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/LoginServiceTestResult.png)

---
#### LoginServiceArgumentCaptorTest
- This provides a basic example for capturing the argument of
a mocke dobject.
    - define a class property of **ArgumentCaptor<Target>** where "Target"
  is the type of object we want to capture. 
      - class property is annotated w/ **Captor**


- Test Logic
  - Exercise the SUT
  - capture the argument provided to the SUT
  - assess the value to validate that the test passes. 
  
##### Exec Result
![LoginServiceACTestResult](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/LoginServiceACTestResult.png)

---
#### LoginServiceSpyTest
- a *spy* uses the real impl in non-stubbed methods. 
  - If we don't stub methods in a spy object, we get the REAL OBJECT
  during our test. 
  
  
- In this example our SUT is still **LoginService**, but we are spying
the **LoginRepository**

##### ExecResult
![LoginServiceSpyTest](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/LoginServiceSpyTest.png)