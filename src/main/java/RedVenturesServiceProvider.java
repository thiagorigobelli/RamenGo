import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

import org.eclipse.microprofile.rest.client.RestClientBuilder;

@ApplicationScoped
public class RedVenturesServiceProvider {

    public RedVenturesService redVenturesService() {
    	String apiKey = "ZtVdh8XQ2U8pWI2gmZ7f796Vh8GllXoN7mr0djNf";
        URL url;
		try {
			url = new URL("https://api.tech.redventures.com.br/");
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		}

        return RestClientBuilder.newBuilder()
        		.baseUrl(url)
        		.register(new CustomHeaderProvider("x-api-key", apiKey))
        		.build(RedVenturesService.class);
    }
    
    public class CustomHeaderProvider implements ClientRequestFilter {
	    private final String name;
	    private final String value;

	    public CustomHeaderProvider(String name, String value) {
	       this.name = name;
	       this.value = value;
	    }

	    @Override
	    public void filter(ClientRequestContext requestContext) throws IOException {
	        requestContext.getHeaders().add(name, value);
	    }
	}
}