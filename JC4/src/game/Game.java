/**
 * 
 */
package game;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 */
public class Game {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board b;
		Scanner in = new Scanner(System.in);
		boolean newGame = false;
		Player p1;
		Player p2;
		Player player;
		System.out.println("Welcome to connect 4!");
		do {
			System.out.println("Would you like to play with a custom board? (Y/N)");
			if(in.nextLine().trim().toUpperCase().equals("Y"))
				b = customizeBoard(in);
			else
				b = new Board();
			System.out.println("Please enter in the name of Player 1 (RED)");
			p1 = new Player(in.nextLine(), b, Colour.RED);
			System.out.println("Please enter in the name of Player 2 (BLUE)");
			p2 = new Player(in.nextLine(), b, Colour.BLUE);
			int col = -1;
			int turn = 0;
			boolean intError;
			while(!b.hasConnectFour() && !b.isFilled()) {
				if(turn % 2 == 0)
					player = p1;
				else 
					player = p2;
				
				System.out.println(b);
				do{
					intError = false;
					col = queryScannerForInt(in, 
							String.format("Select the column to place your piece in (0-%d)", b.getWidth()-1));
					turn++;
				}while(intError || !player.placePiece(col));
			}
			System.out.println(b);
			if(b.hasConnectFour()) {
				System.out.println(String.format("Player %s Wins!", b.getWinner().toString()));
			}else {
				System.out.println("Tie!");
			}
			System.out.println("Play again? (Y/N)");
			newGame = (in.nextLine().trim().toUpperCase().equals("Y"));
		}while(newGame);
		System.out.println("Closing app");
		in.close();
		
		
	}
	
	private static Board customizeBoard(Scanner sc) {
		boolean connectError = false;
		int width;
		int height;
		int connect;
		do {
			connectError = false;
			try {
				connect = queryScannerForInt(sc, "Please enter how many dots you'd like to connect");
				height = queryScannerForInt(sc, "Please enter how many dots tall you'd like the board to be");
				width = queryScannerForInt(sc, "Please enter how many dots wide you'd like the board to be");
				return new Board(height, width, connect);
			}catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
				connectError = true;
			}
		}while(connectError);
		return new Board();
	}
	
	private static int queryScannerForInt(Scanner sc, String prompt) {
		boolean intError;
		do {
			intError = false;
			System.out.println(prompt);
			try {
				return Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				intError = true;
			}
		}while(intError);
		return -1;
	}
}
