# 16.3 Initialization Safety

    "The guarantee of initialization safety allows properly constructed
    IMMUTABLE objects to be safely shared across threads without synchronization...
    
        regardless of how they are published
        
        even if published using a data race."

### Init Safety and Security        
- w/o Init Safety, it is (in theory) possible for immutable objects to appear to change their value
if *synchronization* isn't used by both the publishing and consuming threads.
    - this would be true for immutable objects like a String
    
- the security architecture of Java relies on the immutability of String, such that the lack of 
init safety would create security vulnerabilities that would compromise the viability of the language. 


### Initialization Safety Guarantees
- for all properly constructed objects:
    - all threads will see the correct values of final fields that were set by constructor
        - regardless of how the object is published
    - any variables that can be reached through a final field are also guaranteed to be visible
    to other threads
        - (i.e. elements of final array, contents of HashMap ref'd by final field)
        - applies only to objects that are reachable only through final fields of the 
        object under construction


- prohibits the reordering of any part of construction of objects that have final fi3elds with the
initial load of a reference to that object
    - (it's like an auto-*happens-before*)
    - similar to an enqueue to **BlockingQueue**, all writes to final fields made by the constructor 
    (or any values reachable via those fields) are frozen when the constructor completes.
        - when a thread obtains a reference to the object created by that constructor it is 
        guaranteed to see a value at least up to date to the value that existed when the
        constructor completed. 
        - any writes that init vars reachable through the final fields aren't
        reordered w/ operations that occur after the constructor completes.
        
### Example

    public class SafeStates {
        
        private final Map<String, String> states;
        
        public SafeStates() {
            states = new HashMap<>();
            states.put("massachusetts", "MA");
            states.put("washington", "WA");
        }
        
        public String getAbbreviation(String str) {
            return states.get(str);
        }
    }
#### Explanation
- this class can be safely published despite:
    - unsafe lazy initialization
    - has a reference to the resource in a public static field w/o *synchronization*
    - relies on non-thread safe HashSet
    
- this works because the object is final, and therefore has "Initialization Safety"

#### Limitations/Problems
- if **states** weren't final, or if any other method than the constructor was able to modify
the contents of states, initialization safety wouldn't provide thread safety w/o *synchronization*
    
- if **SafeStates** had any other nonfinal fields, other threads might still see incorrect values of those 
fields

- allowing the object to escape during construction INVALIDATES initialization safety

    
    Initialization Safety makes visibility guarantees for the value that are reachable through
    final fields as of th etime the constructor finishes.
    
    For values that are reachable through nonfinal fields or values that can be modified after
    construction
    
        you MUST use synchronization to ensure visibility.



    
    

