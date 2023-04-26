# Back-Pressure
- A mechanism for a CONSUMER of events to SIGNAL an events PRODUCER that is emitting
events at a faster rate that the consumer can handle them. 

- in reactive systems, it is useful to pause/slow-down a PRODUCER so that CONSUMERS
avoid accumulating unprocessed events in unbounded memory buffers leading to resource 
exhaustion

## HOW IT WORKS
Data buffers are read from a file stream (or some non-Blocking API) 
- passed to a handler which writes the contents of the buffer to some WriteStream
(i.e. HTTP Response)
- each HTTP Response would likewise write to the underlying TCP buffer
    - (probably in smaller chunks, but it could be directly)
    
NOTE: since write operations are Non-Blocking, a buffer is required.
(IN the case of a TCP buffer, it is highly likely that it will be holding onto
pending buffers to be written)

### PROBLEM
- reads are much faster than writes, so the write buffer is going to grow LARGE
- If we are sending a lot of data, our write buffers could easily be saturated. 

MORE BUFFERS = MORE MEMORY CONSUMPTION 

### ASYNC/NON-BLOCKING API
- since asynchronous/non-blocking IOs create independent streams, read and write
operations in those systems operate at different paces. 
    - THIS REQUIRES BUFFERING
    - BUFFERING INCREASES THE RISK OF OVERCONSUMPTION
    
### READ STREAM BACK-PRESSURE MANAGEMENT

| Method | Description | 
| --- | --- | 
| pause() | pauses a stream, preventing further data from being sent to handler |
| resume() | starts reading data again and sending it to the handler |
| fetch(long) | demands N number of elements to be read (at most). The stream must have been paused before calling fetch |


### WriteStream BACKPRESSURE Management

| Method | Description |
| --- | --- |
| setWriteQueueMaxSize(int) | Defines what the max write buffer queue size should be like before considered as full. (size in terms of Vert.x buffers to be written, not in terms of ACTUAL BYTES, because queued buffers may be of different sizes |
| boolean writeQueueFull() | Indicates when the write buffer queue size is full |
| drainHandler(Handler<Void>) | defines a callback when the write buffer queue has been DRAINED, typically when it is back to half its max size |
