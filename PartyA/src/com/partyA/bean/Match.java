package com.partyA.bean;

import com.partyA.exception.IllegalMoveException;

/**
 * User: Nana Yin
 * Date: 10/30/19
 */
public class Match {
    private User whiteUser;
    private User blackUser;
    private GameBoard board;

    //0 black win, 1 white win
    private int result;

    public Match(User whiteUser, User blackUser, GameBoard board) {
        this.whiteUser = whiteUser;
        this.blackUser = blackUser;
        this.board = board;
    }

    public void execute(String fromPosition, String toPosition){
        try {
            board.move(fromPosition,toPosition);
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        }
        result = board.killOpponent(toPosition);

    }



    public String prompt(){
        String msg = "";
        int whoseTurn = board.getWhoseTurn();
        if(whoseTurn%2==0){
            msg = blackUser.getName()+", please move.";
        }else{
            msg = whiteUser.getName()+", please move.";
        }
        return msg;
    }
}
