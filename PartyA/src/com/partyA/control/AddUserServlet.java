package com.partyA.control;

import com.partyA.bean.User;
import com.partyA.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
	private UserService userService=new UserService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      User user=new User();
      user.setName(request.getParameter("uname"));
      user.setPass(request.getParameter("upass"));
      user.setEmail(request.getParameter("uemail"));

      boolean flag=userService.addUser(user);
      PrintWriter out=response.getWriter();
      String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
      System.out.println("**********baseUrl:"+baseUrl);
      String url = baseUrl+"login.jsp";
      response.sendRedirect(url);
      out.print(flag);
      out.flush();  
	}

}
