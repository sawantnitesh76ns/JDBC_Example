//STEP 1. Import required packages
import java.sql.*;

public class JDBCExample {
	static final String DRIVER="com.mysql.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost/SampleDB";
	
	static final String UserName="root";
	static final String PassWord="welcome2020";
	static Connection conn;
	static Statement stmt;
	public static void main(String[] args) {
		
		try {
			Class.forName(DRIVER);
			conn=DriverManager.getConnection(DB_URL,UserName,PassWord);
//			String sql="DROP DATABASE STUDENTS";
//			stmt=conn.createStatement();
//			stmt.executeUpdate(sql);
//			System.out.println("Database Droped Successfully");
			if (conn != null) {
		        System.out.println("Connected");
		    }
			
			insertStatement();
			selectStatement();
			updateStatement();
			deleteStatement();
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public static void insertStatement() {
		String sql = "INSERT INTO Users (username, password, fullname, email) VALUES (?, ?, ?, ?)";
		 
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, "bill");
			statement.setString(2, "secretpass");
			statement.setString(3, "Bill Gates");
			statement.setString(4, "bill.gates@microsoft.com");
			 
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
			    System.out.println("A new user was inserted successfully!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void selectStatement() {
		String sql = "SELECT * FROM Users";
		 
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			 
			int count = 0;
			 
			while (result.next()){
			    String name = result.getString(2);
			    String pass = result.getString(3);
			    String fullname = result.getString("fullname");
			    String email = result.getString("email");
			 
			    String output = "User #%d: %s - %s - %s - %s";
			    System.out.println(String.format(output, ++count, name, pass, fullname, email));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void updateStatement() {
		String sql = "UPDATE Users SET password=?, fullname=?, email=? WHERE username=?";
		 
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, "123456789");
			statement.setString(2, "William Henry Bill Gates");
			statement.setString(3, "bill.gates@microsoft.com");
			statement.setString(4, "bill");
			 
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("An existing user was updated successfully!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void deleteStatement() {
		String sql = "DELETE FROM Users WHERE username=?";
		 
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, "bill");
			 
			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
			    System.out.println("A user was deleted successfully!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
   
}//end JDBCExample