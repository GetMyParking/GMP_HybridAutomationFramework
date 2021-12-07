package MobileObjectMapper;

public class LoginMapper {
	

	private String email;
    private String password;
    private String username;
    
    public LoginMapper(){}
	   
    public String getUserName() 
    {
    	return username;
	}
    
    public void setUsername(String username)
    {
    	this.username= username;
    }
    
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
