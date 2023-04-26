# Directories

    - a directory is a type of File which can contain a list of other files and dirs
    
    - File() objects use/manage dirs by:
        - creating dirs
        - list down files available in a dir
        
        
### Creating Dirs

    - mkdir()
        - creates a dir, return true if dir is successfully created. 
        - Failure:
            - indicates that path specified in File() obj already exists
            - dir can't be created because entire path doesn't exist yet
            - permissions ?
            
    - mkdirs()
        - same as mkdir(), but it creates the target directory and all of the
        parents specified in the path if they do not otherwise exist. 