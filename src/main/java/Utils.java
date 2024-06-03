import pojo.ErrorResponse;

public class Utils {

	public static ErrorResponse authenticateKey(Mapper foodMapper, String apiKey) {
		if(apiKey == null) {
			return foodMapper.mapErrorLog("x-api-key header missing.");
		}
		
		if(!apiKey.equals("8xl7jqfafzgbevl")) {
			return foodMapper.notAuthorized();
		}
		
		return null;
	}
}
