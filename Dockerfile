# Etapa 1: Construcción del JAR
FROM gradle:8.7-jdk17 AS builder
WORKDIR /app

COPY . .
RUN gradle clean bootJar --no-daemon

# Etapa 2: Imagen final ligera
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copia el JAR desde la etapa anterior
COPY --from=builder /app/build/libs/*.jar app.jar

# Variables (Railway las inyecta automáticamente)
ENV JAVA_OPTS=""
ENV API_TOKEN=""
ENV EUREKA_URL=""
ENV USER_URL=""

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
