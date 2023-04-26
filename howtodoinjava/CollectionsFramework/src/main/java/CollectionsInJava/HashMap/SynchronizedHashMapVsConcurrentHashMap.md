# SynchronizedHashMap vs. ConcurrentHashMap

| ConcurrentHashMap | SynchronizedHashMap |
| --- | --- |
| multiple threads can add/remove KV pairs (higher degree of concurrency) | only single thread can add/remove KV pairs |
| no need to lock the map to read a value | requires a lock for read operations|
| no ConcurrentModificationException if one thread tries to modify while another is iterating over it. Iterator reflects state of mape at time of creation | returns an Iterator, which fails-fast on concurrent modification |