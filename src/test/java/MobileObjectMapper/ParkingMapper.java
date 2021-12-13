package MobileObjectMapper;

public class ParkingMapper {
	
	private String parkingname;
    private String parkingidentifier;
    
    public ParkingMapper(){}

    public String getParkingname() {
        return parkingname;
    }

    public void setParkingname(String parkingname) {
        this.parkingname = parkingname;
    }

    public String getParkingidentifier() {
        return parkingidentifier;
    }

    public void setParkingidentifier(String parkingidentifier) {
        this.parkingidentifier = parkingidentifier;
    }

    @Override
    public String toString() {
        return "ParkingMapper{" +
                "parkingname='" + parkingname + '\'' +
                ", parkingidentifier='" + parkingidentifier + '\'' +
                '}';
    }
}