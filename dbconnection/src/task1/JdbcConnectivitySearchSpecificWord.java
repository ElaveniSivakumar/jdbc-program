package task1;


//**************** SEARCHING SPECIFIC WORD IN DATABASE ********************

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcConnectivitySearchSpecificWord {
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
		public static List<Vendor> getAllVendor(String name) throws ClassNotFoundException, SQLException {
						Connection connection = getConnection();
						String query ="SELECT id,name,phone,email,address FROM tb_vendor_elaveni";
						if (null != name) {
							query = query + " WHERE name = '"+name+"' ";
						}
					    PreparedStatement preparedStatement = connection.prepareStatement(query);
					    ResultSet resultSet = preparedStatement.executeQuery();
					    List<Vendor> vendors = new ArrayList<Vendor>();
					    while (resultSet.next()) {
							Integer vendorid = resultSet.getInt(1);
							String vendorname = resultSet.getString(2);
							String vendorphone  = resultSet.getString(3);
							String vendoremail = resultSet.getString(4);
							String vendoraddress = resultSet.getString(5);
							Vendor vendor = new Vendor(vendorid, vendorname, vendorphone, vendoremail, vendoraddress);
							vendors.add(vendor);
						}
					  return vendors;
					} 
		public static void main(String[] args) throws ClassNotFoundException, SQLException {
						List<Vendor> vendors = getAllVendor("Elaveni");
						for (Vendor vendor : vendors) {
							System.out.println(vendor.toString());
						}
						System.out.println("completed successfully........");
					     
					 	
					}
}
