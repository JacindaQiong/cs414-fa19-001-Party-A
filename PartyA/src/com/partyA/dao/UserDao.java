package com.partyA.dao;

import java.sql.*;

import com.partyA.bean.User;
import com.partyA.db.DBUtil;


public class UserDao {
	public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver" ;
	public static final String DBURL = "jdbc:mysql://localhost:3306/mysql?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC" ;
	public static final String DBUSER = "root" ;
	public static final String DBPASS = "Xiankesong198912" ;

	public User searchByNameAndPass(String name, String pass) {
		String sql="select u.user_id, user_name, u.user_password, u.user_email from game_user u where u.user_name=? and u.user_password=? ";//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			Class.forName(DBDRIVER);
			conn= DriverManager.getConnection(DBURL, DBUSER, DBPASS);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,name);
			pstmt.setString(2,pass);

			rs = pstmt.executeQuery();
			while(rs.next()){
				int id = rs.getInt(1);
				String userName = rs.getString(2);
				String password = rs.getString(3);
				String email = rs.getString(4);
				User user=new User(id, userName, password,email);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally{
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}


		return null;
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
