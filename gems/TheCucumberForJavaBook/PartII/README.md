# Part II: A Worked Example

## Chapter 7: Step Definitions: On the Inside

Transforms help with maintainability by removing annoying duplicate code to process captured arguments from steps.

Java code that supports the step definitions can be factored out into separate classes.

It’s good practice to organize step definition files with one file per domain entity.

You can pass state between steps using helper classes that are instantiated and managed by Cucumber’s integration with one of several dependency injection frameworks.

## Chapter 8: Support Code

Four criteria for a simple design, according to Kent Beck (*Extreme Programming Explained*)

* Passes all the tests
* Reveals all the intention
* Contains no duplication
* Uses the fewest number of classes or methods

Working outside-in with Cucumber blurs the lines between testing and development. Always be ready to learn something new about the problem domain, whether you’re deciding on the wording in a Cucumber scenario or choosing the parameters for a method.

By taking care to craft a clean interface between your tests and the system underneath, you’ll end up with tests that can easily evolve with the system’s changing requirements.

Cucumber’s hooks can be used to invoke Java code before and after each scenario or to run them before specific scenarios using tags.

## Chapter 9: Message Queues and Asynchronous Components

When you add asynchronous behavior to a system, you need to make a concerted effort to tame the random effects that it can have on your tests. Build your tests with a knowledge of how the system works and introduce synchronization points where timing issues are likely to arise.

Using sleeps in your steps is not a good way to tackle these timing issues, because it makes your tests slow and doesn’t solve the reliability problem: if the system changes and becomes slower, your sleep may not be long enough and the test will start to break again.

The best solution is to listen for events broadcast by the system and pause at the appropriate points in the scenario until those events have been received. That way, you minimize the amount of time the tests waste waiting for the system.

The next best solution is to use sampling to repeatedly poll the system, looking for an expected change of state. This approach works in most circumstances, but you need to take care, especially when the outcome you’re looking for at the end of the scenario looks just the same as at an earlier time in the scenario.

## Chapter 10: Databases

Resetting state between scenarios is vital; otherwise, you get weird failures.

Transaction-based cleaning is preferred because it is fast, but it works only when there is one single-threaded process.

Truncation-based cleaning is a slower, brute-force technique that works in multiprocess and multithreaded environments.

## Chapter 11: Simplifying Design with Dependency Injection

Dependency injection greatly simplifies the management of the graph of objects needed to run the scenarios.

## Chapter 12: Working with Web Applications

[Selenium WebDriver](https://www.selenium.dev/documentation/en/webdriver/) API is a great tool that can be integrated with Cucumber for implementing tests for web applications.

## Chapter 13: Keeping Your Features Fast

One of the simplest ways to reduce your Cucumber runtime is to run fewer scenarios. Tag your scenarios, and you can choose a subset of the scenarios to run, covering the area that you’re currently working on. Once the work is checked in, a larger, but still reduced, subset of faster tests might run in our continuous integration (CI) server. Larger subsets might run in subsequent steps of a continuous delivery pipeline, or at scheduled intervals (such as overnight or weekly). Finally, the full set of scenarios could run on demand as part of a release or QA process.

Unit tests are preferable to full-stack tests because they are faster, and when they fail, they give us a clear indication of where we need to go to fix the problem. Additionally, unit tests are often less brittle and easier to understand than full-stack tests.

Using BDD, we encourage the team to specify the application’s behavior in business language.
