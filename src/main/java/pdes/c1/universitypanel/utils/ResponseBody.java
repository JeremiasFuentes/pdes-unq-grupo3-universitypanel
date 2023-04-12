package pdes.c1.universitypanel.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseBody {
	
	public static Map<String, Object> create(Object content) {
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("data", content);
		
		return body;
	}
}
