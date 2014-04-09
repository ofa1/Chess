package model;

public class Queen extends ChessMen {

	public Queen(String name, int x, int y, String color) {
		super(name, x, y, color);
	}

	@Override
	public boolean validateMove(Data chessboard, ChessMen destination, Player player) 
	{
		if((new Rook(this.getName(), this.getPosition().getX(), this.getPosition().getY(), this.getColor())).validateMove(chessboard, destination, player))
			return true;
		
		if((new Bishop(this.getName(), this.getPosition().getX(), this.getPosition().getY(), this.getColor())).validateMove(chessboard, destination, player))
			return true;
		
		return false;
	}



}
