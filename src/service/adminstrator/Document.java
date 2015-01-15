package adminstrator;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import common.ResponseUtility;
import common.Utitlity;

@Path("/adminstrator/document")
public class Document {

	@GET
    @Path("/getdocuments")  
    @Produces(MediaType.APPLICATION_JSON) 
    public Response getDocuments(@QueryParam("course") String course) {
    	String documents = null;
    	try{
    		ArrayList<Documents> documentData = null;
    		documentData = DocumentDB.getDocuments(course);
    		documents = Utitlity.construcDocumentsJSON(documentData);
    	}catch(Exception e){
    		return ResponseUtility.error();
    	}
    	return ResponseUtility.ok(documents);
    }
}
