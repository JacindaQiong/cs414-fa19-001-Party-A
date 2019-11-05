package com.partyA.bean;

import com.partyA.exception.IllegalMoveException;
import com.partyA.exception.IllegalPositionException;

import java.util.ArrayList;

/**
 * User: Nana Yin
 * Date: 10/11/19
 * desc: store the state of the board and its pieces
 */
public class GameBoard {

    private Piece[][] board;

    private Match match;

    /*
    * even numbers represent black's turn
    * odd numbers represent white's turn
    * black moves first
    * */
    private int whoseTurn=0;

    public GameBoard() {
        board= new Piece[11][11];
    }

    public void initialize() {
        // white team

//        King whiteKing=new King(this, Piece.Color.WHITE);
//        placePiece(whiteKing,"ab");
//
//        Pawn whitePawn1 = new Pawn(this, Piece.Color.WHITE);
//        placePiece(whitePawn1,"ac");

    }

    /**
     * @param position
     * @return return the chess piece at a given position
     * @throws IllegalPositionException
     */
    public Piece getPiece(String position) throws IllegalPositionException{
        //1. check the position is illegal or not
        if(position==null||position.length()!=2)
            throw new IllegalPositionException("this position is null or its length()!=2 !!");
        char[] pos = position.toCharArray();
        char pos_column= pos[0];
        char pos_row= pos[1];
        if(!(pos_column>='a'&&pos_column<='k'&&pos_row>='a'&&pos_row<='k'))
            throw new IllegalPositionException("this position contains illegal characters or it's outside of the board !!");

        //2. transfer the position(two-character string) into row/column values
        int row = pos_row -'a';
        int column = pos_column -'a';
        //3. find the piece at a given position
        return board[row][column];
    }

    public boolean placePiece(Piece piece, String position){
        //1.1 FALSE: piece or position is illegal
        if(piece==null||position==null||position.length()!=2)
            return false;
        char[] pos = position.toCharArray();
        char pos_column= pos[0];
        char pos_row= pos[1];
        if(!(pos_column>='a' && pos_column<='k' && pos_row>='a' && pos_row<='k'))
            return false;

        try {
            //1.2 FALSE: already has a same color player
            Piece p = getPiece(position);
            if(p!=null&&p.getColor().equals(piece.getColor()))
                return false;

            //2 TRUE: set the piece's position && update the board
            piece.setPosition(position);
            int row = pos_row-'a';
            int column = pos_column-'a';
            board[row][column]=piece;
            return true;
        } catch (IllegalPositionException e) {
            e.printStackTrace();
            return false;
        }

    }

    public void move(String fromPosition, String toPosition) throws IllegalMoveException {
        try {
            //1. check if this moving is a legal move
            Piece piece = getPiece(fromPosition);
            if(piece!=null){
                ArrayList<String> legalMoves = piece.legalMoves();
                if(legalMoves.size()>0 && legalMoves.contains(toPosition)){
                    //2. it's legal---> execute moving
                    placePiece(piece,toPosition);

                    char[] pos = fromPosition.toCharArray();
                    int row = pos[1]-'a';
                    int column = pos[0]-'a';
                    board[row][column] = null;
                    whoseTurn++;
                }else{
                    throw new IllegalMoveException("legalMoves List doesn't contain toPosition!! it's illegal move!!");
                }
            }else{
                throw new IllegalMoveException("fromPosition is illegal!!! cannot get a piece!!  it's illegal move!!");
            }
        } catch (IllegalPositionException e) {
            throw new IllegalMoveException("legalMoves List doesn't contain toPosition!! it's illegal move!!");
        }
    }

    public int getWhoseTurn() {
        return whoseTurn;
    }

