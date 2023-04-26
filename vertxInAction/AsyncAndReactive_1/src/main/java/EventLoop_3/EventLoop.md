# EventLoop
- the benefit of an event-loop is that it is NonBlocking. 

- events are dispatched to a queue (in most cases we consider a NonBlocking Queue an "Event 
Stream" or "Event Store")
     - we can assign a single thread to process the events. 
     - if the thread blocks, it defeats the purpose of using the Event Loop.
     
 - the cool part is that since there is a thread dedicated for processing the events, we 
 can dispatch events from any number of threads w/o worrying about blocking. 