# FileWriter Class

    - inherits from OutputStreamWriter
    - used for writing Character Streams
    
### Constructors

    1       FileWriter(File file) 
            - creates a FileWriter obj given a File obj.
    
    2       FileWriter(File file, boolean append) 
            - creates a FileWriter obj given a File object w/ boolean 
            indicating whether or not to append data written to an existing file
            
    3       FileWriter(FileDescriptor fd) 
            - creates a FileWriter obj associated w/ given file descriptor
            
    4       FileWriter(String filename) 
            - creates a FileWriter obj given a filename
            
    5       FileWriter(String filename, boolean append) 
            - creates a FileWriter obj given a filename w/ a boolean indicating
            whether or not to append data written to an existing file. 
            
### Methods

    1   public void         write(int c) throws IOException
                            - writes a single character
                            
    2   public void         write(char[] c, int offset, int len)
                            - writes a portion of an array of chars starting from
                            "offset" with a length of "len"
                            
    3   public void         write(String s, int offset, int len) 
                            - write a portion of a String, starting from "offset"
                            with a length of "len"
                            

            
    