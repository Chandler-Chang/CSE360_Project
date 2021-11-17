package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

import javafx.event.ActionEvent;

public class patientController {
	private Main main;
	private Patient patient;
	
	private ArrayList<Patient> PatientList = new ArrayList<Patient>(100);
	private ArrayList<Nurse> NurseList = new ArrayList<Nurse>(10);
	private ArrayList<Doctor> DoctorList = new ArrayList<Doctor>(10);

	
	//setMain controller method
	public void setMain(Main main) {
		this.main = main;
	}
	
	public void setLists(ArrayList<Patient> PatientList, ArrayList<Nurse> NurseList, ArrayList<Doctor> DoctorList) {
		this.PatientList = PatientList;
		this.NurseList = NurseList;
		this.DoctorList = DoctorList;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public void setData() {
		patientName.setText(patient.getFirst() + " " + patient.getLast());
		providerPlan.setText("Provider Plan: Premium");
		insuranceProvider.setText("Insurance Provider: Bikini Bottom Insurance");
		planNumber.setText("Plan Number: 21-12390");
		planExpirationDate.setText("Plan Expiration Date: 12/31/2026");
		patientInfoArea.setText("Date of Birth: " + patient.getBirthdate() + "\n\n" + "Gender: None of ya biz" + "\n\n" + "Address: TBD" + "\n\n" + "Phone Number: 123-456-7890");	
		pharmacyInformationBox.setText("Pharmacy: " + patient.getPharmacy() + "\n\n" + "Pharmacy Address: " + patient.getPharmacyAddress());
		
		PatientNode patientSummary = patient.getSummary();
		while(patientSummary != null) {
			summaryArea.setText(summaryArea.getText() + "Date: " + patientSummary.getDate() + "\n" + "Summary: " + patientSummary.getInfo() + "\n------------------------------------------------\n");
			patientSummary = patientSummary.getNext();
		}
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
