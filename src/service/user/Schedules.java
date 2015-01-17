package user;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Schedule;
import user.ScheduleDB;
import common.ResponseUtility;
import common.Utitlity;

@Path("/user/schedule/")
public class Schedules {
	@GET
    @Path("/getschedule/")  
    @Produces(MediaType.APPLICATION_JSON) 
    public Response getSchendules(@QueryParam("day") String day) {
    	String users = null;
    	try{
    		ArrayList<Schedule> scheduleData = null;
    		scheduleData = ScheduleDB.getSchedule(day);
    		users = Utitlity.constructScheduleJSON(scheduleData);
    	}catch(Exception e){
    		return ResponseUtility.error();
    	}
    	return ResponseUtility.ok(users);
    }

}
