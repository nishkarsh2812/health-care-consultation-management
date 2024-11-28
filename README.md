Healthcare Consultation Management System
This is a Java-based project for managing healthcare consultations between doctors and patients. It implements a basic CRUD (Create, Read, Update, Delete) system for managing consultations, doctors, and patients using JDBC for database connectivity.

Features
Manage patients and their medical history.
Manage doctors and their specialties.
Schedule and manage consultations between patients and doctors.
Use of JDBC to connect to a MySQL database.
Modular design following the DAO (Data Access Object) pattern.
Project Structure
The project is organized as follows:

Entities: These represent the objects in the system (e.g., Doctor, Patient, Consultation).
DAO Interfaces: These define the operations for interacting with the database (e.g., DoctorDao, PatientDao, ConsultationDao).
DAO Implementations: These provide the actual database operations for the entities (e.g., DoctorDaoImpl, PatientDaoImpl, ConsultationDaoImpl).
Database: MySQL is used for data persistence.
JDBCConnector: A utility class to establish database connections.
Prerequisites
Java Development Kit (JDK) 8 or above.
MySQL or any compatible database system.
JDBC driver for MySQL (e.g., mysql-connector-java).
IDE like IntelliJ IDEA, Eclipse, or NetBeans.
Database Setup
Before running the project, you need to create a MySQL database and tables. Use the following SQL commands to set up the required database schema.

1. Create Database
sql
Copy code
CREATE DATABASE healthcare;
USE healthcare;
2. Create Tables
sql
Copy code
CREATE TABLE doctors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    specialty VARCHAR(100)
);

CREATE TABLE patients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    medical_history TEXT
);

CREATE TABLE consultations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT,
    doctor_id INT,
    date DATE,
    diagnosis TEXT,
    FOREIGN KEY (patient_id) REFERENCES patients(id),
    FOREIGN KEY (doctor_id) REFERENCES doctors(id)
);
Installation
Clone the repository:

bash
Copy code
git clone https://github.com/yourusername/healthcare-consultation-management.git
Navigate to the project directory:

bash
Copy code
cd healthcare-consultation-management
Ensure you have MySQL installed and running. Update the database connection details in the JDBCConnector.java file.

JDBCConnector.java:

java
Copy code
private static final String JDBC_URL = "jdbc:mysql://localhost:3306/healthcare";
private static final String JDBC_USER = "root"; // Your MySQL username
private static final String JDBC_PASSWORD = "password"; // Your MySQL password
Import the project into your favorite IDE (e.g., IntelliJ IDEA, Eclipse).

Ensure that the MySQL JDBC driver (e.g., mysql-connector-java) is included in your classpath.

Usage
Adding a Doctor
Create a new Doctor object:

java
Copy code
Doctor doctor = new Doctor(0, "Dr. John Smith", "Cardiology");
Create an instance of DoctorDaoImpl and add the doctor:

java
Copy code
DoctorDao doctorDao = new DoctorDaoImpl();
doctorDao.addDoctor(doctor);
Adding a Patient
Create a new Patient object:

java
Copy code
Patient patient = new Patient(0, "Jane Doe", 30, "No known medical history");
Add the patient using PatientDaoImpl:

java
Copy code
PatientDao patientDao = new PatientDaoImpl();
patientDao.addPatient(patient);
Scheduling a Consultation
Create a Consultation object with references to the doctor and patient:

java
Copy code
Consultation consultation = new Consultation(0, patient.getId(), doctor.getId(), "2024-12-01", "General Checkup");
Add the consultation using ConsultationDaoImpl:

java
Copy code
ConsultationDao consultationDao = new ConsultationDaoImpl();
consultationDao.addConsultation(consultation);
Retrieving Consultations
You can retrieve all consultations by using:

java
Copy code
List<Consultation> consultations = consultationDao.getAllConsultations();
for (Consultation consultation : consultations) {
    System.out.println(consultation.getId() + ": " + consultation.getDate() + " - " + consultation.getDiagnosis());
}
Example Code
Here is a simple example to add a doctor, a patient, and schedule a consultation:

java
Copy code
public class Main {
    public static void main(String[] args) {
        // Create and add a doctor
        Doctor doctor = new Doctor(0, "Dr. John Smith", "Cardiology");
        DoctorDao doctorDao = new DoctorDaoImpl();
        doctorDao.addDoctor(doctor);

        // Create and add a patient
        Patient patient = new Patient(0, "Jane Doe", 30, "No known medical history");
        PatientDao patientDao = new PatientDaoImpl();
        patientDao.addPatient(patient);

        // Create and add a consultation
        Consultation consultation = new Consultation(0, patient.getId(), doctor.getId(), "2024-12-01", "General Checkup");
        ConsultationDao consultationDao = new ConsultationDaoImpl();
        consultationDao.addConsultation(consultation);

        System.out.println("Consultation scheduled!");
    }
}
Troubleshooting
MySQL Connection Issues: Ensure that MySQL is running and the database connection details in JDBCConnector.java are correct.
Missing JDBC Driver: Make sure that mysql-connector-java is included in your project dependencies.
Contributing
Feel free to fork this repository, make changes, and submit pull requests. You can also report any issues or bugs by creating an issue in the GitHub repository.
