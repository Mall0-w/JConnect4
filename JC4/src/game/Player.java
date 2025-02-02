package game;

public class Player {
	private Colour c;
	private String name;
	private Board b;
	
	public Player(String name, Board b, Colour c) {
		this.name = name;
		this.b = b;
		this.c = c;
	}
	
	public Colour getColour() {
		return this.c;
	}
	
	public String toString() {
		return this.name;
	}
	
	public boolean placePiece(int column) {
		Piece p = new Piece(this);
		return b.place(p, column);
	}
}
