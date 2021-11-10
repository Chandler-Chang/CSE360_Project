
class Nurse {
	protected String firstName, lastName;
	protected String username, password;
	private Doctor assignedDoc;

	public Nurse() {
		this.firstName = "";
		this.lastName = "";
		this.username = "";
		this.password = "";
		this.assignedDoc = null;
	}
	
	public Nurse(String First, String Last, String uname, String passwd) {
		this.firstName = First;
		this.lastName = Last;
		this.username = uname;
		this.password = passwd;
		this.assignedDoc = null;
	}

	public void setFirst(String First) {
		this.firstName = First;
	}

	public void setLast(String Last) {
		this.lastName = Last;
	}

	public void setUserName(String uname) {
		this.username = uname;
	}

	public void setPassword(String passwd) {
		this.password = passwd;
	}

	public void setDoc(Doctor newDoc) {
		this.assignedDoc = newDoc;
	}

	public String getFirst() {
		return this.firstName;
	}

	public String getLast() {
		return this.lastName;
	}

	public String getUserName() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public Doctor getDoc() {
		return this.assignedDoc;
	}
}