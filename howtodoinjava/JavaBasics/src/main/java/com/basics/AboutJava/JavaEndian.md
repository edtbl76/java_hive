# ENDIAN-NESS
- direction of bytes in a word within CPU architecture

    
    Little Endian:
        - Less Significant byte comes before more significant byte in memory. 
        - "Natural Order"
        
    Big Endian:
        - Most Significant byte comes before less significant byte in memory
        - "Reverse Order"
        - "Network order"
        
    
    EX: 32 bit Hex 0x45679812
    
        Address         00  01  02  03
        Little Endian   12  98  67  45
        Big Endian      45  67  98  12
        
- difference in "endian-ness" can be a problem when transferring data between systems
        
## Java Endian is Big Endian
- benefit is that all Java applications are always Big Endian regardless of the application
and/or platform. 
- disadvantage is that other languages (like C) are Little Endian. 
