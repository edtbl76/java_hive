before testing

Open 3 terminal windows and run

```aidl
cd spring-cloud-load-balancer/SayHello
```
# Execute
(in each terminal)
```aidl



./gradlew bootRun
SERVER_PORT=9092 ./gradlew bootRun
SERVER_PORT=9999 ./gradlew bootRun

```
