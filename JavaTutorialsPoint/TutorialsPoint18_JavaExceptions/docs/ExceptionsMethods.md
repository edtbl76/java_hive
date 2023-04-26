# methods available from Throwable Class


    1   public String               getMessage()
                                    - returns detailed message about exception
                                    that has occurred
                                    - initialized via Throwable constructor
                                    
    2   public Throwable            getCause()
                                    - returns cause of exceptable represented
                                    by Throwable obj
                                    
    3   public String               toString()
                                    - returns name of the class concatenated w/ 
                                    result of getMessage()
                                    
    4   public void                 printStackTrace()
                                    - prints result of toString() along w/ 
                                    stack trace to System.err (error output stream)
                                    
    5   public StackTraceElement[]
                                    getStackTrace()
                                    - returns array containing each element of
                                    stack trace
                                    - element at 0 is TOP of call stack
                                    - last element is BOTTOM of call stack
                                    
    6   public Throwable            fillInStackTrace()
                                    - fills stack trace of 'this' Throwable obj
                                    w/ the current stack trace, adding to any 
                                    previous info in th4e stack trace.  
                                    
                                