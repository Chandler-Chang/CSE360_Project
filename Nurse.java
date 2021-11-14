// Nurse Class
// Defines all features that a nurse should implement
class Nurse extends Person {
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
}