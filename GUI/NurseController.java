package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;

public class NurseController {
	private Main main;
	private Nurse nurse;
	private Patient patient = null;
	
	private ArrayList<Patient> PatientList = new ArrayList<Patient>(100);
	private ArrayList<Nurse> NurseList = new ArrayList<Nurse>(10);
	private ArrayList<Doctor> DoctorList = new ArrayList<Doctor>(10);
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	public void setLists(ArrayList<Patient> PatientList, ArrayList<Nurse> NurseList, ArrayList<Doctor> DoctorList) {
		this.PatientList = PatientList;
		this.NurseList = NurseList;
		this.DoctorList = DoctorList;
	}
	
	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	//to change any data
	public void setData() {
		if (patient != null) {
			patientsInfoArea.setText("Name: " + patient.getFirst() + " " + patient.getLast() + "\n" +
					"Gender: " + patient.getGender() + "\n" +
					"DOB: " + patient.getBirthdate() + "\n\n" +
					"Height: " + patient.getHeight()/12 + "'" + patient.getHeight() % 12 + "\n" +
					"Weight: " + patient.getWeight() + " lbs." + "\n" +
					"Allergic to: " + patient.getAllergies() + "\n\n" +
					"Patient's Pharmacy Name: " + patient.getPharmacy() + "\n" +
					"Pharmacy Address: " + patient.getPharmacyAddress()
			);
			PatientNode patientSummary = patient.getSummary();
			while(patientSummary != null) {
				AppHistoryArea.setText(AppHistoryArea.getText() + "Date: " + patientSummary.getDate() + "\n" + "Summary: " + patientSummary.getInfo() + "\n------------------------------------------------\n");
				patientSummary = patientSummary.getNext();
			}
			
			PatientNode immunizations = patient.getImmunization();
			while(immunizations != null) {
				ImmRecordsArea.setText(ImmRecordsArea.getText() + "Date: " + immunizations.getDate() + "\n" + "Summary: " + immunizations.getInfo() + "\n------------------------------------------------\n");
				immunizations = immunizations.getNext();
			}
		}	
	}
	//Patient's Info Tab
	@FXML
	private TextArea patientsInfoArea, ImmRecordsArea, AppHistoryArea;
	
	//Make a Vital Report Tab
	@FXML
	private DatePicker DateBox;
	@FXML
	private TextField patientFt, patientIn, patientWeightBox, patientTempBox, bloopTopBox, bloopBotBox;
	@FXML
	private CheckBox isOver12;
	//what happens when the checkbox (over 12) is checked, maybe we wont need this function
	@FXML
	public void handleOver12(ActionEvent event) {
		
	}
	
	//Everything outside of tabs
	@FXML
	private Button submitButton, editButton, searchPatientButton;
	@FXML
	private ListView<String> patientList;
	//Pops up the search patient window
	@FXML
	public void handleSeachPatient(ActionEvent event) throws IOException{
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Patient_Search.fxml"));
		Parent root = loader.load();
		
		patientSearchController searchController = loader.getController();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Patient Search");
		stage.show();
		searchController.setLists(PatientList, NurseList, DoctorList);
		searchController.setNurse(nurse);
	}
	@FXML
	public void handleSubmitButton(ActionEvent event) {
		if(!isOver12.isSelected()) {
			if (bloopTopBox.getText().trim().isEmpty() && bloopBotBox.getText().trim().isEmpty()) {
				setPatientVitals();
				resetPatientTab();
				System.out.println("Vitals successfully submitted.");
			}
			else {
				System.out.println("Patient is under 12; cannot have blood pressure recorded.");
			}
		}
		else {
			setPatientVitals();
			resetPatientTab();
			System.out.println("Vitals successfully submitted.");
		}
	}
	@FXML
	public void handleEditButton(ActionEvent event) {
		
	}
	
	public void setPatientVitals() {
		patient.setVitals("Patient Height: " + patientFt.getText() + "'" + patientIn.getText() + "\n"
				+ "Patient Weight: " + patientWeightBox.getText() + "lbs.\n"
				+ "Patient Temperature: " + patientTempBox.getText() + "degrees F\n"
				+ "Patient Blood Pressure: " + bloopTopBox.getText() + " over " + bloopBotBox.getText());
	}
	
	public void resetPatientTab() {
		patientFt.clear();
		patientIn.clear();
		patientWeightBox.clear();
		patientTempBox.clear();
		bloopTopBox.clear();
		bloopBotBox.clear();
		isOver12.setSelected(false);
	}
}
