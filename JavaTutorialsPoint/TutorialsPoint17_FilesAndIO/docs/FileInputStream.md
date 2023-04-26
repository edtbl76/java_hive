# FileInputStream

    - this stream is used for reading data from file(s) 
    (Remember this is a ByteStream so it is 1 byte at a time!)
    
### Example Constructors

    // create an input stream object and takes a file name string as an argument
    InputStream f = new FileInputStream(<filename>);
    
    // create a file object first, then pass that object to the stream
    File f = new File(<filename>);
    InputStream i = new FileInputStream(f);
    
### INPUT STREAM METHODS


    1   public void         close() throws IOException{}
                            - closes file output stream. 
                            - releases any system resources associated w/ file
                            - Throws: IOException
                            
    2   public void         finalize() throws IOException{}
                            - cleans up connection to file .
                            - ensures that close() of file output stream is called
                            and there are no more references to this stream. 
                            - Throws: IOException
                            
    3   public int          read(int r) throws IOException{}
                            - reads specified byte of data from InputStream. 
                            - returns an int if there is a byte of data. 
                            - returns -1 as EOF
    
    4   public int          read(byte[] r) throws IOException{}
                            - reads r.length butes from input stream into an array
                            - returns TOTAL number of bytes read. 
                            - returns -1 as EOF
                            
    5   public int      `   available() throws IOException{}
                            - returns int that represents the
                            number of bytes that can be read from input file stream
                            
                     
        
    