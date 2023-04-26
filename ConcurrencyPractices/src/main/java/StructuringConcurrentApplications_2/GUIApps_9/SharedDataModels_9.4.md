# 9.4 Shared Data Models

Swing presentation objects are all confined to the event thread.

### Structure of Simple GUI Objects
- all mutable state is held in the presentation objects
- there are only two threads
    - event thread
    - main thread
- enforcing thread confinement:
    - don't access data model or presentation components from main thread.
    
    
    NOTE: 
        More complex/sophisticated programs use OTHER threads to move data
        to/from persistence (FS/Database)
            - this prevents data shuttling from compromising responsiveness
            
### Loading Data
- Simple case (Data is only touched by event thread) 
    - data loaded by user
    - statically from a seed file
    - piped from another data source at app startup
- Less Simple
    - presentation model object is only a VIEW onto another data source
        - (db, file system, remote service, et al.)
    - data might be touched by multiple threads as it flows in/out of the
    app
    
    
    TreeControl Example - Displaying a remote file system tree
    
        - waiting to enumerate the entire FS before we display the tree
        control would take up WAY too much time and memory
        
        BETTER SOLUTION
            - lazy load the tree as nodes are expanded by the user
            - (enumeration of a single directory on remote storages can
            be time consuming...)
            
                so kick it to a background task
            
            - moving the data after the background enumeration task completes
            can be accomplished by:
            
                using a thread-safe tree model by "pushing" the data
                from the background task to the event thread. 
                
                This is accomplished by
                    - posting a task w/ invokeLater
                    - having event thread poll to check if the data is
                    available
                    
## 9.4.1 Thread-Safe Data Models

### Notes/Constraints/Invariants/Tenets/WhatDaFuckEva
- a thread-safe data model supports multi-threaded operations on data as long 
as it doesn't impact the responsiveness of the application based on the 
added blocking calls. 
- if the DATA MODEL supports "fine-grained concurrency"
    - the event thread + background threads should be able to share it
    w/o responsiveness conflicts. 
     
### Requirements
- thread-safe data models must generate events when the model is updated
    - these events are used to carry the data to the views. 
               
### Versioned Data Model
(Example: **CopyOnWriteArrayList**)
- may provide thread safety, consistency and good responsiveness
    - when we acquire an iterator for a Copy-on-write type collection,
    that iterator traverses collection "as it existed at creation time"

    
    Performance for copy-on-write collections provide good performance 
    only when 
    
        traversals GREATLY OUTNUMBER modifications
        
- for highly dynamic data (like tracking applications where positioning
information changes  a lot), this isn't going to perform very well, because
the data changes often.


#### Limitations
- it is VERY HARD to build versioned data structures that provide both
    - efficient concurrent access
    - do NOT retain old versions of data longer than needed.

    
    This approach should be considered only when other approaches aren't
    practical.
    
## 9.4.2 Split Data Models

### Definition/Characteristics
Split Model Design
- a program that has both
    - presentation domain data model 
    - application domain data model
       
    
    EXAMPLE: 
        
        - Swing Tabel model classes (like TableModel and TreeModel) are
        the official repository for the data to be displayed. 
        
        - these models are usually VIEWS of other objects that are managed by the the application


- "shared" model (App model) is thread-safe 
    - may be accessed by both EDT and application threads
- presentation model confined to event thread (EDT)
    - registers listeners w/ shared model so it can be notified of updates.
    - updated from SHARED MODEL by 
        - embedding a SNAPSHOT of the relevant state in the update message  
        - (or) having presentation model fetch data directly from SHARED MODEL when it receives an update event
           
#### Enriched Updates
- Description
    - presentation model registers a listener w/ shared model
    - when shared model data is updated, it sends a notification with a snapshot of the state embedded in the updated
- Works well when:
    - data model is small
    - updates are infrequent
    - structure of the two models (presentation and shared) are similar
- Incremental Alternative can be more efficient...
    - if data
        - is large
        - is frequently updated
    - or if one/both sides of the split model contain info that isn't visible to the other side.
                    
    
#### Incremental Updates
- This splits the difference from fully enriched (full snapshot) updates and fetching the data. 
- effectively serializes updates on the SHARED MODEL, then recreates them in the EDT against the presentation
model.
- allows for finer-grained information about what changed
    - CAN improve the quality of the display
    - EX: if a car drives across a screen, we only have to repaint the parts of the screen affected by the motion
    of the car. 
    

### When To Consider a Split Model Design

    This is a good design when the data model must be shared by more than one thread
    
    AND
    
    implementing a thread-safe data model would be "inadvisable" due to
    
        - blocking
        - consistency
        - complexity
        

                
