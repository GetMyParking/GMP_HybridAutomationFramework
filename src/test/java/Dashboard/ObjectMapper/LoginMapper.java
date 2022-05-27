package Dashboard.ObjectMapper;

public class LoginMapper {
	
	 private String username;
	  private String password;
	  public LoginMapper() {
	  }
	  
	  public String getUsername() {
	    return username;
	  }
	  public void setUsername(String username) {
	    this.username = username;
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
	        
	        ", username='" + username + '\'' +
	        ", password='" + password + '\'' +
	        '}';
	  }

	
}
