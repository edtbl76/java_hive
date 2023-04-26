# FileOutputStream

    - used to create a file and write data into it. 
        - Stream will create the file if it doesn't exist before opening it for 
        output
        
        
### Constructors

    // This constructor takes a filename string to create the output stream
    
    OutputStream f = new FileOutputStream(<filename>);
    
    // This constructor takes a previously created file object to create
    // the output stream
    
    File f = new File(<filename>);
    OutputStream out = new FileOutputStream(f);
    
### Methods

    1   public void         close() throws IOException{}
                            - closes file output stream
                            - relases systems resources associated w/ the file
                            - throws an IOException
                            
    2   protected void      finalize() throws IOException{}
                            - cleans up connection to the file
                            - ensures that the close() method is called when there
                            are no more references to this stream
                            - throws an IOException
                            
    3   public void         write(int w) throws IOException{}
                            - writes specified byte to output stream
                            
    4   public void         write(byte[] w) throws IOException {}
                            - writes w.length bytes from the mentioned byte array
                            to the Output Stream
                            
                            