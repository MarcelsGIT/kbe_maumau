package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import cardGame.modell.MauMau;

// TODO: Auto-generated Javadoc
/**
 * The Class WelcomeGame.
 */
@Component
public class WelcomeGame {

	private Scanner input;

	/**
	 * Instantiates a new welcome game.
	 */
	public WelcomeGame() {
		this.input = new Scanner(System.in);
	}

	/**
	 * Welcome msg.
	 */
	public void welcomeMsg() {
		System.out.println("Hello and Welcome to MauMau!");

	}

	/**
	 * Ask how many users.
	 *
	 * @return the int
	 */
	public int askHowManyUsers() {
		int userAmount = 0;
		boolean correctInput = false;
		
		while (correctInput == false) {
		System.out.println(
				"How many players would like to participate in the game?\nPlease type 1,2,3 or 4. If you type 1 a virtual user will automatically be created. Otherwise have fun playing with your human friends!");
		try {
			userAmount = input.nextInt();
			input.nextLine();
			if (userAmount == 1 ||userAmount == 2 || userAmount == 3 ||userAmount == 4 ||userAmount == 5) {
				correctInput = true;
			}else {
				if(userAmount>5 ) {
					System.out.println("Five players is the maximum!");
				}if(userAmount ==0){
					System.out.println("At least one person should play!");
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("Please type a number");
			input.nextLine();
			continue;

		}
		}

		return userAmount;
	}

	/**
	 * Ask user names.
	 *
	 * @param amountPlayers the amount players
	 * @return the list
	 */
	public List<String> askUserNames(int amountPlayers) {
		List<String> userNames = new ArrayList<>();
		String userName = "";

		for (int i = 1; i <= amountPlayers; i++) {
			System.out.println("What is the name of player number " + i + "?");
			userName = input.nextLine();
			if (userName != "") {
				userNames.add(userName);
				userName = "";
			}
		}
		return userNames;
	}

	/**
	 * Ask return to existing game.
	 *
	 * @param unfinishedGames the unfinished games
	 * @return the mau mau
	 */
	public MauMau askReturnToExistingGame(List<MauMau> unfinishedGames) {
		System.out.println(
				"There are unfinished Games for the current users.\nDo you want to Return to an existing Game?");
		int index = 0;

		String response = "";
		MauMau selectedGame = null;
		do {
			for (MauMau mm : unfinishedGames) {
				System.out.println("Press " + index + " for Game [" + mm.getId() + "]");
				index++;
			}
			System.out.println("Enter a number for the certain game or 'n' to create a new game");
			response = input.nextLine();
			if (response.matches("[0-9]+")
					&& (Integer.parseInt(response) >= 0 || Integer.parseInt(response) < unfinishedGames.size()))
				selectedGame = unfinishedGames.get(Integer.parseInt(response));

		} while (selectedGame == null && !response.toLowerCase().equals("n"));
		return selectedGame;
	}
}
