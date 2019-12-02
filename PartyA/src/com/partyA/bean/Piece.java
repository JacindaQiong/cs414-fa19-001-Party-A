package com.partyA.bean;

import com.partyA.exception.IllegalPositionException;

import java.util.ArrayList;

/**
 * User: Nana Yin
 * Date: 10/11/19
 */
public abstract class Piece {

    public enum Color{
        WHITE,
        BLACK
    }

    protected GameBoard board = null;

    protected int x;
    protected int y;

    protected Color color;

    public Piece(GameBoard board, Color color) {
        this.board = board;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public String getPosition(){
        String position = String.valueOf((char)('a'+this.x))+ (char)('a'+this.y);
        return position;
    }


    public void setPosition(String position) throws IllegalPositionException {
        //1. check position
        if(position==null||position.length()!=2)
            throw new IllegalPositionException("this position is null or its length()!=2 !!");
        char[] pos = position.toCharArray();
        char pos_column= pos[0];
        char pos_row= pos[1];
        if(!(pos_column>='a'&&pos_column<='k'&&pos_row>='a'&&pos_row<='k'))
            throw new IllegalPositionException("this position contains illegal characters or it's outside of the board !!");

        //2. set row/column
        this.x = pos_column-'a';
        this.y = pos_row-'a';
    }

    abstract public ArrayList<String> legalMoves();
}