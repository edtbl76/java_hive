# File Class

    - represents files and directory pathnames in an abstract manner.
    - uses
        - file/directory cretion
        - file searching, deletion etc. 
        
### Constructors

    1   File(File parent, String child) 
            - creates a new File instance from a parent abstract pathname and
            a child pathname string
            
    2   File(String pathname) 
            - creates a new File instance by converting given pathname into an
            abstract pathname
            
    3   File(String parent, String child) 
            - creates a new File instance from parent and child pathname Strings
            
    4   File(URI uri) 
            - creaates a new File instance by converting the given file: URI into
            an abstract pathname
            
### Methods

    1   public String           getName()
                                - returns file/directory name denoted by
                                abstract pathname
                                
    2   public String           getParent()
                                - returns pathname String of 'this' abstract
                                pathname's parent
                                - returns null if pathname doesn't have a parent
                                directory
                                
    3   public  File            getParentFile()
                                - returns abstract pathname of this abstract
                                pathname's parent
                                - returns null if this pathname doesn't name
                                a parent directory
                                
    4   public String           getPath()
                                - converts abstract pathname into pathname String
                                
    5   public boolean          isAbsolute()
                                - returns true if abstract pathname is 'absolute'
                                
    6   public String           getAbsolutePath()
                                - returns absolute pathname string of this
                                abstract pathname
                                
    7   public boolean          canRead()
                                - returns true if
                                    - file system contains file denoated by
                                    'this' abstract pathname 
                                    AND
                                    - application is allowed to read from file
                                    
    8   public boolean          canWrite()
                                - returns true if
                                    - file system contains file denoted by
                                    'this' abstract pathname
                                    AND
                                    - application is allowed to write to the file
                                    
    9   public boolean          exists()
                                - returns true if
                                    - file system contains the file or directory
                                    denoted by 'this' abstract pathname
                                    
    10  public boolean          isDirectory()
                                - returns true if file denoted by 'this' 
                                abstract pathname both EXISTS and is a directory
                                
    11  public boolean          isFile()
                                - returns true if file denoted by 'this' 
                                abstract pathname both EXISTS and is a normal file
                                NOTE: 
                                    - a file is normal if 
                                        - it is not a directoru
                                        - AND satisfies other system-dependent criteria
                                    - non-directory file created by Java is
                                    always a normal file. 
                                    
    12  public long             lastModified()
                                - returns the time that the file (denoted by
                                'this' abstract pathname) was last modified
                                
                                - returns a long value representing posix epoch in
                                milliseconds
                                - returns 0L if file does not exist or I/O Error
                                occurts
                                
    13 public long              length()
                                - returns length of the file denoted by 'this' 
                                abstract pathname. 
                                - return value is 'unspecified' if pathname is
                                a directory.
                                
    14  public boolean          createNewFile() throws IOException
                                - creates a new empty file named by 'this' 
                                abstract pathname if the file does NOT already
                                exist
                                - returns true upon successful creation
                                
    15  public boolean          delete()
                                - requests that file/dir denoted by 'this' 
                                abstract pathname be deleted
                                - NOTE: directory must be empty to be deleted.
                                - returns true upon successful deletion
                                
    16  public void             deleteOnExit()
                                - same as delete(), but it occurs when JVM
                                terminates
                                
    17  public String[]         list()
                                - returns an array of Strings naming the files/dirs
                                in the dir denoted by 'this' abstract pathname
                                
    18  public String[]         list(FilenameFilter filter)
                                - returns an array of Strings naming the files/dirs
                                in dir denoted by 'this' abstract pathname that 
                                match the provided 'filter'
    
    (there is no 19?)
                                
    20  public File[]           listFiles()
                                - returns an array of abstract pathnames denoting
                                the files/dirs in the dir denoted by 'this' 
                                abstract pathname
    
    21  public File[]           listFiles(FileFilter filter)
                                - returns an array of abstract pathnames denoting
                                the files/dirs in the dir denoted by 'this' 
                                abstract pathname that match the provided
                                'filter'
                                
    22 public boolean           mkdir()
                                - creates dir named by 'this' abstract pathname
                                - returns true upon successful creation of dir
                                
    23 public boolean           mkdirs()
                                - creates dir named by 'this' abstract pathname
                                including any necessary but nonexistant parent
                                directories (i.e. an entire dir tree) 
                                - returns true upon successful creation of dir or
                                dirtree
                                
    24  public boolean          renameTo(File dest)
                                - renames file denoted by 'this' abstract pathname
                                - returns true upon successful renaming of file
                                
    25  public boolean          setLastModified(long time)
                                - sets last-mod time of the file/dir named by
                                'this' abstract pathname
                                - returns true upon successful alteration of
                                last-mod time
                                
    26  public boolean          setReadOnly()
                                - makes file/dir name by 'this' abstract pathname
                                read-only
                                - returns true only if RO is successfully set
                                
    27  public static File      createTempFile(String prefix, String suffix, 
                                File directory) throws IOException
                                
                                - creates new empty file in specified dir, 
                                using given prefix and suffix strings to
                                create file name. 
                                - returns an abstract pathname denoting
                                a newly-created empty file
      
    28  public static File      createTempFile(String prefix, String suffix) 
                                throws IOException
                                
                                - creates new empty file in default tmp dir, 
                                using given prefix and suffix strings to
                                create file name. 
                                - returns an abstract pathname denoting
                                a newly-created empty file
      
    29  public int              compareTo(File pathname) 
                                - compares 2 abstract pathnames lexicographically
                                - returns  
                                    - 0 (if arg is = to 'this' abstract pathname)
                                    - negative number
                                        - if abstract pathname is lexicographically
                                        < than arg
                                    - positive number
                                        - if abstract pathname is lexicographically 
                                        > than arg
                                        
    30  public int              compareTo(Object o)
                                - compares 'this' abstract pathname to another obj
                                - returns 
                                    - 0 if arg = 'this' abstract pathname
                                    - negative if 'this' abstract pathname is 
                                    lexicographically < arg
                                    - postive if 'this' abstract pathname is
                                    lexicographically > arg
                                    
    31  public boolean          equals(Object obj) 
                                - returns true if the arg is NOT null and is an
                                abstract pathname that denotes the same file/dir
                                as 'this' abstract pathname
                                
    32  public String           toString()
                                - returns pathname string of 'this' abstract
                                pathname
                                - same string as getPath()
    
                         
    
                                
                          
                                
    
                              
                                     
                                
    
                       

    