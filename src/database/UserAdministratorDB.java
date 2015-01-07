package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dataObjects.CourseGroup;
import dataObjects.User;

public class UserAdministratorDB {
	
	
	public static ArrayList<User> getUser(String name, int id) throws SQLException, Exception
	{ 
		ArrayList<User> userData = new ArrayList<User>();
		Connection dbConn = null;
		try
		{
			dbConn = DBConnection.createConnection();
	    	 Statement stmt = dbConn.createStatement();
	    	 String query = "SELECT u.id_user, u.name, u.surname, u.username, u.status FROM user as u, course as c WHERE c.id_course = u.id_course AND c.name='"+name+"' AND c.group_id='"+id+"'";
	 		 ResultSet rs = stmt.executeQuery(query);

	         while (rs.next())
	         {
	        	 User userObject = new User();
	        	 userObject.setId(Integer.parseInt(rs.getString(1)));
	        	 userObject.setName(rs.getString(2));
	        	 userObject.setSurname(rs.getString(3));
	        	 userObject.setUsername(rs.getString(4));
	        	 userObject.setStatus(rs.getString(5));
	        	 userData.add(userObject);
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
		return userData;
	}
	
	public static ArrayList<CourseGroup> getGroup(String name) throws SQLException, Exception
	{ 
		ArrayList<CourseGroup> courseGroupData = new ArrayList<CourseGroup>();
		Connection dbConn = null;
		try
		{
			 dbConn = DBConnection.createConnection();
	    	 Statement stmt = dbConn.createStatement();
	    	 String query = "SELECT group_id FROM course WHERE name='"+name+"' ORDER BY group_id";
	    	 System.out.println(query);
	 		 ResultSet rs = stmt.executeQuery(query);
	         while (rs.next())
	         {
	        	 CourseGroup courseGroupObject = new CourseGroup();
	        	 courseGroupObject.setId(Integer.parseInt(rs.getString(1)));
	        	 courseGroupData.add(courseGroupObject);
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
		return courseGroupData;
	}
	
	public static boolean addUser(int id) throws SQLException, Exception {
	    boolean updateStatus = false;
	    Connection dbConn = null;
	    try {
	         dbConn = DBConnection.createConnection();
	        
	        Statement stmt = dbConn.createStatement();
	        String query = "UPDATE user SET status='1' WHERE id_user = '"+id+"'";
	        int records = stmt.executeUpdate(query);
	        if (records > 0) {
	            updateStatus = true;
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
	    return updateStatus;
	}

	public static boolean deleteUser(int id) throws SQLException, Exception {
	    boolean insertStatus = false;
	    Connection dbConn = null;
	    try {
	         dbConn = DBConnection.createConnection();
	        
	        Statement stmt = dbConn.createStatement();
	        String query = "DELETE FROM user WHERE id_user='"+id+"'";
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


}
