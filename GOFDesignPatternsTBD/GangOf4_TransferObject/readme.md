# TRANSFER OBJECT PATTERN
    
    - used when we want to pass data w/ multiple attributes in one shot from
    client to server. 
    
        - TransferObject = ValueObject
        - it should be a JavaBean/POJO
            - getters + setters and is serializable so that it can be
            transferred over the network. 
        - NO BEHAVIOR (i.e. there are no verbs) 
            - server side business class fetches data from the DB (via DAO),
            and fills the pojo an sends it to the client or pass-by-value. 
        - read-only for client. 
        - in order to send data to the server, the client has to 
        create its own transfer object and pass it to the server to
        update values in the db in a single shot. 
        
            
            -NOTE:
                there is much debate over this terminology. 
                
                technically speaking Serialization is part of the java language
                it isn't a "framework" by strict definition, so the lax nature
                of POJO definition applies. 
                
                At the same time, it is a JavaBean, because it is getters/setters
                and is serializable. 
                
        ENTITIES
            
            BUSINESS OBJECT
                - business service that fills TO/VO w/ data
            
            TRANSFER OBJECT/VALUE OBJECT/BEAN/POJO
                - Simple POJO/Bean having methods to set/get only
                
            CLIENT
                - requests/sends TO to BO
                
                
                
        - 
