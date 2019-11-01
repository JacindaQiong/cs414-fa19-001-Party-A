public class Run {
    public static void main(String args[]) {
        GameBoard gb = new GameBoard();
        gb.initialize();
        String board = gb.showBoard();
        System.out.print(board);
        System.out.println();
        System.out.println("Moving");
        gb.move("HF", "HI");
        gb.move("GF", "IF");
        gb.move("FF", "HF");
        //This one shouldn't move
        gb.move("FG","FF");
        board = gb.showBoard();
        System.out.print(board);
    }
}
