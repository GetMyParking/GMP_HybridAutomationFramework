package Mobile.Utils;

import java.time.Duration;
import org.openqa.selenium.WebElement;

import CommonUtility.CreateSession;
import TestNGListeners.ApcoaListeners;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class DialerRotation {

	int startx = 0;
	int starty = 0;
	int height = 0;
	int width = 0;	
	int endx = 0;
	int endy = 0;
	int midx = 0;
	int midy = 0;

	private void rotateDialerForAndroid(int percent) {
		int x1=0,y1=0;
		int x2=0,y2=0;
		if(percent == 10) {
			ApcoaListeners.logInfo("Rotate 10%");
			x1=midx;
			y1=starty+(10*height/100);
			x2= midx + (2 * (width/2)/5) +15;
			y2= ((midy +starty) /2)  -20;
			swipe(x1,y1,x2,y2);
		}
		else if(percent == 25) {
			ApcoaListeners.logInfo("Rotate 25%");
			x1=midx;
			y1=starty+(10*height/100);
			x2= endx - (20*width/100);
			y2= midy + (10*height/100);
			swipe(x1,y1,x2,y2);
		}
		else if(percent == 50) {
			ApcoaListeners.logInfo("Rotate 50%");
			rotateDialerForAndroid(25);
			x1= endx - (20*width/100);
			y1= midy + (10*height/100);
			x2=midx;
			y2=endy+(10*height/100);
			swipe(x1,y1,x2,y2);
		}
		else if(percent == 75) {
			ApcoaListeners.logInfo("Rotate 75%");
			rotateDialerForAndroid(50);
			x1=midx;
			y1=endy+(10*height/100);
			x2=startx+(15*width/100);
			y2=midy+(10*height/100);
			swipe(x1,y1,x2,y2);
		}
		else if(percent == 100) {
			ApcoaListeners.logInfo("Rotate 100%");
			rotateDialerForAndroid(75);
			x1=startx+(15*width/100);
			y1=midy+(10*height/100);
			x2=midx;
			y2=starty+(10*height/100);
			swipe(x1,y1,x2,y2);
		}
		else {
			ApcoaListeners.logInfo("Invalid value for rotation");
		}
	}

	private void rotateDialerForIOS(int percent) {
		int x1=0,y1=0;
		int x2=0,y2=0;
		if(percent == 10) {
			ApcoaListeners.logInfo("Rotate 10%");
			x1=midx;
			y1=starty+15;
			x2= (midx+endx)/2;
			y2= (midy-(3*(height/2)/4));
			swipe(x1,y1,x2,y2);
		}else if(percent == 25) {
			ApcoaListeners.logInfo("Rotate 25%");
			x1=midx;
			y1=starty+15;
			x2= endx-15;
			y2= midy;
			swipe(x1,y1,x2,y2);
		}else if(percent == 50) {
			ApcoaListeners.logInfo("Rotate 50%");
			rotateDialerForAndroid(25);
			x1= endx-15;
			y1= midy;
			x2=midx;
			y2=endy-15;						
			swipe(x1,y1,x2,y2);
		}else if(percent == 75) {
			ApcoaListeners.logInfo("Rotate 75%");
			rotateDialerForAndroid(50);
			x1= midx;
			y1= endy-15;
			x2=startx+15;
			y2=midy;
			swipe(x1,y1,x2,y2);
		}else if(percent == 100) {
			ApcoaListeners.logInfo("Rotate 100%");
			rotateDialerForAndroid(75);
			x1=startx+15;
			y1=midy;
			x2=midx;
			y2=starty+15;
			swipe(x1,y1,x2,y2);
		}else {
			ApcoaListeners.logInfo("Invalid value for rotation");
		}
	}

	private void swipe(int x1,int y1, int x2, int y2) {
		try {
			ApcoaListeners.logInfo("Going to swipe on coordinates from:["+x1+","+y1+"] to coordinates: ["+x2+","+y2+"].");
			@SuppressWarnings("rawtypes")
			TouchAction action = new TouchAction(CreateSession.getAutomationConfiguration().AppiumDriver);
			action.press(PointOption.point(x1,y1)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(x2,y2)).release().perform();
			ApcoaListeners.logInfo("Swipe Done.");
			try {Thread.sleep(3000);}catch(Exception e) {}
		}catch(Exception e) {
			ApcoaListeners.logInfo("Error in Swipe: "+e.toString());
		}
	}

	public void rotateDialer(int percent, WebElement element) {
		try {
			startx = element.getLocation().getX();
			starty = element.getLocation().getY();
			height = element.getSize().height;
			width = element.getSize().width;	
			endx = startx + width;
			endy = starty + height;
			midx = (startx + endx)/2;
			midy = (starty + endy)/2;
			if(CreateSession.getAutomationConfiguration().Platform.equalsIgnoreCase("Android")) {
				rotateDialerForAndroid(percent);
			}else {
				rotateDialerForIOS(percent);
			}
		}catch(Exception e) {
			ApcoaListeners.logInfo("Error in Dialer Rotation: "+e.toString());
		}
	}
	
}

