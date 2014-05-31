/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * Main class from which all chessmen inherit
 * 
 * Each Chess Piece has three variables:
 * 	Name, Position and Color
 */
public class ChessMen 
{
	private String name;
	private Coordinates position;
	private String color;

	/**
	 * @param chessboard
	 * @param destination
	 * @param player
	 * @return
	 */
	public boolean validateMove(Data chessboard, ChessMen destination, Player player)
	{
		return false;
	}
	
	/**
	 * @param name
	 * @param color
	 */
	public ChessMen(String name, String color)
	{
		this.name = name;
		this.color = color;
	}
	
	/**
	 * @param name
	 * @param x
	 * @param y
	 * @param color
	 */
	public ChessMen(String name, int x, int y, String color)
	{
		this.name = name;
		this.position = new Coordinates(x, y);
		this.setColor(color);
	}
	
	/**
	 * Method to return the string as Color [B/W] + Name of piece [P/R/N/Q/K]
	 */
	public String toString()
	{
		return getColor().charAt(0)+getName() ;
	}
	
	/**
	 * Equals method override, for checking if it is same piece
	 */
	@Override
	public boolean equals(Object obj) {
		ChessMen c = (ChessMen) obj;
		return this.getName().trim().equalsIgnoreCase(c.getName().trim()) && this.getColor().trim().equalsIgnoreCase(c.getColor().trim());
	}
	
	/**
	 * Override the hashCode method to ensure HashMap stores properly
	 */
	@Override
	public int hashCode() {
		return (this.getName().trim() + this.getColor().trim()).hashCode();
	}
	
	/**
	 * Method to get All Possible Positions of a piece on the given chessboard.
	 * It is common method to all pieces
	 * 
	 * @param chessboard
	 * @param player
	 * @return
	 */
	public ArrayList<Coordinates> getAllPossiblePositions(Data chessboard, Player player)
	{
		ArrayList<Coordinates> allPositions = new ArrayList<>();

		for(int i=1; i<=8; i++)
			for(int j=1; j<=8; j++)
				if(validateMove(chessboard, new ChessMen(this.getName(), i, j, this.getColor()), player))
					allPositions.add(new Coordinates(i,j));
		return allPositions;
	}
	
	/**
	 * 
	 * Getters and Setters
	 * 
	 */
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
}
