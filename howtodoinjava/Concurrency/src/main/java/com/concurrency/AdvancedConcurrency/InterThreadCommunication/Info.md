# Inter-Thread Communication
- JDK 1.5 introduced ExecutorService + BlockingQueue

## PIPED STREAMS
- FIFO ORDER (just like a pipe)

### PIPED READER
extends Reader class (used for reading char streams)
- read() method reads from the connected PipedWriter stream

### PIPED WRITER
- extends Writer class (used for writing char streams)
- "does all of the things which Reader class contracts"

### CONNECTIONS

    PipedWriter(PipedReader pr)         - Constructor
    PipedWriter.connect(PipedReader pr) - method
    
### NOTES
- You can't write to a pipe w/o having some sort of reader created and connected to it. 
- you can't switch to another reader once you are done writing to a pipe. 
- you can't read back from the pipe if you close the reader
    - (NOTE: you CAN however close the writer once its done writing)
- You can't read back from the pipe if the THREAD THAT WROTE TO IT ends. 
    - this means you can close the WRITER, but the thread that did the writing must 
    still be alive. 