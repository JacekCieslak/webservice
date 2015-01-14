package common;

import javax.ws.rs.core.Response;

public final class ResponseUtility {
	
	public static Response ok(){
		return	Response.ok()
		      .header("Access-Control-Allow-Origin", "*")
		      .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS, DELETE")
		      .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, X-Requested-With")
		      .status(200)
		      .entity("{}")
		      .build();
	}
	
	public static Response ok(Object obj){
		return	Response.ok()
		      .header("Access-Control-Allow-Origin", "*")
		      .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS, DELETE")
		      .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, X-Requested-With")
		      .entity(obj)
		      .status(200)
		      .build();  
	}
		
	public static Response error(){
		return Response.status(500)
				 .header("Access-Control-Allow-Origin", "*")
			     .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS, DELETE")
			     .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, X-Requested-With")
			     .entity("{}")
			     .build();
	}
	
	public static Response notExist(){
		return Response.status(404)
				.header("Access-Control-Allow-Origin", "*")
			    .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS, DELETE")
			    .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, X-Requested-With")
			     .entity("{}")
			    .build();
	}
}
