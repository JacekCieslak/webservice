package adminstrator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import common.DBConnection;
import common.ResponseUtility;
import common.Utitlity;

@Path("/adminstrator/schedule/")
public class Schedules {
	
	
	
	 	@GET
	    @Path("/getschedule/")  
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response getSchendules() {
	    	String users = null;
	    	try{
	    		ArrayList<Schedule> scheduleData = null;
	    		scheduleData = ScheduleDB.getSchedule();
	    		users = Utitlity.constructScheduleJSON(scheduleData);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}
	    	return ResponseUtility.ok(users);
	    }
	 
	 	@GET
	    @Path("/getschedule/{id}")  
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response getSchendule(@PathParam("id") int id) {
	    	String users = null;
	    	try{
	    		ArrayList<Schedule> scheduleData = null;
	    		scheduleData = ScheduleDB.getSchedules(id);
	    		users = Utitlity.constructScheduleJSON(scheduleData);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}
	    	return ResponseUtility.ok(users);
	    }
	 	
	 	
	 	  @GET
		  @Path("/checkschedule/{hour}/{day}")
		  @Produces(MediaType.APPLICATION_JSON)
		  public Response getGroupName(@PathParam("hour") int hour, @PathParam("day") String day, @QueryParam("week") String week){
		    	boolean result = false;
		    	try{
		    		
		    		result = ScheduleDB.checkSchendule(hour, day, week);
		    	}catch(Exception e){
		    		return ResponseUtility.error();
		    	}

	    		if(result)
	    			return ResponseUtility.ok();
	    		else 
	    			return ResponseUtility.notExist();
		   }
	 	
	 	@POST
	    @Path("/addschedule/")
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response addSchedule(@QueryParam("classes") String  classes, @QueryParam("hour") String hour, @QueryParam("day") String day, @QueryParam("week") String week,
	    									@QueryParam("place") String place, @QueryParam("audytorium") String audytorium, @QueryParam("information") String info) {
	    	boolean result = false;
	    	try{
	    		
	    		result = ScheduleDB.addSchedule(classes,hour,day,week, place, audytorium,info);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

    		if(result)
    			return ResponseUtility.ok();
    		else 
    			return ResponseUtility.error();
	    	
	    }
	 	
	 	@GET
	    @Path("deleteschedule/{id}")
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response deleteSchedule(@PathParam("id") int id) {
	    	boolean result = false;
	    	try{
	    		
	    		result = ScheduleDB.deleteSchedule(id);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

    		if(result)
    			return ResponseUtility.ok();
    		else 
    			return ResponseUtility.error();
	    	
	    }  
	 
	 
}
