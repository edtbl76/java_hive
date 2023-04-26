# Always use length() instead of equals() to Check an empty string. 

    CORRECT:   
        public boolean isEmpty(String str) {
            return str.length() == 0;
        }
        
        
    INCORRECT:
        public boolean isEmpty(String str) {
            return str.equals("");
        }
        
        
WHY? 
    - this has to do w/ the underlying code of the String method itself. 
    
    length() just returns a count
    equals() deals w/ objects and does a whole bunch of shit. The fact that I don't 
    want to type it out and mention it right now is because it TAKES A FUCK LOAD OF TIME.
        - don't be stupid... it took 5 words to tell you what length does. 
        - It is fewer instructions.. so it is less time, less space. use length().
        
        
NOW IGNORE ALL OF THIS because isEmpty() exists from JDK 1.6 and later. 
        