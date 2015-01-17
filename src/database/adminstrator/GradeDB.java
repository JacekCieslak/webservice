package adminstrator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.UserGrade;
import common.DBConnection;

public class GradeDB {
	
	public static ArrayList<UserGrade> getUserGrade( int id) throws SQLException, Exception
	{ 
		ArrayList<UserGrade> userGradeData = new ArrayList<UserGrade>();
		Connection dbConn = null;
		try
		{
			dbConn = DBConnection.createConnection();
	    	 Statement stmt = dbConn.createStatement();
	    	 String query = "SELECT id_grade,  title, grade FROM grade WHERE id_user ='"+id+"'";
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
	public static boolean checkGreade(String title, String grade, int idUser) throws Exception{
		boolean status = false;
	   
	    try {
	        String query = "SELECT id_grade FROM grade WHERE UPPER(title)=UPPER('"+title+"') AND grade='"+grade+"' AND id_user='"+idUser+"'";	
	       status = executeIsAvaliable(query);
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println(e);
	        throw e;
	    }
	    return status;
	}
	
	
	public static boolean addGrade(String title, String grade, int idUser ) throws SQLException, Exception {
	    boolean insertStatus = false;
	    try {
	        String query = "INSERT INTO grade(id_user, title, grade) VALUES ('"+idUser+"', '"+title+"', '"+grade+"')";
	       insertStatus = executeInsertQuery(query);
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println(e);
	        throw e;
	    } 
	    return insertStatus;
	}
	
	public static boolean updateGrade(String title, String grade, int id ) throws SQLException, Exception {
	    boolean insertStatus = false;
	    try {
	        String query = "UPDATE grade SET title='"+title+"',grade='"+grade+"' WHERE id_grade = '"+id+"'";
	       insertStatus = executeInsertQuery(query);
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println(e);
	        throw e;
	    } 
	    return insertStatus;
	}
	
	public static boolean deleteGrade(int id) throws SQLException, Exception {
	    boolean insertStatus = false;
	    Connection dbConn = null;
	    try {
	         dbConn = DBConnection.createConnection();
	        
	        Statement stmt = dbConn.createStatement();
	        String query = "DELETE FROM grade WHERE id_grade='"+id+"'";
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
}
