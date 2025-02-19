package ma.enset.gestionconsultationbdcc.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import ma.enset.gestionconsultationbdcc.dao.ConsultationDao;
import ma.enset.gestionconsultationbdcc.dao.PatientDao;
import ma.enset.gestionconsultationbdcc.entities.Patient;
import ma.enset.gestionconsultationbdcc.services.CabinetService;
import ma.enset.gestionconsultationbdcc.services.ICabinetService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    @FXML private TextField textFieldNom;
    @FXML private TextField textFieldPrenom;
    @FXML private TextField textFieldTel;
    @FXML private TextField textFieldSearch;
    @FXML private TableView<Patient> tablePatients;
    @FXML private TableColumn<Patient, Long> columnId;
    @FXML private TableColumn<Patient, String> columnNom;
    @FXML private TableColumn<Patient, String> columnPrenom;
    @FXML private TableColumn<Patient, String> columnTel;
    @FXML private Label labelSearchState;
    private ICabinetService cabinetService;
    private ObservableList<Patient> patients = FXCollections.observableArrayList();
    private Patient selectedPatient;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cabinetService = new CabinetService(new ConsultationDao(), new PatientDao());

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        columnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        columnTel.setCellValueFactory(new PropertyValueFactory<>("tel"));

        tablePatients.setItems(patients);
        loadPatients();

        // Search functionality
        textFieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            patients.setAll(cabinetService.searchPatientsByQuery(newValue));
        });

        // Handle table row selection
        tablePatients.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                textFieldNom.setText(newValue.getNom());
                textFieldPrenom.setText(newValue.getPrenom());
                textFieldTel.setText(newValue.getTel());
                selectedPatient = newValue;
            }
        });
    }

    private void loadPatients() {
        patients.setAll(cabinetService.getPatients());
    }

    public void ajouterPatient() {
        String nom = textFieldNom.getText();
        String prenom = textFieldPrenom.getText();
        String tel = textFieldTel.getText();

        if (nom.isEmpty() || prenom.isEmpty() || tel.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText("Tous les champs sont obligatoires");
            alert.setContentText("Veuillez remplir le nom, le prénom et le téléphone du patient.");
            alert.showAndWait();
            return;
        }

        Patient patient = new Patient();
        patient.setNom(nom);
        patient.setPrenom(prenom);
        patient.setTel(tel);

        try {
            cabinetService.addPatient(patient);
            labelSearchState.setText("Le patient a été ajouté avec succès.");

            // Clear status label after 3 seconds
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.seconds(3),
                    event -> labelSearchState.setText("")
            ));
            timeline.setCycleCount(1);
            timeline.play();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur d'ajout");
            alert.setHeaderText("Échec de l'ajout du patient");
            alert.setContentText("Une erreur s'est produite lors de l'ajout du patient : " + e.getMessage());
            alert.showAndWait();
        }

        loadPatients();
        clearTextFields();
    }

    public void delPatient() {
        Patient patient = tablePatients.getSelectionModel().getSelectedItem();
        if (patient == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Aucun patient sélectionné");
            alert.setContentText("Veuillez sélectionner un patient à supprimer.");
            alert.showAndWait();
            return;
        }

        cabinetService.deletePatient(patient);
        labelSearchState.setText("Le patient a été supprimé avec succès.");

        // Clear status label after 3 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            labelSearchState.setText("");
        }));
        timeline.setCycleCount(1);
        timeline.play();

        loadPatients();
    }

    public void modifierPatient() {
        if (selectedPatient == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Aucun patient sélectionné");
            alert.setContentText("Veuillez sélectionner un patient à modifier.");
            alert.showAndWait();
        } else {
            String nom = textFieldNom.getText();
            String prenom = textFieldPrenom.getText();
            String tel = textFieldTel.getText();

            if (nom.isEmpty() || prenom.isEmpty() || tel.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Champs manquants");
                alert.setHeaderText("Tous les champs sont obligatoires");
                alert.setContentText("Veuillez remplir le nom, le prénom et le téléphone du patient.");
                alert.showAndWait();
                return;
            }

            selectedPatient.setNom(nom);
            selectedPatient.setPrenom(prenom);
            selectedPatient.setTel(tel);
            cabinetService.updatePatient(selectedPatient);
            labelSearchState.setText("Le patient a été modifié avec succès.");

            // Clear status label after 3 seconds
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
                labelSearchState.setText("");
            }));
            timeline.setCycleCount(1);
            timeline.play();

            loadPatients();
            clearTextFields();
            selectedPatient = null;
        }
    }

    private void clearTextFields() {
        textFieldNom.setText("");
        textFieldPrenom.setText("");
        textFieldTel.setText("");
    }
}