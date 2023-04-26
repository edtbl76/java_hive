# ListIterator interfaces
- extends Iterator interface
- applicable only for LIST implemented classes
- supports ANY CRUD operation
- bidirectional
    - forward/backward iteration
- there is no concept of a current element
    - cursor position is always between the element would be returned by a call
    to previous() and the element that would be returned by a call to next()
    
## METHODS
void add(Object o)
- inserts given element into the list

boolean hasNext()
- true if iterator has more elements while traversing FORWARD

boolean hasPrevious()
- true if iterator has more elements while traversinig BACKWARD

Object next()
- returns next element, and moves cursor forward

int nextIndex()
- returns index of element to be returned by a call to next()

Object previous()
- returns previous element, and moves cursor backward

int previousIndex()
- returns index of element to be returned by a call to previous()

void remove()
- removes last element to be returned by next() or previous()

void set(Object o)
- REPLACES last element returned by next()/previoous() w/ specified element.