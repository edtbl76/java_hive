# Test Design

### Design Goals
- in order to properly design a test we need to define what must
be impl'd
  
### Test Structure
![TestCaseAndAssertions](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/TestCaseAndAssertions.png)
- Test Fixture (Setup & Teardown)
    - required state in the SUT to carry out a test
    - Test fixture is established at the beginning of a test during
    "setup" and then "released" at the end of the test during
    "teardown"
      
- SUT (System under Test)
  - system under test
  - unit tests should be performed in isolation
    - SUT is IN-SCOPE
  

- DOCs (Depend on Components)
  - unit tests should be performed in isolation
    - DOC is OUT-OF-SCOPE
  - mocked/stubbed/spied (Test Doubles)
  

- Assertions
  - IMPORTANT
  - w/o assertions of what should be true, there is no way to 
  compare the result of a test to its expectations

- Test Data
  - test oracles (source of test data)
    - usually extracted from requirements
    - common sources of test oracles
      - "inverse relationship"
        - different program that produces the expected output
      - heuristic/statistical oracle that provides approximate results
      - values based on experience of human experts.
  - derived based on testing technique
    - BlackBox 
      - (exercise a requirement based on using some input and expecting
        some output)
      - Equivalence Partitioning
      - Boundary Analysis
    - WhiteBox
      - (code structure is basis for test)
      - test coverage is the key to select test input that
      maximizes coverage rates. 
  

---
## Equivalence Partitioning
- Blackbox Technique
  - i.e. one that relies on the system requirements
  
- The goal is to reduce the number of tests that should be 
executed against a SUT
  

    Glenford Myers (1978)

      A technique that partitions the input domain of a program
      into a finute number of classes[sets], it then identifies
      a minimal set of well-selected test cases to represent these
      classes.

### Concept
- divide all possible input test data in a set of values for which 
we assume to be processed in the same way by the SUT


- we call "equivalence classes" to each set of values
  - testing ONE representative value within each equivalence class (or set
    of values), is considered sufficient because we assume that all
    the values are processed in the same way by the SUT
    

### Types
- 2 types of equivalance classes
  - VALID
  - INVALID
  

- The concept of Equivalence Partitioning ensures that only ONE test
case of each partition is needed to evaluate the behavior of the
program for the related partition
    - we need to exercise both VALID and INVALID cases
  

### Process/Structure
- 1.) determine domain of ALL possible inputs for a SUT
  - this is HUGE, so be cognizant of the input domain
  - we rely on the specification (features and requirements)
  - the SUT should process these values CORRECTLY
    - (VALID Equivalence Class)
  

- 2.) Establish Equivalence Classes for sets of elements that
  are processed in a similar enough manner that the validation of
  one element is the equivalent to another of the same set. 
  

- 3.) Values outside of the accepted cases should be considered 
  an "invalid" equivalence class
    - (a "sibling" to the valid equivalence class for
      the same domain set)
      
      
- 4.) For each equivalence class
  - a representative value is selected
  - (based on heuristic process or tester experience)
  

- 5.) For every test input
  - proper test output is also selected
  - w/ these values, we are able to complete the full test case
    - test exercise + assertions.

---
## Boundary Analysis
- a method which complements Equivalence Partitioning by
evaluating the boundaries of range-based test input. 
  - defined by NIST 1981 (National Institute of
    Standards and Technology)    
    

    A selection technique in which test data are chosen to 
    lie along 'boundaries' of the input domain [or output range]
    classes, data structures, and procedure parameters. 


### How to do it
- evaluate the SUT within the boundaries of each equivalence class
- This results in 2 test cases
  - one each for the upper and lower boundary of the equivalence class

---

## Test Coverage
- the percentage of code in SUT that is exercised for any of their tests
- useful for finding untested parts of the SUT
  - whitebox approach, because it requires an understanding of the code structure
  

- GOOD test coverage, as a general rule, is considered 80% and up

### CodeCoverage Libraries
- Cobertura
  - open source reporting tool
    - can be executed via Ant, Maven or CLI
  

- EclEmma
  - eclipse specific. No. 
  

- JaCoCo
  - open source, derived from EclEmma
  

- CodeCov
  - cloud-based
  - user-friendly web dashboard
  - free for open source projects


---
