package com.markaldrich.chess.chessGame;

import java.util.ArrayList;

/**
 * Created by maste on 7/2/2016.
 */
public enum ChessPiece {
	H_PAWN(10),
	H_KNIGHT(20),
	H_ROOK(20),
	H_BISHOP(20),
	H_KING(100),
	H_QUEEN(50),
	C_PAWN(10),
	C_KNIGHT(20),
	C_ROOK(20),
	C_BISHOP(20),
	C_KING(100),
	C_QUEEN(50);
	
	int value;
	ChessPiece(int value) {
		this.value = value;
	}
	
	ArrayList<RelativeCoordinate> getPossibleMoves(int x, int y, ChessBoard board) {
		ArrayList<RelativeCoordinate> moves = new ArrayList<>();
		
		switch (this) {
			case H_PAWN:
				// #
				// #
				if (ChessBoard.inBounds(x, y - 1) && board.getPiece(x, y - 1) == null) {
					moves.add(new RelativeCoordinate(x, y - 1));
				}
				// #
				//  #
				if (ChessBoard.inBounds(x - 1, y - 1) && board.getPiece(x - 1, y - 1) != null) {
					moves.add(new RelativeCoordinate(x - 1, y - 1));
				}
				
				//  #
				// #
				if (ChessBoard.inBounds(x + 1, y - 1) && board.getPiece(x + 1, y - 1) != null) {
					moves.add(new RelativeCoordinate(x + 1, y - 1));
				}
				break;
			case H_KNIGHT:
			case C_KNIGHT:
				// ##
				//  #
				//  #
				if (ChessBoard.inBounds(x - 1, y + 2)) {
					moves.add(new RelativeCoordinate(x - 1, y + 2));
				}
				
				// ##
				// #
				// #
				if (ChessBoard.inBounds(x + 1, y + 2)) {
					moves.add(new RelativeCoordinate(x + 1, y + 2));
				}
				
				//  #
				//  #
				// ##
				if (ChessBoard.inBounds(x - 1, y - 2)) {
					moves.add(new RelativeCoordinate(x - 1, y - 2));
				}
				
				// #
				// #
				// ##
				if (ChessBoard.inBounds(x + 1, y - 2)) {
					moves.add(new RelativeCoordinate(x + 1, y - 2));
				}
				
				// #
				// ###
				if (ChessBoard.inBounds(x - 2, y - 1)) {
					moves.add(new RelativeCoordinate(x - 2, y - 1));
				}
				
				// ###
				// #
				if (ChessBoard.inBounds(x - 2, y + 1)) {
					moves.add(new RelativeCoordinate(x - 2, y + 1));
				}
				
				//   #
				// ###
				if (ChessBoard.inBounds(x + 2, y - 1)) {
					moves.add(new RelativeCoordinate(x + 2, y - 1));
				}
				
				// ###
				//   #
				if (ChessBoard.inBounds(x + 2, y + 1)) {
					moves.add(new RelativeCoordinate(x + 2, y + 1));
				}
				break;
			case H_ROOK:
			case C_ROOK:
				// Moving ->
				for (int xx = x + 1; ChessBoard.inBounds(xx, y); xx++) {
					moves.add(new RelativeCoordinate(xx, y));
					if (board.getPiece(xx, y) != null) {
						break;
					}
				}

				// Moving <-
				for (int xx = x - 1; ChessBoard.inBounds(xx, y); xx--) {
					moves.add(new RelativeCoordinate(xx, y));
					if (board.getPiece(xx, y) != null) {
						break;
					}
				}
				
				// Moving /\
				for (int yy = y - 1; ChessBoard.inBounds(x, yy); yy--) {
					moves.add(new RelativeCoordinate(x, yy));
					if (board.getPiece(x, yy) != null) {
						break;
					}
				}
				
				// Moving \/
				for (int yy = y + 1; ChessBoard.inBounds(x, yy); yy++) {
					moves.add(new RelativeCoordinate(x, yy));
					if (board.getPiece(x, yy) != null) {
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
					moves.add(new RelativeCoordinate(xx, yy));
					if (board.getPiece(xx, yy) != null) {
						break;
					}
				}
				
				break;
			case H_KING:
				break;
			case H_QUEEN:
				break;
			case C_PAWN:
				break;
			case C_KING:
				break;
			case C_QUEEN:
				break;
		}
		
		return moves;
	}
	
	public static void main(String[] args) {
		//ChessPiece e = ChessPiece.H_BISHOP;
		//e.getPossibleMoves(0, 0);
	}
}
