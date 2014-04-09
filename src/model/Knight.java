package model;

public class Knight extends ChessMen {

	public Knight(String name, int x, int y, String color) {
		super(name, x, y, color);
	}

	@Override
	public boolean validateMove(Data chessboard, ChessMen destination, Player player) {
		int a = this.getPosition().getX();
		int b = this.getPosition().getY();
		
		int x = destination.getPosition().getX();
		int y = destination.getPosition().getY();
		
		if(
				(x == a-2 && (y == b-1 || y == b+1)) 
				|| 
				(x == a-1 && (y == b-2 || y == b+2)) 
				|| 
				(x == a+1 && (y == b-2 || y == b+2))	
				|| 
				(x == a+2 && (y == b-1 || y == b+1))
				)
		{
			return true;
		}
		return false;
	}

}
