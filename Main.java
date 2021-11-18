/*Quick explanation on what all the different files mean
//Main.java just initializes the first stage/scene and pops up the login page
The fxml files are all the visuals/gui. Basically the gpu part of the code
The java files are the controllers of each page individually, the cpu part of the code */

package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	//Stage means window, scene is the content inside the window
	private Stage primaryStage;
	
	private ArrayList<Patient> PatientList = new ArrayList<Patient>(100);
	private ArrayList<Nurse> NurseList = new ArrayList<Nurse>(10);
	private ArrayList<Doctor> DoctorList = new ArrayList<Doctor>(10);
	
	//makes the window be the loginwindow
    @Override
    public void start(Stage primaryStage){
    	this.primaryStage = primaryStage;
    	System.out.println("Starting");
    	
    	System.out.println(readPatientData());
    	System.out.println(readNurseData());
    	System.out.println(readDoctorData());
    	
    	//readPatientData();
    	//readNurseData();
    	//readDoctorData();
    	
    	LoginWindow();
    	
    	//writePatientData();
    	//writeNurseData();
    	//writeDoctorData();
    }

    public void LoginWindow(){
    	try {
    		//loads the content of the loginpage.fxml file into a pane
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
    		AnchorPane pane = loader.load();
    		
    		//Calls the loginpage controller and sets it as main controller (I think, not really sure)
    		LoginController loginController = loader.getController();
    		loginController.setMain(this);
    		loginController.setLists(PatientList, NurseList, DoctorList);
    		
    		//puts everything inside the pane as a scene and shows it
    		Scene scene = new Scene(pane);
    		primaryStage.setScene(scene);
    		primaryStage.setTitle("Office Automation System (OAS)");
    		primaryStage.show();
    		
    		loginController.setData();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    

    public String readPatientData() {	//NEW: READ PATIENT FILE
		File file = new File("C:\\Users\\Chandler\\eclipse-workspace\\application\\src\\application\\patientData.txt");		
		try {
			
			Scanner read = new Scanner(file);
			
			while(read.hasNextLine()){
				
				String fName = read.nextLine();
				String lName = read.nextLine();
				String uName = read.nextLine();
				String passwd = read.nextLine();
				String IDasString = read.nextLine();
				int ID = Integer.parseInt(IDasString);
				String birthdate = read.nextLine();
				String pharmacy = read.nextLine();
				String pharmAddr = read.nextLine();
				String allergy = read.nextLine();
				String summary = read.nextLine();
				String immunization = read.nextLine();
				String prescription = read.nextLine();
				
				
				Patient data = new Patient(fName, lName, uName, passwd, ID);
				data.setBirthdate(birthdate);
		    	data.setPharmacy(pharmacy);
		    	data.setPharmacyAddress(pharmAddr);
		    	String[] allAllergies = allergy.split("\\|");
		    	for(int i = 0; i < allAllergies.length; i++) {
		    		data.addAllergies(allAllergies[i]);
		    	}
		    	String[] allSummaries = summary.split("\\|");
		    	for(int i = 0; i < allSummaries.length; i++) {
		    		if(allSummaries[i].equals("none")) {
			    		break;
			    	}
		    		data.addSummary(allSummaries[i], allSummaries[i+1]);
		    		i++;
		    	}
		    	String[] allImmunizations = immunization.split("\\|");
		    	for(int i = 0; i < allImmunizations.length; i++) {
		    		if(allImmunizations[i].equals("none")) {
			    		break;
			    	}
		    		data.addImmunization(allImmunizations[i], allImmunizations[i+1]);
		    		i++;
		    	}
		    	String[] allPrescriptions = prescription.split("\\|");
		    	for(int i = 0; i < allPrescriptions.length; i++) {
		    		if(allPrescriptions[i].equals("none")) {
			    		break;
			    	}
		    		data.addPrescription(allPrescriptions[i], allPrescriptions[i+1]);
		    		i++;
		    	}
		    	
				/*int assignedDoc = read.nextInt();  Was before Patient data = yadayadayada
				
				data.setDoctor(assignedDoc);
				data.setBirthdate(read.nextLine());
				data.setPharmacy(read.nextLine());
				data.setPharmacyAddress(read.nextLine());
				data.setInsurance(read.nextLine());
				data.addAllergies(read.nextLine());*/
				
				PatientList.add(data);		//NEW: add this patient to PatientList
			}
			
			read.close();
			
			return "File read successfully";
		} catch (FileNotFoundException e) {
			return("Error: File not found");
		}
	
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
		    		} while(summaryNode.getNext() != null);
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
		    		} while(immunizationNode.getNext() != null);
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
		    		} while(precriptionNode.getNext() != null);
		    	}
		    	outFile.print(prescriptions);
			}
			outFile.close();
			return("File written succesfully");
		} catch (FileNotFoundException e) {
			return("Error: Unable to open file for writing");
		}
	
	}
    
    public String readNurseData() {		//NEW: READ NURSE FILE
    	File file = new File("C:\\Users\\Chandler\\eclipse-workspace\\application\\src\\application\\nurseData.txt");		
		try {
			
			Scanner read = new Scanner(file);
			
			while(read.hasNextLine()){
				
				String fName = read.nextLine();
				String lName = read.nextLine();
				String uName = read.nextLine();
				String passwd = read.nextLine();
				String IDasString = read.nextLine();
				int ID = Integer.parseInt(IDasString);
				
				//int assignedDoc = read.nextInt();
				
				Nurse data = new Nurse(fName, lName, uName, passwd, ID);
				
				//data.setDoctor(assignedDoc);
				NurseList.add(data);	//NEW: add this nurse to the ArrayList
				
				
			} 
			
			read.close();
			
			return "File read successfully";
		} catch (FileNotFoundException e) {
			return("Error: File not found");
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
				outFile.println(NurseList.get(i).getID());
				
				
			}
			outFile.close();
			return("File written succesfully");
		} catch (FileNotFoundException e) {
			return("Error: Unable to open file for writing");
		}
	
	}
    
    public String readDoctorData() {	//NEW: READ DOCTOR FILE
    	File file = new File("C:\\Users\\Chandler\\eclipse-workspace\\application\\src\\application\\doctorData.txt");		
		try {
			
			Scanner read = new Scanner(file);
			int i = 0;	//INDICATOR FOR PATIENT AND DOCTOR
			while(read.hasNextLine()){
				String fName = read.nextLine();
				String lName = read.nextLine();
				String uName = read.nextLine();
				String passwd = read.nextLine();
				String IDasString = read.nextLine();
				int ID = Integer.parseInt(IDasString);
				Doctor data = new Doctor(fName, lName, uName, passwd, ID);
				/*String nurses = read.nextLine();
				int a = 0; int b = 4;
				while(b < nurses.length()) {
					String IDasString = nurses.substring(a, b);
					int IDasInt = Integer.parseInt(IDasString);
					data.addNurse(IDasInt);
					a++;
					b = a * b;
				}
				String patients = read.nextLine();
				a = 0; b = 4;
				while(b < patients.length()) {
					String IDasString = patients.substring(a, b);
					int IDasInt = Integer.parseInt(IDasString);
					data.addPatient(IDasInt);
					a++;
					b = a * b;
				}*/
				DoctorList.add(data);	//NEW: add this doctor to the ArrayList.
				data.addPatient(PatientList.get(i).getID());
		    	data.addNurse(NurseList.get(i).getID());
		    	NurseList.get(i).setDoctor(data.getID());
		    	i++;
			}
			
			read.close();
			
			return "File read successfully";
		} catch (FileNotFoundException e) {
			return("Error: Doctor file not found");
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
				outFile.println(DoctorList.get(i).getID());
				
				
			}
			outFile.close();
			return("File written succesfully");
		} catch (FileNotFoundException e) {
			return("Error: Unable to open file for writing");
		}
	
	}
    
    public void generatePatients() {
    	readPatientData();
    	/*Patient patient = new Patient("Spongebob", "Squarepants", "ssquarepants", "gary", 1000);
    	PatientList.add(patient);
    	patient.setBirthdate("04/14/1992");
    	patient.setPharmacy("Krusty Krab");
    	patient.setPharmacyAddress("123 Shell Lane");
    	patient.addAllergies("Peanuts");
    	patient.addSummary("12/11/2022", "Ingrown toenail, scheduled for surgery on 01/03/2021.");
    	patient.addSummary("01/01/2022", "Got too drunk on New Years");
    	patient.addImmunization("02/14/2019", "Tetanus");
    	patient.addImmunization("03/17/2020", "Moderna COVID-19");
    	
    	
    	Patient patient2 = new Patient("Patrick", "Star", "pstar", "patricia", 1001);
    	patient2.setBirthdate("07/24/1990");
    	patient2.setPharmacy("Chum Bucket");
    	patient2.setPharmacyAddress("104 Shell Lane");
    	patient2.addAllergies("Krabby Patties");
    	patient2.addAllergies("Jellyfish");
    	patient2.addSummary("12/11/2022", "Ingrown toenail, scheduled for surgery on 01/03/2021.");
    	patient2.addSummary("01/01/2022", "Got too drunk on New Years");
    	patient2.addImmunization("02/14/2019", "Tetanus");
    	patient2.addImmunization("03/17/2020", "Moderna COVID-19");
    	PatientList.add(patient2);*/
    }
    
    public void generateNurses() {
    	readNurseData();
    	/*Nurse nurse = new Nurse("James", "Morgan", "jmorgan", "weezing", 2000);
    	NurseList.add(nurse);
    	
    	Nurse nurse2 = new Nurse("Jessie", "Murrow", "jmurrow", "arbok", 2001);
    	NurseList.add(nurse2);*/
    }

    public void generateDoctors() {
    	readDoctorData();
    	/*Doctor doctor = new Doctor("James", "Sullivan", "jsullivan", "boo", 3000);
    	DoctorList.add(doctor);
    	doctor.addPatient(PatientList.get(0).getID());
    	doctor.addNurse(NurseList.get(0).getID());
    	NurseList.get(0).setDoctor(doctor.getID());
    	
    	Doctor doctor2 = new Doctor("Mike", "Wazowski", "mwazowski", "selia", 3001);
    	DoctorList.add(doctor2);
    	doctor2.addPatient(PatientList.get(1).getID());
    	doctor2.addNurse(NurseList.get(1).getID());
    	NurseList.get(1).setDoctor(doctor2.getID());*/
    }
    
}