// Doctor is a type of Nurse
// Takes all of Nurse functions and adds to them
class Doctor extends Nurse {
	private Nurse[] assignedNurses;
	private Patient[] assignedPatients;
	private MessageNode messageHead;
	
	
	// Default Doctor Constructor
	// Default Nurse/Patient Arrays equal to 10 per doctor
	public Doctor() {
		this.assignedNurses = new Nurse[10];
		this.assignedPatients = new Patient[10];
		initAssignedLists();
	}
	
	// Constructor of Doctor first and last name, user name and password
	public Doctor(String First, String Last, String uname, String passwd) {
		super(First, Last, uname, passwd);
		this.assignedNurses = new Nurse[10];
		this.assignedPatients = new Patient[10];
		initAssignedLists();
	}
	
	// Initialize the nurse array to all be null
	public void initAssignedLists() {
		int length = assignedNurses.length;
		for (int i = 0; i < length; i++) {
			this.assignedNurses[i] = null;
			this.assignedPatients[i] = null;
		}
	}
	
	// Add a singular nurse to the nurse array
	public boolean addNurse(Nurse newNurse) {
		int i = 0;
		while (this.assignedNurses[i] != null) {
			// If the doctor's nurse array is full, cannot add nurse, return false
			if (i == this.assignedNurses.length) {
				System.out.println("This doctor has too many nurses!");
				return false;
			}
			// If nurse currently assigned to this doctor, cannot add nurse, return false
			if (newNurse == this.assignedNurses[i]) {
				System.out.println("Nurse already exists!");
				return false;
			}
			i++;
		}
		// Add the nurse to first null element of the array
		this.assignedNurses[i] = newNurse;
		return true;
	}
	
	// Searches nurse by first name and returns index of array they are at
	public int searchNurse(String nurseFirst) {
		int i = 0;
		while (this.assignedNurses[i] != null) {
			if(this.assignedNurses[i].firstName.equals(nurseFirst))
				return i; // Nurse is found, return the index they are at
			i++;
		}
		// Nurse does not exist in this array.
		System.out.println(nurseFirst + " not found");
		return -1;
	}
	
	// Searches for nurse and removes them from the array
	public boolean removeNurse(String firstName) {
		int removalIndex = searchNurse(firstName);
		if (removalIndex == -1) 
			return false; // Cannot remove a non-existent nurse
		
		// Shift the array to the left and thus remove the nurse's reference
		for (int i = removalIndex; i < this.assignedNurses.length - 1; i++)
			this.assignedNurses[i] = this.assignedNurses[i + 1];
		
		return true;
	}
	
	// Print the array of nurses for the given doctor
	public void printNurses() {
		System.out.println(this.firstName + " has the following nurses:");
		int i = 0;
		while (this.assignedNurses[i] != null) {
			System.out.println(this.assignedNurses[i].firstName + " " + this.assignedNurses[i].lastName);
			i++;
		}
	}
	
	// Return the nurse array
	public Nurse[] getAssignedNurses() { return this.assignedNurses; }
	
	// Add a singular patient to the nurse array
	public boolean addPatient(Patient newPatient) {
		int i = 0;
		while (this.assignedPatients[i] != null) {
			// If the doctor's patient array is full, cannot add patient, return false
			if (i == this.assignedPatients.length) {
				System.out.println("This doctor has too many patients!");
				return false;
			}
			// If patient currently assigned to this doctor, cannot add patient, return false
			if (newPatient == this.assignedPatients[i]) {
				System.out.println("Patient already exists!");
				return false;
			}
			i++;
		}
		// Add the patient to first null element of the array
		this.assignedPatients[i] = newPatient;
		return true;
	}
	
	// Searches patient by first name and returns index of array they are at
	public int searchPatient(String patientFirst) {
		int i = 0;
		while (this.assignedPatients[i] != null) {
			if(this.assignedPatients[i].firstName.equals(patientFirst))
				return i; // Patient is found, return the index they are at
			i++;
		}
		// Patient does not exist in this array.
		System.out.println(patientFirst + " not found");
		return -1;
	}
	
	// Searches for patient and removes them from the array
	public boolean removePatient(String firstName) {
		int removalIndex = searchPatient(firstName);
		if (removalIndex == -1) 
			return false; // Cannot remove a non-existent patient
		
		// Shift the array to the left and thus remove the patient's reference
		for (int i = removalIndex; i < this.assignedPatients.length - 1; i++)
			this.assignedPatients[i] = this.assignedPatients[i + 1];
		
		return true;
	}
	
	// Print the array of patients assigned the given doctor
	public void printPatients() {
		System.out.println(this.firstName + " has the following patients:");
		int i = 0;
		while (this.assignedPatients[i] != null) {
			System.out.println(this.assignedPatients[i].firstName + " " + this.assignedPatients[i].lastName);
			i++;
		}
	}
	
	// Return the patient array
	public Patient[] getAssignedPatients() { return this.assignedPatients; }
	
	
	// Return Messages
	public MessageNode getMessages() { return this.messageHead; }
	
	
	// Add doctor's message to the patient
	public boolean addMessage(String date, String message) {
		int patientIndex = searchPatient(firstName);
		if (patientIndex == -1) 
			return false; // Cannot communicate with non-assigned patient
		
		// No messages, initialize the conversation
		if (this.messageHead == null) {
			MessageNode node = new MessageNode(date, message, null, this.assignedPatients[patientIndex], this);
			this.messageHead = node;
			return true;
		}
		
		// Messages exist, respond to the conversation
		MessageNode node = new MessageNode(date, message, this.messageHead, this.assignedPatients[patientIndex], this);
		this.messageHead = node;
		return true;
	}
	
}