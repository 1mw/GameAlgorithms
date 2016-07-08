package com.markaldrich.chess.chessGame;

import java.util.Random;

/**
 * Created by maste on 7/2/2016.
 */
public class ChessComputerPlayer extends ChessPlayer {
	private Random random;
	
	public ChessComputerPlayer(ChessBoard board, long seed) {
		this.board = board;
		random = new Random(seed);
	}
	
	public ChessComputerPlayer(ChessBoard board) {
		this.board = board;
		random = new Random();
	}
	
	@Override
	public ChessMove move() {
		return null;
	}
}
