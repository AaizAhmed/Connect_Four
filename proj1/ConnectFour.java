/**
 * This class is ConnectFour.java. It sets up the board and makes sure everything is right.
 *	@author Aaiz Ahmed <aaiza2@umbc.edu>
 * @version Sept 21, 2013
 * @project CMSC 202 - Fall 2013 - Project #1
 * @section 06
 */

package Connect_Four.proj1;

//package proj1;

public class ConnectFour 
{
	//Declaring variables to be used in the class.
	private int rows, cols;	
	private char [][] board;

	/**
	 * Constructor: Creates a new instant of ConnectFour class and makes sure 
	 * board size is not less the 5 by 5.
	 * @param	xrow:	Number of rows
	 * @param	ycolm:	Number of columns
	 */
	public ConnectFour (int xRow, int yCol ) 
	{
		// Confirming the number of rows and columns to be at least 5.
		if (xRow < 5) 
		{
			xRow = 5;
		}
		if (yCol < 5)
		{
			yCol = 5;
		}

		rows = xRow;
		cols = yCol;		
		board = new char[rows][cols];		

		// Nested for loops to fill up 2D board array  
		for (int i = 0; i < rows; i++) 
		{		
			for ( int j = 0; j < board[0].length; j++)   
			{
				board[i][j]= '_';  
			}
		}  
	}

	/**
	 * Method to assign values to the board based on players choice of column.
	 * It checks to make sure it's a valid column number otherwise code is not executed. 
	 * If condition is true 'x' is placed for player 1 or 'o' for player2  at that position.
	 * @param col: the column number where player wants to move.
	 * @param player: to tell if it's player1 or player2.
	 */
	public void makeMove (int col, int player) 
	{
		if (col > 0 && col <= this.cols)
		{			
			// The board has to be filled from bottom to top, this for loop checks which 
			// row is empty in the given column and fills it.			
			for (int i = rows-1; i >= 0 ; i--) 
			{
				if (board[i][col-1] == '_')
				{
					if (player == 0) 
					{
						board[i][col-1] = 'x';
						return; 
					}  
					else 
					{
						board[i][col-1] = 'o'; 
						return; 
					}
				}
			}
		}
	} 

	/**
	 * To see if column given by user is already full or not. 
	 * It also makes sure input is valid. 
	 * If invalid column/input is entered code doesn't execute. 
	 * @param col: column number given by a player.
	 */
	public boolean columnCheck (int col) 
	{
		if (col > 0 && col <= this.cols)
		{
			// Only check the top row at index Zero
			if (board[0][col-1] == 'x' || board[0][col-1] == 'o') 
			{	
				return true;	
			} 
		}

		return false;
	}

	/**
	 * This methods checks the horizontal win condition. 
	 * @return true or false
	 */
	public boolean horizontalWin () 
	{
		for (int i = 0; i < rows; i++) 
		{   
			// Go up to col-3 so that we don't get out of bound while checking the next 3 symbols
			for (int j = 0; j < cols-3; j++) 
			{
				if (board[i][j] == 'x' && board[i][j+1]=='x' && board[i][j+2]=='x' && board[i][j+3]=='x') 
				{
					return true; 

				}
				else if (board[i][j] == 'o' && board[i][j+1]=='o' && 
						 board[i][j+2]=='o' && board[i][j+3]=='o') 
				{
					return true;
				}
			}
		} 

		return false;	 
	}
	
	/**
	 * This method checks the vertical win condition. 
	 * @return true or false
	 */
	public boolean verticalWin () 
	{
		for (int i = 0; i < cols; i++) 
		{   
			for (int j = 0; j < rows-3; j++) 
			{
				if (board[j][i]=='x' && board[j+1][i]=='x' && board[j+2][i]=='x' && board[j+3][i]=='x' ) 
				{
					return true;  
				}
				else if (board[j][i]=='o' && board[j+1][i]=='o' && board[j+2][i]=='o' && board[j+3][i]=='o') 
				{
					return true;
				}
			}
		} 
		
		return false;	
	}
	
	/**
	 * This method checks the Diagonal win. From top left corner to bottom right corner. 
	 * @return true or false
	 */

	public boolean diagonallWinOne () 
	{
		for (int i = 0; i < rows-3; i++) 
		{   
			for (int j = 0; j < cols-3; j++) 
			{
				if (board[i][j]=='x' && board[i+1][j+1]=='x' && board[i+2][j+2]=='x' && board[i+3][j+3]=='x') 
				{		
					return true; 
				}   
				else if (board[i][j]=='o' && board[i+1][j+1]=='o' && board[i+2][j+2]=='o' && board[i+3][j+3]=='o') 
				{
					return true;
				}				 
			}
		}

		return false;	
	}
	
	/**
	 * This method checks for the other case of the Diagonal win.  
	 * From Bottom left corner to top right corner. 
	 * @return true or false
	 */
	public boolean diagonallWinTwo () 
	{
		for (int i = rows-1; i >= 3; i--) 
		{   
			for (int j = 0; j < cols-3; j++) 
			{
				if (board[i][j]=='x' && board[i-1][j+1]=='x' && board[i-2][j+2]=='x' && board[i-3][j+3]=='x') 
				{
					return true;    
				}
				else if (board[i][j]=='o' && board[i-1][j+1]=='o' && board[i-2][j+2]=='o' && board[i-3][j+3]=='o') 
				{
					return true; 
				} 
			}
		} 
		
		return false;	
	}
	
	/**
	 * This method combines all the winning conditions and returns true if 
	 * any of them is true and false otherwise.
	 * @return true or false
	 */
	public boolean allWins() 
	{
		if (horizontalWin() || verticalWin() || diagonallWinOne() || diagonallWinTwo() ) 
		{
			return true; 
		}
		
		else 
			return false;
	}

	/**
	 * This method is used to print the board as a string.
	 * It prints the board and allows the players to see their current and previous moves.
	 */
	public String toString () 
	{
		for (int i = 0; i < rows; i++) 
		{  
			for (int j = 0; j < cols; j++) 
			{
				System.out.print( board[i][j] );  
			}			
			System.out.println();
		}
		System.out.println ();

		return ""; 
	}

	public static void main(String args[])
	{
		ConnectFour c = new ConnectFour(5, 5);
	}

}

