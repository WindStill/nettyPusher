package com.four.dake.db;

import java.sql.*;
import java.util.*;


public class CommonDao {
	//������ݿ���²����ķ���
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

    //������ݿ�ͨ�õĲ�ѯ������¼
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
            
            //����rs�������õ�������ľ�����Ϣ
            ResultSetMetaData rsmd = rs.getMetaData();
            //�õ����ж�����
            int columnCount = rsmd.getColumnCount();
            //��ȡ����������������������
            String[] columnnames = new String[columnCount];
            for(int i=0; i<columnnames.length;i++){
            	columnnames[i] = rsmd.getColumnName(i+1);
            }
            
            //�����ݷ�װ����������
            while(rs.next()){
            	//��װһ����¼
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
    
    //������ݿ�ͨ�õĲ�ѯһ������
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
            
            //����rs�������õ�������ľ�����Ϣ
            ResultSetMetaData rsmd = rs.getMetaData();
            //�õ����ж�����
            int columnCount = rsmd.getColumnCount();
            //��ȡ����������������������
            String[] columnnames = new String[columnCount];
            for(int i=0; i<columnnames.length;i++){
            	columnnames[i] = rsmd.getColumnName(i+1);
            }
            
            //�����ݷ�װ����������
            if(rs.next()){
            	//��װһ����¼
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