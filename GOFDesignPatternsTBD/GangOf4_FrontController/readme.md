# FRONT CONTROLLER PATTERN

    - used to provide a centralized request handling mechanism so that all requests 
    are handled by a singel handler. 
    - can do authN/authZ, logging or tracking of request to corresponding
    handlers.
    
        ENTITIES
        - FRONT CONTROLLER
            - single handler for all types of requests coming to the 
            application ( web or desktop based.. HAHAHA who writes for
                            desktops anymore!)
                            
        - DISPATCHER
            - optional object used by the Front Controller which can
            dispatch the request to the corresponding specific handler
            
        - VIEW
            - object for which requests are made on behalf of