package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;

public class patientController {
	private Main main;
	
	//setMain controller method
	public void setMain(Main main) {
		this.main = main;
	}
	
	public void setData() {
		
	}
	//Main tab
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
	//Medical Information Tab
	@FXML
	private Label providerPlan, insuranceProvider, planNumber, planExpirationDate;
	@FXML
	private TextArea pharmacyInformationBox;
	@FXML
	private Button editInfoButton;
	@FXML
	public void handleEditInfo(ActionEvent event) {
		
	}
	
	//Patient Info Tab
	@FXML
	private Label patientName;
	@FXML
	private TextArea patientInfoArea;
	@FXML
	private Button editButton;
	@FXML
	public void handleEditButton(ActionEvent event) {
		
	}
	
	//Appointment History Tab
	@FXML
	private TextArea summaryArea;
	
	//Ask the Doctor
}
