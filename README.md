# Pokémon Search Engine 🔍

A powerful and intuitive search engine for Pokémon data, built with modern web technologies to provide fast and accurate Pokémon information retrieval.

![Pokémon](https://img.shields.io/badge/Pokémon-Search%20Engine-red?style=for-the-badge&logo=pokemon)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-green?style=for-the-badge&logo=springboot)
![React](https://img.shields.io/badge/React-18.2.0-blue?style=for-the-badge&logo=react)
![Maven](https://img.shields.io/badge/Maven-3.8.0-orange?style=for-the-badge&logo=apache-maven)
![Java](https://img.shields.io/badge/Java-21-red?style=for-the-badge&logo=openjdk)

## 🌟 Features

### Backend (Spring Boot)
- **RESTful API**: Comprehensive endpoints for Pokémon data
- **JPA/Hibernate**: Efficient data persistence and retrieval
- **Spring Security**: Secure API endpoints
- **Caching**: Redis/Ehcache for improved performance
- **Swagger Documentation**: Interactive API documentation
- **Database Integration**: MySQL/PostgreSQL support
- **Search Optimization**: Advanced search algorithms

### Frontend (React)
- **Advanced Search**: Search Pokémon by name, type, abilities, and more
- **Responsive Design**: Works seamlessly on all devices
- **Modern UI**: Clean and user-friendly interface
- **Real-time Updates**: Live search results

## 🚀 Quick Start

### Prerequisites

- **Java 17** or higher
- **Maven 3.8+**
- **Node.js 16+** and npm
- **MySQL 8.0**

### Backend Setup (Spring Boot)

1. **Clone the repository**
   ```bash
   git clone https://github.com/mgnak/Pokemon-searchEngine.git
   cd Pokemon-searchEngine/backend

Configure database
Update src/main/resources/application.properties:

properties
   ```bash
         spring.datasource.url=jdbc:mysql://localhost:3306/pokemon_db
         spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   Build and run the application


   # Using Maven
   mvn clean install
   mvn spring-boot:run

   # Or run the JAR file
   java -jar target/pokemon-search-engine-1.0.0.jar
   ```

## Verify the API
The application will start on http://localhost:8080

API Documentation: http://localhost:8080/swagger-ui.html

Health Check: http://localhost:8080/actuator/health

## Frontend Setup (React)
Navigate to frontend directory

```bash
cd ../frontend
Install dependencies
```
```bash
npm install
# or
yarn install
```

Configure API endpoint

REACT_APP_API_BASE_URL=http://localhost:8080/api/v1
Start the development server

```bash
npm start
# or
yarn start
```

### Access the application
Open http://localhost:3000 in your browser

## 🛠️ Build and Deployment
Backend Build
```bash
cd backend
mvn clean package -DskipTests
# Produces: target/pokemon-search-engine-1.0.0.jar
Frontend Build
```

```bash
cd frontend
npm run build
```

### Creates optimized build in 'build/' folder
Docker Deployment
dockerfile

### Backend Dockerfile
FROM openjdk:17-jdk-slim
COPY target/pokemon-search-engine-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

### Build and run with Docker Compose
```bash
docker-compose up --build
```

## Acknowledgments

- PokéAPI for providing comprehensive Pokémon data
- Pokémon and Pokémon character names are trademarks of Nintendo
- Currently, this project under progress due to limited time.
