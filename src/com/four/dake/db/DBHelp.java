package com.four.dake.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBHelp {
	
	//��ȡ���ݿ�����
	public static Connection getConn(){
		Connection conn = null;
		Properties p = new Properties();
		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "config.properties";
		InputStream is;
		try {
			is = new FileInputStream(path);
			p.load(is);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Class.forName(p.getProperty("driverClass"));
			conn = DriverManager.getConnection(p.getProperty("url"), p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	//�ر����ݿ�����
	public static void closeObject(Object o){
		if(o!=null){
			//�жϵ�ǰ��������������
			if(o instanceof Connection){
				try {
					((Connection)o).close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(o instanceof Statement){
				try {
					((Statement)o).close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(o instanceof PreparedStatement){
				try {
					((PreparedStatement)o).close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(o instanceof ResultSet){
				try {
					((ResultSet)o).close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
