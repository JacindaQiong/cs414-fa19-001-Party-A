public class Match{
    GameBoard board;
    public Match() {
        board = new GameBoard();
    }
    public String prompt(){
        if(board.getCount() %2 ==0){
            return "Player 1";
        }
        else
            return "Player 2";
    }

}
