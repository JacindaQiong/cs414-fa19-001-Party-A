import com.partyA.bean.*;
import com.partyA.exception.IllegalMoveException;
import com.partyA.exception.IllegalPositionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * User: Nana Yin
 * Date: 10/11/19
 */

import static org.junit.jupiter.api.Assertions.*;

public class GameBoardTest {
    private GameBoard board;
    @BeforeEach
    public void setUp(){
        User black = new User(1,"AAA","abcx","AAA@gamil.com");
        User white = new User(2,"BBB","fgfd","BBB@gamil.com");
        Match match = new Match(black,white);
        GameBoard board = new GameBoard(match);
        board.initialize();
    }

    @Test
    public void testInitialize(){
        System.out.println(board);
    }


    @Test
    public void testMove(){



    }
    @Test
    public void testKillOpponent(){
        Pawn opponent1 = new Pawn(board, Piece.Color.BLACK);
        Pawn same1 = new Pawn(board, Piece.Color.WHITE);
        Pawn p = new Pawn(board,Piece.Color.WHITE);

        board.placePiece(opponent1,"hd");
        board.placePiece(same1,"he");

        board.placePiece(p,"ac");
        try {
            int result = board.move("ac","hc");
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        }

        try {
            assertNull(board.getPiece("hd"));
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testKillOpponentWithCrossBox(){
        Pawn opponent1 = new Pawn(board, Piece.Color.BLACK);
        Pawn p = new Pawn(board,Piece.Color.WHITE);

        board.placePiece(opponent1,"aj");

        board.placePiece(p,"aa");
        try {
            int result = board.move("aa","ai");
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        }

        try {
            assertNull(board.getPiece("aj"));
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testKillOpponentWithCrossBox2(){
        Pawn opponent1 = new Pawn(board, Piece.Color.BLACK);
        Pawn p = new Pawn(board,Piece.Color.WHITE);

        board.placePiece(opponent1,"jk");


        board.placePiece(p,"ii");
        try {
            board.move("ii","ik");
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        }

        try {
            assertNull(board.getPiece("jk"));
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
    }

    //4 black pieces --> kill king
    @Test
    public void testKillKing(){
        Pawn opponent1 = new Pawn(board, Piece.Color.BLACK);
        Pawn opponent2 = new Pawn(board, Piece.Color.BLACK);
        Pawn opponent3 = new Pawn(board, Piece.Color.BLACK);
        Pawn opponent4 = new Pawn(board, Piece.Color.BLACK);

        King king = new King(board, Piece.Color.WHITE);
        board.placePiece(king,"eg");
        board.placePiece(opponent1,"eh");
        board.placePiece(opponent2,"dg");
        board.placePiece(opponent3,"fg");

        board.placePiece(opponent4,"ee");


        // black wins
        try {
            assertEquals(0,board.move("ee","ef"));
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        }
    }

    //3 black pieces + center square --> kill king
    @Test
    public void testKillKing2(){
        Pawn opponent1 = new Pawn(board, Piece.Color.BLACK);
        Pawn opponent2 = new Pawn(board, Piece.Color.BLACK);
        Pawn opponent3 = new Pawn(board, Piece.Color.BLACK);

        King king = new King(board, Piece.Color.WHITE);
        board.placePiece(king,"ef");
        board.placePiece(opponent1,"eg");
        board.placePiece(opponent2,"df");

        board.placePiece(opponent3,"aa");
        try {
            board.move("aa","ee");
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        }


    }

    //3 king wins
    @Test
    public void testKingWin(){
        King king = new King(board, Piece.Color.WHITE);
        board.placePiece(king,"aa");
        int result = 0;
        try {
            result = board.move("aa","ka");
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        }

        // king wins
        assertEquals(1,result);
    }

}
