package adminstrator;

import java.util.ArrayList;

import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Documents;
import common.ResponseUtility;
import common.Utitlity;

@Path("/model/document")
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
	
	 @GET
	    @Path("/getdocument")
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response getDocument(@QueryParam("coursename") String courseName, @QueryParam("name") String name ) {
	    	boolean result = false;
	    	try{
	    		result = DocumentDB.getDocument(name,courseName);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

 		if(result)
 			return ResponseUtility.ok();
 		else 
 			return ResponseUtility.notExist();
	    	
	    }
	 
	 	@POST
	    @Path("/insertdocument")
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response insertDocument(@QueryParam("name") String name, @QueryParam("coursename") String course) {
	    	boolean result = false;
	    	try{
	    		
	    		result = DocumentDB.insertDocument(name, course);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

 		if(result)
 			return ResponseUtility.ok();
 		else 
 			return ResponseUtility.error();
	    	
	    }
	 	
	 	@GET
	    @Path("/deletedocument/{id}")
	    @Produces(MediaType.APPLICATION_JSON) 
	    public Response deleteDocument(@PathParam("id") int id) {
	    	boolean result = false;
	    	try{
	    		
	    		result = DocumentDB.deleteDocument(id);
	    	}catch(Exception e){
	    		return ResponseUtility.error();
	    	}

    		if(result)
    			return ResponseUtility.ok();
    		else 
    			return ResponseUtility.error();
	    	
	    }  
}
