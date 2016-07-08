package com.markaldrich.chess.chessGame;

import java.util.ArrayList;

/**
 * Created by maste on 7/2/2016.
 */
public enum ChessPiece {
	H_PAWN(10, true),
	H_KNIGHT(20, true),
	H_ROOK(20, true),
	H_BISHOP(20, true),
	H_KING(100, true),
	H_QUEEN(50, true),
	
	C_PAWN(10, false),
	C_KNIGHT(20, false),
	C_ROOK(20, false),
	C_BISHOP(20, false),
	C_KING(100, false),
	C_QUEEN(50, false);
	
	private final int value;
	private final boolean human;
	ChessPiece(int value, boolean human) {
		this.value = value;
		this.human = human;
	}
	
	@Override
	public String toString() {
		switch (this) {
			case H_PAWN:
				return "HPA";
			case H_KNIGHT:
				return "HKN";
			case H_ROOK:
				return "HRO";
			case H_BISHOP:
				return "HBI";
			case H_KING:
				return "HKI";
			case H_QUEEN:
				return "HQU";
			case C_PAWN:
				return "CPA";
			case C_KNIGHT:
				return "CKN";
			case C_ROOK:
				return "CRO";
			case C_BISHOP:
				return "CBI";
			case C_KING:
				return "CKI";
			case C_QUEEN:
				return "CQU";
		}
		
		return "This will never happen but I am just doing this to make Java happy.";
	}
	
