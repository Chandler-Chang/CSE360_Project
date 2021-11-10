
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

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public MessageNode getNext() {
			return next;
		}

		public void setNext(MessageNode next) {
			this.next = next;
		}

		public Patient getPatient() {
			return patient;
		}

		public void setPatient(Patient patient) {
			this.patient = patient;
		}
		
		public Doctor getDoctor() {
			return doctor;
		}

		public void setDoctor(Doctor doctor) {
			this.doctor = doctor;
		}
}
