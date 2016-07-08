package com.markaldrich.chess.chessGame;

import java.util.Scanner;

/**
 * Created by maste on 7/2/2016.
 */
public class ChessHumanPlayer extends ChessPlayer {
	private Scanner scanner;
	
	public ChessHumanPlayer(ChessBoard board, Scanner scanner) {
		this.board = board;
		this.scanner = scanner;
	}
	
	@Override
	public ChessMove move() {
		while (true) {
			System.out.print("Enter coordinates of piece to move and coordinates of where to move piece (ax, ay, bx, by): ");
			String input = scanner.nextLine();
			
			input = input.replace(" ", "");
			String[] strings = input.split(",");
			
			if (strings.length != 4) {
				System.out.println("You did not enter four numbers");
				continue;
			}
			
			int[] numbers = new int[4];
			numbers[0] = Integer.valueOf(strings[0]);
			numbers[1] = Integer.valueOf(strings[1]);
			numbers[2] = Integer.valueOf(strings[2]);
			numbers[3] = Integer.valueOf(strings[3]);
			
			ChessMove move = new ChessMove(numbers[0], numbers[1], numbers[2], numbers[3]);
			if (move.isValidMove(board)) {
				return move;
			} else {
				System.out.println("Not a valid move");
			}
		}
	}
}
