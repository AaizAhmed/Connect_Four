/**
 * This class is Project1.java. It communicates with ConnectFour.java and the players/users. 
 * It controls input and output.
 * @author Aaiz Ahmed <aaiza2@umbc.edu>
 * @version Sept 21, 2013
 * @project CMSC 202 - Fall 2013 - Project #1
 * @section 06
 * @param args
 */

package Connect_Four.proj1;
//package proj1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Project1 
{
	private static Scanner scan = new Scanner(System.in);
	
	private static Pattern p = Pattern.compile("^\\d+$");
	private static Matcher m; 

	/**
	 * This method checks if the input given by the user is a number or not
	 * @param 	input
	 * @return  true or false
	 */
	public static boolean isNumber(String input)
	{
		m = p.matcher(input);
		return m.matches();
	}
	
	/**
	 * This method run a full game of Connect Four. It uses a for loop and helper methods.
	 * @param board		A Connect Four board
	 * @param rows		Number of rows
	 * @param cols		Number of columns
	 */
	public static void startGame(ConnectFour board, int rows, int cols)
	{
		int colNum = 0;

		// For loop is used to execute total number of moves and alternating between the players.
		for (int i = 0; i < rows*cols; i++) 
		{
			if (i%2 == 0) 
			{
				System.out.println("Player 1: What is your choice?");

				// Use isValid() method to check if the column number given by the user is valid.
				colNum = isValid(board, cols, 1);			

				// Making a move based on input provided.
				board.makeMove(colNum, 0);
				board.toString();				

				if (board.allWins ()) 
				{ 
					System.out.println("Player 1: Congratulations!! You won!"); 
					return;  
				} 
			}	  
			else   
			{ 
				System.out.println("Player 2: What is your choice?");

				colNum = isValid(board, cols, 2);
				board.makeMove(colNum, 1);
				board.toString();

				if (board.allWins ()) 
				{ 
					System.out.println("Player 2: Congratulations!! You won!"); 
					return;  
				} 
			}
		}
		
		System.out.println("It was a draw!");
	}

	/**
	 * This method checks if the column given by the user to make a move is valid.
	 * Additionally, it quits the game and program if the user types q.
	 * 
	 * @param board		A Connect Four board.
	 * @param cols		Number of columns
	 * @param player	Players whose turn it is
	 * 
	 * @return			A valid column number as an integer.
	 */
	public static int isValid(ConnectFour board, int cols, int player)
	{
		String input = "";

		while(true)
		{						
			System.out.printf ("Enter a valid column between 1 and %d or q to quit the game: \n", cols );
			input = scan.next();

			if (isNumber(input)) 
			{
				int numEntered = Integer.parseInt(input);
				boolean isFull = board.columnCheck(numEntered);

				if (isFull)
				{
					System.out.printf ("The column you entered is already full. Pick a different column: ");
				}

				if (isFull == false && (numEntered > 0 && numEntered <= cols) )
				{	
					return numEntered;
				}
			}

			if (input.equals("q"))
			{
				// Exist the game and stop the program.
				System.out.println("Player " + player + " has quit the game!");
				System.out.println("Game ending...");
				System.exit(0);
			}
		}		
	}

	public static void main (String[] args) 
	{
		//Declaring variables to be used.
		int rows = 0, cols = 0; 		

		System.out.println ("Hello and welcome!\nThis game is played between 2 players and a "
						  + "player can quit anytime by pressing q.\n");

		String input = "";

		//Outer most while loop. Will be used to start a new game if it's a win or a draw.
		while (!input.equals("q")) 
		{			 
			while(true)
			{
				System.out.println ("Please enter the number of rows. It can not be less then 5." );
				input = scan.next();

				// Use isNumber() method to check if the input given by the user is a number.
				if (isNumber(input) &&  Integer.parseInt(input) >= 5) 
				{
					rows = Integer.parseInt(input);
					break;
				}				
			}

			while(true)
			{
				System.out.println ("Please enter the number of columns. It can not be less then 5." );
				input = scan.next();

				if (isNumber(input) &&  Integer.parseInt(input) >= 5) 
				{
					cols = Integer.parseInt(input);
					break;
				}				
			}

			// Instantiating a ConnectFour object.
			ConnectFour board = new ConnectFour (rows, cols); 
			board.toString();
			
			// Play the game.  
			startGame(board, rows, cols);	

			// The code execution returns here after the end of the current game.
			while(true)
			{
				System.out.println("Do you want to play again? Press y or q: ");
				input = scan.next();

				if( input.equals("y") || input.equals("q") )
				{
					break;
				}				
			}			
		}	
		
		// Close the scanner object.
		scan.close();
	}
}
