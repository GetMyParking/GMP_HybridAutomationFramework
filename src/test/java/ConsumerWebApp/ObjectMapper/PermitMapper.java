package ConsumerWebApp.ObjectMapper;

public class PermitMapper {
	private String carparkname;
    private String permitname;
   
    public PermitMapper() {
    }

    public String getCarparkname() {
        return carparkname;
    }

    public void setCarparkname(String carparkname) {
        this.carparkname = carparkname;
    }

    public String getPermitname() {
        return permitname;
    }

    public void setPermitname(String permitname) {
        this.permitname = permitname;
    }
    
    @Override
    public String toString() {
        return "PermitMapper{" +
                "carparkname='" + carparkname + '\'' +
                ", permitname='" + permitname + '\'' +
                '}';
    }
}
