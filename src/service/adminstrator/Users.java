package adminstrator;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.CourseGroup;
import model.User;
import common.ResponseUtility;
import common.Utitlity;
 
 @Path("/model/user/")
public class Users {
	 
//	 @GET
//	    @Path("/users")  
//	    @Produces(MediaType.APPLICATION_JSON) 
//	    public Response getUsers() {
//	    	String users = null;
//	    	try{
//	    		ArrayList<User> userData = null;
//	    		userData = UserAdministratorDB.getUser();
//	    		users = Utitlity.constructUserJSON(userData);
//	    	}catch(Exception e){
//	    		return ResponseUtility.error();
//	    	}
//	    	return ResponseUtility.ok(users);
//	    }
	 
	 @GET
	    @Path("/users/{name}/{id}")  
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response getGroupUsers(@PathParam("name") String name, @PathParam("id") int id) {
	    	String users = null;
	    	try{
	    		ArrayList<User> userData = null;
	    		userData = UserDB.getUser( name, id);
	    		users = Utitlity.constructUserJSON(userData);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}
	    	return ResponseUtility.ok(users);
	    }
	 
	 
	 @GET
	    @Path("/group/{name}")  
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response getGroupUser(@PathParam("name") String name) {
	    	String gropus = null;
	    	try{
	    		ArrayList<CourseGroup> groupData = null;
	    		groupData = UserDB.getGroup(name);
	    		gropus = Utitlity.constructUserGroupJSON(groupData);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}
	    	return ResponseUtility.ok(gropus);
	    }
	 
	 	@POST
	    @Path("/adduser/{id}")
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response addUser(@PathParam("id") int id) {
	    	boolean result = false;
	    	try{
	    		
	    		result = UserDB.addUser(id);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

 		if(result)
 			return ResponseUtility.ok();
 		else 
 			return ResponseUtility.error();
	    	
	    }
	    
	 	
	 	@GET
	    @Path("deleteuser/{id}")
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response deleteCourse(@PathParam("id") int id) {
	    	boolean result = false;
	    	try{
	    		
	    		result = UserDB.deleteUser(id);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

    		if(result)
    			return ResponseUtility.ok();
    		else 
    			return ResponseUtility.error();
	    	
	    }  
	    
}
