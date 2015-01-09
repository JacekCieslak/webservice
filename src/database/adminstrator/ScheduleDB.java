package adminstrator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	 		System.out.print(query);
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

}
