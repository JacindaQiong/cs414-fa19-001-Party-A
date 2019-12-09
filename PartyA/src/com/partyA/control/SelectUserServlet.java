package com.partyA.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.partyA.service.UserService;


/**
 * Servlet implementation class SelectUserServlet
 */
@WebServlet("/back/selectUser")
public class SelectUserServlet extends HttpServlet {

	private UserService userService =new UserService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		PrintWriter out=response.getWriter();
		String temp=userService.searchUser(Integer.parseInt(page), Integer.parseInt(rows));
	    out.print(temp);
	    out.flush();
	}

}
