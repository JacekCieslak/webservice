package adminstrator;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import common.ResponseUtility;
import common.Utitlity;


@Path("/adminstrator/grade/")
public class Grade {
	
	
	@GET
    @Path("/user/{id}")  
    @Produces(MediaType.APPLICATION_JSON) 
    public Response getUserGrade(@PathParam("id") int id) {
    	String userGrade = null;
    	try{
    		ArrayList<UserGrade> userGradeData = null;
    		userGradeData = GradeDB.getUserGrade(id);
    		userGrade = Utitlity.constructUserGradeJSON(userGradeData);
    	}catch(Exception e){
    		return ResponseUtility.error();
    	}
    	return ResponseUtility.ok(userGrade);
    }
	

	@GET
    @Path("/checkgrade/{title}/{grade}")  
    @Produces(MediaType.APPLICATION_JSON) 
    public Response checkGreade(@PathParam("title") String title, @PathParam("grade") String grade, @QueryParam("id") int idUser) {
		boolean result = false;
    	try{
    		
    		result = GradeDB.checkGreade(title,grade, idUser);
    	}catch(Exception e){
    		return ResponseUtility.error();
    	}

		if(result)
			return ResponseUtility.ok();
		else 
			return ResponseUtility.notExist();
    }
	@POST
    @Path("/updategrade/{id}")
    @Produces(MediaType.APPLICATION_JSON) 
    public Response updateGrade(@QueryParam("title") String title, @QueryParam("grade") String grade, @PathParam("id") int id) {
    	boolean result = false;
    	try{
    		
    		result = GradeDB.updateGrade(title, grade, id);
    	}catch(Exception e){
    		return ResponseUtility.error();
    	}

		if(result)
			return ResponseUtility.ok();
		else 
			return ResponseUtility.error();
    }
	
	@POST
    @Path("/addgrade/{iduser}")
    @Produces(MediaType.APPLICATION_JSON) 
    public Response addGrade(@QueryParam("title") String title, @QueryParam("grade") String grade, @PathParam("iduser") int idUser) {
    	boolean result = false;
    	try{
    		
    		result = GradeDB.addGrade(title, grade, idUser);
    	}catch(Exception e){
    		return ResponseUtility.error();
    	}

		if(result)
			return ResponseUtility.ok();
		else 
			return ResponseUtility.error();
    }
	
	@GET
    @Path("deletegrade/{id}")
    @Produces(MediaType.APPLICATION_JSON) 
    public Response deleteCourse(@PathParam("id") int id) {
    	boolean result = false;
    	try{
    		
    		result = GradeDB.deleteGrade(id);
    	}catch(Exception e){
    		return ResponseUtility.error();
    	}

		if(result)
			return ResponseUtility.ok();
		else 
			return ResponseUtility.error();
    	
    }  
}
