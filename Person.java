package application;

public class Person {
	// Each person has first/last name, user name and password
	protected String firstName, lastName;
	protected String username, password;
	protected int ID;
	
	// Default Person Constructor
	public Person() {
		this.firstName = "";
		this.lastName = "";
		this.username = "";
		this.password = "";
		this.ID = 0;
	}
		
	// Constructor of Person with first and last name, user name and password
	public Person(String First, String Last, String uname, String passwd, int ID) {
		this.firstName = First;
		this.lastName = Last;
		this.username = uname;
		this.password = passwd;
		this.ID = ID;
	}
	
	// Change Person's first name
	public void setFirst(String First) { this.firstName = First; }

	// Change Person's last name
	public void setLast(String Last) { this.lastName = Last; }

	
	// Change Person's user name
	public void setUserName(String uname) { this.username = uname; }

	// Change Person's password
	public void setPassword(String passwd) { this.password = passwd; }
	
	// Set Person's ID
	public void setID(int ID) { this.ID = ID; }
	
	
	// Returns Person's first name
	public String getFirst() { return this.firstName; }

	// Returns Person's last name
	public String getLast() { return this.lastName; }

	// Returns Person's user name
	public String getUsername() { return this.username; }

	// Returns Person's password
	public String getPassword() { return this.password; }
	
	// Returns Person's password
	public int getID() { return this.ID; }
}