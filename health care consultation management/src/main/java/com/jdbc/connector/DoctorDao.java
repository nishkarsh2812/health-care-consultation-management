package com.jdbc.connector;

import java.util.List;

public interface DoctorDao {
    void addDoctor(Doctor doctor);
    void updateDoctor(Doctor doctor);
    void deleteDoctor(int doctorId);
    Doctor getDoctorById(int doctorId);
    List<Doctor> getAllDoctors();
}
