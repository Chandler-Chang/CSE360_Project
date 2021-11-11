
public class PatientNode {
	
	private String date;
	private String info;
	private PatientNode next;

	public PatientNode(String date, String info, PatientNode next) {
		this.date = date;
		this.info = info;
		this.next = next;
	}

	public void setDate(String date) { this.date = date; }

	public void setInfo(String info) { this.info = info; }

	public void setNext(PatientNode next) { this.next = next; }
	
	
	
	public String getDate() { return this.date; }

	public String getInfo() { return this.info; }
	 
	public PatientNode getNext() { return this.next; }
}
