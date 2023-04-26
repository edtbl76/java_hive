# InetAddress Class

    - represents an IP address
    
    
### METHODS

    static InetAddress getByAddress(byte[] addr)
    
            - returns InetAddress obj given the raw IP address
            
    static InetAddress getByAddress(String host, byte[] addr)
    
            - creates an InetAddress based on 'host' (name) and IP address 'addr'
            
    static InetAddress getByName(String host) 
    
            - determines IP of host, given 'host'(name) 
            
    String getHostAddress()
    
            - returns textual/stringified IP Address
            
    String getHostName()
    
            - returns the hostname for this IP address
            
    
    static InetAddress InetAddress getLocalHost()
    
            - returns the localhost
            
    String toString()
    
            - converts IP address to a string
            
            