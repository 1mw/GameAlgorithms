package com.markaldrich.tictactoe;

/**
 * Created by maste on 7/2/2016.
 */
public abstract class TicTacToePlayer {
	private TicTacToeGame game;
	
	public abstract void move(int x, int y);
	
	public TicTacToeGame getGame() {
		return game;
	}
	
	public void setGame(TicTacToeGame game) {
		this.game = game;
	}
}
