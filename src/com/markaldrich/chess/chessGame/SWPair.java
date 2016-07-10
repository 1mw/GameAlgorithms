package com.markaldrich.chess.chessGame;

/**
 * Created by maste on 7/10/2016.
 */
public class SWPair {
	private int strengths;
	private int weaknesses;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		SWPair swPair = (SWPair) o;
		
		if (strengths != swPair.strengths) return false;
		return weaknesses == swPair.weaknesses;
		
	}
	
	@Override
	public int hashCode() {
		int result = strengths;
		result = 31 * result + weaknesses;
		return result;
	}
	
	@Override
	public String toString() {
		return "SWPair{" +
				"strengths=" + strengths +
				", weaknesses=" + weaknesses +
				'}';
	}
	
	public int getStrengths() {
		return strengths;
	}
	
	public void setStrengths(int strengths) {
		this.strengths = strengths;
	}
	
	public int getWeaknesses() {
		return weaknesses;
	}
	
	public void setWeaknesses(int weaknesses) {
		this.weaknesses = weaknesses;
	}
	
	public SWPair(int strengths, int weaknesses) {
		this.strengths = strengths;
		this.weaknesses = weaknesses;
	}
}
