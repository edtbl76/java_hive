| Iterator | Spliterator | 
| --- | --- |
| Since Java 1.2 | Java 8 |
| iterates any collection class | iterates array, stream, list and set (no maps) |
| no parallel processing | supports parallel processing, through trySplit(). NOTE it is not thread safe |