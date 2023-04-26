# Repeated Tests

## @RepeatedTest
- special annotation that takes an integer as a parameter to 
indicate the number of times the test should be repeated. 
  

- each test behaves like **@Test**
- each repeated test preserves lifecycle callbacks
    - e.g. **@BeforeEach/@AfterEach** etc.
    

#### Example: Simple

        
    class SimpleRepeatedTest {
        
        @RepeatedTest(5)
        void test() {
            System.out.println("Repeated test");
        }
    }
![RepeatedTestOutput](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/RepeatedTestOutput.png)


### Additional Attributes
- (beyond the number of repetitions)
- {displayName}
   - name of the **@RepeatedTest** Method
- {currentRepetition}
  - current repetition count
- {totalRepetitions}
  - total number of repetitions
  

#### Example: Additional Attributes


    class TunningDisplayInRepeatedTest {
        
        @RepeatedTest(
            value  = 2,
            name = {displayName}{currentRepetition}/{totalRepetitions})
        @DisplayName("Repeat!")
        void customDisplayName(TestInfo testInfo) {
            System.out.println(testInfo.getDisplayName());
        }

        @RepeatedTest(value = 2, name = RepeatedTest.LONG_DISPLAY_NAME)
        @DisplayName("Test using long display name")
        void customDisplayNameWithLongPattern(TestInfo testInfo) {
            System.out.println(testInfo.getDisplayName());
        }

        @RepeatedTest(value = 2, name = RepeatedTest.SHORT_DISPLAY_NAME)
        @DisplayName("Test using short display name")
        void customDisplayNameWithShortPattern(TestInfo testInfo) {
            System.out.println(testInfo.getDisplayName());
        }

    }
![RepeatedTestWithDisplayNameOutput](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/RepeatedTestWithDisplayNameOutput.png)