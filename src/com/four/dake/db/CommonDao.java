package com.four.dake.db;

import java.sql.*;
import java.util.*;


public class CommonDao {
	//完成数据库更新操作的方法
    public static boolean updateSQL(String sql,String... args){
    	boolean flag = false;
    	Connection conn = null;
    	PreparedStatement pst = null;
    	try{
    		conn = DBHelp.getConn();
    		pst = conn.prepareStatement(sql);
    		if(args!=null){
    			for(int i=0; i<args.length; i++){
    				pst.setString(i+1, args[i]);
    			}
    		}
    		if(pst.executeUpdate()>0){
    			flag = true;
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		DBHelp.closeObject(pst);
    		DBHelp.closeObject(conn);
    	}
    	return flag;
    }

    //完成数据库通用的查询多数记录
    public static List<Map<String,String>> getResult(String sql,String... args){
    	List<Map<String,String>> list = new ArrayList<Map<String,String>>();
    	Connection conn = null;
    	PreparedStatement pst = null;
    	ResultSet rs = null;
    	try{
    		conn = DBHelp.getConn();
    		pst = conn.prepareStatement(sql);
    		if(args!=null){
    			for(int i=0; i<args.length; i++){
    				pst.setString(i+1, args[i]);
    			}
    		}
            rs = pst.executeQuery();
            
            //根据rs对象来得到表里面的具体信息
            ResultSetMetaData rsmd = rs.getMetaData();
            //得到表有多少列
            int columnCount = rsmd.getColumnCount();
            //获取列名，并保存在数组里面
            String[] columnnames = new String[columnCount];
            for(int i=0; i<columnnames.length;i++){
            	columnnames[i] = rsmd.getColumnName(i+1);
            }
            
            //把数据封装到集合里面
            while(rs.next()){
            	//封装一条记录
            	Map<String,String> map = new HashMap<String,String>();
                for(int i=0; i<columnnames.length;i++){
                	map.put(columnnames[i], rs.getString(i+1));
                }
                list.add(map);
            }
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		DBHelp.closeObject(rs);
    		DBHelp.closeObject(pst);
    		DBHelp.closeObject(conn);
    	}
    	return list;
    }
    
    //完成数据库通用的查询一个对象
    public static Map<String,String> getOneResult(String sql,String... args){
    	Map<String,String> map = new HashMap<String,String>();
    	Connection conn = null;
    	PreparedStatement pst = null;
    	ResultSet rs = null;
    	try{
    		conn = DBHelp.getConn();
    		pst = conn.prepareStatement(sql);
    		if(args!=null){
    			for(int i=0; i<args.length; i++){
    				pst.setString(i+1, args[i]);
    			}
    		}
            rs = pst.executeQuery();
            
            //根据rs对象来得到表里面的具体信息
            ResultSetMetaData rsmd = rs.getMetaData();
            //得到表有多少列
            int columnCount = rsmd.getColumnCount();
            //获取列名，并保存在数组里面
            String[] columnnames = new String[columnCount];
            for(int i=0; i<columnnames.length;i++){
            	columnnames[i] = rsmd.getColumnName(i+1);
            }
            
            //把数据封装到集合里面
            if(rs.next()){
            	//封装一条记录
                for(int i=0; i<columnnames.length;i++){
                	map.put(columnnames[i], rs.getString(i+1));
                }
            }
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		DBHelp.closeObject(rs);
    		DBHelp.closeObject(pst);
    		DBHelp.closeObject(conn);
    	}
    	return map;
    }
}