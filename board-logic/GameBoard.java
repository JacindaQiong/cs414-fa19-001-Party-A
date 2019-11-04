public class GameBoard{
    private GameBoard gameBoard;
    private  Piece[][] board;
    private int count =0;

    //Constructor
    public GameBoard(){
    }

    //Get count to keep track of moves
    public int getCount(){
        return count;
    }
    //Check if position is on the board
    public boolean validPosition(String position){
        int col = toNIndex(position.charAt(0));
        int row = toNIndex(position.charAt(1));
        if(col <0 || row<0 || col >10 || row >10)
            return false;
        else
            return true;
    }
    //Check if move is general move is valid)
    public boolean validMove(String fromPosition, String toPosition){
        int fromCol = toNIndex(fromPosition.charAt(0));
        int fromRow = toNIndex(fromPosition.charAt(1));
        int toCol = toNIndex(toPosition.charAt(0));
        int toRow = toNIndex(toPosition.charAt(1));
        //Verify the position is on the board
        if(!validPosition(toPosition)){
            return false;
        }
        //If trying to change both column and row (aka diagonal, etc.) return false
        if(fromCol != toCol && fromRow != toRow){
            return false;
        }
        //If jumping over a piece return false
        if(fromCol != toCol){
            for(int i = fromCol+1; i<toCol; i++){
                if(board[i][toRow] != null){
                    return false;
                }
            }
        }
        if(fromRow != toRow){
            for(int i = fromRow+1; i<toRow; i++){
                if(board[toCol][i] != null){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isCorner(String position){
        if(position == "AA"|| position == "AK" || position == "KA" || position == "KK")
            return true;
        else return false;
    }
    public boolean isCenter(String position){
        if(position == "FF")
            return true;
        else return false;
    }
    public void initialize(){
        board =new Piece[11][11];
        //Attackers
        //Pawns top row
        for(int i = 3; i< 8; i++){
            Pawn p = new Pawn(this, "BLACK");
            board [0][i] = p;

        }
        //Middle top
        board[1][5] = new Pawn(this, "BLACK");
        //Right
        for(int i = 3; i< 8; i++){
            Pawn p = new Pawn(this, "BLACK");
            board [i][10] = p;

        }
        //Left of Right
        board[5][9] = new Pawn(this, "BLACK");
        //Left
        for(int i = 3; i< 8; i++){
            Pawn p = new Pawn(this, "BLACK");
            board [i][0] = p;
        }
        //Right of Left
        board[5][1] = new Pawn(this, "BLACK");
        //Bottom
        for(int i = 3; i< 8; i++){
            Pawn p = new Pawn(this, "BLACK");
            board [10][i] = p;

        }
        //Top of Bottom
        board[9][5] = new Pawn(this, "BLACK");

        //Defenders
        //Outer 4
        board [3][5] = new Pawn(this, "WHITE");
        board [7][5] = new Pawn(this, "WHITE");
        board [5][3] = new Pawn(this, "WHITE");
        board [5][7] = new Pawn(this, "WHITE");

        //Inner 8
        board [4][4] = new Pawn(this, "WHITE");
        board [4][5] = new Pawn(this, "WHITE");
        board [4][6] = new Pawn(this, "WHITE");
        board [5][4] = new Pawn(this, "WHITE");
        board [5][6] = new Pawn(this, "WHITE");
        board [6][4] = new Pawn(this, "WHITE");
        board [6][5] = new Pawn(this, "WHITE");
        board [6][6] = new Pawn(this, "WHITE");

        //King
        board[5][5] = new King(this, "WHITE");

        for(int i=0; i<11; i++){
            for(int j=0; j<11; j++){
                if(board[i][j] != null){
                    board[i][j].initialLocation("" +toSIndex(i) +toSIndex(j));
                }
            }
        }
    }

    //Method to convert string representation to natural integer representation
    public int toNIndex(char in){
        int nValue;
        switch(in){
            case 'A':
                nValue =0;
                break;
            case 'B':
                nValue =1;
                break;
            case 'C':
                nValue =2;
                break;
            case 'D':
                nValue =3;
                break;
            case 'E':
                nValue =4;
                break;
            case 'F':
                nValue =5;
                break;
            case 'G':
                nValue =6;
                break;
            case 'H':
                nValue =7;
                break;
            case 'I':
                nValue =8;
                break;
            case 'J':
                nValue =9;
                break;
            case 'K':
                nValue =10;
                break;
            default:
                nValue = -1;
                break;
       }
        return nValue;
    }

    public char toSIndex(int in){
        char sValue;
        switch(in){
            case 0:
                sValue ='A';
                break;
            case 1:
                sValue ='B';
                break;
            case 2:
                sValue ='C';
                break;
            case 3:
                sValue ='D';
                break;
            case 4:
                sValue ='E';
                break;
            case 5:
                sValue ='F';
                break;
            case 6:
                sValue ='G';
                break;
            case 7:
                sValue ='H';
                break;
            case 8:
                sValue ='I';
                break;
            case 9:
                sValue ='J';
                break;
            case 10:
                sValue = 'K';
                break;
            default:
                sValue = 'Z';
                break;
        }
        return sValue;
    }

    public void move(String fromPosition, String toPosition){
        int fromCol = toNIndex(fromPosition.charAt(0));
        int fromRow = toNIndex(fromPosition.charAt(1));
        int toCol = toNIndex(toPosition.charAt(0));
        int toRow = toNIndex(toPosition.charAt(1));

        if(board[fromCol][fromRow].setPosition(toPosition)){
            board[toCol][toRow] = board[fromCol][fromRow];
            board[fromCol][fromRow] = null;
            count++;
        }
    }

    //For implementation/testing purposes
    public String showBoard(){
        String theBoard = "";
        for(int i =0; i<11; i++){
            for(int j =0; j<11; j++){
                theBoard += board[i][j];
            }
            theBoard += '\n';
        }
        return theBoard;
    }

}
