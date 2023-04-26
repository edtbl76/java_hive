# Software Testing
- consists of the *dynamic* evaluation of the behavior of a
software program on a *finite* set of test cases
    - suitably *selected* from the usually infinite
    executions domain
    - against the *expected* behavior
    

- **Dynamic**
    - **SUT** (System Under Test) executed w/ specific input values
    to find failures in its behavior
    - **SUT** ensures that design, code and environment are correct
        - (libraries, OS, network etc.)
        
        
- **Finite**
    - exhaustive testing is impractical(or impossible)
        - large number of allowable inputs per operation
        - larger number of invalid/unexpected inputs
        - possible sequences of operations are infinite
    - test selection should represent a combination of tasks
    and activities that provide the highest confidence in the 
    software based on the available time and budget. 
    
    
- **Selected** 
    - the process by which we select the tests that are most
    likely to expose failures in the system to achieve the
    highest possible confidence in the quality of the software
    given the available resources (time, money, etc.)
    

- **Expected**
    - after each text execution, the results of the execution 
    should be deterministic
        - did it fail or pass ? 
        
        
### Categories of Software Testing
![SoftwareTestingTaxonomy](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/TaxonomySoftwareTesting.png)

## Testing Levels
![TestingLevels](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/TestingLevelsVV.png)

### Unit Testing
- individual program units are tested to ensure that impl is correct
- focuses on functionality objects/methods

#### 4 Phases
![Unit Test Structure](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/UnitTestStructure.png)
##### Setup
- initialize *test fixture*
    - the initial state for the SUT to exhibit the expected behavior
    - ("Given")

##### Exercise
- test case interacts with SUT, resulting in some output
- SUT may query another component called the **DOC** (Depends On Component)
- ("When")

##### Verify
- determines whether or not the expected outcome has occurred 
    - using *assertions/predicates*
- ("Then")

##### Teardown
- restores SUT to initial state

#### Test Doubles 
- Unit Tests are typically performed in isolation so that there is no
direct interaction w/ a DOC. 
- In order to make this happen, we use *test doubles*

##### Dummy
- statisfies an actual object API, but is never used
- typically passed as parameters to satisfy *method signature*

##### Fake
- replaces a complex "real" object w/ a simpler impl
    - EX: using SqlLite instead of MySQL


##### Stub
- replaces a real object w/ "hard-coded" values as 
responses

##### Mock
- replaces real object w/ programmed expectations as responses

##### Spy
- partial mocked object
    - some responses are real object's impl
    - some responses are programmed w/ expectations


---
### Integration Testing
- units are combined to create composite components
- focused on testing components and interfaces

#### Strategies

##### Top-Down Integration
- main unit/module is the root of the procedural test tree
    - lower-level modules are substituted by a *test-double*
    - doubles are gradually replaced with actual code as testers are
    convinced main module's logic is correct
    - rinse and repeat for each lower level


- PRO:
    - easier to find defects in interfaces/hand-offs


##### Bottom-up Integration
- start from the most "elementary" units
- assemble elementary units into subsystems (higher levels)
    - test connectivity points between the assemblies


- PRO:
    - no test doubles are needed, because we are testing low-level units, and
building on top of this.
    - can sit on top of unit test framework
    

##### Ad Hoc Integration
- testing of components in their natural completion order


- PRO: 
    - typically faster (cheapest to fix)
- CONS:
    - test doubles required
    - disorganized, hard to plan
    
##### Backbone integration
- a skeleton of components is built and others are gradually
integrated onto/into the skeleton


- CONS:
    - complicated
    - labor intensive
    

##### Big Bang Integration (NO)
- This is when testers wait until all or most units are developed and
integrated
    - all failures are found at the same time. 
    - time consuming/difficult
    - depending on when the components were created, many of them 
    may be expensive to fix due to stagnance
    

---

### System Testing
- all components are integrated, and the system is tested in its
entirety
    - verification that components are compatible
    - verifies that components interact properly
    - verifies that the right data is transferred at the
    right times. 
    

- *end-to-end testing*
    - special type of system testing where the "final user" is impersonated
    via automation techniques
    

    
