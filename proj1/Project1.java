/**
 * This class is Project1.java. It communicates with ConnectFour.java and the players/users. It controls input and output.
 * @author Aaiz Ahmed <aaiza2@umbc.edu>
 * @version Sept 21, 2013
 * @project CMSC 202 - Fall 2013 - Project #1
 * @section 06
 * @param args
 */
package proj1;

import java.util.Scanner;

public class Project1 {
	public static void main (String[] args) {

		//Declaring variables to be used.
		int row = 0, col = 0; 	String option;
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		System.out.println ("Hello and welcome!\nDo you want to play Connect Four game? Press enter or any key to continue and Q/q to quit. ");
		option = scan.nextLine();

		//Outer most while loop. Will be used to start a new game if it's a win or a draw.
		while (!option.equalsIgnoreCase("q")) {
			System.out.println ("Please enter the number of rows, it can't be less then 5." );
			row = scan.nextInt();

			while (row < 5) {
				System.out.println ("Please enter the number of rows, it can't be less then 5." );
				row = scan.nextInt(); 
			}
			System.out.println ("Please enter the number of columns, it can't be less then 5." );
			col = scan.nextInt();

			while (col<5) {
				System.out.println ("Please enter the number of columns, it can't be less then 5." );
				col = scan.nextInt();  }

			//Instantiating a ConnectFour object.
			ConnectFour hh = new ConnectFour (row,col); 
			System.out.println (hh.toString());
			System.out.println ();


			int times = 0, stage = 0; String check;
			//For loop for total number of moves and alternating between the players.
			for (int i = 0; i < row*col; i++ ) {
				if (i%2 == 0) {
					System.out.println ("Player1: Please enter the column where you want to play. Give column number." );
					check = scan.next();
					if(check.equalsIgnoreCase("q")) {  stage = 1; break; }
					else {  
						times = Integer.parseInt(check);            

						//Making sure input is valid by while loop.
						while (times <= 0 || times > col) { 
							System.out.println ("Player1: Please enter a valid column number. It can't be negative, zero or greater then " + col );
							check = scan.next();  
							if (check.equalsIgnoreCase("q")) { 
								stage = 2; break;} //goes to for loop.
							else {
								times = Integer.parseInt(check); }
						}

						if (stage == 2) {break;} //gets out of for loop

						times = Integer.parseInt(check);
						// Checking if column entered by player1 is already full or not.   
						while (hh.columnCheck(times)) {
							System.out.println ("Player1: This column is already full. Please choose another column." );
							check = scan.next(); 
							if (check.equalsIgnoreCase("q")) { stage = 3; break;} //goes to for loop
							else
								times = Integer.parseInt(check);

							//If column is full and asked to play in another column and player1 gives an invalid column this while loop will prompt for a valid column.  		 
							while (times <= 0 || times > col) { 
								System.out.println ("Player1: Please enter a valid column number. It can't be negative, zero or greater then " + col );
								check = scan.next(); 
								if (check.equalsIgnoreCase("q")) {stage = 4; break;} //goes to outer while loop. 
								else 
									times = Integer.parseInt(check);} 
							if (stage==4) { break;} //goes to for loop.
						} 
						if (stage==3 || stage == 4) { stage = 5; break;} //gets out of for loop

						//Calling methods to reassign x based on player1's valid input, printing the board and checking for win conditions. 	
						hh.reassign(times, 0); 
						System.out.println (hh.toString());  
						if ( hh.allWins ()) { 
							System.out.println ("\nPlayer1:"); break;  } 
					}	
				}  
				else   { 
					System.out.println ("Player2: Please enter the column where you want to play. Give column number." );
					check = scan.next();
					if(check.equalsIgnoreCase("q")) {  stage = 6; break; }
					else {  
						times = Integer.parseInt(check);            

						//Making sure input is valid by while loop.   
						while (times <= 0 || times > col) { 
							System.out.println ("Player2: Please enter a valid column number. It can't be negative, zero or greater then " + col );
							check = scan.next();  
							if (check.equalsIgnoreCase("q")) { 
								stage = 7; break;} //goes to for loop.
							else {
								times = Integer.parseInt(check); }
						}

						if (stage == 7) {break;} //gets out of for loop

						times = Integer.parseInt(check);

						// Checking if column entered by player2 is already full or not.     
						while (hh.columnCheck(times)) {
							System.out.println ("Player2: This column is already full. Please choose another column." );
							check = scan.next(); 
							if (check.equalsIgnoreCase("q")) { stage = 8; break;} //goes to the for loop
							else
								times = Integer.parseInt(check);

							//If column is full and asked to play in another column and player2 gives an invalid column this while loop will prompt for a valid column.		 
							while (times <= 0 || times > col) { 
								System.out.println ("Player2: Please enter a valid column number. It can't be negative, zero or greater then " + col );
								check = scan.next(); 
								if (check.equalsIgnoreCase("q")) {stage = 9; break;} //goes to while loop. 
								else 
									times = Integer.parseInt(check);} 
							if (stage==9) { break;} //goes to for loop.
						} 
						if (stage==8 || stage == 9) { stage = 10; break;}
					} 	 

					//Calling methods to reassign o based on player2's valid input, printing the board and checking for win conditions.    
					hh.reassign(times, 1); 
					System.out.println (hh.toString()); 
					if (hh.allWins ()) { 
						System.out.println ("\nPlayer2:"); break;} 
				}
			}
			if (stage > 0){

				if (stage == 1 || stage == 2 || stage == 3 || stage == 4|| stage == 5 ) {
					System.out.println ("Player1 Quit"); break; }

				else
					//	System.out.println ("Player2 stage value: " + stage);
					if (stage == 6 || stage == 7 || stage == 8 || stage == 9|| stage == 10) {
						System.out.println ("Player2 Quit"); break; }

			}

			else {
				if ( hh.allWins ()) {
					System.out.println ("Congratulations!! You won! Press enter or any key to play again and Q/q to Quit the game. " ); 
					option = scan.nextLine();}
				else
					System.out.println ("It's a draw. Do you want to play again? Press enter or any key to play again and Q/q to Quit the game. " );
				option = scan.next();}
		}	
	}
}
