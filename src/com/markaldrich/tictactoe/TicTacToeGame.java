package com.markaldrich.tictactoe;

import java.util.Scanner;

/**
 * Created by maste on 7/2/2016.
 */
public class TicTacToeGame {
	private Scanner scanner;
	private TicTacToePlayer human, cpu;
	private TicTacToeBoard board;
	
	/**
	 * Constructs a new TicTacToeGame
	 * @param scanner Scanner that the game and the HumanTicTacToePlayer should use to get its inputs
	 */
	public TicTacToeGame(Scanner scanner) {
		human = new HumanTicTacToePlayer(scanner);
	}
	
	@Override
	public String toString() {
		return "TicTacToeGame{" +
				"scanner=" + scanner +
				", human=" + human +
				", cpu=" + cpu +
				", board=" + board +
				", viewGame()=" + viewGame() +
				'}';
	}
	
	public String viewGame() {
		String ret = "";
		
		// X axis
		ret += "\t0\t1\t2";
		
		for (int y = 0; y < 3; y++) {
			// Y axis
			ret += y;
			
			// Status of spots
		}
		
		return ret;
	}
}
