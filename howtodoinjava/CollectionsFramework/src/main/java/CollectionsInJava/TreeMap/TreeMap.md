# TreeMap
Similar to HashMap
- provides an efficient map to store KV pairs in sorted order. 
- Red-Black Tree-Based NavigableMap implementation

## HIERARCHY

    public class TreeMap<K,V>
        extends AbstractMap<K,V>
        implements NavigableMap<K,V>, Cloneable, java.io.Serializable 
    {
        // impl code
    }
    
## TREEMAP FEATURES
- stores KV pairs like HashMap
- allows only unique keys (dupes are impossible)
- does NOT support a null key
    - unlike other HashMap implementations which support a SINGLE null key
- supports multiple null values
- sorts keys
    - in sorted/natural order
    - using Comparator provided at map creation time
- provides GUARANTEED log(n) time for
    - containsKey()
    - get()
    - put()
    - remove()
- NOT SYNCHRONIZED 
    - to work in concurrent environment use
        - Collections.synchronizedSortedMap(new TreeMap())
- iterators are fail-fast

## CONSTRUCTORS
TreeMap()
- new, empty TreeMap using natural ordering of keys

TreeMap(Comparator c)
- new, empty TreeMap ordered according to given Comparator

TreeMap(Map map)
- new tree map initialized by contents of given map, w/ natural ordering

TreeMap(SortedMap map)
- new tree map initialized by contents and ordering of given collection

## METHODS
void clear()
- removes all KV pairs from map

int size()
- returns no. of KV pairs present in map

boolean isEmpty()
- returns true if map has no KV pairs

boolean containsKey(Object key)
- returns true if given key is in map

boolean containsValue(Object value)
- returns true if given value is in map

Object get(Object key)
- retrieves VALUE mapped by given key
    - returns null if map doesn't contain mapping for given key
    
Object remove(Object key)
- removes KV pair associated w/ given key, if present.

Comparator comparator()
- returns comparator used to order keys in this map
    - returns null if map uses natural ordering.
    
Object firstKey()
    - returns first (least) key currently in tree map
    
Object lastKey()
    - returns last (greatest) key currently in tree map
    
Object ceilingKey(Object key)
    - returns least key that is >= to given key (or null if no such key)
    
Object higherKey(Object key)
    - returns least key > given key 
    
NavigableMap descendingMap()
    - returns reverse order view of the mappings contained in map.
    
## USE CASES
- provides efficient method to store/retrieve data contained within in a sorted manner
    - where sorting methodology is more intricate/complex than insertion/access (i.e. 
    what is provided by LinkedHashMap)
    
## PERFORMANCE
- log(n) for most ops
    - add(), remove(), contains()
    - slower than HashMap (O(1) for the previous operationos)

- good memory mgmt performance 
    - doesn't maintain internal/backing array to store KV pairs
    
## Concurrency in TreeMap
- not synchronized/thread-safe. 

- for Concurrency use Collections.synchronizedSortedMap(new TreeMap())
