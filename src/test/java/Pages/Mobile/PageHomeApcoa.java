package Pages.Mobile;

import CommonUtility.GenericMethods;
import TestNGListeners.ApcoaListeners;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PageHomeApcoa {
    WebDriver driver;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.ImageView[1]")
    WebElement btnMenu;

    @AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_positive_action")
    WebElement acceptPushNotificationBtn;

    @AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_search")
    WebElement btnsearchparking;

    @AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/edt_Search")
    WebElement edtxsearchparking;

    @AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_address")
    WebElement txtfirstresult;

    @AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_start_session_with_buy_pass")
    WebElement btnstartsession;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Image\"])[2]")
    WebElement selectfirsttariff;

    @AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/csb_time_dialer")
    WebElement btnfinalstartsession;

    @AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/btn_confirm_pay")
    WebElement btnsonfirmstartsession;

    @AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_success_heading")
    WebElement getsucessheading;

    @AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/iv_close")
    WebElement closepopup;

    @AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_sessions")
    WebElement gotomysessions;

    @AndroidFindBy(id = "com.apcoaflow.consumer.staging:id/tv_name")
    WebElement usernametxt;

    public String Username = "";

    public PageHomeApcoa(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void acceptPushNotification() {
        GenericMethods.explicitWaitForWebElementOnly(driver, acceptPushNotificationBtn, 45);
        ApcoaListeners.logInfo("Going to click Accept Push Notification ");
        acceptPushNotificationBtn.click();
        ApcoaListeners.logInfo("Clicked Accept Push Notification button sucessfully.");
    }

    public void checkUserName() {
        GenericMethods.explicitWaitForWebElementOnly(driver, btnMenu, 75);
        btnMenu.click();
        GenericMethods.explicitWaitForWebElementOnly(driver, usernametxt, 75);
        ApcoaListeners.logInfo("username is: " + usernametxt.getText());
        Username = usernametxt.getText();
        PageAddVehicle.goBack();
    }
}
