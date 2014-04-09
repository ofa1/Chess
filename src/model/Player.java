package model;

public class Player 
{
	private String Name;
	private String color;
	
	public Player(String name, String color)
	{
		this.Name = name;
		this.color = color;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public String toString()
	{
		return this.color;
	}
	
}
