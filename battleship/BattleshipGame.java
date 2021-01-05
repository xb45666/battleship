package battleship;

import java.util.Scanner;

public class BattleshipGame {

	public static void main(String[] args) {
		
		boolean gameStop=false;
		
		while(!gameStop) {
		Ocean ocean=new Ocean();
		
		ocean.placeAllShipsRandomly();
		//used to debug
		//ocean.printSolution();
		
		Scanner scanner =new Scanner(System.in);
		
		
		while (!ocean.isGameOver()) {
			
			System.out.println(" ");
			ocean.print();
			
			System.out.println("Enter row,column:");
			
			String input= scanner.nextLine();
			String[] inputArray=input.split(",");
			
			String rowString=inputArray[0];
			String colString=inputArray[1];
			try {
			int row=Integer.parseInt(rowString);
			int col=Integer.parseInt(colString);
			
			boolean shootResult=ocean.shootAt(row, col);
			
			if (shootResult==true) {
				System.out.println("hit!");
				if (ocean.getShipArray()[row][col].isSunk()) {
					System.out.println("You just sank a ship-"+ocean.getShipArray()[row][col].getShipType());
				}

			}
			if(shootResult==false) {
				System.out.println("Miss.");
			}
			}catch(NumberFormatException e) {
				System.out.println("Input should be two numbers seperated by comma please)");
			}
		}
		
		ocean.printSolution();
		System.out.println("Game over!");
		System.out.println("This is the battleship map.It requires 20 shots.");
		System.out.println("Shotsfired:"+ocean.getShotsFired());
		System.out.println("Shots hitted:"+ocean.getHitCount());			
	
		
	
		while(true) {
			
			System.out.println("Do you want to play again?");
        	//get next token
	        String answer = scanner.next();
	        
	        if (answer.toLowerCase().charAt(0) == 'y') {
	        	break;
	        } else if (answer.toLowerCase().charAt(0) == 'n') {
	        	gameStop= true;
	        	break;
	        }
        }
		scanner.close();
	}
		
}
}
