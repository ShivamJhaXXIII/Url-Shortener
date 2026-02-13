# 🔗 URL Shortener

A modern, full-stack URL shortening service built with **Spring Boot** and **Thymeleaf**, featuring a beautiful **Tailwind CSS** frontend and RESTful API.

This is a solution to the URL shortener project on roadmap.sh : https://roadmap.sh/projects/url-shortening-service

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.2-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue.svg)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Ready-2496ED.svg)](https://www.docker.com/)

---

## ✨ Features

- 🎨 **Beautiful UI** - Modern, responsive Tailwind CSS design
- 📱 **Mobile-First** - Fully responsive for all devices
- 🚀 **Fast** - Base62 encoding for compact short URLs
- 📊 **Analytics** - Click tracking for each shortened URL
- 🐳 **Docker Ready** - One-command setup with Docker Compose
- 📋 **Copy-to-Clipboard** - One-click URL copying
- 🔄 **RESTful API** - Full API support for integrations

---

## 🚀 Quick Start

### Prerequisites

- **Docker & Docker Compose** (required)

---

### 🐳 **Run with Docker**

The easiest way to get started - just one command:

```bash
# Clone the repository
git clone https://github.com/YOUR_USERNAME/shorten_url.git
cd shorten_url

# Start everything (PostgreSQL + App)
docker-compose up -d

# View logs
docker-compose logs -f app

# Stop everything
docker-compose down
```

✅ **That's it!** Open [http://localhost:8080](http://localhost:8080)

---

### 💻 **Run Locally (Without Docker)**

#### 1. Start PostgreSQL

```bash
# Using Docker for just the database
docker run -d \
  --name postgres \
  -e POSTGRES_DB=url_db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  postgres:16
```

#### 2. Run the Application

```bash
# Using Maven wrapper
./mvnw spring-boot:run

# OR build and run JAR
./mvnw clean package
java -jar target/shorten_url-0.0.1-SNAPSHOT.jar
```

✅ **Open** [http://localhost:8080](http://localhost:8080)

---

## 📸 How It Works

### Web Interface
```
┌─────────────────────────────────────────────┐
│              🔗 URL Shortener               │
│     Transform long URLs into short,         │
│           shareable links                   │
│                                             │
│  Enter your long URL:                       │
│  ┌──────────────────────────────────────┐  │
│  │ https://example.com/very/long/url... │  │
│  └──────────────────────────────────────┘  │
│                                             │
│      [✨ Shorten URL]                       │
│                                             │
│  ✅ Success! Your shortened URL:            │
│  ┌───────────────────────┐                 │
│  │ http://localhost/abc  │  [📋 Copy]      │
│  └───────────────────────┘                 │
└─────────────────────────────────────────────┘
```

---

## 🌐 API Usage

### **Shorten a URL**

```bash
curl -X POST http://localhost:8080/api/shorten \
  -H "Content-Type: application/json" \
  -d '{"url":"https://www.example.com/very/long/url"}'
```

**Response:**
```json
{
  "shortUrl": "http://localhost:8080/1"
}
```

---

### **Redirect Short URL**

```bash
curl -I http://localhost:8080/1
```

**Response:**
```
HTTP/1.1 302 Found
Location: https://www.example.com/very/long/url
```

---

### **URL Encoding Examples**

| ID | Short Code | Full Short URL |
|----|------------|----------------|
| 1 | `1` | `http://localhost:8080/1` |
| 10 | `a` | `http://localhost:8080/a` |
| 62 | `10` | `http://localhost:8080/10` |
| 100 | `1C` | `http://localhost:8080/1C` |

*Uses Base62 encoding (0-9, a-z, A-Z)*

---

## 🏗️ Architecture

```
┌─────────────┐
│   Browser   │
└──────┬──────┘
       │ HTTP
       ▼
┌─────────────────────────────────┐
│     Spring Boot Application     │
│  ┌─────────────────────────┐   │
│  │   Web Controller        │   │  ← Thymeleaf
│  └─────────────────────────┘   │
│  ┌─────────────────────────┐   │
│  │   REST Controller       │   │  ← JSON API
│  └─────────────────────────┘   │
│  ┌─────────────────────────┐   │
│  │   URL Service           │   │  ← Logic
│  └─────────────────────────┘   │
│  ┌─────────────────────────┐   │
│  │   JPA Repository        │   │  ← Data
│  └─────────────────────────┘   │
└────────────┬────────────────────┘
             │ JDBC
             ▼
      ┌─────────────┐
      │ PostgreSQL  │
      └─────────────┘
```

---

## 🗂️ Project Structure

```
shorten_url/
├── src/main/
│   ├── java/com/shivam/shorten_url/
│   │   ├── Controller/
│   │   │   ├── UrlController.java      # REST API
│   │   │   └── WebController.java      # Web UI
│   │   ├── Entity/Url.java             # Database model
│   │   ├── Repository/UrlRepo.java     # Data access
│   │   ├── Service/UrlService.java     # Business logic
│   │   └── Utility/Base62Encoder.java  # URL encoding
│   └── resources/
│       ├── application.properties       # Config
│       └── templates/index.html        # Frontend
├── docker-compose.yml                   # Docker setup
├── Dockerfile                          # Docker image
└── pom.xml                             # Dependencies
```

---

## 🛠️ Technology Stack

- **Spring Boot 4.0.2** - Framework
- **Java 21** - Language
- **PostgreSQL 16** - Database
- **Thymeleaf** - Templating
- **Tailwind CSS** - Styling
- **Docker** - Containerization

---

## 🧪 Testing

### **Web Interface:**
1. Open http://localhost:8080
2. Enter a long URL
3. Click "Shorten URL"
4. Copy and test the short URL

### **API:**

```bash
# Shorten
curl -X POST http://localhost:8080/api/shorten \
  -H "Content-Type: application/json" \
  -d '{"url":"https://www.google.com"}'

# Test redirect
curl -I http://localhost:8080/1
```

---

## 🐳 Docker Commands

```bash
# Start
docker-compose up -d

# View logs
docker-compose logs -f app

# Stop
docker-compose down

# Rebuild
docker-compose up -d --build

# Remove all data
docker-compose down -v

# Check status
docker-compose ps
```

---


## 📋 Roadmap

- [x] URL shortening with Base62
- [x] Click tracking
- [x] Tailwind CSS UI
- [x] Docker support
- [x] REST API
- [ ] Custom short URLs
- [ ] Analytics dashboard
- [ ] User authentication
- [ ] QR code generation

---

## 🤝 Contributing

1. Fork the repository
2. Create feature branch
3. Commit changes
4. Push to branch
5. Open Pull Request

---

## 📄 License

MIT License

---

## 👨‍💻 Author

**Shivam**

---

## ⭐ Show Your Support

Give a ⭐️ if this project helped you!

---

<div align="center">

**Made with ❤️ using Spring Boot and Tailwind CSS**

</div>
