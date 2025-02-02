package game;

public class PieceChecker {
	
	//checks for a connect4, if there is one returns the winner
	public static Player checkConnect4(Piece p) {
		if(p == null)
			throw new IllegalArgumentException("piece cannot be null");
		Player player = p.getPlayer();
		//get neighbours to piece,
		//run recursive checks for each direction and sum
		int[] coords = p.getCoords();
		Board b = p.getBoard();
		if(b == null)
			throw new IllegalArgumentException("Piece's board cannot be null");
		Piece[][] board = p.getBoard().getBoard();
		//only have to check first coord since can only set both at the same time
		int row = coords[0];
		int col = coords[1];
		int neededConnections = b.getNeededConnections();
		if(row == -1)
			throw new IllegalArgumentException("Piece's coordinates must initalized");
		
		//now that we have coordinates and board, go through all the neighbours and recurse in directions summing them
		//note that the board MUST be at least 4x4
		
		//check horizontal line first
		int horizontalSum = 1;
		if(row - 1 >= 0)
			horizontalSum += PieceChecker.checkConnect4L(p, board[row-1][col]);
		if(row + 1 < b.getHeight())
			horizontalSum += PieceChecker.checkConnect4R(p, board[row+1][col]);
		
		if(horizontalSum >= neededConnections)
			return player;
		
		int verticalSum = 1;
		if(col - 1 >= 0)
			verticalSum += PieceChecker.checkConnect4D(p, board[row][col-1]);
		if(col + 1 < b.getWidth())
			verticalSum += PieceChecker.checkConnect4U(p, board[row][col+1]);
		
		if(verticalSum >= neededConnections)
			return player;
		
		int diagSumUR = 1; //diag going up to the right
		int diagSumDR = 1; //diag going down to the right
		
		if(col - 1 >= 0 && row -1 >= 0)
			diagSumUR += PieceChecker.checkConnect4DL(p, board[row-1][col-1]);
		if(col + 1 < b.getWidth() && row + 1 < b.getHeight())
			diagSumUR += PieceChecker.checkConnect4UR(p, board[row+1][col+1]);
		
		if(diagSumUR >= neededConnections)
			return player;
		
		if(col - 1 >= 0 && row + 1 < b.getHeight())
			diagSumDR += PieceChecker.checkConnect4UL(p, board[row+1][col-1]);
		if(col + 1 < b.getWidth() && row - 1 >= 0)
			diagSumDR += PieceChecker.checkConnect4DR(p, board[row-1][col+1]);
		
		return diagSumDR >= neededConnections ? player : null;
	}
	
	private static boolean sameColourPiece(Piece p1, Piece p2) {
		return (p1 != null && p2 != null && p1.getPlayer().getColour() == p2.getPlayer().getColour());
	}
	
	/*
	 * Following functions go recursivley in one direction and then sum up the sequence of the same colours
	 */
	
	private static int checkConnect4L(Piece prev, Piece curr) {
		if(!PieceChecker.sameColourPiece(prev, curr))
			return 0;
		
		int[] coords = curr.getCoords();
		int row = coords[0];
		int col = coords[1];
		if(row-1 < 0)
			return 1;
		Piece next = curr.getBoard().getBoard()[row-1][col];
		return 1 + PieceChecker.checkConnect4L(curr, next);
	}
	
	private static int checkConnect4R(Piece prev, Piece curr) {
		if(!PieceChecker.sameColourPiece(prev, curr))
			return 0;
		
		int[] coords = curr.getCoords();
		int row = coords[0];
		int col = coords[1];
		if(row+1 >= curr.getBoard().getHeight())
			return 1;
		Piece next = curr.getBoard().getBoard()[row+1][col];
		return 1 + PieceChecker.checkConnect4R(curr, next);
	}
	
	private static int checkConnect4U(Piece prev, Piece curr) {
		if(!PieceChecker.sameColourPiece(prev, curr))
			return 0;
		
		int[] coords = curr.getCoords();
		int row = coords[0];
		int col = coords[1];
		if(col+1 >= curr.getBoard().getWidth())
			return 1;
		Piece next = curr.getBoard().getBoard()[row][col+1];
		return 1 + PieceChecker.checkConnect4U(curr, next);
	}
	
	private static int checkConnect4D(Piece prev, Piece curr) {
		if(!PieceChecker.sameColourPiece(prev, curr))
			return 0;
		
		int[] coords = curr.getCoords();
		int row = coords[0];
		int col = coords[1];
		if(col-1 < 0)
			return 1;
		Piece next = curr.getBoard().getBoard()[row][col-1];
		return 1 + PieceChecker.checkConnect4D(curr, next);
	}
	
	private static int checkConnect4UR(Piece prev, Piece curr) {
		if(!PieceChecker.sameColourPiece(prev, curr))
			return 0;
		
		int[] coords = curr.getCoords();
		int row = coords[0];
		int col = coords[1];
		if(col+1 >= curr.getBoard().getWidth() || row+1 >= curr.getBoard().getHeight())
			return 1;
		Piece next = curr.getBoard().getBoard()[row+1][col+1];
		return 1 + PieceChecker.checkConnect4UR(curr, next);
	}
	
	private static int checkConnect4UL(Piece prev, Piece curr) {
		if(!PieceChecker.sameColourPiece(prev, curr))
			return 0;
		
		int[] coords = curr.getCoords();
		int row = coords[0];
		int col = coords[1];
		if(col-1 < 0 || row+1 >= curr.getBoard().getHeight())
			return 1;
		Piece next = curr.getBoard().getBoard()[row+1][col-1];
		return 1 + PieceChecker.checkConnect4UL(curr, next);
	}
	
	private static int checkConnect4DR(Piece prev, Piece curr) {
		if(!PieceChecker.sameColourPiece(prev, curr))
			return 0;
		
		int[] coords = curr.getCoords();
		int row = coords[0];
		int col = coords[1];
		if(col+1 > curr.getBoard().getWidth() || row-1 < 0)
			return 1;
		Piece next = curr.getBoard().getBoard()[row-1][col+1];
		return 1 + PieceChecker.checkConnect4DR(curr, next);
	}
	
	private static int checkConnect4DL(Piece prev, Piece curr) {
		if(!PieceChecker.sameColourPiece(prev, curr))
			return 0;
		
		int[] coords = curr.getCoords();
		int row = coords[0];
		int col = coords[1];
		if(col-1 < 0 || row-1 < 0)
			return 1;
		Piece next = curr.getBoard().getBoard()[row-1][col-1];
		return 1 + PieceChecker.checkConnect4DL(curr, next);
	}

}
