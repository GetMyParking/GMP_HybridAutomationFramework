package Mobile;


import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;


public class ApcoaGenericMethods {

	
	public static void threadWait(AppiumDriver<MobileElement> driver, long time) throws InterruptedException {
		synchronized (driver) {
			driver.wait(time);
		}
	}

	public static String randomString(int length) {


		
		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		return generatedString;
	}


	public static String randomNumbers(int length) {


		boolean useLetters = false;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		return generatedString;
	}

	public static char getRandomAlphabet() {
		Random rnd = new Random();
		char C = (char) ('A' + rnd.nextInt(26));
		return C;

	} 

	public static int getRandomNumber() {
		Random rnd = new Random();
		int upperbound=9;
		int random=rnd.nextInt(upperbound);

		return random;
	}

	public static String generateLPRWithSpacesForAustria() {
		String AustriaLPR=ApcoaGenericMethods.randomString(6);
		char[]lpr=AustriaLPR.toCharArray();
		lpr[0]='W';
		lpr[1]=' ';
		lpr[2]=ApcoaGenericMethods.getRandomAlphabet();
		lpr[3]=ApcoaGenericMethods.getRandomAlphabet();
		lpr[4]=' ';
		lpr[5]='2';
		AustriaLPR=String.valueOf(lpr);
		
		return AustriaLPR;

	}

	public static String generateLPRWithSpacesForPoland ( ) {

		String PolandLPR = ApcoaGenericMethods.randomString (7);
		char[] lpr = PolandLPR.toCharArray ();
		lpr[0] = 'P';
		lpr[1] = 'L';
		lpr[2] = ' ';
		lpr[3] = '1';
		PolandLPR = String.valueOf ( lpr );
		
		return PolandLPR;
	}



}
