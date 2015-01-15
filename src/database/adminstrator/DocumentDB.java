package adminstrator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.DBConnection;

public class DocumentDB {

	public static ArrayList<Documents> getDocuments(String course) throws SQLException, Exception
	{ 
		ArrayList<Documents> dData =  new ArrayList<Documents>();
		Connection dbConn = null;
		try
		{
			dbConn = DBConnection.createConnection();
	    	 Statement stmt = dbConn.createStatement();
	    	 String query = "SELECT id_file, name, course FROM file WHERE UPPER(course) =UPPER('"+course+"')";
	    	 System.out.print(query);
	 		 ResultSet rs = stmt.executeQuery(query);

	         while (rs.next())
	         {
	        	 Documents documentObject = new Documents();
	        	 documentObject.setId(Integer.parseInt(rs.getString(1)));
	        	 documentObject.setName(rs.getString(2));
	        	 documentObject.setCourse(rs.getString(3));
	        	 dData.add(documentObject);
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
		return dData;
	}
	
}
