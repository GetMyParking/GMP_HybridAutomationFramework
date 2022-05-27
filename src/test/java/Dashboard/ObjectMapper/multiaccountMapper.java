package Dashboard.ObjectMapper;

public class multiaccountMapper {
	
	 private String carparkname;
	 private String carpermit;
	 private String username;
	  
	  public multiaccountMapper() {
		  
	  }
	  
	  public String getCarparkname() {
	    return carparkname;
	  }
	  public void setCarparkname(String carparkname) {
	    this.carparkname = carparkname;
	  }
	  
	  public String getCarpermit() {
		    return carpermit;
		  }
		  public void setCarpermit(String carpermit) {
		    this.carpermit = carpermit;
		  }
		  
	  public String getUsername() {
			    return username;
			  }
			  public void setUsername(String username) {
			    this.username = username;
			  }	  
	  
	  @Override
	  public String toString() {
	    return "multiaccountMapper{" +
	        
	        ", carparkname='" + carparkname + '\'' +
	         ", carpermit='" + carpermit + '\'' +
	          ", username='" + username + '\'' +
	        
	        '}';
	  }

	
}
