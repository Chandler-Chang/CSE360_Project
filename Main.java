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
    	
    	generatePatients();
    	generateNurses();
    	generateDoctors();
    	
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
		File file = new File("C:\\Users\\ap4go\\eclipse-workspace\\CSE360Project\\src\\application\\patientData.txt");		
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
				
				Patient data = new Patient(fName, lName, uName, passwd, ID);
				data.setBirthdate(birthdate);
		    	data.setPharmacy(pharmacy);
		    	data.setPharmacyAddress(pharmAddr);
		    	data.addAllergies(allergy);
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
    
    public String writePatientData(Patient[] allPatients) {		//NEW: WRITE PATIENT FILE, NEEDS TO BE REWRITTEN FOR ARRAYLIST PatientList
		try {
			PrintStream outFile = new PrintStream(new File("patientData.txt"));
			for(int i = 0; i < 100; i++) {					//CONTAINS ASSUMED MAXIMUM PERSONS
				outFile.println(allPatients[i].getFirst());
				outFile.println(allPatients[i].getLast());
				outFile.println(allPatients[i].getUsername());
				outFile.println(allPatients[i].getPassword());
				outFile.println(allPatients[i].getID());
				outFile.println(allPatients[i].getDoc());
				outFile.println(allPatients[i].getBirthdate());
				outFile.println(allPatients[i].getPharmacy());
				outFile.println(allPatients[i].getPharmacyAddress());
				outFile.println(allPatients[i].getInsurance());
				outFile.println(allPatients[i].getAllergies());
				outFile.println();
			}
			outFile.close();
			return("File written succesfully");
		} catch (FileNotFoundException e) {
			return("Error: Unable to open file for writing");
		}
	
	}
    
    public String readNurseData() {		//NEW: READ NURSE FILE
    	File file = new File("C:\\Users\\ap4go\\eclipse-workspace\\CSE360Project\\src\\application\\nurseData.txt");		
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
    
    public String writeNurseData(Nurse[] allNurses) {	//NEW: WRITE NURSE FILE, NEEDS TO BE REWRITTEN FOR ARRAYLIST NurseList
		try {
			PrintStream outFile = new PrintStream(new File("nurseData.txt"));
			for(int i = 0; i < 100; i++) {					//CONTAINS ASSUMED MAXIMUM PERSONS
				outFile.println(allNurses[i].getFirst());
				outFile.println(allNurses[i].getLast());
				outFile.println(allNurses[i].getUsername());
				outFile.println(allNurses[i].getPassword());
				outFile.println(allNurses[i].getID());
				outFile.println(allNurses[i].getDoc());
				outFile.println();
			}
			outFile.close();
			return("File written succesfully");
		} catch (FileNotFoundException e) {
			return("Error: Unable to open file for writing");
		}
	
	}
    
    public String readDoctorData() {	//NEW: READ DOCTOR FILE
    	File file = new File("C:\\Users\\ap4go\\eclipse-workspace\\CSE360Project\\src\\application\\doctorData.txt");		
		try {
			
			Scanner read = new Scanner(file);
			
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
			}
			
			read.close();
			
			return "File read successfully";
		} catch (FileNotFoundException e) {
			return("Error: Doctor file not found");
		}
	
	}
    
    public String writeDoctorData(Doctor[] allDoctors) {	//NEW: WRITE DOCTOR FILE, NEEDS TO BE REWRITTEN FOR ARRAYLIST DoctorList
		try {
			PrintStream outFile = new PrintStream(new File("doctorData.txt"));
			for(int i = 0; i < 100; i++) {					//CONTAINS ASSUMED MAXIMUM PERSONS
				outFile.println(allDoctors[i].getFirst());
				outFile.println(allDoctors[i].getLast());
				outFile.println(allDoctors[i].getUsername());
				outFile.println(allDoctors[i].getPassword());
				outFile.println(allDoctors[i].getID());
				ArrayList<Integer> nursesAssigned = new ArrayList<>(10);
				nursesAssigned = allDoctors[i].getAssignedNurses();
				for(int j = 0; j < nursesAssigned.size(); j++) {
					outFile.print(nursesAssigned.get(j));
				}
				outFile.println();
				
				ArrayList<Integer> patientsAssigned = new ArrayList<>(10);
				patientsAssigned = allDoctors[i].getAssignedPatients();
				for(int j = 0; j < patientsAssigned.size(); j++) {
					outFile.print(patientsAssigned.get(j));
				}
				outFile.println();
			}
			outFile.close();
			return("File written successfully");
		} catch (FileNotFoundException e) {
			return("Error: Unable to open Doctor file for writing");
		}
	
	}
    
    public void generatePatients() {
    	//readPatientData();
    	
    	Patient patient = new Patient("Spongebob", "Squarepants", "ssquarepants", "gary", 1000);
    	PatientList.add(patient);
    	patient.setBirthdate("04/14/1992");
    	patient.setPharmacy("Krusty Krab");
    	patient.setPharmacyAddress("123 Shell Lane");
    	patient.addAllergies("Peanuts");
    	
    	Patient patient2 = new Patient("Patrick", "Star", "pstar", "patricia", 1001);
    	patient2.setBirthdate("07/24/1990");
    	patient2.setPharmacy("Chum Bucket");
    	patient2.setPharmacyAddress("104 Shell Lane");
    	patient2.addAllergies("Krabby Patties");
    	patient2.addAllergies("Jellyfish");
    	PatientList.add(patient2);
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