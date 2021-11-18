package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import javafx.event.ActionEvent;

public class NurseController {
	private Main main;
	private Nurse nurse;
	private Patient patient = null;
	
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
	
	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	//to change any data
	public void setData() {
		if (patient != null) {
			patientsInfoArea.setText("Name: " + patient.getFirst() + " " + patient.getLast() + "\n" +
					"Gender: " + patient.getGender() + "\n" +
					"DOB: " + patient.getBirthdate() + "\n\n" +
					"Height: " + patient.getHeight()/12 + "'" + patient.getHeight() % 12 + "\n" +
					"Weight: " + patient.getWeight() + " lbs." + "\n" +
					"Allergic to: " + patient.getAllergies() + "\n\n" +
					"Patient's Pharmacy Name: " + patient.getPharmacy() + "\n" +
					"Pharmacy Address: " + patient.getPharmacyAddress()
			);
			PatientNode patientSummary = patient.getSummary();
			while(patientSummary != null) {
				AppHistoryArea.setText(AppHistoryArea.getText() + "Date: " + patientSummary.getDate() + "\n" + "Summary: " + patientSummary.getInfo() + "\n------------------------------------------------\n");
				patientSummary = patientSummary.getNext();
			}
			
			PatientNode immunizations = patient.getImmunization();
			while(immunizations != null) {
				ImmRecordsArea.setText(ImmRecordsArea.getText() + "Date: " + immunizations.getDate() + "\n" + "Summary: " + immunizations.getInfo() + "\n------------------------------------------------\n");
				immunizations = immunizations.getNext();
			}
		}	
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
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Patient_Search.fxml"));
		Parent root = loader.load();
		
		patientSearchController searchController = loader.getController();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Patient Search");
		stage.show();
		searchController.setLists(PatientList, NurseList, DoctorList);
		searchController.setNurse(nurse);
	}
	@FXML
	public void handleSubmitButton(ActionEvent event) {
		if (patient == null) {
			resetPatientTab();
			showPatientError();
			return;
		}
		if(!isOver12.isSelected()) {
			if (bloopTopBox.getText().trim().isEmpty() && bloopBotBox.getText().trim().isEmpty()) {
				setPatientVitals();
				resetPatientTab();
				System.out.println("Vitals successfully submitted.");
			}
			else {
				showOver12Error();
			}
		}
		else {
			setPatientVitals();
			resetPatientTab();
			System.out.println("Vitals successfully submitted.");
		}
	}
	@FXML
	public void handleEditButton(ActionEvent event) {
		
	}
	
	@FXML
    private Button signoutButton;
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
        loginController.setLists(PatientList, NurseList, DoctorList);
        
        writePatientData(PatientList);
        writeNurseData(NurseList);
        writeDoctorData(DoctorList);
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
	
	public void setPatientVitals() {
		patient.setVitals("Patient Height: " + patientFt.getText() + "'" + patientIn.getText() + "\n"
				+ "Patient Weight: " + patientWeightBox.getText() + "lbs.\n"
				+ "Patient Temperature: " + patientTempBox.getText() + "degrees F\n"
				+ "Patient Blood Pressure: " + bloopTopBox.getText() + " over " + bloopBotBox.getText());
	}
	
	public void resetPatientTab() {
		patientFt.clear();
		patientIn.clear();
		patientWeightBox.clear();
		patientTempBox.clear();
		bloopTopBox.clear();
		bloopBotBox.clear();
		isOver12.setSelected(false);
	}
	
	public void showOver12Error() {
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setHeaderText("Over12 Error");
		errorAlert.setContentText("You cannot record blood pressure if the patient is under 12 years old.");
		errorAlert.showAndWait();
	}
	
	public void showPatientError() {
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setHeaderText("No Patient");
		errorAlert.setContentText("No patient is connected to this operation.");
		errorAlert.showAndWait();
	}
	
