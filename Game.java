/* importations */
import java.util.Scanner;
import java.lang.System;
import java.util.InputMismatchException;

public class Game
{
	private final int EMPTY_CELL = 0;
	private final int UNKNOWN_CELL = 9;
	private final int MINE_CELL = 10;	
	private Scanner scanner;
	private int width;
	private int height;
	private int mineNumber;
	private int totalCellNumber;
	private int coveredNonminedCellNumber;
	private int grid[][];

	public Game()
	{
		scanner = new Scanner(System.in);
		width = 0;
		height = 0;
		mineNumber = 0;

		//get width, height and number of mines from user through console
		width = getInt("Width >0");
		height = getInt("Height >0");
		totalCellNumber = width*height;
		do
			mineNumber = getInt("Number of mines 0<...<" + totalCellNumber);
		while (totalCellNumber <= mineNumber);

		//compute the number of non-mined cells
		coveredNonminedCellNumber = totalCellNumber - mineNumber;

		//create grid and fill it with mines
		grid = new int[height][width];
		fillGrid(width,height,grid,mineNumber);
	}

	private int getInt(String varDescriptor)
	{
		int varToSet = 0;

		while (0 >= varToSet)
		{
			try
			{
				System.out.print(varDescriptor+ " : ");
				varToSet = scanner.nextInt();
			}
			catch (InputMismatchException e) {
				
                System.out.println("/!\\ not a positive integer");
                scanner.next();
			}
		}

		return (varToSet);
	}

	private void fillGrid(int width, int height, int grid[][], int mineNumber)
	{
		//to do

	}
}