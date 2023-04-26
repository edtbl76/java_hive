# BEST PRACTICES
Writing Verticles
- is processing asynchronous events

Using the event-bus
- making verticles cooperate

## TRADEOFFS
Too many verticles
- introduces increasing overhead due to event-bus communication

Too few verticles
- results in poor bounded contexts, such that a single verticle does too much

## RECOMMENDATIONS
Bill Belichick Verticles
- You have one job!
- this is CRITICAL for scaling. 
    - each verticle can have a scalable number of instances (driven by workload)
    - Event-Bus comms make it transparent to user whether or not verticles are within the
    same JVM process or if they are across the network in another instance. 

Remember that verticles don't need to know about each other. They just need to agree
on destination (topic) names and message formats.


