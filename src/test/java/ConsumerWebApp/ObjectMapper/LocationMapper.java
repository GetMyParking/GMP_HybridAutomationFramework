package ConsumerWebApp.ObjectMapper;

public class LocationMapper {
	 private String location;

	    public LocationMapper() {
	    }

	    public String getLocation() {
	        return location;
	    }

	    public void setLocation(String location) {
	        this.location = location;
	    }

	    @Override
	    public String toString() {
	        return "LocationMapper{" +
	                "location='" + location + '\'' +
	                '}';
	    }
}
