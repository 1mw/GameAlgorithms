package com.markaldrich.chess.chessGame;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

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
}