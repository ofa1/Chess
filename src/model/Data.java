package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Class to store all the data of the moves, etc.
 */
public class Data
{

	private ChessMen [][] array;
	private ArrayList<String> logs;
	private HashMap<ChessMen, Coordinates> positions;

	public Data()
	{
		array = new ChessMen[9][9];
		logs = new ArrayList<String>();
		positions = new HashMap<ChessMen, Coordinates>(44);
		initialize();
	}

	public Data(ChessMen[][] arr, HashMap<ChessMen, Coordinates> pos)
	{
		this.array = new ChessMen[9][9];
		for(int i=0; i<9; i++)
			for(int j=0; j<9; j++)
				this.array[i][j] = arr[i][j];
		this.positions = new HashMap<ChessMen, Coordinates>(44);
		positions.putAll(pos);
		logs = new ArrayList<String>();
	}

	private void initialize()
	{
		//Create chess board

		//W Chess men

		setElement(new Rook("R1", 1, 1, "W"));
		setElement(new Knight("N1", 1, 2, "W"));
		setElement(new Bishop("BB", 1, 3, "W"));
		setElement(new King("K ", 1, 4, "W"));
		setElement(new Queen("Q ", 1, 5, "W"));
		setElement(new Bishop("BW", 1, 6, "W"));
		setElement(new Knight("N2", 1, 7, "W"));
		setElement(new Rook("R2", 1, 8, "W"));

		setElement(new Pawn("P1", 2, 1, "W"));
		setElement(new Pawn("P2", 2, 2, "W"));
		setElement(new Pawn("P3", 2, 3, "W"));
		setElement(new Pawn("P4", 2, 4, "W"));
		setElement(new Pawn("P5", 2, 5, "W"));
		setElement(new Pawn("P6", 2, 6, "W"));
		setElement(new Pawn("P7", 2, 7, "W"));
		setElement(new Pawn("P8", 2, 8, "W"));


		//B Chess Men
		setElement(new Rook("R1", 8, 1, "B"));
		setElement(new Knight("N1", 8, 2, "B"));
		setElement(new Bishop("BW", 8, 3, "B"));
		setElement(new King("K ", 8, 4, "B"));
		setElement(new Queen("Q ", 8, 5, "B"));
		setElement(new Bishop("BB", 8, 6, "B"));
		setElement(new Knight("N2", 8, 7, "B"));
		setElement(new Rook("R2", 8, 8, "B"));

		setElement(new Pawn("P1", 7, 1, "B"));
		setElement(new Pawn("P2", 7, 2, "B"));
		setElement(new Pawn("P3", 7, 3, "B"));
		setElement(new Pawn("P4", 7, 4, "B"));
		setElement(new Pawn("P5", 7, 5, "B"));
		setElement(new Pawn("P6", 7, 6, "B"));
		setElement(new Pawn("P7", 7, 7, "B"));
		setElement(new Pawn("P8", 7, 8, "B"));


		//initializing remaining elements
		for(int i=3; i<7; i++)
			for(int j=1; j<9; j++)
				setElement(new Blank("--", i, j, "-"));
	}

	/**
	 * Method to get the piece that is at this location
	 * @param i
	 * @param j
	 * @return
	 */
	public ChessMen getElement(int i, int j)
	{
		return array[i][j];
	}



	/**
	 * Method to set the location of the piece
	 * @param piece
	 * @param i
	 * @param j
	 */
	public void setElement(ChessMen piece)
	{		
		int i = piece.getPosition().getX();
		int j = piece.getPosition().getY();

		ChessMen killed = getElement(i, j);

		if(!(killed == null) && !(killed.getName().startsWith("--")) && !piece.getName().startsWith("--"))
		{
			positions.remove(killed);
//			System.out.println("Killed: "+ killed);
		}

		array[i][j] = piece;
		positions.put(piece, new Coordinates(i, j));
	}

	/**
	 * Method to get position of piece
	 * @param piece
	 * @return
	 */
	public Coordinates getPosition(ChessMen piece)
	{
		return positions.get(piece);
	}

	/**
	 * Method to get iterator for all positions
	 * @return
	 */
	public Iterator<ChessMen> getPositionIterator()
	{
		return positions.keySet().iterator();
	}

	public HashMap<ChessMen, Coordinates> getPositions() {
		return positions;
	}

	/**
	 * Method to add to logs
	 * @param log
	 */
	public void addLog(String log)
	{
		this.logs.add(log);
	}

	/**
	 * Method to print the logs
	 * @return
	 */
	public String getLogs()
	{
		return this.logs.toString();
	}

	/**
	 * Method to print the chess board
	 */
	public void view()
	{
		System.out.print("X/Y");
		for(int i=1; i<=8; i++ )
			System.out.print("  " + i + " ");
		System.out.println();
		for(int i=1; i<=8; i++)
		{
			System.out.print(i + "   ");
			for(int j=1; j<=8; j++)
			{
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Method to move piece
	 * @param piece
	 * @param a
	 * @param b
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean movePiece(ChessMen piece, int a, int b, int x, int y)
	{
		addLog(piece + "::" + a + "::" + b + "::" + x + "::" + y);

		piece.setPosition(new Coordinates(x, y));
		
		if(piece instanceof Pawn)
		{
			if(((Pawn) piece).isPromoted())
			{
				if(piece.getName().length() == 1)
				{
					int count = 0;
					Iterator<ChessMen> i = getPositionIterator();
					while (i.hasNext())
						if (i.next().getName().startsWith(piece.getName()))
							count += 1;
					piece.setName(piece.getName() + count);
					addLog("pawnPromotion::" + x + "::" + y);
				}
			}

		}

		setElement(piece);
		setElement(new Blank("--", a, b, "-"));

		return true;
	}

	public ChessMen[][] getArray() {
		return array;
	}

	public void ChessMen(ChessMen[][] array) {
		this.array = array;
	}

	public void setLogs(ArrayList<String> logs) {
		this.logs = logs;
	}

	public void setPositions(HashMap<ChessMen, Coordinates> positions) {
		this.positions = positions;
	}
}
