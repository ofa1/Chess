package model;

/**
 * Blank on the board. Does nothing.
 */
public class Blank extends ChessMen {

	public Blank(String name, int x, int y, String color) {
		super(name, x, y, color);
	}

	@Override
	public boolean validateMove(Data chessboard, ChessMen destination, Player player) {
		return false;
	}

}
