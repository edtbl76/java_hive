# Nested Tests

## Benefits
- allow test devs more capabilities to express relationship and order
in a group of tests.
  - Jupiter supports this by marking inner classes w/ **@Nested**
    annotation. 
    - all methods in inner class will be executed 
    - (unless they are filtered out)
  

## Constraints/Limitations
- only non-static nested classes (a.k.a. inner classes) can be
**@Nested**
- can be arbitrarily deep
  - dont' be stupid. 
  - setup/teardown for each test is inherited in nested tests
    - **@BeforeEach/@AfterEach**
  

- doesn't support **@BeforeAll/@AfterAll**
  - Java doesn't allow *static* members in inner classes. 
    
      
    WORKAROUND
      
      Annotate the top level test class with:

          @TestInstance(LifeCycle.PER_CLASS)

      This annotation changes the default behavior of the test
      instance lifecycle from "PER METHOD" to "PER CLASS"
    
      When the test instance lifecycle is set to PER CLASS, 
      the @BeforeAll and @AfterAll annotations don't have to be
      static, therefore they can be used in Nested tests. 

## Example 1: Simple Nesting (3 layers deep)

    class NestedTest {
        
        @BeforeEach
        void setup1() {
            System.out.println("Setup 1");
        }

        @Test
        void topTest() {
            System.out.println("Test 1");
        }

        @Nested
        class InnerClassTier1 {

            @BeforeEach
            void setup2() {
                System.out.println("Setup 2");
            }

            @Test
            void innerTestTier1() {
                System.out.println("Test 2");
            }

            @Nested
            class InnerClassTier2 {

                @Test
                void innerTestTier2() {
                    System.out.println("Test 3");
                }
            }
        }
    }
### Result
![NestedTestResultOutput](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/NestedTestResultOutput.png)


## Example 2: Nested Tests w/ Dispaly Name
- The output of Nested tests is pretty ugly as we saw in the previous
example
- This can be solved by using **@DisplayName**


    @DisplayName("A stack test")
    class StackTest {
        
        @Test
        @DisplayName("is instantiated")
        void isInstantiated() { } 

        @Nested
        @DisplayName("when empty")
        class WhenNew {
            
            @Test
            @DisplayName("is empty")
            void isEmpty() { }

            @Test
            @DisplayName("throws exception when popped")
            void throwsExceptionWhenPopped() { }

            @Test
            @DisplayName("throws exception when peeked")
            void throwsExceptionWhenPeeked() { }

            @Test
            @DisplayName("after pushing an element")
            class AfterPushing {

                @Test
                @DisplayName("it is no longer empty")
                void isNotEmpty() { }

                @Test
                @DisplayName("returns element when popped")
                void returnsElementWhenPopped() { }
  
                @Test
                @DisplayName("returns element when peeked")
                void returnsElementWhenPeeked() { }
            }
        }
    }

### Objective
- the class structure provides an order of execution for the
tests. 
- the **@DisplayName** helps make the output more readable. 
![IntellijReadableNested](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/IntellijReadableNested.png)
