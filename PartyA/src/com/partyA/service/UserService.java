package com.partyA.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
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

	public String searchUser(int page,int show){
		UserDao dao=new UserDao();
		List<User> list=dao.searchAll(page, show);
		int number=dao.searchCount();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("page", page);
		map.put("rows", list);
		map.put("total", number);
		ObjectMapper mapper=new ObjectMapper();
		String temp=null;

		try{
			temp=mapper.writeValueAsString(map);
		}catch(IOException e){
			e.printStackTrace();
		}
		return temp;
	}
}
