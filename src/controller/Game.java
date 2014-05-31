package controller;

import java.util.ArrayList;
import java.util.Iterator;

import model.ChessMen;
import model.Coordinates;
import model.Data;
import model.Player;

public class Game 
{
	public Data chessboard;
	private boolean check = false;
	Player CurrentPlayer;
	Player p1;
	Player p2;

	/**
	 * Default constructor to create a game
	 * 
	 * @param player1
	 * @param player2
	 */
	public Game(String player1, String player2)
	{
		chessboard = new Data();
		p1 = new Player(player1, "W");
		p2 = new Player(player2, "B");
		CurrentPlayer = p1;
	}

	/**
	 * Special constructor for creating hypothetical chessboards in order to check for conditions
	 * @param secondboard
	 * @param p
	 */
	public Game(Data secondboard, Player p)
	{
		
		this.chessboard = secondboard;
		p1 = new Player("player1", "W");
		p2 = new Player("player2", "B");
		CurrentPlayer = p;
	}


	/**
	 * Method to validate the move
	 * 
	 * @param pieceName
	 * @param x
	 * @param y
	 * @param player
	 * @return
	 */
	public boolean validate(String pieceName, int x, int y, Player player)
	{
		Coordinates co = chessboard.getPositions().get(new ChessMen(pieceName, player.getColor()));
		if(co == null)
		{
			System.out.println("no coordinates for piece: " + pieceName);
			return false;
		}
		int a = co.getX();
		int b = co.getY();

		//if trying to move out of the chess board
		if(x < 1 || x > 8 || a < 1 || a > 8 || b < 1 || b > 8 || y < 1 || y > 8)
			return false;
		
		if(a == x && b == y)
			return false;
		
		ChessMen piece = chessboard.getElement(a, b);

		//If move is not of same player's pieces
		if(!piece.getColor().equalsIgnoreCase(player.getColor()))
			return false;
		
		//If piece already present at the position and is of same player
		if(chessboard.getElement(x, y).getColor().equalsIgnoreCase(player.getColor()))
			return false;
		
		return piece.validateMove(chessboard, chessboard.getElement(x,  y), player);
	}



	/**
	 * Method to move a piece
	 * 
	 * @param piece
	 * @param a
	 * @param b
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean movePiece(ChessMen piece, int a, int b, int x, int y)
	{		
		ChessMen dest = chessboard.getElement(x, y);
		
		//moving the piece
		chessboard.movePiece(piece, a, b, x, y);
		
		//if in check because of the move then reverting the move
		if(checkCheck(getCurrentPlayer()))
		{
			piece.setPosition(new Coordinates(a,  b));
			dest.setPosition(new Coordinates(x, y));

			chessboard.setElement(piece);
			chessboard.setElement(dest);			
			return false;
		}
		return true;
	}
	
	/**
	 * Method to check for Check condition 
	 * @return
	 */
	public boolean checkCheck(Player player)
	{
		Player opponentPlayer;
		if(player.getColor().equalsIgnoreCase(p1.getColor()))
			opponentPlayer = p2;
		else
			opponentPlayer = p1;
		
		String king = "K";

		Coordinates co = chessboard.getPositions().get(new ChessMen(king, player.getColor()));
		int x = co.getX();
		int y = co.getY();
		
		//Iterate over each piece to check if it can threaten the king
		Iterator<ChessMen> i = chessboard.getPositionIterator();
		while(i.hasNext())
		{
			ChessMen piece = i.next();

			//if piece is of same player skip checking 
			if(piece.getColor().charAt(0) != opponentPlayer.getColor().charAt(0))
				continue;

			//check if there could be a check with the current piece
			if(validate(piece.getName(), x, y, opponentPlayer))
				return true;
		}
		return false;
	}

	/**
	 * Method to check for checkmate condition
	 * @return
	 */
	public boolean checkCheckmate()
	{
		String col = (getCurrentPlayer().getColor().equalsIgnoreCase("W") ? "B" : "W");
		Player opponentPlayer = new Player(col, col);
		//Get all the chessmen
		Iterator<ChessMen> i = chessboard.getPositionIterator();
		try
		{
			//Iterate over each chessman
			while(i.hasNext())
			{
				ChessMen piece = i.next();

				//if piece is of opponent skip checking 
				if(!piece.getColor().equalsIgnoreCase(getCurrentPlayer().getColor()))
					continue;

				int a = chessboard.getPosition(piece).getX();
				int b = chessboard.getPosition(piece).getY();

				//for every piece getting all possible positions
				ArrayList<Coordinates> positions = piece.getAllPossiblePositions(chessboard, getCurrentPlayer());

				for(int j=0; j<positions.size(); j++)
				{
					int x = positions.get(j).getX();
					int y = positions.get(j).getY();

					//check if current check can be removed by moving current piece
					if(validate(piece.getName(), x, y, getCurrentPlayer()))
					{
						//Creating hypothetical game to analyze move
						Game g = new Game(new Data(chessboard.getArray(), chessboard.getPositions()), getCurrentPlayer());
						if(g.movePiece(piece, a, b, x, y))
						{
							if(!g.checkCheck(opponentPlayer))
							{
								//if check is removed by moving this piece then there is no check mate
								return false;
							}
						}
					}
				}//end of for loop
			}//end of while loop
		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return true;
	}


	/**
	 * Method to get the current player
	 * @return
	 */
	public Player getCurrentPlayer()
	{
		return CurrentPlayer;
	}

	/**
	 * Method to set current player
	 * @param i - number of the player 1 or 2
	 */
	public void setCurrentPlayer(int i)
	{
		if(i == 1)
			this.CurrentPlayer = p1;
		else
			this.CurrentPlayer = p2;
	}

	/**
	 * Method to check for Check condition
	 * @return
	 */
	public boolean isCheck() {
		return check;
	}

	/**
	 * Method to set whether it is Check or not
	 * @param check
	 */
	public void setCheck(boolean check) {
		this.check = check;
	}
}
