# Test Planning

## Test Plan
- document that is a blueprint for performing software testing


- describes the properties of the testing efforts
    - objective
    - scope
    - approach
    - focus
    - distribution
    

## IEEE 829 Standard for Test Documentation
- (pretty formal, but it provides a good set of guidelines if 
  the formality isn't of interest.)
  
### 1. Analyze the product
- reinforcement of understanding the requirements of the system from
the consumer needs.
  

    It isn't possible to test a software system if there is no 
    information about it available. 


### 2. Design the test strategy

#### Scope
- "In Scope"
    - system components that will be tested
- "Out of Scope"
    - system components that will NOT be tested
    

##### Scope Reality
- exhaustive testing isn't practical, therefore deciding what will
and will not be tested is often a tradeoff
    - prioritization of customer requests
    - project budget
    - timeline
    - skills of software engineers/SDET
    
#### Testing Type
- which levels of testing
    - unit, integration, acceptance
- the strategy applied to each level
    - black box, white box, non-functional, Hybrid.
    
#### Risk Management
- potential risks should be documented so we can plan ahead. 

### 3. Define Test Objectives. 
- list of features to be tested are listed together w/ the target of
testing each one
  
### 4. Define Test Criteria
- 2 main categories of criteria. 
    - in both cases, the criteria is often measured as a percentage of
    passing/failing tests. 

#### Suspension Criteria
- the criteria or scenarios at which the development of new features is
suspended until the team solves all of the failures
  

    NOTE: in most DevOps cultures, problems are swarmed fairly quickly
        as soon as they occur. 

##### Exit Criteria
- the criteria/scenario at which we can proceed to the next phase of
development
  
### 5. Resource Planning
- summarization of the resources required to carry out testing
    - infrastructure
    - heads
    - tools
    - more
    
### 6. Plan Test Environment
- software/hardware/"stuff" on which tests should be executed. 

### 7. Schedule and estimation
- managers break out the project into small tasks that estimate the
efforts (person-month)
  
### 8. Determine Test Deliverables
- determine all documents and resources that have to be
    maintained to support the testing activies as well as the 
    verification that it took place. 
  

  
  