package ConsumerWebApp.ObjectMapper;

public class PermitMapper {
	private String carparkname;
    private String permitname;
    private String approvalcode;
    private String directcode;

   
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
    public String getApprovalcode() {
        return approvalcode;
    }

    public void setApprovalcode(String approvalcode) {
        this.approvalcode = approvalcode;
    }
    
    public String getDirectcode() {
        return directcode;
    }

    public void setDirectcode(String directcode) {
        this.directcode = directcode;
    }
    
    @Override
    public String toString() {
        return "PermitMapper{" +
                "carparkname='" + carparkname + '\'' +
                ", permitname='" + permitname + '\'' +
                ", approvalcode='" + approvalcode + '\'' +
                ", directcode='" + directcode + '\'' +
                '}';
    }
}
