package user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.UserGrade;

import common.DBConnection;

public class GradeDB {

	
	public static ArrayList<UserGrade> getUserGrade( String userName) throws SQLException, Exception
	{ 
		ArrayList<UserGrade> userGradeData = new ArrayList<UserGrade>();
		Connection dbConn = null;
		try
		{
			dbConn = DBConnection.createConnection();
	    	 Statement stmt = dbConn.createStatement();
	    	 String query = "SELECT id_grade, title, grade FROM grade WHERE id_user = "
	    	 		+ "(SELECT id_user FROM user WHERE username = '"+userName+"')";
	 		 ResultSet rs = stmt.executeQuery(query);

	         while (rs.next())
	         {
	        	 UserGrade userGradeObject = new UserGrade();
	        	 userGradeObject.setId(Integer.parseInt(rs.getString(1)));
	        	 userGradeObject.setTitle(rs.getString(2));
	        	 userGradeObject.setGrade(rs.getString(3));
	        	 userGradeData.add(userGradeObject);
	         }
	      
	 	   } catch (SQLException sqle) {
		        System.out.println(sqle);
		        sqle.printStackTrace();
		        throw sqle;
		     } catch (Exception e) {
		        e.printStackTrace();
		        System.out.println(e);
		        if (dbConn != null) {
		        	dbConn.close();
		        }
		        throw e;
		     } finally {
		    	 if (dbConn != null) {
		    		 dbConn.close();
		    	 }
		     }
		return userGradeData;
	}
}
