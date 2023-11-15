
// ************** READING THE VALUES FROM DATABASE *************8888


package task1;

	import java.sql.Connection;


	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

	public class JdbcConnectionRead {
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
		public static void getAllVendor() throws ClassNotFoundException, SQLException {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,name,phone,email,address FROM tb_vendor_elaveni");
		    
		    ResultSet resultSet = preparedStatement.executeQuery();
		    while (resultSet.next()) {
				Integer vendorid = resultSet.getInt(1);
				String vendorname = resultSet.getString(2);
				String vendorphone  = resultSet.getString(3);
				String vendoremail = resultSet.getString(4);
				String vendoraddress = resultSet.getString(5);
				System.out.println(vendorid + " : " + vendorname + " : " + vendorphone + " : " + vendoremail + " : " + vendoraddress);
				
			}
		    
		} 
		public static void main(String[] args) throws ClassNotFoundException, SQLException {
			getAllVendor();
			System.out.println("completed successfully........");
		     
		 	
		}
}
