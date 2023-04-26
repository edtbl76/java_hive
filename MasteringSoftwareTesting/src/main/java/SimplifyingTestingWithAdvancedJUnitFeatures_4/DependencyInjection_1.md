# Dependency Injection

### Constructor Parameters
- prior to JUnit 5 test constructors and test methods couldn't hvae
parameters.
  
### ParameterResolver
- Jupiter extension point that provides DI
- defines an API for test extensions want to dynamically resolve
parameters at runtime.
  

- When a test constructor/method is annotated and accepts a parameter,
that parameter is resolved at runtime by a resolver
    - resolver is a subclass of **ParamterResolver**
    - valid annotations
        - @Test, @Before/AfterAll, @Before/AfterEach, @TestFactory


---
## TestInfoParameterResolver
- this is the JUnit 5 resolver used for test classes w/ a method
parameter of type **TestInfo**
  

- supplies an instance of **TestInfo** corresponding to the current test
as the value for the declared parameter
  
### TestInfo (org.junit.jupiter.api)
- used to retrieve info about the current test
    - display name
    - test class
    - test method
    - associated tags
    
- drop-in replacement for JUnit4 **TestName** rule.

#### API
![TestInfoAPI](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/TestInfoAPI.png)
- **String getDisplayName()**
    - returns display name of test/container


- **Set\<String> getTags()**
    - gets the set of all tags for current test/container
  
  
- **Optional\<Class<?>> getTestClass()**
    - gets class associated w/ current test/container (if available)
  
  
- **Optional\<Method> getTestMethod()**
    - gets method associated w/ current test (if available)
  
### Example 
- see (test/java/Ch4/TestInfoTest)
![TestInfoResult](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/TestInfoResult.png)

---
## RepetitionInfoParameterResolver
- this is the JUnit 5 resolver used for test classes w/ a method
  parameter of type **RepetitionInfo**


- supplies an instance of **RepetitionInfo** corresponding to the current test
  as the value for the declared parameter

### RepetitionInfo (org.junit.jupiter.api)
- used to retrieve info about corresponding **@RepeatedTest**
  - current repetition 
  - total repetitions
  
### API
![RepetitionInfoAPI](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/RepetitionInfoAPI.png)
- **int getCurrentRepetition()**
  - gets current repetition of corresponding **@RepeatedTest**
  
- **int getTotalRepetitions()**
  - gets total repetitions of corresponding **@RepeatedTest**

### Example
- see (test/java/Ch4/RepetitionInfoTest)
![RepetitionInfoResult](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/RepetitionInfoResult.png)

---
## TestReporterParameterResolver
- this is the JUnit 5 resolver used for test classes w/ a method
  parameter of type **TestReporter**


- supplies an instance of **TestReporter** corresponding to the current test
  as the value for the declared parameter

### TestReporter (org.junit.jupiter.api)
- used to publish additional data about test execution
  - information is consumed via **reportingEntryPublished**
  - it is then requested by IDEs or included in test reports.
  

- each instance sotres information as a map (KV collection)


### API
![TestReporterAPI](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/TestReporterAPI.png)


### Example
- see (test/java/Ch3/TestReporterTest)
---
