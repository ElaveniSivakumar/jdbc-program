package task1;

import java.sql.Connection;



import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//******** CONNECTION TO THE SQL *********

public class JdbcDmlOperations {
private static Connection getConnection() throws SQLException, ClassNotFoundException {
	System.out.println(" getconnection method started...........");
	Class.forName("com.mysql.jdbc.Driver");
	System.out.println(" Driver successfully loaded...");
	Connection connection = DriverManager.getConnection("jdbc:mysql://101.53.155.156:3306/dbms_april_2023","dbms_april_2023","Ebrain@20");
	System.out.println("JDBC connection established successfully "+connection);
	return connection;
}

// ******** INSERTING DATA TO THE TABLE *****

private static void saveUser(User user) throws ClassNotFoundException, SQLException {
	Connection connection = getConnection();
	String insertQuery = "insert into tb_user_elaveni (name,email) values (?,?)";
	PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
	preparedStatement.setString(1,user.getUsername());
	preparedStatement.setString(2,user.getUseremail());
	preparedStatement.executeUpdate();
}

// ******** GETTING ALL DATA FROM THE TABLE **************
private static User getUserByUserId(Integer id) throws Exception{
	Connection connection = getConnection();
	String singleSelectQuery = "select id,name,email from tb_user_elaveni where id = ?";
	PreparedStatement preparedStatement = connection.prepareStatement(singleSelectQuery);
	preparedStatement.setInt(1, id);
	ResultSet resultSet = preparedStatement.executeQuery();
	User user = null;
	while(resultSet.next()) {
		user = new User();
		user.setUserid(resultSet.getInt(1));
		user.setUsername(resultSet.getString(2));
		user.setUseremail(resultSet.getString(3));
		
	}
	return user;
	
}

// ****** SELECTING SPECIFIC COLUMN AND PRINTING THE DATA (GETTING ONE OR TWO DATA)*********
private static List<User> getAllUsers() throws Exception{
	Connection connection = getConnection();
	String insertQuery = "select id,name,email from tb_user_elaveni";
	PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
	ResultSet resultSet = preparedStatement.executeQuery();
	List<User> userList = new ArrayList<User>();
	User user = null;
	while(resultSet.next()) {
		user = new User();
		user.setUserid(resultSet.getInt(1));
		user.setUsername(resultSet.getString(2));
		user.setUseremail(resultSet.getString(3));
	
		userList.add(user);
		
	}
	
	return userList;
}

//******** UPDATE **********

private static void updateUser(User user) throws ClassNotFoundException, SQLException {
	Connection connection = getConnection();
	String updateQuery = "update tb_user_elaveni set name=?, email=? where id=?";
	PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
	preparedStatement.setString(1,user.getUsername());
	preparedStatement.setString(2,user.getUseremail());
	preparedStatement.setInt(3,user.getUserid());
	preparedStatement.executeUpdate();
}

//// ***** DELETE ***********
//
//private static void deleteUser(User user) throws ClassNotFoundException, SQLException {
//	Connection connection = getConnection();
//	String deleteQuery = "delete from tb_user_elaveni where id=?";
//	PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
//	preparedStatement.setInt(1,user.getUserid());
//	preparedStatement.executeUpdate();
//}

public static void main(String[] args) throws Exception {
	
	// ******** INSERTING DATA TO THE TABLE *****
	
//	User user = new User();
//	user.setUsername("vimal");
//	user.setUseremail("yyyyyyyyyyyyyyyy@gmail.com");
//	saveUser(user);
	
	
	// ******** GETTING ALL DATA FROM THE TABLE **************
	
	List<User> users = getAllUsers();
	for (User obj : users) {
		System.out.println("ID : " +obj.getUserid() +" NAME : " +obj.getUsername()+ " EMAIL ID: "+obj.getUseremail());
	}
	//java 8
	//users.forEach(obj ->{System.out.println("ID : " +sobj.getUserid()+"NAME : " +obj.getUsername()+"EMAIL ID: "+obj.getUseremail());});
	
	System.out.println("----------------------------------------------------------------------------");
	
	// ****** SELECTING SPECIFIC COLUMN AND PRINTING THE DATA (GETTING ONE OR TWO DATA)*********
    User user = getUserByUserId(1);
    System.out.println("ID : " +user.getUserid() +" NAME : " +user.getUsername()+ " EMAIL ID: "+user.getUseremail());
    
    // ******* UPDATE *******
    
    user.setUsername("vijay");
    user.setUseremail("zzzzzzzzzzz@gmail.com");
    updateUser(user);
    
    System.out.println("----------------------------------------------------------------------------");
	
//    // ***** DELETE ***********
//    deleteUser(user);
}
}
