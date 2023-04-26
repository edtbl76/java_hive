# BUSINESS DELEGATE PATTERN

    - used to decouple the presentation tier and business tier. 
    - reduce communication or remote lookup functionality to business tier codee
    in presentation tier code. 
    
        BUSINESS TIER ENTITIES
        
        - client 
            - presentation tier code (JSP, servlet, UI Java) 
            
        - Business Delegate
            - single entry point class for client entities to provide access 
            to Business Service methods
            
        - LookUp Service
            - lookup svc obj is repsonsible to get relative business implementation
            and provide business obj access to business delegate obj
            
        - Business Service
            - Business Service interface
            - COncreate classes implement this business service to provide actual
            business implementation logic