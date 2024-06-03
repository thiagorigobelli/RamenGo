import pojo.ErrorLog;

public class Utils {

	public static ErrorLog authenticateKey(Mapper foodMapper, String apiKey) {
		if(apiKey == null) {
			return foodMapper.mapErrorLog("x-api-key header missing.");
		}
		
		if(!apiKey.equals("8xl7jqfafzgbevl")) {
			return foodMapper.notAuthorized();
		}
		
		return null;
	}
}
