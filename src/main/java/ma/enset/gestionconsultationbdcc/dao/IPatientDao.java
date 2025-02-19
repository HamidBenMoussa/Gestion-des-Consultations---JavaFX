package ma.enset.gestionconsultationbdcc.dao;

import ma.enset.gestionconsultationbdcc.entities.Patient;

import java.sql.SQLException;
import java.util.List;

public interface IPatientDao extends Dao<Patient,Long>{
     List<Patient> searchByQuery(String query) throws SQLException;
}
