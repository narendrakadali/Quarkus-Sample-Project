# Quarkus Reactive Microservice

## 📌 Project Overview
This is a **Quarkus-based Identity Governance and Access Management (IGA) microservice** that:
- ✅ **Uses Hibernate Reactive with Panache**
- ✅ **Connects to MySQL using Reactive Client**
- ✅ **Implements Kafka Event Streaming**
- ✅ **Enforces Strict JSON Serialization with Jackson**
- ✅ **Follows an API-First Approach**

## 🛠️ **Tech Stack**
| Component         | Technology |
|------------------|------------|
| **Backend**      | Quarkus (Reactive) |
| **Database**     | MySQL (Reactive) |
| **ORM**         | Hibernate Reactive Panache |
| **Messaging**    | Kafka (Event-Driven) |
| **API Gateway**  | Resteasy Reactive |
| **Logging**      | OpenTelemetry + JSON Logging |

---

## 🚀 **Getting Started**

### 1️⃣ **Clone the Repository**
```sh
git clone <your-repo-url>
cd naidentity-iga
```

### 2️⃣ **Start Required Services (MySQL & Kafka)**
Ensure `nerdctl` is installed and run:
```sh
nerdctl compose up -d
```

Check running containers:
```sh
nerdctl ps
```

### 3️⃣ **Run the Application in Dev Mode**
```sh
./mvnw quarkus:dev
```

### 4️⃣ **Test API Endpoints**

#### ➤ **Check All Users**
```sh
curl -X GET http://localhost:8080/users
```

#### ➤ **Create a New User**
```sh
curl -X POST http://localhost:8080/users      -H "Content-Type: application/json"      -d '{"username": "alice_smith", "email": "alice.smith@example.com"}'
```

#### ➤ **Delete a User**
```sh
curl -X DELETE http://localhost:8080/users/1
```

---

## 🛠 **Configuration (`application.properties`)**

### ✅ **Database Configuration**
```properties
quarkus.datasource.db-kind=mysql
quarkus.datasource.username=root
quarkus.datasource.password=rootpassword
quarkus.datasource.reactive.url=mysql://mysql-db:3306/naidentity
quarkus.datasource.reactive.pool.enabled=true
```

### ✅ **Kafka Configuration**
```properties
mp.messaging.outgoing.user-events.bootstrap.servers=kafka:9092
mp.messaging.incoming.user-events.bootstrap.servers=kafka:9092
```

### ✅ **Jackson & JSON Serialization Enforcement**
```properties
quarkus.resteasy-reactive.json.default-mapper=jackson
quarkus.resteasy-reactive.json.fail-on-unknown-properties=true
```

---

## 🛠 **Development Notes**

### ✅ **Database Migration (Manually Creating `users` Table)**
If Hibernate does not auto-create the table, run:
```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### ✅ **Inserting Sample Data**
```sql
INSERT INTO users (username, email, created_at) VALUES
('john_doe', 'john.doe@example.com', NOW()),
('jane_doe', 'jane.doe@example.com', NOW()),
('alice_smith', 'alice.smith@example.com', NOW()),
('bob_jackson', 'bob.jackson@example.com', NOW());
```

### ✅ **Checking Database Inside Container**
```sh
nerdctl exec -it mysql-db mysql -u root -prootpassword -e "USE naidentity; SHOW TABLES;"
```

---

## 🛠 **Project Structure**
```
naidentity-iga/
│── src/main/java/com/sample/iga/
│   ├── controller/         # REST APIs (Reactive)
│   ├── service/            # Business Logic Layer
│   ├── repository/         # Database Access Layer (Reactive Panache)
│   ├── model/              # DTOs & Entity Classes
│   ├── event/              # Kafka Event Handlers
│── src/main/resources/
│   ├── application.properties  # Configuration file
│── docker/                     # Docker Compose for MySQL & Kafka
│── pom.xml                      # Maven Dependencies
│── README.md                    # Project Documentation
```

---

## 🔥 **Troubleshooting**
### ❌ **Issue: `No current Mutiny.Session found`**
✅ **Fix:** Add `@WithSession` and `@WithTransaction` in `UserService.java`.

### ❌ **Issue: `Table 'naidentity.users' doesn't exist`**
✅ **Fix:** Ensure `quarkus.hibernate-orm.database.generation=update` is enabled.

### ❌ **Issue: API Returns `[User<1>, User<2>]` Instead of JSON**
✅ **Fix:** Add `quarkus-resteasy-reactive-jackson` in `pom.xml`.

---

## 📜 **License**
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 💡 **Contributing**
Pull requests are welcome! If you find an issue, feel free to create one.

🚀 **Happy Coding!** 🚀
