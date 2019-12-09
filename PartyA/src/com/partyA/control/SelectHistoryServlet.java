package com.partyA.control;

import com.partyA.service.MatchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/selectHistory")
public class SelectHistoryServlet extends HttpServlet {
    private MatchService matchService = new MatchService();
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page=request.getParameter("page");
        String rows=request.getParameter("rows");
        PrintWriter out=response.getWriter();
        MatchService service =new MatchService();
        String result=matchService.searchMatch(Integer.parseInt(page), Integer.parseInt(rows));
        out.print(result);
        out.flush();
    }
}
