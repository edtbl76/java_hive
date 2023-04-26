# HTTP Client API Changes
- pre Java 11
    - HttpUrlConnection insufficient
    - replaced w/ 3rd party (feature-rich)
        - Apache HttpComponents
        - OkHttp
        
- Java 9 Client enhancements
    - experimental feature of HttpClient implementation
    
- Java 11
    - HttpClient official release
    
## HttpClient USAGE
- Create instance of HttpClient (configure as needed)
- Create instance of HttpRequest (populate information)
- Pass request to client, perform request
    - receive/handle instance of HttpResponse
- process information (or fail) from HttpResponse

- Supports Asynchronous and Synchronous examples