package game;

public class Piece {
	private Player player;
	private Board b = null;
	private int [] coords = {-1, -1};
	private int connectCount = 1;
	
	public Piece(Player p) {
		this.player = p;
	}
	
	public void setBoard(Board b ) {
		this.b = b;
	}
	public Board getBoard() {
		return this.b;
	}
	
	public int[] getCoords() {
		return this.coords.clone();
	}
	
	public void setPlayer(Player p) {
		this.player = p;
	}
	public Player getPlayer() {
		return this.player;
	}
	
	public void setCoords(int[] coords) {
		if(this.b == null)
			throw new IllegalStateException("board must be not be null to set coordinates");
		if(coords.length != 2) 
			throw new IllegalArgumentException("coordinate array must be length 2");
		if(coords[0] < 0 || coords[0] > b.getHeight() -1 || coords[1] < 0 || coords[1] > b.getWidth() - 1) 
			throw new IllegalArgumentException("Coordinates cannot be outside the bounds of the board");
		
		
		this.coords = coords;
		
	}
	
	public boolean hasConnect4() {
		return this.connectCount >= 4;
	}
	
	
	public String toString() {
		return String.format("%sO%s",player.getColour().toString(), Colour.RESET.toString());
	}
}
