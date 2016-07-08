package com.markaldrich.chess;

import com.markaldrich.chess.chessGame.*;

import java.util.Scanner;

/**
 * Created by maste on 7/2/2016.
 */
public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ChessGame game = new ChessGame(scanner);
		game.start();
	}
}
