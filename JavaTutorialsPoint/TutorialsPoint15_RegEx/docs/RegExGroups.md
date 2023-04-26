# Capturing Groups

    - these are a way to treat multiple characters as a single unit. 
    
    - they are created by placing the characters to be grouped inside a set of
    parentheses. 
    
        EX: (cat) is a capturing group of the letters 'c', 'a', 't'
        
    - Capturing Groups are numbered by counting their open parentheses from 
    left to right. 
    
        EX: ((A)(B(C)))
        
        This has FOUR Capt. Groups.
        
        1.) ((A)(B(C)))
        2.) (A)
        3.) (B(C))
        4.) (C)
        
        NOTE: the groupCount() method on Matcher objects will return an 'int'
        representing the number of capturing groups present in the matcher's
        pattern.
        
        NOTE: 'group 0' is a special group that represents the entire expression,
        this group isn't reflected in the groupCount().