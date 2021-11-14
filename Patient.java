public class Patient extends Person{
	private String birthdate;
	private String pharmacy;
	private String pharmacyAddress;
	private String insurance;
	private String vitals;
	private String allergies = null;
	private Doctor assignedDoctor;
	private PatientNode summaryHead = null;
	private PatientNode prescriptionHead;
	private PatientNode immunizationHead;
	private MessageNode messageHead;
	
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
		this.allergies = (allergies == null) ? allergies : this.allergies + ", " + allergies;
	}
	
	// Adds patient to doctors assigned patients if assigned patient list has space for them
	public void setDoctor (Doctor doctor) {
		if (doctor == null) this.assignedDoctor = null;
		if (doctor.addPatient(this.ID)) 
			this.assignedDoctor = doctor;
	}

	// Adds to patient's summary
	public void addSummary(String date, String summary) {
		summaryHead = (summaryHead == null) ?
			new PatientNode(date, summary, null) :
			new PatientNode(date, summary, summaryHead);
	}
	
	// Adds new prescription for the patient
	public void addPrescription(String date, String prescription) {
		prescriptionHead = (prescriptionHead == null) ?
			new PatientNode(date, prescription, null) :
			new PatientNode(date, prescription, summaryHead);
	}
	
	// Adds new immunization for the patient
	public void addImmunization(String date, String immunization) {
		immunizationHead = (immunizationHead == null) ?
			new PatientNode(date, immunization, null) :
			new PatientNode(date, immunization, summaryHead);
	}
	
	// Adds new message between patient and doctor
	public void addMessage(String date, String message) {
		messageHead = (messageHead == null) ?
			new MessageNode(date, message, null, this, this.assignedDoctor) :
			new MessageNode(date, message, messageHead, this, this.assignedDoctor);
	}
	
	
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
	public Doctor getDoc() { return this.assignedDoctor; }
	
	// Return patient's summary
	public PatientNode getSummary() { return this.summaryHead; }
	
	// Return patient's current prescriptions
	public PatientNode getPrescription() { return this.prescriptionHead; }
	
	// Returns patient's immunizations
	public PatientNode getImmunization() { return this.immunizationHead; }

	// Return patient's messages
	public MessageNode getMessages() { return this.messageHead; }
	
}
