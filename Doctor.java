// Doctor is a type of Nurse
// Takes all of Nurse functions and adds to them
class Doctor extends Nurse {
	private Nurse[] assignedNurses;
	
	
	// Default Doctor Constructor
	// Default Nurse Array is equal to 10 nurses/doctor
	public Doctor() {
		this.firstName = "";
		this.lastName = "";
		this.username = "";
		this.password = "";
		this.assignedNurses = new Nurse[10];
		initNurseList();
	}
	
	
	// Constructor of Doctor first and last name, user name and password
	public Doctor(String First, String Last, String uname, String passwd) {
		this.firstName = First;
		this.lastName = Last;
		this.username = uname;
		this.password = passwd;
		this.assignedNurses = new Nurse[10];
		initNurseList();
	}
	
	// Initialize the nurse array to all be null
	public void initNurseList() {
		int length = assignedNurses.length;
		for (int i = 0; i < length; i++) {
			this.assignedNurses[i] = null;
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
	
	// Searches for nurse and removes her from the array
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
	
	
}