/*
📄 README - Java Banking System (JDBC)

A simple console-based banking system using Java and JDBC with MySQL.

🔧 Features:
- User Registration & Login
- JDBC MySQL connection
- Clean menu-driven interface

🗄️ MySQL Setup:
CREATE DATABASE jdbc_demo;
USE jdbc_demo;
CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL
);

⚙️ DB Config (in code):
String url = "jdbc:mysql://localhost:3306/jdbc_demo";
String username = "root";
String pass = "your_mysql_password";

▶️ Run Steps:
1. Install MySQL and JDBC driver (mysql-connector-j)
2. Update DB credentials
3. Compile & run:
   javac User.java
   java User

📌 Note:
- Use PreparedStatement for secure queries
- Optionally hash passwords with Password.java

✅ Created for learning JDBC and basic account systems.
*/
