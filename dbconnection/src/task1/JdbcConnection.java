
//************** INSERTING THE VALUES IN DATABASE **************


package task1;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcConnection {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
	
	Class.forName("com.mysql.jdbc.Driver");	
 	Connection connection = DriverManager.getConnection("jdbc:mysql://101.53.155.156:3306/dbms_april_2023","dbms_april_2023","Ebrain@20");
	return connection;
	}
	
	public static void saveVendor(Integer vendorId,String vendorName,String phone,String emailId,String address) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tb_vendor_elaveni(id,name ,phone,email,address) VALUES(?,?,?,?,?)");
	    preparedStatement.setInt(1,vendorId);
	    preparedStatement.setString(2,vendorName);
	    preparedStatement.setString(3,phone);
	    preparedStatement.setString(4,emailId);
	    preparedStatement.setString(5,address);
	    preparedStatement.executeUpdate();
	    
	} 

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		saveVendor(10003, "Subhashini", "9585245191","subha16@gmail.com","Thanjavur");
		System.out.println("completed successfully........");
	     
	 	
	}
}
