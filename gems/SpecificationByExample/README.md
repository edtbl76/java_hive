# Specification by Example

Main notes taken from the book [Specification by Example: How successful teams deliver the *right* software](https://gojko.net/books/specification-by-example/)

## Part 1: Getting Started

### Chapter 1: Key benefits

* Building the product right and building the right product are two different things. You need to do both in order to succeed.
* *Specification by Example* provides just enough documentation at the right time, helping to build the right product with short iterations or flow-based development processes.
* *Specification by Example* helps to improve the quality of software products, significantly reduces rework, and enables teams to better align analysis, development, and testing activities.
* In the long term, *Specification by Example* helps teams create a living documentation system, a relevant and reliable description of the functionality that’s automatically updated with the programming language code.
* The practices of *Specification by Example* work best with short iterative (Scrum, Extreme Programming [XP]) or flow-based (Kanban) development methods. Some ideas are also applicable to structured development (Rational Unified Process, Waterfall) processes.

### Chapter 2: Key process patterns

* The key process patterns of *Specification by Example* are deriving scope from goals, specifying collaboratively, illustrating specifications using examples, refining the specifications, automating validation without changing the specifications, validating the system frequently, and evolving living documentation.
* With *Specification by Example*, functional requirements, specifications, and acceptance tests are the same thing.
* The result is a living documentation system that explains what the system does and that is as relevant and reliable as the programming language code but much easier to understand.
* Teams in different contexts use different practices to implement process patterns.

### Chapter 3: Living documentation

* There are several models of looking at *Specification by Example*. Different models are useful for different purposes.
* *Specification by Example* allows you to build up a good documentation system incrementally.
* *Living documentation* is an important artifact of the delivery process, as vital as code.
* Focusing on creating a business-process documentation system should help you avoid the most common long-term maintenance problems with specifications and tests.

### Chapter 4: Initiating the changes

* *Specification by Example* is a good way to provide development teams with just-in-time specifications, so it’s a key factor for success with short iterations or flow-based development.
* Handle small chunks of software efficiently to enforce quick turnaround time and feedback.
* Emphasize effective, efficient communication instead of long, boring documents.
* Integrate cross-functional teams where testers, analysts, and developers work together to build the right specification of the system.
* Plan for automation overhead upfront.

## Part 2: Key process patterns

### Chpater 5: Deriving scope from goals

* When you’re given requirements as tasks, push back: Get the information you need to understand the real problem; then collaboratively design the solution.
* If you can’t avoid getting tasks, ask for high-level examples of how they would be useful—this will help you understand who needs them and why, so you can then design the solution.
* To derive the appropriate scope, think about the business goal of a milestone and the stakeholders who can contribute or be affected by that milestone.
* Start with the outputs of a system to get the business users more engaged.
* Reorganize component teams into teams that can deliver complete features.

### Chapter 6: Specifying collaboratively

* *Specification by Example* relies heavily on collaboration between business users and delivery team members.
* Everyone on the delivery team shares the responsibility for the right specifications. Programmers and testers have to offer input about the technical implementation and the validation aspects.
* Most teams collaborate on specifications in two phases: Someone works up front to prepare initial examples for a feature, and then those who have a stake in the feature discuss it, adding examples to clarify or complete the specification.
* The balance between the work done in preparation and the work done during collaboration depends on several factors: the maturity of the product, the level of domain knowledge in the delivery team, typical change request complexity, process bottlenecks, and availability of business users.

### Chapter 7: Illustrating using examples

* Using a single set of examples consistently from specification through development to testing ensures that everyone has the same understanding of what needs to be delivered.
* Examples used for illustrating features should be precise, complete, realistic, and easy to understand.
* Realistic examples help spot inconsistencies and functional gaps faster than implementation.
* Once you have an initial set of examples, experiment with data and look for alternative ways to test a feature to complete the specification.
* When examples are complex and there are too many examples or too many factors present, look for missing concepts and try to explain the examples at a higher level of abstraction. Use a set of focused examples to illustrate the new concepts separately.

### Chapter 8: Refining the specification

* Don’t just use the first set of examples directly; refine the specification from them.
* To get the most out of the examples, the resulting specification should be precise and testable, self-explanatory, focused, in domain language, and about business functionality.
* Avoid scripts and talking about software design in specifications.
* Don’t try to cover every single case. Specifications aren’t replacements for combinatorial regression testing.
* Start with one example for each important set of cases and add examples that illustrate particular areas of concern to programmers and testers.
* Define and use the Ubiquitous Language in specifications, software design, and tests.

### Chapter 9: Automating validation without changing specifications

* Refined specifications should be automated with as little change as possible.
* The automation layer should define how something is tested; specifications should define what is to be tested.
* Use the automation layer to translate between the business language and user interface concepts, APIs, and databases. Create higher-level reusable components for specifications.
* Automate below the user interface if possible.
* Don’t rely too much on existing data if you don’t have to.

### Chapter 10: Validating frequently

* Validate executable specifications frequently to keep them reliable.
* Compared to continuous integration with unit tests, the two main challenges for continuous validation are fast feedback and stability.
* Set up an isolated environment for continuous validation and fully automate deployments to make it more reliable.
* Look for ways to get faster feedback. Split quick and slow tests, create a pack for current iteration specifications, and divide long-running packs of executable specifications into smaller packs.
* Don’t just disable failing tests—either fix the problems or move the tests to a pack for low-priority regression issues that’s closely monitored.

### Chapater 11: Evolving a documentation system

* To get the most out of your living documentation system, keep it consistent and make sure that the individual executable specifications are easy to understand and easy to access for everyone, including business users.
* Evolve the ubiquitous language and use it consistently.
* As the system evolves, watch out for long specifications or several small ones that explain the same thing with minor variations. Look for concepts at a higher level of abstraction that would make these things easier to explain.
* Organize the living documentation system into a hierarchy that allows you to easily find all the specifications for the current iteration and any feature that was previously implemented.

## Part 3: Case studies

### Chapter 18: Concluding thoughts

* Collaboration on requirements builds trust between stakeholders and delivery team members
* Collaboration requires preparation
* There are many different ways to collaborate
* Looking at the end goal as business process documentation is a useful model
* Long-term value comes from living documentation
