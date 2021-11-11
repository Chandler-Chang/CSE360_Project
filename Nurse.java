// Nurse Class
// Defines all features that a nurse should implement
class Nurse extends Person {
	// Only nurse has assigned doctor
	private Doctor assignedDoc;

	// Default Nurse Constructor
	public Nurse() {
		this.firstName = "";
		this.lastName = "";
		this.username = "";
		this.password = "";
		this.assignedDoc = null;
	}
	
	// Constructor of Nurse with first and last name, user name and password
	public Nurse(String First, String Last, String uname, String passwd) {
		this.firstName = First;
		this.lastName = Last;
		this.username = uname;
		this.password = passwd;
		this.assignedDoc = null;
	}

	
	// Change Nurse's assigned doctor
	public void setDoc(Doctor newDoc) { this.assignedDoc = newDoc; }

	
	// Returns Nurse's assigned doctor
	public Doctor getDoc() { return this.assignedDoc; }
}