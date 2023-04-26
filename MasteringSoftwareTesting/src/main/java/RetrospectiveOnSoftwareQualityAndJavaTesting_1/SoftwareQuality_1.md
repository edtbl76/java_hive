# Software Quality 1
- As defined by Roger Pressman
 
     
     An effective software process applied in a manner that
     creates a useful product that provides measurable value
     for those who produce it and those wo use it.

### Participants
#### Consumer Roles
- Customers
    - people responsible for the acquisition of software
    
    
- Users
    - people who use software 
    

- Both :)


#### Producers
- people involved w/ the development, management, maintenance, 
marketing and service of software.

### SLA (Service Level Agreement)
- formal agreement that provides a contractual obligation and 
quantifiable definitions for software quality that must be met for the
consumer.  
---
## Quality Engineering (aka Quality Management)
- process that evaluates, assesses and improves the quality of software

![SQE Process](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/SQEProcess.png)
### 3 Major Activities of QE
- **Quality Planning**
    - establish overall quality goal 
        - manage customer expectations based on project cost/budget constraints
    - includes test plan/strategy
        - selection of test methodologies
        - set target measurements to "prove" quality
        
        
- **QA (Quality Assurance)**
    - planning/performing of activities (tests) that ensure quality is
    built into the software during SDLC
    - EXAMPLES
        - V & V (the most common)
        - use of quality standards
        - configuration management
        - documentation management
        - expert opinion
        
- **Post-QA** (Meta QA)
    - a collection of tasks to provide "quantitative" assessment 
    of product quality and identification of improvement opportunities
        - analysis, feedback, follow-up, etc.
        - "How can we further increase confidence?"

### Requirements and Specification
- **Requirement**
    - a statement identifying a capability, physical characteristic,
    or quality factor that bounds a product or process need for which
    a solution will be pursued.


- Requirements Development/Engineering
    - process of producing and analyzing customer, product and 
    product component requirements.
        - planning
        - traceability
        - impact analysis
        - change management
        
#### Functional Requirements
- actions that the product must do to be useful to users
- "does the thing" that stakeholders needs 
    - "action verbs"
    
#### Non Functional Requirements aka "Quality Attributes"
- properties or qualities that the product/feature must have
- the "ilities"

#### Specification 
- Design document linked to requirements that must specify the following
in a complete, precise, MEASURABLE/VERIFIABLE manner
    - requirements
    - design
    - behavior
    - system characteristics
    - procedures for determining whether or not these provisions have
    been satisfied.

---
### Quality Assurance
- Definition per Daniel Galin (Software Quality Assurance - 2004)


    Systematic , planned set of actions necessary to provide
    adequate confidence that the software development and
    maintenance process of a software system product conforms to
    established specification as well as with the managerial
    requirements of keeping the schedule wand operating within
    the budgetary confines
    
- selects quality standards and the V&V tasks, tools, methods to support them
    - V&V is a QA process that is barrier to release, meaning that 
    software MUST successfully navigate V&V.
    - "You shall not pass"
    