	public ArrayList<Coordinate> getPossibleMoves(int x, int y, ChessBoard board) {
		ArrayList<Coordinate> moves = new ArrayList<>();
		
		switch (this) {
			case H_PAWN:
				// #
				// #
				if (ChessBoard.inBounds(x, y - 1) && board.isEmpty(x, y - 1)) {
					moves.add(new Coordinate(x, y - 1));
				}
				
				// #
				//  #
				if (ChessBoard.inBounds(x - 1, y - 1) && board.canCapture(x - 1, y - 1, human)) {
					moves.add(new Coordinate(x - 1, y - 1));
				}
				
				//  #
				// #
				if (ChessBoard.inBounds(x + 1, y - 1) && board.canCapture(x + 1, y - 1, human)) {
					moves.add(new Coordinate(x + 1, y - 1));
				}
				break;
			case C_PAWN:
				// #
				// #
				if (ChessBoard.inBounds(x, y + 1) && board.isEmpty(x, y + 1)) {
					moves.add(new Coordinate(x, y + 1));
				}
				
				//  #
				// #
				if (ChessBoard.inBounds(x - 1, y + 1) && board.canCapture(x - 1, y + 1, human)) {
					moves.add(new Coordinate(x - 1, y + 1));
				}
				
				// #
				//  #
				if (ChessBoard.inBounds(x + 1, y + 1) && board.canCapture(x + 1, y + 1, human)) {
					moves.add(new Coordinate(x + 1, y + 1));
				}
				break;
			case H_KNIGHT:
			case C_KNIGHT:
				// ##
				//  #
				//  #
				if (ChessBoard.inBounds(x - 1, y + 2) && board.canCaptureOrMove(x - 1, y + 2, human)) {
					moves.add(new Coordinate(x - 1, y + 2));
				}
				
				// ##
				// #
				// #
				if (ChessBoard.inBounds(x + 1, y + 2) && board.canCaptureOrMove(x + 1, y + 2, human)) {
					moves.add(new Coordinate(x + 1, y + 2));
				}
				
				//  #
				//  #
				// ##
				if (ChessBoard.inBounds(x - 1, y - 2) && board.canCaptureOrMove(x - 1, y - 2, human)) {
					moves.add(new Coordinate(x - 1, y - 2));
				}
				
				// #
				// #
				// ##
				if (ChessBoard.inBounds(x + 1, y - 2) && board.canCaptureOrMove(x + 1, y - 2, human)) {
					moves.add(new Coordinate(x + 1, y - 2));
				}
				
				// #
				// ###
				if (ChessBoard.inBounds(x - 2, y - 1) && board.canCaptureOrMove(x - 2, y - 1, human)) {
					moves.add(new Coordinate(x - 2, y - 1));
				}
				
				// ###
				// #
				if (ChessBoard.inBounds(x - 2, y + 1) && board.canCaptureOrMove(x - 2, y + 1, human)) {
					moves.add(new Coordinate(x - 2, y + 1));
				}
				
				//   #
				// ###
				if (ChessBoard.inBounds(x + 2, y - 1) && board.canCaptureOrMove(x + 2, y - 1, human)) {
					moves.add(new Coordinate(x + 2, y - 1));
				}
				
				// ###
				//   #
				if (ChessBoard.inBounds(x + 2, y + 1) && board.canCaptureOrMove(x + 2, y + 1, human)) {
					moves.add(new Coordinate(x + 2, y + 1));
				}
				break;
			case H_ROOK:
			case C_ROOK:
				// Moving ->
				for (int xx = x + 1; ChessBoard.inBounds(xx, y); xx++) {
					if (!board.isEmpty(xx, y) && board.getPiece(xx, y).human == human) {
						break;
					}
					moves.add(new Coordinate(xx, y));
					if (board.canCapture(xx, y, human)) {
						break;
					}
				}

				// Moving <-
				for (int xx = x - 1; ChessBoard.inBounds(xx, y); xx--) {
					if (!board.isEmpty(xx, y) && board.getPiece(xx, y).human == human) {
						break;
					}
					moves.add(new Coordinate(xx, y));
					if (board.canCapture(xx, y, human)) {
						break;
					}
				}
				
				// Moving /\
				for (int yy = y - 1; ChessBoard.inBounds(x, yy); yy--) {
					if (!board.isEmpty(x, yy) && board.getPiece(x, yy).human == human) {
						break;
					}
					moves.add(new Coordinate(x, yy));
					if (board.canCapture(x, yy, human)) {
						break;
					}
				}
				
				// Moving \/
				for (int yy = y + 1; ChessBoard.inBounds(x, yy); yy++) {
					if (!board.isEmpty(x, yy) && board.getPiece(x, yy).human == human) {
						break;
					}
					moves.add(new Coordinate(x, yy));
					if (board.canCapture(x, yy, human)) {
						break;
					}
				}
				break;
			case H_BISHOP:
			case C_BISHOP:
				// Moving
				// UP
				//  \
				for (int xx = x - 1, yy = y - 1; ChessBoard.inBounds(xx, yy); xx--, yy--) {
					if (!board.isEmpty(xx, yy) && board.getPiece(xx, yy).human == human) {
						break;
					}
					moves.add(new Coordinate(xx, yy));
					if (board.canCapture(xx, yy, human)) {
						break;
					}
				}
				
				// Moving
				//  UP
				// /
				for (int xx = x + 1, yy = y - 1; ChessBoard.inBounds(xx, yy); xx++, yy--) {
					if (!board.isEmpty(xx, yy) && board.getPiece(xx, yy).human == human) {
						break;
					}
					moves.add(new Coordinate(xx, yy));
					if (board.canCapture(xx, yy, human)) {
						break;
					}
				}
				
				// Moving
				//     /
				// DOWN
				for (int xx = x - 1, yy = y + 1; ChessBoard.inBounds(xx, yy); xx--, yy++) {
					if (!board.isEmpty(xx, yy) && board.getPiece(xx, yy).human == human) {
						break;
					}
					moves.add(new Coordinate(xx, yy));
					if (board.canCapture(xx, yy, human)) {
						break;
					}
				}
				
				// Moving
				// \
				//  DOWN
				for (int xx = x + 1, yy = y + 1; ChessBoard.inBounds(xx, yy); xx++, yy++) {
					if (!board.isEmpty(xx, yy) && board.getPiece(xx, yy).human == human) {
						break;
					}
					moves.add(new Coordinate(xx, yy));
					if (board.canCapture(xx, yy, human)) {
						break;
					}
				}
				break;
			case H_KING:
			case C_KING:
				//  *
				//  #
				// 
				if (ChessBoard.inBounds(x, y - 1) && board.canCaptureOrMove(x, y - 1, human)) {
					moves.add(new Coordinate(x, y - 1));
				}
				
				// * 
				//  #
				// 
				if (ChessBoard.inBounds(x - 1, y - 1) && board.canCaptureOrMove(x - 1, y - 1, human)) {
					moves.add(new Coordinate(x - 1, y - 1));
				}
				
				//  
				// *#
				// 
				if (ChessBoard.inBounds(x - 1, y) && board.canCaptureOrMove(x - 1, y, human)) {
					moves.add(new Coordinate(x - 1, y));
				}
				
				//  
				//  #
				// *
				if (ChessBoard.inBounds(x - 1, y + 1) && board.canCaptureOrMove(x - 1, y + 1, human)) {
					moves.add(new Coordinate(x - 1, y + 1));
				}
				
				//  
				//  #
				//  *
				if (ChessBoard.inBounds(x, y + 1) && board.canCaptureOrMove(x, y + 1, human)) {
					moves.add(new Coordinate(x, y + 1));
				}
				
				//  
				//  #
				//   *
				if (ChessBoard.inBounds(x + 1, y + 1) && board.canCaptureOrMove(x + 1, y + 1, human)) {
					moves.add(new Coordinate(x + 1, y + 1));
				}
				
				//  
				//  #*
				//   
				if (ChessBoard.inBounds(x + 1, y) && board.canCaptureOrMove(x + 1, y, human)) {
					moves.add(new Coordinate(x + 1, y));
				}
				
				//   *
				//  #
				//   
				if (ChessBoard.inBounds(x + 1, y - 1) && board.canCaptureOrMove(x + 1, y - 1, human)) {
					moves.add(new Coordinate(x + 1, y - 1));
				}
				break;
			case H_QUEEN:
			case C_QUEEN:
				// Moving ->
				for (int xx = x + 1; ChessBoard.inBounds(xx, y); xx++) {
					if (!board.isEmpty(xx, y) && board.getPiece(xx, y).human == human) {
						break;
					}
					moves.add(new Coordinate(xx, y));
					if (board.canCapture(xx, y, human)) {
						break;
					}
				}
				
				// Moving <-
				for (int xx = x - 1; ChessBoard.inBounds(xx, y); xx--) {
					if (!board.isEmpty(xx, y) && board.getPiece(xx, y).human == human) {
						break;
					}
					moves.add(new Coordinate(xx, y));
					if (board.canCapture(xx, y, human)) {
						break;
					}
				}
				
				// Moving /\
				for (int yy = y - 1; ChessBoard.inBounds(x, yy); yy--) {
					if (!board.isEmpty(x, yy) && board.getPiece(x, yy).human == human) {
						break;
					}
					moves.add(new Coordinate(x, yy));
					if (board.canCapture(x, yy, human)) {
						break;
					}
				}
				
				// Moving \/
				for (int yy = y + 1; ChessBoard.inBounds(x, yy); yy++) {
					if (!board.isEmpty(x, yy) && board.getPiece(x, yy).human == human) {
						break;
					}
					moves.add(new Coordinate(x, yy));
					if (board.canCapture(x, yy, human)) {
						break;
					}
				}
				
				// Moving
				// UP
				//  \
				for (int xx = x - 1, yy = y - 1; ChessBoard.inBounds(xx, yy); xx--, yy--) {
					if (!board.isEmpty(xx, yy) && board.getPiece(xx, yy).human == human) {
						break;
					}
					moves.add(new Coordinate(xx, yy));
					if (board.canCapture(xx, yy, human)) {
						break;
					}
				}
				
				// Moving
				//  UP
				// /
				for (int xx = x + 1, yy = y - 1; ChessBoard.inBounds(xx, yy); xx++, yy--) {
					if (!board.isEmpty(xx, yy) && board.getPiece(xx, yy).human == human) {
						break;
					}
					moves.add(new Coordinate(xx, yy));
					if (board.canCapture(xx, yy, human)) {
						break;
					}
				}
				
				// Moving
				//     /
				// DOWN
				for (int xx = x - 1, yy = y + 1; ChessBoard.inBounds(xx, yy); xx--, yy++) {
					if (!board.isEmpty(xx, yy) && board.getPiece(xx, yy).human == human) {
						break;
					}
					moves.add(new Coordinate(xx, yy));
					if (board.canCapture(xx, yy, human)) {
						break;
					}
				}
				
				// Moving
				// \
				//  DOWN
				for (int xx = x + 1, yy = y + 1; ChessBoard.inBounds(xx, yy); xx++, yy++) {
					if (!board.isEmpty(xx, yy) && board.getPiece(xx, yy).human == human) {
						break;
					}
					moves.add(new Coordinate(xx, yy));
					if (board.canCapture(xx, yy, human)) {
						break;
					}
				}
				break;
		}
		
		return moves;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean isHuman() {
		return human;
	}
}
