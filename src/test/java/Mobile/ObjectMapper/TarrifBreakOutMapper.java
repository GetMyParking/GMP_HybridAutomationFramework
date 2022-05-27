package Mobile.ObjectMapper;

public class TarrifBreakOutMapper {

	private String parkingname;
    private String parkingidentifier;
    private String currency;
    private String tariffname;
    private String promocode;
    private String drstart;
    private String drextend;
    private String type;
    private String countrycode;
    
    
    public TarrifBreakOutMapper(){}
    
    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrencysymbol(String currency) {
        this.currency = currency;
    }
 
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
    
    public String getTariffname() {
        return tariffname;
    }

    public void setTariffname(String tariffname) {
        this.tariffname = tariffname;
    }
    
    public String getPromocode() {
        return promocode;
    }

    public void setPromocode(String promocode) {
        this.promocode = promocode;
    }
    
    public String getDrstart() {
        return drstart;
    }

    public void setDrstart(String drstart) {
        this.drstart = drstart;
    }
    
    public String getDrextend() {
        return drextend;
    }

    public void setDrextend(String drextend) {
        this.drextend = drextend;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return  "ParkingMapper{" +
                "parkingname='" + parkingname + '\'' +
                ", parkingidentifier='" + parkingidentifier + '\'' +
                ", currency='" + currency+ '\'' +  
                '}';
    }
}