---

### Acceptance Testing
- consumers decide if the system is RELEASABLE
- (i.e. does it meet the customer driven specification/acceptance criteria)


---
## Testing Methods/Strategies
- defines the way for designing test cases

- A good overall test strategy should use several underlying
strategies to compliment each other for a comprehensive approach to
increasing confidence of software 

### Black-Box Testing (Based on Responsibility)
- (a.k.a FUNCTIONAL/BEHAVIORAL testing)
- based on requirements that have no knowledge of internal program structure/data
- relies on specification of system/component being tested

#### Systematic Testing
- SUT is shown to exhaustively conform to a specification 
    - generates test cases limited by each domain subpoint, which
    is represented as a "singleton sub-domain"


- test types performed
    - *equivalence partitioning*
    - *boundary value analysis*
    - logic techniqyes
        - *cause-effect graphing*
        - *decision table*
        - *pairwise testing*
        
#### Random Testing
- opposite of Systematic Testing
- sampling of random inputs are selected from the entire input
domain


- *Fuzz Testing*
    - black-box random testing
    - randomly mutates well-formed inputs and tests the resulting
    data. 
    - delivers randomly sequenced and/or structually unsafe/incorrect
    data to a system to determine if failures occur. 
    

#### GUI Testing
- ensuring that the GUI conforms to the specification


- event-driven
    - provides a front-end to underlying app code through events, 
    messages, method calls
    

- unit level GUI testing is usually at the "actor" level
    - decomposed to a single interaction point (i.e. a button)
    
- system level GUI testing exercises the flow of events through the
system


