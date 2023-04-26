# 8.5 Parallelizing Recursive Algorithms

### Review Exploitation of Parallelization (6.3)
- Final result was to isolate each image download as an iteration of a loop
- (loop iterations make good task boundaries)
- This yields good opportunities for parallelism

    
    Loop whose bodies contain nontrivial computation or perform 
    potentially blocking I/O are frequently good candidates for 
    parallelization, as long as the iterations are independent.
    
#### Sequential Loop To Parallel Loop
If a loop's iterations are independent and we don't need to wait for
all of them to COMPLETE before proceeding
- use an **Executor** to transform a sequential loop into a parallel loop

        
        Transforming Sequential Execution to Parallel Execution
        
            void processSequentially(List<Element> elements) {
                for (Element element : elements) {
                    process(element);
                }
            }
            
            void processInParallel(
                    Executor executor, List<Element> elements) {
                for (final Element element : elements) {
                    executor.execute(() -> process(element));
                }
            }

- Double Edged Sword
    - the call to processInParallel() is going to complete more quickly, 
    because we don't have to wait for completion. 
    - once all of the tasks are submitted to the work queue, then 
    processInParallel() returns, whereas processSequentially() has to 
    wait (at the very least) for all work but the last element to complete
- Waiting For Completion
    - this can be done by using **ExecutionService.invokeAll**
    - (use a **CompletionService** like **Renderer** to retrieve the 
    results as they become available)


    Sequential loop iterations can be parallelized when each iteration is
        
            independent of the others
            
                AND
                
            the work done in each iteration is significant enough to
            offset the cost of managing a new task. 
            
    Is the juice worth the squeeze? 
        - if it costs more to manage the task than it does to do the
        work...
        
### Loop parallelization of recursive designs
- Can be carried out in the same manner as Sequential (See above)
    - Optimal case is similar to sequential, where each iteration doesn't require
    the results of the recursive iterations it invokes (i.e. no backtracing) 
    
    
     Transforming Sequential Tail-recursion into Parallelized Recursion
     
     
        public<T> void sequentialRecursive(List<Node<T>> nodes, Collection<T> results) {
            
            for(Node<T> node : nodes) {
                results.add(node.compute());
                sequentialRecursive(node.getChildren(), results);
            }
        }
        
        public<T> void parallelRecursive(
                final Executor executor, List<Node<T>> nodes, final Collection<T> results) {
            
            for (final Node<T> node : nodes) {
                executor.execute(() -> results.add(node.compute));
                parallelRecursive(executor, node.getChildren(), results);
            }
                    
        }
#### NOTES
- sequentialRecursive()
    - Depth first traversal of a tree
    - calculation performed on each node, result stored in a collection
- parallelRecursive()
    - also depth first traversal of a tree
    - instead of computing result as each node is visited:
        - it submits a task to compute the result
    - when this returns, all nodes have been visited 
        - visitation is still sequential
        - calls to compute are executed in parallel. 
        - computations are queued to **Executor**
        
        
    Waiting for results to be calculated in parallel. 
    
        public<T> Collection<T> getParallelResults(List<Node<T>> nodes) 
                    throws InterruptedException {
                    
            ExecutorService executor = Executors.newCachedThreadPool();
            Queue<T> resultQueue = new ConcurrentLinkedQueue<T>();
            parallelRecursive(executor, nodes, resultQueue);
            
            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            return resultQueue;
            
        }
- Callers wait for results by:
    - creating an **Executor** specific to the traversal
    - calling it's **shutdown** and **awaitTermination** methods.
        
