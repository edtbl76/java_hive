# Dynamic Tests

## Static Testing
- When JUnit4/5 annotate test classes/methods using @Test
- these tests are defined at compile time. 

### Limitations
- The primary limitation of static testing is for the common
use case of executing the same test for a large variety of
input data. 
  
#### JUnit 4 Workaround 1: Looping
- loop input test data and exercise same test logic

    
    EXAMPLE

        public class MyTest {
            
            @Test
            public void test() {
                String[] input = { "A", "B", "C" };
                for (String s : input) {
                    exercise(s);
                }
            }

            private void exercise(String s) {
                System.out.println(s);
            }
        }

#### JUnit 4 Workaround 2: Paramterized Tests

    
        @RunWith(Parameterized.class) 
        public class Parameterizedtest {
            
            @Parameter(0)
            public Integer input1;

            @Parameter(1)
            public String input2;

            @Parameters(
                name = "My test #{index} -- input data: {0} and {1}")
            public static Collection<Object[]> data() {
                return Arrays
                    .asList(new Object[][] {
                        {1, "hello"},
                        {2, "goodbye"}
                    });
            }

            @Test
            public void test() {
                System.out.println(input1 + " " + input2);
            }
        }

### JUnit 5 TestFactory
- **@TestFactory** allows the creation of tests at runtime
    - supports various containers of **DynamicTest** instances
        - **Stream**
        - **Collection**
        - **Iterable**
        - **Iterator**
    
#### DynamicTest
- executed lazily, allowing the dynamic generation of test cases


#### Example 1: TestFactory and DynamicTests 

    class CollectionTest {

        // NOTE: This test raises an exception

        @TestFactory
        List<String> dynamicTestsWithInvalidReturnType() {
            return Arrays.asList("Hello");
        }

        @TestFactory
        Collection<DynamicTest> dynamicTestsFromCollection() {
            return Arrays.asList(
                dynamicTest("First dynamic test",
                    () -> assertTrue(true)),
                dynamicTest("Second dynamic test",
                    () -> assertEquals(4, 2 * 2))
            );
        }

        @TestFactory
        Iterable<DynamicTest> dynamicTestsFromIterable() {
            return Arrays.asList(
                dynamicTest("Third dynamic test",
                    () -> assertTrue(true)),
                dynamicTest("Fourth dynamic test",
                    () -> assertEquals(4, 2 * 2))
            );
        }

        @TestFactory
        Iterator<DynamicTest> dynamicTestsFromIterator() {
            return Arrays.asList(
                dynamicTest("Fifth dynamic test",
                    () -> assertTrue(true)),
                dynamicTest("Sixth dynamic test",
                    () -> assertEquals(4, 2 * 2)).iterator();
            );
        }
    }

##### Result
![DynamicTestExecution](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/DynamicTestExecution.png)


#### Example 2 : Dynamic Test Generation

        
    class DynamicExampleTest {
        
        @TestFactory
        Stream<DynamicTest> dynamicTestsFromStream() {
            Stream<String> inputStream = 
                Stream.of("A", "B", "C");

            retuyrn inputStream.map(
                input -> dynamicTest(
                    "Display name for input " + input,
                    () -> {
                        System.out.println("Testing " + input);
                    })
            )
        }
    }
##### Result
![DynamicTestGeneration](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/DynamicTestGeneration.png)


#### Example 3: DynamicTest.stream
- this method requires an input generator and a test executor
    - (input generator is a function that generates a display name
      based on an input value)


    EXAMPLE

        class StreamExampleTest {
            
            @TestFactory
            Stream<DynamicTest> streamTest() {

                // Input Data
                Integer array[] = { 1, 2, 3};
                Iterator<Integer> inputGenerator = 
                    Arrays.asList(array).iterator();

                // Display Names
                Function<Integer, String> displayNameGenerator =
                    (input) -> "Data input:" + input;

                // Test executor
                ThrowingConsumer<Integer> testExecutor = 
                    (input) -> {
                        System.out.println(input);
                        assertTrue(input % 2 == 0);
                    };

                // Returns a stream of dynamic tests
                return stream(inputGenerator, displayNameGenerator,testExecutor);
            }
            
        }

##### Result
![DynamicTestStreamResult](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/DynamicTestStreamResult.png)