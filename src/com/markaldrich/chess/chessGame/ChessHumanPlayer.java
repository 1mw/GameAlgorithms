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
}
