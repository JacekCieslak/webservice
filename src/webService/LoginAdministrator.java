package webService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import database.DBConnection;
//Path: http://localhost/<appln-folder-name>/login
@Path("/loginadmin")
public class LoginAdministrator {
    // HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/login/dologin
    @Path("/dologin")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
    public Response doLogin(@QueryParam("username") String uname, @QueryParam("password") String pwd){
    	String responseStatus = "";
        if(checkCredentials(uname, pwd)){
        	responseStatus = Utitlity.constructJSONP("login",true);
        }else{
        	responseStatus = Utitlity.constructJSONP("login", false, "Niepoprawny login lub has�o");
        }
        return ResponseUtility.ok(responseStatus);     
    }
 
    /**
     * Method to check whether the entered credential is valid
     * 
     * @param uname
     * @param pwd
     * @return
     */
    private boolean checkCredentials(String uname, String pwd){
        System.out.println("Inside checkCredentials");
        boolean result = false;
        if(Utitlity.isNotNull(uname) && Utitlity.isNotNull(pwd)){
            try {
                result = DBConnection.checkLoginAdmin(uname, pwd);
                //System.out.println("Inside checkCredentials try "+result);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                //System.out.println("Inside checkCredentials catch");
                result = false;
            }
        }else{
            //System.out.println("Inside checkCredentials else");
            result = false;
        }
 
        return result;
    }
 
}