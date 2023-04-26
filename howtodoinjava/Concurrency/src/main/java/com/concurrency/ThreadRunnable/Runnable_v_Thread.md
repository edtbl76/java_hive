# RUNNABLE vs. THREAD
- Runnable INTERFACE (implements)
- Thread CLASS (extends)

## Runnable Interface

    public class DemoRunnable implements Runnable {
        public void run() {
            // Code
        }
    }
    
    // Start a new thread w/ following call.
        
        new Thread(new demoRunnable()).start()
        
## Thread Class

    public class DemoThread extends Thread {
        public DemoThread() {
            super("DemoThread");
        }
        public void run() {
            // Code
        }
    }
    
    // start a new thread w/ following call
   
## DIFFERENCES

### RUNNABLE
- preferred way to implement a new thread
    - like any interface, you just give it a contract. 
    - we aren't specializing/modifying the Thread's behavior, therefore we recommend
    COMPOSITION.
- instantiating interfaces creates cleaner separation between code and implementation of
threads.
- makes classes more flexible. 

### THREAD
- Since java only supports single inheritance, you can only extend one class.
- not as flexible
    - if you extend Thead, then the action you are performing is always going to be in a
    Thread. 

