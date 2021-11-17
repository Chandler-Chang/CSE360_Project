package application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


public class LoginController{
	//Interactive components of the screen
	@FXML
	private Button LoginButton;
	@FXML
	private ComboBox<String> portalComboBox;
	@FXML
	private PasswordField passwordTextField;
	@FXML
	private TextField UsernameTextField;
	
	private Main main;
	
	private ArrayList<Patient> PatientList = new ArrayList<Patient>(100);
	private ArrayList<Nurse> NurseList = new ArrayList<Nurse>(10);
	private ArrayList<Doctor> DoctorList = new ArrayList<Doctor>(10);
	
	public void setLists(ArrayList<Patient> PatientList, ArrayList<Nurse> NurseList, ArrayList<Doctor> DoctorList) {
		this.PatientList = PatientList;
		this.NurseList = NurseList;
		this.DoctorList = DoctorList;
	}
	
	//setMain controller method
	public void setMain(Main main) {
		this.main = main;
	}
	
	//Sets the data in the screen, in case we want to change any data shown in the screen
	public void setData() {
		portalComboBox.getItems().clear();
		
		portalComboBox.getItems().addAll(
				"Patient",
				"Nurse",
				"Doctor"
				);
		portalComboBox.setValue("Patient");
	}
	
	//This method is called whenever the log in button is clicked
	@FXML
	public void handleLoginButton(ActionEvent event) throws IOException{
		//Login verification
		switch(portalComboBox.getValue()) {
			case "Patient":
				sendToPatientPortal();
				break;
			case "Nurse":
				sendToNursePortal();
				break;
			case "Doctor":
				sendToDoctorPortal();
				break;
			}
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
	
	//sends to nursePortal
	private void sendToNursePortal() throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("NursePage.fxml"));
		Parent root = loader.load();
		
		NurseController nurseController = loader.getController();
		nurseController.setMain(this.main);
		nurseController.setLists(PatientList, NurseList, DoctorList);
		
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Nurse Portal");
		stage.show();
		nurseController.setData();
	}
	
	//sends to patientPortal
	private void sendToPatientPortal() throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Patient_Portal.fxml"));
		Parent root = loader.load();
		
		patientController patientController = loader.getController();
		patientController.setMain(this.main);
		patientController.setLists(PatientList, NurseList, DoctorList);
		
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Patient Portal");
		stage.show();
		patientController.setData();
	}
	
	//sends to doctorPortal
	private void sendToDoctorPortal() throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("doctorPortal.fxml"));
		Parent root = loader.load();
		
		doctorController doctorController = loader.getController();
		doctorController.setMain(this.main);
		doctorController.setLists(PatientList, NurseList, DoctorList);
		
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Doctor Portal");
		stage.show();
		doctorController.setData();
	}
}
