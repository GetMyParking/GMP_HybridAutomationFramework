package ConsumerWebApp.ObjectMapper;

public class LoginMapper {
	private String useremail;
    private String password;
   
    public LoginMapper() {
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    @Override
    public String toString() {
        return "LoginMapper{" +
                "useremail='" + useremail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
