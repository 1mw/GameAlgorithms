package com.markaldrich.chess.chessGame;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Created by maste on 7/6/2016.
 */
public class ChessBoardTest {
	@Test
	public void testInBounds1() {
		Assert.assertTrue(ChessBoard.inBounds(1, 1));
	}
	
	@Test
	public void testInBounds2() {
		Assert.assertFalse(ChessBoard.inBounds(-1, -1));
	}
	
	@Test
	public void testInBounds3() {
		Assert.assertTrue(ChessBoard.inBounds(4, 7));
	}
	
	@Test
	public void testInBounds4() {
		Assert.assertFalse(ChessBoard.inBounds(-4, 3));
	}
	
	@Test
	public void testCPawnMovement1() {
		ChessBoard b = new ChessBoard();
		b.clear();
		/*
		  	0	1	2	3	4	5	6	7
		 0	[]	[]	[]	[]	[]	[]	[]	[]
		 1	[]	CPA	[]	[]	[]	[]	[]	[]
		 2	HKN	[]	HPA	[]	[]	[]	[]	[]
		 */
		b.setPiece(1, 1, ChessPiece.C_PAWN);
		b.setPiece(0, 2, ChessPiece.H_KNIGHT);
		b.setPiece(2, 2, ChessPiece.H_PAWN);
		
		ArrayList<Coordinate> possibleMoves = b.getPiece(1, 1).getPossibleMoves(1, 1, b);
		
		// The CPA should be able to move in all three ways
		System.out.println("Possible moves generated {");
		for (int i = 0; i < possibleMoves.size(); i++) {
			System.out.println("" + i + ": " + possibleMoves.get(i).toString() + ",");
		}
		System.out.println("}");
		
		
		Assert.assertEquals(possibleMoves.size(), 3);
		
		// Possible moves should be to: {0, 2}, {1, 2}, {2, 2}
		Assert.assertTrue(possibleMoves.contains(new Coordinate(0, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(1, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(2, 2)));
	}
	
	@Test
	public void testCPawnMovement2() {
		ChessBoard b = new ChessBoard();
		b.clear();
		/*
		  	0	1	2	3	4	5	6	7
		 0	[]	[]	[]	[]	[]	[]	[]	[]
		 1	[]	CPA	[]	[]	[]	[]	[]	[]
		 2	HKN	HQU	CPA	[]	[]	[]	[]	[]
		 */
		b.setPiece(1, 1, ChessPiece.C_PAWN);
		b.setPiece(0, 2, ChessPiece.H_KNIGHT);
		b.setPiece(1, 2, ChessPiece.H_QUEEN);
		b.setPiece(2, 2, ChessPiece.C_PAWN);
		
		ArrayList<Coordinate> possibleMoves = b.getPiece(1, 1).getPossibleMoves(1, 1, b);
		
		// The CPA should only be able to capture the HKN at {0, 2}
		System.out.println("Possible moves generated {");
		for (int i = 0; i < possibleMoves.size(); i++) {
			System.out.println("" + i + ": " + possibleMoves.get(i).toString() + ",");
		}
		System.out.println("}");
		
		
		Assert.assertEquals(possibleMoves.size(), 1);
		
		// Possible moves should be to: {0, 2}
		Assert.assertTrue(possibleMoves.contains(new Coordinate(0, 2)));
	}
	
	@Test
	public void testHPawnMovement1() {
		ChessBoard b = new ChessBoard();
		b.clear();
		/*
		  	0	1	2	3	4	5	6	7
		 0	[]	[]	[]	[]	[]	[]	[]	[]
		 1	CKN	[]	CPA	[]	[]	[]	[]	[]
		 2	[]	HPA	[]	[]	[]	[]	[]	[]
		 */
		b.setPiece(0, 1, ChessPiece.C_KNIGHT);
		b.setPiece(1, 2, ChessPiece.H_PAWN);
		b.setPiece(2, 1, ChessPiece.C_PAWN);
		
		ArrayList<Coordinate> possibleMoves = b.getPiece(1, 2).getPossibleMoves(1, 2, b);
		
		// The HPA should be able to move in all three ways
		System.out.println("Possible moves generated {");
		for (int i = 0; i < possibleMoves.size(); i++) {
			System.out.println("" + i + ": " + possibleMoves.get(i).toString() + ",");
		}
		System.out.println("}");
		
		
		Assert.assertEquals(possibleMoves.size(), 3);
		
		// Possible moves should be to: {0, 1}, {1, 1}, {2, 1}
		Assert.assertTrue(possibleMoves.contains(new Coordinate(0, 1)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(1, 1)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(2, 1)));
	}
	
	@Test
	public void testHPawnMovement2() {
		ChessBoard b = new ChessBoard();
		b.clear();
		/*
		  	0	1	2	3	4	5	6	7
		 0	[]	[]	[]	[]	[]	[]	[]	[]
		 1	CKN	CQU	HPA	[]	[]	[]	[]	[]
		 2	[]	HPA	[]	[]	[]	[]	[]	[]
		 */
		b.setPiece(0, 1, ChessPiece.C_KNIGHT);
		b.setPiece(1, 1, ChessPiece.C_KNIGHT);
		b.setPiece(1, 2, ChessPiece.H_PAWN);
		b.setPiece(2, 1, ChessPiece.H_PAWN);
		
		ArrayList<Coordinate> possibleMoves = b.getPiece(1, 2).getPossibleMoves(1, 2, b);
		
		// The CPA should only be able to capture the CKN at {0, 1}
		System.out.println("Possible moves generated {");
		for (int i = 0; i < possibleMoves.size(); i++) {
			System.out.println("" + i + ": " + possibleMoves.get(i).toString() + ",");
		}
		System.out.println("}");
		
		
		Assert.assertEquals(possibleMoves.size(), 1);
		
		// Possible moves should be to: {0, 2}
		Assert.assertTrue(possibleMoves.contains(new Coordinate(0, 1)));
	}
	
	@Test
	public void testCRookMovement1() {
		ChessBoard b = new ChessBoard();
		b.clear();
		/*
		  	0	1	2	3	4	5	6	7
		 0	[]	[]	[]	CKN	[]	[]	[]	[]
		 1	[]	[]	[]	[]	[]	[]	[]	[]
		 2	[]	HPA	[]	CRO	[]	[]	[]	[]
		 3	[]	[]	[]	[]	[]	[]	[]	[]
		 4	[]	[]	[]	HKN	[]	[]	[]	[]
		 */
		b.setPiece(3, 0, ChessPiece.C_KNIGHT);
		b.setPiece(3, 2, ChessPiece.C_ROOK);
		b.setPiece(3, 4, ChessPiece.H_KNIGHT);
		b.setPiece(1, 2, ChessPiece.H_PAWN);
		
		ArrayList<Coordinate> possibleMoves = b.getPiece(3, 2).getPossibleMoves(3, 2, b);
		
		
		System.out.println("Possible moves generated {");
		for (int i = 0; i < possibleMoves.size(); i++) {
			System.out.println("" + i + ": " + possibleMoves.get(i).toString() + ",");
		}
		System.out.println("}");
		
		
		Assert.assertEquals(possibleMoves.size(), 9);
		
		// Possible moves should be to: {3, 1}, {2, 2}, {1, 2}, {4, 2}, {5, 2}, {6, 2}, {7, 2}, {3, 3}, {3, 4}   
		Assert.assertTrue(possibleMoves.contains(new Coordinate(3, 1)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(2, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(1, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(4, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(5, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(6, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(7, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(3, 3)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(3, 4)));
	}
	
	@Test
	public void testHRookMovement1() {
		ChessBoard b = new ChessBoard();
		b.clear();
		/*
		  	0	1	2	3	4	5	6	7
		 0	[]	[]	[]	HKN	[]	[]	[]	[]
		 1	[]	[]	[]	[]	[]	[]	[]	[]
		 2	[]	CPA	[]	HRO	[]	[]	[]	[]
		 3	[]	[]	[]	[]	[]	[]	[]	[]
		 4	[]	[]	[]	CKN	[]	[]	[]	[]
		 */
		b.setPiece(3, 0, ChessPiece.H_KNIGHT);
		b.setPiece(3, 2, ChessPiece.H_ROOK);
		b.setPiece(3, 4, ChessPiece.C_KNIGHT);
		b.setPiece(1, 2, ChessPiece.C_PAWN);
		
		ArrayList<Coordinate> possibleMoves = b.getPiece(3, 2).getPossibleMoves(3, 2, b);
		
		
		System.out.println("Possible moves generated {");
		for (int i = 0; i < possibleMoves.size(); i++) {
			System.out.println("" + i + ": " + possibleMoves.get(i).toString() + ",");
		}
		System.out.println("}");
		
		
		Assert.assertEquals(possibleMoves.size(), 9);
		
		// Possible moves should be to: {3, 1}, {2, 2}, {1, 2}, {4, 2}, {5, 2}, {6, 2}, {7, 2}, {3, 3}, {3, 4}   
		Assert.assertTrue(possibleMoves.contains(new Coordinate(3, 1)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(2, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(1, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(4, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(5, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(6, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(7, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(3, 3)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(3, 4)));
	}
	
	@Test
	public void testCKnightMovement1() {
		ChessBoard b = new ChessBoard();
		b.clear();
		/*
		  	0	1	2	3	4	5	6	7
		 0	[]	[]	[]	CKI	HKN	[]	[]	[]
		 1	[]	HPA	[]	[]	[]	[]	[]	[]
		 2	[]	[]	[]	CKN	[]	[]	[]	[]
		 3	[]	[]	[]	[]	[]	[]	[]	[]
		 4	[]	[]	CQU	HBI	[]	[]	[]	[]
		 */
		b.setPiece(3, 0, ChessPiece.C_KNIGHT);
		b.setPiece(4, 0, ChessPiece.H_KNIGHT);
		b.setPiece(1, 1, ChessPiece.H_PAWN);
		b.setPiece(3, 2, ChessPiece.C_KNIGHT);
		b.setPiece(2, 4, ChessPiece.C_QUEEN);
		b.setPiece(3, 4, ChessPiece.H_BISHOP);
		
		ArrayList<Coordinate> possibleMoves = b.getPiece(3, 2).getPossibleMoves(3, 2, b);
		
		
		System.out.println("Possible moves generated {");
		for (int i = 0; i < possibleMoves.size(); i++) {
			System.out.println("" + i + ": " + possibleMoves.get(i).toString() + ",");
		}
		System.out.println("}");
		
		
		Assert.assertEquals(possibleMoves.size(), 7);
		
		// Possible moves should be to: {2, 0}, {4, 0}, {5, 1}, {5, 3}, {4, 4}, {1, 3}, {1, 1} 
		Assert.assertTrue(possibleMoves.contains(new Coordinate(2, 0)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(4, 0)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(5, 1)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(5, 3)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(4, 4)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(1, 3)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(1, 1)));
	}
	
	@Test
	public void testHKnightMovement1() {
		ChessBoard b = new ChessBoard();
		b.clear();
		/*
		  	0	1	2	3	4	5	6	7
		 0	[]	[]	[]	HKI	CKN	[]	[]	[]
		 1	[]	CPA	[]	[]	[]	[]	[]	[]
		 2	[]	[]	[]	HKN	[]	[]	[]	[]
		 3	[]	[]	[]	[]	[]	[]	[]	[]
		 4	[]	[]	HQU	CBI	[]	[]	[]	[]
		 */
		b.setPiece(3, 0, ChessPiece.H_KNIGHT);
		b.setPiece(4, 0, ChessPiece.C_KNIGHT);
		b.setPiece(1, 1, ChessPiece.C_PAWN);
		b.setPiece(3, 2, ChessPiece.H_KNIGHT);
		b.setPiece(2, 4, ChessPiece.H_QUEEN);
		b.setPiece(3, 4, ChessPiece.C_BISHOP);
		
		ArrayList<Coordinate> possibleMoves = b.getPiece(3, 2).getPossibleMoves(3, 2, b);
		
		
		System.out.println("Possible moves generated {");
		for (int i = 0; i < possibleMoves.size(); i++) {
			System.out.println("" + i + ": " + possibleMoves.get(i).toString() + ",");
		}
		System.out.println("}");
		
		
		Assert.assertEquals(possibleMoves.size(), 7);
		
		// Possible moves should be to: {2, 0}, {4, 0}, {5, 1}, {5, 3}, {4, 4}, {1, 3}, {1, 1} 
		Assert.assertTrue(possibleMoves.contains(new Coordinate(2, 0)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(4, 0)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(5, 1)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(5, 3)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(4, 4)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(1, 3)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(1, 1)));
	}
	
	@Test
	public void testCBishopMovement1() {
		ChessBoard b = new ChessBoard();
		b.clear();
		/*
		  	0	1	2	3	4	5	6	7
		 0	[]	[]	[]	CKI	[]	HKN	[]	[]
		 1	[]	HPA	[]	[]	[]	[]	[]	[]
		 2	[]	[]	[]	CBI	[]	[]	[]	[]
		 3	[]	[]	[]	[]	HPA	[]	[]	[]
		 4	[]	CQU	[]	HBI	[]	[]	[]	[]
		 */
		b.setPiece(3, 0, ChessPiece.C_KNIGHT);
		b.setPiece(5, 0, ChessPiece.H_KNIGHT);
		b.setPiece(1, 1, ChessPiece.H_PAWN);
		b.setPiece(3, 2, ChessPiece.C_BISHOP);
		b.setPiece(4, 3, ChessPiece.H_PAWN);
		b.setPiece(1, 4, ChessPiece.C_QUEEN);
		b.setPiece(3, 4, ChessPiece.H_BISHOP);
		
		ArrayList<Coordinate> possibleMoves = b.getPiece(3, 2).getPossibleMoves(3, 2, b);
		
		
		System.out.println("Possible moves generated {");
		for (int i = 0; i < possibleMoves.size(); i++) {
			System.out.println("" + i + ": " + possibleMoves.get(i).toString() + ",");
		}
		System.out.println("}");
		
		
		Assert.assertEquals(possibleMoves.size(), 6);
		
		// Possible moves should be to: {2, 1}, {1, 0}, {4, 1}, {5, 0}, {4, 3}, {2, 3} 
		Assert.assertTrue(possibleMoves.contains(new Coordinate(2, 1)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(1, 0)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(4, 1)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(5, 0)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(4, 3)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(2, 3)));
	}
	
	@Test
	public void testHBishopMovement1() {
		ChessBoard b = new ChessBoard();
		b.clear();
		/*
		  	0	1	2	3	4	5	6	7
		 0	[]	[]	[]	HKI	[]	CKN	[]	[]
		 1	[]	CPA	[]	[]	[]	[]	[]	[]
		 2	[]	[]	[]	HBI	[]	[]	[]	[]
		 3	[]	[]	[]	[]	CPA	[]	[]	[]
		 4	[]	HQU	[]	CBI	[]	[]	[]	[]
		 */
		b.setPiece(3, 0, ChessPiece.H_KNIGHT);
		b.setPiece(5, 0, ChessPiece.C_KNIGHT);
		b.setPiece(1, 1, ChessPiece.C_PAWN);
		b.setPiece(3, 2, ChessPiece.H_BISHOP);
		b.setPiece(4, 3, ChessPiece.C_PAWN);
		b.setPiece(1, 4, ChessPiece.H_QUEEN);
		b.setPiece(3, 4, ChessPiece.C_BISHOP);
		
		ArrayList<Coordinate> possibleMoves = b.getPiece(3, 2).getPossibleMoves(3, 2, b);
		
		
		System.out.println("Possible moves generated {");
		for (int i = 0; i < possibleMoves.size(); i++) {
			System.out.println("" + i + ": " + possibleMoves.get(i).toString() + ",");
		}
		System.out.println("}");
		
		
		Assert.assertEquals(possibleMoves.size(), 6);
		
		// Possible moves should be to: {2, 1}, {1, 0}, {4, 1}, {5, 0}, {4, 3}, {2, 3} 
		Assert.assertTrue(possibleMoves.contains(new Coordinate(2, 1)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(1, 0)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(4, 1)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(5, 0)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(4, 3)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(2, 3)));
	}
	
	@Test
	public void testCKingMovement1() {
		ChessBoard b = new ChessBoard();
		b.clear();
		/*
		  	0	1	2	3	4	5	6	7
		 0	[]	[]	CRO	CKI	[]	[]	[]	[]
		 1	[]	[]	[]	HPA	CPA	[]	[]	[]
		 2	[]	[]	[]	[]	[]	[]	[]	[]
		 3	[]	[]	[]	[]	[]	[]	[]	[]
		 4	[]	[]	[]	[]	[]	[]	[]	[]
		 */
		b.setPiece(2, 0, ChessPiece.C_ROOK);
		b.setPiece(3, 0, ChessPiece.C_KING);
		b.setPiece(3, 1, ChessPiece.H_PAWN);
		b.setPiece(4, 1, ChessPiece.C_PAWN);
		
		ArrayList<Coordinate> possibleMoves = b.getPiece(3, 0).getPossibleMoves(3, 0, b);
		
		
		System.out.println("Possible moves generated {");
		for (int i = 0; i < possibleMoves.size(); i++) {
			System.out.println("" + i + ": " + possibleMoves.get(i).toString() + ",");
		}
		System.out.println("}");
		
		
		Assert.assertEquals(possibleMoves.size(), 3);
		
		// Possible moves should be to: {4, 0}, {2, 1}, {3, 1} 
		Assert.assertTrue(possibleMoves.contains(new Coordinate(4, 0)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(2, 1)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(3, 1)));
	}
	
	@Test
	public void testHKingMovement1() {
		ChessBoard b = new ChessBoard();
		b.clear();
		/*
		  	0	1	2	3	4	5	6	7
		 0	[]	[]	HRO	HKI	[]	[]	[]	[]
		 1	[]	[]	[]	CPA	HPA	[]	[]	[]
		 2	[]	[]	[]	[]	[]	[]	[]	[]
		 3	[]	[]	[]	[]	[]	[]	[]	[]
		 4	[]	[]	[]	[]	[]	[]	[]	[]
		 */
		b.setPiece(2, 0, ChessPiece.H_ROOK);
		b.setPiece(3, 0, ChessPiece.H_KING);
		b.setPiece(3, 1, ChessPiece.C_PAWN);
		b.setPiece(4, 1, ChessPiece.H_PAWN);
		
		ArrayList<Coordinate> possibleMoves = b.getPiece(3, 0).getPossibleMoves(3, 0, b);
		
		
		System.out.println("Possible moves generated {");
		for (int i = 0; i < possibleMoves.size(); i++) {
			System.out.println("" + i + ": " + possibleMoves.get(i).toString() + ",");
		}
		System.out.println("}");
		
		
		Assert.assertEquals(possibleMoves.size(), 3);
		
		// Possible moves should be to: {4, 0}, {2, 1}, {3, 1} 
		Assert.assertTrue(possibleMoves.contains(new Coordinate(4, 0)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(2, 1)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(3, 1)));
	}
	
	@Test
	public void testCQueenMovement1() {
		ChessBoard b = new ChessBoard();
		b.clear();
		/*
		  	0	1	2	3	4	5	6	7
		 0	[]	[]	[]	HKI	[]	CKN	[]	[]
		 1	[]	[]	[]	[]	[]	[]	[]	[]
		 2	[]	[]	[]	CQU	[]	[]	[]	[]
		 3	[]	[]	[]	[]	[]	[]	[]	[]
		 4	[]	CPA	[]	HPA	[]	HKN	[]	[]
		 */
		b.setPiece(3, 0, ChessPiece.H_KING);
		b.setPiece(5, 0, ChessPiece.C_KNIGHT);
		b.setPiece(3, 2, ChessPiece.C_QUEEN);
		b.setPiece(1, 4, ChessPiece.C_PAWN);
		b.setPiece(3, 4, ChessPiece.H_PAWN);
		b.setPiece(5, 4, ChessPiece.H_KNIGHT);
		
		ArrayList<Coordinate> possibleMoves = b.getPiece(3, 2).getPossibleMoves(3, 2, b);
		
		
		System.out.println("Possible moves generated {");
		for (int i = 0; i < possibleMoves.size(); i++) {
			System.out.println("" + i + ": " + possibleMoves.get(i).toString() + ",");
		}
		System.out.println("}");
		
		
		Assert.assertEquals(possibleMoves.size(), 17);
		
		// Possible moves should be to: {3, 0}, {3, 1}, {4, 1}, {4, 2}, {5, 2}, {6, 2}, {7, 2}, {4, 3}, {5, 4}, {3, 3}, 
		// {3, 4}, {2, 3}, {2, 2}, {1, 2}, {0, 2}, {2, 1}, {1, 0} 
		Assert.assertTrue(possibleMoves.contains(new Coordinate(3, 0)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(3, 1)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(4, 1)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(4, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(5, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(6, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(7, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(4, 3)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(5, 4)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(3, 3)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(3, 4)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(2, 3)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(2, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(1, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(0, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(2, 1)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(1, 0)));
	}
	
	@Test
	public void testHQueenMovement1() {
		ChessBoard b = new ChessBoard();
		b.clear();
		/*
		  	0	1	2	3	4	5	6	7
		 0	[]	[]	[]	CKI	[]	HKN	[]	[]
		 1	[]	[]	[]	[]	[]	[]	[]	[]
		 2	[]	[]	[]	HQU	[]	[]	[]	[]
		 3	[]	[]	[]	[]	[]	[]	[]	[]
		 4	[]	HPA	[]	CPA	[]	CKN	[]	[]
		 */
		b.setPiece(3, 0, ChessPiece.C_KING);
		b.setPiece(5, 0, ChessPiece.H_KNIGHT);
		b.setPiece(3, 2, ChessPiece.H_QUEEN);
		b.setPiece(1, 4, ChessPiece.H_PAWN);
		b.setPiece(3, 4, ChessPiece.C_PAWN);
		b.setPiece(5, 4, ChessPiece.C_KNIGHT);
		
		ArrayList<Coordinate> possibleMoves = b.getPiece(3, 2).getPossibleMoves(3, 2, b);
		
		
		System.out.println("Possible moves generated {");
		for (int i = 0; i < possibleMoves.size(); i++) {
			System.out.println("" + i + ": " + possibleMoves.get(i).toString() + ",");
		}
		System.out.println("}");
		
		
		Assert.assertEquals(possibleMoves.size(), 17);
		
		// Possible moves should be to: {3, 0}, {3, 1}, {4, 1}, {4, 2}, {5, 2}, {6, 2}, {7, 2}, {4, 3}, {5, 4}, {3, 3}, 
		// {3, 4}, {2, 3}, {2, 2}, {1, 2}, {0, 2}, {2, 1}, {1, 0} 
		Assert.assertTrue(possibleMoves.contains(new Coordinate(3, 0)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(3, 1)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(4, 1)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(4, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(5, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(6, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(7, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(4, 3)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(5, 4)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(3, 3)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(3, 4)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(2, 3)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(2, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(1, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(0, 2)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(2, 1)));
		Assert.assertTrue(possibleMoves.contains(new Coordinate(1, 0)));
	}
	
	@Test
	public void testDoMove() {
		ChessBoard b = new ChessBoard();
		b.clear();
		
		/*
		  	0	1	2	3	4	5	6	7
		 0	[]	[]	[]	[]	[]	[]	[]	[]
		 1	[]	CPA	[]	[]	[]	[]	[]	[]
		 2	HKN	[]	HPA	[]	[]	[]	[]	[]
		 */
		b.setPiece(1, 1, ChessPiece.C_PAWN);
		b.setPiece(0, 2, ChessPiece.H_KNIGHT);
		b.setPiece(2, 2, ChessPiece.H_PAWN);
		
		// CPA to capture HKN
		ChessMove move = new ChessMove(1, 1, 0, 2);
		
		b.doMove(move);
		
		System.out.println(b.toString());
		
		Assert.assertNull(b.getPiece(1, 1));
		Assert.assertEquals(b.getPiece(0, 2), ChessPiece.C_PAWN);
	}
}