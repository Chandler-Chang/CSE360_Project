
public class Person {

	// Each person has first/last name, user name and password
	protected String firstName, lastName;
	protected String username, password;
	
	// Default Person Constructor
	public Person() {
		this.firstName = "";
		this.lastName = "";
		this.username = "";
		this.password = "";
	}
		
	// Constructor of Person with first and last name, user name and password
	public Person(String First, String Last, String uname, String passwd) {
		this.firstName = First;
		this.lastName = Last;
		this.username = uname;
		this.password = passwd;
	}
	
	// Change Person's first name
	public void setFirst(String First) { this.firstName = First; }

	// Change Person's last name
	public void setLast(String Last) { this.lastName = Last; }

	
	// Change Person's user name
	public void setUserName(String uname) { this.username = uname; }

	// Change Person's password
	public void setPassword(String passwd) { this.password = passwd; }
	
	
	// Returns Person's first name
	public String getFirst() { return this.firstName; }

	// Returns Person's last name
	public String getLast() { return this.lastName; }

	
	// Returns Person's user name
	public String getUserName() { return this.username; }

	// Returns Person's password
	public String getPassword() { return this.password; }
}
