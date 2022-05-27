/*
-------------------------------------------------------------
Author Name: Karan Kumar Agarwal

Date:7-Mar-2022

Purpose /Description: Common methods for generating some random
data.
-------------------------------------------------------------
 */

package Utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.RandomStringUtils;

public class CustomiseRandom {

	public static String generateRandomAlpha(int length) {
	
		return RandomStringUtils.random(length, true, false);
	}

	public static String generateRandomAphaNumeric(int length) {
		return RandomStringUtils.random(length, true, true);
	}

	public static String generateRandomNumber(int length) {
		return RandomStringUtils.random(length, false, true);
	}

	public static String generateRandonEmail(String preEmail, int length) {
		String pre= preEmail.split("@")[0];
		String post = preEmail.split("@")[1];
		String randomInt = RandomStringUtils.random(length, false, true);
		return pre+randomInt+"@"+post;
	}

	public static String generateRandomEmailWithDateTimeStamp(String preEmail) {
		String pre= preEmail.split("@")[0];
		String post = preEmail.split("@")[1];
		String randomInt = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		return pre+randomInt+"@"+post;
	}


}