    private boolean isBlack(int row, int column){
        if(row<0||column<0)
            return false;

        String position = String.valueOf((char)('a'+column))+ (char)('a'+row);
        if("aa".equals(position)||"ak".equals(position)||"ka".equals(position)||"kk".equals(position)||"ff".equals(position))
            return true;

        try {
            Piece p = getPiece(position);
            if(p!=null&&Piece.Color.BLACK.equals(p.getColor()))
                return true;
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
        return false;


    }
    public int killOpponent(String toPosition) {
        try {
            King king = null;
            int king_row = 0;
            int king_column = 0;

            for(int i=0;i<11;i++){
                for(int j=0;j<11;j++){
                    String pos = String.valueOf((char)('a'+j))+ (char)('a'+i);
                    Piece p = getPiece(pos);
                    if(p instanceof  King){
                        king = (King) p;
                        king_row = i;
                        king_column = j;
                    }
                }
            }
            if(king != null){

                String kingPos = king.getPosition();
                // 1. white win
                if("aa".equals(kingPos)||"ak".equals(kingPos)||"ka".equals(kingPos)||"kk".equals(kingPos))
                    return 1;

                // 2. black win
                boolean king_left = isBlack(king_row,king_column-1);
                boolean king_right = isBlack(king_row,king_column+1);
                boolean king_up = isBlack(king_row+1,king_column);
                boolean king_down = isBlack(king_row-1,king_column);
                if(king_left&&king_right&&king_up&&king_down)
                    return 0;
            }

            // 3. kill an opponent
            char[] pos = toPosition.toCharArray();
            int row = pos[1]-'a';
            int column = pos[0]-'a';
            Piece p = getPiece(toPosition);
            Piece.Color currColor=p.getColor();
            //top

            String top_pos1 = String.valueOf((char)('a'+column))+ (char)('a'+row+1);
            if(top_pos1.charAt(0)>='a'&&top_pos1.charAt(0)<='k'&&top_pos1.charAt(1)>='a'&&top_pos1.charAt(1)<='k'){
                Piece top_piece1 = getPiece(top_pos1);
                if(top_piece1!=null && !top_piece1.getColor().equals(currColor)){
                    String top_pos2 = String.valueOf((char)('a'+column))+ (char)('a'+row+2);
                    if(top_pos2.charAt(0)>='a'&&top_pos2.charAt(0)<='k'&&top_pos2.charAt(1)>='a'&&top_pos2.charAt(1)<='k'){
                        Piece top_piece2 = getPiece(top_pos2);
                        if(top_piece2!=null&&top_piece2.getColor().equals(currColor))
                            board[row+1][column] = null;
                        if(top_piece2==null){
                            if("aa".equals(top_pos2)||"ak".equals(top_pos2)||"ka".equals(top_pos2)||"kk".equals(top_pos2)||"ff".equals(top_pos2))
                                board[row+1][column] = null;
                        }
                    }

                }
            }

            //bottom
            String bottom_pos1 = String.valueOf((char)('a'+column))+ (char)('a'+row-1);
            if(bottom_pos1.charAt(0)>='a'&&bottom_pos1.charAt(0)<='k'&&bottom_pos1.charAt(1)>='a'&&bottom_pos1.charAt(1)<='k'){
                Piece bottom_piece1 = getPiece(bottom_pos1);
                if(bottom_piece1!=null && !bottom_piece1.getColor().equals(currColor)){
                    String bottom_pos2 = String.valueOf((char)('a'+column))+ (char)('a'+row-2);
                    if(bottom_pos2.charAt(0)>='a'&&bottom_pos2.charAt(0)<='k'&&bottom_pos2.charAt(1)>='a'&&bottom_pos2.charAt(1)<='k'){
                        Piece bottom_piece2 = getPiece(bottom_pos2);
                        if(bottom_piece2!=null&&bottom_piece2.getColor().equals(currColor))
                            board[row-1][column] = null;
                        if(bottom_piece2==null){
                            if("aa".equals(bottom_pos2)||"ak".equals(bottom_pos2)||"ka".equals(bottom_pos2)||"kk".equals(bottom_pos2)||"ff".equals(bottom_pos2))
                                board[row-1][column] = null;
                        }
                    }
                }
            }
            
            //left
            String left_pos1 = String.valueOf((char)('a'+column-1))+ (char)('a'+row);
            if(left_pos1.charAt(0)>='a'&&left_pos1.charAt(0)<='k'&&left_pos1.charAt(1)>='a'&&left_pos1.charAt(1)<='k'){
                Piece left_piece1 = getPiece(left_pos1);
                if(left_piece1!=null && !left_piece1.getColor().equals(currColor)){
                    String left_pos2 = String.valueOf((char)('a'+column-2))+ (char)('a'+row);
                    if(left_pos2.charAt(0)>='a'&&left_pos2.charAt(0)<='k'&&left_pos2.charAt(1)>='a'&&left_pos2.charAt(1)<='k'){
                        Piece left_piece2 = getPiece(left_pos2);
                        if(left_piece2!=null&&left_piece2.getColor().equals(currColor))
                            board[row][column-1] = null;
                        if(left_piece2==null){
                            if("aa".equals(left_pos2)||"ak".equals(left_pos2)||"ka".equals(left_pos2)||"kk".equals(left_pos2)||"ff".equals(left_pos2))
                                board[row][column-1] = null;
                        }
                    }


                }
            }



            //right
            String right_pos1 = String.valueOf((char)('a'+column+1))+ (char)('a'+row);
            if(right_pos1.charAt(0)>='a'&&right_pos1.charAt(0)<='k'&&right_pos1.charAt(1)>='a'&&right_pos1.charAt(1)<='k'){
                Piece right_piece1 = getPiece(right_pos1);
                if(right_piece1!=null && !right_piece1.getColor().equals(currColor)){
                    String right_pos2 = String.valueOf((char)('a'+column+2))+ (char)('a'+row);
                    if(right_pos2.charAt(0)>='a'&&right_pos2.charAt(0)<='k'&&right_pos2.charAt(1)>='a'&&right_pos2.charAt(1)<='k'){
                        Piece right_piece2 = getPiece(right_pos2);
                        if(right_piece2!=null&&right_piece2.getColor().equals(currColor))
                            board[row][column+1] = null;
                        if(right_piece2==null){
                            if("aa".equals(right_pos2)||"ak".equals(right_pos2)||"ka".equals(right_pos2)||"kk".equals(right_pos2)||"ff".equals(right_pos2))
                                board[row][column+1] = null;
                        }
                    }


                }
            }


        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }

        return -1;

    }


    public String toString(){
        String chess="";
        String upperLeft = "\u250C";
        String upperRight = "\u2510";
        String horizontalLine = "\u2500";
        String horizontal3 = horizontalLine + "\u3000" + horizontalLine;
        String verticalLine = "\u2502";
        String upperT = "\u252C";
        String bottomLeft = "\u2514";
        String bottomRight = "\u2518";
        String bottomT = "\u2534";
        String plus = "\u253C";
        String leftT = "\u251C";
        String rightT = "\u2524";

        String topLine = upperLeft;
        for (int i = 0; i<11; i++){
            topLine += horizontal3 + upperT;
        }
        topLine += horizontal3 + upperRight;

        String bottomLine = bottomLeft;
        for (int i = 0; i<11; i++){
            bottomLine += horizontal3 + bottomT;
        }
        bottomLine += horizontal3 + bottomRight;
        chess+=topLine + "\n";

        for (int row = 10; row >=0; row--){
            String midLine = "";
            for (int col = 0; col < 11; col++){
                if(board[row][col]==null) {
                    midLine += verticalLine + " \u3000 ";
                } else {midLine += verticalLine + " "+board[row][col]+" ";}
            }
            midLine += verticalLine;
            String midLine2 = leftT;
            for (int i = 0; i<11; i++){
                midLine2 += horizontal3 + plus;
            }
            midLine2 += horizontal3 + rightT;
            chess+=midLine+ "\n";
            if(row>=1)
                chess+=midLine2+ "\n";
        }

        chess+=bottomLine;
        return chess;
    }
}
