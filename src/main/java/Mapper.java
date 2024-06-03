import java.math.BigDecimal;
import java.util.ArrayList;

import pojo.Broth;
import pojo.ErrorResponse;
import pojo.OrderResponse;
import pojo.Protein;
import pojo.RedVenturesOrder;

public class Mapper {
	
	public ArrayList<Broth> allBroths(){
		ArrayList<Broth> brothList = new ArrayList<>();
		var broth1 = new Broth();
		
		broth1.setId("1");
		broth1.setImageInactive("https://tech.redventures.com.br/icons/salt/inactive.svg");
		broth1.setImageActive("https://tech.redventures.com.br/icons/salt/active.svg");
		broth1.setName("Salt");
		broth1.setDescription("Simple like the seawater, nothing more.");
		broth1.setPrice(new BigDecimal("10"));
		
		brothList.add(broth1);
		
		return brothList;
	}

	public ArrayList<Protein> allProteins(){
		ArrayList<Protein> proteinList = new ArrayList<>();
		var protein1 = new Protein();
		
		protein1.setId("1");
		protein1.setImageInactive("https://tech.redventures.com.br/icons/pork/inactive.svg");
		protein1.setImageActive("https://tech.redventures.com.br/icons/pork/active.svg");
		protein1.setName("Chasu");
		protein1.setDescription("A sliced flavourful pork meat with a selection of season vegetables.");
		protein1.setPrice(new BigDecimal("10"));
		
		proteinList.add(protein1);
		
		return proteinList;
	}
	
	public OrderResponse postOrder(RedVenturesOrder order) {
		var orderResponse = new OrderResponse();
		
		orderResponse.setId(order.getOrderId());
		orderResponse.setDescription("Salt and Chasu Ramen");
		orderResponse.setImage("https://tech.redventures.com.br/icons/ramen/ramenChasu.png");
		
		return orderResponse;
	}
	
	public ErrorResponse mapErrorLog(String message) {
		var error = new ErrorResponse();
		
		error.setError(message);
		
		return error;
	}

	public ErrorResponse notAuthorized() {
		var error = new ErrorResponse();
		
		error.setError("Unauthorized opperation.");
		
		return error;
	}
}
