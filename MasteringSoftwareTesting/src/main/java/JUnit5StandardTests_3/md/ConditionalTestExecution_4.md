# Conditional Test Execution

### ExecutionCondition
- conditional extension point used to deactivate
    - all tests in a class
    - individual tests
  

#### Utility Enum 
    EXAMPLE

      public enum OS {
          WINDOWS, MAC, LINUX, OTHER;

          public static OS determine() {
              
              OS out = OTHER:
              String myOs = System.getProperty("os.name").toLowerCase()
              if (myOs.contains("win")) {
                  out = WINDOWS;
              } else if (myOs.contains("mac")) {
                  out = MAC;
              } else if (myOs.contains("nux")) {
                  out = LINUX;
              }
              return out;
          }
      }


#### Extension of ExecutionCondition 


    Example: Extension of ExecutionCondition

        public class OsCondition implements ExecutionCondition {
            

            @Override
            public ConditionEvaluationResult evaluateExecutionCondition(
                        ExtensionContext context) {

                Optional<AnnotatedElement> element = context.getElement();
                ConditionEvaluationResult out = ConditionEvaluationResult
                        .enabled("@DisabledOnOs is not present");

                Optional<DisabledOnOs> disabledOnOs = AnnotationUtils
                        .findAnnotation(element, DisabledOnOs.class);
  
                if (disabledOnOs.isPresent()) {
                    OS myOs = OS.determine();
                    if (arrays.asList(disabledOnOs.get().value()).contains*(myOs)) {
                        out = ConditionEvaluationResult.disabled(
                            "Test is disabled on " + myOs);
                    } else {
                        out = ConditionEvaluationResult.enabled(
                            "Test is not disabled on " + myOs);
                    }
                }
              
                System.out.println("--> " + out.getReason().get());
                return out;
            }
        }
- evaluation is performed by checking whether the **@DisabledOnOs** custom annotation is present
  - if it is, then the value of the OS is compared w/ the current platform.
  - the verdict of the condition results in whether or not the test is disabled/enabled.
  

#### Custom Annotation


    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @ExtendWith(OsCondition.class)
    public @interface DisabledOnOs {
        OS[] value();
    }


#### Test Using Custom Annotation

    class DisabledOnOsTest {
        
        @DisabledOnOs({ MAC, LINUX})
        @Test
        void conditionalTest() {
            System.out.println("This test will be disabled on OS X and Linux distributions");
        }
    }


## Assumptions
- This is a type of conditional execution
  - assumptions are programmatical implementations of pre-conditions, such that the test will only 
  be executed if the pre-conditions are true. 
    

- all JUnit Jupiter assumptions are *static* methods in the **org.junit.jupiter.Assumptions** class.
![Assumptions class](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/AssumptionsMethods.png)
  

### Examples
- **assumeTrue/False** can be used to skip tests whose pre-conditions
aren't met
- **assumingThat** is used to condition the execution of a PART in a 
test
  

    EXAMPLE
  
        class AssumptionsTest {
            
            @Test
            void assumeTrueTest() {
                assumeTrue(false);
                fail("Test 1 failed");
            }

            @Test
            void assumeFalseTest() {
                assumeFalse(this::getTrue);
                fail("Test 1 failed");
            }

            private boolean getTrue() {
                return true;
            }

            @test
            void assumingThatTest() {
                assumingThat(false, () -> fail("Test 3 failed");
            }
        }

#### Result
![AssumptionsTestResult](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/AssumptionsTestResult.png)
- first two tests are skipped altogether because the precondtions aren't
met
- the **assumingThatTest** is "partially executed"
  - only the lambda test is skipped. 