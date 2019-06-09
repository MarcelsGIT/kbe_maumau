package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class WelcomeGame {
	
	private Scanner input;
	
	public WelcomeGame() {
		this.input = new Scanner(System.in);
	}

	public void welcomeMsg() {
		System.out.println("Hello and Welcome to MauMau!");
		
	}
	
	public int askHowManyUsers() {
		int userAmount = 0;
		System.out.println("How many players would like to participate in the game?\nPlease type 1,2,3 or 4.");
		try {
			userAmount = input.nextInt();
			input.nextLine();
		} catch(InputMismatchException e) {
			System.out.println("Please type a number");
		}
		
		return userAmount;
	}
	
	public List<String> askUserNames(int amountPlayers) {
		List <String> userNames = new ArrayList<>();
		String userName = "";

		for(int i=1; i<=amountPlayers; i++) {
			System.out.println("What is the name of player number " + i + "?");
			userName = input.nextLine();
			if(userName != "") {
				userNames.add(userName);
				userName = "";
			}
		}
		return userNames;
	}
}
