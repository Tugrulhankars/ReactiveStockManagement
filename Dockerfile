FROM openjdk:17-jdk-slim
WORKDIR /src
LABEL authors="karsl"
COPY target/ReactiveStockManagement-0.0.1-SNAPSHOT.jar .
EXPOSE 81
ENTRYPOINT ["java", "-java","ReactiveStockManagement-0.0.1-SNAPSHOT.jar"]