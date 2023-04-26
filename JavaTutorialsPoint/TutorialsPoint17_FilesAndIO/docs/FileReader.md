# FileReader Class

    - inherits InputStreamReader
    - used for reading Character Streams
    
### Constructors

    1   FileReader(File file) 
        - creates a new FileReader given the File to read from
        
    2   FileReader(FileDescriptor fd)
        - creates a new FileReader given the FileDescriptor to read from
        
    3   FileReader(String fileName) 
        - creates a new FileReader given the name of the file to read from.
        

### Methods

    1   public int          read()  throws IOException
                            - reads single char. 
                            - returns an int, which represents character that was
                            read.
                            
    2   public int          read(char[] c, int offset, int len)
                            - reads char into an array. 
                            - returns # of chars read
                            
                        