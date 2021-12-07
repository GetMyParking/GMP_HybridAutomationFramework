package MobileObjectMapper;

public class ParkingMapper {
	
	private String parkingname;
    private String parkingidentifier;
    
    public ParkingMapper(){}
	   
    public String getparkingname() {
    	return parkingname;
	}
	   
    public void setparkingname(String parkingname) {
	
    	this.parkingname = parkingname;
	   
    }
	
    public String getparkingidentifier() {

    	return parkingidentifier;
   
    }
   public void setparkingidentifier(String parkingidentifier) {

	   this.parkingidentifier = parkingidentifier;
	   
   }
	
   public String toString(){
	
	   return "Parking: { parkingname:"+parkingname+",  parkingidentifier:"+ parkingidentifier+ "}";
	   
   }
}
