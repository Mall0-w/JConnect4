package game;
public class Board {
	private static final int DEFAULT_WIDTH = 7;
	private static final int DEFAULT_HEIGHT = 6;
	private static final int DEFAULT_CONNECT = 4;
	private Player winner = null;
	private int height;
	private int width;
	private int connect;
	private Piece [][] board;
	
	
	public Board(int height, int width, int connect) {
		if(connect <= 1)
			throw new IllegalArgumentException("must connect more than 1");
		if(height < connect || width < connect) {
			throw new IllegalArgumentException("Both Width and Height must be at least " + connect);
		}
		this.height = height;
		this.width = width;
		this.connect = connect;
		board = new Piece[height][width];
	}
	
	public Board() {
		this(DEFAULT_HEIGHT, DEFAULT_WIDTH, DEFAULT_CONNECT);
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getNeededConnections() {
		return this.connect;
	}
	
	public Piece[][] getBoard(){
		return this.board.clone();
	}
	
	//tries to place the piece on the board, returns true if successfully placed and false otherwise
	public boolean place(Piece p, int column){
		if(column >= this.width || column < 0)
			return false;
		for(int i = this.height - 1; i >= 0; i --) {
			if(this.board[i][column] == null) {
				this.board[i][column] = p;
				p.setBoard(this);
				int[] coords = {i, column};
				p.setCoords(coords);
				winner = PieceChecker.checkConnect4(p);
				return true;
			}
		}
		return false;
	}
	
	public boolean isFilled() {
		for(int j = 0; j < this.getHeight(); j++) {
			for(int i = 0; i < this.getWidth(); i++) {
				if(this.board[i][j] == null)
					return false;
			}
		}
		return true;
	}
	
	public boolean hasConnectFour() {
		return winner != null;
	}
	
	public Player getWinner() {
		return winner;
	}
	
	public String toString() {
		StringBuilder finished = new StringBuilder();
		
		for(Piece[] row : board) {
			for(Piece p : row) {
				if(p == null) finished.append("_ ");
				else finished.append(p.toString() + " ");
			}
			finished.append("\n");
		}
		for(int i = 0; i < this.getWidth(); i++) {
			finished.append(i + " ");
		}
		finished.append("\n");
		//remove the last character to get rid of the final newline (board cannot be size 0 so don't have to worry about errors)
		finished.deleteCharAt(finished.length() - 1);
		return finished.toString();
	}
}
