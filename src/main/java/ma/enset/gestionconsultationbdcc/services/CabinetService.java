package ma.enset.gestionconsultationbdcc.services;

import ma.enset.gestionconsultationbdcc.dao.ConsultationDao;
import ma.enset.gestionconsultationbdcc.dao.IConsultationDao;
import ma.enset.gestionconsultationbdcc.dao.IPatientDao;
import ma.enset.gestionconsultationbdcc.dao.PatientDao;
import ma.enset.gestionconsultationbdcc.entities.Consultation;
import ma.enset.gestionconsultationbdcc.entities.Patient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CabinetService implements ICabinetService {
    private IConsultationDao consultationDao;
    private IPatientDao patientDao;


    public CabinetService(IConsultationDao consultationDao, IPatientDao patientDao) {
        this.consultationDao = consultationDao;
        this.patientDao = patientDao;
    }

    @Override
    public void addPatient(Patient patient)  {
        try {
            patientDao.create(patient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePatient(Patient patient) {
        try {
            patientDao.update(patient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePatient(Patient patient) {
        try {
            patientDao.delete(patient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Patient> getPatients() {
        List<Patient> patients = null;
        try {
            patients =  patientDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patients;
    }

    @Override
    public Patient getPatientById(Long id) {
        Patient patient = null;
        try {
            patient = patientDao.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patient;
    }

    @Override
    public List<Patient> searchPatientsByQuery(String query) {
        try {
            return patientDao.searchByQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addConsultation(Consultation consultation) {
        try {
            consultationDao.create(consultation);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Consultation> getConsultations() {
       List<Consultation> consultations = null;
        try {
            consultations = consultationDao.findAll();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consultations;
    }

    @Override
    public Consultation getConsultationById(Long id) {
       Consultation consultation = null;
        try {
            consultation = consultationDao.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consultation;
    }

    @Override
    public void updateConsultation(Consultation consultation) {
        try {
            consultationDao.update(consultation);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteConsultation(Consultation consultation) {
        try {
            consultationDao.delete(consultation);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Consultation> searchConsultationsByQuery(String query) {
        try {
          return consultationDao.searchByQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
