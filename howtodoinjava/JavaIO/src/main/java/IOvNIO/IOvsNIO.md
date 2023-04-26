# I/O VS NIO

## STREAMS vs. BLOCKS
IO = stream-oriented
- 1+ data bytes at a time
    - input stream PRODUCES 1 byte
    - output stream CONSUMERS 1 byte
- can't move "back and forth"
    - this requires a buffer/cache
- supports chaining of filters to create a serial processing 
pipeline.


NIO = block-oriented
- each operation produces OR consumes a block of data in a single step
    - much faster than processing it in a stream.
- supports moving back/forth in buffer
    - more flexible processing. 
- DOWNSIDES
    - you have to check if the buffer has all the data you need in order
    to fully process it
    - you have to make sure that you don't overwrite unprocessed data in
    the buffer. 
    
## SYNCHRONOUS vs. ASYNCHRONOOUS
SYNCHRONOUS/BLOCKING
- threads must
    - block/wait until there is some data to read()
    - block/wait until it is fully written via write()
- uses polling or many connections
    
ASYNCHRONOUS/NON-BLOCKING
- thread doesn't wait for data to be present when issuing read()
- thread doesn't wait until data is written to finish a write()
    - in both cases, single thread can manage multiple channels of I/O
    - by NOT WAITING, threads can perform I/O against other channels 
- uses a SELECTOR
    - register interest in various I/O events
    - selector informs you that events have occurred.
    
## TRADEOFFS
NIO allows you to manage multiple channel using fewer (sometimes only 1!)
threads. 
- DOWNSIDES
    - parsing data is somewhat more complex than blocking stream w/ 
    standard IO
    
Standard IO is better for few connections and HIGH B/W, where we are
sending a lot of data at a time. 

NIO is better almost as a performance optimization or for scale. 
    