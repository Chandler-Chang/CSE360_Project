package application;

// Nurse Class
// Defines all features that a nurse should implement
class Nurse extends Person {
	//protected Nurse[] allNurses = new Nurse[100]; //NEW ADDITION!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	// Only nurse has assigned doctor
	private int assignedDoctor = 0;

	// Default Nurse Constructor
	public Nurse() { }
	
	// Constructor of Nurse with first and last name, user name and password
	public Nurse(String First, String Last, String uname, String passwd, int ID) {
		super(First, Last, uname, passwd, ID);
	}
	
	// Change Nurse's assigned doctor if doctor can successfully add them to the list
	public void setDoctor(int doctor) {
		this.assignedDoctor = doctor; 
	}

	
	// Returns Nurse's assigned doctor
	public int getDoc() { return this.assignedDoctor; }
	
}