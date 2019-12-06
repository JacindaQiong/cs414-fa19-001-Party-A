package com.partyA.control;

import com.partyA.bean.Invitation;
import com.partyA.service.InvitationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/back/selectInvitation")
public class SelectInviationServlet {
    private static final long serialVersionUID = 1L;

    public SelectInviationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page=request.getParameter("page");
        String rows=request.getParameter("rows");
        PrintWriter out=response.getWriter();
        InvitationService service =new InvitationService();
        String temp=service.searchInvitation(Integer.parseInt(page), Integer.parseInt(rows));
        out.print(temp);
        out.flush();
    }
}
