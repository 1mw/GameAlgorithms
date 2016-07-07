package com.markaldrich.chess.chessGame;

/**
 * Created by maste on 7/3/2016.
 */
public class RelativeCoordinate {
	private int x, y;

	public RelativeCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		RelativeCoordinate that = (RelativeCoordinate) o;

		if (x != that.x) return false;
		return y == that.y;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}
}
