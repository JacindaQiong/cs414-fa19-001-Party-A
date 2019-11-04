
/**
 * @author Brandon Hua
 *
 */
public class ChessBoard {

	private ChessPiece[][] board;
	
	public ChessBoard(){
		this.board = new ChessPiece[8][8];
	}

	public void initialize(){

		ChessPiece br1 = new Rook(this, ChessPiece.Color.BLACK);
		ChessPiece bk1 = new Knight(this, ChessPiece.Color.BLACK);
		ChessPiece bb1 = new Bishop(this, ChessPiece.Color.BLACK);
		ChessPiece bki = new King(this, ChessPiece.Color.BLACK);
		ChessPiece bq = new Queen(this, ChessPiece.Color.BLACK);
		ChessPiece br2 = new Rook(this, ChessPiece.Color.BLACK);
		ChessPiece bk2 = new Knight(this, ChessPiece.Color.BLACK);
		ChessPiece bb2 = new Bishop(this, ChessPiece.Color.BLACK);
		ChessPiece bp1 = new Pawn(this, ChessPiece.Color.BLACK);
		ChessPiece bp2 = new Pawn(this, ChessPiece.Color.BLACK);
		ChessPiece bp3 = new Pawn(this, ChessPiece.Color.BLACK);
		ChessPiece bp4 = new Pawn(this, ChessPiece.Color.BLACK);
		ChessPiece bp5 = new Pawn(this, ChessPiece.Color.BLACK);
		ChessPiece bp6 = new Pawn(this, ChessPiece.Color.BLACK);
		ChessPiece bp7 = new Pawn(this, ChessPiece.Color.BLACK);
		ChessPiece bp8 = new Pawn(this, ChessPiece.Color.BLACK);


		placePiece(br1, "a8");
		placePiece(bk1, "b8");
		placePiece(bb1,"c8");
		placePiece(bki, "d8");
		placePiece(bq, "e8");
		placePiece(bb2, "f8");
		placePiece(bk2, "g8");
		placePiece(br2, "h8");
		placePiece(bp1, "a7");
		placePiece(bp2, "b7");
		placePiece(bp3, "c7");
		placePiece(bp4, "d7");
		placePiece(bp5, "e7");
		placePiece(bp6, "f7");
		placePiece(bp7, "g7");
		placePiece(bp8, "h7");

		ChessPiece wr1 = new Rook(this, ChessPiece.Color.WHITE);
		ChessPiece wk1 = new Knight(this, ChessPiece.Color.WHITE);
		ChessPiece wb1 = new Bishop(this, ChessPiece.Color.WHITE);
		ChessPiece wr2 = new Rook(this, ChessPiece.Color.WHITE);
		ChessPiece wk2 = new Knight(this, ChessPiece.Color.WHITE);
		ChessPiece wb2 = new Bishop(this, ChessPiece.Color.WHITE);
		ChessPiece wki = new King(this, ChessPiece.Color.WHITE);
		ChessPiece wq = new Queen(this, ChessPiece.Color.WHITE);
		ChessPiece wp1 = new Pawn(this, ChessPiece.Color.WHITE);
		ChessPiece wp2 = new Pawn(this, ChessPiece.Color.WHITE);
		ChessPiece wp3 = new Pawn(this, ChessPiece.Color.WHITE);
		ChessPiece wp4 = new Pawn(this, ChessPiece.Color.WHITE);
		ChessPiece wp5 = new Pawn(this, ChessPiece.Color.WHITE);
		ChessPiece wp6 = new Pawn(this, ChessPiece.Color.WHITE);
		ChessPiece wp7 = new Pawn(this, ChessPiece.Color.WHITE);
		ChessPiece wp8= new Pawn(this, ChessPiece.Color.WHITE);

		placePiece(wr1, "a1");
		placePiece(wk1, "b1");
		placePiece(wb1,"c1");
		placePiece(wki, "d1");
		placePiece(wq, "e1");
		placePiece(wb2, "f1");
		placePiece(wk2, "g1");
		placePiece(wr2, "h1");
		placePiece(wp1, "a2");
		placePiece(wp2, "b2");
		placePiece(wp3, "c2");
		placePiece(wp4, "d2");
		placePiece(wp5, "e2");
		placePiece(wp6, "f2");
		placePiece(wp7, "g2");
		placePiece(wp8, "h2");	
	}

