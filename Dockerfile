# Use OpenJDK 21 as base image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY target/service-b-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8081
EXPOSE 8081

# Set environment variables
ENV SPRING_PROFILES_ACTIVE=docker
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://service_b_bd:5432/service_b_bd
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres
ENV SPRING_RABBITMQ_HOST=rabbitmq
ENV SPRING_RABBITMQ_PORT=5672
ENV SPRING_RABBITMQ_USERNAME=guest
ENV SPRING_RABBITMQ_PASSWORD=guest

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8081/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

