package adminstrator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Schedule;
import common.DBConnection;

public class ScheduleDB {
	
	
	public static ArrayList<Schedule> getSchedule() throws SQLException, Exception
	{ 
		ArrayList<Schedule> userData = new ArrayList<Schedule>();
		Connection dbConn = null;
		try
		{
			dbConn = DBConnection.createConnection();
	    	 Statement stmt = dbConn.createStatement();
	    	 String query = "SELECT id_schedule, classes, hour, day, week, place, audytorium, information FROM schedule";
	 		 ResultSet rs = stmt.executeQuery(query);

	         while (rs.next())
	         {
	        	 Schedule scheduleObject = new Schedule();
	        	 scheduleObject.setId(Integer.parseInt(rs.getString(1)));
	        	 scheduleObject.setClasses(rs.getString(2));
	        	 scheduleObject.setHour(Integer.parseInt(rs.getString(3)));
	        	 scheduleObject.setDay(rs.getString(4));
	        	 scheduleObject.setWeek(rs.getString(5));
	        	 scheduleObject.setPlace(rs.getString(6));
	        	 scheduleObject.setAudytorium(rs.getString(7));
	        	 scheduleObject.setInformation(rs.getString(8));
	        	 userData.add(scheduleObject);
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
	
	public static ArrayList<Schedule> getSchedules(int id) throws SQLException, Exception
	{ 
		ArrayList<Schedule> userData = new ArrayList<Schedule>();
		Connection dbConn = null;
		try
		{
			dbConn = DBConnection.createConnection();
	    	 Statement stmt = dbConn.createStatement();
	    	 String query = "SELECT id_schedule, classes, hour, day, week, place, audytorium, information FROM schedule WHERE id_schedule ='"+id+"'";
	 		
	    	 ResultSet rs = stmt.executeQuery(query);

	         while (rs.next())
	         {
	        	 Schedule scheduleObject = new Schedule();
	        	 scheduleObject.setId(Integer.parseInt(rs.getString(1)));
	        	 scheduleObject.setClasses(rs.getString(2));
	        	 scheduleObject.setHour(Integer.parseInt(rs.getString(3)));
	        	 scheduleObject.setDay(rs.getString(4));
	        	 scheduleObject.setWeek(rs.getString(5));
	        	 scheduleObject.setPlace(rs.getString(6));
	        	 scheduleObject.setAudytorium(rs.getString(7));
	        	 scheduleObject.setInformation(rs.getString(8));
	        	 userData.add(scheduleObject);
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
	
	public static boolean checkSchendule(int hour, String day, String week) throws  Exception {
		boolean status = false;
		   
	    try {
	    	String query = "SELECT * FROM schedule where hour='"+hour+"' AND day='"+day+"' AND (week='"+week+"' OR week='A/B') ";
	        status = executeIsAvaliable(query);
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println(e);
	        throw e;
	    }
	    return status;
	}
	
	
	public static boolean addSchedule( String classes, String hour, String day, String week, String place, String audytorium, String info) throws SQLException, Exception {
		 boolean insertStatus = false;
		    try {
		    String query = "INSERT INTO schedule(classes, hour, day, week, place, `audytorium`, `information`) "
		        		+ "VALUES ('"+classes+"', '"+hour+"', '"+day+"', '"+week+"', '"+place+"', '"+audytorium+"', '"+info+"')";
		    System.out.println(query);   
		    insertStatus = executeInsertQuery(query);
		      
		    } catch (Exception e) {
		        e.printStackTrace();
		        System.out.println(e);
		        throw e;
		    } 
		    return insertStatus;
		}
	
	public static boolean deleteSchedule(int id) throws SQLException, Exception {
	    boolean insertStatus = false;
	    Connection dbConn = null;
	    try {
	         dbConn = DBConnection.createConnection();
	        
	        Statement stmt = dbConn.createStatement();
	        String query = "DELETE FROM schedule WHERE id_schedule='"+id+"'";
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
 	    boolean isAvailable = false;
 	    
 	   	Connection dbConn = null;
 	    	try
 	    	{
 	    		dbConn = DBConnection.createConnection();
 		    	 Statement stmt = dbConn.createStatement();
 		    	    	    	 
 		 		 ResultSet rs = stmt.executeQuery(query);

 	        while (rs.next()) {
 	            isAvailable = true;
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
 	    return isAvailable;
 	}
}
