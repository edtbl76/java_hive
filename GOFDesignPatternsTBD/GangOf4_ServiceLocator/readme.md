# SERVICE LOCATOR PATTERN

    - used when we want to locate various services using JNDI lookup
    - uses a caching technique to mitigate high cost of using JNDI lookup
        - caches svc object first time they are required.
        - future lookups are pulled from cache. 
        
    ENTITIES
    
        SERVICE
            - actual service will process request
            - reference to svc looked up by JNDI
        CONTENT/INITIAL CONTEXT
            - JNDI context carries reference to svc used for lookup
        SERVICE LOCATOR
            - single point of contact to get services by JNDI lookup
            caching the services
        CACHE
            - stores refs of svcs for reuse
        CLIENT
            - obj that invokes services via ServiceLocator
            
            
