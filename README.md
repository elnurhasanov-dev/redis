# Redis Configuration Examples with Spring Boot

This repository demonstrates three different Redis configuration approaches in a Spring Boot application:
1. Redisson Client
2. RedisTemplate
3. Spring Cache with `@Cacheable`

The project uses PostgreSQL for database operations and focuses on Redis integration patterns (simplified implementation without complex layers).

## Prerequisites

- Docker and Docker Compose
- JDK 17+
- Gradle

## Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/your-repo.git
   cd your-repo
   
2. **Start container**
      ```bash
   docker-compose up -d
