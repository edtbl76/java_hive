FROM amazoncorretto:11.0.13-al2
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "hello.Application"]
