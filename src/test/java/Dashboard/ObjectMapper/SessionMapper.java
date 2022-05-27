package Dashboard.ObjectMapper;

public class SessionMapper {
	
	private String carpark;
	  private String carpermit;
	  private String email;
	  
	  public SessionMapper() {
	  }
	  
	  public String getCarpark() {
	    return carpark;
	  }
	  public void setCarpark(String carpark) {
	    this.carpark = carpark;
	  }
	  public String getCarpermit() {
	    return carpermit;
	  }
	  public void setCarpermit(String carpermit) {
	    this.carpermit = carpermit;
	  }
	  public String getEmail() {
		  return email;
	  }
	  public void setEmail(String email) {
		  this.email = email;
	  }
	  
	  @Override
	  public String toString() {
	    return "PermitPurchaseDataMapper{" +
	        
	        ", carparkname='" + carpark + '\'' +
	        ", carpermit='" + carpermit + '\'' +
	        ", username='" + email + '\'' +
	        
	        '}';
	  }
	  

}
