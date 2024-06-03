
public class Utils {

	public static ErrorLog authenticateKey(Mapper foodMapper, String apiKey) {
		if(apiKey == null) {
			return foodMapper.missingApiKey();
		}
		
		if(!apiKey.equals("12345")) {
			return foodMapper.notAuthorized();
		}
		
		return null;
	}
}
