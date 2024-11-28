package com.jdbc.connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/healthcare";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    @Override
    public void addDoctor(Doctor doctor) {
        String query = "INSERT INTO doctors (name, specialty) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getSpecialty());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        String query = "UPDATE doctors SET name = ?, specialty = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getSpecialty());
            statement.setInt(3, doctor.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDoctor(int doctorId) {
        String query = "DELETE FROM doctors WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, doctorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Doctor getDoctorById(int doctorId) {
        String query = "SELECT * FROM doctors WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, doctorId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Doctor(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("specialty"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                doctors.add(new Doctor(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("specialty")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }
}
