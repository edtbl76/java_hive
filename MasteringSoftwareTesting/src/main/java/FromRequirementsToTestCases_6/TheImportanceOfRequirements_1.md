# The importance of Requirements

## Challenges w/ requirements
- "nebulous"
    - needs are broad, hard to define/prioritize
        - (especially in the early stages of projects)


- dynamic
    - the needs will change (a lot, and with great depth) over
    the life of the project
      


    Fred Brooks - The Mythical Man-Month (1975)

        The hardest single part of building a software system is deciding
        precisely what to build. No other part of the conceptual work is
        as difficult as establishing the detailed technical requirements.

        No other part of the work so cripples the resulting system if
        done wrong. No other part is as diffult to rectify later. 


### Features
- derived from end user needs
- defined as high-level descriptions of a software system's 
functionality.
  
### Requirements
- from each feature, one or more requirements are derived
    - functional OR non-functional
- defined as "everything that is true about the software, in order to 
  meet the consumer's expectations"
  

- scenarios (real-life examples) tend to be useful for adding details
  to a requirements description
    - (more valuable than abstract descriptions)
    
    
### Specification
- group of requirements and/or lists of features of a software system. 
---

## Stage 1: Requirements Elicitation
- stage of defining requirements
    - WHAT is the problem we are trying to solve?
    
### Modeling
- at the end of the requirements elicitation, we start modeling the
system or changes to the system. 
    - UML Diagrams 
        
- "Use Case" diagrams
    - model of the functionality of the system and its relationship;
    w/ the involved actors
    - COMPONENTS:
        - the pieces of the diagrams/system
    - SEQUENCE: 
        - how the state of the system changes based on the relationship
        w/ the actors.
          


#### Sketching vs. Modeling
- Modeling is VERY detailed and formal
- sketching is sort of "modeling-light". This is the type of design/arch
that takes place in agile.
  - informal
    

- Factors that determine the amount/type of modeling
    - type/size of the company 
        - multinational
        - SME
        - government
    - dev process
        - waterfall
        - agile
        - spiral
        - prototyping
    - type of software
        - build vs. buy (custom vs. COTS)
    - background of participants
        - experience
    - size of the project
        - the bigger the project, the more detail/granularity provided
        by the diagrams.
        
    
---

## Stage 2: Analysis
- requirements refinement
- stated requirements are analyzed
    - clarify ambiguity
    - resolve contradictions/conflicts. 
    

- modeling continues
    - technology choices have yet to be made. 
    

- goal is to firmly establish the WHAT of the system. 
---
  
## Stage 3: Design
- once we have defined the WHAT, we start focusing on the HOW 
    - implementation
    
- project guidelines are established

- software system architecture is derived from the requirements.

- modeling is at its heaviest in this phase. 
    - even in agile, this is when "sketching" is the most prevalent. 
    - Structural Diagrams:
        - component
        - deployment
        - object
        - package
        - and many more!!
    - Behavioral Diagrams
        - acticity
        - communication
        - state
        - sequence
    

- The goal of software DESIGN is to bridge the gap between the business
requirements and the technical impl details
  - modeling or sketching needs to establish a ubiquitous language to
    enhance/optimize communication between the different roles of 
    software engineers participating in a project. 

---
## Stage 4: Impl
- code that shit bitch!
  

---

## Stage 5: Testing
- Test plans are derived from the specification


    In order to verify our system, we need to know beforehand what we
    expect from it. 


- Barry Boehm (Retrospective on Software Quality and Java Testing)
    - are we building the product right? 
        - VERIFICATION
            - we need to know the requirements/desired features
    - Are we building the right product? 
        - VALIDATION
            - closes the gap between
                - the specification (features and requirements)
                - the real needs of the customer. 
            - high-level assessment
                - should usually include the end user
    

## Generic Development Process
![SDLC](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/SDLC.png)


    There is no universal workflow for the terms presented so far
        - communication
        - elicitation
        - refinement/analysis
        - design
        - implementation
        - test
        - deployment

    Visually it appears to flow linearly, but it can be iterative, 
    evolutionary or parallel. 

---