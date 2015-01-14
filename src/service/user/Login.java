package user;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import common.DBConnection;
import common.ResponseUtility;
import common.Utitlity;
@Path("/login")
public class Login {
	
	@GET
    @Path("/dologin")
    @Produces(MediaType.APPLICATION_JSON) 
    public Response doLogin(@QueryParam("username") String uname, @QueryParam("password") String pwd){
    	String responseStatus = "";
        if(checkCredentials(uname, pwd)){
        	responseStatus = Utitlity.constructJSON("login",true);
        }else{
        	responseStatus = Utitlity.constructJSON("login", false, "Niepoprawny login lub has³o lub twoje konto nie zosta³o jeszcze aktywowane");
        }
        return ResponseUtility.ok(responseStatus);       
    }
    
    
    
    private boolean checkCredentials(String uname, String pwd){
        boolean result = false;
        if(Utitlity.isNotNull(uname) && Utitlity.isNotNull(pwd)){
            try {
                result = DBConnection.checkLogin(uname, pwd);
            } catch (Exception e) {
                result = false;
            }
        }else{
            result = false;
        }
        System.out.println("checkCredentials: "+result);
        return result;
    }
 
}