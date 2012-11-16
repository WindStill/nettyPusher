package com.four.dake.test;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.four.dake.db.DBHelp;
import com.four.dake.entity.User;

import net.sf.json.JSONObject;

public class Test {

	public static void main(String[] args) {
		Connection con = DBHelp.getConn();
		System.out.println(con.toString());
	}
}
