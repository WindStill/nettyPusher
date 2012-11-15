package com.four.dake.test;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.four.dake.entity.User;

import net.sf.json.JSONObject;

public class Test {

	public static void main(String[] args) {
//		String str = "aaaaa";
//		String s = str.replaceFirst(""+str.charAt(0), ""+(char)(str.charAt(0)-32));
//		System.out.print(s);
		
		JSONObject json = new JSONObject();
		
		Map<String, String> userMap = new HashMap<String, String>();
		userMap.put("id", "2");
		userMap.put("name", "yangkit");
		json.put("user", userMap);
		JSONObject userJson = json.getJSONObject("user");
		String id = userJson.getString("id");
		userJson.remove("id");
		System.out.println(id);
		System.out.println(userJson);
//		String str = user.toString();
//		System.out.println(str);
	}
}
