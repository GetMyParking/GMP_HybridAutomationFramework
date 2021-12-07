package Pages.Mobile;


import CommonUtility.AutomationConfiguration;
import TestNGListeners.ApcoaListeners;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SessionCreationPage {
    AppiumDriver<WebElement> driver;
    WebDriverWait wait;
    @SuppressWarnings("rawtypes")
    TouchAction action;

    public String Parkingprice;
    public String ActualInitialParkingPrice;
    public String ActualParkingHour;
    public String ActualParkingMin;
    public String ActualPaymentConfirmMsg;
    public String ActualPaymentSuccess;
    public String ActualSessionEndMsg;
    public String ActualSessionEndSuccessmsg;
    public String ActualExtendedParkingPrice;
    public String ActualParkingName;
    public String ExpectedPaymentConfirmMsg = "You will be charged " + Parkingprice + " before the session starts";
	public String ActiveSessionID;
	public String ActiveSessionCost;
	public String ExpiredSessionID;
	public String ExpiredSessionCost;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public SessionCreationPage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 100);
        action = new TouchAction((PerformsTouchActions) driver);
    }

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.ImageView[1]")
    private WebElement btnMenu;

    @AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_sessions")
    private WebElement MenuMySessions;

    @AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_session_id")
    private WebElement MySessionsSessionID;

    @AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_cost")
    private WebElement MySessionsTotalCost;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc='Expired']")
    private WebElement MySessionsGotoExpiredSessions;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_search")
	private WebElement ParkingSearchbartv;

	@AndroidFindBy(xpath = "(//android.widget.RelativeLayout)[7]")
	private WebElement ParkingSearchbar;

	@AndroidFindBy(xpath = ".//android.widget.EditText[@resource-id='com.apcoaflow.consumer.staging:id/edt_Search']")
	private WebElement searchParking;

	@AndroidFindBy(xpath = "(//android.widget.LinearLayout)[4]")
	private WebElement selectParking;

	@AndroidFindBy(xpath = "(//android.view.ViewGroup)[2]")
	private WebElement selectParking2;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/rl_setting_parent")
	public WebElement scrollStartPointInMap;

	//[6] for ringturm
	@AndroidFindBy(xpath = "(//android.view.View)[5]")
	public WebElement clickOnParking;

	@AndroidFindBy(xpath = "(//android.view.View)[10]")
	public WebElement clickOnParkingGermany;

	@AndroidFindBy(xpath = "(//android.view.View)[4]")
	public WebElement clickOnParkingAustria_Poland;    //zamek ujazdowski

	@AndroidFindBy(xpath = "(//android.view.View)[18]")
	public WebElement clickOnParkingItaly;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_display_name")
	private WebElement parkingDetailScreen;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_flow_benefits_header")
	private WebElement scrollStartPoint;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/vehicle_type_header")
	private WebElement scrollEndPoint;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/iv_favourite")
	private WebElement SetfavParking;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_start_session_with_buy_pass")
	private WebElement startSessionwithpass;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_parking_price_with_units")
	private WebElement ParkingPrice;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_parking_hours")
	private WebElement ParkingHour;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_parking_minutes")
	private WebElement ParkingMinute;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/csb_time_dialer")
	private WebElement clickOnDialer;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_message")
	private WebElement paymentConfirmMsg;
	//com.apcoaflow.consumer.staging:id/tv_confirm_pay
	//com.apcoaflow.consumer.staging:id/btn_confirm_pay
	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_confirm_pay")
	private WebElement payAmount;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_success_heading")
	private WebElement paySuccessMsg;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/iv_close")
	private WebElement closePayment;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_login")
	private WebElement Login;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/email")
	private WebElement email;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/edt_password")
	private WebElement password;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/btn_continue")
	private WebElement continuelogin;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_stop_parking_session")
	private WebElement stopSession;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_desc")
	private WebElement SessionStopMsg;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_confirm")
	private WebElement SessionStopConfirm;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_success_sub_heading")
	private WebElement SessionEndSuccessMsg;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_extend_parking_session")
	private WebElement ExtendSession;

	@AndroidFindBy(xpath = ".//android.widget.TextView[@resource-id='com.apcoaflow.consumer.staging:id/tv_garage_access_door']")
	private WebElement opengaragedoor;

	@AndroidFindBy(xpath = "(.//android.widget.RelativeLayout[@resource-id='com.apcoaflow.consumer.staging:id/rl_garage_door_item'])[1]")
	private WebElement selectFirstdoor;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_action_button")
	private WebElement openaccessdoor;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_action_button")
	private WebElement confirmpaymentforextendsession;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_negative_action_button")
	private WebElement goback;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/rl_start_session")
	private WebElement StartSession;

	@AndroidFindBy(xpath = "(.//android.widget.ImageView[@resource-id='com.apcoaflow.consumer.staging:id/iv_selected_mode'])[2]")
	private WebElement SelectSecondTariffMode;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/ub_page_button_cancel")
	private WebElement feedbackPopUp;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_area_code_search")
	private WebElement SearchAreaCode;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/et_search")
	private WebElement AreaCodeSearchBar;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/cl_parent")
	private WebElement Selectparking;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/rl_select_parking_lot")
	private WebElement SelectparkingLot;

	@AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id='com.apcoaflow.consumer.staging:id/iv_selected_mode'])[2]")
	private WebElement SelectSecondLot;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_start_session")
	private WebElement StartSessionafterSelectinglot;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/rl_header")
	public WebElement DetailScreenscrollStartPoint;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/service_header")
	public WebElement DetailScreenscrollEndPoint;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/iv_menu")
	private WebElement ClickOnsideBar;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_sessions")
	private WebElement My_Sessions;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_name")
	private WebElement ActiveParking;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/iv_back")
	private WebElement GoBacktoSideBar;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_view_parking_session")
	private WebElement emptyActiveSession;

	@AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/rl_extend_session_option")
	private WebElement btnselectfirsttariff;


    public void GotoMyActiveSessions() {
        try {
            Thread.sleep(5000);
            CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver, btnMenu, 100);
            btnMenu.click();

            Thread.sleep(3000);
            CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver, MenuMySessions, 100);
            MenuMySessions.click();
            Thread.sleep(8000);

            CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver, MySessionsSessionID, 100);
            ActiveSessionID = MySessionsSessionID.getText();
            System.out.println("Active Session ID: " + ActiveSessionID);

            action.press(PointOption.point(505, 1870)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(528, 784)).release().perform();
            Thread.sleep(3000);
            CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver, MySessionsTotalCost, 100);
            ActiveSessionCost = MySessionsTotalCost.getText();
            System.out.println("Active Session cost : " + ActiveSessionCost);
        } catch (Exception e) {
            System.out.println("PaymentConfirmation end error: " + e.toString());
        }
    }

    public void GotoMyExpiredSessions() {
        try {
            Thread.sleep(5000);
            CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver, btnMenu, 100);
            btnMenu.click();
            Thread.sleep(3000);

            CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver, MenuMySessions, 100);
            MenuMySessions.click();
            Thread.sleep(8000);

            CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver, MySessionsGotoExpiredSessions, 10);
            MySessionsGotoExpiredSessions.click();
            Thread.sleep(3000);

            CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver, MySessionsSessionID, 100);
            ExpiredSessionID = MySessionsSessionID.getText();
            System.out.println("Expired Session ID: " + ExpiredSessionID);

            Thread.sleep(3000);
            ExpiredSessionCost = MySessionsTotalCost.getText();
            System.out.println("Expired Session Cost: " + ExpiredSessionCost);

        } catch (Exception e) {
            System.out.println("PaymentConfirmation end error: " + e.toString());

        }
    }

    public void GettheParking(String ParkingName) {
        try {
            ApcoaListeners.logInfo("GettheParking start");

            CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver, ParkingSearchbar, 30);
            ParkingSearchbar.click();

            CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver, searchParking, 30);
            searchParking.sendKeys(ParkingName);

            Thread.sleep(15000);
            boolean temp = selectParking2.isDisplayed();
            if (temp) {
                selectParking2.click();
            } else {
                selectParking.click();
            }
            CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver, ParkingSearchbar, 30);
            ApcoaListeners.logInfo("parking name: " + ParkingSearchbartv.getText());
            ActualParkingName = ParkingSearchbartv.getText();
            ApcoaListeners.logInfo("GettheParking  end");
        } catch (Exception e) {
            ApcoaListeners.logInfo("GettheParking start: error:" + e.toString());
        }
    }

    public void scrolldown(WebElement pageStart, WebElement PageEnd) {
        try {
            pageStart.isDisplayed();
            Thread.sleep(3);
            Dimension dim = driver.manage().window().getSize();
            int x = dim.getWidth() / 2;
            int startY = (int) (dim.getHeight() * 0.2);
            int endY = (int) (dim.getHeight() * 0.8);
            action.press(PointOption.point(x, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(x, endY)).release().perform();
            boolean scrolled = PageEnd.isDisplayed();
            Thread.sleep(4000);
            //Log4j.afterClickOn(PageEnd, driver);
            System.out.println(scrolled);
        } catch (Exception e) {
            //Log4j.onException(e, driver);
            e.printStackTrace();
        }
    }

    public void scrollUp(WebElement pageStart, WebElement PageEnd) {
        try {
            pageStart.isDisplayed();
            //Log4j.afterClickOn(pageStart, driver);
            Thread.sleep(3);
            Dimension dim = driver.manage().window().getSize();
            int x = dim.getWidth() / 2;
            int startY = (int) (dim.getHeight() * 0.8);
            int endY = (int) (dim.getHeight() * 0.2);
            action.press(PointOption.point(x, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(x, endY)).release().perform();
            boolean scrolled = PageEnd.isDisplayed();
            Thread.sleep(4000);
            //Log4j.afterClickOn(PageEnd, driver);
            System.out.println(scrolled);
        } catch (Exception e) {
            //Log4j.onException(e, driver);
            e.printStackTrace();
        }

    }

    public void favPark() {
        try {
            CommonUtility.GenericMethods.explicitWaitForWebElement(driver, clickOnParkingGermany, 100);
            CommonUtility.GenericMethods.explicitWaitForWebElement(driver, parkingDetailScreen, 100);
            SetfavParking.click();
            //Log4j.afterClickOn(SetfavParking, driver);
        } catch (Exception e) {
            //Log4j.onException(e, driver);
            e.printStackTrace();
        }

    }

    public void dialerMovement(String Country) {
        try {
            ApcoaListeners.logInfo("dialerMovement start");
            Thread.sleep(15000);

            if (Country.equalsIgnoreCase("Sweden")) {
                //action.press(PointOption.point(538, 1173)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(747, 1595)).release().perform(); //emualator
                action.press(PointOption.point(361, 891)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(520, 1165)).release().perform();
            } else if (Country.equalsIgnoreCase("Austria")) {
                ApcoaListeners.logInfo("dialerMovement Austria");
                action.press(PointOption.point(553, 1427)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(847, 1640)).release().perform();
                ApcoaListeners.logInfo("dialerMovement Austria end");
            } else if (Country.equalsIgnoreCase("Poland")) {
                //action.press(PointOption.point(538, 1173)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(689, 1653)).release().perform(); //emulator
                action.press(PointOption.point(359, 891)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(532, 1136)).release().perform();
            } else {
                //action.press(PointOption.point(542, 1173)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(701, 1641)).release().perform(); //emulator
                action.press(PointOption.point(359, 891)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(483, 1212)).release().perform();
            }

            CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver, ParkingPrice, 30);
            Parkingprice = ParkingPrice.getText();
            ActualInitialParkingPrice = Parkingprice.substring(1);

            Thread.sleep(3000);

            ActualParkingHour = ParkingHour.getText();
            ActualParkingMin = ParkingMinute.getText();

            CommonUtility.GenericMethods.explicitWaitForWebElement(driver, clickOnDialer, 100);
            ApcoaListeners.logInfo("dialerMovement end");
        } catch (Exception e) {
            ApcoaListeners.logInfo("dialerMovement end error: " + e.toString());
        }
    }

    public void ExtendPaymentConfirmation() {
        try {
            ApcoaListeners.logInfo("ExtendPaymentConfirmation start");
            CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver, closePayment, 100);
            closePayment.click();
            ApcoaListeners.logInfo("ExtendPaymentConfirmation end: ");
        } catch (Exception e) {
            ApcoaListeners.logInfo("ExtendPaymentConfirmation end error: " + e.toString());
        }
    }

    public void PaymentConfirmation() {
        try {
            ApcoaListeners.logInfo("PaymentConfirmation start");

            Thread.sleep(12000);

            CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver, payAmount, 30);
            //com.apcoaflow.consumer.staging:id/btn_confirm_pay
            ApcoaListeners.logInfo("payAmount.getText(): " + payAmount.getText());
            CommonUtility.GenericMethods.explicitWaitForWebElement(driver, payAmount, 15);

            Thread.sleep(25000);
            CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver, paySuccessMsg, 100);
            ActualPaymentSuccess = paySuccessMsg.getText();
            CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver, closePayment, 100);
            closePayment.click();
            ApcoaListeners.logInfo("PaymentConfirmation end: ActualPaymentSuccess " + ActualPaymentSuccess);
        } catch (Exception e) {
            ApcoaListeners.logInfo("PaymentConfirmation end error: " + e.toString());
        }
    }

    public void StopSession() {
        try {
            ApcoaListeners.logInfo("StopSession start");
            CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver, stopSession, 100);
            stopSession.click();
            ActualSessionEndMsg = SessionStopMsg.getText();
            CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver, SessionStopConfirm, 100);
            SessionStopConfirm.click();

            Thread.sleep(8000);
            ((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));

            ApcoaListeners.logInfo("StopSession end");
        } catch (Exception e) {
            ApcoaListeners.logInfo("StopSession end error: " + e.toString());
        }
    }

    public void ExtendSession(String Country) {
        try {
            ApcoaListeners.logInfo("ExtendSession start");
            CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver, ExtendSession, 100);
            ExtendSession.click();
            //AppiumGenericMethods.threadWait(driver, 8000);
            Thread.sleep(8000);
            if (Country.equalsIgnoreCase("Austria")) {
                action.press(PointOption.point(553, 1427)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(847, 1640)).release().perform();

            } else if (Country.equalsIgnoreCase("Italy")) {
                action.press(PointOption.point(359, 853)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(538, 1151)).release().perform();
            } else if (Country.equalsIgnoreCase("Poland")) {
                action.press(PointOption.point(359, 853)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(532, 1157)).release().perform();
            } else {
                action.press(PointOption.point(359, 853)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(561, 1067)).release().perform();
            }

            Thread.sleep(10000);
            CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver, ParkingPrice, 100);
            Parkingprice = ParkingPrice.getText();
            ActualExtendedParkingPrice = Parkingprice.substring(1);
            CommonUtility.GenericMethods.explicitWaitForWebElement(driver, clickOnDialer, 100);
            ApcoaListeners.logInfo("ExtendSession end:Parkingprice " + Parkingprice);

            ApcoaListeners.logInfo("ExtendSession end: ActualExtendedParkingPrice" + ActualExtendedParkingPrice);
            CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver, confirmpaymentforextendsession, 100);
            confirmpaymentforextendsession.click();
        } catch (Exception e) {
            ApcoaListeners.logInfo("ExtendSession end error: " + e.toString());
        }
    }

    public void OpenGarageDoor() {
        try {
            opengaragedoor.click();
            CommonUtility.GenericMethods.explicitWaitForWebElement(driver, selectFirstdoor, 100);
            openaccessdoor.click();
            goback.click();
        } catch (Exception e) {
            //Log4j.onException(e, driver);
            e.printStackTrace();
        }
    }

    public void StartsessionforParkingwithPass() {
        ApcoaListeners.logInfo("StartsessionforParkingwithPass start");

        CommonUtility.GenericMethods.explicitWaitForWebElement(driver, startSessionwithpass, 100);
        CommonUtility.GenericMethods.explicitWaitForWebElement(driver, btnselectfirsttariff, 100);

        ApcoaListeners.logInfo("StartsessionforParkingwithPass end");
    }
}