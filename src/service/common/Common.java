package common;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.GroupedCourse;



@Path("/common")
public class Common {
	
	@GET
    @Path("/courses")  
    @Produces(MediaType.APPLICATION_JSON) 
    public Response course() {
    	String courses = null;
    	try{
    		ArrayList<GroupedCourse> groupedCourseData = null;
    		groupedCourseData = DBConnection.getGroupedCourse();
    		courses = Utitlity.constructGroupedCourseJSON(groupedCourseData);
    	}catch(Exception e){
    		System.out.println("Exception Error");
    		ResponseUtility.error();
		       
    	}
    	return ResponseUtility.ok(courses);  
    	
    }

}