#### MBT (Model Based Testing)
- test cases are derived in some part from a model that describes
some/all aspects of the SUT
    - the tests are generated from the specification, which is
    derived from the requirements
    - (therefore it's black box)


- Can be performed at various hierarchices of test framework
    - system/integ/unit    

#### Smoke Testing
- process of ensuring CRITICAL functionality of SUT
- typically the "first" test performed by testers before
accepting a build for further testing


- Failure of smoke testing is barrier to promotion


- the term is derived from electrical testing, where engineers would
switch on a device to see if "smoked"


#### Sanity Testing
- ensures BASIC functionality of SUT
    - objective is to ensure that SUT basic features continue
    working as expected
    - (the "rationality" of the SUT)
- like smoke tests, performed at the beginning of test process


---
### White-Box Testing (Based on Implementation)
- AKA - STRUCTURAL Testing
- rely on the analysis of source code to create test cases
    - determine if code structure/logic is incorrect.
    - accuracy of tests is tied to the tester's understanding of
    what the code is supposed to do.
    

#### Code Coverage
- defines a percentage of lines of source code that has been tested

- CON:
    - Code coverage is often constrained to the "easiest code to test"
    - this means that more complex paths or targets (the ones most
    likely to break) are the ones not covered. 

##### Statement Coverage
- the line of code coverage granularity

##### Decision/Branch Coverage
- control structure coverage (i.e. if-else)

##### Condition Coverage
- boolean expression coverage (true/false)

##### Paths Coverage
- every possible code path through SUT granularity

##### Entry/Exit Coverage
- Call and return of the coverage granularity

---
#### Fault Injection 
- Chaos/Failure Engineering
    - deliberate injection of faults into software to determine
    how well/poorly the SUT behaves. 
    - defects can "propagate"
        - i.e. the effects of the error pollutes or corrupts state
        in areas of the program outside of where the initial fault
        occurred.
        
#### Mutation Testing
- validates tests and test data by executing them against MANY 
different copies of the SUT in order to ID omissions in the code.
    - different/single/deliberate changes

### Grey-Box (Hybrid)
- This is a mix of Responsibility/Implementation driven test cases

### Non-Functional/Quality Attributes 
- requires considerable effort to test.
- broad in scope

#### Performance Testing
- measure response time w/ one or more users exercising the system

#### Load Testing
- focused on increasing the load on the system to some stated/implied
max load 
    - verifies that the system can handle defined boundaries
    - (usually a good idea to test PAST those boundaries)
    
#### Volume Testing
- load testing approach that is focused on data

#### Stress Testing
- analagous to load testing to the point of failure. 
- rather than validating that the system can handle defined boundaries, 
we test beyond the normal capacity to the point of failure


- purposes
    - identify the actual boundaries of the system
    - observe how the system behaves up to the point of failure
    - identify bottlenecks
    

#### Security Testing
- Ensures the following properties

##### Confidentiality
- protection against the disclosure of info

##### Integrity
- ensuring the correctness of information

##### Authentication
- ensuring the identity of a user

##### Authorization
- determining that a user is allowed to receive a service or perform
an operation

##### Availability
- ensures that the system performs its functionality when required

##### Non-Repudiation
- ensuring the denial that an action happened

##### Pen(atration) Testing 
- a type of security testing that involves allowed attempts for evaluating
the security of system infrastructure via attempts to exploit it

#### Usability Testing
- focuses on finding UI problems that make the product difficult to use
or make the output hard to understand or interpret

#### Accessibility Testing
- ensuring that the product is accessibility compliant
    - (i.e. the ability to access system functionality)
---
## Testing Types

### Manual Testing
- assessment of SUT by a person

#### Exploratory Testing
- a type of manual testing in which persons evaluate the system by 
free evaluation and investigation via personal perception.
- creative non-repetitive type of work. 


### Automated Testing
- assessment of SUT via special software/infrastructure
- automated tests are most commonly executed during the CI/CD phase
- most commonly impl'd w/ a *framework*


    Automated Software Testing
    - (Elfriede Dustin, "Implementing Automated Software Testing:
    How to Save Time and Lower Costs While Raising Quality (2009)")
    
        Application and implementation of software technology
        throughout the entire software testing life cycle with
        the goal to improve efficiencies and effectiveness.
        

- BENEFITS
    - anticipated cost savings
    - shorter test durations
    - increased thoroughness/completeness of tests performed
    - increased accuracy of tests performed
    - improvement of results reporting, statistical processing and
    subsequent reporting
    
    
    
#### Framework
- a test framework is a set of abstract concepts, processes, 
procedures and environments in which automated tests will
be designed, created and implemented. 
    - includes
        - physical structures used for test creation and impl
        - logical interactions among the framework components
        

- BENEFITS
    - High Code Reuse

##### Library vs. Framework
![LibVsFwk](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/LibraryVsFramework.png)

    per Martin Fowler
    
        A LIBRARY is essentially a set of functions that you 
        can call, these days usually organized into a set of classes.
        Each call does some work and returns control to the client. 
        
        A FRAMEWORK embodies some abstract design, with more
        behavior built in. In order to use it you need to insert 
        your behavior into various places in the framework either
        by subclassing or by plugging in your own classes. 
        The framework's code than calls your code at these points.
        
---
### Other Testing Approaches

#### Conformance Testing
- SUT is tested to determine whether or not the system meets its
specifications
- common in compliance/regulatory driven domains

#### Progression Testing
- testing new feature/functionality that is introduced into 
a system 
    - i.e. running a build w/ the new feature immediately after
    running the previous build before the introduction of the new
    feature
    
#### Regression testing
- similar to progression testing
    - (sometimes considered a subset)
- running existing test cases to ensure that new feature/functionality
doesn't impact the correctness of the rest of the system.

#### System Integration Testing
- exercising a system against any external or 3rd party components

#### User/Customer Testing
- test stage where users/customers can be invited to test and provide
input on the system

##### Alpha testing
- testing performed collaboratively between development and consumers before 
it is released externally. 
    - takes place at the development site (i.e. internally)
    - Customers come to the business site. 
    - (CAT Customer *Acceptance Testing* falls into this category)
    
##### Beta Testing
- testing performed as a "mini-release", being tested by a small subset of
users at their own locations before being released as a generally 
available. 

##### Operational Testing
- performed by the end user in its normal operating environment
- software is generally available.

#### Release Testing
- process of testing that a particular release of a system performed
by a separate team outside of the dev team that created it. 
    - "second pair of eyes"
- the goal is to convince the supplier of the system that it is good
enough for use. 
