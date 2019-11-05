import com.partyA.bean.GameBoard;
import com.partyA.bean.King;
import com.partyA.bean.Piece;
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
        board = new GameBoard();
        king=new King(board, Piece.Color.BLACK);
        board.placePiece(king,position);
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
    public void testSetPositionForNull(){
        assertThrows(IllegalPositionException.class,()->king.setPosition(null),"IllegalPositionException should be expected!!");
    }

    @Test
    public void testSetPositionForEmpty(){
        assertThrows(IllegalPositionException.class,()->king.setPosition(""),"IllegalPositionException should be expected!!");
    }

    @Test
    public void testSetIllegalPosition(){
        assertThrows(IllegalPositionException.class,()->king.setPosition("h^9"),"IllegalPositionException should be expected!!");
    }

    @Test
    public void testLegalMovesForEmptySquares(){
        //king is in the middle of the board, it has 8 choices.
        ArrayList<String> legalMoves = new ArrayList<>();


        assertTrue(legalMoves.containsAll(king.legalMoves())&&king.legalMoves().containsAll(legalMoves),"actual result should be equal with expected result.");
    }

}