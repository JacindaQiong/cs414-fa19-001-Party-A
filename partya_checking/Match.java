package partya;

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
            result = board.killOpponent(toPosition);
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        }

    }



    public String prompt(){
        String msg = "";
        int whoseTurn = board.getWhoseTurn();
        if(whoseTurn%2==0){
            msg = blackUser.getNickname()+", please move.";
        }else{
            msg = whiteUser.getNickname()+", please move.";
        }
        return msg;
    }
}
