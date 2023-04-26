# Assertions

### Assertion/Predicate
- boolean statement typically used to reason about software
correctness.

#### Three Components of an Assertion

##### 1. Expected Value
- this is derived from *test oracles*
    - (a test oracle is a reliable source of expected outputs)
        - Ex: System Specification
    
##### 2. Real Outcome
- this is derived from the exercise stage made by the test against the
SUT
  
##### 3. Test Verdict 
- the expected value and real outcome are compared using a logic
comparator in one of many ways:
    - object identity (equals/not equals)
    - magnitude (comparison operators)
    - etc.
- The result of the comparison is the test verdict
    - whether or not the test has succeeded or failed. 
    
![AssertionSchematic](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/AssertionSchematic.png)


---
## Jupiter Assertions
- static methods from **Assertions** class in **org.junit.jupiter** 
package
  
#### Complete List 
![JupiterAssertions](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/JupiterAssertionsCompleteList.png)

#### Jupiter Assertion Types

| Assertion | Description |
| --- | --- |
| fail | fails a test w/ a given message and/or exception |
| assertTrue | Asserts that given condition is true | 
| assertFalse | Asserts that given condition is false |
| assertNull | Asserts that supplied object is null |
| assertNotNull | Asserts that a supplied object is not null |
| assertEquals | Asserts that two supplied objects are equal |
| assertArrayEquals | Asserts that 2 supplied arrays are equal |
| assertIterableEquals | Asserts that 2 iterable objects are DEEPly equal |
| assertLinesMatch | Asserts that two lists of Strings are equal |
| assertNotEquals | Asserts that two supplied objects aren't equal |
| assertSame | Assert that two objects are the same (==) |
| assertNotSame | Assert that two objects aren't the same (!=) |
- for each assertion, an optional (**String**) failure message can 
be provided
    - ALWAYS the last parameter in the assertion method
    - in JUnit4, failure messages were the FIRST parameter in the assertion
  methods. 
      

#### Example

    class StandardAssertionsTest {
    
        @Test
        void standardAssertions() {
            assertEquals(2, 2);
            assertTrue(true, 
                "The optional assertion message is the last parameter in JUnit 5");
            assertFalse(false, () -> "Really " + "expensive " + "message" + ".");
        }
    }
- In this example I've created a very expensive message by concatenating a bunch of immutable **Strings**
    - (Technically it uses **StringBuilder** now anyway)
- This is why the failure message was moved to the final argument


      Assertion messages are LAZILY evaluated so that they are only evaluated if the assertion 
      succeeds. 
        - this prevents creating complex messages unnecessarily

---
### Group of Assertions

#### assertAll
- assertion that allows to group different assertions at the same time. 
  - in grouped assertions
      - all assertions are ALWAYS executed
      - any failures are reported together
  

- accepts 
    - varargs of lambda expressions (**Executable...**)
    - stream of lambda expressions (**Stream<Executable>**)
    - (optional) first argument can be a **String** message to label the assertion group
  

    EXAMPLE:

        class GroupedAssertionsTest {
        
            @Test
            void groupedAssertions() {
                
                Address address = new Address("John", "Smith");

                /*
                    In a grouped assertion, all assertions are executed.
                    Any failures are reported together
                */
                assertAll("address",
                    () -> assertEquals("John", address.getFirstName()),
                    () -> assertEquals("User", address.getLastName())
                ); 
            }
        }

##### Result
![assertAllExample](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/GroupedAssertionsExample.png)
  
---
### Asserting Exceptions

#### assertThrows
- allows a test to verify that a given exception has been raised in 
a piece of code.


- accepts 2 arguments
  - expected exception class
  - lambda expression (executable) in which the exception should be
  thrown
    

#### Example


    class ExceptionTest {
        
        @Test
        void exceptionTesting() {
            Throwable exception = 
                assertThrows(
                    IllegalArgumentException.class, 
                    () -> { throw new IllegalArgumentException("Clever Error Message");}
                );
            assertEquals("Clever Error Message", exception.getMessage());
        }
    }

##### Result
![ExceptionTestResult](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/assertThrowsExample.png)

---
### Asserting Timeouts

#### assertTimeout
- used to verify timeout of a given operation
  - uses **java.time.Duration**
  
- one of the benefits of assertTimeout is that it will allow an operation to finish, even
if the timeout is exceeded.

##### Example 1 
- simplest use case is to execute an action and ensure that the action happens
in the asserted time. 


    class TimeoutExceededTest {
        
        @Test
        void timeoutNotExceeded() {
            assertTimeout(ofMinutes(2), 
              () - > { // task < 2 minutes });
        }

        @Test
        void timeoutExceeded() {
            assertTimeout(ofMillis(10), 
              () - > { Thread.sleep(100); });
        }
    }
![assertTimeoutExample Result](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/assertTimeoutExample1Result.png)
- second assertion fails, because the sleep is longer than the
timeout assertion
  
##### Example 2 
- compound option is to test both 
  - the time it takes for an operation to occur by timeboxing it w/ assertTimeout
  - and then getting the result to test the correctness of the operation


- this is possible because **assertTimeout** doesn't quite when the timeout is exceeded.


    class TimeoutWithResultOrMethodTest {
      
        @Test
        void timeoutNotExceededWithResult() {
            String result = assertTimeout(
                ofMinutes(1), 
                () -> {
                    return "hi there";
                }
            );
      
            assertEquals("hi there", result);
        }


        @Test
        void timeoutNotExceededWithMethod() {
            String result = assertTimeout(
                ofMinutes(1), TimeoutWithResultOrMethodTest::greeting);
      
            assertEquals("hello world", result);
        }

        private static String greeting() {
            return "hello world";
        }
    }
  

#### assertTimeoutPreemptively
- this offers the same functionality as **assertTimeout**, however it whatever operation 
is being performed once that timeout is exceeded.
  
  
    class TimeoutWithPreemptiveTerminationTest {
        
        @Test
        void timeoutExceededWithPreemptiveTermination() {
            assertTimeoutPreemptively(ofMillis(10), 
              () - > { Thread.sleep(100); });
        }
    }

![assertPreemptively Example](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/assertPreemptivelyExample.png)



---
## 3rd Party Assertion Libraries

### Hamcrest
- assertion framework to write matcher objects allowing 
rules ot be defined declaratively
  

#### Dependency Config

    MAVEN pom.xml

      <dependency>
          <groupId>org.hamcrest</groupId>
          <artifactId>hamcrest-core</artifactId>
          <version>${hamcrest.version}</version>
          <scope>test</scope>
      </dependency>


    GRADLE build.gradle

      testCompile("org.hamcrest:hamcrest-core:${hamcrest}")

      
  
#### assertThat
- allows the creation of readable and highly configuration
assertions
  
- 2 arguments
  - actual object
  - **Matcher** object
    - impls interface **org.hamcrest.Matcher**
  

- matcher utilities
  - is, either, or, not hasItem
  

- matcher methods use builder pattern
  - allows combination of multiple methods to create a chain
  of **Matchers**
  

#### Example

    class HamcrestTest {

        @Test
        assertWithHamcrestMatcher() {
            assertThat(2 + 1, equalTo(3));
            assertThat("Foo", notNullValue());
            assertThat("Hello World", containsString("World"));
        }
    }

![Hamcrest Test Example](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/HamcrestTestExample.png)


### AssertJ
- "fluent assertions for Java"

### Truth
- Google's assertion library for Java designed to make 
test assertions and failure messages more readable. 