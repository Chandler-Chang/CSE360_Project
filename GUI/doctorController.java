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

public class doctorController {
	private Main main;
	
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
	
	//to change any data
	public void setData() {
		
	}
	//Patient Info Tab
	@FXML
	private Button pullVitalsBttn;
	@FXML
	private TextArea patientInfoArea, immunizationArea, appHistoryArea;
	@FXML
	public void handlePullVitals(ActionEvent event) {
		
	}
	
	//Physical Test Tab
	@FXML
	private CheckBox functioningCBox, unresponsiveCBox;
	@FXML
	private TextArea earNoseArea, breathingArea, notesArea;
	
	//Make Prescription Tab
	@FXML
	private TextField prescriptionArea, dosageArea, patientName, patientBirth, pharmasyAddress;
	@FXML
	private TextArea InstructionsArea;
	
	//Main Tab
	@FXML
	private ListView<String> patientList; //IDK if its a listview of strings or patients, you may try to change.
	@FXML
	private Button submitBttn, searchPatientBttn, addRemovePatient;
	@FXML
	public void handleSubmit(ActionEvent event) {
		
	}
	@FXML
	public void handleAddRemove(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("addRemovePortal.fxml"));
		Parent root = loader.load();
		
		addRemoveController addRemoveController = loader.getController();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Add/Remove Patient");
		stage.show();
		addRemoveController.setData();
	}
	//Pops up the search patient window
	@FXML
	public void handlePatientSearch(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Patient_Search.fxml"));
		Parent root = loader.load();
		
		patientSearchController searchController = loader.getController();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Patient Search");
		stage.show();
		searchController.setData();
	}
}
