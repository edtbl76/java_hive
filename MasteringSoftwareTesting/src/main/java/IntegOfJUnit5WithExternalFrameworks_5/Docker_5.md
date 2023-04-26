# Docker

---
## Docker In A Nutshell

### LXC (Linux Containers)
- 2008
- provides complete "isolation execution" via
    - cgroups, kernel namespaces, chroot, etc.
    - super complicated, so... Docker.
    

### Docker
- obfuscates underlying complexity of resource isolation to allow independent containers
to run within a single Linux instance. 
  

- high-level API
    - used to pack/ship/exec any app as a container
    

#### Container
- bundles an app together w/ its dependencies. 
- multiple containers can run on same machine/share same OS w/ other containers
- a SINGLE container runs as an isolated process in user space. 
- no need for hypervisor (virtual machines)


![VMVsContainer](/Users/Edward/IdeaProjects/edu/MasteringSoftwareTesting/src/main/resources/images/VMVsContainer.png)


#### Components
- Docker Engine
  - responsible for creating/running containers
  - 3 major components
    - server impl'd as a daemon process
    - REST API
      - specifies the interfaces that programs use to
      chat w/ the daemon, and what its capabilities are
    - CLI client (*docker*) command
  

- Docker Hub
  - cloud service for distributing containers
  - TONS of public container images for download
    - and TONS of opportunity to get FUCKED by insecure
    software

---
## JUnit 5 Extension for Docker
- "junit5-docker" isn't maintained anymore.
- Look at DockerTest
  - i use the docker-java library to interact w/ the
  docker client and run commands and what not. 

---