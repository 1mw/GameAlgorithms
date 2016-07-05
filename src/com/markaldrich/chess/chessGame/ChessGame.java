package com.markaldrich.chess.chessGame;

import java.util.Scanner;

/**
 * Created by maste on 7/2/2016.
 */
public class ChessGame {
	private ChessHumanPlayer human;
	private ChessComputerPlayer computer;
	private Scanner scanner;
	private ChessBoard currentBoard;
	
	public ChessGame(Scanner scanner) {
		// INITIALIZE BOARD!
		this.scanner = scanner;
		human = new ChessHumanPlayer(currentBoard, scanner);
		computer = new ChessComputerPlayer();
	}
	
	public void start() {
		
	}
}
