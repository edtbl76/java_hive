# RECURSION
- programmatic technique where a method invokes itself repeatedly until a certain predefined condition is
met. 
- the purpose of recursion is to take a large multi-step problem and break it down into
decreasingly smaller problems until it can be broken up no longer further, and solving that 
smaller problem. 


## RECURSION SYNTAX

    method(T parameters...) {
    
        if(precondition == true) {          // precondition that ends recursion
            return result;
        }
        
        return method(T parameters...);    // recursive call
    }
    
### TAIL RECURSION

- when the last statement is executed inside the method (usually along w/ a return statement)


    method(T parameters...) {
        if(precondition == true) {
            return result;
        }
        
        return method(T parameters);
    }
    
### HEAD RECURSION

- any recursion that isn't tail recursion

    
    method(T parameters...) {
        
        if(some condition) {
            return method(T parameters...);
        }
        
        return result;
    }