# ---------- Build Stage ----------
FROM maven:3.9.9-eclipse-temurin-20 AS build

WORKDIR /app

# Copy project files
COPY . .

# Build jar
RUN mvn clean package -DskipTests


# ---------- Run Stage ----------
FROM eclipse-temurin:20-jdk-jammy

WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (Spring Boot default)
EXPOSE 8080

# Run application
ENTRYPOINT ["java","-jar","app.jar"]
