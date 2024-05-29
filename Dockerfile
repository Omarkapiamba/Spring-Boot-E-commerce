# Basbild docker-image
FROM maven

# Arbetskatalogen inuti containern där kommandon och instruktioner ska köras.
WORKDIR /app

# Kopiera resten av projektet till containern
COPY . /app

# Bygg applikationen med Maven
RUN mvn clean package -DskipTests

# Ange porten som din Spring Boot-applikation kommer att lyssna på
EXPOSE 8080

# Starta din Spring Boot-applikation
CMD ["java", "-jar", "target/Spring-boot-E-Commerce-0.0.1-SNAPSHOT.jar"]