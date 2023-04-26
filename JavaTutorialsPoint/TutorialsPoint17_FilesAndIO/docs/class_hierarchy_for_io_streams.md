# Class Hierarchy For Dealing with Input and Output Streams

    (NOTE: Object is the parent of InputStream and OutputStream, I just didn't 
    have room here to put the hierarchy all in one chart) 
    

    Object  (Input) 
        |
    InputStream----------------------------------------------------------------
        |                           |                   |                      |
    FilterInputStream       ObjectInputStream   ByteArrayInputStream    FileInputStream  
        |___________________________
        |                           |
    BufferedInputStream     DataInputStream
    
    
    Object  (Output) 
        |
    OutputStream----------------------------------------------------------------
        |                           |                   |                      |
    FilterOutputStream      ObjectOutputStream  ByteArrayOutputStream   FileOutputStream  
        |___________________________
        |                           |
    BufferedOutputStream     DataOutputStream