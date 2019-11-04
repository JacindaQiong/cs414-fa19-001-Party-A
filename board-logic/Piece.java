import java.util.ArrayList;
public interface Piece {

    //GameBoard board;
    //String color;
    //int row;
    //int column;

    String getColor();
    String getPosition();
    boolean setPosition(String position);
    void initialLocation(String position);
    ArrayList<String> legalMoves();


}
