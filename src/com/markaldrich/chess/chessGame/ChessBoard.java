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
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			return null;
		}
		return board[y][x];
	}
	
	public static boolean inBounds(int x, int y) {
		return !(x < 0 || x > 7 || y < 0 || y > 7);
	}
	
	public static void main(String[] args) {
		System.out.println("Test inBounds");
		System.out.println((inBounds(1, 1)) ? "Test 1 Passed" : "Test 1 Failed");
		System.out.println((!inBounds(-1, -1)) ? "Test 2 Passed" : "Test 2 Failed");
		System.out.println((inBounds(4, 7)) ? "Test 3 Passed" : "Test 3 Failed");
		System.out.println((!inBounds(-4, 3)) ? "Test 4 Passed" : "Test 4 Failed");
		// System.out.println(() ? "Test # Passed" : "Test # Failed");
	}
		
}
