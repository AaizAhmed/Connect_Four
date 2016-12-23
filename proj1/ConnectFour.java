/**
  * This class is ConnectFour.java. It sets up the board and makes sure everything is right.
  *	@author Aaiz Ahmed <aaiza2@umbc.edu>
  * @version Sept 21, 2013
  * @project CMSC 202 - Fall 2013 - Project #1
  * @section 06
*/
package proj1;

public class ConnectFour {
//Declaring variables to be used in the class.
private int row, col;	
private char [][] board;

/**
 * Constructor - Creates a new instant of ConnectFour class and makes sure board size is not less the 5 by 5.
 */
public ConnectFour (int xrow, int ycolm ) {
	row = xrow;
	col = ycolm;
 
//Setting up the board.	
if (row < 5) {
    board = new char [5][col]; }
else 
	if (col < 5)  {
		board = new char [row][5]; }
    else {	 
	   board = new char [row][col]; }

 // Nested for loops to fill up 2D board array  
	for (int i = 0; i < board.length; i++) {
	    for ( int j = 0; j < board[0].length; j++)   {
		  board[i][j]= '_';  
		}
	} //loop finishes 
}

/*
 * This method prints out the board. 
 *
public void printBoard () {
	for (int i = 0; i < board.length; i++) {  
	     for (int j = 0; j < board[0].length; j++) {  
	             
	    	 System.out.print(board[i][j] );  }
	       
	         System.out.println(); }
} */

/**
 * Method to assign values to the board based on players choice of column.
 * It checks to make sure it's a valid column number otherwise code is not executed. 
 * If condition is true 'x' is placed for player1 or 'o' for player2 at that position.
 * @param col: the column number where player wants to move.
 * @param player: to tell if it's player1 or player2.
 */
public void reassign (int col, int player) {

if (col > 0 || col < this.col) 	
	
	// As board has to be filled from bottom to top, this for loop checks that which row is empty in given column
	// filles it
		for (int j = row-1; j >= 0 ; j--) {
			
			if (board[j][col-1]=='_')  
			 
			   if (player == 0) {
				   board[j][col-1] = 'x';
				   	  
			break; }  
			   else 
			   {
				   board[j][col-1] = 'o'; 
			       break; }
		     }
	} 
/**
* To see if column given by user is already full or not. 
* It also makes sure input is valid. If invalid column/input is entered code doesn't execute. 
* @param col: column number given by a player.
*/
  public boolean columnCheck ( int col) {
	  
	  if (col > 0 || col < this.col)
	
		if (board[0][col-1]=='x' || board[0][col-1]=='o') {
			return true; } 
		
		return false;
}

/**
 * This methods checks the horizontal win condition. 
 * @return true or false
 */
public boolean horizontalWin () {
	
 	
	for (int i = 0; i < row; i++) 
	{   for (int j = 0; j < col; j++) 
	   {
			if (board[i][j]=='x' && (j+1 < col && j+2 < col && j+3 < col)) {
				if (board[i][j+1]=='x' && board[i][j+2]=='x' && board[i][j+3]=='x') {
					return true; }   
				}
		else 
				if (board[i][j]=='o' && (j+1 < col && j+2 < col && j+3 < col) ) {
					if (board[i][j+1]=='o' && board[i][j+2]=='o' && board[i][j+3]=='o')
						return true; } 
		}
		 
	} 
	return false;	 
}
/**
 * This method checks the vertical win condition. 
 * @return true or false
 */
public boolean verticalWin () {
	for (int i = 0; i < col; i++) 
	{   for (int j = 0; j < row; j++) 
	   {
			if (board[j][i]=='x' && (j+1 < row && j+2 < row && j+3 < row) ) {
				if (board[j+1][i]=='x' && board[j+2][i]=='x' && board[j+3][i]=='x') {
					return true; }   }
		else 
				if (board[j][i]=='o' && (j+1 < row && j+2 < row && j+3 < row)) {
					if (board[j+1][i]=='o' && board[j+2][i]=='o' && board[j+3][i]=='o')
						return true; } 
		}
		 
	} 
	return false;	
}
/**
 * This method checks the Diagonal win. From top left corner to bottom right corner. 
 * @return true or false
 */

public boolean diagonallWinOne () {
	for (int i = 0; i < row-3; i++) 
	{   for (int j = 0; j < col-3; j++) 
	   {
			if (board[i][j]=='x') {
				if (board[i+1][j+1]=='x' && board[i+2][j+2]=='x' && board[i+3][j+3]=='x') {
					return true; }   
				}
		else 
				if (board[i][j]=='o') {
					if (board[i+1][j+1]=='o' && board[i+2][j+2]=='o' && board[i+3][j+3]=='o')
						return true; } 
		}
		 
	} 
	return false;	
}
/**
 * This method checks for other case of the Diagonal win.  From Bottom left corner to top right corner. 
 * @return true or false
 */
public boolean diagonallWinTwo () {

	for (int i = row-1; i >= 3; i--) 
	{   for (int j = 0; j < col-3; j++) 
	   {
			if (board[i][j]=='x') {
				if (board[i-1][j+1]=='x' && board[i-2][j+2]=='x' && board[i-3][j+3]=='x') {
					return true; }   
				}
		else 
				if (board[i][j]=='o') {
					if (board[i-1][j+1]=='o' && board[i-2][j+2]=='o' && board[i-3][j+3]=='o')
						return true; } 
		}
		 
	} 
	return false;	
}
/**
 * This method combines all the winning conditions and returns true if any of them is true and false otherwise.
 * @return true or false
 */

public boolean allWins () {
	if (horizontalWin () || verticalWin () || diagonallWinOne () || diagonallWinTwo () ) {
		return true; }
	else
	return false;
}
/**
 * This method is used to print the board as a string.
 * It prints the board and allows the players to see their current and previous moves.
 */
public String toString () {
	String boardString = "";
	for (int i = 0; i < board.length; i++) {  
	     for (int j = 0; j < board[0].length; j++) {  
	             
	    	 boardString +=  board[i][j]  ;  }
	       
	         boardString += "\n";
	 	
	         }
			
	return boardString; 
}

}

