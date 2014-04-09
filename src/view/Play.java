package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.ChessMen;
import model.Coordinates;
//import model.Player;
import controller.Game;

public class Play
{
	Game game;
	boolean gameOver;

	public Play(String player1, String player2)
	{
		game = new Game(player1, player2);
		gameOver = false;
	}

	@SuppressWarnings("resource")
	void playgame()
	{
		game.chessboard.view();

		if(game.isCheck())
		{
			if(game.checkCheckmate())
			{
				System.out.println("CHECKMATE");
				gameOver = true;
				return ;
			}
			System.out.println("CHECK");
		}
		try
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("\nEnter the piece you want to move, " + game.getCurrentPlayer().getName());
			String pieceName = sc.next();
			pieceName = pieceName.toUpperCase();

			//checking if the input is of same player
			if(pieceName.charAt(0) != game.getCurrentPlayer().getColor().charAt(0))
				throw new InputMismatchException("Not " + game.getCurrentPlayer().getName() + "'s move!");

			pieceName = pieceName.substring(1);
			pieceName = pieceName.trim();
			System.out.println("\tentered piece: " + game.getCurrentPlayer().getColor() + pieceName);

			Coordinates co = game.chessboard.getPositions().get(new ChessMen(pieceName, game.getCurrentPlayer().getColor()));
			int a = co.getX();
			int b = co.getY();

			ChessMen piece = game.chessboard.getElement(a, b);

			//			System.out.println("input: " + pieceName);

			System.out.println("Enter the position where you want to move the piece: X, Y");
			int x = sc.nextInt();
			int y = sc.nextInt();

			if(game.validate(pieceName, x, y, game.getCurrentPlayer()))
			{
				if(game.movePiece(piece, a, b, x, y))
				{
					if(game.getCurrentPlayer().getColor().equalsIgnoreCase("W"))
						game.setCurrentPlayer(2);
					else
						game.setCurrentPlayer(1);

					//checking if the opponent is in check because of the move
					if(game.checkCheck(game.getCurrentPlayer()))
					{
						game.setCheck(true);
					}
					else
						game.setCheck(false);
				}
				else
				{
					System.out.println("\tPlease enter a valid move\n");
				}
			}
			else
			{
				System.out.println("\tPlease enter a valid move\n");
			}

		}
		catch (Exception e)
		{
			System.out.println("\tEnter proper input! " + e.getMessage());
//			e.printStackTrace();
		}
	}

	
}
