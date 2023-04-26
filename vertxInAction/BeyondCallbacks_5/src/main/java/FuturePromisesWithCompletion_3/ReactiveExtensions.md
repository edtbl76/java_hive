# Reactive Extensions
1.) Observing event or data streams 
- i.e. observing an incoming HTTP request

2.) Composing OPERATORS to transform streams
- i.e. merge multiple HTTP request streams as one

3.) SUBSCRIBING to streams and reacting to events/errors

## Reactive Type Table

| Type | Description | Example | 
| --- | --- | --- | 
| Observable<T> | A stream of events of type T. No Back-pressure support | Timer events, observable source where we can't apply back pressure (i.e. GUI events) |
| Flowable<T> | a stream of events of type T, where back-pressure can be applied | Network data/File System inputs |
| Single<T> | a source that emits exactly one event of type T | Fetching an entry from a data store by key |
| Maybe<T> | a source that may emit one event of type T, or None | Fetching an entry from a data store by key, where the key might not exist |
| Completable | a source that notifies of some action having completed, but no value is being given | Deleting files |

## HOT vs. COLD
HOT SOURCE 
- is a source where events are being emitted regardless of whether or not there are subscribers
- You only get the events that were emitted after you subscribe to the publisher

COLD SOURCE
- events are only emitted once the first subscriber connects and subscribes. 
- you can get ALL events ever created. 