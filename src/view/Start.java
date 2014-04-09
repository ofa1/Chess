package view;

public class Start {

	public static void main(String[] args) {
		Play newGame = new Play("Player-1", "Player-2");
		while(!newGame.gameOver)
		{
			newGame.playgame();
		}

	}
}
