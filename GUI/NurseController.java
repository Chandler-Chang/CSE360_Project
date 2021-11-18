package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;

public class NurseController {
	private Main main;
	
	public void setMain(Main main) {
		this.main = main;
	}
	//to change any data
	public void setData() {
		
	}
	//main tab
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Patient_Search.fxml"));
		Parent root = loader.load();
		
		patientSearchController searchController = loader.getController();
		searchController.setMain(this.main);
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Patient Search");
		stage.show();
	}
	@FXML
	public void handleSubmitButton(ActionEvent event) {
		
	}
	@FXML
	public void handleEditButton(ActionEvent event) {
		
	}
}
