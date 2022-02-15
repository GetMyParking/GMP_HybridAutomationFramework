package Dashboard.ObjectMapper;

public class PermitDataMapper {

	private String carparkname;
	private String carpermit;
	private String firstname;
	private String lastname;
	private String email;
	private String contactno;
	private String address;
	private String city;
	private String state;
	private String zipcode;
	private String vehicleno;
	private String carmake;
	private String modelname;
	private String colorname;
	private String displayname;

	public PermitDataMapper() {
	}

	public String getCarparkname() {
		return carparkname;
	}
	public void setCarparkname(String carparkname) {
		this.carparkname = carparkname;
	}
	public String getCarpermit() {
		return carpermit;
	}
	public void setCarpermit(String carpermit) {
		this.carpermit = carpermit;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;  
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicle) {
		this.vehicleno = vehicle;
	}
	public String getCarmake() {
		return carmake;
	}
	public void setCarmake(String car) {
		this.carmake = car;
	}
	public String getModelname() {
		return modelname;
	}
	public void setModelname(String model) {
		this.modelname = model;
	}
	public String getColorname() {
		return colorname;
	}
	public void setColorname(String color) {
		this.colorname = color;
	}
	public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String display) {
		this.displayname = display;
	}


	@Override
	public String toString() {
		return "PermitPurchaseDataMapper{" +

	        ", carparkname='" + carparkname + '\'' +
	        ", carpermit='" + carpermit + '\'' +
	        ", firstname='" + firstname + '\'' +
	        ", lastname='" + lastname + '\'' +
	        ", email='" + email + '\'' +
	        ", contactno='" + contactno + '\'' +
	        ", address='" + address + '\'' +
	        ", city='" + city + '\'' +
	        ", state='" + state + '\'' +
	        ", zipcode='" + zipcode + '\'' +
	        ", vehicleno='" + vehicleno + '\'' +
	        ", carmake='" + carmake + '\'' +
	        ", modelname='" + modelname + '\'' +
	        ", colorname='" + colorname + '\'' +
	        ", displayname='" + displayname + '\'' +
	        '}';
	}
}

