import com.partyA.bean.GameBoard;
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


}
