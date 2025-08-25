# Используем официальный OpenJDK образ
FROM eclipse-temurin:24-jdk-alpine

# Указываем рабочую директорию
WORKDIR /app

# Копируем собранный jar (будем билдить через Maven или Gradle)
COPY target/*.jar app.jar

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"]