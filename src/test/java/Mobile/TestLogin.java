package Mobile;

import CommonUtility.AutomationConfiguration;
import CommonUtility.CreateSession;
import DataDriven.ExcelDriven;
import Mobile.Utils.SessionUtils;
import MobileObjectMapper.LoginMapper;
import MobileObjectMapper.ParkingMapper;
import MobileObjectMapper.VehicleMapper;
import Pages.Mobile.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class TestLogin {

    @Parameters({"Environment", "Country"})
    @BeforeSuite
    public void initializeDriver(String ennv, String country) throws IOException {
        AutomationConfiguration.Environment = ennv;
        AutomationConfiguration.Country = country;
        CreateSession.readConfigFile("/src/test/java/resources/configAppcoaStaging.properties");
    }

    @DataProvider
    public LoginMapper[] getLoginData() throws Exception {
        String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/AppcoaDataset.xlsx";
        ExcelDriven.readExcelFile(excelfilepath, AutomationConfiguration.Environment);
        String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment, AutomationConfiguration.Country, "Login");

        ObjectMapper mapper = new ObjectMapper();
        LoginMapper[] login = new LoginMapper[1];
        login[0] = mapper.readValue(data, LoginMapper.class);
        System.out.println(data);

        return login;

    }

    @DataProvider
    public VehicleMapper[] getVehicleData() throws Exception {
        String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/AppcoaDataset.xlsx";
        ExcelDriven.readExcelFile(excelfilepath, AutomationConfiguration.Environment);
        String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment, AutomationConfiguration.Country, "Add Vehicle");

        ObjectMapper mapper = new ObjectMapper();
        VehicleMapper[] login = new VehicleMapper[1];
        login[0] = mapper.readValue(data, VehicleMapper.class);
        System.out.println(login[0].getLpr());
        return login;
    }


    @DataProvider
    public ParkingMapper[] getParkingData() throws Exception
    {
        String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/AppcoaDataset.xlsx";
        ExcelDriven.readExcelFile(excelfilepath, AutomationConfiguration.Environment);
        String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment, AutomationConfiguration.Country, "Session");

        ObjectMapper mapper = new ObjectMapper();
        ParkingMapper[] login = new ParkingMapper[1];
        login[0] = mapper.readValue(data, ParkingMapper.class);
        System.out.println(login[0].getParkingidentifier());
        System.out.println(login[0].getParkingname());

        return login;
    }


    @BeforeMethod
    public void initializeAssertions() {
        AutomationConfiguration.SoftAsserts = new SoftAssert();
    }

    @AfterMethod
    public void AssertAll() {
        AutomationConfiguration.SoftAsserts.assertAll();
    }


    @Test(priority = 1)
    public void selectCountry() throws Exception {
        //getVehicleData();
        //getParkingData();
        Thread.sleep(15000);
        PageSelectCountry selectcountry = new PageSelectCountry(AutomationConfiguration.AppiumDriver);
        selectcountry.selectCountryClick();
        Thread.sleep(2000);
        selectcountry.selectCountry(AutomationConfiguration.Country);
        Thread.sleep(5000);
        selectcountry.btnLoginClick();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(AutomationConfiguration.Country.toUpperCase(), selectcountry.CountrySelected.toUpperCase(), "Country not selected");
        softAssert.assertAll();
    }

    @Test(priority = 2, dataProvider = "getLoginData")
    public void loginAppcoa(LoginMapper loginMapper) throws InterruptedException {
        System.out.println(loginMapper.getEmail() + " " + loginMapper.getPassword());
        Thread.sleep(10000);

        PageLogin login = new PageLogin(AutomationConfiguration.AppiumDriver);
        login.enterCredentials(loginMapper.getEmail(), loginMapper.getPassword());
        login.clickContinue();

        PageHomeApcoa home = new PageHomeApcoa(AutomationConfiguration.AppiumDriver);
        home.acceptPushNotification();

        home.checkUserName();


        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(loginMapper.getUsername().toUpperCase(), home.Username.toUpperCase());
        softAssert.assertAll();

    }

    @Test(priority = 3, dataProvider = "getVehicleData")
    public void addVehicle(VehicleMapper vehicleMapper) throws InterruptedException {
        String vehicleno = vehicleMapper.getLpr();
        SoftAssert softAssert = new SoftAssert();
        PageAddVehicle addvehicle = new PageAddVehicle(AutomationConfiguration.AppiumDriver);
        addvehicle.addVehicle(vehicleno);
        Thread.sleep(4000);
        System.out.println("first lpr: " + addvehicle.getfirstvehiclelpr());
        String vno = addvehicle.getfirstvehiclelpr();
        softAssert.assertEquals(vehicleno, vno, "Vehicle LPR not added");
        PageAddVehicle.goBack();
        softAssert.assertAll();
    }

    @Test(priority = 5, dataProvider = "getVehicleData")
    public void deleteVehicle(VehicleMapper vehicleMapper) throws InterruptedException {
        Thread.sleep(8000);
        String vehicleno = vehicleMapper.getLpr();
        SoftAssert softAssert = new SoftAssert();
        PageAddVehicle delvehicle = new PageAddVehicle(AutomationConfiguration.AppiumDriver);
        delvehicle.deletelpr();
        Thread.sleep(5000);
        System.out.println("first lpr: " + delvehicle.getfirstvehiclelpr());
        String vno = delvehicle.getfirstvehiclelpr();
        softAssert.assertNotEquals(vehicleno, vno, "Vehicle LPR not deleted");
        PageAddVehicle.goBack();
        softAssert.assertAll();
        Thread.sleep(5000);
    }

    @Test(priority = 4, dataProvider = "getParkingData")
    public void startExtendStopSession(ParkingMapper parkingMapper) throws InterruptedException {
		Thread.sleep(8000);
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		String country = AutomationConfiguration.Country;
		SoftAssert softAssert = new SoftAssert();

		SessionUtils sessionUtils = new SessionUtils(SC, country, softAssert);
		sessionUtils.startSession(parkingMapper);

		float finalPrice = sessionUtils.extendSession();

		sessionUtils.stopSession(finalPrice);

        softAssert.assertAll();
        PageAddVehicle.goBack();
    }
}





