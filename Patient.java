package application;

public class Patient extends Person{
	private String birthdate;
	private String pharmacy;
	private String pharmacyAddress;
	private String insurance;
	private String vitals = null;
	private String allergies = null;
	private int assignedDoctor = 0;
	private String gender = "Male";
	private PatientNode summaryHead = null;
	private PatientNode prescriptionHead = null;
	private PatientNode immunizationHead = null;
	
	private int height = 66;
	private int weight = 150;
	
	//private MessageNode messageHead;
	
	// Default Patient Constructor
	public Patient() {}
	
	// Constructor of Patient with first and last name, user name and password
	public Patient(String first, String last, String uname, String passwd, int ID) {
		super(first, last, uname, passwd, ID);
	}
	
	// Changes patient's birth date
	public void setBirthdate(String birthdate) { this.birthdate = birthdate; }

	// Changes patient's pharmacy
	public void setPharmacy(String pharmacy) { this.pharmacy = pharmacy; }	

	// Changes patient's pharmacy address
	public void setPharmacyAddress(String pharmacyAddress) { this.pharmacyAddress = pharmacyAddress; }

	// Changes patient's vitals
	public void setVitals(String vitals) { this.vitals = vitals; }

	// Changes patient's insurance info
	public void setInsurance(String insurance) { this.insurance = insurance; }
	
	// Adds to list of patients allergies
	public void addAllergies(String allergies) {
		if (this.allergies == null) {
			this.allergies = allergies;
		}
		else {
			this.allergies = this.allergies + ", " + allergies;
		}
	}
	
	// Adds patient to doctors assigned patients if assigned patient list has space for them
	public void setDoctor (int doctor) {
		this.assignedDoctor = doctor;
	}

	// Adds to patient's summary
	public void addSummary(String date, String summary) {
		if (summaryHead == null) {
			PatientNode summaryNode = new PatientNode(date, summary, null);
			summaryHead = summaryNode;
		}
		else {
			PatientNode summaryNode = new PatientNode(date, summary, summaryHead);
			summaryHead = summaryNode;
		}
	}
	
	// Adds new prescription for the patient
	public void addPrescription(String date, String prescription) {
		if (prescription.equals("none")) {
			return;
		}
		if (prescriptionHead == null) {
			PatientNode prescriptionNode = new PatientNode(date, prescription, null);
			prescriptionHead = prescriptionNode;
		}
		else {
			PatientNode prescriptionNode = new PatientNode(date, prescription, prescriptionHead);
			prescriptionHead = prescriptionNode;
		}
	}
	
	// Adds new immunization for the patient
	public void addImmunization(String date, String immunization) {
		if (immunizationHead == null) {
			PatientNode immunizationNode = new PatientNode(date, immunization, null);
			immunizationHead = immunizationNode;
		}
		else {
			PatientNode immunizationNode = new PatientNode(date, immunization, immunizationHead);
			immunizationHead = immunizationNode;
		}
	}
	
	// Adds new message between patient and doctor
	//public void addMessage(String date, String message) {
	//	messageHead = (messageHead == null) ?
	//		new MessageNode(date, message, null, this, this.assignedDoctor) :
	//		new MessageNode(date, message, messageHead, this, this.assignedDoctor);
	//}
	
	
	// Return patient's birth date
	public String getBirthdate() { return this.birthdate; }

	// Return patient's pharmacy
	public String getPharmacy() { return this.pharmacy; }

	// Return patient's pharmacy address
	public String getPharmacyAddress() { return this.pharmacyAddress; }

	// Return patient's insurance
	public String getInsurance() { return this.insurance; }

	// Return patient's vitals
	public String getVitals() { return this.vitals; }

	// Return patient's allergies
	public String getAllergies() { return this.allergies; }
	
	// Return patient's assigned doctor
	public int getDoc() { return this.assignedDoctor; }
	
	// Return patient's summary
	public PatientNode getSummary() { return this.summaryHead; }
	
	// Return patient's current prescriptions
	public PatientNode getPrescription() { return this.prescriptionHead; }
	
	// Returns patient's immunizations
	public PatientNode getImmunization() { return this.immunizationHead; }

	public String getGender() { return gender; }

	public void setGender(String gender) { this.gender = gender; }

	public int getHeight() { return height; }

	public void setHeight(int height) { this.height = height; }

	public int getWeight() { return weight; }

	public void setWeight(int weight) { this.weight = weight; }

	// Return patient's messages
	//public MessageNode getMessages() { return this.messageHead; }
}
