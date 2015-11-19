import java.lang.System;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

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
	private Boolean isTheEnd;
	private int x,y;

	public Game()
	{
		scanner = new Scanner(System.in);
		width = 0;
		height = 0;
		mineNumber = 0;
		isTheEnd = false;

		//get width, height and number of mines from user through console
		width = getInt("Width 0<...<=50 : ",50);
		height = getInt("Height 0<...<=20 : ",20);
		totalCellNumber = width*height;
		do
			mineNumber = getInt("Number of mines 0<...<"+totalCellNumber+" : ",totalCellNumber);
		while (totalCellNumber <= mineNumber);

		//compute the number of non-mined cells
		coveredNonminedCellNumber = totalCellNumber - mineNumber;

		//create grid and fill it with mines
		grid = new int[height][width];
		fillGrid();

		//step : display grid and ask user for his next move
		while (false == isTheEnd)
		{
			displayGrid();
			System.out.println("Please choose a cell to uncover [lin col] : ");
			x = getInt("", height-1);
			y = getInt("", width-1);
			step();
		}
		System.out.println("Game is over.");
	}

	public static void clearScreen()
	{
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	private int getInt(String varDescriptor, int threshold)
	{
		int varToSet = 0;

		while ((0 >= varToSet) || (threshold < varToSet))
		{
			try
			{
				System.out.print(varDescriptor);
				varToSet = scanner.nextInt();
			}
			catch (InputMismatchException e) {
				
                System.out.println("/!\\ not a positive integer");
                scanner.next();
			}
		}

		return (varToSet);
	}

	private void fillGrid()
	{
		Random random = new Random();
		int randCol = 0;
		int randLin = 0;
		int i,j;
		int minesToPlace = mineNumber;
		
		//init grid with unknown cells :
		for (i=0; i<height; i++)
		{
			for (j=0; j<width; j++)
			{
				grid[i][j] = UNKNOWN_CELL;
			}
		}

		//insert mines
		while (0 < minesToPlace)
		{
			randCol = random.nextInt(width);
			randLin = random.nextInt(height);
			if (UNKNOWN_CELL == grid[randLin][randCol])
			{
				grid[randLin][randCol] = MINE_CELL;
				minesToPlace--;
			}
		}
	}

	private void displayGrid()
	{
		int i,j;
		clearScreen();

		//horizontal axis legend
		System.out.print("  ");
		for (j=0; j<width; j++) System.out.print(String.format("%3d", j));
		System.out.print("\n   ");
		for (j=0; j<3*width; j++) System.out.print("_");
		System.out.print("\n");

		//vertical axis legend + grid
		for (i=0; i<height; i++)
		{
			System.out.print(i+" | ");
			for (j=0; j<width; j++)
			{
				if (UNKNOWN_CELL == grid[i][j]) System.out.print(".");
				else if ((MINE_CELL == grid[i][j]) && (false==isTheEnd)) System.out.print(".");
				else if ((MINE_CELL == grid[i][j]) && (true==isTheEnd)) System.out.print("X");
				else System.out.print(grid[i][j]);
				System.out.print("| ");
			}
			System.out.println("");
		}
	}

	private void step()
	{
		if (MINE_CELL == grid[x][y])
		{
			isTheEnd = true;
			displayGrid();
			System.out.println("There was a mine at ["+x+","+y+"]...\nYou loose !");
		}
	}

}