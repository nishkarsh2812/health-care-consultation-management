package com.jdbc.connector;

import java.util.List;

public interface ConsultationDao {
    void addConsultation(Consultation consultation);
    void updateConsultation(Consultation consultation);
    void deleteConsultation(int consultationId);
    Consultation getConsultationById(int consultationId);
    List<Consultation> getAllConsultations();
}
