FROM eclipse-temurin:21-jdk
VOLUME /tmp
ARG JAR_FILE
COPY ./build/libs/fsse_project-1.0.0.jar Project_Backend.jar
ENTRYPOINT ["java","-jar","/Project_Backend.jar"]