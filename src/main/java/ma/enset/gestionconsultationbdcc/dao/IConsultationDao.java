package ma.enset.gestionconsultationbdcc.dao;

import ma.enset.gestionconsultationbdcc.entities.Consultation;
import ma.enset.gestionconsultationbdcc.entities.Patient;

import java.sql.SQLException;
import java.util.List;

public interface IConsultationDao extends Dao<Consultation,Long>{
    List<Consultation> searchByQuery(String query) throws SQLException;
}
