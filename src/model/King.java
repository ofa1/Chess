package model;

import controller.Game;

public class King extends ChessMen
{
	private boolean moved;

	public King(String name, int x, int y, String color)
	{
		super(name, x, y, color);
		setMoved(false);
	}

	@Override
	public boolean validateMove(Data chessboard, ChessMen destination, Player player) 
	{
		int a = this.getPosition().getX();
		int b = this.getPosition().getY();

		int x = destination.getPosition().getX();
		int y = destination.getPosition().getY();

		if(Math.abs(y - b) == 2 && x == a)	//check for special move castling
		{
			return castling(chessboard, destination, player);
		}

		if(
				((Math.abs(a-x) == 1) && (Math.abs(b-y) == 1 )) //Moving Diagonally
				|| 
				((Math.abs(a-x) == 1 && Math.abs(b-y) == 0) //Moving in same column
				|| 
				(Math.abs(a-x) == 0 && Math.abs(b-y) == 1) ))	//Moving in same row
		{
			//valid move
			if(destination.getName().equalsIgnoreCase("--"))
			{
				//Empty position, free to move
				return true;
			}
			else if(destination.getColor().startsWith("B"))
			{
				//Moving over black piece
				if(!player.getColor().startsWith("B"))
				{
					//Kill white piece
					return true;
				}
			}
			else if(destination.getColor().startsWith("W"))
			{
				//Moving over white piece
				if(!player.getColor().startsWith("W"))
				{
					//Kill black piece
					return true;
				}
			}
		}
		//else invalid move
		return false;
	}

	private boolean castling(Data chessboard, ChessMen destination, Player player)
	{
		String rook = "R";

		
		int a = this.getPosition().getX();
		int b = this.getPosition().getY();
		int x = destination.getPosition().getX();
		int y = destination.getPosition().getY();

		if(y > b)
			rook += "2";
		else
			rook += "1";
		{
			Coordinates co = chessboard.getPosition(new Rook(rook, 0, 0, this.getColor()));
			if(!(chessboard.getElement(co.getX(), co.getY()) instanceof Rook))
				return false;
			Rook roo = (Rook) chessboard.getElement(co.getX(), co.getY());
			int p = roo.getPosition().getX();
			int q = roo.getPosition().getY();
			if(!this.isMoved() && !roo.isMoved())				//1) Both pieces did not move
			{
				if(p == x)															//4) Both king and rook are on the same rank
				{
					for(int i = Math.min(b, q) + 1; i< Math.max(b, q); i++)
					{
						if(!chessboard.getElement(a, i).getName().startsWith("--"))			//2) No pieces between rook and king
							return false;
					}

					//Creating hypothetical game to analyze moves
					Game g = new Game(new Data(chessboard.getArray(), chessboard.getPositions()), player);
					for(int i = Math.min(b, y); i <= Math.max(b, y); i++)
					{
						if(g.movePiece(this, a, i-1, a, i))
						{
							if(g.checkCheck(player))				//3) No check in between
								return false;
						}
					}
					
					if(chessboard.movePiece(roo, p, q, p, b>y?(y+1):(b+1)))
						return true;
				}
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
