package user;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import common.DBConnection;
import common.ResponseUtility;
import common.Utitlity;


@Path("/register")
public class Register {
    @POST
    @Path("/doregister")  
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON) 
   // public Response doRegister(@QueryParam("name") String name, @QueryParam("surname") String surname, @QueryParam("username") String uname, @QueryParam("password") String pwd, @QueryParam("coursename") String courseName,  @QueryParam("coursegroup") String courseGroup ){
    public Response doRegister(MultivaluedMap<String, String> newUser){
    	
    	String name = newUser.getFirst("name");
    	String surname = newUser.getFirst("surname");
    	String uname = newUser.getFirst("username");
    	String pwd = newUser.getFirst("password");
    	String courseName = newUser.getFirst("coursename");
    	String courseGroup = newUser.getFirst("coursegroup");
    	
    	System.out.println(name);
    	System.out.println(surname);
    	System.out.println(uname);
    	System.out.println(pwd);
    	System.out.println(courseName);
    	
    	String responseStatus = "";
        int retCode = registerUser(name, surname, uname, pwd, courseName, courseGroup);
        if(retCode == 0){
        	responseStatus = Utitlity.constructJSON("register",true);
        }else if(retCode == 1){
        	responseStatus = Utitlity.constructJSON("register",false, "You are already registered");
        }else if(retCode == 2){
        	responseStatus = Utitlity.constructJSON("register",false, "Special Characters are not allowed in Username and Password");
        }else if(retCode == 3){
        	responseStatus = Utitlity.constructJSON("register",false, "Error occured");
        }
        return ResponseUtility.ok(responseStatus);
  		       
 
    }
    
    
    
 
    private int registerUser(String name, String username,  String uname, String pwd, String courseName, String courseGroup){
        System.out.println("Inside checkCredentials");
        int result = 3;
        if(Utitlity.isNotNull(uname) && Utitlity.isNotNull(username) && Utitlity.isNotNull(pwd) && Utitlity.isNotNull(courseName) && Utitlity.isNotNull(courseGroup)){
            try {
                if(DBConnection.insertUser(name, username, uname, pwd, courseName, courseGroup)){
                    System.out.println("RegisterUSer if");
                    result = 0;
                }
            } catch(SQLException sqle){
                System.out.println("RegisterUSer catch sqle");
                //When Primary key violation occurs that means user is already registered
                if(sqle.getErrorCode() == 1062){
                    result = 1;
                } 
                //When special characters are used in name,username or password
                else if(sqle.getErrorCode() == 1064){
                    System.out.println(sqle.getErrorCode());
                    result = 2;
                }
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.println("Inside checkCredentials catch e ");
                result = 3;
            }
        }else{
            System.out.println("Inside checkCredentials else");
            result = 3;
        }
 
        return result;
    }
 
}