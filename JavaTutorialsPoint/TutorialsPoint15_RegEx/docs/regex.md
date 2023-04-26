# java.util.regex

### Pattern Class
    
    - A pattern object is a compiled representation of a regular expression
    - NO public constructors
    
    - to create a pattern you must invoke one of its public static 'compile()'
    methods, which returns a Pattern object. 
        - (These methods accept a regex as the first argument) 
       

# Matcher Class

    - a matcher object is the engine that interprets Pattern and performs match
    operations against an input string
    - NO public constructors
    
    - to create a Matcher, you have to invoke the 'matcher()' method on a Pattern 
    object
    
### Methods of the Matcher Class

    1   public int          start()
                            - Returns the start index of the previous match
                            
    2   public int          start(int group) 
                            - Returns the start index of the subsequence
                            captured by the given group during previous
                            match operation
                            
    3   public  int         end()
                            - Returns offset after last character matched
                            
    4   public  int         end(int group) 
                            - returns offset after last char of subsequence 
                            captured by the given group during the previous
                            match operation
                            
    "Study Methods"
    
    1   public boolean      lookingAt()
                            - attempts to match input sequence, starting at 
                            beginning of the region, against the pattern
                            (This is similar to contains, such that the 
                            the entire sequence doesn't need to be matched)
                            
    2   public boolean      find()
                            - attempts to find next subsequence of the input
                            sequence that matches the pattern
                            
    3   public boolean      find(int start) 
                            - resets this matcher and then attempts to find
                            the next subseq. of the input sequence that matches
                            the pattern (starting at the specified index) 
                            
    4   public boolean      matches()
                            - attempts to match the entire region against the 
                            pattern
                            (this means that the entire sequence must be matched 
                            or equal)
                            
                                EX: foo = foo, foo != fooo
                            
    "Replacement Methods"
    
    1   public Matcher      appendReplacement(StringBuffer sb, String replacement)
                            - implements a non-terminal append-and-replace step
                            (non-terminal means that this stops when it doesn't
                            find a match. If there is any unmatched data after
                            the this makes its final match, it will be omitted
                            from the Stringbuffer!) 
                            
    2   public StringBuffer
                            appendTail(StringBuffer sb) 
                            - implements a terminal append-and-replace step
                            (This step is 'the_rest', such that it captures
                            any remaining data after the last match made by
                            appendReplacement and adds it to the sb) 
    
    3   public String       replaceAll(String replacement) 
                            - replaces every subsequence of the input sequence
                            that matches the pattern with the given replacement
                            string
                            
    4   public String       replaceFirst(String replacement) 
                            - replaces the first subsequence of the input 
                            sequence that matches the pattern w/ the given
                            replacement string
                            
    5   public static String
                            quoteReplacement(String s) 
                            - Returns a literal replacement String for the specified
                            String. 
                            - produces a String that will work as a literal 
                            replacement 's' in the appendReplacement method of the
                            Matcher class
                            
    
                         
    
# PatternSyntaxException

    - an object that represents an unchecked exception that indicates a syntax error
    in a regular expression pattern.
    
### PatternSyntaxException Class Methods


    1   public String           getDescription()
                                - retrieves description of the error
                                
    2   public int              getIndex()
                                - retrieves error index
                                
    3   public String           getPattern()
                                - retrieves erroneous regex pattern
                                
    4   public String           getMessage()
                                - returns multi-line string containing descr. of
                                the syntax error, its index, the bad regex pattern, 
                                and visual indication of the error index within
                                the pattern.