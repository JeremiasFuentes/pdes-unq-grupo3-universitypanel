package pdes.c1.universitypanel.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseBody {
	
	public static Map<String, String> create(String content) {
		Map<String, String> body = new HashMap<String, String>();
		body.put("data", content);
		
		return body;
	}
}
