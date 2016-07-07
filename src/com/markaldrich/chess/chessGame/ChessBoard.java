package com.markaldrich.chess.chessGame;

/**
 * Created by maste on 7/2/2016.
 */
public class ChessBoard {
	private ChessPiece[][] board;
	
	public ChessBoard() {
		// setPiece(0, 0, );
	}

	public void setPiece(int x, int y, ChessPiece piece) {
		board[y][x] = piece;
	}
	

	public ChessPiece getPiece(int x, int y) {
		if (!inBounds(x, y)) {
			return null;
		}
		return board[y][x];
	}
	
	public boolean isEmpty(int x, int y) {
		return getPiece(x, y) == null;
	}
	
	public boolean canCapture(int x, int y, boolean human) {
		return getPiece(x, y) != null && getPiece(x, y).isHuman() != human;
	}
	
	public boolean canCaptureOrMove(int x, int y, boolean human) {
		return canCapture(x, y, human) || isEmpty(x, y);
	}
	
	public static boolean inBounds(int x, int y) {
		return !(x < 0 || x > 7 || y < 0 || y > 7);
	}
		
}
