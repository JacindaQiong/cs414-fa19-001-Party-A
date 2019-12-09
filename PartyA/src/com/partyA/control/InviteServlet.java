package com.partyA.control;

import com.partyA.bean.Invitation;
import com.partyA.service.InvitationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/back/sendInvite")
public class InviteServlet extends HttpServlet{
    private InvitationService invitationService=new InvitationService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        String invitee = request.getParameter("invitee");
        String inviter = request.getParameter("inviter");
        String invitee_id=request.getParameter("invitee_id");
        String inviter_id=request.getParameter("inviter_id");
        Invitation invitation=new Invitation();
        invitation.setInviter(inviter);
        invitation.setInvitee(invitee);
        invitation.setInviterID(inviter_id);
        invitation.setInviteeID(invitee_id);
        invitation.setTime(Long.toString(System.currentTimeMillis()));
        boolean flag=invitationService.addInvitation(invitation);
        response.sendRedirect("index.jsp");
        out.print(flag);
        out.flush();
    }
}