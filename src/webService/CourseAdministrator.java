 package webService;
 
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dataObjects.course;
import database.CourseAdministratorDB;
 
 @Path("/adminstrator")
public class CourseAdministrator {

	    @GET
	    @Path("/courses")  
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response getCourses() {
	    	String courses = null;
	    	try{
	    		ArrayList<course> courseData = null;
	    		courseData = CourseAdministratorDB.getCoursesAdmin();
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
	    		
	    		result = CourseAdministratorDB.updateCourse(id,name,group);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

    		if(result)
    			return ResponseUtility.ok();
    		else 
    			return ResponseUtility.error();
	    	
	    }
	    
	    
	    @GET
	    @Path("/courses/deletegroup/{id}")
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response deleteGroup(@PathParam("id") int id) {
	    	boolean result = false;
	    	try{
	    		
	    		result = CourseAdministratorDB.deleteGroup(id);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

    		if(result)
    			return ResponseUtility.ok();
    		else 
    			return ResponseUtility.error();
	    	
	    } 
	    @GET
	    @Path("/courses/deletecourse/{name}")
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response deleteCourse(@PathParam("name") String name) {
	    	boolean result = false;
	    	try{
	    		
	    		result = CourseAdministratorDB.deleteCourse(name);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

    		if(result)
    			return ResponseUtility.ok();
    		else 
    			return ResponseUtility.error();
	    	
	    }  
	    
	    @GET
	    @Path("/courses/deleteusers/{name}")
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response deleteUsers(@PathParam("name") String name) {
	    	boolean result = false;
	    	try{
	    		
	    		result = CourseAdministratorDB.deleteUsers(name);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

    		if(result)
    			return ResponseUtility.ok();
    		else 
    			return ResponseUtility.error();
	    	
	    }  
	    

	    @GET
	    @Path("/courses/getcourse")
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response getCourseName(@QueryParam("name") String name) {
	    	boolean result = false;
	    	try{
	    		
	    		result = CourseAdministratorDB.getCourse(name);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

    		if(result)
    			return ResponseUtility.ok();
    		else 
    			return ResponseUtility.notExist();
	    	
	    }
	    
	    
	    
	    @POST
	    @Path("/courses/insertcourse")
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response insertCourse(@QueryParam("name") String name, @QueryParam("group") int group) {
	    	boolean result = false;
	    	try{
	    		
	    		result = CourseAdministratorDB.insertCourse(name, group);
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
	    public Response insertGroup(@QueryParam("name") String name, @QueryParam("group") int group) {
	    	boolean result = false;
	    	try{
	    		
	    		result = CourseAdministratorDB.insertGroup(name,group);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

    		if(result)
    			return ResponseUtility.ok();
    		else 
    			return ResponseUtility.error();
	    }
	    
	    @POST
	    @Path("/courses/updatecourse")
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response updateCourse(@QueryParam("oldname") String oldname, @QueryParam("newname") String newname) {
	    	boolean result = false;
	    	try{
	    		
	    		result = CourseAdministratorDB.updateCourse(oldname,newname);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

    		if(result)
    			return ResponseUtility.ok();
    		else 
    			return ResponseUtility.error();
	    }
	    
	    @GET
	    @Path("/courses/{courseid}/{groupname}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response getGroupName(@PathParam("courseid") int courseid, @PathParam("groupname") String groupname ){
	    	boolean result = false;
	    	try{
	    		
	    		result = CourseAdministratorDB.getGroup(courseid,groupname);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

    		if(result)
    			return ResponseUtility.ok();
    		else 
    			return ResponseUtility.notExist();
	    }
	    
	    @GET
	    @Path("/courses/course/{coursename}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response getCourseGroupsName(@PathParam("coursename") String name ){
	    	boolean result = false;
	    	try{
	    		
	    		result = CourseAdministratorDB.getCourseGroups(name);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

    		if(result)
    			return ResponseUtility.ok();
    		else 
    			return ResponseUtility.notExist();
	    }
	    
	    
	    
	   
	   
	    
 }
