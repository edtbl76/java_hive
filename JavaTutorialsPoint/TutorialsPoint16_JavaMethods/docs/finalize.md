# finalize() Method

    - This method is called just before an object's final destruction by the 
    garbage collector. 
    
    - The entire purpose of the finalize() method is to ensure that an object
    terminates cleanly
    
        - closes open file handles
        - clears memory etc.
        
    ADDING FINALIZER TO A CLASS
    - supa simple> just define the finalize() method. 
    
    - Java runtime calls finalize() whenever it is about to recycle an object of
    that class. 
        - inside finalize you specify the actions you want performed 
        before an object is destroyed. 
        
        *YES, you have to do it yourself!
        
        
    EX:
    
        protected void finalize() {
            // clean shit up here. 
        }
        
    NOTE: if your program terminates before GC occurs, then finalize() isn't called1