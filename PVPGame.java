/**
 * Names: Yazan Alshoroogi
 * netID: yalshoro
 * G#: 01395561
 * Lecture section: 001
 * Lab section: 202
 */

// PVPGame.java
import java.util.Scanner;
public class PVPGame{
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		Player p1 = new UserPlayer(s,"Red");
		Player p2 = new UserPlayer(s,"Black");
		MiniCheckers board = new MiniCheckers(p1,p2);

		while(!board.checkWin(p1) && !board.checkWin(p2)){
			board = p1.chooseMove(board);
			Player tmp = p1;
			p1 = p2;
			p2 = tmp;
		}
		if(board.checkWin(p1)){
			System.out.println("Player ["+p1+"] wins!");
		} else if(board.checkWin(p2)){
			System.out.println("Player ["+p2+"] wins!");
		} else {
			System.out.println("Draw?");
		}
		System.out.println("Final game board:");
		System.out.println(board);		
	}
}