#CLASSPATH

parameter that tells the JVM where to look for classes and packages(jar)

## set classpath as ENV

setting classpath as an ENVIRONMENTAL VARIABLE is usually advised when you have a jar or set of jars
that are always required during application runtime. 

At runtime, APPLICATION CLASS LOADER (the one that runs third) scans jar files and classes at the
specified path(s) in this variable. 

The advantage of an ENV, is that you can specify multiple file system paths, allowing you to leave the 
jars where they were to begin with as opposed to moving them out of some path that makes intuitive sense
for them to remain

MULTIPLE JARS can be added by using the OS-specific delimiter
    
        WINDOWS ;
        UNIX :
        
You can also add all jars from a specific directory by using the *.jar syntax. If you want to all
jars and classfiles, you can just specify the '*'  wildcard without the .jar file extension. 

## set classpath from CLI

This can be achieved by using the '-classpath' argument to set the classpath from the console. 