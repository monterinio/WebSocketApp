package pl.pwr.login;

import java.util.HashMap;
import java.util.Map;
import pl.pwr.util.Strings;

public final class RedirectData {
	
	private RedirectData() {}
	
	public static Map<String,String> createConnectionRedirects() {
		Map<String, String> redirects = new HashMap<>();
		for(int clientID = 1; clientID <= Strings.NO_OF_CLIENTS; clientID++) {
			StringBuilder builder = new StringBuilder();
			builder.append("app").append(clientID).append("/apk-").append(clientID).append("-index.html");
			redirects.put("client" + clientID, builder.toString());
		}
		return redirects;
	}
	
}
