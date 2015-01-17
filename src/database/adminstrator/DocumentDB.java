package adminstrator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Documents;
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
	
	public static boolean getDocument(String name, String courseName) throws Exception{
		boolean status = false;
	   
	    try {
	        String query = "SELECT id_file FROM file WHERE UPPER(name)=UPPER('"+name+"') and UPPER(course)=UPPER('"+courseName+"')";	
	        status = executeIsAvaliable(query);
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println(e);
	        throw e;
	    }
	    return status;
	}
	
	public static boolean insertDocument( String name, String course) throws SQLException, Exception {
		 boolean insertStatus = false;
		    try {
		    String query = "INSERT INTO file(name, course) VALUES ('"+name+"','"+course+"')";
		    System.out.println(query);   
		    insertStatus = executeInsertQuery(query);
		      
		    } catch (Exception e) {
		        e.printStackTrace();
		        System.out.println(e);
		        throw e;
		    } 
		    return insertStatus;
		}
	
	public static boolean deleteDocument(int id) throws SQLException, Exception {
	    boolean insertStatus = false;
	    Connection dbConn = null;
	    try {
	         dbConn = DBConnection.createConnection();
	        
	        Statement stmt = dbConn.createStatement();
	        String query = "DELETE FROM file  WHERE id_file='"+id+"'";
	        System.out.println(query);
	        int records = stmt.executeUpdate(query);
	        if (records > 0) {
	            insertStatus = true;
	        }
	    } catch (SQLException sqle) {
	    	System.out.println(sqle);
	        sqle.printStackTrace();
	        throw sqle;
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println(e);
	        // TODO Auto-generated catch block
	        if (dbConn != null) {
	            dbConn.close();
	        }
	        throw e;
	    } finally {
	        if (dbConn != null) {
	            dbConn.close();
	        }
	    }
	    return insertStatus;
	}
	
	private static boolean executeInsertQuery(String query) throws SQLException, Exception{
		boolean status = false;
	    Connection dbConn = null;
	 //   System.out.println("try inserstUser" );
	    try {
	           
	    	dbConn = DBConnection.createConnection();
	        Statement stmt = dbConn.createStatement();
	       
	        int records = stmt.executeUpdate(query);
	        System.out.println(records);
	        //When record is successfully inserted
	        if (records > 0) {
	            status = true;
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
	    return status;
	}

	
	private static boolean executeIsAvaliable(String query) throws SQLException, Exception {
	    boolean isUserAvailable = false;
	    
	   	Connection dbConn = null;
	    	try
	    	{
	    		dbConn = DBConnection.createConnection();
		    	 Statement stmt = dbConn.createStatement();
		    	    	    	 
		 		 ResultSet rs = stmt.executeQuery(query);

	        while (rs.next()) {
	            isUserAvailable = true;
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
	    return isUserAvailable;
	}
}
