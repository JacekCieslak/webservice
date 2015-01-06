 package webService;
 
import java.util.ArrayList;


import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dataObjects.course;
import database.DBConnection;
 
 @Path("/adminstrator")
public class Administrator {

	    @GET
	    @Path("/courses")  
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response getCourses() {
	    	String courses = null;
	    	try{
	    		ArrayList<course> courseData = null;
	    		courseData = DBConnection.getCoursesAdmin();
	    		courses = Utitlity.constructCourseJSON(courseData);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}
	    	return ResponseUtility.ok(courses);
	    }
	    
	    @POST
	    @Path("/courses/{id}")
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response updateCourse(@PathParam("id") int id, @QueryParam("name") String name, @QueryParam("group") String group) {
	    	boolean result = false;
	    	try{
	    		
	    		result = DBConnection.updateCourse(id,name,group);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

    		if(result)
    			return ResponseUtility.ok();
    		else 
    			return ResponseUtility.error();
	    	
	    }
	    
	    @GET
	    @Path("/courses/addcourse")
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response updateCoursesName(@QueryParam("name") String name) {
	    	boolean result = false;
	    	try{
	    		
	    		result = DBConnection.updateCoursesName(name);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

    		if(result)
    			return ResponseUtility.ok();
    		else 
    			return ResponseUtility.error();
	    	
	    }
	    
	    
	    
	    @GET
	    @Path("/courses/{id}")
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response deleteCourse(@PathParam("id") int id) {
	    	boolean result = false;
	    	try{
	    		
	    		result = DBConnection.deleteCourse(id);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

    		if(result)
    			return ResponseUtility.ok();
    		else 
    			return ResponseUtility.error();
	    	
	    }  
	    
	    
	    @POST
	    @Path("/courses/addgroup")
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response insertGroup(@QueryParam("name") String name, @QueryParam("group") String group) {
	    	boolean result = false;
	    	try{
	    		
	    		result = DBConnection.insertGroup(name,group);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

    		if(result)
    			return ResponseUtility.ok();
    		else 
    			return ResponseUtility.error();
	    }
	    
	    @POST
	    @Path("/courses/insertcourse")
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response updateCourse(@QueryParam("oldname") String oldname, @QueryParam("newname") String newname) {
	    	boolean result = false;
	    	try{
	    		
	    		result = DBConnection.updateCourse(oldname,newname);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

    		if(result)
    			return ResponseUtility.ok();
    		else 
    			return ResponseUtility.error();
	    }
	    
	    @GET
	    @Path("/courses/course/{coursename}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response getCourseGroupsName(@PathParam("coursename") String name ){
	    	boolean result = false;
	    	try{
	    		
	    		result = DBConnection.getCourseGroups(name);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

    		if(result)
    			return ResponseUtility.ok();
    		else 
    			return ResponseUtility.notExist();
	    }
	    
	    @GET
	    @Path("/courses/{courseid}/{groupname}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response getGroupName(@PathParam("courseid") int courseid, @PathParam("groupname") String groupname ){
	    	boolean result = false;
	    	try{
	    		
	    		result = DBConnection.getGroup(courseid,groupname);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

    		if(result)
    			return ResponseUtility.ok();
    		else 
    			return ResponseUtility.notExist();
	    }
	    
	    @GET
	    @Path("/courses/getcourse")
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response getCourseName(@QueryParam("name") String name) {
	    	boolean result = false;
	    	try{
	    		
	    		result = DBConnection.getCourse(name);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

    		if(result)
    			return ResponseUtility.ok();
    		else 
    			return ResponseUtility.error();
	    	
	    }
	   
	    
 }
