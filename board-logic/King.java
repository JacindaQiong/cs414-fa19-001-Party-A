import java.util.ArrayList;
public class King implements Piece {
    GameBoard board;
    protected String color;
    int row;
    int column;


    //Constructor
    public King(GameBoard board, String color){
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
        if(board.validMove(getPosition(), position)) {
            this.column = board.toNIndex(position.charAt(0));
            this.row = board.toNIndex(position.charAt(1));
            return true;
        }
        else{
            return false;
        }
    }
    public ArrayList<String> legalMoves(){
        ArrayList<String> legal = new ArrayList<>();
        for(int i =0; i<10; i++){
            for(int j=0; j<10; j++){
                if(board.validMove(getPosition(), ""+board.toSIndex(i) +board.toSIndex(j))){
                    legal.add( ""+board.toSIndex(i) +board.toSIndex(j));
                }

            }
        }
        return legal;
    }

    public void initialLocation(String position){
        this.column = board.toNIndex(position.charAt(0));
        this.row = board.toNIndex(position.charAt(1));
    }

    //For implementation/testing purposes
    public String toString(){
        return "King";
    }

}
