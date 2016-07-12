package com.markaldrich.chess;

import com.markaldrich.chess.chessGame.*;
import com.markaldrich.jgl.JGLGame;
import com.markaldrich.jgl.JGLGameController;
import com.markaldrich.jgl.JGLGameProperties;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

/**
 * Created by maste on 7/10/2016.
 */
public class GUI extends JGLGame implements MouseListener {
	
	private volatile ChessHumanPlayer human;
	private volatile ChessComputerPlayer computer;
	private volatile Scanner scanner;
	private volatile ChessBoard currentBoard;
	
	private volatile JGLGameProperties props;
	
	private volatile boolean selectFirst = true;
	private volatile Coordinate selected = null;
	
	private volatile Coordinate lastMove1 = null;
	private volatile Coordinate lastMove2 = null;
	
	public GUI(JGLGameProperties props) {
		super(props);
		this.props = props;
	}
	
	@Override
	public void initGame() {
		currentBoard = new ChessBoard();
		scanner = new Scanner(System.in);
		human = new ChessHumanPlayer(currentBoard, scanner);
		computer = new ChessComputerPlayer(currentBoard, 5, 123L);
		addMouseListener(this);
	}
	
	@Override
	public void render(Graphics2D g) {
		for (int y = 0; y < 8; y++) {
			boolean white = (y % 2 == 0);
			for (int x = 0; x < 8; x++) {
				if ((lastMove1 != null && lastMove1.getX() == x && lastMove1.getY() == y)
						|| (lastMove2 != null && lastMove2.getX() == x && lastMove2.getY() == y)) {
					g.setColor(Color.GREEN);
				} else {
					g.setColor((white) ? Color.WHITE : Color.BLACK);
				}
				
				g.fillRect(x * 100, y * 100, 100, 100);
				
				// Invert color for drawing text and for next square
				white = !white;
				
				g.setColor((white) ? Color.WHITE : Color.BLACK);
				g.setFont(new Font("Courier New", Font.PLAIN, 50));
				ChessPiece piece = currentBoard.getPiece(x, y);
				if (piece != null)
					g.drawString(piece.toString(), x * 100, y * 100 + 50);
			}
		}
	}
	
	@Override
	public void update() {
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("CLICKED");
		int x = e.getX() / 100;
		int y = e.getY() / 100;
		
		System.out.println("x=" + x + " y=" + y);
		
		if (selectFirst) {
			selected = new Coordinate(x, y);
			selectFirst = false;
		} else {
			ChessMove move = new ChessMove(selected.getX(), selected.getY(), x, y);
			
			if (move.isValidMove(currentBoard)) {
				currentBoard.doMove(move);
				
				if (currentBoard.humanWon()) {
					System.out.println("Human was won!");
					return;
				}
				if (currentBoard.computerWon()) {
					System.out.println("Computer has won!");
					return;
				}
				
				new Thread() {
					@Override
					public void run() {
						ChessMove computerMove = computer.move();
						currentBoard.doMove(computerMove);
						
						if (currentBoard.humanWon()) {
							System.out.println("Human was won!");
							return;
						}
						if (currentBoard.computerWon()) {
							System.out.println("Computer has won!");
							return;
						}
						
						lastMove1 = new Coordinate(computerMove.getAx(), computerMove.getAy());
						lastMove2 = new Coordinate(computerMove.getBx(), computerMove.getBy());
						selectFirst = true;
						Toolkit.getDefaultToolkit().beep();
					}
				}.start();
			} else {
				System.out.println("Invalid move!");
				selectFirst = true;
			}
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	public static void main(String[] args) {
		JGLGameProperties props = new JGLGameProperties("Chess!", 800, 800);
		JGLGameController.startGame(new GUI(props), props);
	}
}
