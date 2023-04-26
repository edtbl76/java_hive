# Parameterized Tests
- special kinds of tests in which the data input is injected into the test in 
order to reuse the same test logic over and over. 
  
## Dependency Management - junit-jupiter-params

    pom.xml Maven

        <dependency>
            <groupid>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-params</artifactId>
            <version>${junit.jupiter.version}</version>
            <scope>test</scope>
        </dependency>



    build.gradle Gradle

        dependencies {
            testCompile("org.junit.jupiter:junit-jupiter-params:${junitJupiterVersion}")
        }


## @ParameterizedTest
- annotation located in *org.junit.jupiter.params*


- tests declared w/ this annotation behave like@Test
    - all lifecycle callbacks and extensions continue to work the same way.
    

## Argument Provider
- additional annotation required when using **@ParameterizedTest**
    - these annotations allow for provisioning data input (the test parameters)
    from different sources.
      

| Arguments Provider annotation | Description |
| --- | --- |
| @ValueSource | used to specify an array of literal values of String, int, long or double |
| @EnumSource | Argument source for constants of a specified enumeration (java.lang.Enum) |
| @MethodSource | Provides access to values returned by static methods of the class in which this annotation is declared |
| @CsvSource | Argument source which reads comma-separated-values from its attribute |
| @CsvFileSource | Argument source which is used to load CSV files from one or more classpath resources |
| @ArgumentsSource | Used to specify a custom argument provider (Java class that impls the interface) org.junit.jupiter.params.provider.ArgumentsProvider |

### @ValueSource

#### Example 1 : String Parameter
- (See examples.ValueSourceStringsParameterizedTest)
- test method defines param'd test
  - annotation includes an array of **String**
  - array has 2 elements, so test is exec'd twice, once per element
  - data is injected into the test method using the argument of the method. 
  
##### Result
![ValueSourceResultString](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/ValueSourceResult.png)

#### Example 2: Integer Primitives
- (See examples.ValueSourcePrimitiveTypesParameterizedTest)

##### Result
![ValueSourceResultPrimitive](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/ValueSourceResultPrimitive.png)

---
### @EnumSource
- Argument source for constants of a specified enumeration (java.lang.Enum)

#### Example 1 : EnumSourceParam'dTest
- see example.EnumSourceParameterizedTest
- this scans the provided class for enum values and then executes
the test logic once per enum value found in the class

##### Result
![EnumSourcePTResult](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/EnumSourcePTResult.png)

#### Example 2: Enum Filtering
- see example. 
- **@EnumSource** supports elements that can be used for filtering
  - *mode*
    - Constant value which determines the TYPE of filtering
    - defined as an enum in inner class *org.junit.jupiter.params.provider.EnumSource.Mode*
    - POSSIBLE VALUES:
      - **INCLUDE**
        - (default) selects values whose names are provided via the *names* element
      - **EXCLUDE**
        - selects all values OTHER than those names that are provided fia the *names* element
      - **MATCH_ALL**
        - selects values whose names match ALL of the PATTERNS in the *names* element
      - **MATCH_ANY**
        - selects values whose names match ANY of the PATTERNS in the *names* element
  - *names*
    - array of strings which allows the selection of a group of *enum* constants
    - criteria for inclusion/exclusion linked to value of the mode. 
    - patterns (regular expressions) may be provided for the **MATCH_XXX** modes. 
  
##### Result
![EnumFilteringPTResult](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/EnumFilteringPTResult.png)

___
### @MethodSource
- allows the definition of a *static* method in which the 
arguments for test are provided as a **Stream**
  
#### Example - stringProvider
- see example.MethodSourceStringsParameterizedTest
- the test logic execs once per element in the stream.

##### Result
![MethodSourceStringsPTResult](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/MethodSourceStringsPTResult.png)

#### Example - Objects Provider
- see example.MethodSourceObjectsParameterizedTest

#### Result
![MethodSourceObjectsPTResult](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/MethodSourceObjectsPTResult.png)

#### Example - Primitives 
- see example.MethodSourcePrimitiveTypesParameterizedTest

##### Result
![MethodSourcePrimitivesPTResult](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/MethodSourcePrimitivesPTResult.png)


#### Example - Mixed Types
- see example.MethodSourceMixedTypesParameterizedTest
- This uses Jupiter's **Arguments** class. 

##### Result
![MethodSourceMixedTypePTResult](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/MethodSourceMixedTypePTResult.png)
---
### @CsvSource and CsvFileSource
- uses CSV files to specify argument source. 
  - CSV content is AUTOMATICALLY converted to **String** and *int*
  
#### Example 1 - CsvSource
- see example.CsvSourceParameterizedTest
- this works for very short blobs of CSV data. (prefiltered?)

##### Result
![CsvSourcePTResult](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/CsvSourcePTResult.png)

#### Example 2 - More Data, CsvFileSource
- see resources.input.csv and example.CsvFileSourceParameterizedTest


- Internally, **@CsvFileSource** locates the file via 
  - *java.lang.Class.getResourceAsStream()*
  - the path of the file is interpreted as a path local to the pkg
  class we are calling it from.
      - i shoved it in "resources", so we have to specify it as
      - "/input.csv"

