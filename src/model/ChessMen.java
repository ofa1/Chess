/**
 * 
 */
package model;

import java.util.ArrayList;

public class ChessMen 
{
	private String name;
	private Coordinates position;
	private String color;

	public boolean validateMove(Data chessboard, ChessMen destination, Player player)
	{
		return false;
	}
	
	public ChessMen(String name, String color)
	{
		this.name = name;
		this.color = color;
	}
	
	public ChessMen(String name, int x, int y, String color)
	{
		ChessMen(name, color);
		this.position = new Coordinates(x, y);
	}
	
	public String toString() {
		return getColor().charAt(0)+getName() ;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof ChessMen))
			return false;
		ChessMen c = (ChessMen) obj;
		return this.getName().trim().equalsIgnoreCase(c.getName().trim()) && this.getColor().trim().equalsIgnoreCase(c.getColor().trim());
		
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	public Coordinates getPosition() {
		return position;
	}
	public void setPosition(Coordinates position) {
		this.position = position;
	}
	
	@Override
	public int hashCode() {
		return (this.getName().trim() + this.getColor().trim()).hashCode();
	}
	
	public ArrayList<Coordinates> getAllPossiblePositions(Data chessboard, Player player)
	{
		ArrayList<Coordinates> allPositions = new ArrayList<Coordinates>();

		for(int i=1; i<=8; i++)
			for(int j=1; j<=8; j++)
				if(validateMove(chessboard, new ChessMen(this.getName(), i, j, this.getColor()), player))
					allPositions.add(new Coordinates(i,j));
		return allPositions;
	}
}
