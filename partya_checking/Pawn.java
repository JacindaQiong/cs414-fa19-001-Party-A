package partya;

import java.util.ArrayList;

/**
 * User: Nana Yin
 * Date: 10/11/19
 * desc: 1. in the initial position, pawn can move one or two squares vertically forward to an empty square
 *       2. subsequently, pawn can move only one square vertically forward to an empty square
 *       3. pawn may capture an opponent's piece diagonally one square in front of it.
 *       4. pawn can never move backwards.
 */
public class Pawn extends Piece {
    public Pawn(GameBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        if (Color.WHITE.equals(this.color))
            return "\u2659";
        else
            return "\u265F";
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