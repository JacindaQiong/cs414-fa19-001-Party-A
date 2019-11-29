package com.partyA.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.partyA.bean.User;
import com.partyA.db.DBUtil;


public class UserDao {

	public User searchByNameAndPass(String name, String pass) {
		User user=null;
		String sql="select * from game_user where user_name=? and user_password=?";
		DBUtil db=new DBUtil();
		ResultSet rs=db.query(sql,name,pass);
		
		try {
			if(rs.next()){
				user=new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return user;
	}
public int add(User user){
	int temp=-1;
	String sql="insert into game_user(user_name,user_password,user_email)values(?,?,?)";
	DBUtil db=new DBUtil();
	temp=db.update(sql,user.getName(),user.getPass(),user.getEmail());
	db.close();
	return temp;
}
	public List<User> searchAll(int page, int show){
		List<User> list=new ArrayList<User>();
		String sql="select * from game_user LIMIT ?,?";
		DBUtil db=new DBUtil();
		ResultSet rs=db.query(sql,(page-1)*show,show);
		try {
			while(rs.next()){
				list.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.close();
		return list;
	}
	public int searchCount() {
		int temp=0;
		String sql="select count(*) from game_user";
		DBUtil db=new DBUtil();
		ResultSet rs=db.query(sql);
		try {
			if(rs.next()){
				temp=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.close();
		return temp;
	}
}
