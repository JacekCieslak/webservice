package webService;

import java.sql.SQLException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import database.DBConnection;


//Path: http://localhost/<appln-folder-name>/register
@Path("/register")
public class Register {
    // HTTP Get Method
    @POST
    // Path: http://localhost/<appln-folder-name>/register/doregister
    @Path("/doregister")  
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/register/doregister?name=pqrs&username=abc&password=xyz
    public Response doRegister(@QueryParam("name") String name, @QueryParam("surname") String surname, @QueryParam("username") String uname, @QueryParam("password") String pwd, @QueryParam("specializationName") String specializationName,  @QueryParam("specializationGroup") String specializationGroup ){
    	String responseStatus = "";
      //  System.out.println("Inside doregister "+name+" "+surname+" "+uname+"  "+pwd+" "+specializationName+" "+specializationGroup);
        int retCode = registerUser(name, surname, uname, pwd, specializationName, specializationGroup);
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
    
    
    
 
    private int registerUser(String name, String username,  String uname, String pwd, String specializationName, String specializationGroup){
        System.out.println("Inside checkCredentials");
        int result = 3;
        if(Utitlity.isNotNull(uname) && Utitlity.isNotNull(username) && Utitlity.isNotNull(pwd) && Utitlity.isNotNull(specializationName) && Utitlity.isNotNull(specializationGroup)){
            try {
                if(DBConnection.insertUser(name, username, uname, pwd, specializationName, specializationGroup)){
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