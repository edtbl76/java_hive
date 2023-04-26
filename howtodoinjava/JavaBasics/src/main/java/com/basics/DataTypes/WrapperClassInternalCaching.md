# Java Wrapper Classes Internal Caching

"new" keyword allocates objects in HEAP, so creating new objects is ALWAYS AN EXPENSIVE PROCESS.
- sometimes, having objects "ready made" can mitigate the performance hit of creating new objects.

## Wrapper Classes
- immutable
- like a string pool, they CAN (and already have) their own pool. 
- JDK provided wrapper classes stores a list of commonly used interfaces of its own type in a cache. 

### Integer Cache Demonstration

Has an "IntegerCache"inner class. 

    
    EX:
        Integer i = 10;
        Integer i = Integer.valueOf(10);
        
When a new int is assigned to an Integer (like above), an "already created" Integer instance is 
returned and its reference is stored in i
- NOTE: If you use the 'new' keyword, the internal cache is bypassed because you are explicitly 
asking for memory to be allocated on the heap. 
- NOTE: the cache is ONLY available in the two scenarios shown above. 


    EX:
        private static class IntegerCache  {
            private IntegerCache() {}
            static final Integer cache[] = new Integer[-(-128) + 127 + 1];
            static {
                for(int i = 0; i < cache.length; i++)
                    cache[i] = new Integer(i - 128);
            }
        }
        
        public static valueOf(int i) {
            final int offset = 128;
            if (i >=128 && i <= 127) {   // must cache
                return IntegerCache.cache[i + offset];
            }
            return new Integer(i);
        }
        
#### Modifying  Cache Size

    -Djava.lang.Integer.IntegerCache.high=2000
