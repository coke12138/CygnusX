package Model;

public class User {

	private int MyID;
	private String MyName,MyStatus;
	
	public User() {
		
	}
	public User(int id, String name, String status) {
		this.MyID=id;
		this.MyName=name;
		this.MyStatus=status;
	}
	public void setMyID(int id) {
		this.MyID=id;
	}
	public void setMyName(String name) {
		this.MyName=name;
	}
	public void setMyStatus(String status) {
		this.MyStatus=status;
	}
	public int getMyID() {
		return this.MyID;
	}
	public String getMyName() {
		return this.MyName;
	}
	public String getMyStstua() {
		return this.MyStatus;
	}
}
