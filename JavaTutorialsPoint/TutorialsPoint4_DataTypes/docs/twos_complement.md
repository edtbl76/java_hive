# Two's Complement

This is a reference to primitive data types being referred to
as 'two's complement integerts

Two's Complement is a mathematical operation on binary numbers, 
especially used in computing as a method of 'signed number
representation'

The two's complement of an N-bit number is defined as its
complement w/ respect to 2^N

EX:

N = the number of bits in the binary number
n = the binary value of the decimal

DECIMAL     BINARY      TWO'S ((2^N - n)) 
0           000         000     8 - 0 
                                1000 - 000
                                        
1           001         111     8 - 1
                                1000 - 001 = 111 (7)
                                
2           010         110     8 - 2
                                1000 - 010 = 110 (6)
                       
3           011         101     8 - 3
                                1000 - 011 = 101 (5) 
                  
-4          100         100     8 - (-4)
                                1000 - 100 = 100 (4)
                                
-3          101         011     8 - (-3)
                                1000 - 101 = 011 (3)

-2          110         010     8 - 2
                                1000 - 110 = 010 (2) 
                                
-1          111         001     8 - 7
                                1000 - 111 = 001 (1)
                                
(4 bits)
0           0000        0000    16 - 0
                                10000 - 0000
                                
1           0001        1111    16 - 1  
                                10000 - 0001 = 1111 (15) 
                                
2           0010        1110    16 - 2
                                10000 - 0010 = 1110 (14) 

3           0011        1101    16 - 3
                                10000 - 0011 = 1100 (13)
                 
4           0100        0100    16 - 4
                                10000 - 0100 = 0100 (12)
                                
5           0101        1011    16 - 5
                                10000 - 0101 = 1011 (11)
                 
6           0110        1010    16 - 6
                                10000 - 0110 = 1010 (10)

7           0111        1001    16 - 7
                                10000 - 0111 = 1001 (9)
                                
-8          1000        1000    16 - 8
                                10000 - 1000 = 1000 (8)
                                
-7          1001        0111    16 - (-7)   
                                10000 - 1001 = 0111 (7)
                                
-6          1010        0110    16 - (-6)
                                10000 - 1010 = 0110 (6)
                                
-5          1011        0101    16 - (-5)
                                10000 - 1011 = 0101 (5)
                                
-4          1100        0100    16 - (-4)
                                10000 - 1100 = 0100 (4)
                                
-3          1101        0011    16 - (-3)
                                10000 - 1101 = 0011 (3)
                                
-2          1110        0010    16 - (-2)
                                10000 - 1110 = 0010 (2)
                                
-1          1111        0001    16 - (-1)
                                10000 - 1111 = 0001 (1)
                                
                                               
                                
(Subtraction in binary depends on the number of bits in 
the number.)

