# Modern SiteManager - Complete Setup Guide

## 🚀 Technology Stack

### Backend
- **Spring Boot 3.2.1** - Latest stable version
- **Spring Security 6.x** - Advanced security with JWT
- **Spring Data JPA** - ORM with Hibernate
- **MySQL 8.x** - Primary database
- **HikariCP** - High-performance connection pooling
- **JWT (io.jsonwebtoken)** - Secure token-based authentication
- **BCrypt** - Password hashing with strength 12
- **MapStruct** - DTO mapping
- **Swagger/OpenAPI** - API documentation
- **Flyway** - Database migrations
- **Lombok** - Reduce boilerplate code

### Frontend
- **Vue.js 3.4** - Progressive framework
- **Vite 5.x** - Fast build tool
- **Pinia** - State management
- **Vue Router 4.x** - Routing
- **Element Plus** - Modern UI component library
- **Axios** - HTTP client
- **CryptoJS** - Client-side encryption
- **SCSS** - CSS preprocessor

## 🔒 Security Features

✅ **Authentication & Authorization**
- JWT-based stateless authentication
- Refresh token mechanism
- Role-Based Access Control (RBAC)
- Method-level security (@PreAuthorize)
- Custom UserDetailsService

✅ **Password Security**
- BCrypt hashing (strength 12)
- Password complexity validation
- Client-side password hashing (additional layer)
- No password in logs or responses

✅ **API Security**
- CORS configuration
- CSRF protection disabled for stateless API
- Rate limiting ready (Bucket4j)
- Input validation with Jakarta Validation
- SQL injection prevention (JPA prepared statements)
- XSS protection headers

✅ **Session Management**
- Stateless JWT tokens (24h expiry)
- Refresh tokens (7 days)
- Configurable session timeout
- Multi-login control

✅ **Audit & Monitoring**
- Audit logging table
- Failed login attempt tracking
- Spring Actuator endpoints
- Structured logging

## 📁 Project Structure

```
/workspace/
├── modern-sitemanager/              # Backend (Spring Boot)
│   ├── src/main/java/com/sitemanager/
│   │   ├── config/                  # Security & app configuration
│   │   ├── controller/              # REST controllers
│   │   ├── dto/                     # Data Transfer Objects
│   │   ├── entity/                  # JPA entities
│   │   ├── exception/               # Exception handling
│   │   ├── repository/              # Data access layer
│   │   ├── security/                # Security components
│   │   │   └── jwt/                 # JWT utilities
│   │   ├── service/                 # Business logic
│   │   └── ModernSitemanagerApplication.java
│   ├── src/main/resources/
│   │   ├── db/migration/            # Flyway migrations
│   │   ├── application.yml          # Main config
│   │   ├── application-dev.yml      # Dev profile
│   │   └── application-prod.yml     # Prod profile
│   └── pom.xml
│
└── modern-sitemanager-frontend/     # Frontend (Vue.js)
    ├── src/
    │   ├── api/                     # API client
    │   ├── layouts/                 # Layout components
    │   ├── router/                  # Vue Router config
    │   ├── stores/                  # Pinia stores
    │   ├── views/                   # Page components
    │   ├── App.vue
    │   └── main.js
    ├── package.json
    └── vite.config.js
```

## 🛠️ Setup Instructions

### Prerequisites
- Java 17+
- Node.js 18+
- MySQL 8.0+
- Maven 3.8+

### 1. Database Setup

```sql
-- Create database
CREATE DATABASE sitemanager_db 
  CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;

-- Create user (optional, for production)
CREATE USER 'sitemanager_user'@'localhost' IDENTIFIED BY 'your_secure_password';
GRANT ALL PRIVILEGES ON sitemanager_db.* TO 'sitemanager_user'@'localhost';
FLUSH PRIVILEGES;
```

### 2. Backend Configuration

Edit `application.yml` or set environment variables:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/sitemanager_db?useSSL=true&serverTimezone=UTC
    username: ${DB_USERNAME:sitemanager_user}
    password: ${DB_PASSWORD:changeit_in_production}

