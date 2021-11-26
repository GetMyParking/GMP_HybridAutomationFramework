package MobileObjectMapper;

public class LoginMapper {
	

	private String email;
    private String password;
    
    public LoginMapper(){}
	   
    public String getEmail() {
    	return email;
	}
	   
    public void setEmail(String email) {
	
    	this.email = email;
	   
    }
	
    public String getPassword() {

    	return password;
   
    }
   public void setPassword(String password) {

	   this.password = password;
	   
   }
	
   public String toString(){
	
	   return "Login: { email:"+email+",  password:"+ password+ "}";
	   
   }
	
}
