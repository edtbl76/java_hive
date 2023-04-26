# JAVA NETWORKING

    - java.net package
        - contains collection fo classes + interfaces that provide low-level comm 
        detail
        
        - support for 2 common network protocols
        
            TCP (Transmission Control Protocol) 
                - allows for reliable comms between 2 apps. 
                - usually used over IP (hence TCP/IP)
                
            UDP (User Datagram Protocol) 
                - connectionless protocol that allows for packets of 
                data to be transmitted between apps (best effort) 
                
### SOCKET PROGRAMMING

    - a 'Socket' provides the communication mechanism between 2 computers using TCP
    
        a client creates a socket on its end of the comm and attempts to
        connect that socket to a server.
        
        when connection is made, the server creates a socket object on its end
        of the comm. 
        
        client + server can now communicate by write to/reading from the socket
        
    - java.net.Socket class represents a socket
    
    - java.net.ServerSocket class provides a mechanism for server program to 
        listen for clients and establish connections w/ them
        
        
    ESTABLISHING TCP CONNECTION BETWEEN 2 SYSTEMS USING SOCKETS
    
        - server instantiates ServerSocket obj, noting the port # comms are to 
        occur on
        
        - server invokes ServerSocket.accept() method, which waits for a clinet
        to connect to server at gfiven port.
        
        - client instantiates a Socket obj, specifying the server name and port #
        to connect to
        
        - constructor of Socket class attempts to connect client to server:port
        - if comms are established, the client now has a Socket object capable of
        chatting w/ the server. 
        
        - on server side, accept() method returns a reference to a new socket on 
        the server that is connected to the client's socket.
        
        
        Once connections are established, comms can occur using I/O streams 
        
        - each socket has both an OutputStream and an InputStream (duplex baby!) 
        
        Client OutputStream ----> Server InputStream
        Client InputStream  <---- Server OutputStream
        
        