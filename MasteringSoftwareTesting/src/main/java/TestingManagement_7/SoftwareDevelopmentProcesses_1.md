#  SDLC (Software Development Life Cycle) 
- the workflow for the activities, actions and tasks required to create
software systems. 
  
## Usual Phases
- Define the WHAT
    - requirements elicitiation
    - analysis
    - use case modeling
    


- Define the HOW
    - system architecture 
    - modeling of structural and behavioral diagrams
    

- Implementation
    - Fuckin' code it. 


- Release
    - activities that make software available for consumption
        - registration, deployment/delivery etc. 
    

## Test Methodologies
![WhereTestsSit](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/WhereTestsSit.png)

### BDD (Behavioral-Driven Development)
- test methodology that is naturally aligned w/ iterative/Agile
methodologies.

#### Relationship to Analysis 
- end user/consumer and dev team meet to come to an understanding 
about the features
    - This is typically part of the UX experience
    - involves Software Architect, PM, UX etc. 
- forms the basis to develop acceptance tests using 
Cucumber, etc. 
  
### TDD (Test-Driven Development) 
- derived from XP (Extreme Programming)
- tests are impl'd before software design
- conversion of requirements in the analysis phase directly to 
test cases. 
- software is designed/impl'd to pass the tests. 


### TFD (Test First Development)
- Derived from Unified Process
    - (RUP - Rational Unified Process)
- tests impl'd after design stage, but before impl of SUT
  - assures that software units are fully understood before
  its actual impl. 
    

### TLD (Test-Last Development) 
- Tests are impl'd after the impl of the SUT
- this is the "classic software development" approach
  - waterfall or spiral (risk oriented multi-waterfall)
