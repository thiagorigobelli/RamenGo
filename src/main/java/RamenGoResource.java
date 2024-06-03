import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("api/v1")
public class RamenGoResource {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("orders")
	public Response postOrder(@HeaderParam("x-api-key") String apiKey, Order order) {
		var mapper = new Mapper();
		
		var error = authenticateKey(mapper, apiKey);
		
		if(error != null) {
			return Response.status(Status.FORBIDDEN)
					.entity(error)
					.build();
		}
		
		var orderResponse = mapper.postOrder();

		return Response.ok(201)
				.entity(orderResponse)
				.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("broths")
	public Response fetchAllBroths(@HeaderParam("x-api-key") String apiKey) {
		var mapper = new Mapper();
		
		var error = authenticateKey(mapper, apiKey);
		
		if(error != null) {
			return Response.status(Status.FORBIDDEN)
					.entity(error)
					.build();
		}

		var brothList = mapper.allBroths();

		return Response.ok(200)
				.entity(brothList)
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("proteins")
	public Response fetchAllProteins(@HeaderParam("x-api-key") String apiKey) {
		var mapper = new Mapper();
		
		var error = authenticateKey(mapper, apiKey);
		
		if(error != null) {
			return Response.status(Status.FORBIDDEN)
					.entity(error)
					.build();
		}

		var proteinList = mapper.allProteins();

		return Response.ok(200)
				.entity(proteinList)
				.build();
	}

	private ErrorLog authenticateKey(Mapper foodMapper, String apiKey) {
		if(apiKey == null) {
			return foodMapper.missingApiKey();
		}
		
		if(!apiKey.equals("12345")) {
			return foodMapper.notAuthorized();
		}
		
		return null;
	}
}
