
public class Utils {

	public static ErrorLog authenticateKey(Mapper foodMapper, String apiKey) {
		if(apiKey == null) {
			return foodMapper.missingApiKey();
		}
		
		if(!apiKey.equals("8xl7jqfafzgbevl")) {
			return foodMapper.notAuthorized();
		}
		
		return null;
	}
}