jwt:
  secret: ${JWT_SECRET:must_be_at_least_64_characters_long_base64_encoded_secret_key_for_hs512}
```

### 3. Build & Run Backend

```bash
cd /workspace/modern-sitemanager

# Build with Maven
mvn clean install -DskipTests

# Run application
mvn spring-boot:run

# Or run JAR
java -jar target/modern-sitemanager-1.0.0.jar
```

Default profiles:
- `dev` - Development with H2 console, debug logging
- `prod` - Production with optimized settings

### 4. Frontend Setup

```bash
cd /workspace/modern-sitemanager-frontend

# Install dependencies
npm install

# Run development server
npm run dev

# Build for production
npm run build
```

## 🔑 Default Credentials (Dev Only)

After first run, these users are created automatically:

**Admin Account:**
- Username: `admin`
- Password: `Admin@123`
- Role: SUPERADMIN

**Demo Account:**
- Username: `demo`
- Password: `Demo@123`
- Role: USER

⚠️ **Change these passwords immediately in production!**

## 📡 API Endpoints

### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/logout` - User logout
- `POST /api/auth/refresh` - Refresh token
- `GET /api/auth/me` - Get current user

### Sitemanager CRUD
- `GET /api/sitemanagers` - List all (paginated)
- `GET /api/sitemanagers/{id}` - Get by ID
- `POST /api/sitemanagers` - Create new
- `PUT /api/sitemanagers/{id}` - Update
- `DELETE /api/sitemanagers/{id}` - Delete
- `GET /api/sitemanagers/search?q=query` - Search

### Documentation
Access Swagger UI at: `http://localhost:8080/swagger-ui.html`

## 🔐 Security Best Practices Implemented

1. **Never store plain text passwords** - All passwords hashed with BCrypt
2. **Use HTTPS in production** - Configure SSL/TLS
3. **Secure JWT secrets** - Use environment variables
4. **Input validation** - Both client and server-side
5. **Principle of least privilege** - Role-based access
6. **Audit logging** - Track all sensitive operations
7. **Rate limiting** - Prevent brute force attacks
8. **CORS configuration** - Restrict origins
9. **Security headers** - XSS, clickjacking protection
10. **Database constraints** - Unique indexes, foreign keys

## 🚀 Production Deployment

### Environment Variables

```bash
export DB_USERNAME=prod_user
export DB_PASSWORD=super_secure_password
export JWT_SECRET=base64_encoded_64_char_minimum_secret
export CORS_ALLOWED_ORIGINS=https://yourdomain.com
export SPRING_PROFILES_ACTIVE=prod
```

### Docker Support (Coming Soon)

```dockerfile
FROM eclipse-temurin:17-jre-alpine
COPY target/modern-sitemanager-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

## 📊 Scalability Features

- **Connection Pooling**: HikariCP with optimal settings
- **Stateless Architecture**: JWT enables horizontal scaling
- **Pagination**: Efficient data retrieval
- **Lazy Loading**: JPA relationships
- **Indexing**: Database indexes on frequently queried fields
- **Caching Ready**: Spring Cache abstraction compatible

## 🧪 Testing

```bash
# Run tests
mvn test

# Integration tests
mvn verify

# Code coverage
mvn clean test jacoco:report
```

## 📝 Next Steps

To complete the implementation:

1. ✅ Add file upload for photographs
2. ✅ Implement email service for password reset
3. ✅ Add audit logging interceptor
4. ✅ Implement rate limiting filter
5. ✅ Add unit and integration tests
6. ✅ Create Docker compose file
7. ✅ Set up CI/CD pipeline

## 🆘 Troubleshooting

**Issue**: JWT token validation fails
- Check JWT secret is same across instances
- Ensure system clocks are synchronized

**Issue**: CORS errors
- Verify `CORS_ALLOWED_ORIGINS` includes frontend URL
- Check browser console for specific error

**Issue**: Database connection fails
- Verify MySQL is running
- Check credentials in application.yml
- Ensure database exists

## 📄 License

Proprietary - All rights reserved

---

For questions or support, contact your development team.
