package partya;


import java.util.ArrayList;

/**
 * User: Nana Yin
 * Date: 10/11/19
 * desc: king can only move one square horizontally, vertically or diagonally.
 */
public class King extends Piece {
    public King(GameBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        if(Color.WHITE.equals(this.color))
            return "\u2654";
        else
            return "\u265A";
    }

    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> legalMoves = new ArrayList<>();
        try {
            int i,j;
            //vertically: up
            for(i=row+1;i<=7;i++){
                String position = String.valueOf((char)('a'+column))+ (char)(i+'1');
                Piece p = board.getPiece(position);
                if(p==null)
                    legalMoves.add(position);
                else
                    break;
            }
            //vertically: down
            for(i=row-1;i>=0;i--){
                String position = String.valueOf((char)('a'+column))+ (char)(i+'1');
                Piece p = board.getPiece(position);
                if(p==null)
                    legalMoves.add(position);
                else
                    break;

            }
            //horizontally: left
            for(j=column-1;j>=0;j--){
                String position = String.valueOf((char)('a'+j))+ (char)(row+'1');
                Piece p = board.getPiece(position);
                if(p==null)
                    legalMoves.add(position);
                else
                    break;
            }
            //horizontally: right
            for(j=column+1;j<=7;j++){
                String position = String.valueOf((char)('a'+j))+ (char)(row+'1');
                Piece p = board.getPiece(position);
                if(p==null)
                    legalMoves.add(position);
                else
                    break;
            }
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
        return legalMoves;
    }
}