## 8.5.1 - Example - A Puzzle Framework
- USE CASE
    - solving puzzles that require finding a sequence of transformations 
        - initial state -> goal state. 
        - EX: "sliding block puzzles" (http://www.puzzleworld.org/SlidingBlockPuzzles)
    - "Puzzle"
        - combination of:
            - initial position/state
            - goal position/state
            - set of rules/constraints determining valid moves
                - part 1: computing list of legal moves from a given position
                - part 2: computing result of applying a move to a position
- (See ExampleCode.SlidingBlockPuzzles for code Examples)

### Puzzle Interface
- Type Parameters
    - P = position object
    - M = move object 

### Node<P, M> 
- (in ExampleCode.SlidingBlockPuzzles.SequentialPuzzleSolver)
- Example "node" that has been reached through X eries of moves. 
    - holds a reference to move M that created the position
    - holds a reference to previous Node<P, M>
    
    
    Following Links back from any Node, allows us to recreate the
    sequence of moves that led to current position. 
        - it also allows us to work back to other positions if we go
        down a dead end.
        
### SequentialPuzzleSolver
- Uses a HashSet to track positions (avoids infinite loop)
- depth first search of puzzle space
    - stores state of the search on the call stack
        - This means that the search is bounded by the stack size. 
- terminates when it finds a solution
    - not necessarily shortest/best

#### Benefits of Exploiting Concurrency
- calculate next move and evaluate **isGoal()** in parallel
    - process of evaluating one move is "mostly" independent from other moves
    - (MOSTLY, because the Set of visited nodes is shared mutable state)
- concurrent execution can reduce the "Time to solve"
    - (assuming that there are multiple CPUs/cores available to exploit)             
    
### ConcurrentPuzzleSolver
- uses inner class that extends **Node**, impls **Runnable** 
- uses ConcurrentHashMap instead of HashSet to track positions
    - thread safety
    - avoids check-and-set race condition, by using putIfAbsent for 
    conditional updates of a collection
- stores state of search using the internal work queue of the thread pool
(via **execute()** instead of the call stack)
     
#### Limitation Tradeoff
- SequentialPuzzleSolver used a Depth First Search on the call stack. 
    - this limits our search space to the size of the call stack. 
- ConcurrentPuzzleSolver uses breadth-first search
    - search space isn't bound by size of call stack
    - (we can still run out of MEM if set of positions to be searched
        or already has been searched are greater than the MEM available
        to the program)

#### Result-Bearing Latch
(See ExampleCode.SlidingBlockPuzzles.ValueLatch) <br>
PROBLEM 1:
- In order to stop searching, we have to be able to determine whether
    another thread has already found a solution yet. 
    
PROBLEM 2:
- if we want to accept the FIRST solution (i.e. the fastest) we
    need to update the solution IF and ONLY IF no other task/thread has
    found a solution

SOLUTION
- These requirements describe a Latch (Synchronizer -- Section 5.5.1) 
- Specifically -> **result-bearing latch**
    - BEST PRACTICES
        - easier/less error prone to use existing library classes to 
        build complex latches
        
(look inside the run() method in ConcurrentPuzzleSolver)
- SolverTask.run() first checks the state of the latch 
    - (This is the solution.isSet() call)
        - if TRUE, return immediately. 
- the main thread (the caller of solve()) reaches solution.getValue()
    - This makes the blocking **CountDownLatch.await()** call
    - CountDownLatch is set to terminate when it decrements by 1, indicating
    that the called thread has found a solution.
    
- since the latch has a count of 1, the first call to **setValue** is 
going to decrement the latch to 0, causing the thread to terminate. 
    - this unblocks the main thread from **getValue**
    - the net result is that the first thread to call setValue is the first
    one to solve the puzzle.

- the finally block in **solve()** includes the **Executor.shutdown()**
    call.
    - so the first thread to call setValue (i.e. find the solution), also
    shuts down the **Executor**
    - this prevents new tasks from being accepted (because they are no
    longer relevant)
    
    
- the caller should avoid RejectedExecutionException by setting
    the rejected execution handler to discard any submitted tasks
    once a solution has been found. 
    - unfinished tasks run to completion
        - (Alternative to short-circuit long running tasks is to 
        interrupt them instead of letting them finish)
    - any further attempts to execute new tasks will fail silently
        - this allows the executor to terminate, which in turn allows
        the JVM to terminate.  

#### Limitation 1: Doesn't handle "No Solution"
- The provided example doesn't have a default termination case (i.e. if
no solution is found)
    - if all options are exhausted, the call to **solve()** waits 
    indefinitely


    NOTE: This is a normal limitation of moving sequential problem spaces to
    be concurrent. 
        
            sequential problem spaces have a clear start/finish. 
            
##### Task Tracker Solution


    public class PuzzleSolver<P,M> extends ConcurrentPuzzleSolver<P,M> {
        
        private final AtomicInteger taskCount = new AtomicInteger(0);
        
        protected Runnable new Task(P position, M move, Node<P,M> node) {
            return new CountingSolverTask(position, move, node);
        }
        
        class CountingSolverTask extends SolverTask {
            CountingSolverTask(P position, M move, Node<P,M> node) {
                super(position, move, node);
                taskCount.incrementAndGet();
            }
            
            public void run() {
                try {
                    super.run();
                } finally {
                    if (taskCount.decrementAndGet() == 0) {
                        solution.setValue(null);
                    }
                }
            }
        }
    
    }
    
- we can count the number of active tasks/threads in the problem space. 
- once the count drops to zero, there are no more threads looking for a
solution, so we know the options have been exhausted. 
    - we brute force our way out of the algorithm by setting the value to 
    null
    
#### Limitation 2: Solution May Exist, but Takes Too Long To Find
- This is a variation of the previous problem. Maybe the solution DOES
exist, but the problem space is so large, then the time it takes to 
solve is longer than we can wait to get it. 

SOLUTION 1: (Time Limit) 
- We can provide a timed version of **ValueLatch.getValue()** that wraps
the timed version of **CountDownLatch.await()**
    - on timeout:
        - shutdown the **Executor**
        - declare FAILURE (i.e "no solution found within allowed time")
- This is a "General" solution that fits most search algorithms that exploit
concurrency


SOLUTION 2: (Bounded Search)
- We could provide a specific boundary to the search, such as how many 
positions to search, or how many failed attempts we'll allow, etc. 
- This is more or less a puzzle specific solution
    - the disadvantage is that it is specialized code
    - the advantage is that you can tailor the search parameters to your
    specific problem domain.
    
SOLUTION 3: (Choose Your Own)
- We can provide a cancellation mechanism and expose it to the client allowing
them to make their own decision about how/when to stop searching.