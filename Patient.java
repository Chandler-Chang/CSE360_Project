
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
	
	public Patient(String name) {
		super(name);
	}
	
	public String getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(String pharmacy) {
		this.pharmacy = pharmacy;
	}

	public String getPharmacyAddress() {
		return pharmacyAddress;
	}

	public void setPharmacyAddress(String pharmacyAddress) {
		this.pharmacyAddress = pharmacyAddress;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public String getVitals() {
		return vitals;
	}

	public void setVitals(String vitals) {
		this.vitals = vitals;
	}

	public String getAllergies() {
		return allergies;
	}

	public void addAllergies(String allergies) {
		if (allergies == null ) {
			this.allergies = allergies;
		}
		else {
			this.allergies = this.allergies + ", " + allergies;
		}
	}

	public PatientNode getSummary() {
		return summaryHead;
	}

	public void addSummary(String date, String summary) {
		if (summaryHead == null) {
			PatientNode node = new PatientNode(date, summary, null);
			summaryHead = node;
		}
		else {
			PatientNode node = new PatientNode(date, summary, summaryHead);
			summaryHead = node;
		}
	}
	
	public PatientNode getPrescription() {
		return prescriptionHead;
	}

	public void addPrescription(String date, String prescription) {
		if (prescriptionHead == null) {
			PatientNode node = new PatientNode(date, prescription, null);
			prescriptionHead = node;
		}
		else {
			PatientNode node = new PatientNode(date, prescription, summaryHead);
			prescriptionHead = node;
		}
	}
	
	public PatientNode getImmunization() {
		return immunizationHead;
	}

	public void addImmunization(String date, String immunization) {
		if (immunizationHead == null) {
			PatientNode node = new PatientNode(date, immunization, null);
			immunizationHead = node;
		}
		else {
			PatientNode node = new PatientNode(date, immunization, summaryHead);
			immunizationHead = node;
		}
	}
	
	public MessageNode getMessages() {
		return messageHead;
	}

	public void addMessage(String date, String message) {
		if (messageHead == null) {
			MessageNode node = new MessageNode(date, message, null, this, this.assignedDoctor);
			messageHead = node;
		}
		else {
			MessageNode node = new MessageNode(date, message, messageHead, this, this.assignedDoctor);
			messageHead = node;
		}
	}
}
