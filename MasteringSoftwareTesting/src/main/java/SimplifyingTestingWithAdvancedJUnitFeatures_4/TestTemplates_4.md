# Test Templates

## @TestTemplate
- methods that are templates for test cases
- methods that use this annotation will usually be invoked multiple times depending on the
invocation context that is returned by registered providers.
  

    EXAMPLE

        class TemplateTest {
            
            @TestTemplate
            @ExtendWith(MyTestTemplateInvocationContextProvider.class)
            void testTemplate(String parameter) {
                System.out.println(parameter);
            }
        }
- test templates are used together w/ a registered **TestTemplateInvocationContextProvider**

## TestTemplateInvocationContextProvider extension

    EXAMPLE
    
        public class MyTestTemplateInvocationContextProvider 
                        implements TestTemplateInvocatinoContextProvider {
            
            @Override
            public boolean supportsTestTemplate(ExtensionContext context) {
                    return true;
            }

            @Override
            public Stream<TestTemplateInvocationContext> 
                            provideTestTemplateInvocationContexts(ExtensionContext context) {
            
                    return Stream.of(
                            invocationContext("parameter1"), 
                            invocationContext("parameter2")
                    );
            }

            private TestTemplateInvocationContext invocationContext(String parameter) {
                    return new TestTemplateInvocationContext() {
                        @Override
                        public String getDisplayName(int invocationIndex) {
                            return parameter;
                        }

                        @Override
                        public List<Extension> getAdditionalExtensions() {
                            return Collections.singletonList(new ParameterResolve() {
                                
                                    @Override
                                    public boolean supportsParameter(
                                                ParameterContext parameterContext, 
                                                ExtensionContext extensionContext) {
                                        
                                            return parameterContext
                                                .getParameter()
                                                .getType()
                                                .equals(String.class);
                                    }
    
                                    @Override
                                    public Object resolveParameter(
                                                ParameterContext parameterContext,
                                                ExtensionContext extensionContext) {
                                        
                                            return parameter;
                                    }
                            });
                        }
                    }
            }
            
        }


#### Result
![TestTemplateExample](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/TestTemplateExample.png)
