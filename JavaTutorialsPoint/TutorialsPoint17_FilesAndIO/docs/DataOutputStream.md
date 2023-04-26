# DataOutputStream

    - writes primitive data typ4s to an output source
    
### Constructors

    DataOutputStream out = DataOutputStream(OutputStream out);
    
### Methods

    1   public final void           write(byte[] w, int off, int len) throws IOException
                                    - writes "len" bytes to specified byte array "w"
                                    - starts writing at "off"
                                    
    2   public final int            write(byte[] b) throws IOException
                                    - writes cuyrrent # of bytes written to this 
                                    data output stream
                                    - returns total # of bytes written into the buffer
    
    3   public final void           writeBoolean() throws IOException
        public final void           writeByte() throws IOException
        public final void           writeShort() throws IOException
        public final void           writeInt() throws IOException
            
                                    - these methods write specific primitive data
                                    type(s) into the output stream as bytes
                                    
    4   public void                 flush() throws IOException
                                    - flushes data output stream
                                    
    5   public final void           writeBytes(String s) throws IOException
                                    - writes out the string to the underlying
                                    output stream as a sequence of bytes. 
                                    - each char in the string is written out, 
                                    in sequence, by discardings its high 8 bits.
                                    
                                 
    
