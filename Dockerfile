FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/*.jar /app/desafio-cafe-da-manha-api.jar

EXPOSE 8080

CMD java -XX:+UseContainerSupport -Xmx256m -Dserver.port=${PORT} -jar desafio-cafe-da-manha-api.jar