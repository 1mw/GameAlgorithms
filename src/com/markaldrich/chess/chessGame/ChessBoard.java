package com.markaldrich.chess.chessGame;

import java.util.Arrays;

/**
 * Created by maste on 7/2/2016.
 */
public class ChessBoard {
	private ChessPiece[][] board;
	
	public ChessBoard() {
		board = new ChessPiece[8][8];
		
		setPiece(0, 0, ChessPiece.C_ROOK);
		setPiece(1, 0, ChessPiece.C_KNIGHT);
		setPiece(2, 0, ChessPiece.C_BISHOP);
		setPiece(3, 0, ChessPiece.C_KING);
		setPiece(4, 0, ChessPiece.C_QUEEN);
		setPiece(5, 0, ChessPiece.C_BISHOP);
		setPiece(6, 0, ChessPiece.C_KNIGHT);
		setPiece(7, 0, ChessPiece.C_ROOK);
		
		setPiece(0, 1, ChessPiece.C_PAWN);
		setPiece(1, 1, ChessPiece.C_PAWN);
		setPiece(2, 1, ChessPiece.C_PAWN);
		setPiece(3, 1, ChessPiece.C_PAWN);
		setPiece(4, 1, ChessPiece.C_PAWN);
		setPiece(5, 1, ChessPiece.C_PAWN);
		setPiece(6, 1, ChessPiece.C_PAWN);
		setPiece(7, 1, ChessPiece.C_PAWN);
		
		
		setPiece(0, 6, ChessPiece.H_PAWN);
		setPiece(1, 6, ChessPiece.H_PAWN);
		setPiece(2, 6, ChessPiece.H_PAWN);
		setPiece(3, 6, ChessPiece.H_PAWN);
		setPiece(4, 6, ChessPiece.H_PAWN);
		setPiece(5, 6, ChessPiece.H_PAWN);
		setPiece(6, 6, ChessPiece.H_PAWN);
		setPiece(7, 6, ChessPiece.H_PAWN);
		
		setPiece(0, 7, ChessPiece.H_ROOK);
		setPiece(1, 7, ChessPiece.H_KNIGHT);
		setPiece(2, 7, ChessPiece.H_BISHOP);
		setPiece(3, 7, ChessPiece.H_KING);
		setPiece(4, 7, ChessPiece.H_QUEEN);
		setPiece(5, 7, ChessPiece.H_BISHOP);
		setPiece(6, 7, ChessPiece.H_KNIGHT);
		setPiece(7, 7, ChessPiece.H_ROOK);
	}
	
	public ChessBoard(ChessBoard c) {
		board = new ChessPiece[8][8];
		// Manual shallow copy because I didn't want to deal with using clone() or System.arrayCopy() with
		// multidimensional arrays
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				setPiece(x, y, c.getPiece(x, y));
			}
		}
	}
	
	public void clear() {
		board = new ChessPiece[8][8];
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
	
	public boolean containsPiece(ChessPiece piece) {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (getPiece(x, y) == piece) {
					return true;
				}
			}
		}
		
		
		return false;
	}
	
	public boolean humanWon() {
		return containsPiece(ChessPiece.H_KING) && !containsPiece(ChessPiece.C_KING);
	}
	
	public boolean computerWon() {
		return containsPiece(ChessPiece.C_KING) && !containsPiece(ChessPiece.H_KING);
	}
	
	public void doMove(ChessMove move) {
		if (!move.isValidMove(this)) {
		 	return;
		}
		
		ChessPiece pieceToMove = getPiece(move.getAx(), move.getAy());
		setPiece(move.getAx(), move.getAy(), null);
		setPiece(move.getBx(), move.getBy(), pieceToMove);
	}
	
	@Override
	public String toString() {
		String ret = "\t\t\t\tX";
		ret += "\n\t0\t1\t2\t3\t4\t5\t6\t7";
		
		for (int y = 0; y < 8; y++) {
			ret += "\n" + y;
			for (int x = 0; x < 8; x++) {
				ChessPiece piece = getPiece(x, y);
				ret += "\t" + ((piece == null) ? "[]" : piece.toString());
			}
		}
		
		return ret;
	}
}
