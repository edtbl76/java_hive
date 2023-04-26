# Beyond JUnit 5.0
- Future Features


## Scenario Tests
- allows the ordering of different test methods within a class
    - @ScenarioTest
        - class-level annotation that denotes that a test class 
        contains steps that make up a single "scenario" test
    - @Step
        - method-level annotation that denotes a test method as
        a single step within a "scenario" test
          

## Parallel Test Execution
- out-of-box concurrent test execution 


## Early Termination For Dynamic Tests
- introduce a timeout that stops execution before
it terminates itself
    - this is to avoid uncontrolled non-deterministic executions


## Test Reporting
- capturing stdout/err to be included in test reports
- provide a reliable way to get classname of executed test methods
- specify order of tests in test reports