	public String writePatientData(ArrayList<Patient> PatientList) {		
    	File file = new File("C:\\Users\\Chandler\\eclipse-workspace\\application\\src\\application\\patientData.txt");
		try {
			PrintStream outFile = new PrintStream(file);
			for(int i = 0; i < PatientList.size(); i++) {					//CONTAINS ASSUMED MAXIMUM PERSONS
				outFile.println(PatientList.get(i).getFirst());
				outFile.println(PatientList.get(i).getLast());
				outFile.println(PatientList.get(i).getUsername());
				outFile.println(PatientList.get(i).getPassword());
				outFile.println(PatientList.get(i).getID());
				//outFile.println(allPatients[i].getDoc());
				outFile.println(PatientList.get(i).getBirthdate());
				outFile.println(PatientList.get(i).getPharmacy());
				outFile.println(PatientList.get(i).getPharmacyAddress());
				//outFile.println(allPatients[i].getInsurance());
				String allergies = PatientList.get(i).getAllergies();
				String[] allAllergies = allergies.split(", ");
				String allergyFormat = "";
		    	for(int j = 0; j < allAllergies.length; j++) {
		    		allergyFormat = allergyFormat + allAllergies[j] + "|";
		    	}
		    	outFile.println(allergyFormat);
		    	
		    	String summaries = "";
		    	PatientNode summaryNode = PatientList.get(i).getSummary();
		    	if(summaryNode == null) {
		    		summaries += "none" + "|";
		    	}
		    	else {
		    		do {
		    			summaries += summaryNode.getDate() + "|";
		    			summaries += summaryNode.getInfo() + "|";
		    			summaryNode = summaryNode.getNext();
		    		} while(summaryNode != null);
		    	}
		    	outFile.println(summaries);
		    	
		    	String immunizations = "";
		    	PatientNode immunizationNode = PatientList.get(i).getImmunization();
		    	if(immunizationNode == null) {
		    		immunizations += "none" + "|";
		    	}
		    	else {
		    		do {
		    			immunizations += immunizationNode.getDate() + "|";
		    			immunizations += immunizationNode.getInfo() + "|";
		    			immunizationNode = immunizationNode.getNext();
		    		} while(immunizationNode != null);
		    	}
		    	outFile.println(immunizations);
		    	
		    	String prescriptions = "";
		    	PatientNode precriptionNode = PatientList.get(i).getPrescription();
		    	if(precriptionNode == null) {
		    		prescriptions += "none" + "|";
		    	}
		    	else {
		    		do {
		    			prescriptions += precriptionNode.getDate() + "|";
		    			prescriptions += precriptionNode.getInfo() + "|";
		    			precriptionNode = precriptionNode.getNext();
		    		} while(precriptionNode != null);
		    	}
		    	if (i + 1 == PatientList.size()) {
		    		outFile.print(prescriptions);
		    	}
		    	else {
		    		outFile.println(prescriptions);
		    	}
			}
			outFile.close();
			return("File written succesfully");
		} catch (FileNotFoundException e) {
			return("Error: Unable to open file for writing");
		}
	
	}
	
	public String writeNurseData(ArrayList<Nurse> NurseList) {	//NEW: WRITE NURSE FILE, NEEDS TO BE REWRITTEN FOR ARRAYLIST NurseList
    	File file = new File("C:\\Users\\Chandler\\eclipse-workspace\\application\\src\\application\\nurseData.txt");
		try {
			PrintStream outFile = new PrintStream(file);
			for(int i = 0; i < NurseList.size(); i++) {					//CONTAINS ASSUMED MAXIMUM PERSONS
				outFile.println(NurseList.get(i).getFirst());
				outFile.println(NurseList.get(i).getLast());
				outFile.println(NurseList.get(i).getUsername());
				outFile.println(NurseList.get(i).getPassword());
				
				if (i + 1 == NurseList.size()) {
					outFile.print(NurseList.get(i).getID());
				}
				else {
					outFile.println(NurseList.get(i).getID());
				}
			}
			outFile.close();
			return("File written succesfully");
		} catch (FileNotFoundException e) {
			return("Error: Unable to open file for writing");
		}
	
	}
	
	public String writeDoctorData(ArrayList<Doctor> DoctorList) {	//NEW: WRITE DOCTOR FILE, NEEDS TO BE REWRITTEN FOR ARRAYLIST DoctorList
		   File file = new File("C:\\Users\\Chandler\\eclipse-workspace\\application\\src\\application\\doctorData.txt");
			try {
				PrintStream outFile = new PrintStream(file);
				for(int i = 0; i < DoctorList.size(); i++) {					//CONTAINS ASSUMED MAXIMUM PERSONS
					outFile.println(DoctorList.get(i).getFirst());
					outFile.println(DoctorList.get(i).getLast());
					outFile.println(DoctorList.get(i).getUsername());
					outFile.println(DoctorList.get(i).getPassword());
					
					if (i + 1 == DoctorList.size()) {
						outFile.print(DoctorList.get(i).getID());
					}
					else {
						outFile.println(DoctorList.get(i).getID());
					}			
				}
				outFile.close();
				return("File written succesfully");
			} catch (FileNotFoundException e) {
				return("Error: Unable to open file for writing");
			}
			
	}
}
