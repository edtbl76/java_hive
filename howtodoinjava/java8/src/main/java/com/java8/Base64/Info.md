# BASE 64
- Base64 encoding converts binary data into 64 printable ASCII characters. 
- this is done to include binary data in email as well as BASIC HTTP authentication

## List of Base64 Characters

- INDEX 0 --> 25
    - 26 uppercase letters [A..Z]  
- INDEX 26 --> 51
    - 26 lowercase letters [a..z]
- INDEX 52 --> 61
    - 10 digits [0..9]
- INDEX 62 and 63
    - 2 symbols
    - NOTE: the last two symbols are usually "+"  and "/", but there are  6 different
    specifications 
        - ( + / )
            - PEM (RFC 1421) Deprecated
            - MIME (RFC 2045)
            -  "Base64_standard" (RFC 3548, then RFC 4648)
            - Radix-64 OpenPGP (RFC 4880)
            - Base64 for UTF-7
        - ( -  _ )
            - URL and Filename-Safe (RFC 4648 )
        - ( _ - )
            - Program Identifier variant 1 non-standard
        - ( + , )
            - IMAP mailbox names (RFC 3501)
        - ( .  _ )
            - YUI Library Y64 URL-Safe
            - Program Identifier variant 2 non-standard
        - ( ~ -)
            - Freenet URL Safe
        
- PADDING SYMBOL (=)
    - the = in Base64 is padding for empty data
