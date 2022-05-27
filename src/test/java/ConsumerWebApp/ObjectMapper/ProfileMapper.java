package ConsumerWebApp.ObjectMapper;

public class ProfileMapper {
	private String number;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipcode;
   
    public ProfileMapper() {
    }

    public String getContactnumber() {
        return number;
    }

    public void setContactnumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getCityname() {
        return city;
    }

    public void setCityname(String city) {
        this.city = city;
    }
    
    public String getStatename() {
        return state;
    }

    public void setStatename(String state) {
        this.state = state;
    }
    
    public String getCountryname() {
        return country;
    }

    public void setCountryname(String country) {
        this.country = country;
    }
    
    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    
    @Override
    public String toString() {
        return "ProfileMapper{" +
                "number='" + number + '\'' +
                ", adress='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
