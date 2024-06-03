import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import pojo.Order;

@Path("api/v1")
public class RamenGoResource {
	
	@Inject
    RedVenturesServiceProvider redVenturesServiceProvider;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("broths")
	public Response fetchAllBroths(@HeaderParam("x-api-key") String apiKey) {
		var mapper = new Mapper();
		
		var error = Utils.authenticateKey(mapper, apiKey);
		
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
		
		var error = Utils.authenticateKey(mapper, apiKey);
		
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
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("orders")
	public Response postOrder(@HeaderParam("x-api-key") String apiKey, Order order) {
		var mapper = new Mapper();
		
		var error = Utils.authenticateKey(mapper, apiKey);
		
		if(error != null) {
			return Response.status(Status.FORBIDDEN)
					.entity(error)
					.build();
		}
		
		if(order.getBrothId() == null || order.getProteinId() == null) {
			return Response.status(Status.BAD_REQUEST)
					.entity(mapper.mapErrorLog("both brothId and proteinId are required"))
					.build();
		}
		
		var redVenturesOrder = redVenturesServiceProvider.redVenturesService().postOrder();
		
		try {
			var orderResponse = mapper.postOrder(redVenturesOrder);
			
			return Response.ok(201)
					.entity(orderResponse)
					.build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(mapper.mapErrorLog("could not place order"))
					.build();
		}

	}
}
