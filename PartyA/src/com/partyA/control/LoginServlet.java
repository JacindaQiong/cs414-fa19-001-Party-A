package com.partyA.control;

import com.partyA.bean.User;
import com.partyA.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private UserService userService =new UserService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("uname");
		String pass=request.getParameter("upass");

		User user=userService.login(name,pass);

		if(user==null){
			response.sendRedirect("login.jsp?msg=0");
		}else{
			HttpSession session=request.getSession();
			session.setAttribute("userInfo",user);
			session.setAttribute("name",user.getName());
			response.sendRedirect("index.jsp");
//			response.sendRedirect("back/index.jsp");
		}
	}

}