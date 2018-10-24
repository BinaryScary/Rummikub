package core;

import java.util.Scanner;

public class CLI implements UserInterface {
	Scanner userInput = new Scanner(System.in);

	@Override
	public void message(String mes) {
		System.out.println(mes);
	}

	@Override
	public char response(String mes) {
		System.out.print(mes);
		
		char u = userInput.next().charAt(0);
		userInput.nextLine(); //consume \n
		//userInput.close(); can't close due to System.in being unable to reopen

		return u;
	}

	@Override
	public void playerTurn(char player) {
		switch (player) {
			case 'H':
			case 'h':
				System.out.println("Player Human's turn.");
				break;
			case '1':
				System.out.println("Player P1's turn.");
				break;
			case '2':
				System.out.println("Player P2's turn.");
				break;
			case '3':
				System.out.println("Player P3's turn.");
				break;
			default:
				System.out.println("*Invalid player");
				break;
		}
	}

	@Override
	public void displayMeld(Meld m) {
		System.out.println(m.toString());
	}

	@Override
	public void displayTable(Table t) {
		System.out.println(t.toString());
	}

	@Override
	public void outcome(char out) {
		switch (out) {
			case 'H':
			case 'h':
				System.out.println("Human has won!");
				break;
			case '1':
				System.out.println("Player P1's has won!");
				break;
			case '2':
				System.out.println("Player P2's has won!");
				break;
			case '3':
				System.out.println("Player P3's has won!");
				break;
			default:
				System.out.println("*Invalid player");
				break;
		}
	}
}
