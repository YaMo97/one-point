package com.aicte.opvs.helpers;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONReply {

	public static JSONObject generateReplyJSON(int errorCode, String status, JSONArray data, String message) {
		
		JSONObject replyJson = new JSONObject();
		
		replyJson.put("errorCode", errorCode);
		replyJson.put("status", status);
		replyJson.put("data", data);
		replyJson.put("message", message);
        
		return replyJson;
	}
	
	public static JSONObject formToJSON(String str) {
		
		JSONObject json = new JSONObject();
		
////		public static String paramJson(String paramIn) {
//	    str = srtr.replaceAll("=", "\":\"");
//	    paramIn = paramIn.replaceAll("&", "\",\"");
//	    return "{\"" + paramIn + "\"}";
		return json;
	}
	
	public static Map<String, List<String>> splitQuery(URL url) throws UnsupportedEncodingException {
		
		  final Map<String, List<String>> query_pairs = new LinkedHashMap<String, List<String>>();
		  final String[] pairs = url.getQuery().split("&");
		  for (String pair : pairs) {
		    final int idx = pair.indexOf("=");
		    final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
		    if (!query_pairs.containsKey(key)) {
		      query_pairs.put(key, new LinkedList<String>());
		    }
		    final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
		    query_pairs.get(key).add(value);
		  }
		  return query_pairs;
		}
}
