package application;

public class MessageNode {

	private String date;
	private String message;
	private MessageNode next;
	private Patient patient;
	private Doctor doctor;
	
	public MessageNode(String date, String message, MessageNode next, Patient patient, Doctor doctor) {
		this.date = date;
		this.message = message;
		this.next = next;
		this.patient = patient;
		this.doctor = doctor;
	}
	
	// Change message date
	public void setDate(String date) { this.date = date; }
	
	// Change message info
	public void setMessage(String message) { this.message = message; }
	
	// Change next message
	public void setNext(MessageNode next) { this.next = next; }	
	
	// Change patient involved with the message
	public void setPatient(Patient patient) { this.patient = patient; }	
	
	// Change doctor involved with the message
	public void setDoctor(Doctor doctor) { this.doctor = doctor; }
	
	
	// Return message date
	public String getDate() { return this.date; }

	// Return message
	public String getMessage() { return this.message; }

	// Return next message
	public MessageNode getNext() { return this.next; }

	// Return patient involved with the message
	public Patient getPatient() { return this.patient; }

	// Return doctor involved with the message	
	public Doctor getDoctor() { return this.doctor; }


}
