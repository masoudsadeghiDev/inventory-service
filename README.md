# 🛒 Inventory Service — ShedLock Simulation

**Inventory Service** is a Spring Boot-based microservice that demonstrates how to use [ShedLock](https://github.com/lukas-krecan/ShedLock) to prevent race conditions in distributed systems. When running scheduled tasks across multiple instances (e.g., in a Kubernetes cluster), ShedLock ensures that only one instance executes a scheduled job at a time — preserving data integrity in your inventory management workflows.

---

## ⚙️ Key Features

* ✅ Scheduled task that simulates inventory synchronization or stock level recalculation
* 🔒 Uses ShedLock to coordinate task execution across distributed instances
* 🗄️ Backed by a persistent store (e.g., PostgreSQL) for distributed lock management
* 📈 Clear logging to illustrate locking behavior and race condition prevention

---

✨ What’s Inside?
This project contains:

🔄 InventorySyncServiceWithRaceCondition.java – a baseline example that does not use locking

🔐 InventorySyncService.java – the locked version using ShedLock to prevent overlapping execution

⚙️ ShedLockConfig.java – ShedLock configuration to integrate with your DB

🧪 InventorySchedulerTest.java – unit test for scheduled task behavior

---

## 📁 Project Structure

```
inventory-service/
├── src/
│   ├── main/
│   │   ├── java/com/example/inventory_service/
│   │   │   ├── config/
│   │   │   │   └── ShedLockConfig.java
│   │   │   ├── entity/
│   │   │   │   └── InventoryItem.java
│   │   │   ├── repository/
│   │   │   │   └── InventoryRepository.java
│   │   │   ├── service/
│   │   │   │   ├── InventoryService.java
│   │   │   │   ├── InventorySyncService.java
│   │   │   │   └── InventorySyncServiceWithRaceCondition.java
│   │   │   └── InventoryServiceApplication.java
│   └── resources/
│       ├── application.yml
│       ├── application.properties
│       └── schema.sql
├── test/java/com/example/inventory_service/
│   └── InventorySchedulerTest.java
├── docker-compose.yml
└── README.md

```

---

## 🔧 Getting Started

### ✅ Prerequisites

* Java 21+
* Spring Boot 3.x
* Maven
* A database (PostgreSQL recommended)

### 📦 Setup Instructions

1. **Clone the repository:**

```bash
git clone https://github.com/your-org/inventory-service.git
cd inventory-service
```

2. **Configure your database:**

Edit `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/inventorydb
    username: your_user
    password: your_password
```

3. **Run the application:**

```bash
./mvnw spring-boot:run
```

Or with Gradle:

```bash
./gradlew bootRun
```

---

🧪 Run and Simulate
🔄 Without ShedLock
Run InventorySyncServiceWithRaceCondition.java in multiple instances:

You’ll observe overlapping task executions — a classic race condition scenario.

✅ With ShedLock
Run InventorySyncService.java in multiple instances:

Only one instance executes the scheduled task at a time.

Others skip execution if the lock is already held.

---

## 🔀 Running Multi-Instance with Different Ports

To simulate a **distributed environment**, you can run multiple instances of the Inventory Service on your local machine, each bound to a different port. This setup helps demonstrate how **ShedLock** ensures only one instance executes the scheduled task at a time — preventing race conditions.

### ✅ Steps to Run Multiple Instances Locally

1. **Package the application** (if you haven't already):

   ```bash
   ./mvnw clean package
   ```

2. **Run the first instance** on port `8080`:

   ```bash
   java -jar target/inventory-service-0.0.1-SNAPSHOT.jar --server.port=8080
   ```

3. **Run a second instance** on port `8081`:

   ```bash
   java -jar target/inventory-service-0.0.1-SNAPSHOT.jar --server.port=8081
   ```

> Ensure both instances are connecting to the **same shared database** (configured in `application.yml`) so that ShedLock can coordinate between them.

### 🔍 What You'll Observe

When the scheduled task runs:

* **Only one instance** (e.g., on port 8080) acquires the lock and performs the operation.
* The **other instance** (e.g., on port 8081) logs that it could not acquire the lock and skips execution.

This effectively demonstrates **race condition prevention** across distributed nodes.

---

## 🛠️ Technologies Used

* Spring Boot
* Spring Scheduler
* ShedLock
* PostgreSQL (or any compatible ShedLock store)
* SLF4J / Logback for logging

---

## 🐳 Running with Docker (Optional)

Use Docker to quickly spin up a PostgreSQL DB:

```bash
docker-compose up -d
```

Make sure your `application.yml` points to the containerized DB.

---

## 🧪 Testing

You can run the included test:

```bash
./mvnw test
```

This runs `InventorySchedulerTest.java`, which verifies scheduling behavior.

---

## 📚 Useful References

* [ShedLock Documentation](https://github.com/lukas-krecan/ShedLock)
* [Spring Scheduled Tasks](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/annotation/Scheduled.html)

---

## 📝 License

Licensed under the MIT License.
