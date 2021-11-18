package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;

public class doctorController {
	private Main main;
	
	public void setMain(Main main) {
		this.main = main;
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
	@FXML
	private TextField physicalDateBox;
	
	//Make Prescription Tab
	@FXML
	private TextField prescriptionArea, dosageArea, patientName, patientBirth, pharmasyAddress, prescriptionDate, pharmacyAddress;
	@FXML
	private TextArea InstructionsArea;
	
	//Main Tab
	@FXML
	private ListView<String> patientList; //IDK if its a listview of strings or patients, you may try to change.
	@FXML
	private Button submitBttn, searchPatientBttn, addRemovePatient, signoutButton;
	@FXML
	public void handleSignout(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
		Parent root = loader.load();
		
		LoginController loginController = loader.getController();
		loginController.setMain(this.main);
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Office Aumation System (OAS)");
		stage.show();
		loginController.setData();
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
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
