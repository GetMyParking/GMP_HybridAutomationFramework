package Dashboard.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVWriter;

import Utility.CustomiseRandom;

public class GenerateCsv {

	public static String generateCsv() {
		String filepath = System.getProperty("user.dir").toString()+"/Output/PermitData"+CustomiseRandom.generateRandomNumber(7);
		System.out.println(filepath);
		File file = new File(filepath);

		try {
			FileWriter outputfile = new FileWriter(file);
			CSVWriter writer = new CSVWriter(outputfile);
			List<String[]> data = new ArrayList<String[]>();

			data.add(new String[] {"firstName","lastName","email",	"countryCode","accessType",
					"identifier","externalIdentifier","vehicleType","displayName","carMake",
					"color","state","model","phoneNumber","phoneNumberCountryCode",
			"externalCorporateId" });

			for(int i=0;i<3;i++) {
				
				String firstName=CustomiseRandom.generateRandomAlpha(7);
				String lastName="Testing";
				String email=CustomiseRandom.generateRandonEmail("perm@yopmail.com",5);
				String countryCode="IN";
				String accessType="LPR";
				String identifier="LPR"+CustomiseRandom.generateRandomNumber(3);
				String externalIdentifier="LPR"+CustomiseRandom.generateRandomNumber(3);
				String vehicleType="CAR";
				String displayName="BMW";
				String carMake="MAKE";
				String color="BLUE";
				String state="MH";
				String model="MODEL";
				String phoneNumber="12377575686";
				String phoneNumberCountryCode="91";
				String externalCorporateId="EMPH"+CustomiseRandom.generateRandomNumber(7);

				data.add(new String[] {firstName, lastName, email, countryCode, accessType, identifier, externalIdentifier,
						vehicleType, displayName, carMake, color,state,	model, phoneNumber,	phoneNumberCountryCode,	externalCorporateId});

				
			}
			writer.writeAll(data);
			writer.close();
			File input = new File(filepath);
            File output = new File(filepath+".csv");
            Scanner sc = new Scanner(input);
            PrintWriter printer = new PrintWriter(output);
            while (sc.hasNextLine()) {
                String s = sc.nextLine().replaceAll("\"", "");
                printer.write(s+"\n");
               
            }
            printer.flush();
            printer.close();
            
			
			
			return filepath+".csv";








		}
		catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
}
