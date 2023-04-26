# 64 Bit Computing
- use of processors that have widths of 64 bits/8 bytes for:
    - datapath widths
    - integer size
    - memory address widths 
- 64-bit CPU and ALU architectures have the following components based on that size
    - based on registers
    - address/data buses
- software
    - use of code w/ 64-bit virtual memory addresses
    
    
- can use 17.2 BILLION GB of RAM. 
    
# 32 Bit Computing
- same thing as above but w/ 32 bits/4 bytes

- limited to 4 GB of RAM 


# Java, 32 bit v 64 bit
1. 30-50% MORE heap is required on 64-bit vs. 32-bit
    - Object Headers
        - 64-bit JVM -> 12 bytes
        - 32-bit JVM -> 8 bytes
    - Object References
        - 64-bit JVM -> 4 or 8 bytes depending on JVM flags and heap size
        - 32-bit JVM -> 4 bytes. 
        
2. Longer GC pauses
    - building up more heap means there is more work to be done by GC while cleaning it up from 
    unused objects. 
        - w/o fine tuning and measuring, it is possible to introduce full GC pauses that
        span several minutes due to accumulating heaps > 12 - 16 GB
        
## ByteCode/Class Files
- Java Byte Code is independent of the bit depth of a system. 