package com.partyA.bean;

import com.partyA.exception.IllegalPositionException;

import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(GameBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        if (Color.WHITE.equals(this.color))
            return "W";
        else
            return "B";
    }

    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> legalMoves = new ArrayList<>();
        try {
            int i,j;
            //vertically: up
            for(i= y +1; i<=10; i++){
                String position = String.valueOf((char)('a'+ x))+ (char)('a'+i);
                if("aa".equals(position)||"ak".equals(position)||"ka".equals(position)||"kk".equals(position)||"ff".equals(position))
                    continue;
                Piece p = board.getPiece(position);
                if(p==null)
                    legalMoves.add(position);
                else
                    break;
            }
            //vertically: down
            for(i= y -1; i>=0; i--){
                String position = String.valueOf((char)('a'+ x))+ (char)('a'+i);
                if("aa".equals(position)||"ak".equals(position)||"ka".equals(position)||"kk".equals(position)||"ff".equals(position))
                    continue;
                Piece p = board.getPiece(position);
                if(p==null)
                    legalMoves.add(position);
                else
                    break;
            }
            //horizontally: left
            for(j= x -1; j>=0; j--){
                String position = String.valueOf((char)('a'+j))+ (char)('a'+ y);
                if("aa".equals(position)||"ak".equals(position)||"ka".equals(position)||"kk".equals(position)||"ff".equals(position))
                    continue;
                Piece p = board.getPiece(position);
                if(p==null)
                    legalMoves.add(position);
                else
                    break;
            }
            //horizontally: right
            for(j= x +1; j<=10; j++){
                String position = String.valueOf((char)('a'+j))+ (char)('a'+ y);
                if("aa".equals(position)||"ak".equals(position)||"ka".equals(position)||"kk".equals(position)||"ff".equals(position))
                    continue;
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