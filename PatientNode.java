
public class PatientNode {
	
	private String date;
	private String info;
	private PatientNode next;

	public PatientNode(String date, String info, PatientNode next) {
		this.date = date;
		this.info = info;
		this.next = next;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public PatientNode getNext() {
		return next;
	}

	public void setNext(PatientNode next) {
		this.next = next;
	}
	
	
}
