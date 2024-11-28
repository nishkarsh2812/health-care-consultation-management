package com.jdbc.connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultationDaoImpl implements ConsultationDao {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/healthcare";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    @Override
    public void addConsultation(Consultation consultation) {
        String query = "INSERT INTO consultations (patient_id, doctor_id, date, diagnosis) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, consultation.getPatientId());
            statement.setInt(2, consultation.getDoctorId());
            statement.setString(3, consultation.getDate());
            statement.setString(4, consultation.getDiagnosis());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateConsultation(Consultation consultation) {
        String query = "UPDATE consultations SET patient_id = ?, doctor_id = ?, date = ?, diagnosis = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, consultation.getPatientId());
            statement.setInt(2, consultation.getDoctorId());
            statement.setString(3, consultation.getDate());
            statement.setString(4, consultation.getDiagnosis());
            statement.setInt(5, consultation.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteConsultation(int consultationId) {
        String query = "DELETE FROM consultations WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, consultationId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Consultation getConsultationById(int consultationId) {
        String query = "SELECT * FROM consultations WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, consultationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Consultation(resultSet.getInt("id"), resultSet.getInt("patient_id"),
                        resultSet.getInt("doctor_id"), resultSet.getString("date"),
                        resultSet.getString("diagnosis"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Consultation> getAllConsultations() {
        List<Consultation> consultations = new ArrayList<>();
        String query = "SELECT * FROM consultations";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                consultations.add(new Consultation(resultSet.getInt("id"), resultSet.getInt("patient_id"),
                        resultSet.getInt("doctor_id"), resultSet.getString("date"),
                        resultSet.getString("diagnosis")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }
}
