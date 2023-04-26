# PAIR

A Map is a collection of pairs designed to be operated on as a collection. 

A Pair is a single KV pair which can be operated on as its own structure. 

USE CASES
    - passing a pair to a method as an argument
    - methods that need to return 2 values in the form of a pair. 
    
    
## Apache commons lang

Pair, ImmutablePair and MutablePair.

Pair class is a pair consisting of two elements
- refers to the elements as LEFT and RIGHT
- implements Map.Entry interface where LEFT is the key and the RIGHT is value

Immutable Pair
- if mutable objects are stored in the pair, then the pair becomes mutable
- class is not final so subclasses could add undesirable behavior
- thread-safe (and stored objects are thread-safe)

## Java Tuple
- "an ordered collection of objects of different types"
- not necessarily a relationship to each other, but potentially they will
have some collective meaning (even if is just temporal)

- Java doesn't have built-in Tuple support

### Examples

    ["java", "1.11", "centos7"]
    ["Ed", 43, "MA", true ]
    
### Tuple vs. Lists
- tuple is an object that supports HETEROGENEOUS data (i.e. not of the same type)
- Lists and Arrays are restricted to holding the same TYPE

- tuples are considered the FASTEST data structure
- tuples consume the least amount of memory (and therefore space)

- tuples are IMMUTABLE
- Lists/Arrays are mutable. 

- tuples are fixed in size (like an array)

## org.javatuples
- supports tuples up to size 10
    - Unit (1)
    - Pair (2)
    - Triplet (3)
    - Quartet, Quintet, Sextet, Septet, Octet, Ennead, Decade
- two classes for easy representation of pairs
    - KeyValue
    - LabelValue
- FEATURE SET
    - type safe
    - Immutable
    - Iterable
    - Serializable
    - Comparable (implements Comparable)
    - implementing equals() and hashCode()
    - implementing toString()
    
### JavaTuples Common Operators
#### CREATE TUPLE
- Factory Method "with()" 
    - from each tuple class

    
    EX:
        Pair<String, Integer> pair = Pair.with("Ed", 43);
        
- Constructor

    
    EX:
        Pair<String, Integer> person = new Pair<>("Ed", 43);
        

- Collection or Iterable
    - we can create tuples from these structures AS LONG AS THEY HAVE
    THE EXACTLY CORRECT NO. OF OBJECTS

#### RETRIEVE VALUES


## io.vavr.Tuple2 
Tuple2 provides useful methods to work on data storefd init
- T1 _1()   
    - getter for first element
- T2 _2()
    - getter for 2nd element
- Tuple2 update1(T1 value)
    - setter for 1st element
- Tuple2 update2(T2 value) 
    - setter for 2nd element
- Map.Entry toEntry()
    - converts the tuple ot java.util.Map.Entry TUple
- Tuple2 swap()
    - swaps the element of this Tuple
- Tuple2 map(BiFunction mapper) 
    - maps the components of this tuple using a mapper function.
- int compareTo(Tuple2 that)
    - compare two Tuple2 instances
