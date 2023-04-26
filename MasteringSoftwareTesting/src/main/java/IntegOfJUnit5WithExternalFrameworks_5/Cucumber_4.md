# Cucumber

- BDD (Behavioral Driven Development) styled framework for automating
acceptance tests
  
  
## Cucumber In a Nutshell

### Gherkin
- plain text natural DSL used in Cucumber.
    - designed to be used by non-programmers
        - managers, customers, business analysts etc.


- *.feature (Gherkin file extension)
    - lines start w/ a "keyword" followed by text in 'natural language'
    
#### Keywords

| Keyword | Description |
| --- | --- |
| Feature | High level description of the software feature to be tested. "Use case description" |
| Scenario | Concrete example that illustrates the business rule. Scenarios have a common pattern <br /> - describe initial context <br /> - describe an event <br/> - describe expected outcome |
| Given | preconditions and initial state before the start of a test | 
| When | actions taken by a user during a test |
| Then | Outcome from actions taken in the "when" clause | 
| Background | supports declaring steps in a manner that they can be reused in different scenarios to avoid repeating different steps in different scenarios |
| Scenario Outline | Scenarios in which steps are marked w/ variables (uses symbols '<' and '>') |
| Examples | a scenario outline declaration is always followed by 1+ example sections, which is a container table w/ values for the declared variables in the 'Scenario Outline' |


- NOTE: Given, When and, Then are also considered the *steps* of the scenario. 
    - 2 Additional Steps
        - **And**
            - used for "logical and" for different steps
        - **But**
            - used for negative form of and
    

- Cucumber doesn't interpret lines that don't begin w/ keywords
    - (there are effectively comments.)
    

#### Steps Definition
- once feature files are defined, the *steps definition* translates Gherkin into
actions that actually exercise the SUT
  

- Java does this via annotations that match the "steps"
    - **@Given**, **@Then**, **@When**, **@And**, and **@But**
    - The string value of each step can contain regex which are mapped as fields in the
    method
      

---
## JUnit 5 extension for Cucumber
- NOTE: 
    - The examples take a left turn, because bonigarcia's book has a bug in it.
    - As of late 2020, Cucumber had no support for JUnit5, so it still requires the
        - **@RunWith(Cucumber.class)** convention.

### Files
- Gherkin Feature File
    - see *resources.features.calculator.feature*
    - this is explained above.
    

- **CalculatorSteps**
    - this wires the Gherkin "Given When Then" steps to method level annotations with 
    the same name.
      
- **CucumberTest**
    - this is the JUnit test that includes class-level annotation 
    - this is the JUnit 4 @RunWith and @CucumberOptions (configuration)
    - This should be an empty file. 
    

#### Output
![CucumberJupiterTest](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/CucumberJupiterTest.png)

---