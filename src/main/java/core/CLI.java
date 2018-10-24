package core;

import java.util.Scanner;

public class CLI implements UserInterface {
	Scanner userInput = new Scanner(System.in);

	public void message(String mes) {
		System.out.println(mes);
	}

	public char response(String mes) {
		System.out.print(mes);
		
		char u = userInput.next().charAt(0);
		userInput.nextLine(); //consume \n
		//userInput.close(); can't close due to System.in being unable to reopen

		return u;
	}
}
