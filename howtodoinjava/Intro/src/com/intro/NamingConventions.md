# Packages

- group of words starting w/ all lowercase domain name (eg com, org, net)
- subsequent parts of package name may be different according to orgs own conventions


    Ex: com.google.search.common
    
# Classes

- NOUNS
- Title-Case, w/ the first letter of each separate word capitalized


    EX: 
        public class Employee {}
        public class SoftwareEngineer {}
        
# Interfaces

- Adjectives (generically speaking in Java this is always the case)
    - (occasionally they can be NOUYNs when they represent a FAMILY of classes e.g. List and Map)
- Title-Case, w/ the first letter of each separate word capitalized- 
    
    
    EX: 
        public interface Serializable {}
        public interface Clonable {}
        public interface Iterable {}
        
# Methods

- VERBS
- represent an action, so the name should CLEARLY state what that action is. 
    - avoid more than 2-3 words (and ideally use 1)
- CamelCase

    
    EX: 
        public Long getLicensePlate() {}
        public Integer getId() {}
        
# Variables

- camelCase
- short, but enough to describe their purpose. 
    - temp vars are OK to be abbreviated to a single character per Java specification
    - I DO NOT AGREE. 
    
    
    EX: 
        public Long id;
        public EmployeeDao employeeDao;
        private Properties properties;
        
# Constants

- UPPERCASE
- snake_case (words separated by an underscore)
- final modifier should always be used w/ constants

    
    EX:
        public final String SECURITY_TOKEN = "...";
        public final int INITIAL_SIZE = 101;
        
# GENERIC TYPES

- UPPERCASE SINGLE LETTERS
    - T for "TYPE"
    - E for "Collection Elements"
    - S for "Service Loaders"
    - K and V for "Key and Values", respectively in Maps
    
    
    EX:
        public interface Map <K, V> {}
        public interface List<E> extends Collection<E> {}
        Iterator<E> iterator() {}
        
# ENUMS

- UPPERCASE


    EX:
        enum Direction { NORTH, EAST, SOUTH, WEST }
        
# ANNOTATIONS

- Adjective, Verb or Noun based on requirements
- TitleCase


    EX: 
        public @interface FunctionalInterface {}
        public @interface Deprecated {}
        public @interface Documented {}
