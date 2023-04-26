# Iterator() Interface
All Java collection classes provide an iterator() method which returns an 
iterator instance
    - walks over elements in that collection.
    
## METHODS
hasNext()
- returns true if iteration has more elements remaining

next()
- returns next element in iteration
    - throws NoSuchElementException if iterator has no more elements
    
remove()
- removes the last element returned by the iterator from the underlying collection
- can only be called ONCE per next() call. 
    - this is intuitive. Think about it
- throws ConcurrentModificationException if underlying collection is modified while
iteration is in progress in any other way than by calling remove()
    - this is called a "FAIL-FAST" iterator
    - they fail fast and cleanly rather than risking arbitrary non-deterministic
    behavior at an undetermined time in the future.
    
forEachRemaining()
- performs the given task for each remaining element until all elements are
processed or an exception is encountered. 
- performed in order of iteration
- throws NPE if action isn't specified. 
