package ConsumerWebApp.ObjectMapper;

public class LocationMapper {
	 private String location;
	 private String number;


	    public LocationMapper() {
	    }

	    public String getLocation() {
	        return location;
	    }

	    public void setLocation(String location) {
	        this.location = location;
	    }
	    
	    public String getContactnumber() {
	        return number;
	    }

	    public void setContactnumber(String number) {
	        this.number = number;
	    }

	    @Override
	    public String toString() {
	        return "LocationMapper{" +
	                "location='" + location + '\'' +
	                 ", number='" + number + '\'' +
	                '}';
	    }
}
