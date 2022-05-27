package Pages.PayG;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import CommonUtility.CreateSession;


/*
 * Author- Pranab Mukherjee
 * DB validations all around the tenants.
 * 
 * 
 */

public class dbVerification {
	public static void main(String[] args ) {
		CreateSession.getAutomationConfiguration().Tenant = "GMP";
		CreateSession.getAutomationConfiguration().Environment= "QA";
		HashMap<String, String> DB =  sqlDbVerification("SELECT * FROM get_my_parking.transaction WHERE entity_id = 1");
	}
	public synchronized static HashMap<String, String> sqlDbVerification(String sql) {
		HashMap<String,String> data_map = new HashMap<>();
		HashMap<String,List<String>> config = new HashMap<>();
		config.put("GMP STAGING", new ArrayList<>(Arrays.asList("jdbc:mysql://gmp-staging.cobfujfoasov.ap-southeast-1.rds.amazonaws.com:3306/get_my_parking?useSSL=false", "test123", "W:w6ubVf8R@Y<~y")));
		config.put("GMP PRODUCTION", new ArrayList<>(Arrays.asList("jdbc:mysql://gmp-prod-main.cdn3irnd4lbp.us-east-1.rds.amazonaws.com:3306/get_my_parking?useSSL=false", "pranab", "B>HMC9uvjYAssDs")));
		config.put("GMP QA", new ArrayList<>(Arrays.asList("jdbc:mysql://gmp-qa-rds.ciby4hupcl5m.ap-south-1.rds.amazonaws.com:3306/get_my_parking?useSSL=false", "pranabm", "#D+dT2sd78y7V5{")));
		String Country = CreateSession.getAutomationConfiguration().Country ;
		String Tenant = CreateSession.getAutomationConfiguration().Tenant;
		String Env = CreateSession.getAutomationConfiguration().Environment;
		System.out.println(Tenant+" "+ Env);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
//			Connection con = DriverManager.getConnection("jdbc:mysql://gmp-staging.cobfujfoasov.ap-southeast-1.rds.amazonaws.com:3306/get_my_parking?useSSL=false", "test123", "W:w6ubVf8R@Y<~y");
			Connection con = DriverManager.getConnection( config.get(Tenant.toUpperCase()+" "+Env.toUpperCase()).get(0), config.get(Tenant.toUpperCase()+" "+Env.toUpperCase()).get(1), config.get(Tenant.toUpperCase()+" "+Env.toUpperCase()).get(2));
			java.sql.Statement stmt =  con.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    java.sql.ResultSetMetaData md = rs.getMetaData();
		    System.out.println("Connection Done");
		    
		    
		    	while(rs.next()) {
		    		for (int i=1; i<= md.getColumnCount(); i++){
		    			data_map.put(md.getColumnName(i), rs.getString(i));
//		    			System.out.println(rs.getString(i));
		    			}
		    		}
//		    	System.out.println(data_map);
		    	con.close();
		    
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			
			// TODO: handle exception
		}
		return data_map;
		
	}

}
