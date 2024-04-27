FROM eclipse-temurin:22-jdk-alpine

WORKDIR /app

COPY /api/target/*.jar /app/api.jar
COPY /service/target/*.jar /app/service.jar
COPY /application/target/*.jar /app/app.jar

CMD ["java","--enable-preview","-p", "/app/api.jar:/app/service.jar:/app/app.jar", "-m", "dev.ebyrdeu.application/dev.ebyrdeu.console.App"]
