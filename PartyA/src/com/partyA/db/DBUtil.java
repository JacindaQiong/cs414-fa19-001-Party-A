package com.partyA.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;



public class DBUtil {
	private Connection conn;
	private PreparedStatement prep;

	public  DBUtil(){
		Properties pp=new Properties();
		try {
			pp.load(DBUtil.class.getResourceAsStream("db.properties"));
			Class.forName(pp.getProperty("driver"));
			System.out.println("222222");
			conn=DriverManager.getConnection(pp.getProperty("url"),
					pp.getProperty("user"),
					pp.getProperty("pass"));
			System.out.println("11111");
		} catch (IOException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public int update(String sql){

		 int temp=-1;		
		try {
			prep=conn.prepareStatement(sql);

			temp=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	   }
		return temp;
	}	

	public int update(String sql,Object ... arr){
		  int temp=-1;	
		 try {
			prep=conn.prepareStatement(sql);
			for(int i=0;i<arr.length;i++){
				prep.setObject(i+1, arr[i]);
			}
			temp=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return temp;
	}
	

	public ResultSet query(String sql){
		ResultSet rs=null;	
		try {
			prep=conn.prepareStatement(sql);
			rs=prep.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}


	public ResultSet query(String sql,Object ... arr){
		  ResultSet rs=null;		
		  try {
			prep=conn.prepareStatement(sql);
			for(int i=0;i<arr.length;i++){
				prep.setObject(i+1, arr[i]);
			}
			rs=prep.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return rs;}
	
	

	public void close(){
		try {
			if(prep!=null ){
				prep.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
