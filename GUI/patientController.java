package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;


import javafx.event.ActionEvent;

public class patientController {
	private Main main;
	
	//setMain controller method
	public void setMain(Main main) {
		this.main = main;
	}
	
	public void setData() {
		
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
	private TextArea previousArea, upcomingArea, summaryArea;
	
	//Ask the Doctor
}
