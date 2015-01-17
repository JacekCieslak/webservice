package adminstrator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import common.DBConnection;
import common.ResponseUtility;
import common.Utitlity;
@Path("/loginadmin")
public class Login {
  
    @GET
    @Path("/dologin")
    @Produces(MediaType.APPLICATION_JSON) 
    public Response doLogin(@QueryParam("username") String uname, @QueryParam("password") String pwd){
    	String responseStatus = "";
        if(checkCredentials(uname, pwd)){
        	responseStatus = Utitlity.constructJSONP("login",true);
        }else{
        	responseStatus = Utitlity.constructJSONP("login", false, "Niepoprawny login lub has³o");
        }
        return ResponseUtility.ok(responseStatus);     
    }
 
    private boolean checkCredentials(String uname, String pwd){
        System.out.println("Inside checkCredentials");
        boolean result = false;
        if(Utitlity.isNotNull(uname) && Utitlity.isNotNull(pwd)){
            try {

                result = DBConnection.checkLoginAdmin(uname, pwd);
            } catch (Exception e) {
                result = false;
            }
        }else{
            result = false;
        }
 
        return result;
    }
 
}