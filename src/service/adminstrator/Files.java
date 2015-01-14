package adminstrator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.multipart.FormDataParam;
 




//import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;






@Path("/files")
public class Files {
			 
	@POST
    @Path("/pdf")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(@FormDataParam("file") File file) {
        InputStream IS = null;;
        String uploadedFileLocation = "d://" + "Test.zip";

        try {
            IS = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

        }

        // save it
        writeToFile(IS, uploadedFileLocation);

        String output = "The PDF File uploaded to : " + uploadedFileLocation;

        return Response.status(200).entity(output).build();

    }
	
	 private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
	        OutputStream out = null;
	        try {
	            out = new FileOutputStream(new File(uploadedFileLocation));
	            int read = 0;
	            byte[] bytes = new byte[1024];

	            out = new FileOutputStream(new File(uploadedFileLocation));
	            while ((read = uploadedInputStream.read(bytes)) != -1) {
	                out.write(bytes, 0, read);
	            }
	            out.flush();
	            out.close();
	            uploadedInputStream.close();
	        } catch (IOException e) {

	            e.printStackTrace();
	        } finally {
	            try {
	                if (out != null) {
	                    out.close();

	                }
	                if (uploadedInputStream != null) {
	                    uploadedInputStream.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }

	    }
//	@POST
//	@Path("/upload")
//	@Consumes("multipart/form-data")
//	public Response uploadFile(MultipartFormDataInput input) {
// 
//		String fileName = "";
// 
//		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
//		List<InputPart> inputParts = uploadForm.get("uploadedFile");
// 
//		for (InputPart inputPart : inputParts) {
// 
//		 try {
// 
//			MultivaluedMap<String, String> header = inputPart.getHeaders();
//			fileName = getFileName(header);
// 
//			//convert the uploaded file to inputstream
//			InputStream inputStream = inputPart.getBody(InputStream.class,null);
// 
//			byte [] bytes = IOUtils.toByteArray(inputStream);
// 
//			//constructs upload file path
//			fileName = UPLOADED_FILE_PATH + fileName;
// 
//			writeFile(bytes,fileName);
// 
//			System.out.println("Done");
// 
//		  } catch (IOException e) {
//			e.printStackTrace();
//		  }
// 
//		}
// 
//		return Response.status(200)
//		    .entity("uploadFile is called, Uploaded file name : " + fileName).build();
// 
//	}
// 
//	/**
//	 * header sample
//	 * {
//	 * 	Content-Type=[image/png], 
//	 * 	Content-Disposition=[form-data; name="file"; filename="filename.extension"]
//	 * }
//	 **/
//	//get uploaded filename, is there a easy way in RESTEasy?
//	private String getFileName(MultivaluedMap<String, String> header) {
// 
//		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
// 
//		for (String filename : contentDisposition) {
//			if ((filename.trim().startsWith("filename"))) {
// 
//				String[] name = filename.split("=");
// 
//				String finalFileName = name[1].trim().replaceAll("\"", "");
//				return finalFileName;
//			}
//		}
//		return "unknown";
//	}
// 
//	//save to somewhere
//	private void writeFile(byte[] content, String filename) throws IOException {
// 
//		File file = new File(filename);
// 
//		if (!file.exists()) {
//			file.createNewFile();
//		}
// 
//		FileOutputStream fop = new FileOutputStream(file);
// 
//		fop.write(content);
//		fop.flush();
//		fop.close();
// 
//	}
}
