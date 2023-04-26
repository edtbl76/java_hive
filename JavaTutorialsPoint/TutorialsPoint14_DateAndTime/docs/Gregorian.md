# GregorianCalendar Class

    This is a concrete implementation of a Calendar class that implements the 
    normal Gregorian calendar. 
    
    getInstance()
    -   Returns a GregorianCalendar initialized w/ current date and time in the
    default locale and time zone. 
    - defines two fields 'AD' and 'BC'
    

#  Constructor

    1   GregorianCalendar()
        - constructs default GregorianCalendar using current date and time in
        the default time zone w/ the default locale
        
    2   GregorianCalendar(int year, int month, int date)
        - constructs GregorianCalendar w/ given date set in the default time zone
        and default locale
        
    3   GregorianCalendar(int year, int month, int date, int hour, int minute)
        - constructs GregorianCalendar w/ given date and time set for the default
        time zone w/ the default locale
        
    4   GregorianCalendar(int year, int month, int date, int hour, int minute, int
        second)
        - constructs GregorianCalendar w/ given date and time set for the default
        time zone w/ the default locale
        
    5   GregorianCalendar(Locale aLocale)
        - constructs a GregorianCalendar based on the current time in the 
        default time zone w/ the GIVEN locale
        
    6   GregorianCalendar(TimeZone zone)
        - constructs a GregorianCalendar based on the current time in the given
        time zone w/ the default locale
        
    7   GregorianCalendar(TimeZone zone, Locale aLocale)
        - constructs a GregorianCalendar based on the current time in the given
        time zone w/ the given locale. 
        
### GregorianCalendar methods

    1   void                add(int field, int amount) 
                            - adds specified (signed) amount of time to given
                            time field based on the calendar's rules
                            
    2   protected void      computeFields()
                            - converts UTC as milliseconds to time field values
                            
    3   protected void      computeTime()
                            - overrides calendar converts time field values to UTC
                            as milliseconds.
                            
    4   boolean             equals(Object obj)
                            - returns true if 'this' GregorianCalendar is equal
                            to the specified object reference
                            
    5   int                 get(int field)
                            - gets the value for the given time field
                            
    6   int                 getActualMaximum(int field) 
                            - returns max value that this field could have, given
                            the current date
                            
    7   int                 getActualMinimum(int field) 
                            - returns min value that this field could have, given
                            the current date
                            
    8   int                 getGreatestMinimum(int field) 
                            - returns highest minimum value for the given field if
                            varies
                            
    9   Date                getGregorianChange()
                            - gets the GregorianCalendar change date
                            
    10  int                 getLeastMaximum(int field)
                            - returns lowest max value for the given field if varies
                            
    11  int                 getMaximum(int field)
                            - returns max value for the given field
                            
    12  Date                getTime()
                            - gets 'this' calendar's current time
                            
    13  long                getTimeInMillis()
                            - gets 'this' calendar's current time as a long 
            
    14  TimeZone            getTimeZone()
                            - gets the time zone
                            
    15  int                 getMinimum(int field) 
                            - returns min value for the given field
                            
    16  int                 hashCode()
                            - overrides hashcode
                            
    17  boolean             isLeapYear(int year) 
                            - returns true if specified year is a leap year
                            
    18  void                roll(int field, boolean up)
                            - adds/subtracts (up/down) a single unit of time on
                            the given field without changing larger fields
                            
    19  void                set(int field, int value)
                            - sets time field w/ given value
                
    20  void                set(int year, int month, int date) 
                            - sets values for the fields, year, month and date
            
    21  void                set(int year, int month, int date, int hour, int minute)
                            - sets values for the fields year, month, date, hour 
                            and minute
                            
    22  void                set(int year, int month, int date, int hour, int minute,
                            int second)
                            - sets values for the fields year, month, date, hour, 
                            minute and second
                            
    23  void                setGregorianChange(Date date) 
                            - sets GregorianCalendar change date
                            
    24  void                setTime(Date date) 
                            - sets 'this' Calendar's current time w/ given date
                            
    25  void                setTimeInMillis(long millis) 
                            - Sets 'this' Calendar's current time from given long
                            value
                            
    26  void                setTimeZone(TimeZone value) 
                            - sets time zone w/ given tz value
                            
    27  String              toString()
                            - returns a String representation of 'this' Calendar
                            
    
                            

  
                           
                       
                         
        