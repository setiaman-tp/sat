
public class Staff {
	public Staff(String staffNo, String staffname, String address, String mobileNo) {
		super();
		this.staffNo = staffNo;
		this.staffname = staffname;
		this.address = address;
		this.mobileNo = mobileNo;
	}
	//declare attributes
	protected String staffNo;
	protected String staffname;
	protected String address;
	protected String mobileNo;
	public String getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
	public String getStaffname() {
		return staffname;
	}
	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
}
