# LinkedHashMap
This is a specialized implementation of HashMap that MAINTAINS THE ORDER OF ELEMENTS
INSERTED INTO IT.

## HEIRARCHY


    public class LinkedHashMap<K,V> 
        extends HashMap<K,V>
        implements Map<K,V>
    {
        // implementation code
    }
    
## FEATURES
- stores KV pairs similar to HashMap
- contains ONLY unique keys (no dupes allowed!)
- NULL
    - one null key (due to no dupes allowed)
    - many null values
- maintains the order of K,V pairs inserted to it by adding elements to its
backing structure
    - backing structure = DOUBLY-LINKED LIST
    
## ACCESS ORDERING
- sorted on the basis of access order "last time they were accessed" using any method of LINKEDHASHMAP. 

### METHODS:
- put()
- putIfAbsent()
- get()
- getOrDefault()
- compute()
- computeIfAbsent()
- computeIfPresent()
- merge()

Anything else won't update the last access time, and will therefore not impact the ordering. 

### HOW IT WORKS
- keys are sorted from least recently accessed to most recently accessed. 
- THIS BUILDS A DEFAULT LRU CACHE. 

## CONSTRUCTORS
LinkedHashMap()
- default implementation
    - initial capacity (16 entries)
    - load factor of 0.75
    
LinkedHashMap(int capacity)
- uses specified capacity w/  default load factor

LinkedHashMap(Map map)
- initializes a LinkedHashMap w/ another Collection of type Map<>

LinkedHashMap(int capacity, float fillRatio) 
- uses specified capacity and load factor

LinkedHashMap(int capacity, float fillRatio, boolean Order)
- uses specified capacity and load factor
- last parameter is 
    - access order (when true)
    - insertion order (when false) - DEFAULT
    
## METHODS
void clear()
- removes ALL KV pairs from the map

int size()
- returns no. of KV pairs in the map

boolean isEmpty()
- returns true if there are no KV pairs.

boolean containsKey(Object key)
- true if specified key is in the map

boolean containsValue(Object value)
- true if a specified value is mapped to at least one key in the map

Object get(Object key)
- retrieves value mapped by specified key

Object remove(Object key)
- removes entire KV pair for specified key, if present

boolean removeEldestEntry(Map.Entry eldest)
- returns true when map removes its eldest entry from the access ordered map.

## USE CASES:
It is a drop in replacement for HashMap (i.e. transparent for the most part)

Specifically, it is designed to be used when HashMap ORDER is important
- insertion order (i.e. tracking when it was added)
- access order (tracks most/least recently used)
    - this is a common mechanism for caching.
    - useful w/ LRUs when calling removeEldestEntry()
        - automatically dumps the most stale element. 
        
## PERFORMANCE
- only a slight perf. hit from HashMap
    (HashMap only maintains a linked list, while a LinkedHashMap
    maintains a DOUBLY linked list.)
    
## CONCURRENCY IN LINKEDHASHMAP
- like HashMap, it isn't thread safe. 
- Does NOT support ConcurrentHashMap, so the only supported way to manage concurrency
with a linkedhashmap is 
    - Collections.synchronizedMap(Map map)