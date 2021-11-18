package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class addRemoveController {
	private Main main;
	private Doctor doctor;
	
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
	
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	public void setData() {
		addRemoveBox.getItems().clear();
		
		addRemoveBox.getItems().addAll(
				"Add",
				"Remove"
				);
		
		addRemoveBox.setValue("Add");
	}
	
	@FXML
	private Button backButton, submitButton;
	@FXML
	private ComboBox<String> addRemoveBox;
	@FXML
	private TextField firstNameField, lastNameField, doctorField, birthdateField;
	
	@FXML
	public void handleBack(ActionEvent event) throws IOException {
		sendBackToDoctorPortal();
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
	
	@FXML
	public void handleSubmit(ActionEvent event) throws IOException {
		switch (addRemoveBox.getValue()) {
			case "Add":
				String input_firstName = firstNameField.getText().trim();
				String input_lastName = lastNameField.getText().trim();
				String input_doctor = doctorField.getText().trim();
				String input_date = birthdateField.getText().trim();
				
				if (input_firstName.isEmpty() || input_lastName.isEmpty() || input_doctor.isEmpty() || input_date.isEmpty()) {
					missingFieldError();
					break;
				}
				else {
					Patient patient = new Patient(input_firstName, input_lastName, "0000", "0000", 1000 + PatientList.size());
					int doctor_ID = Integer.parseInt(input_doctor);
					int doctor_Index = doctorFound(doctor_ID);
					System.out.println(doctor_Index);
					if (doctor_Index >= 0) {
						patient.setDoctor(doctor_ID);
						DoctorList.get(doctor_Index).addPatient(patient.getID());
						PatientList.add(patient);
						((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
						sendBackToDoctorPortal();
						break;
					}
					else {
						noDoctorFoundError();
						break;
					}
				}
			case "Remove":
				int index = patientFound();
				System.out.println(index);
				if (patientFound() >= 0) {
					PatientList.remove(index);
					((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
					sendBackToDoctorPortal();
					break;
				}
				else {
					patientNotFoundError();
					break;
				}
		}
	}
	
	//Search for patient in list, return true if found
	public int patientFound() {
		String input_firstName = firstNameField.getText().trim();
		String input_lastName = lastNameField.getText().trim();
		String input_date = birthdateField.getText().trim();
		for (int i = 0; i < PatientList.size(); i++) {
			Patient patient = PatientList.get(i);
			if (patient.getFirst().equals(input_firstName) && patient.getLast().equals(input_lastName) && patient.getBirthdate().equals(input_date)) {
				return i;
			}
		}
		return -1;
	}
	
	public int doctorFound(int doctorID) {
		for (int i = 0; i < DoctorList.size(); i++) {
			Doctor doctor = DoctorList.get(i);
			if (doctor.getID() == doctorID) {
				return i;
			}
		}
		return -1;
	}
	
	public void sendBackToDoctorPortal() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("doctorPortal.fxml"));
		Parent root = loader.load();
	
		doctorController doctorController = loader.getController();
		doctorController.setMain(this.main);
		doctorController.setLists(PatientList, NurseList, DoctorList);
		doctorController.setDoctor(doctor);
	
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Doctor Portal");
		stage.show();
		doctorController.setData();
	}
	
	//clear all fields
	public void clearFields() {
		firstNameField.clear();
		lastNameField.clear();
		doctorField.clear();
		birthdateField.clear();
	}
	
	public void patientNotFoundError() {
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setHeaderText("Patient Not Found");
		errorAlert.setContentText("No patient was found matching your input.");
		errorAlert.showAndWait();
	}
	
	public void missingFieldError() {
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setHeaderText("Missing Field");
		errorAlert.setContentText("One of the required fields was left blank.");
		errorAlert.showAndWait();
	}
	
	public void noDoctorFoundError() {
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setHeaderText("Doctor Not Found");
		errorAlert.setContentText("No doctor was found with that ID number.");
		errorAlert.showAndWait();
	}
}
