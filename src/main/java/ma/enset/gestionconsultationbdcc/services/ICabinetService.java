package ma.enset.gestionconsultationbdcc.services;

import ma.enset.gestionconsultationbdcc.entities.Consultation;
import ma.enset.gestionconsultationbdcc.entities.Patient;

import java.sql.SQLException;
import java.util.List;

public interface ICabinetService {
    void addPatient(Patient patient) throws SQLException;
    void updatePatient(Patient patient);
    void deletePatient(Patient patient);
    List<Patient> getPatients();
    Patient getPatientById(Long id);
    List<Patient> searchPatientsByQuery(String query);

    void addConsultation(Consultation consultation);
    List<Consultation> getConsultations();
    Consultation getConsultationById(Long id);
    void updateConsultation(Consultation consultation);
    void deleteConsultation(Consultation consultation);
    List<Consultation> searchConsultationsByQuery(String query);

}
