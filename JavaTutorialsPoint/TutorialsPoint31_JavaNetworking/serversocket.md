# SERVER SOCKET CLASS

    java.net.ServerSocket
    
### CONSTRUCTOR 


    public ServerSocket(int port) throws IOException
    
                - attempts to create a server socket bound to 'port'
                - exception occurs if port is already bound to another app
                
    pubic ServerSocket(int port, int backlog) throws IOException
        
                - same as previous, but 'backlog' specifies how many
                incoming clients to store in a wait queue
                
    public ServerSocket(int port, int backlog, InetAddress address) 
                throws IOException
                
                - same as previous, but binds to local IP address 'address'
                - this is used when servers have multiple IP addresses, 
                allowing the server to choose which IP addresses it bind sto
                
    public ServerSocket() throws IOException
    
                - creates unbound Server socket. 
                - this requires the use of the bind() method to bind the socket
                to a port and being accepting requests. 
                
                
### COMMON METHODS

    public int  getLocalPort()
    
                - returns the port that 'this' ServerSocket (SS) is listening on
                - (this is useful if you had passed 0 as the port number in a 
                constructor, which allows the server to find an open port for you)
                
    public Socket accept() throws IOException
    
                - waits for incoming client. 
                - blocks until either a client connects to the server on the 
                specified port or the socket times out. 
                    - assumes that a time-out value has been set using 
                    setSoTimeout(). 
                    - NOTE: if there is no timeout, this will wait indefinitely
                    
    public void setSoTieout(int timeout) 
    
                - sets time-out value for how long the server socket waits for 
                a client during the accept() 
                
    public void bind(SocketAddress host, int backlog) 
    
                - binds socket to specified server and port in the
                    'SocketAddress' object. 
                    - use this if you use default SocketServer constructor .
                    
    
    