import com.partyA.bean.GameBoard;
import com.partyA.bean.Pawn;
import com.partyA.bean.Piece;
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
    private String position="c2";

    @BeforeEach
    public void setUp(){
         board = new GameBoard();
        pawn=new Pawn(board, Piece.Color.WHITE);
        board.placePiece(pawn,position);
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
    public void testSetPositionForNull(){
        assertThrows(IllegalPositionException.class,()->pawn.setPosition(null),"IllegalPositionException should be expected!!");
    }

    @Test
    public void testSetPositionForEmpty(){
        assertThrows(IllegalPositionException.class,()->pawn.setPosition(""),"IllegalPositionException should be expected!!");
    }

    @Test
    public void testForIllegalPosition(){
        assertThrows(IllegalPositionException.class,()->pawn.setPosition("h^9"),"IllegalPositionException should be expected!!");
    }

}