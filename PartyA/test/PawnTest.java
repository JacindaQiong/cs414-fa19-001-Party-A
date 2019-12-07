import com.partyA.bean.*;
import com.partyA.exception.IllegalPositionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * User: Nana Yin
 * Date: 10/11/19
 */
public class PawnTest {

    private GameBoard board;
    private Pawn pawn;
    private String position="fb";

    @BeforeEach
    public void setUp(){
//        User black = new User(1,"AAA","abcx","AAA@gamil.com");
//        User white = new User(2,"BBB","fgfd","BBB@gamil.com");
        Match match = new Match(1,2);
        GameBoard board = new GameBoard(match);

        pawn=new Pawn(board, Piece.Color.WHITE);
        board.placePiece(pawn,position);

        Pawn p1 = new Pawn(board, Piece.Color.BLACK);
        board.placePiece(p1,"kb");

        Pawn p2 = new Pawn(board, Piece.Color.BLACK);
        board.placePiece(p2,"fh");

        Pawn p3 = new Pawn(board, Piece.Color.BLACK);
        board.placePiece(p3,"db");
    }

    @Test
    public void testGetColor(){
        assertEquals(Piece.Color.WHITE, pawn.getColor(),"pawn getColor() failed !!");
    }

    @Test
    public void testGetPosition(){
        assertEquals(position, pawn.getPosition(),"pawn getPosition() failed!!");
    }

    @Test
    public void testForIllegalPosition(){
        assertThrows(IllegalPositionException.class,()->pawn.setPosition("h^9"),"IllegalPositionException should be expected!!");
    }

    @Test
    public void testLegalMoves(){
        ArrayList<String> legalMoves = new ArrayList<>();
        legalMoves.add("fa");

        legalMoves.add("fc");
        legalMoves.add("fd");
        legalMoves.add("fe");
        legalMoves.add("fg");

        legalMoves.add("eb");

        legalMoves.add("gb");
        legalMoves.add("hb");
        legalMoves.add("ib");
        legalMoves.add("jb");

        assertTrue(legalMoves.containsAll(pawn.legalMoves())&&pawn.legalMoves().containsAll(legalMoves),"actual result should be equal with expected result.");
    }


}