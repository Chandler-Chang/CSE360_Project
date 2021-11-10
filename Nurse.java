// Nurse Class
// Defines all features that a nurse should implement
class Nurse {
	// Each nurse/doctor has all of these
	protected String firstName, lastName;
	protected String username, password;
	
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
	
	// Constructor of Nurse first and last name, user name and password
	public Nurse(String First, String Last, String uname, String passwd) {
		this.firstName = First;
		this.lastName = Last;
		this.username = uname;
		this.password = passwd;
		this.assignedDoc = null;
	}

	// Change Nurse's first name
	public void setFirst(String First) { this.firstName = First; }

	// Change Nurse's last name
	public void setLast(String Last) { this.lastName = Last; }

	
	// Change Nurse's user name
	public void setUserName(String uname) { this.username = uname; }

	// Change Nurse's password
	public void setPassword(String passwd) { this.password = passwd; }

	
	// Change Nurse's assigned doctor
	public void setDoc(Doctor newDoc) { this.assignedDoc = newDoc; }

	
	
	// Returns Nurse's first name
	public String getFirst() { return this.firstName; }

	// Returns Nurse's last name
	public String getLast() { return this.lastName; }

	
	// Returns Nurse's user name
	public String getUserName() { return this.username; }

	// Returns Nurse's password
	public String getPassword() { return this.password; }

	
	// Returns Nurse's assigned doctor
	public Doctor getDoc() { return this.assignedDoc; }
}