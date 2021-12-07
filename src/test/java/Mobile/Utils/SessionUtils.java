package Mobile.Utils;

import MobileObjectMapper.ParkingMapper;
import Pages.Mobile.SessionCreationPage;
import org.testng.asserts.SoftAssert;

public class SessionUtils {
    private final SessionCreationPage SC;
    private final String country;
    private final SoftAssert softAssert;

    public SessionUtils(SessionCreationPage SC, String country, SoftAssert softAssert) {
        this.SC = SC;
        this.country = country;
        this.softAssert = softAssert;
    }

    public void startSession(ParkingMapper parkingMapper) throws InterruptedException {
        String parkingName = parkingMapper.getparkingidentifier();
        String fullParkingName = parkingMapper.getparkingname();

        this.SC.GettheParking(parkingName);
        this.softAssert.assertEquals(fullParkingName, this.SC.ActualParkingName, "Parking Not Found");
        Thread.sleep(8000);
        this.SC.StartsessionforParkingwithPass();
        Thread.sleep(8000);
        this.SC.dialerMovement(this.country);

        System.out.println("Main ActualInitialParkingPrice:" + this.SC.ActualInitialParkingPrice);//Main ActualInitialParkingPrice:1.14
        System.out.println("Main ActualParkingHour:" + this.SC.ActualParkingHour);//Main ActualParkingHour:00
        System.out.println("Main ActualParkingMin:" + this.SC.ActualParkingMin);//Main ActualParkingMin:12

        Thread.sleep(8000);

        //softAssert.assertEquals(initialparkingprice, 1.14,"Initial parking price fail.");
        //softAssert.assertEquals(initialparkinghours, 0,"Initial parking hours fail.");
        //softAssert.assertEquals(initialparkingminutes, 12,"Initial parking min fail.");

        this.SC.PaymentConfirmation();
        System.out.println("Main ActualPaymentSuccess:" + this.SC.ActualPaymentSuccess);//Main ActualPaymentSuccess:Payment Successful
        this.softAssert.assertEquals(this.SC.ActualPaymentSuccess, "Payment Successful", "Payment Successful message fail");

        Thread.sleep(8000);
    }

    public float extendSession() throws InterruptedException {
        this.SC.ExtendSession(this.country);
        float initialparkingprice = Float.parseFloat(this.SC.ActualInitialParkingPrice);
        //float initialparkinghours = Float.parseFloat(SC.ActualParkingHour);
        //float initialparkingminutes = Float.parseFloat(SC.ActualParkingMin);
        float extendedParkingPrice = Float.parseFloat(this.SC.ActualExtendedParkingPrice);

        System.out.println("Main ActualExtendedParkingPrice:" + this.SC.ActualExtendedParkingPrice);//Main ActualExtendedParkingPrice:0.00

        Thread.sleep(8000);

        this.SC.ExtendPaymentConfirmation();

        Thread.sleep(8000);

        float finalprice = extendedParkingPrice + initialparkingprice;
        this.SC.GotoMyActiveSessions();

        float activesessionprice = Float.parseFloat(this.SC.ActiveSessionCost.split("€")[1]);

        this.softAssert.assertEquals(activesessionprice, finalprice, "Final price in active session fail");

        System.out.println("Main ActiveSessionID:" + this.SC.ActiveSessionID);//Main ActiveSessionID:ID: #105899
        System.out.println("Main ActiveSessionCost:" + this.SC.ActiveSessionCost);//Main ActiveSessionCost:Total : €1.14

        Thread.sleep(3000);
        return finalprice;
    }

    public void stopSession(float finalprice) throws InterruptedException {
        float activesessionid = Float.parseFloat(this.SC.ActiveSessionID.split("#")[1]);
        this.SC.StopSession();

        Thread.sleep(3000);
        this.SC.GotoMyExpiredSessions();

        float expiredsessionid = Float.parseFloat(this.SC.ExpiredSessionID.split("#")[1]);
        float expiredsessionprice = Float.parseFloat(this.SC.ExpiredSessionCost.split("€")[1]);

        this.softAssert.assertEquals(expiredsessionprice, finalprice, "Final price in expired session fail");

        this.softAssert.assertEquals(expiredsessionid, activesessionid, "Session ID is not matching in expired sessions.");

        System.out.println("Main ExpiredSessionID:" + this.SC.ExpiredSessionID);//Main ExpiredSessionID:ID: #105899
        System.out.println("Main ExpiredSessionCost:" + this.SC.ExpiredSessionCost);//Main ExpiredSessionCost:Total : €1.14

        Thread.sleep(8000);
    }
}
