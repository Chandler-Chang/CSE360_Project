
class Doctor extends Nurse {
	private Nurse[] assignedNurses;
	
	public Doctor() {
		this.firstName = "";
		this.lastName = "";
		this.username = "";
		this.password = "";
		this.assignedNurses = new Nurse[10];
		initNurseList();
	}
	
	public Doctor(String First, String Last, String uname, String passwd) {
		this.firstName = First;
		this.lastName = Last;
		this.username = uname;
		this.password = passwd;
		this.assignedNurses = new Nurse[10];
		initNurseList();
	}
	
	public void initNurseList() {
		int length = assignedNurses.length;
		for (int i = 0; i < length; i++) {
			this.assignedNurses[i] = null;
		}
	}
	
	public boolean addNurse(Nurse newNurse) {
		int i = 0;
		while (this.assignedNurses[i] != null) {
			if (i == this.assignedNurses.length) {
				System.out.println("This doctor has too many nurses!");
				return false;
			}
			if (newNurse == this.assignedNurses[i]) {
				System.out.println("Nurse already exists!");
				return false;
			}
			i++;
		}
		this.assignedNurses[i] = newNurse;
		return true;
	}
	
	public int searchNurse(String nurseFirst) {
		int i = 0;
		while (this.assignedNurses[i] != null) {
			if(this.assignedNurses[i].firstName.equals(nurseFirst))
				return i;
			i++;
		}
		System.out.println(nurseFirst + " not found");
		return -1;
	}
	
	public boolean removeNurse(String firstName) {
		int removalIndex = searchNurse(firstName);
		if (removalIndex == -1) 
			return false;
		for (int i = removalIndex; i < this.assignedNurses.length - 1; i++)
			this.assignedNurses[i] = this.assignedNurses[i + 1];
		
		return true;
	}
	
	public void printNurses() {
		System.out.println(this.firstName + " has the following nurses:");
		int i = 0;
		while (this.assignedNurses[i] != null) {
			System.out.println(this.assignedNurses[i].firstName + " " + this.assignedNurses[i].lastName);
			i++;
		}
	}
	
	public Nurse[] getAssignedNurses() {
		return this.assignedNurses;
	}
	
	
}