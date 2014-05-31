package model;

/**
 * Bishop class - inherits from ChessMen class
 */
public class Bishop extends ChessMen {

	public Bishop(String name, int x, int y, String color) {
		super(name, x, y, color);
	}

	@Override
	public boolean validateMove(Data chessboard, ChessMen destination, Player player)
	{
		int a = this.getPosition().getX();
		int b = this.getPosition().getY();
		
		int x = destination.getPosition().getX();
		int y = destination.getPosition().getY();
		
		if((Math.abs(a-x) == Math.abs(b-y))) //moving diagonally
		{
			if(a < x && b < y) //E.g.: 1,1 to 3,3
			{
				for (int i = a + 1, j = b + 1; i < x && j < y; i++, j++) 
				{
					if (!chessboard.getElement(i, j).getName().equalsIgnoreCase("--"))
						return false;
				}
			}
			else if(a > x && b > y) //E.g.: 3,3 to 1,1
			{
				for (int i = x+1, j = y+1; i < a && j < b; i++, j++) 
				{
					if (!chessboard.getElement(i, j).getName().equalsIgnoreCase("--"))
						return false;
				}
			}
			else if(a < x && b > y) //E.g.: 1,3 to 3,1
			{
				for (int i = a+1, j = b-1; i < x && j > y; i++, j--) 
				{
					if (!chessboard.getElement(i, j).getName().equalsIgnoreCase("--"))
						return false;
				}
			}
			else if(a > x && b < y) //E.g.: 3,1 to 1,3
			{
				for (int i = x-1, j = y+1; i > a && j < b; i--, j++) 
				{
					if (!chessboard.getElement(i, j).getName().equalsIgnoreCase("--"))
						return false;
				}
			}
			return true;
		}
		return false;
	}

}
