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
		currentBoard = new ChessBoard();
		this.scanner = scanner;
		human = new ChessHumanPlayer(currentBoard, scanner);
		computer = new ChessComputerPlayer(currentBoard, 5, 123L);
	}
	
	public void start() {
		while (true) {
			System.out.println(currentBoard.toString());
			currentBoard.doMove(human.move());
			System.out.println(currentBoard.toString());
			currentBoard.doMove(computer.move());
			
			if (currentBoard.humanWon()) {
				System.out.println("Human was won!");
				return;
			}
			if (currentBoard.computerWon()) {
				System.out.println("Computer has won!");
				return;
			}
		}
	}
}
