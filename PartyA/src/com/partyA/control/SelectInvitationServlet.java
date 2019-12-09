package com.partyA.control;

import com.partyA.service.InvitationService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/back/selectInvitation")
public class SelectInvitationServlet extends HttpServlet {

    private InvitationService invitationService =new InvitationService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page=request.getParameter("page");
        String rows=request.getParameter("rows");
        String currentUser = request.getParameter("current");
        String invitee_id=request.getParameter("invitee_id");
        String inviter_id=request.getParameter("inviter_id");
        PrintWriter out=response.getWriter();
        String temp=invitationService.searchInvitation(Integer.parseInt(page), Integer.parseInt(rows), currentUser);
        out.print(temp);
        out.flush();
    }
}
