# UUID 

Universally Unique Identifier 
-aka GUID (Globally Unique Identifier)
- 128 bit long identifier
- SPATIALLY and TEMPORALLY unique w/ respect to the space of all other UUIDs

- requires no central registration.

UUID generation algorithms support HUGE allocation rates
- 10 million per second per machine (could even support transaction IDs)

TYPES
- usually 5 groups separated by hyphens (36 chars[32 alphanum, 4 hyphens])
    - (8-4-4-4-12)
    
    
    xxxxxxxx-xxxx-Mxxx-Nxxx-xxxxxxxxxxxx
    

- M indicates the UUID version
    - holds a value that describes the TYPE of UUID
        - Version 1 
            - Time-Based UUID 
                - generated from a time and a node id
        - Version 2
            - DCE (Distributed Computing Environment) security
                - generated from identifier, time and a node id
        - Version 3 (MD5)
            - Name-Based
                - hashing of a namespace identifier and name
        - Version 4
            - // Good enough for most cases. 
            - randomly generated (random/pseudo-random)
        - Version 5 (Most recommended)
            - name-based using SHA1 (160 bits)
            - hashing of a namespace identifier and a name
            - NO JAVA IMPLEMENTATION
         
- N indicates the UUID variant
    - field that contains a value which identifies the layout of the UUID