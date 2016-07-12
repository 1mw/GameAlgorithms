package com.markaldrich.chess.chessGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

/**
 * Created by maste on 7/2/2016.
 */
public class ChessComputerPlayer extends ChessPlayer {
	private volatile Random random;
	private int difficulty;
	
	private volatile ArrayList<ChessMove> possibleMoves = new ArrayList<>();
	private volatile int threads = 1;
	private final int maxThreads = Runtime.getRuntime().availableProcessors();
	
	public ChessComputerPlayer(ChessBoard board, int difficulty, long seed) {
		this.difficulty = difficulty;
		this.board = board;
		random = new Random(seed);
	}
	
	public ChessComputerPlayer(ChessBoard board, int difficulty) {
		this.difficulty = difficulty;
		this.board = board;
		random = new Random();
	}
	
	@Override
	public ChessMove move() {
		LinkedHashMap<ChessMove, Integer> moves = new LinkedHashMap<>();
		
		for (Coordinate coordinate : board.allComputerPieces()) {
			int x = coordinate.getX(), y = coordinate.getY();
			board.getPiece(x, y).getPossibleMoves(x, y, board)
					.forEach((c) -> possibleMoves.add(new ChessMove(x, y, c.getX(), c.getY())));
			
			for (ChessMove move : possibleMoves) {
				if (threads < maxThreads) {
					threads++;
					new Thread() {
						@Override
						public void run() {
							moves.put(move, favorabilityOfMove(move, board, 0, difficulty));
							threads--;
						}
					}.start();
				} else {
					moves.put(move, favorabilityOfMove(move, board, 0, difficulty));
				}
			}
			
			possibleMoves.clear();
		}
		
		int counter = 0;
		while (threads > 1) {
			if (counter == 1000) {
				System.out.println("threads=" + threads);
				System.out.println("moves.size()=" + moves.size());
				counter = 0;
			} else {
				counter++;
			}
		}
		
		Integer[] favors = moves.values().toArray(new Integer[0]);
		ChessMove[] movesToConsider = moves.keySet().toArray(new ChessMove[0]);
		ChessMove move = movesToConsider[0];
		int highestFavor = favors[0];
		
		for (int i = 1; i < favors.length; i++) {
			if (favors[i] > highestFavor) {
				highestFavor = favors[i];
				move = movesToConsider[i];
			}
		}
		
		System.out.println(move);
		System.out.println("CPU DONE MOVING");
		
		return move;
	}
	
	private SWPair favorabilityOfBoard(ChessBoard board) {
		int strengths = 0;
		int weaknesses = 0;
		
		for (Coordinate coordinate : board.allComputerPieces()) {
			strengths += calculateStrengths(coordinate, board, false);
		}
		
		for (Coordinate coordinate : board.allHumanPieces()) {
			weaknesses += calculateStrengths(coordinate, board, true);
		}
		
		return new SWPair(strengths, weaknesses);
	}
	
	
	private int calculateStrengths(Coordinate piece, ChessBoard board, boolean human) {
		int strengths = 0;
		int x = piece.getX(), y = piece.getY();
		for (Coordinate coordinate : board.getPiece(x, y).getPossibleMoves(x, y, board)) {
			if (board.canCapture(coordinate.getX(), coordinate.getY(), human)) {
				strengths += board.getPiece(coordinate.getX(), coordinate.getY()).getValue() 
						/ ((human) ? 2 : 3);
			}
		}
		
		return strengths;
	}
	
	private int favorabilityOfMove(ChessMove move, ChessBoard board, int level, int maxLevel) {
		ChessBoard virtualBoard = new ChessBoard(board);
		virtualBoard.doMove(move);
		
		SWPair sw = favorabilityOfBoard(virtualBoard);
		int strengths = sw.getStrengths();
		int weaknesses = sw.getWeaknesses();
		
		double adjustedStrengths = strengths * -log2(((double) level) / ((double) maxLevel));
		double adjustedWeaknesses = weaknesses * -log2(((double) level) / ((double) maxLevel * 1.5d));
		
		int favor = (int) (adjustedStrengths - adjustedWeaknesses);
		
		if (level < maxLevel) {
			int sum = 0;
			int count = 0;
			Coordinate newLocation = new Coordinate(move.getBx(), move.getBy());
			
			int x = newLocation.getX(), y = newLocation.getY();
			ArrayList<ChessMove> possibleMoves = new ArrayList<>();
			virtualBoard.getPiece(x, y).getPossibleMoves(x, y, virtualBoard)
					.forEach((c) -> possibleMoves.add(new ChessMove(x, y, c.getX(), c.getY())));
			for (ChessMove anotherMove : possibleMoves) {
				
				sum += favorabilityOfMove(anotherMove, virtualBoard, level + 1, maxLevel);
				count++;
			}
			
			int average = (count > 0) ? sum / count : 0;
			favor += average;
		}
		
		return favor;
	}
	
	private static double log2(double x) {
		return Math.log(x) / Math.log(2d);
	}
	
}
