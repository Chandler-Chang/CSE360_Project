package application;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class patientSearchController {
		private Main main;
		private Doctor doctor = null;
		private Nurse nurse = null;
		
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
		
		public void setNurse(Nurse nurse) {
			this.nurse = nurse;
		}
		
		@FXML
		private TextField birthdateBox;
		@FXML
		private Button searchButton, backButton;
		@FXML
		private TextField firstNameBox, lastNameBox;
		//Closes the window when back button is pressed. (Sometimes it caused an error)
		@FXML
		public void handleBack(ActionEvent event) throws IOException {
			((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
			backToDoctorPortal(null);
		}
		
		//When search button is clicked
		@FXML
		public void handleSearch(ActionEvent event) throws IOException {
			for (int i = 0; i < PatientList.size(); i++) {
				Patient patient = PatientList.get(i);
				if (patient.getFirst().equals(firstNameBox.getText()) && patient.getLast().equals(lastNameBox.getText()) && patient.getBirthdate().equals(birthdateBox.getText())) {
					if (nurse == null) {
						backToDoctorPortal(patient);
						((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); //close the search window
						return;
					}
					else if (doctor == null) {
						backToNursePortal(patient);
						((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
						return;
					}
				}
			}
			System.out.println("Patient not found"); //if patient not found, do not close, send error and reset text fields
		}
		
		//send to Doctor Portal
		public void backToDoctorPortal(Patient patient) throws IOException {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("doctorPortal.fxml"));
			Parent root = loader.load();
		
			doctorController doctorController = loader.getController();
			doctorController.setMain(this.main);
			doctorController.setLists(PatientList, NurseList, DoctorList);
			doctorController.setDoctor(doctor);
			doctorController.setPatient(patient);
		
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Doctor Portal");
			stage.show();
			doctorController.setData();
		}
		
		//send to Nurse Portal
		public void backToNursePortal(Patient patient) throws IOException {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("NursePage.fxml"));
			Parent root = loader.load();
			
			NurseController nurseController = loader.getController();
			nurseController.setMain(this.main);
			nurseController.setLists(PatientList, NurseList, DoctorList);
			nurseController.setNurse(nurse);
			nurseController.setPatient(patient);
			
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Nurse Portal");
			stage.show();
			nurseController.setData();
		}
}
