# Rx Microservice Failure
one of the hardest problems to solve in distribute architectures is to 
ensure local failure. 
    - We don't want to pass failure on to peer microservices.
    - failure isolation is one of the first class features of an ideal microservice architecture
    
## callback model + failure
The guts of this model are pretty simple. 
- event handlers receive an AsyncResult as a parameter which encapsulates the result of an async operation. 
    - i.e. if I sent an async http request to the server, then the server's response is captured by that AsyncResult
    parameter
- if the result is a successful operation, we consume it and push it on down the line for more processing, formatting or 
into a customer's enthusiastic little hands. 
- if the result is a FAILURE, then it is of type Throwable

        
Failure Example 1: NON-RX  if/else
        
        - client.get("/").as(BodyCodec.JsonObject())
            .send(ar -> {
                if (ar.failed()) {
                    Throwable cause = ar.cause();
                } else {
                    JsonObject json = ar.result().body();   
               }
            })
            
Failure Example 2: RX Java in subscribe()

        - client.get("/").as(BodyCodec.JsonObject())
            .rxRequest()
            .map(HttpResponse::body)
            .subscribe(
                result -> { /* success */ }
                error -> { /* crap! */ }
            );

Failure Example 3:
- if a failure occurs in one of the OBSERVED() streams, the error handler is
called. 
- this allows us to handle the error earlier than the subscribe() method

        - client.get("/").as(BodyCodec.JsonObject())
            .rxRequest()        <-- observed stream
            .map(HttpResponse::body)
            .onErrorReturn(throwable -> {
                // This is called if rxRequest() produces an error. 
                return new JsonObject;
            })
            .subscribe(result -> /* success */);
        

# TIMEOUTS
- timeouts are very handy in distributed systems. After some reasonable length of
time, it is heuristically likely that we aren't going to receive a response. Although, 
asynchronous systems aren't going to wait, there are other purposes (auditing, customer
notification, failure debugging, predictive analytics/self-healing) that make it prudent to note 
timeouts
- for microservices, timeouts provide FAILURE ISOLATION. If I timeout and return an error from MY service, it
ensures that I'm not passing a failure condition on to YOUR service. 
    - NOTE: you have several options of how to handle these circumstances
        - 1. retries. Forever? x N ?
        - 2. shut down, crash, die, exit gracefully? 
        - 3. continue operation in a degraded state? 
        
        
## TIMEOUT DOESNT ALWAYS MEAN FAILURE
this is important to consider. Timeouts mean "It has taken as long as I'm willing to wait" 
- it is possible that an operation MIGHT succeed beyond the timeout 

### TIMEOUT CASES
1. A sends message to B, but it is lost. 
    - B never gets message, so it won't know to respond
    - A times out
    - Operation never executed
1. A sends message to B
    - B gets the message and just fucking breaks
    - No response is sent
    - A times out
1. A sends message to B
    - B receives the message and EXECUTES ITS OPERATION
    - B sends a response to A
    - response to A is lost
    - A times out.
        
# RETRIES
When Retries are Good
- transient failures like timing issues, brief network crappiness (dropped packets, bad cable, NSA listening in)

When Retries are Bad
- significant problem that can't actually be handled (i.e. if it is a failure that can't be overcome ever, then retrying
4-5 times is just adding wasted load.)

When Retries are Really Fucking Bad
- look at case 3 above
- This is the CASE FOR IDEMPOTENCY. If the operation being sent to B is not idempotent, then we are going to do harm to 
the system. 
    - if the operation is idempotent, then it doesn't matter how many times we exec the operation, we'll get the same
    result. 
    - this prevents the system from harm and allows us to use retries more judiciously (to create a self healing
    system)
    - ALWAYS CHECK THAT YOUR SYSTEM CAN HANDLE REATTEMPTED OPERATIONS GRACEFULLY
    
    
## CAPPING RETRIES "ENOUGH IS ENOUGH"
Keep in mind that while retries increase the possibility for success in recoverable failures, it also extends the 
amount of time a customer has to wait before they get a response. At some point, we have to consider "how long is
too long"

## DON'T SOLVE CONGESTION WITH MORE TRAFFIC
UNDERSTAND your failure modes. If congestion is a problem, then retries are just going to exacerbate it. 