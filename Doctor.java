import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

class Doctor extends Person {
	private ArrayList<Integer> assignedNurses;
	private ArrayList<Integer> assignedPatients;
	private MessageNode messageHead;
	
	protected Doctor[] allDoctors = new Doctor[100]; //NEW ADDITION!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	// Default Doctor Constructor
	// Default Nurse/Patient Arrays equal to 10 per doctor
	public Doctor() {
		this.assignedNurses = new ArrayList<>(10);
		this.assignedPatients = new ArrayList<>(10);
	}
	
	// Constructor of Doctor first and last name, user name and password
	public Doctor (String First, String Last, String uname, String passwd, int ID) {
		super(First, Last, uname, passwd, ID);
		this.assignedNurses = new ArrayList<>(10);
		this.assignedPatients = new ArrayList<>(10);
	}


	
	// Add a singular nurse to the nurse array
	public boolean addNurse(int nurseID) {
		// If the doctor's nurse array is full, cannot add nurse, return false
		if (this.assignedNurses.size() == 10) {
			System.out.println("This doctor has too many nurses!");
			return false;
		}
		// If nurse currently assigned to this doctor, cannot add nurse, return false
		for (int i : this.assignedNurses)
			if (nurseID == i) {
				System.out.println("Nurse already exists!");
				return false;
			}
		
		// Add the nurse to first null element of the array
		this.assignedNurses.add(nurseID);
		return true;
	}
	
	// Searches nurse by first name and returns index of array they are at
	public int searchNurse(int nurseID) {
		for (int thisID : this.assignedNurses)
	        if (thisID == nurseID) return this.assignedNurses.indexOf(thisID);
	    return -1; 	// Nurse not found, return -1 (false)
	}
	
	// Removes nurse if they exist
	public boolean removeNurse(int nurseID) {
		int removeIndex = searchNurse(nurseID);
		if (removeIndex == -1) return false;
		this.assignedNurses.remove(removeIndex);
		return true;
	}
	
	
	// Print the array of nurses for the given doctor
	public void printNurses() {
		System.out.println("Doctor " + this.lastName + " has the following nurses:");
		for (int i : assignedNurses) System.out.println(i);
	}
	
	// Return the nurse array
	public ArrayList<Integer> getAssignedNurses() { return this.assignedNurses; }
	
	// Add a singular patient to the nurse array
	public boolean addPatient(int patientID) {
		// If the doctor's patient array is full, cannot add patient, return false
		if (this.assignedPatients.size() == 10) {
			System.out.println("This doctor has too many patients!");
			return false;
		}
		
		// If patient currently assigned to this doctor, cannot add patient, return false
		for (int i : assignedPatients)
			if (patientID == i) {
				System.out.println("Patient already exists!");
				return false;
			}
		
		// Add the patient to first null element of the array
		this.assignedPatients.add(patientID);
		return true;
	}
	
	// Searches patient by first name and returns index of array they are at
	public int searchPatient(int patientID) {
		for (int thisID : assignedPatients)
	        if (thisID == patientID) return this.assignedPatients.indexOf(thisID);
	    return -1;	// Patient not found, return -1 (false)
	}
	
	// Removes patient if they exist
	public boolean removePatient(int patientID) {
		int removeIndex = searchPatient(patientID);
		if (removeIndex == -1) return false;
		this.assignedPatients.remove(removeIndex);
		return true;
	}
	
	// Print the array of patients assigned the given doctor
	public void printPatients() {
		System.out.println("Doctor " + this.lastName + " has the following patients:");
		for (int i : assignedPatients) System.out.println(i);
	}
	
	// Return the patient array
	public ArrayList<Integer> getAssignedPatients() { return this.assignedPatients; }
	
	
	// Return Messages
	public MessageNode getMessages() { return this.messageHead; }
	
	
//	// Add doctor's message to the patient
//	public boolean addMessage(String date, String message) {
//		int patientIndex = searchPatient(patientID);
//		if (patientIndex == -1) 
//			return false; // Cannot communicate with non-assigned patient
//		
//		// No messages, initialize the conversation
//		if (this.messageHead == null) {
//			MessageNode node = new MessageNode(date, message, null, this.assignedPatients.get(patientIndex), this);
//			this.messageHead = node;
//			return true;
//		}
//		
//		// Messages exist, respond to the conversation
//		MessageNode node = new MessageNode(date, message, this.messageHead, this.assignedPatients[patientIndex], this);
//		this.messageHead = node;
//		return true;
//	}
	
	
	public String writeDoctorData(Doctor[] allDoctors) {	//NEW ADDITION!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		try {
			PrintStream outFile = new PrintStream(new File("doctorData.txt"));
			for(int i = 0; i < 100; i++) {					//CONTAINS ASSUMED MAXIMUM PERSONS
				outFile.println(allDoctors[i].getFirst());
				outFile.println(allDoctors[i].getLast());
				outFile.println(allDoctors[i].getUserName());
				outFile.println(allDoctors[i].getPassword());
				outFile.println(allDoctors[i].getID());
				ArrayList<Integer> nursesAssigned = new ArrayList<>(10);
				nursesAssigned = allDoctors[i].getAssignedNurses();
				for(int j = 0; j < nursesAssigned.size; j++) {
					outFile.print(nursesAssigned.get(j));
				}
				outFile.println();
				
				ArrayList<Integer> patientsAssigned = new ArrayList<>(10);
				patientsAssigned = allDoctors[i].getAssignedPatients();
				for(int j = 0; j < patientsAssigned.size; j++) {
					outFile.print(patientsAssigned.get(j));
				}
				outFile.println();
			}
			outFile.close();
			return("File written succesfully");
		} catch (FileNotFoundException e) {
			return("Error: Unable to open file for writing");
		}
	
	}
	
	public String readDoctorData() {	//NEW ADDITION!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		Scanner read = null;
		try {
			read = new Scanner(new File("doctorData.txt"));
			for(int i = 0; i < 100; i++) {
				String fName = read.nextLine();
				String lName = read.nextLine();
				String uName = read.nextLine();
				String passwd = read.nextLine();
				int ID = read.nextInt();
				Doctor data = new Doctor(fName, lName, uName, passwd, ID);
				String nurses = read.nextLine();
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
				}
				allDoctors[i] = data;
			}
			
			read.close();
			
			return "File read successfully";
		} catch (FileNotFoundException e) {
			return("Error: File not found");
		}
	
	}
	
}