# java.util.Date class


    1       Date()
            - initializes object w/ current date and time.
            
    2       Date(long millisec)
            - constructor accepts argument that equals "epoch time" (# of 
            milliseconds since midnight January 1, 1970)
            
# methods of java.util.Date class

    1   boolean             after(Date date)
                            - returns true if 'this' date is later than one
                            specified by date arg. 
                            
    2   boolean             before(Date date) 
                            - returns true if 'this' date is earlier than one 
                            specified by date arg
                            
    3   Object              clone()
                            - duplicates 'this' date
                            
    4   int                 compareTo(Date date)
                            - Compares value of invoking ('this') object to 
                            the specified date. 
                            - Returns 0 if values are equal
                            - Returns NEGATIVE value if 'this' object is earlier
                            - Returns POSITIVE value if 'this' object is later.
                            
    5   int                 compareTo(Object obj)
                            - works the same way as the previous compareTo
                            - Returns 'true' if object is of class date, otherwise
                            it throws a ClassCastException
                            
    6   boolean             equals(Object date) 
                            - returns true if 'this' Date object contains the
                            same time and date as the one specified. 
                            
    7   long                getTime()
                            - returns epoch time
                            
    8   int                 hashCode()
                            - returns hash code for 'this' object
                            
    9   void                setTime(long time)
                            - sets time and date as specified by time (which should
                            represent epoch time)
                            
    10  String              toString()
                            Converts 'this' Date to string and returns the result. 
                            
### Date Comparison

    - use getTime() to get epoch for 2 separate Date objects and compare the
    'long' results against each other. 
    
    - use before(), after() and equals()
    
    - use compareTo()
    


       

