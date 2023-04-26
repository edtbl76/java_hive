### BitSet

    BitSet class implements a group of bits/flags that can be set/cleared 
    individually. (special type of array, sort of a "vector' of bits)
    
    - very useful for keeping track of a set of boolean values
    
    - legacy class reengineered in Java 1.4
    
    
    CONSTRUCTORS
        
        1   BitSet()            - creates a default object
        2   BitSet(int size)    - allows you to specify the initial size (
                                the no. of bits that it can hold) 
                                
                - in either case, all bits are initialized to zero
                
    METHODS
    
        NOTE: BitSet implements the Cloneable interface
        
        1   void        and(BitSet bitSet)
                        - ANDS the contents of the invoking BitSet object
                        w/ those specified by bitSet. 
                            - the result is placed into the invoking object. 
                            
        2   void        andNot(BitSet bitSet) 
                        - for each 1 bit in bitSet, the corresponding bit 
                        in the invoking BitSet is cleared
                        
        3   int         cardinality()
                        - returns # of set bits in invoking object
                        
        4   void        clear()
                        - sets all bits to zero
                        
        5   void        clear(int index)
                        - zeros the bit specified by 'index'
                        
        6   void        clear(int startIndex, int endIndex)
                        - zeroes the bits from startIndex to endIndex
                        
        7   Object      clone()
                        - duplicats invoking BitSet object
                        
        8   boolean     equals(Object BitSet) 
                        - returns true if 'this' BitSet is equivalent to the
                        one being passed as an argument
                        
        9   void        flip(int index)
                        - reverse the bit specified by the index
                        
        10  void        flip(int startIndex, int endIndex) 
                        - returns bits from 'startIndex' to 'endIndex'
                        
        11  boolean     get(int index) 
                        - returns the current state of the bit at the 
                        specified index
                        
        12  BitSet      get(int startIndex, int endIndex) 
                        - returns a new BitSet that consists of the bits
                        from 'startIndex' to 'endIndex'
                        
        13  int         hashCode()
                        - rerurns hash code for 'this' BitSet
                        
        14  boolean     interSects(BitSet bitSet)
                        - returns true if at least one pair of corresponding
                        bits within 'this' object and 'bitSet' are 1
                        
        15  boolean     isEmpty()
                        - returns true if all bits in 'this' BitSet are 0
                        
        16  int         length()
                        - returns no. of bits required to hold the contents
                        of the invoking BitSet. 
                        
                        - value is determined by location of the last 1 bit
                        NOTE: this means that a BitSet initialized with a 
                        specific size may not be the same as length()
                        
        17  int         nextClearBit(int startIndex)
                        - returns index of the next 0 bit starting from 
                        'startIndex'
                        
        18  int         nextSetBit(int startIndex)
                        - returns index of the next 1 bit starting from
                        'startIndex'
                        
        19  void        or(BitSet bitSet) 
                        - ORS the contents of 'this' BitSet object w/ that
                        specified by 'bitSet'
                        - result is placed in 'this' BitSet
                        
        20  void        set(int index) 
                        - sets bit specified by 'index'
                        
        21  void        set(int index, boolean v) 
                        - sets bit specified by 'index' to the value 
                        passed in 'v'
                            - true sets the bit
                            - false clears it
                            
        22  void        set(int startIndex, int endIndex) 
                        - sets bits from 'startIndex' to 'endIndex'
                        
        23  void        set(int startindex, int endIndex, boolean v)
                        - sets bits from 'startIndex' to 'endIndex' to the value 
                          passed in 'v'
                            - true sets the bit
                            - false clears it      
                            
        24  int         size()
                        - returns # of bits in 'this' BitSet 
                        (regardless of what their values are!) 
                            - remember size v. length in arrays 
                            
                                SIZE = allocated space
                                LENGTH = used space
                                
        25  String      toString()
                        - returns the string equivalent of 'this' BitSet
                        
        26  void        xor(BitSet bitSet) 
                        - XORs the contents of 'this' BitSet object w/ that 
                        specified by 'bitSet'
                        - result is placed (overwrites) 'this' object            
                                                                                                
                     
                 
                                         

            
       
