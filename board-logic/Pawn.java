import java.util.ArrayList;

public class Pawn implements Piece {
    //public enum Color{WHITE, BLACK};
    protected String color;
    GameBoard board;
   int column;
   int row;

    //Constructor
    public Pawn(GameBoard board, String color){
        this.board = board;
        this.color = color;
    }
    public String getColor(){
        return this.color;
    }
    public String getPosition(){
        String position = "";
        position += board.toSIndex(column);
        position += board.toSIndex(row);
        return position;
    }
    public boolean setPosition(String position){
        if(pawnValidMove(getPosition(),position)){
            this.column = board.toNIndex(position.charAt(0));
            this.row = board.toNIndex(position.charAt(1));
            return true;
        }
        else{
            return false;
        }
    }
    public boolean pawnValidMove(String fromPosition, String toPosition){
        if(!board.validMove(fromPosition, toPosition)){
            return false;
        }
        if(toPosition == "AA" || toPosition == "AK" ||toPosition == "KA" || toPosition == "KK" || toPosition == "FF"){
            return false;
        }
        return true;
    }

    //Doesn't check validity; only used to initialize board
    public void initialLocation(String position){
        this.column = board.toNIndex(position.charAt(0));
        this.row = board.toNIndex(position.charAt(1));
    }

    public ArrayList<String> legalMoves(){
        return null;
    }

    //For implementation/testing purposes
    public String toString(){
        return "pawn";
    }
}
