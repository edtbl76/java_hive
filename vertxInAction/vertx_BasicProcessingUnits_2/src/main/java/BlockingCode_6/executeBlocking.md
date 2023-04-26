# ExecuteBlocking
make sure to see WorkerVerticle first. This example is associated w/ the offload class. 

Sometimes, it doesn't make sense to pull blocking code into worker verticles. 
- this can proliferate quickly, even for small tasks, leading to increases in cost

The alternative is to use ExecuteBlocking
- this takes some code and offloads the task to a worker thread
- then it puts the result back into the event loop using a result handler (callback)