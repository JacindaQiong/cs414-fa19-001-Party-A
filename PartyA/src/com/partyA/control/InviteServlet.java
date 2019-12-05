package com.partyA.control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/back/sendInvite")
public class InviteServlet extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        String invitee = request.getParameter("invitee");
        String inviter = request.getParameter("inviter");
        //Still needs to start new instance of game and add to invite database
        System.out.println("Inviter: "+ inviter + " Invitee: " +invitee);
        out.write("...");
        out.flush();
        out.close();
    }
}