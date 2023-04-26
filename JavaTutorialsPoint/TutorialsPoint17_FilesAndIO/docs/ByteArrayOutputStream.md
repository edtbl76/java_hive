# ByteArrayOutputStream

    - creates a buffer in memory and all data sent to stream is stored
    in the buffer
    
### Constructors

    1       ByteArrayOutputStream()
            - creates a ByteArrayOutputStream having a default buffer of 
            32 bytes
            
    2       ByteArrayOutputStream(int a)
            - creates a ByteArrayOutputStream having a buffer of given size
            
### Methods

    1   public void             reset()
                                - resets # of valid bytes of 'this' 
                                ByteArrayOutputStream to 0
                                - DISCARDS all accumulated output in the stream.
                                
    2   public byte[]           toByteArray()
                                - creates a newly allocated byte array
                                - size is equal to current size of output stream
                                - contents of buffer are copied into it
                                - returns current contents of the output stream
                                as a byte array
                                
    3   public String           toString()
                                - converts buffer content into a string
                                - (Translation performed based on default
                                character encoding)
                                - returns String translated from buffer's content
                                
    4   public void             write(int w)
                                - writes specified array to output stream
                                
    5   public void             write(byte[] b, int off, int len)
                                - writes "len" # of bytes to output stream
                                - starts from "off"
    
    6   public void             writeTo(OutputStream outSt)
                                - writes ENTIRE content of this Stream to 
                                specified stream argument
