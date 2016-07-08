package com.markaldrich.chess.chessGame;

/**
 * Created by maste on 7/2/2016.
 */
public abstract class ChessPlayer {
	protected ChessBoard board;
	protected boolean human;
	
	public ChessBoard getBoard() {
		return board;
	}
	
	public void setBoard(ChessBoard board) {
		this.board = board;
	}
	
	public boolean isHuman() {
		return human;
	}
	
	public void setHuman(boolean human) {
		this.human = human;
	}
}