##### Result
![CsvFileSourcePTResult](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/CsvFileSourcePTResult.png)
---
### @ArgumentsSource
- allows specifying a custom class that contains the parameters
for the test
    - NOTE: this is reusable in different tests.


- must implement *org.junit.jupiter.params.provider.ArgumentsProvider* interface

#### Example 1 - CustomArgumentsProvider1
- (SEE example.CustomArgumentsProvider1 and example.ArgumentsSourceParameterizedTest)
- custom ArgumentsProvider
  - implements **ArgumentsProvider** interface
  - must Override **provideArguments**
    - this method defines the parameters that will be used in 
    the test.
    - returns a **Stream** of **Arguments**
  
##### ExtensionContext
- *org.junit.jupiter.api.extension.ExensionContext* 
  - contextual object with an API that exports different methods
  to enumerate attributes of the test instance
![ExtensionContextAPI](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/ExtensionContextAPI.png)

##### Result
- the first line of the result demonstrates how **ExtensionContext** 
  provides programmatic identification of the test.
![ArgumentsSourceExample1](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/ArgumentsSourceExample1.png)
  
#### Example 2 - CustomArgumentsProvider2
- (SEE example.CustomArgumentsProvider2 and example.ArgumentsSourcesParameterizedTest)
- it is possible to provide multiple argument sources in the
same parameterized test.
  - Method 1:
    - **@ArgumentsSource** is a *java.lang.annotation.Repeatable*
    annotation, so we can use multiple instances of the annotation
    in the same parameterized test
  - Method 2:
    - use **@ArgumentsSources** annotations which provides a 
    simpler, more readable, implementation of method 1 by tucking
    multiple singular instances in a container
      
##### Results
![ArgumentsSourcesExample](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/ArgumentsSourcesExample.png)

### Argument Conversion
- Jupiter provides built-in implicit converts to support cases like @CsvSource/@CsvFileSource
  - can be impl'd based on specific requirements by using EXPLICIT converters

#### Implicit Conversion
- internal handling/rules used by JUnit5 for conversions of parameterized test arguments from **String**
to some other target type.
- this can be thought of as the "default" conversion capabilities 

| Target Type | Example | 
| --- | --- |
| boolean/Boolean | "false" -> false |
| byte/Byte | "1" -> (byte) 1 |
| char/Character | "a" -> 'a' |
| short/Short | "2" -> (short) 2 |
| int/Integer | "3" -> 3 |
| long/Long | "4" -> 4L |
| float/Float | "5.0" -> 5.0f |
| double/Double | "6.0" -> 6.0d |
| Enum subclass | "SECONDS" -> TimeUnit.SECONDS |
| java.time.Instant | "1970-01-01T00:00:00Z" -> Instant.ofEpochMilli(0) |
| java.time.LocalDate | "2017-10-24" -> LocalDate.of(2017, 10, 24) |
| java.time.LocalDateTime | "2017-03-14T12:34:56.789" -> LocalDateTime.of(2017,3, 14, 12, 34, 56, 789_000_000) |
| java.time.LocalTime | "12:34:56.789" -> LocalTime.of(12, 34, 56, 789_000_000) | 
| java.time.OffsetDateTime | "2017-03-14T12:34:56.789Z" -> LocalDateTime.of(2017,3, 14, 12, 34, 56, 789_000_000, ZoneOffset.UTC) |
| java.time.OffsetTime |"12:34:56.789" -> OffsetTime.of(12, 34, 56, 789_000_000, ZoneOffset.UTC) | 
| java.time.Year | "2017" -> Year.of(2017) |
| java.time.YearMonth | "2017-10" -> YearMonth.of(2017, 10) | 
| java.time.ZonedDateTime |"2017-10-24T12:34:56.789Z" -> ZonedDateTIme.of(2017,10 , 24, 12, 34, 56, 789_000_000, ZoneOffset.UTC) |


##### Example
- see example.ImplicitConversionTest
- code is fairly straightforward based on the table above

##### Result
![ImplicitConversionExample](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/ImplicitConversionExample.png)

#### Explicit Conversion
- these are customizable capabilities beyond what is provided by
JUnit 5's implicit conversion
  - allows specification of a class which is going to
  make the custom conversion of parameter types.
  - the custom converter is identified via the **@ConvertWith**
  annotation
    
##### Example
- see example.ExplicitConversionTest and example.CustomArgumentsConverter
- our custom class (**CustomArgumentsConverter**) extends **SimpleArgumentConverter**
  - this class overrides the **convert** method where the action
  conversion takes place. 
    - (in our case we just transform the argument source to
      **String**)
      
##### Result
![ExplicitConversionExample](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/ExplicitConversionExample.png)

### Custom Names
- a parameterizes test is usually executed as several
single test
- for the purposes of traceability it is a good practice to
link each test exec. w/ the argument source
  

- @ParameterizedTest takes an element called *name*, which
accepts a custom name (**String**) that can be used for
built-in placeholders
  
| Placeholder | Description |
| --- | --- |
| {index} | current invocation index (first one is 1, second is 2...) |
| {arguments} | comma-separated arguments complete list | 
| {0}, {1}, ... | Value for an individual argument (0-based) |

#### Example
- see example.CustomNamesParameterizedTest

##### Result
![CustomNamesExample](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/CustomNamesExample.png)