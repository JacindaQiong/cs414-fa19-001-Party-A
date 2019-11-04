package partya;

import java.util.ArrayList;

/**
 * User: Nana Yin
 * Date: 10/11/19
 * desc: store the state of the board and its pieces
 */
public class GameBoard {

    /** 2-dimensional array of size 11*11
     * a=0, b=1, c=2, d=3, e=4, f=5, g=6, h=7, i=8, j=9, k=10
     * In the initial position, the white king at f6 is at index[5][g]
     */
    private Piece[][] board;

    private Match match;

    /*
    * even numbers represent black's turn
    * odd numbers represent white's turn
    * black moves first
    * */
    private int whoseTurn=0;

    /**
     * initialize the board to an 11*11 array with all empty square
     */
    public GameBoard() {
        board= new Piece[8][8];
    }

    /** initialize the board to the standard chess opening state
     *  1. use the constructors of the appropriate pieces
     *  2. call placePiece to place the newly constructed pieces in the right position
     */

    public void initialize() {
        // white team

        King whiteKing=new King(this, Piece.Color.WHITE);
        placePiece(whiteKing,"e1");

        Pawn whitePawn1 = new Pawn(this, Piece.Color.WHITE);
        placePiece(whitePawn1,"a2");

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
        if(!(pos_column>='a'&&pos_column<='h'&&pos_row>='1'&&pos_row<='8'))
            throw new IllegalPositionException("this position contains illegal characters or it's outside of the board !!");

        //2. transfer the position(two-character string) into row/column values
        int row = pos_row -'1';
        int column = pos_column -'a';
        //3. find the piece at a given position
        return board[row][column];
    }

    /**
     * @param piece
     * @param position
     * @return true if successful, false if failed
     * @throws IllegalPositionException
     * 1.1 if the position is illegal --->it failed
     * 1.2 if there is already a piece of the same player in the given position ---> it failed
     * if an opponent's piece exists, that piece is captured ---> successful
     * 2.1 if successful, call setPosition() in the ChessPiece class to set the piece's position
     * 2.2 update the board
     */
    public boolean placePiece(Piece piece, String position){
        //1.1 FALSE: piece or position is illegal
        if(piece==null||position==null||position.length()!=2)
            return false;
        char[] pos = position.toCharArray();
        char pos_column= pos[0];
        char pos_row= pos[1];
        if(!(pos_column>='a' && pos_column<='h' && pos_row>='1' && pos_row<='8'))
            return false;

        try {
            //1.2 FALSE: already has a same color player
            Piece p = getPiece(position);
            if(p!=null&&p.getColor().equals(piece.getColor()))
                return false;

            //2 TRUE: set the piece's position && update the board
            piece.setPosition(position);
            int row = pos_row-'1';
            int column = pos_column-'a';
            board[row][column]=piece;
            return true;
        } catch (IllegalPositionException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * @param fromPosition
     * @param toPosition
     * @throws IllegalMoveException
     * @throws IllegalPositionException
     * 1. check if this moving is a legal move
     */
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
                    int row = pos[1]-'1';
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

    public void setWhoseTurn(int whoseTurn) {
        this.whoseTurn = whoseTurn;
    }

    private boolean isBlack(int row, int column){
        if(row<0||column<0)
            return false;

        String position = String.valueOf((char)('a'+column))+ (char)(row+'1');
        if("a1".equals(position)||"a11".equals(position)||"k1".equals(position)||"k11".equals(position))
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
                    String pos = String.valueOf((char)('a'+j))+ (char)(i+'1');
                    Piece p = getPiece(pos);
                    if(p instanceof  King){
                        king = (King) p;
                        king_row = i;
                        king_column = j;
                    }
                }
            }
            String kingPos = king.getPosition();
            // 1. white win
            if("a1".equals(kingPos)||"a11".equals(kingPos)||"k1".equals(kingPos)||"k11".equals(kingPos))
                return 1;

            // 2. black win
            boolean king_left = isBlack(king_row,king_column-1);
            boolean king_right = isBlack(king_row,king_column+1);
            boolean king_up = isBlack(king_row+1,king_column);
            boolean king_down = isBlack(king_row-1,king_column);
            if(king_left&&king_right&&king_up&&king_down)
                return 0;

            // 3. kill an opponent
            char[] pos = toPosition.toCharArray();
            int row = pos[1]-'1';
            int column = pos[0]-'a';
            Piece p = getPiece(toPosition);
            Piece.Color currColor=p.getColor();
            //top
            String top_pos1 = String.valueOf((char)('a'+column))+ (char)(row+1+'1');
            Piece top_piece1 = getPiece(top_pos1);
            if(top_piece1!=null && !top_piece1.getColor().equals(currColor)){
                String top_pos2 = String.valueOf((char)('a'+column))+ (char)(row+2+'1');
                Piece top_piece2 = getPiece(top_pos2);
                if(top_piece2!=null&&top_piece2.getColor().equals(currColor))
                    board[row+1][column] = null;
                if(top_piece2==null){
                    if("a1".equals(top_pos2)||"a11".equals(top_pos2)||"k1".equals(top_pos2)||"k11".equals(top_pos2)||"f6".equals(top_pos2))
                        board[row+1][column] = null;
                }

            }
            //bottom
            String bottom_pos1 = String.valueOf((char)('a'+column))+ (char)(row-1+'1');
            Piece bottom_piece1 = getPiece(bottom_pos1);
            if(bottom_piece1!=null && !bottom_piece1.getColor().equals(currColor)){
                    String bottom_pos2 = String.valueOf((char)('a'+column))+ (char)(row-2+'1');
                    Piece bottom_piece2 = getPiece(bottom_pos2);
                    if(bottom_piece2!=null&&bottom_piece2.getColor().equals(currColor))
                        board[row-1][column] = null;
                    if(bottom_piece2==null){
                        if("a1".equals(bottom_pos2)||"a11".equals(bottom_pos2)||"k1".equals(bottom_pos2)||"k11".equals(bottom_pos2)||"f6".equals(bottom_pos2))
                            board[row-1][column] = null;
                    }

            }


            //left
            String left_pos1 = String.valueOf((char)('a'+column-1))+ (char)(row+'1');
            Piece left_piece1 = getPiece(left_pos1);
            if(left_piece1!=null && !left_piece1.getColor().equals(currColor)){
                String left_pos2 = String.valueOf((char)('a'+column-2))+ (char)(row+'1');
                Piece left_piece2 = getPiece(left_pos2);
                if(left_piece2!=null&&left_piece2.getColor().equals(currColor))
                    board[row][column-1] = null;
                if(left_piece2==null){
                    if("a1".equals(left_pos2)||"a11".equals(left_pos2)||"k1".equals(left_pos2)||"k11".equals(left_pos2)||"f6".equals(left_pos2))
                        board[row][column-1] = null;
                }

            }

            //right
            String right_pos1 = String.valueOf((char)('a'+column+1))+ (char)(row+'1');
            Piece right_piece1 = getPiece(right_pos1);
            if(right_piece1!=null && !right_piece1.getColor().equals(currColor)){
                String right_pos2 = String.valueOf((char)('a'+column+2))+ (char)(row+'1');
                Piece right_piece2 = getPiece(right_pos2);
                if(right_piece2!=null&&right_piece2.getColor().equals(currColor))
                    board[row][column+1] = null;
                if(right_piece2==null){
                    if("a1".equals(right_pos2)||"a11".equals(right_pos2)||"k1".equals(right_pos2)||"k11".equals(right_pos2)||"f6".equals(right_pos2))
                        board[row][column+1] = null;
                }

            }

        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }

        return -1;

    }
}
