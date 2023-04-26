# Test Anti-Patterns

- Test code that contains a lot of duplicated code
    - hard to read/maintain
  

- Instead of writing a new method to verify another feature/requirement, a new assertion is
added to an existing test


- only verifies expected results w/o testing boundaries and exceptions
  - (This is ok to do as long as you are also testing SAD PATH)


- test dependent to some specific local environment
  - requires some existing data populated somewhere before the test runs
  - (improperly set up test fixtures)


- Tests that must be run in a certain order
    - i.e. each test changes the state of the SUT that is expected by the next. 


- too many test doubles
  - Test just returns data from doubles w/o exercising the SUT


- test passes even if there is an unintended exception


- tests that require excessive amounts of setup in order to exercise the SUT


- tests that use unhealthy ways to exercise the SUT
  - i.e. reading private fields via reflection
  

- tests w/ no clear indicator about what is being tested
  - It's ok to use Jira IDs, but use a DESCRIPTION too!
  

- unit tests that last several seconds
  - slow running tests
  

- Heisenbugs/non-deterministic tests
  - very common with concurrent code that has race conditions
  

- tests that need to wait/sleep for a specific amount of time before it can
verify an expected behavior
  

- god objects (i.e. something that does too much)
  - test class that has a ton of likely unrelated tests. 
  

- incomplete test fixture
  - setup w/o teardown. 
  

- test pollutes the fixture
  - i.e. the test somehow destroys/tampers the fixture
  

- tests that use rely on testing framework to report a test failure based on a
  thrown exception rather than using an Assertion
  - Exceptions should NEVER be used to control the flow of code. 
  

- try to avoid environmental variables
  - INJECT configuration/data through other means. 
  

- test fixtures w/ gold plating
  - don't do more than is necessary to perform the test
  

- improper isolation
  - i.e. a unit test that tests the DOCs. 
  

- tests that don't actually test what they are supposed to. 

---
## Code Smells
- Don't violate DRY (Don't Repeat Yourself)
  - duplicated code is hard to maintain
  - this is even worse in tests, because they demand clarity
  
  
- overly complex code
  - too many branches/loops
  - break these down into smaller pieces. 
  

- long method
  - this is generally bad in code, but it's also bad when it is a test, because
  it's likely to be really fucking slow
    

- poor naming conventions
  - overly long is just ugly and hard to read
  - overly small is usually ambiguous. 
  - naming is hard. 
