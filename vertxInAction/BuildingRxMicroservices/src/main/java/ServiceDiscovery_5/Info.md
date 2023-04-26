# Service Discovery
a pattern that creates location transparency between microservices in a 
distributed system
- provides elasticity and dynamicity
    - consumers can call different instances of a microservice using a 
    round-robin strategy
    - between those invocations, the microservice could have been updated
    
## How it works
- each microservice announces how it can be invoked and its 
characteristics
    - including location, versions, security policies
- each microservice can WITHDRAW its announcements from the registry
- consumers looking for services can use specific types based on 
metadata in the service registry, or they can also be configured to use
the "best match"

## SERVICE REGISTRY (a.k.a Service Discovery Infrastructure)
- generic "store" for microservice announcements, typically provided by the
execution environment
![alt-text](/Users/emangini/IdeaProjects/vertxInAction/BuildingRxMicroservices/src/main/java/ServiceDiscovery/Screen Shot 2019-12-29 at 2.30.31 PM.png)


- Registry has to store "announcements" from services
- has to provide notifications of registration/deregistration events

## Client Side Service Discovery
Consumer looks for a service based on name and metadata in the service registry, selects that service and then 
uses it. 


Service Registry forms for Client Side Discovery
- distributed data structure
- dedicated infrastructure (i.e Hashicorp Consul)
- Inventory service (Apache Zookeeper, Redis)

## Server-Side Discovery
lets a Load Balancer, Router, Proxy, or API Gateway/Edge Service manage discovery for you
- consumers still search for services based on name + metadata
- it retrieves a VIRTUAL ADDRESS that represents the service it is trying to contact.
- requests to the service  are ROUTED to actual service
    - EX: Kubernetes w/ AWS ELB
![alt-text](/Users/emangini/IdeaProjects/vertxInAction/BuildingRxMicroservices/src/main/java/ServiceDiscovery/Screen Shot 2019-12-29 at 3.22.23 PM.png)

# Vert.X Service Discovery
![alt-text](/Users/emangini/IdeaProjects/vertxInAction/BuildingRxMicroservices/src/main/java/ServiceDiscovery/Screen Shot 2019-12-29 at 6.49.44 PM.png)
- Services are received by TYPES to get a configured SERVICE CLIENT that is ready to be used
- services can be
    - HTTP endpoint
    - event bus address
    - data source
    - et cetera