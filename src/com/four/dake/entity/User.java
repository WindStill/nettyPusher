package com.four.dake.entity;

import java.lang.reflect.Method;
import java.util.Iterator;

import net.sf.json.JSONObject;

public class User {
	private String id;
	
	private String name;
	
	private String nickname;

	private String avatar;
	
	public User(JSONObject json){
		Iterator<String> keys = json.keys();
		while(keys.hasNext()){
			String key = keys.next();
			try {
				String methodName = "set" + key.replaceFirst(""+key.charAt(0), ""+(char)(key.charAt(0)-32));
				Method method = this.getClass().getMethod(methodName, String.class);
				method.invoke(this, json.get(key));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
