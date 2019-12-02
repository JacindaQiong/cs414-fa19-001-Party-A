package com.partyA.control.gameboard;

import com.partyA.bean.*;
import com.partyA.service.UserService;
//import org.json.JSONArray;
//import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Nana Yin
 * Date: 11/24/19
 */
@WebServlet("/initialGame")
public class GameServlet extends HttpServlet {
    private GameBoard game;

	private static final long serialVersionUID = 1L;

    public GameServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User black = new User(1,"AAA","abcx","AAA@gamil.com");
        User white = new User(2,"BBB","fgfd","BBB@gamil.com");
        Match match = new Match(black,white);
        GameBoard board = new GameBoard(match);
        board.initialize();
        HttpSession session=request.getSession();
        Piece[][] array = board.getBoardArray();
        session.setAttribute("boardArray", array);
Map<String, String> map = new HashMap<String, String>();
        for(int i=0; i<11; i++){
            for(int j=0; j<11; j++){
                Piece p = array[i][j];
                String val = "0";
                if(p!=null){
                    if((p instanceof  Pawn)&& p.getColor().equals(Piece.Color.WHITE))
                        val = "1";
                    if(p instanceof King)
                        val = "2";
                    map.put(val,i+","+j);
                }
            }

        }
        response.setContentType("text/html");
        // Writing the message on the web page
        PrintWriter out = response.getWriter();
        String str = board.getBoardArray().toString();
//        JSONObject jsonObj=new JSONObject(map);
//        out.println(jsonObj.toString());


    }

}