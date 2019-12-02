package com.partyA.bean;


import com.partyA.exception.IllegalPositionException;

import java.util.ArrayList;

/**
 * User: Nana Yin
 * Date: 10/11/19
 */
public class King extends Piece {
    public King(GameBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
            return "K";
    }

    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> legalMoves = new ArrayList<>();
        try {
            int i,j;
            //vertically: up
            for(i= y +1; i<=10; i++){
                String position = String.valueOf((char)('a'+ x))+ (char)('a'+i);
                Piece p = board.getPiece(position);
                if(p==null)
                    legalMoves.add(position);
                else
                    break;
            }
            //vertically: down
            for(i= y -1; i>=0; i--){
                String position = String.valueOf((char)('a'+ x))+ (char)('a'+i);
                Piece p = board.getPiece(position);
                if(p==null)
                    legalMoves.add(position);
                else
                    break;

            }
            //horizontally: left
            for(j= x -1; j>=0; j--){
                String position = String.valueOf((char)('a'+j))+ (char)('a'+ y);
                Piece p = board.getPiece(position);
                if(p==null)
                    legalMoves.add(position);
                else
                    break;
            }
            //horizontally: right
            for(j= x +1; j<=10; j++){
                String position = String.valueOf((char)('a'+j))+ (char)('a'+ y);
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