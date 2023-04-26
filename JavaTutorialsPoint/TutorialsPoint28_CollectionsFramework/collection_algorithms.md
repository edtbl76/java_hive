# Collection Algorithms

    static int      binarySearch(List list, Object value, Comparator c)
    
                        - searches for 'value' in the 'list' according to 'c'
                        - returns position of 'value' in 'list' or '-1' if not found
                                
    static int      binarySearch(List list, Object value) 
    
                        - search for 'value' in 'list' 
                        - (list MUST be sorted) 
                        - returns position of 'value' in 'list' or '-1' if not found
                        
    static void     copy(List list1, List list2)
        
                        - copies elements of 'list2', to 'list1'
                        
    static Enumeration  
                    enumeration(Collection c)
                    
                        - returns an enumeration over 'c'
                        
    static  void    fill(List list, Object obj)
    
                        - assigns 'obj' to each element of 'list'
                        
    static  int     indexOfSubList(List list, List subList) 
        
                        - searches 'list' for first occurence of 'subList'
                        - returns index of first match or '.1' if no match is found
                        
    static  int     lastIndexOfSubList(List list, List subList)
    
                        - searches 'list' for last occurence of 'subList'
                        - returns index of first match or '.1' if no match is found
                        
    static  ArrayList   
                    list(Enumeration enum) 
                        
                        - returns an ArrayList that contains the elements of 'enum'
                        
    static Object   max(Collection c, Comparator comp)
    
                        - returns max element from 'c' determined by 'comp'
                        
    static Object   max(Collection c) 
    
                        - returns max element from 'c' as determined by natural
                        ordering 
                            - collection doesn't need to be sorted
                            
    static Object   min(Collection c, Comparator comp)
    
                        - returns min element from 'c' determined by 'comp'
                        
    static Object   min(Collection c) 
    
                        - returns min element from 'c' as determined by natural
                        ordering 
                            - collection doesn't need to be sorted
                            
    static List     nCopies(int num, Object obj) 
    
                        - returns 'num' copies of 'obj' contained in an immutable list
                        - 'num' must be >= 0
                        
    static boolean  replaceAll(List list, Object old, Object new)
    
                        - replaces all occurences of 'old' w/ 'new' in 'list'
                        - returns true if at least ONE replacement occurs
                        
    static void     reverse(List list) 
    
                        - reverses sequence in 'list'
                        
    static Comparator
                    reverseOrder()
                        
                        - returns a reverse comparator
                        
    static void     rotate(List list, int n)
    
                        - rotates 'list' by 'n' places to the right. 
                        - (use negative value for 'n' to rotate to the left) 
                        
    static void     shuffle(List list, Random r)
    
                        - randomizes/shuffles elements in 'list' by using 'r'
                        as source of random numbers
                        
    static void     shuffle(List list) 
    
                        - randomizes/shuffles elements in 'list'
                        
    static Set      singleton(Object obj) 
    
                        - returns 'obj' as immutable set. 
                        - (easy way to convert a single object into a set) 
                        
    static List     singletonList(Object obj) 
    
                        - returns 'obj' as an immutable list
                        - (easy way to convert a single object into a list) 
                        
    static Map      singletonMap(Object k, Object v) 
        
                        - returns 'k' and 'v' as an immutable map. 
                        - (easy way to convert a single KV pair into a map)
                        
    static void     sort(List list, Comparator comp)
    
                        - sorts elements of 'list' as determined by 'comp'
                        
    static void     sort(List list) 
    
                        - sorts elements of 'list' as deteremined by their
                        natural ordering
                        
    static void     swap(List list, int idx1, int idx2)
                        
                        - exchanges elements in 'list' at indexes 'idx1' and 'idx2'
                        
    static Collection
                    synchronizedCollection(Collection c) 
                    
                        - returns a thread-safe collection backed by 'c'
                        
    static List     synchronizedList(List list) 
    
                        - returns a thread-safe list backed by 'list'
                        
    static Map      synchronizedMap(Map map) 
    
                        - returns a thread-safe map backed by 'map'
                        
    static Set      synchronizedSet(Set s) 
    
                        - returns a thread-safe set backed by 's'
                        
    static SortedMap   
                    synchronizedSortedMap(SortedMap sm) 
                    
                        - returns a thread-safe sorted set backed by 'sm'
                        
    static SortedSet
                    synchronizedSortedSet(SortedSet ss) 
                    
                        - returns a thread-safe sorted map backed by 'ss'
                        
    static Collection
                    unmodifiableCollection(Collection c) 
                    
                        - returns an unmodifiable Collection backed by 'c'
                        
    static List     unmodifiableList(List list) 
        
                        - returns an unmodifiable List backed by 'list'
                        
    static Map      unmodifiableMap(Map map) 
    
                        - returns an unmodifiable Map backed by 'map'
                        
    static Set      unmodifiableSet(Set s) 
    
                        - returns an unmodifiable Set backed by 's'
                        
    static SortedMap    
                    unmodifiableSortedMap(SortedMap sm) 
                    
                        - returns an unmodifiable SortedMap backed by 'sm'
                        
    static SortedSet    
                    unmodifiableSortedSet(SortedMap ss) 
                    
                        - returns an unmodifiable SortedSet backed by 'ss'
                        
    
                       
                        
                    