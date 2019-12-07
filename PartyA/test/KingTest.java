import com.partyA.bean.*;
import com.partyA.exception.IllegalPositionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * User: Nana Yin
 * Date: 10/11/19
 */
public class KingTest {

    private GameBoard board;
    private King king;
    private String position="ff";

    @BeforeEach
    public void setUp(){
        Match match = new Match(1,2);
        GameBoard board = new GameBoard(match);

        king=new King(board, Piece.Color.BLACK);
        board.placePiece(king,position);

        Pawn p1 = new Pawn(board, Piece.Color.BLACK);
        board.placePiece(p1,"fk");

        Pawn p2 = new Pawn(board, Piece.Color.BLACK);
        board.placePiece(p2,"fc");

        Pawn p3 = new Pawn(board, Piece.Color.BLACK);
        board.placePiece(p3,"df");

        Pawn p4 = new Pawn(board, Piece.Color.BLACK);
        board.placePiece(p1,"jf");

        System.out.println(board.toString());
    }

    @Test
    public void testGetColor(){
        assertEquals(Piece.Color.BLACK, king.getColor(),"king getColor() failed !!");
    }

    @Test
    public void testGetPosition(){
        assertEquals(position, king.getPosition(),"king getPosition() failed!!");
    }

    @Test
    public void testSetIllegalPosition(){
        assertThrows(IllegalPositionException.class,()->king.setPosition("h^9"),"IllegalPositionException should be expected!!");
    }

    @Test
    public void testLegalMoves(){
        ArrayList<String> legalMoves = new ArrayList<>();
        legalMoves.add("fd");
        legalMoves.add("fe");

        legalMoves.add("fg");
        legalMoves.add("fh");
        legalMoves.add("fi");
        legalMoves.add("fj");

        legalMoves.add("ef");
        legalMoves.add("gf");
        legalMoves.add("hf");
        legalMoves.add("if");


        assertTrue(legalMoves.containsAll(king.legalMoves())&&king.legalMoves().containsAll(legalMoves),"actual result should be equal with expected result.");
    }

}