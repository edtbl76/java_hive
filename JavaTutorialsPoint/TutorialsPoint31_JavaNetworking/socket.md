# SOCKET

    java.net.Socket
    
### CONSTRUCTORS

    public Socket(String host, int port)
            throws UnknownHostException, IOException
            
            - attempts to connect to 'host':'port'. 
                - if no exception is thrown, it is expected that the client has
                successfully connected to the server
                
    public Socket(InetAddress host, int port) throws IOExeption
    
            - same as previous constructor, but this uses an InetAddress object
            instead of a hostname string
            
    public Socket(String host, int port, InetAddress localAddress, int localPort)
            throws IOException
            
            - same as previous constructor, but specifies the local IP and port to
            create the socket rather than allowing it to be dynamically allocated
            
    public Socket(InetAddress host, int port, InetAddress localAddress, int localPort)
            throws IOException
            
            - same as above, but uses an InetAddress obj instead of a hostname String
            
    public Socket()
        
            - default (unconnected) socket. 
            - you have to use the connect() method in order to connect this socket
            to a server. 
            
### METHODS

    public void connect(SocketAddress host, int timeout) throws IOException
    
            - connects socket to 'host' w/ a timeout of 'timeout'
            - NOTE: this is only needed if you use the default (unconnected) 
            constructor
            
    public InetAddress getInetAddress()
    
            - returns an InetAddress object w/ the IP Address of the socket at the
            other end of the connection
            
    public int getPort()
    
            - returns port the socket is bound to on the remote side of the connection
            
    public int getLocalPort()
    
            - returns port the socket is bound to on the local side of the connection
            
    public SocketAddress getRemoteSocketAddress()
    
             - returns address of the remote socket
             
     public InputStream getInputStream() throws IOException
     
            - returns input stream of the socket. 
            - (input stream is connected to output stream of REMOTE socket) 
            
    public OutputStream getOutputStream() throws IOException
    
            - returns output stream of the socket
            - (output stream is connected to input stream of REMOTE socket) 
            
    public void close() throws IOException
    
            - closes socket, which makes the Socket object no longer
            capable of connecting again to a server .
         