- QA goals
    - minimize costs of quality by introducing a variety of tasks during
    development and support lifecycle
        - preventative (i.e. don't let errors happen)
        - reactive (i.e. detect them when they do)
        - proactive (i.e. correct them in early stages of development before
        they can impact customers)
    - reduce the rate of "non-qualifying product/release"
    
#### ISO/IEC-25000
- SQuaRE (Software product Quality Requirements and Evaluation)
    - international standard 


##### Main Categories
- Internal Quality
    - properties of the system that can be measured w/o executing it.


- External Quality
    - properties of the system that can be measured during execution


-  Quality In Use
    - properties experiences by users during operation and maintenance of 
the system.

---
![ReferenceModel](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/ISO:IEC25000RefModel.png)
- Process Quality (development) influences Internal Quality
- Internal Quality determines external quality
- External quality determines quality in use

##### 8 Top-Level Quality Features (Ilities)
- **Functional Suitability**
    - degree to which product/system provides functionality that meets
    the stated and implied needs when used under specified conditions
 
    
- **Performance efficiency**
    - performance benchmark achieved relative to the amount of resources
    consumed under stated conditions
    

- **Compatibility**
    - while sharing the same hardware/software environment:
        - degree to which a product/system/component can exchange data
    w/ other products/systems/components 
        - and/or perform its required functions
        
        
- **Usability**
    - degree to which product/system can be used by specified users
    to achieve specified goals w/ effectiveness, efficiency and 
    satisfaction in a specified context of use
    
    
- **Reliability**
    - degree to which a system/product/component performs specified
    functions under specified conditions for a specific period of time
    
    
- **Security**
    - degree to which a product/system protects data so that
    persons or other products/systems have a level of data access
    appropriate to their types + levels of authZ
    
    
- **Maintainability**
    - degree of effectiveness + efficiency w/ which a product can be
    modified to improve, correct or adapt it to changes in
    environment and requirements
    
    
- **Portability**
    - degree of effectiveness + efficiency w/ which a product/system/component
    can be transferred from one hardware/software (or other operational or usage)
    env to another.
    
##### 5 Attributes of Quality In Use
- **Effectiveness**
    - accuracy/completeness w/ which users achieve specified goals


- **Efficiency**
    - resources expended in relation to Effectiveness
    

- **Satisfaction**
    - degree to which user needs are satisfied when a product/system
    is used in a specified context of use.


- **Risk Management**
    - degree to which a product/system mitigates potential risk to 
        - economic status
        - human life
        - health
        - environment
        - (Jeez...)
        
- **Context Coverage**
    - degree to which a product/system can be used w/ the previous 4
    attributes in both
        - specified contexts of use
        - contexts beyond those initially explicitly ID'd
        

## Verification and Validation
- (aka Software Quality Control)
- concerned w/ evaluating and ensuring that 
    - software meets its specifications
    - software delivers criteria expected by its users
    
#### Verification vs Validation (Barry Boehm)

##### Verification -> "Are we building the product right?"
- determine if software meets its specification
    - functional requirements
    - quality attributes (NFRs)

##### Validation -> "Are we building the right product?"
- ensure that software meet's acceptance criteria
- generalized process because a formal specification doesn't 
necessarily reflect the real wishes/needs of consumers


#### V&V/Software Quality Control Tasks

##### Software Testing aka "Dynamic Analysis"
- bulk of the work of QA
    - execution based
    - has a prerequisite of the existence of units/components/system 
    under test. 


- **testing**
    - given a piece of code, this consists of observing *test cases* 
    (sample of executions) and providing a verdict on those tests. 


##### Static Analysis
- non-execution based V&V
- performed against a source representation of the software
    - model of the specification of design
    - source code of the program
    
    
- EXAMPLES
    - manual inspection/*code review*
    - automated software analysis
        - source code is checked for well-scrutinized patterns that
        lead to errors
---
### Software Defects
- **defect/bug**: a generic software problem



#### Software Error Taxonomy (IEEE 610.12)
- **Error**: human action that produces an incorrect result
    - *Syntax Error*: program statement that violates one/more rules
    of language in wich it was written
    - *Logic Error*: incorrect data fields, out-of-range terms or
    invalid combinations


- **Fault**: manifestation of an error in the software system
    - incorrect step, process or data definition


- **Failure**: inability of software system to perform its required
functions


- *incidences*: systems associated with a failure perceived by
a consumer of the software.
    - NOTE this isn't defined by the IEEE's taxonomy but has been 
    included in most conversations

##### Causal Relationship
- Errors lead to the injection of faults into the software
- faults may cause failures when the software is executed
- incidences occur when failures are experienced by consumers.

#### Defect Countermeasures
- (As defined by Jeff Tian - Software Quality Engineering - 2005)
![Software Defect Countermeasures](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/DefectCountermeasures.png)

- Defect Prevention by Error Removal
    - use of product standards or process to minimize the injection of
faults into software


-  Defect Reduction by Fault Detection and Removal
    - this encompasses traditional testing and static analysis


- Defect containment through Failure Prevention
    - beyond scope of software system
    - minimization of the damage CAUSED by software system failures 
---
### Static Analysis

#### Advantages of Static Analysis
- During testing, it is possible for one error to be thrown, masking
other potential errors.
    - this doesn't happen during static analysis, because the code
    isn't executed. (There is no intra-error interactions)


- testing requires execution, which requires functional/complete code (or
alternatively a test harness to 'complete' the code)
    - static analysis can be performed against incomplete code w/o
    any additional cost. 
    
- Static analysis can exercise broader quality attributes of a system
    - compliance w/ standards (security, localization, et al.)
    - portability
    - maintainability
    
#### Categories of Static Analysis
- **Inspection**
    - manual examinations of software artifacts aimed at discovering
    and fixing faults in software. 
        - EXAMPLES:
            - specification
            - design models
    - the goal is to be able to start inspecting software before it 
    is an executable program.
    
    
- **Review**
    - process by which a group of people examine software and 
    associated docs looking for:
        - errors
        - non-conformity w/ standards
        - omissions
    - usually performed against new code before merging it into VCS
    - *peer review* is when the code is analyzed by another IC from 
    the same team.
    - EXPENSIVE (time and effort)
        - BUT, can ensure high internal code quality


- *walkthrough*
    - special type of review
    - per IEEE Standard for Software Reviews:
    
    
        a form of software peer review in which a designer 
        or programmer leads members of the dev team (and other 
        interested parties) through a software product and 
        participants ask questions and make comments about
        possible errors, violation of dev standards and other
        problems. 
        

- **Automated software analysis**
    - scans software using patterns known to be potentially
    dangerous or egregious.
    - delivered as open source/commercial tools (*linters*)
    - what they do:
        - locate many common programming faults 
        - analyze source code prior to test
        - id potential in order to re-code before manifested as failures
    - List of Fault Categories Detected by LINTING
        - DATA FAULTS
            - declared, unused variables
            - variables assigned multiple times w/o intermediate
            use
        - CONTROL FAULTS
            - unreachable code
            - unconditional branches into loops
        - I/O FAULTS
            - variables output twice w/o intermediate assignment
        - INTERFACE FAULTS
            - parameter-type mismatches
            - parameter under mismatches
            - non-usage of returns from function
            - uncalled functions/procedures
        - STORAGE MGMT FAULTS
            - unassigned pointers
            - bad pointer math
            
            
- *formal verification*
    - midpoint of static and dynamic analysis
    - provides mechanisms to check that a system operates
    according to its formal specification
    - This is a MATHEMATICAL proof of software via
        - logical operations
        - combinations of static/dynamic evaluation
    
    
    FORMAL VERIFICATION is often not adopted due to scalability 
    problems. 
    
    This is typically reserved for critical kernel systems or the like
        
        