# NIO Complexity
Check out Asynchronous Echo first. 

Got it??
The complexity of code in Non-Blocking implementations of NIO is so convoluted, that only 
a true sadist would ever use it. 

## DOWNSIDES OF NIO FOR NON-BLOCKING API
- implementation of most protocols requires multiple states for reading/writing data
- state machine complexity is significant
- no higher-level protocol-specific helpers. 
- no threading model

