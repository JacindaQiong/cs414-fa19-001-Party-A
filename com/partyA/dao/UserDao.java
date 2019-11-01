package com.partyA.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

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

}
