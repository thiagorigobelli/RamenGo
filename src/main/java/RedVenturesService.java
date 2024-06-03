import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("orders")
@RegisterRestClient
@Consumes("application/json")
@Produces("application/json")
public interface RedVenturesService {
    @POST
    @Path("generate-id")
    RedVenturesOrder postOrder();
}