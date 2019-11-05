import com.partyA.bean.GameBoard;
import com.partyA.bean.King;
import com.partyA.bean.Pawn;
import com.partyA.bean.Piece;
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
        board = new GameBoard();
        board.initialize();
    }

    @Test
    public void testInitialize(){
        System.out.println(board);
    }

    @Test
    public void testKillOpponent(){
        Pawn opponent1 = new Pawn(board, Piece.Color.BLACK);
        Pawn same1 = new Pawn(board, Piece.Color.WHITE);
        Pawn p = new Pawn(board,Piece.Color.WHITE);

        board.placePiece(opponent1,"hd");
        board.placePiece(same1,"he");

        board.placePiece(p,"hc");
        board.killOpponent("hc");

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

        board.placePiece(p,"ai");
        board.killOpponent("ai");

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

        board.placePiece(p,"ik");
        board.killOpponent("ik");

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

        board.placePiece(opponent4,"ef");
        board.killOpponent("ef");

        // black wins
        assertEquals(0,board.killOpponent("ef"));
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

        board.placePiece(opponent3,"ee");
        board.killOpponent("ee");

        // black wins
        assertEquals(0,board.killOpponent("ee"));
    }

    //3 king wins
    @Test
    public void testKingWin(){
        King king = new King(board, Piece.Color.WHITE);
        board.placePiece(king,"ka");

        // king wins
        assertEquals(1,board.killOpponent("ka"));
    }

}
