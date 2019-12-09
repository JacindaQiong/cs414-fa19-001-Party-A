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
	private static final long serialVersionUID = 1L;
	private UserService userService=new UserService();


    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      User user=new User();
      user.setName(request.getParameter("uname"));
      user.setPass(request.getParameter("upass"));
      user.setEmail(request.getParameter("uemail"));

      boolean flag=userService.addUser(user);
      PrintWriter out=response.getWriter();
      response.sendRedirect("http://localhost:8080/PartyA_war_exploded/login.jsp");
      out.print(flag);
      out.flush();  
	}

}
