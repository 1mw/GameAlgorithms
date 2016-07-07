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
	
	private int value;
	private boolean human;
	ChessPiece(int value, boolean human) {
		this.value = value;
		this.human = human;
	}
	
	ArrayList<RelativeCoordinate> getPossibleMoves(int x, int y, ChessBoard board) {
		ArrayList<RelativeCoordinate> moves = new ArrayList<>();
		
		switch (this) {
			case H_PAWN:
				// #
				// #
				if (ChessBoard.inBounds(x, y - 1) && board.isEmpty(x, y - 1)) {
					moves.add(new RelativeCoordinate(x, y - 1));
				}
				
				// #
				//  #
				if (ChessBoard.inBounds(x - 1, y - 1) && board.canCapture(x - 1, y - 1, human)) {
					moves.add(new RelativeCoordinate(x - 1, y - 1));
				}
				
				//  #
				// #
				if (ChessBoard.inBounds(x + 1, y - 1) && board.canCapture(x + 1, y - 1, human)) {
					moves.add(new RelativeCoordinate(x + 1, y - 1));
				}
				break;
			case C_PAWN:
				// #
				// #
				if (ChessBoard.inBounds(x, y + 1) && board.isEmpty(x, y + 1)) {
					moves.add(new RelativeCoordinate(x, y + 1));
				}
				
				//  #
				// #
				if (ChessBoard.inBounds(x - 1, y + 1) && board.canCapture(x - 1, y + 1, human)) {
					moves.add(new RelativeCoordinate(x - 1, y + 1));
				}
				
				// #
				//  #
				if (ChessBoard.inBounds(x + 1, y + 1) && board.canCapture(x + 1, y + 1, human)) {
					moves.add(new RelativeCoordinate(x + 1, y + 1));
				}
				break;
			case H_KNIGHT:
			case C_KNIGHT:
				// ##
				//  #
				//  #
				if (ChessBoard.inBounds(x - 1, y + 2) && board.canCaptureOrMove(x - 1, y + 2, human)) {
					moves.add(new RelativeCoordinate(x - 1, y + 2));
				}
				
				// ##
				// #
				// #
				if (ChessBoard.inBounds(x + 1, y + 2) && board.canCaptureOrMove(x + 1, y + 2, human)) {
					moves.add(new RelativeCoordinate(x + 1, y + 2));
				}
				
				//  #
				//  #
				// ##
				if (ChessBoard.inBounds(x - 1, y - 2) && board.canCaptureOrMove(x - 1, y - 2, human)) {
					moves.add(new RelativeCoordinate(x - 1, y - 2));
				}
				
				// #
				// #
				// ##
				if (ChessBoard.inBounds(x + 1, y - 2) && board.canCaptureOrMove(x + 1, y - 2, human)) {
					moves.add(new RelativeCoordinate(x + 1, y - 2));
				}
				
				// #
				// ###
				if (ChessBoard.inBounds(x - 2, y - 1) && board.canCaptureOrMove(x - 2, y - 1, human)) {
					moves.add(new RelativeCoordinate(x - 2, y - 1));
				}
				
				// ###
				// #
				if (ChessBoard.inBounds(x - 2, y + 1) && board.canCaptureOrMove(x - 2, y + 1, human)) {
					moves.add(new RelativeCoordinate(x - 2, y + 1));
				}
				
				//   #
				// ###
				if (ChessBoard.inBounds(x + 2, y - 1) && board.canCaptureOrMove(x + 2, y - 1, human)) {
					moves.add(new RelativeCoordinate(x + 2, y - 1));
				}
				
				// ###
				//   #
				if (ChessBoard.inBounds(x + 2, y + 1) && board.canCaptureOrMove(x + 2, y + 1, human)) {
					moves.add(new RelativeCoordinate(x + 2, y + 1));
				}
				break;
			case H_ROOK:
			case C_ROOK:
				// Moving ->
				for (int xx = x + 1; ChessBoard.inBounds(xx, y); xx++) {
					if (board.getPiece(xx, y).human == human) {
						break;
					}
					moves.add(new RelativeCoordinate(xx, y));
					if (board.canCapture(xx, y, human)) {
						break;
					}
				}

				// Moving <-
				for (int xx = x - 1; ChessBoard.inBounds(xx, y); xx--) {
					if (board.getPiece(xx, y).human == human) {
						break;
					}
					moves.add(new RelativeCoordinate(xx, y));
					if (board.canCapture(xx, y, human)) {
						break;
					}
				}
				
				// Moving /\
				for (int yy = y - 1; ChessBoard.inBounds(x, yy); yy--) {
					if (board.getPiece(x, yy).human == human) {
						break;
					}
					moves.add(new RelativeCoordinate(x, yy));
					if (board.canCapture(x, yy, human)) {
						break;
					}
				}
				
				// Moving \/
				for (int yy = y + 1; ChessBoard.inBounds(x, yy); yy++) {
					if (board.getPiece(x, yy).human == human) {
						break;
					}
					moves.add(new RelativeCoordinate(x, yy));
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
					if (board.getPiece(xx, yy).human == human) {
						break;
					}
					moves.add(new RelativeCoordinate(xx, yy));
					if (board.canCapture(xx, yy, human)) {
						break;
					}
				}
				
				// Moving
				//  UP
				// /
				for (int xx = x + 1, yy = y - 1; ChessBoard.inBounds(xx, yy); xx++, yy--) {
					if (board.getPiece(xx, yy).human == human) {
						break;
					}
					moves.add(new RelativeCoordinate(xx, yy));
					if (board.canCapture(xx, yy, human)) {
						break;
					}
				}
				
				// Moving
				//     /
				// DOWN
				for (int xx = x - 1, yy = y + 1; ChessBoard.inBounds(xx, yy); xx--, yy++) {
					if (board.getPiece(xx, yy).human == human) {
						break;
					}
					moves.add(new RelativeCoordinate(xx, yy));
					if (board.canCapture(xx, yy, human)) {
						break;
					}
				}
				
				// Moving
				// \
				//  DOWN
				for (int xx = x + 1, yy = y + 1; ChessBoard.inBounds(xx, yy); xx++, yy++) {
					if (board.getPiece(xx, yy).human == human) {
						break;
					}
					moves.add(new RelativeCoordinate(xx, yy));
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
					moves.add(new RelativeCoordinate(x, y - 1));
				}
				
				// * 
				//  #
				// 
				if (ChessBoard.inBounds(x - 1, y - 1) && board.canCaptureOrMove(x - 1, y - 1, human)) {
					moves.add(new RelativeCoordinate(x - 1, y - 1));
				}
				
				//  
				// *#
				// 
				if (ChessBoard.inBounds(x - 1, y) && board.canCaptureOrMove(x - 1, y, human)) {
					moves.add(new RelativeCoordinate(x - 1, y));
				}
				
				//  
				//  #
				// *
				if (ChessBoard.inBounds(x - 1, y + 1) && board.canCaptureOrMove(x - 1, y + 1, human)) {
					moves.add(new RelativeCoordinate(x - 1, y + 1));
				}
				
				//  
				//  #
				//  *
				if (ChessBoard.inBounds(x, y + 1) && board.canCaptureOrMove(x, y + 1, human)) {
					moves.add(new RelativeCoordinate(x, y + 1));
				}
				
				//  
				//  #
				//   *
				if (ChessBoard.inBounds(x + 1, y + 1) && board.canCaptureOrMove(x + 1, y + 1, human)) {
					moves.add(new RelativeCoordinate(x + 1, y + 1));
				}
				
				//  
				//  #*
				//   
				if (ChessBoard.inBounds(x + 1, y) && board.canCaptureOrMove(x + 1, y, human)) {
					moves.add(new RelativeCoordinate(x + 1, y));
				}
				
				//   *
				//  #
				//   
				if (ChessBoard.inBounds(x + 1, y - 1) && board.canCaptureOrMove(x + 1, y - 1, human)) {
					moves.add(new RelativeCoordinate(x + 1, y - 1));
				}
				break;
			case H_QUEEN:
			case C_QUEEN:
				// Moving
				// UP
				//  \
				for (int xx = x - 1, yy = y - 1; ChessBoard.inBounds(xx, yy); xx--, yy--) {
					if (board.getPiece(xx, yy).human == human) {
						break;
					}
					moves.add(new RelativeCoordinate(xx, yy));
					if (board.canCapture(xx, yy, human)) {
						break;
					}
				}
				
				// Moving
				//  UP
				// /
				for (int xx = x + 1, yy = y - 1; ChessBoard.inBounds(xx, yy); xx++, yy--) {
					if (board.getPiece(xx, yy).human == human) {
						break;
					}
					moves.add(new RelativeCoordinate(xx, yy));
					if (board.canCapture(xx, yy, human)) {
						break;
					}
				}
				
				// Moving
				//     /
				// DOWN
				for (int xx = x - 1, yy = y + 1; ChessBoard.inBounds(xx, yy); xx--, yy++) {
					if (board.getPiece(xx, yy).human == human) {
						break;
					}
					moves.add(new RelativeCoordinate(xx, yy));
					if (board.canCapture(xx, yy, human)) {
						break;
					}
				}
				
				// Moving
				// \
				//  DOWN
				for (int xx = x + 1, yy = y + 1; ChessBoard.inBounds(xx, yy); xx++, yy++) {
					if (board.getPiece(xx, yy).human == human) {
						break;
					}
					moves.add(new RelativeCoordinate(xx, yy));
					if (board.canCapture(xx, yy, human)) {
						break;
					}
				}
				
				// Moving ->
				for (int xx = x + 1; ChessBoard.inBounds(xx, y); xx++) {
					if (board.getPiece(xx, y).human == human) {
						break;
					}
					moves.add(new RelativeCoordinate(xx, y));
					if (board.canCapture(xx, y, human)) {
						break;
					}
				}
				
				// Moving <-
				for (int xx = x - 1; ChessBoard.inBounds(xx, y); xx--) {
					if (board.getPiece(xx, y).human == human) {
						break;
					}
					moves.add(new RelativeCoordinate(xx, y));
					if (board.canCapture(xx, y, human)) {
						break;
					}
				}
				
				// Moving /\
				for (int yy = y - 1; ChessBoard.inBounds(x, yy); yy--) {
					if (board.getPiece(x, yy).human == human) {
						break;
					}
					moves.add(new RelativeCoordinate(x, yy));
					if (board.canCapture(x, yy, human)) {
						break;
					}
				}
				
				// Moving \/
				for (int yy = y + 1; ChessBoard.inBounds(x, yy); yy++) {
					if (board.getPiece(x, yy).human == human) {
						break;
					}
					moves.add(new RelativeCoordinate(x, yy));
					if (board.canCapture(x, yy, human)) {
						break;
					}
				}
				break;
		}
		
		return moves;
	}
	
	public static void main(String[] args) {
		//ChessPiece e = ChessPiece.H_BISHOP;
		//e.getPossibleMoves(0, 0);
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public boolean isHuman() {
		return human;
	}
	
	public void setHuman(boolean human) {
		this.human = human;
	}
}
