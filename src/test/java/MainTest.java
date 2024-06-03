import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class MainTest {

	@Test    
    public void testBrothEndpointNoApiKey() {
		given()
          .when().get("/api/v1/broths")
          .then()
          .statusCode(403)    
          .body("error", is("x-api-key header missing."));
    }
	
	@Test    
    public void testBrothEndpointWrongApiKey() {
		given().header("x-api-key", "78987")
          .when().get("/api/v1/broths")
          .then()
          .statusCode(403)    
          .body("error", is("Unauthorized opperation."));
    }
	
	@Test    
    public void testBrothEndpointCorrectApiKey() {
		given().header("x-api-key", "8xl7jqfafzgbevl")
          .when().get("/api/v1/broths")
          .then()
             .statusCode(200)    
             .body("id", hasItem("1"),
            		 "imageInactive", hasItem("https://tech.redventures.com.br/icons/salt/inactive.svg"),
            		 "imageActive", hasItem("https://tech.redventures.com.br/icons/salt/active.svg"),
            		 "name", hasItem("Salt"),
            		 "description", hasItem("Simple like the seawater, nothing more."),
            		 "price", hasItem(10));
    }
	
	@Test    
    public void testProteinEndpointNoApiKey() {
		given()
          .when().get("/api/v1/proteins")
          .then()
          .statusCode(403)    
          .body("error", is("x-api-key header missing."));
    }
	
	@Test    
    public void testProteinEndpointWrongApiKey() {
		given()
		  .header("x-api-key", "78987")
          .when().get("/api/v1/proteins")
          .then()
          .statusCode(403)    
          .body("error", is("Unauthorized opperation."));
    }
	
	@Test    
    public void testProteinEndpointCorrectApiKey() {
		given().header("x-api-key", "8xl7jqfafzgbevl")
          .when().get("/api/v1/proteins")
          .then()
             .statusCode(200)    
             .body("id", hasItem("1"),
            		 "imageInactive", hasItem("https://tech.redventures.com.br/icons/pork/inactive.svg"),
            		 "imageActive", hasItem("https://tech.redventures.com.br/icons/pork/active.svg"),
            		 "name", hasItem("Chasu"),
            		 "description", hasItem("A sliced flavourful pork meat with a selection of season vegetables."),
            		 "price", hasItem(10));
    }
	
	@Test    
    public void testOrderEndpointNoApiKey() {
		given()
		  .header("Content-type", "application/json")
		  .and()
		  .body("{\"brothId\": \"1\",\"proteinId\": \"1\"}")
	      .when().post("/api/v1/orders")
	      .then()
	      .statusCode(403)    
	      .body("error", is("x-api-key header missing."));
    }
	
	@Test    
    public void testOrderEndpointWrongApiKey() {
		given()
		  .header("Content-type", "application/json")
		  .and()
		  .header("x-api-key", "78987")
		  .and()
          .when().post("/api/v1/orders")
          .then()
          .statusCode(403)    
          .body("error", is("Unauthorized opperation."));
    }
}
