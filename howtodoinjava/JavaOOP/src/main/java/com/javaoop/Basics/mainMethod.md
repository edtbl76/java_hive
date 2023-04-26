# MAIN METHOD

## Syntax Example

    EX:
        public class Main {
        
                public static void main(String[] args) {
                
                    System.out.println("Hello World!!");
                }
        }
        
### Why 'public'?
- The thinking is that it is public so it CAN BE ACCESSIBLE EVERYWHERE ANDS TO EVERY OBJECT
WHICH MAY DESIRE TO USE IT FOR LAUNCHING THE APPLICATION

- It isn't true though. 
    - you can invoke via the JNI w/o the main() method. 
    - if main() method isn't public, there is no compiler error
        - there is a RUNTIME error though, because the matching main() method isn't present.
        
### Why 'static'?
- Java supports overloaded constructors.
- like public, you can compile w/o the 'static' keyword, but it will result in a RUNTIME ERROR

### Why 'void'?
- because there is no point returning a value to the JVM (who actually invokes main())

### Why 'main()'?
- the thinking is that devs were already familiar with this (coming from C/C++)

### What happens internally when you invoke main()

- java (the binary executable) results in some JNI calls.
    - actually a simple exec file that parses the CLI
    - creates a String array in hte JVM to hold the CLI arguments
    - parses out the class name from that Array that was specified as the owner of main()
    - uses JNI calls to find the actual main() method
    - invokes the main() method  w/ the remainder of the CLI args passed  in as parameters
- this is very similar to what happens when we use REFLECTION from Java
    - it uses "confusingly named native function calls"
