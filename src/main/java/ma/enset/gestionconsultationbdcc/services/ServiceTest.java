package ma.enset.gestionconsultationbdcc.services;

import ma.enset.gestionconsultationbdcc.dao.ConsultationDao;
import ma.enset.gestionconsultationbdcc.dao.PatientDao;
import ma.enset.gestionconsultationbdcc.entities.Patient;

import java.sql.SQLException;
import java.util.List;

public class ServiceTest {
    public static void main(String[] args) {
        ICabinetService service = new CabinetService(new ConsultationDao(),new PatientDao());
        Patient patient = service.getPatientById(1L);
        patient.setNom(" Hamid");
        patient.setPrenom("benm-Moussq");
        patient.setTel("0696511751");
        service.updatePatient(patient);

        List<Patient> patients = service.getPatients();
        patients.forEach(System.out::println);

    }
}
