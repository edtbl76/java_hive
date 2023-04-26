# Throw Keyword
- this is used to explicitly THROW an exception... i.e. makes the exception happen. 


    public void method() {
        // crappy code
        throw new CrappyCodeException("I have encountered Crappy Code");
    }
    

# Throws
- used to declare the list of exceptions which MAY BE THROWN by the given method or constructor.
(i.e "Folks, this is how I break...")

# UNCHECKED EXCEPTIONS
(Every subclass of Error and RuntimeException)
- it is NOT mandatory to handle unchecked exceptions
- these are NOT propagated to the caller method, which is why they don't require
explicit exception handling.
    
    public class JavaExample {
        
        public static void main(String[] args) {
            method();
        }
        
        public static void method() {
            throw new NullPointerException();
        }
    }
    
    
# CHECKED EXCEPTIONS
(Everything else under Throwable)
- it is MANDATORY to handle checked exceptions
- checked exceptions are always propagated to the caller method. 

    
    public class JavaExample {
            
            public static void main(String[] args) {
            
                try {
                    method();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }   
            }
            
            public static void method() throws FileNotFoundException {
                throw new FileNotFoundException();
            }
        }
    
    
# Throw vs. Throws

| Throw | Throws |
| --- | --- |
| throw a single exception explicitly from any method/constructor | used in a method/constructor to denote what exception(s) can be thrown |
| followed by an instance of exception class | followed by exception class name |
| used inside the body of the method/constructor | used as part of the method/constructor signature |
| can only throw a single exception | multiple exceptions may be declared |
| can break a switch statement or loop w/o using break | <--- can't do that |
