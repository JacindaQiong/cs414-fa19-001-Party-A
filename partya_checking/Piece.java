package partya;

import java.util.ArrayList;

/**
 * User: Nana Yin
 * Date: 10/11/19
 * desc: This is the parent class for all the chess piece classes.
 */
public abstract class Piece {

    public enum Color{
        WHITE,
        BLACK
    }

    /**
     * the board it belongs to
     */
    protected GameBoard board = null;

    /**
     * the index of the horizontal rows in the range 0..7
     */
    protected int row;

    /**
     * the index of the vertical column in the range 0..7
     */
    protected int column;

    /**
     * the color of the piece
     */
    protected Color color;

    /**
     * set the board and color attributes
     * @param board
     * @param color
     */
    public Piece(GameBoard board, Color color) {
        this.board = board;
        this.color = color;
    }

    /**
     * @return the color of the piece
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return return the position of the piece
     * @desc: a two-character string: a single letter(a..h) + a single digit(1..8)
     */
    public String getPosition(){
        String position = String.valueOf((char)('a'+this.column))+ (char)(this.row+'1');
        return position;
    }

    /**
     * set the position of the piece to the appropriate row and column
     * @param position :a single letter(a..h) + a single digit(1..8)
     * @throws IllegalPositionException: if the "position" is illegal
     */
    public void setPosition(String position) throws IllegalPositionException{
        //1. check position
        if(position==null||position.length()!=2)
            throw new IllegalPositionException("this position is null or its length()!=2 !!");
        char[] pos = position.toCharArray();
        char pos_column= pos[0];
        char pos_row= pos[1];
        if(!(pos_column>='a'&&pos_column<='h'&&pos_row>='1'&&pos_row<='8'))
            throw new IllegalPositionException("this position contains illegal characters or it's outside of the board !!");

        //2. set row/column
        this.row = pos_row-'1';
        this.column = pos_column-'a';
    }

    /**
     * to be implemented in the concrete subclasses corresponding to each chess piece
     * @return all the legal moves that a piece can make based on the rules.
     *         if no legal move, return empty ArrayList.
     */
    abstract public ArrayList<String> legalMoves();
}