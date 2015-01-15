package adminstrator;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.google.common.io.Files;

@Path("/file")
public class FileDirecotry {



	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {

		
		String savePath = System.getProperty("java.io.tmpdir");
		
			System.out.println(savePath);
		
		String filePath = savePath 
				+ contentDispositionHeader.getFileName();
		
		
		// save the file to the server
		saveFile(fileInputStream, filePath);

		String output = "File saved to server location : " + filePath;

		return Response.status(200).entity(output).build();

	}
	
	@GET
	@Path("/attachment")
	@Consumes("text/plain; charset=UTF-8")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getAttachment(
	  @QueryParam("file") String fileName) {
	  try {
	    if (fileName == null) {
	      System.err.println("No such item");
	      return Response.status(Response.Status.BAD_REQUEST).build();
	    }
	    String path = "/opt/tomcat/files/";
	    StreamingOutput so = new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException,
                    WebApplicationException {
            	FileInputStream fis = new FileInputStream(path+fileName);
                byte[] buffer = new byte[4 * 1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                fis.close();
                os.flush();
                os.close();
            }
        };
	 
	    return Response.ok(so, "application/pdf") //TODO: set content-type of your file
	            .header("content-disposition", "attachment; filename = "+ fileName)
	            .build();
	   
	  } catch (Exception e) {
	     System.err.println(e.getMessage());
	     return Response.status(Response.Status.BAD_REQUEST).build();
	  }
	}
	
	
	
	@GET
	@Path("/attachment/delete")
	@Consumes("text/plain; charset=UTF-8")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response removeAttachment(
	  @QueryParam("file") String fileName) {
	  try {
	    if (fileName == null) {
	      System.err.println("No such item");
	      return Response.status(Response.Status.BAD_REQUEST).build();
	    }
	 
	   String savePath = "/opt/tomcat/files/"; 
      
         
        String filePath = savePath + fileName;
        
        System.out.println(filePath);
        File f = new File(filePath);
        f.delete();
		
	    return Response.ok() 
	            .build();
	   
	    } catch (Exception e) {
	     System.err.println(e.getMessage());
	     return Response.status(Response.Status.BAD_REQUEST).build();
	  }
	}

	@GET
	@Path("/attachment/all")
	@Consumes("text/plain; charset=UTF-8")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response all() {
	  
      String files ="";
      
	
    
File folder = new File("/opt/tomcat/files/");
File[] listOfFiles = folder.listFiles();
for (File file : listOfFiles) {
    if (file.isFile()) {
	files+="\n "+file.getName();
        System.out.println(file.getName());
    }
}
	    return Response.ok(files, "application/text") 
	            .build();
 
	}
	
	
	
	
	// save uploaded file to a defined location on the server
    private void saveFile(InputStream uploadedInputStream, String serverLocation) {
		try {
			String path = serverLocation.toLowerCase().replace("temp","files/");
			System.out.println(path);
			
			File targetFile = new File(path);
			File parent = targetFile.getParentFile();
			if(!parent.exists()){
				Files.createParentDirs(targetFile);
			}
			System.out.println(path);
			OutputStream outpuStream = new FileOutputStream(targetFile );
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = uploadedInputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}