	public boolean onBoard(String position){
		char column = position.charAt(0);
		int row = Integer.valueOf(position.charAt(1) + "");
		if(column >= 'a' && column <= 'h') {//colunn is valid
			if (row >= 1 && row <= 8) {
				if (position.length() == 2) {
					return true;
				}
			}
		}
		return false;
	}
	public ChessPiece getPiece(String position)throws IllegalPositionException{

		if(!onBoard(position)){
			throw new IllegalPositionException("ChessPiece is not on board");
		}

		int row = (Integer.parseInt(position.substring(1))) -1;

		int column =0;
		switch(position.charAt(0)){
			case 'a': column =0;
				break;
			case 'b': column =1;
			break;
			case 'c': column =2;
			break;
			case 'd': column =3;
			break;
			case 'e': column =4;
			break;
			case 'f': column =5;
			break;
			case 'g': column =6;
			break;
			case 'h': column =7;
			break;
			default:
				break;
		}
		return this.board[row][column];
	}
	public boolean placePiece(ChessPiece piece, String position){
		try {
			if (getPiece(position) == null || piece.color != getPiece(position).color) {
				piece.setPosition(position);
				board[piece.row][piece.column] = piece;
				return true;
			}
		}catch(IllegalPositionException e ){System.out.println("You can't place a piece here");}
		return false;
	}

	public void move(String fromPosition, String toPosition) throws IllegalMoveException{
		try {
			if (getPiece(fromPosition).legalMoves().contains(toPosition)) {

						int row = getPiece(fromPosition).row;
						int col = getPiece(fromPosition).column;
						placePiece(board[row][col], toPosition);

						board[row][col] = null;



			}else{throw new IllegalMoveException("This isn't a legal move");}

		}catch(IllegalPositionException e){System.out.println("this is an illegal position");}
		catch(IllegalMoveException f){System.out.println("this is an illegal move");}

	}
	public String toString(){
	    String chess="";
	    String upperLeft = "\u250C";
	    String upperRight = "\u2510";
	    String horizontalLine = "\u2500";
	    String horizontal3 = horizontalLine + " " + horizontalLine; // "\u3000"
	    String verticalLine = "\u2502";
	    String upperT = "\u252C";
	    String bottomLeft = "\u2514";
	    String bottomRight = "\u2518";
	    String bottomT = "\u2534";
	    String plus = "\u253C";
	    String leftT = "\u251C";
	    String rightT = "\u2524";

	    String topLine = upperLeft;
	    for (int i = 0; i<7; i++){
	        topLine += horizontal3 + upperT;
	    }
	    topLine += horizontal3 + upperRight;

	    String bottomLine = bottomLeft;
	    for (int i = 0; i<7; i++){
	        bottomLine += horizontal3 + bottomT;
	    }
	    bottomLine += horizontal3 + bottomRight;
	    chess+=topLine + "\n";

	    for (int row = 7; row >=0; row--){
	        String midLine = "";
	        for (int col = 0; col < 8; col++){
	            if(board[row][col]==null) {
	                midLine += verticalLine + "   "; // " \u3000 "
	            } else {midLine += verticalLine + " "+board[row][col]+" ";}
	        }
	        midLine += verticalLine;
	        String midLine2 = leftT;
	        for (int i = 0; i<7; i++){
	            midLine2 += horizontal3 + plus;
	        }
	        midLine2 += horizontal3 + rightT;
	        chess+=midLine+ "\n";
	        if(row>=1)
	            chess+=midLine2+ "\n";
	    }

	    chess+=bottomLine;
	    return chess;
	}
	
	public static void main(String[] args) {
		try {
			ChessBoard board = new ChessBoard();
			board.initialize();
			System.out.println(board);
			board.move("a8", "a1");

			board.move("c7", "c6");
			System.out.println(board);
		} catch(IllegalMoveException move){System.out.println("llegal Move");}

	}
}
