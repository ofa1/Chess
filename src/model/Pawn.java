package model;

import java.util.Scanner;

public class Pawn extends ChessMen {

	boolean promoted;
	ChessMen newPawn;
	public Pawn(String name, int x, int y, String color) {
		super(name, x, y, color);
		promoted = false;
	}

	@Override
	public void setName(String name) 
	{
		if(isPromoted())
		{
			newPawn.setName(name);
		}
		else
			super.setName(name);
	}
	
	@Override
	public String getName() 
	{
		if(isPromoted())
		{
			return newPawn.getName();
		}
		else
			return super.getName();
	}
	

	@Override
	public boolean validateMove(Data chessboard, ChessMen destination, Player player)
	{
		if(isPromoted())
		{
			//Do the new piece's moves and return
			return newPawn.validateMove(chessboard, destination, player);
		}
		int a = this.getPosition().getX();
		int b = this.getPosition().getY();
		
		int x = destination.getPosition().getX();
		int y = destination.getPosition().getY();
		
		if(player.getColor().startsWith("W"))
		{
			if(x <= a)
				return false;
			
			if((a == 2 && ((x-a == 1 || x-a == 2) && (y == b))) || (a == 2 && x-a == 1 && Math.abs(y-b) == 1) || (x-a == 1 && (y == b || Math.abs(y-b) == 1)))
			{
				if(x-a != 2)
				{
					
					if(Math.abs(y-b) == 1 && !destination.getColor().startsWith(player.getColor()) && !destination.getName().startsWith("--"))
					{
						return true;
					}
					else if(destination.getName().startsWith("--") && y == b)
					{
						return true;
					}
				}
				else
				{
					if(destination.getName().startsWith("--") && chessboard.getElement(a+1, b).getName().startsWith("--"))
					{
						return true;
					}
				}
			}
		}
		else
		{
			if(x >= a)
				return false;

			if((a == 7 && ((a-x == 1 || a-x == 2) && (y == b))) || (a == 7 && a-x == 1 && Math.abs(y-b) == 1) || (a-x == 1 && (y == b || Math.abs(y-b) == 1)))
			{
				if(a-x != 2)
				{
					if(Math.abs(y-b) == 1 && !destination.getColor().startsWith(player.getColor()) && !destination.getName().startsWith("--"))
					{
						return true;
					}
					else if(destination.getName().startsWith("--") && y == b)
					{
						return true;
					}
				}
				else
				{
					if(destination.getName().startsWith("--") && chessboard.getElement(x + 1, b).getName().startsWith("--"))
					{
						return true;
					}
				}
			}
		}

		return false;
	}
	
	@Override
	public void setPosition(Coordinates position)
	{
		super.setPosition(position);
		if(!isPromoted() && (position.getX() == 1 || position.getX() == 8))
		{
			pawnPromotion();
		}
	}
	
	
	@SuppressWarnings("resource")
	public void pawnPromotion()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Pawn Promotion: Which piece do you want(rook/knight/bishop/queen)");
		String newPiece = sc.next();
		newPiece = newPiece.toUpperCase();
//		System.out.println("You entered: " + newPiece + " coordinates are: " +this.getPosition());
		setPromoted(true);
		
		if(newPiece.startsWith("R"))
		{
			newPawn = new Rook("R", this.getPosition().getX(), this.getPosition().getY(), this.getColor());
		}
		else if(newPiece.startsWith("K"))
		{
			newPawn = new Knight("N", this.getPosition().getX(), this.getPosition().getY(), this.getColor());
		}
		else if(newPiece.startsWith("Q"))
		{
			newPawn = new Queen("Q", this.getPosition().getX(), this.getPosition().getY(), this.getColor());
		}
		else if(newPiece.startsWith("B"))
		{
			newPawn = new Bishop("B", this.getPosition().getX(), this.getPosition().getY(), this.getColor());
		}
		else
		{
			System.out.println("Invalid name.. You lost your chance to promote your pawn");
		}
	}
	
	public boolean isPromoted() {
		return promoted;
	}

	public void setPromoted(boolean promoted) {
		this.promoted = promoted;
	}
}
