package CommonUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Statement;
public class DBConnect {
	public static  synchronized HashMap<String, String> sqlDbVerification(String sql) {
		HashMap<String,String> data_map = new HashMap<>();
		try {
			
			
			
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Before connection");
			Connection con = DriverManager.getConnection("jdbc:mysql://gmp-staging.cobfujfoasov.ap-southeast-1.rds.amazonaws.com:3307", "karan", "bGNjuLtkdxjRaeUg");
//																	   gmp-staging.cobfujfoasov.ap-southeast-1.rds.amazonaws.com
			System.out.println("DB connected successfully");
			java.sql.Statement stmt =  con.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    java.sql.ResultSetMetaData md = rs.getMetaData();
		    
		    
		    	while(rs.next()) {
		    		for (int i=1; i<= md.getColumnCount(); i++){
		    			data_map.put(md.getColumnName(i), rs.getString(i));
		    			System.out.println(rs.getString(i));
		    			}
		    		}
		    	System.out.println(data_map);
		    	con.close();
		    
		} catch (Exception e) {
			System.out.println(e);
			
			// TODO: handle exception
		}
		return data_map;
		
	}

	public static void main(String[] rags) {
		String sqlQuery = "SELECT * FROM get_my_parking_copy.parking_session WHERE id = '1'";
		HashMap<String,String> map = sqlDbVerification(sqlQuery);
	}
	
}

