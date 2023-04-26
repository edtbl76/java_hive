# Exceptions
CHECKED EXCEPTIONS
- must be specified in throws clause of a method or caugh tiinside them
UNCHECKED EXCEPTIOINS
- don't need to be specified or caught. 

# run() exceptions
- inside Thread.run() we have to catch exceptions, because run() doesn't accept a throws clause
- default behavior here for unchecked exceptions
    - dump stacktrace to console and/or log it to file/stream
    - bail out of program.