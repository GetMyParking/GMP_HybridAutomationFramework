package MobileObjectMapper;

public class VehicleMapper {

	private String lpr;
    
    public VehicleMapper(){}
	   
    public String getlpr() {
    	return lpr;
	}
	   
    public void setlpr(String lpr) {
	
    	this.lpr = lpr;
	   
    }
	
   public String toString(){
	
	   return "vehicle: { lpr:"+lpr+"}";
	   
   }
}
