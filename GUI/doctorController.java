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
	//Patient Info Tab
	@FXML
	private Button pullVitalsBttn;
	
	@FXML
	public void handlePullVitals(ActionEvent event) {
		
	}
	
	//Physical Test Tab
	
	
	//Make Prescription Tab
	
	
	//Main Tab
	@FXML
	private Button submitBttn;
	@FXML
	private Button searchPatientBttn;
	
	@FXML
	public void handleSubmit(ActionEvent event) {
		
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
	}
	//to change any data
	public void setData() {
		
	}

}
