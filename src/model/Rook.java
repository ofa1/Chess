package model;

public class Rook extends ChessMen {
	
	private boolean moved;

	public Rook(String name, int x, int y, String color) {
		super(name, x, y, color);
		setMoved(false);
	}

	@Override
	public	boolean validateMove(Data chessboard, ChessMen destination, Player player)
	{
		int a = this.getPosition().getX();
		int b = this.getPosition().getY();
		int x = destination.getPosition().getX();
		int y = destination.getPosition().getY();
		
		if(x == a)	//Moving in same column
		{
			if(y > b)	//1,1 to 1,3
			{
				for(int i = b+1; i < y; i++)
				{
					if(!(chessboard.getElement(a, i).getName().startsWith("--")))
					{
						return false;
					}
				}
				return true;
			}
			else if(y < b) //1,3 to 1,1
			{
				for(int i = y + 1; i < b; i++)
				{
					if(!(chessboard.getElement(a, i).getName().startsWith("--")))
					{
						return false;
					}
				}
				return true;
			}
		}
		else if(y == b)	//Moving in different columns
		{
			if(x > a) //1, 1 to 3,1
			{
				for(int i = a+1; i < x; i++)
				{
					if(!(chessboard.getElement(i, b).getName().startsWith("--")))
					{
						return false;
					}
				}
				return true;
			}
			else if(x < a) //3,1 to 1,1
			{
				for(int i = x+1; i < a; i++)
				{
					if(!(chessboard.getElement(i, b).getName().startsWith("--")))
					{
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}
}
