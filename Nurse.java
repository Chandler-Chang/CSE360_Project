import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

// Nurse Class
// Defines all features that a nurse should implement
class Nurse extends Person {
	protected Nurse[] allNurses = new Nurse[100]; //NEW ADDITION!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	// Only nurse has assigned doctor
	private Doctor assignedDoctor;

	// Default Nurse Constructor
	public Nurse() { this.assignedDoctor = null; }
	
	// Constructor of Nurse with first and last name, user name and password
	public Nurse(String First, String Last, String uname, String passwd, int ID) {
		super(First, Last, uname, passwd, ID);
		this.assignedDoctor = null;
	}
	
	// Change Nurse's assigned doctor if doctor can successfully add them to the list
	public void setDoctor(Doctor doctor) {
		if (doctor == null) this.assignedDoctor = null;    
		if (doctor.addNurse(this.ID))
			this.assignedDoctor = doctor; 
	}

	
	// Returns Nurse's assigned doctor
	public Doctor getDoc() { return this.assignedDoctor; }
	
	
	
	public String writeNurseData(Nurse[] allNurses) {	//NEW ADDITION!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		try {
			PrintStream outFile = new PrintStream(new File("nurseData.txt"));
			for(int i = 0; i < 100; i++) {					//CONTAINS ASSUMED MAXIMUM PERSONS
				outFile.println(allNurses[i].getFirst());
				outFile.println(allNurses[i].getLast());
				outFile.println(allNurses[i].getUserName());
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
	
	public String readNurseData() {	//NEW ADDITION!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		Scanner read = null;
		try {
			read = new Scanner(new File("nurseData.txt"));
			for(int i = 0; i < 100; i++) {
				String fName = read.nextLine();
				String lName = read.nextLine();
				String uName = read.nextLine();
				String passwd = read.nextLine();
				int ID = read.nextInt();
				int assignedDoc = read.nextInt();
				
				Nurse data = new Nurse(fName, lName, uName, passwd, ID);
				setDoctor(assignedDoc);
				allNurses[i] = data;
			}
			
			read.close();
			
			return "File read successfully";
		} catch (FileNotFoundException e) {
			return("Error: File not found");
		}
	
	}
}