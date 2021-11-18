package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import javafx.event.ActionEvent;

public class doctorController {
	private Main main;
	private Doctor doctor;
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
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	//to change any data
	public void setData() {
		if (patient != null) {
			patientInfoArea.setText("Name: " + patient.getFirst() + " " + patient.getLast() + "\n" +
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
				appHistoryArea.setText(appHistoryArea.getText() + "Date: " + patientSummary.getDate() + "\n" + "Summary: " + patientSummary.getInfo() + "\n------------------------------------------------\n");
				patientSummary = patientSummary.getNext();
			}
			
			PatientNode immunizations = patient.getImmunization();
			while(immunizations != null) {
				immunizationArea.setText(immunizationArea.getText() + "Date: " + immunizations.getDate() + "\n" + "Summary: " + immunizations.getInfo() + "\n------------------------------------------------\n");
				immunizations = immunizations.getNext();
			}
			
			PatientNode prescriptions = patient.getPrescription();
			while(prescriptions != null) {
				prescriptionArea.setText(prescriptionArea.getText() + "Date: " + prescriptions.getDate() + "\n" + "Prescription: " + prescriptions.getInfo() + "\n------------------------------------------------\n");
				prescriptions = prescriptions.getNext();
			}
		}
	}
	
	//Patient Info Tab
	@FXML
	private Button pullVitalsBttn;
	@FXML
	private TextArea patientInfoArea, immunizationArea, appHistoryArea;
	
	@FXML
	public void handlePullVitals(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("vitalsPage.fxml"));
		Parent root = loader.load();
		vitalsController vitalsController = loader.getController();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Vitals Report");
		stage.show();
		vitalsController.setPatient(patient);
		vitalsController.setVitals();
	}
	
	//Physical Test Tab
	@FXML
	private TextField physicalDateBox;
	@FXML
	private CheckBox functioningCBox, unresponsiveCBox;
	@FXML
	private TextArea earNoseArea, breathingArea, notesArea;
	
	//Make Prescription Tab
	@FXML
	private TextField dosageArea, prescriptionDate, prescriptionField, pharmacyAddress;
	@FXML
	private TextArea InstructionsArea, prescriptionArea;
	
	//Main Tab
	//@FXML
	//private ListView<String> patientList; //IDK if its a listview of strings or patients, you may try to change.
	@FXML
	private Button submitBttn, searchPatientBttn, addRemovePatient;
	@FXML
	public void handleSubmit(ActionEvent event) {
		if (patient == null) {
			clearPhysicalTab();
			clearPrescriptionTab();
			System.out.println("No patient connected to this operation.");
		}
		if (!notesArea.getText().trim().isEmpty()) {
			patient.addSummary(physicalDateBox.getText(), notesArea.getText());
			clearData();
			setData();
			clearPhysicalTab();
		}
		else if (!prescriptionField.getText().trim().isEmpty()) {
			patient.addPrescription(prescriptionDate.getText(), prescriptionField.getText() + " " + dosageArea.getText() + "mg");
			clearData();
			setData();
			clearPrescriptionTab();
		}
	}
	@FXML
	public void handleAddRemove(ActionEvent event) throws IOException{
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("addRemovePortal.fxml"));
		Parent root = loader.load();
		
		addRemoveController addRemoveController = loader.getController();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Add/Remove Patient");
		stage.show();
		addRemoveController.setData();
		addRemoveController.setLists(PatientList, NurseList, DoctorList);
	}
	//Pops up the search patient window
	@FXML
	public void handlePatientSearch(ActionEvent event) throws IOException{
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Patient_Search.fxml"));
		Parent root = loader.load();
		
		patientSearchController searchController = loader.getController();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Patient Search");
		stage.show();
		searchController.setLists(PatientList, NurseList, DoctorList);
		searchController.setDoctor(doctor);
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
    
    public void clearData() {
    	patientInfoArea.clear();
    	appHistoryArea.clear();
    	immunizationArea.clear();
    	prescriptionArea.clear();
    }
    
	public void clearPhysicalTab() {
		physicalDateBox.clear();
		functioningCBox.setSelected(false);
		unresponsiveCBox.setSelected(false);
		earNoseArea.clear();
		breathingArea.clear();
		notesArea.clear();
	}
	
	public void clearPrescriptionTab() {
		prescriptionField.clear();
		dosageArea.clear();
		prescriptionDate.clear();
		pharmacyAddress.clear();
		InstructionsArea.clear();
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
