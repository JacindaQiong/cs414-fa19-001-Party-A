package com.partyA.service;

import com.partyA.bean.User;
import com.partyA.dao.UserDao;

public class UserService {

	public User login(String name, String pass) {
		UserDao dao=new UserDao();
		User user=dao.searchByNameAndPass(name,pass);
		return user;
	}
  public boolean addUser(User user){
	  UserDao dao=new UserDao();
	  int temp=dao.add(user);
	  if(temp>0){
		  return true;
	  }else{
		  return false;
	  }
  }
}
