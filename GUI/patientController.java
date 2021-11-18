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
		patientInfoArea.setText("Date of Birth: " + patient.getBirthdate() + "\n\n" + "Gender: " + patient.getGender() + "\n\n" + "Address: TBD" + "\n\n" + "Phone Number: 123-456-7890" + "\n\n" + "Allergies: " + patient.getAllergies());	
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
	private TextArea summaryArea;
	
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
