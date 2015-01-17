package user;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.UserGrade;
import user.GradeDB;
import common.ResponseUtility;
import common.Utitlity;

@Path("/user/grade/")
public class Grade {
	
	@GET
    @Path("/getgrade/")  
    @Produces(MediaType.APPLICATION_JSON) 
    public Response getUserGrade(@QueryParam("username") String userName) {
    	String userGrade = null;
    	try{
    		ArrayList<UserGrade> userGradeData = null;
    		userGradeData = GradeDB.getUserGrade(userName);
    		userGrade = Utitlity.constructUserGradeJSON(userGradeData);
    	}catch(Exception e){
    		return ResponseUtility.error();
    	}
    	return ResponseUtility.ok(userGrade);
    }

}
