package Mobile;

import java.io.IOException;
import java.util.Base64;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import CommonUtility.CreateSession;
import Pages.Mobile.PageSelectCountry;
import Pages.Mobile.NewUserRegistration;

public class NewUserReg {

	WebDriver driver;
	
	/**
	 * method to initialize the appium and launch application
	 *  
	 */
	@Parameters({ "Environment", "Country","Tenant","Platform" })
	@BeforeClass
	public void initializeDriver(String ennv, String country,String tenant, String platform) throws IOException{
		CreateSession cs = new CreateSession();
		cs.readConfigFile("/src/test/java/resources/Mobile/ConfigFiles/"+tenant+".properties",platform);
		CreateSession.getAutomationConfiguration().Country = country;
		CreateSession.getAutomationConfiguration().Tenant = tenant;
		CreateSession.getAutomationConfiguration().Environment = ennv;
		CreateSession.getAutomationConfiguration().Platform = platform;
	}
	
	public static void main(String []args) {
		Base64.Encoder encoder = Base64.getEncoder();  
        Base64.Decoder decoder = Base64.getDecoder();  
        
        String enStr = "karan";
        for(int i=0;i<7;i++) {
        	 enStr = encoder.encodeToString(enStr.getBytes());  
             System.out.println("Encoded string: "+enStr); 
        }
       
        String deStr= enStr;
        for(int i =0;i<7;i++) {
            deStr = new String(decoder.decode(deStr));
            System.out.println("Decoded URL: "+deStr);  
        }
        
        
		
	}
	
	/*Author Name: Ritik Sharma
	 * Date:2-Feb-2022
	 * 
	 *  Purpose:Selecting the Country
	 */
	@Test(priority=1)
	public void selectCountry() throws Exception
	{
		Thread.sleep(2000);
		PageSelectCountry selectcountry = new PageSelectCountry(CreateSession.getAutomationConfiguration().AppiumDriver);
		SoftAssert softAssert = new SoftAssert();
		if(CreateSession.getAutomationConfiguration().Tenant.equalsIgnoreCase("Apcoa")) {
			Thread.sleep(10000);
			selectcountry.selectCountryClick();
			Thread.sleep(2000);
			selectcountry.selectCountry(CreateSession.getAutomationConfiguration().Country);
			softAssert.assertEquals(CreateSession.getAutomationConfiguration().Country.toUpperCase(), selectcountry.CountrySelected.toUpperCase(),"Country not selected" );		
		}
	}

	@Test(priority=2)
	public void EmailRegistration() throws InterruptedException
	{
		NewUserRegistration  NewUsr= new NewUserRegistration(CreateSession.getAutomationConfiguration().AppiumDriver);
		NewUsr.FillDetails();

	}
	@Test(priority=3)
	public void AddVehicle() throws InterruptedException
	{
		NewUserRegistration  NewUsr= new NewUserRegistration(CreateSession.getAutomationConfiguration().AppiumDriver);
		NewUsr.AddVehicle();

	}

	@Test(priority=4)
	public void AddPaymentCard() throws InterruptedException
	{
		NewUserRegistration  NewUsr= new NewUserRegistration(CreateSession.getAutomationConfiguration().AppiumDriver);
		NewUsr.AddCard();

	}


}
