package webService;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import dataObjects.specializationObjects;
import database.DBConnection;


//Path: http://localhost/<appln-folder-name>/register
@Path("/register")
public class Register {
    // HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/register/doregister
    @Path("/doregister")  
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/register/doregister?name=pqrs&username=abc&password=xyz
    public String doRegister(@QueryParam("name") String name, @QueryParam("surname") String surname, @QueryParam("username") String uname, @QueryParam("password") String pwd, @QueryParam("id_specialization") String idspe){
    	String response = "";
        System.out.println("Inside doregister "+name+" "+surname+" "+uname+"  "+pwd+" "+idspe);
        int retCode = registerUser(name, surname, uname, pwd, idspe);
        if(retCode == 0){
            response = Utitlity.constructJSON("register",true);
        }else if(retCode == 1){
            response = Utitlity.constructJSON("register",false, "You are already registered");
        }else if(retCode == 2){
            response = Utitlity.constructJSON("register",false, "Special Characters are not allowed in Username and Password");
        }else if(retCode == 3){
            response = Utitlity.constructJSON("register",false, "Error occured");
        }
        return response;
 
    }
    
    
    @GET
    @Path("/getspecialization")  
    @Produces(MediaType.APPLICATION_JSON) 
    public String speclaization() {
    	String specjalzations = null;
    	try{
    		ArrayList<specializationObjects> specializationData = null;
    		specializationData = DBConnection.getSpejalizations();
    		specjalzations = Utitlity.constructSpecializationJSON(specializationData);
    	}catch(Exception e){
    		System.out.println("Exception Error");
    	}
    	return specjalzations;
    }
 
    private int registerUser(String name, String username,  String uname, String pwd, String idspe){
        System.out.println("Inside checkCredentials");
        int result = 3;
        if(Utitlity.isNotNull(uname) && Utitlity.isNotNull(username) && Utitlity.isNotNull(pwd) && Utitlity.isNotNull(idspe)){
            try {
                if(DBConnection.insertUser(name, username, uname, pwd, idspe)){
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