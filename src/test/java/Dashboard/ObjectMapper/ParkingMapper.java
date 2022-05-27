package Dashboard.ObjectMapper;

public class ParkingMapper {
	
	private String parking;
	private String parkinglot;
	private String company;
	 
	  
	  public ParkingMapper() {
	  }
	  
	  public String getParkingname() {
	    return parking;
	  }
	  public void setParkingname(String parking) {
	    this.parking = parking;
	  }
	  
	 
	  
	  public String getParkinglotname() {
		    return parkinglot;
		  }
		  public void setParkinglotname(String parkinglot) {
		    this.parkinglot = parkinglot;
		  }
	public String getCompanyname() {
			    return company;
			  }
		public void setCompanyname(String company) {
			    this.company = company;
			  }
		
	  
	  @Override
	  public String toString() {
	    return "PermitPurchaseDataMapper{" +
	        
	        ", parking='" + parking + '\'' +
	         ", parkinglot='" + parkinglot + '\'' +
	          ", company='" + company + '\'' +
	          
	       
	        
	        '}';
	  }

}


