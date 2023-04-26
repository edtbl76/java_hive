# INTERCEPTING FILTER

    - used when we want to do pre/post processing w/ request or response of
    application
    
    - Filters are defined and applied on the request before passing the request
    to the actual target application. 
    
    - Filters can do authN/authZ log or tracking of request and then pass
    requests to corresponding handlers. 
    
    ENTITIES
        
        FILTER
            - filter which performs certain task(s) pre/post-exec of request by
            request handler
            
        FILTER CHAIN
            - carries multiple filters and helps to exec them in defined order
            on target
            
        TARGET
            - the request handler
            
        FILTER MANAGER
            - manages filters and filter chain
            
        CLIENT
            - object who sends request to target. 