# Generic Wildcard
parameterized type is an instantiation of a generic type where at least ONE ARGUMENT is a wildcard
(it is a question mark)
- many use cases
    - type of a parameter
    - field
    - local variable
    - a return type... and MORE!

    EX:
        Collection<?<
        List<? extends
        Number<?
        Comparator<? super String?
        Pair<String,?>
        
    