# Vert.x Clustering. 
![alt-text](/Users/emangini/IdeaProjects/vertxInAction/EventBus_3/src/main/java/ClusteringAndDistrEventBus_3/Screen Shot 2019-11-04 at 4.59.55 PM.png)
Cluster Manager provides the following:
- group membership and discovery
    - allows discovery new nodes
    - maintains current list nodes
    - detects when nodes go away

- shared data allows maps and counters to be maintained cluster wide
    - distributed locks may be useful for some forms of coordination between nodes
    
- subscriber topology allows knowing what event-bus destinations each node has interest to
    - useful for efficiently dispatching messages over distributed event-bus
    - we only send what is necessary. (no wasted movement!)