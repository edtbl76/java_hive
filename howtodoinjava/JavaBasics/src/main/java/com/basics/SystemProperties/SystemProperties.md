# Java System Properties
- set of keys/values maintained by Java for its operations. 

## JRE Properties

    java.home               JRE home directory
    java.library.path       JRE library search papth for native libs
                            - usually (but not always) taken from EV PATH
    java.class.path         JRE classpath
    java.ext.dirs           JRE extension lib path
    java.version            JDK version
    java.runtime.version    JRE version
    
## File Related Properties

    file.separator          symbol for file directory separation
                            - '\' for Windows
                            - '/' for Unix/Mac
    path.separator          symbol for separating path entries
                            - ';' for Windows
                            - ':' for Unix/Mac
    line.separator          symbol for EOL or newline
                            - "\r\n" for Windows
                            - "\n" for Unix/Mac
                            
## User Related Properties

    user.name               user name
    user.home               user's home directory
    user.dir                user's current working directory
    
## OS Related Properties

    os.name                 Operating System Name
    os.version              Operating System Version
    os.arch                 Operating System Architecture
    
##  Setting System Properties

    
    CLI VERSION
    
        java -Dcustom_key="custom-value"    application_launcher_class
        
    INSIDE CODE
    
        System.setProperty("custom_key", "custom_value");