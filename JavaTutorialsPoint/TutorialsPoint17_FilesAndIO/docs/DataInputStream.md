# DataInputStream

    - used in context of DataOutputStream
    - primarily used to read PRIMITIVES
    
### Constructors

    InputStream in = new DataInputStream(InputStream in);
    
### Methods

    1   public final int        read(byte[] r, int off, int len) throws IOException
                                - reads up to "len" bytes from input stream into
                                an array of bytes
                                - starts from byte at 'off'
                                - returns total # of bytes read
                                - returns -1 as EOF if no data
                                
    2   public final int        read(byte[] b) throws IOException
                                - reads some bytes from inputstream and stores
                                it to the byte array
                                - returns total # of bytes read
                                - returns -1 as EOF if no data
                                
    3   public final Boolean    readBoolean() throws IOException
        public final byte       readByte() throws IOException
        public final short      readShort() throws IOException
        public final Int        readInt() throws IOException
        
                                - these methods read bytes from contained
                                InputStream
                                - returns next TWO BYTES of InputStream as
                                specific primitive data type
                                
    4   public String           readLine() throws IOException
                                - reads next line of text from input stream. 
                                - reads successive bytes, converting each byte
                                separately into a character until it encounters
                                a line terminator or EOF
                                - returns read chars as a String