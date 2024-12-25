import java.lang.ClassNotFoundException;
//import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
public  class DBConnection{

	Connection con;

	public DBConnection(){
		String username = "root";
		String password = "root";
		String url = "jdbc:mysql://localhost:3306/CopyWindow";
		try{
			Class.forName("com.mysql.cj.jdbc");
			con = DriverManager.getConnection(url,  username, password);
			
		}
		catch(ClassNotFoundException e){
			System.out.println(e);
		}
		catch(SQLException se){
			System.out.println(se);
		}
	}
	private void insertGeneralContent(String label, String content){
		String query = "INSERT into generalContent (label, content) values("+label+","+content+")";
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			System.out.println(rs.getString("name"));
			st.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}