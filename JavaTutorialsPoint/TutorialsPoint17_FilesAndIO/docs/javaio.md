# java.io Package

    - contains a wealth of classes (and subsequent methods) needed to perform 
    input and output (I/O) in Java. 
    
    
## Stream

    - a stream is a sequence of data. 
        
        InPutStream         used to read data from a source
        OutPutStream        used for writing data to a destination
        
    
### ByteStream

    - performs input/output of 8-bit bytes. 
    - FileInputStream + FileOutputStream.
    
### Character Streams. 

    - These are 2x as efficient as ByteStream, because they perform 
    input/output for 16-but unicode. 
    - FileReader + FileWriter
        (internally these actually use FileInputStream + FileOutputStream, but
        they "skip 2 stairs at a time")
        
### Standard Streams

    - (STDIN, STDOUT, STDERR)
    
    - Standard Input  (System.in)
        - used to feed data to user's program. 
        - "Usually fed by a keyboard"
        
    - Standard Output (System.out)
        - used to output data produced by user's program. 
        - "Usually output to a computer screen"
        
    - Standard Error (System.err) 
        - used to output error data produced by a user's program. 
        - "Usually output to a computer screen" 
        - (Hitches a ride w/ STDOUT) 