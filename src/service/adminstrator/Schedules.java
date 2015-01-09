package adminstrator;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	 
}
