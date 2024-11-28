package com.jdbc.connector;

import java.util.List;

public interface PatientDao {
    void addPatient(Patient patient);
    void updatePatient(Patient patient);
    void deletePatient(int patientId);
    Patient getPatientById(int patientId);
    List<Patient> getAllPatients();
}
