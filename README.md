# JDBC MySQL Demo

This project connects to the local MySQL `college` database, inserts a sample student, and prints the student records.

## Requirements

- Java JDK
- MySQL running on `localhost:3306`
- A `college` database with a `student` table containing `id`, `name`, `marks`, `grade`, and `city` columns
- The MySQL Connector/J JAR in `lib`

Set the database password in the `DB_PASSWORD` environment variable. In PowerShell:

```powershell
$env:DB_PASSWORD = "your-password"
```

## Compile and run

From the project directory, run:

```powershell
javac -cp "lib\mysql-connector-j-26.7.0.jar" -d bin src\App.java
java -cp "bin;lib\mysql-connector-j-26.7.0.jar" App
```

Each run inserts another sample row with the next available `id`.
