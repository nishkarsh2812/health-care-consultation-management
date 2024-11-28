package com.jdbc.connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements PatientDao {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/healthcare";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    @Override
    public void addPatient(Patient patient) {
        String query = "INSERT INTO patients (name, age, medical_history) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getMedicalHistory());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePatient(Patient patient) {
        String query = "UPDATE patients SET name = ?, age = ?, medical_history = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getMedicalHistory());
            statement.setInt(4, patient.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePatient(int patientId) {
        String query = "DELETE FROM patients WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, patientId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Patient getPatientById(int patientId) {
        String query = "SELECT * FROM patients WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, patientId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Patient(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getInt("age"), resultSet.getString("medical_history"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patients";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                patients.add(new Patient(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getInt("age"), resultSet.getString("medical_history")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }
}
