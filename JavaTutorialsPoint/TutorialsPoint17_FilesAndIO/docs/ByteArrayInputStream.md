# ByteArrayInputStream

    - allows a buffer in memory to be used as an InputStream
    - input source is a byte array
    
### Constructors

    1       ByteArrayInputStream(byte[] a)
            - accepts a byte array as a parameter
            
    2       ByteArrayInputStream(byte[] a,int off,int len)
            - takes byte array as first parametert
            - off is an int telling program which byte
            to start reading from
            - len is an int telling progrtam how many 
            bytes to read. 
            
            
### Methods

    1   public int          read()
                            - reads next byte of data from InputStream
                            - returns an int as next byte of data
                            - returns -1 as EOF if no data
                            
    2   public int          read(byte[] r,int off, int len)
                            - reads byte array "r"
                            - starts from byte at "off"
                            - reads up to "len" number of bytes
                            - returns total # of bytes read
                            - returns -1 as EOF if no data
                            
    3   public int          available()
                            - returns int that gives # of bytes that can be
                            read from file input stream
                            
    4   public void         mark(int read)
                            - sets current marked position in the stream. 
                            - gives max limit of bytes that can be read before
                            marked position becomes invalid
                            
    5   public long         skip(long n)
                            - skips 'n' # of bytes from stream
                            - returns actual # of bytes skipped
                            
                            
                            
                        
                          