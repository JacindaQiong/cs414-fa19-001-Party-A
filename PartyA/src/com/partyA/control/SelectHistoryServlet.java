package com.partyA.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.partyA.bean.User;
import com.partyA.service.MatchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/selectHistory")
public class SelectHistoryServlet extends HttpServlet {
    private MatchService matchService = new MatchService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page=request.getParameter("page");
        String rows=request.getParameter("rows");
        PrintWriter out=response.getWriter();
        ObjectMapper mapper=new ObjectMapper();
        User user = (User) request.getSession().getAttribute("userInfo");
        if(user != null){
            Map<String,Object> map=matchService.searchMatch(user.getId(),Integer.parseInt(page), Integer.parseInt(rows));
            String result = mapper.writeValueAsString(map);
            out.print(result);
            out.flush();
        }
    }
}
