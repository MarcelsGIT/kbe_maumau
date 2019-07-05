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
				if (userAmount == 1 || userAmount == 2 || userAmount == 3 || userAmount == 4 || userAmount == 5) {
					correctInput = true;
				} else {
					if (userAmount > 5) {
						System.out.println("Five players is the maximum!");
					}
					if (userAmount == 0) {
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
				System.out.println("Press " + index + " for Game [" + mm.getId() + ", " + "Players: "
						+ mm.getPlayers().size() + "]");
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

	public MauMau configureGame(MauMau maumau) {
		System.out.println(
				"You can configure the rules to improve your game experience. Do you want to configure the rules?");

		if (this.closedQuestionInput()) {
			String inp;
			do {
				System.out.println("The following parameters can be adjusted: \n" + "\n\t[0] Jack on Jack " + "("
						+ maumau.getRuleSet().isJackOnJack() + ")"
						+ " - Is it allowed to play a Jack after a Jack was played?" + "\n\t[1] Jack on Everything "
						+ "(" + maumau.getRuleSet().isJackOnEverything() + ")"
						+ " - Is it allowed to play a Jack although he has a different suit than the last one?"
						+ "\n\t[2] penalty cards on 7 " + "(" + maumau.getRuleSet().getPenaltyOnSeven() + ")"
						+ " - How many cards does a user have to take for each seven that was played?"
						+ "\n\t[3] play after penalty" + "(" + maumau.getRuleSet().isPlayAfterPenalty() + ")"
						+ " - Is a user allowed to play a card when he can't play a seven after another seven was played?");
				System.out.println("\nPlease press a number to select the setting you want to change, \"d\" for setting back the game settings or \"q\" to quit.");
				inp = this.input.nextLine();

				if (inp.matches("[0-9]+") && Integer.parseInt(inp) >= 0 && Integer.parseInt(inp) < 4) {
					maumau = this.changeSetting(maumau, Integer.parseInt(inp));
				}else if (inp.matches("[0-9]+") && (Integer.parseInt(inp) < 0 || Integer.parseInt(inp) > 4)) {
					System.out.println("This is out of the scope. Try again.");
				}else if (inp.equalsIgnoreCase("d")) {
					maumau.getRuleSet().setDefaultRules();
				}

			} while (!inp.equalsIgnoreCase("q"));
		}

		return maumau;
	}

	private MauMau changeSetting(MauMau maumau, int setting) {
		String inp = "";

		switch (setting) {
		case 0: {
			System.out.println("\nIs it allowed to play a Jack after a Jack was played? "
					+ "\nPlease choose \"j\" for yes, \"n\" for no or \"q\" to remain unchanged.");
			break;
		}
		case 1: {
			System.out.println("\nIs it allowed to play a Jack although he has a different suit than the last one?"
					+ "\nPlease choose \"j\" for yes, \"n\" for no or \"q\" to remain unchanged.");
			break;
		}
		case 2: {
			System.out.println("\nHow many cards does a user have to take for each seven that was played?(0-7)"
					+ "\nPlease choose a number or press \"q\" to remain unchanged.");
			break;
		}case 3:{
			System.out.println("\nIs a user allowed to play a card when he can't play a seven after another seven was played?"
					+ "\\nPlease choose \"j\" for yes, \"n\" for no or \"q\" to remain unchanged.");
			break;
		}
		}
		do {
			switch (setting) {
			case 0: {
				inp = this.input.nextLine();
				if (inp.equalsIgnoreCase("j")) {
					maumau.getRuleSet().setJackOnJack(true);

				} else if (inp.equalsIgnoreCase("n")) {
					maumau.getRuleSet().setJackOnJack(false);
					
				} else if (!inp.equalsIgnoreCase("j") || !inp.equalsIgnoreCase("n") || !inp.equalsIgnoreCase("q")) {
					System.out.println("Sorry this is not possible. Type \"j\", \"n\", \"q\". Try again.");
				}
				break;
			}
			case 1: {
				inp = this.input.nextLine();
				if (inp.equalsIgnoreCase("j")) {
					maumau.getRuleSet().setJackOnEverything(true);

				} else if (inp.equalsIgnoreCase("n")) {
					maumau.getRuleSet().setJackOnEverything(false);

				} else if (!inp.equalsIgnoreCase("j") || !inp.equalsIgnoreCase("n") || !inp.equalsIgnoreCase("q")) {
					System.out.println("Sorry this is not possible. Type \"j\", \"n\", \"q\". Try again.");
				}
				break;
			}
			case 2: {
				inp = this.input.nextLine();

				if (inp.matches("[0-9]+") && Integer.parseInt(inp) >= 0 && Integer.parseInt(inp) < 8) {
					maumau.getRuleSet().setPenaltyOnSeven(Integer.parseInt(inp));
					inp = "q";
				} else if (inp.matches("[0-9]+") && (Integer.parseInt(inp) < 0 || Integer.parseInt(inp) >= 8)) {
					System.out.println(
							"Sorry this is not possible. Please choose a number between 0 and 7 or type \"q\" to remain unchanged. Try again.");
				}
				break;
			}
			case 3:{
				inp = this.input.nextLine();
				if (inp.equalsIgnoreCase("j")) {
					maumau.getRuleSet().setPlayAfterPenalty(true);

				} else if (inp.equalsIgnoreCase("n")) {
					maumau.getRuleSet().setPlayAfterPenalty(false);

				} else if (!inp.equalsIgnoreCase("j") || !inp.equalsIgnoreCase("n") || !inp.equalsIgnoreCase("q")) {
					System.out.println("Sorry this is not possible. Type \"j\", \"n\", \"q\". Try again.");
				}
				break;
			}
			}
		} while (!inp.equalsIgnoreCase("j") && !inp.equalsIgnoreCase("n") && !inp.equalsIgnoreCase("q"));

		return maumau;

	}

	private boolean closedQuestionInput() {
		System.out.println("For yes please type \"j\" for no please type \"n\"");
		String answer = "";
		boolean response = false;
		do {
			answer = input.nextLine();

			if (!answer.equalsIgnoreCase("j") && !answer.equalsIgnoreCase("n"))
				System.out.println("Sorry your input was not valid. Press \"J\" for yes or \"N\" for no.");

			if (answer.equalsIgnoreCase("j"))
				response = true;
		} while (!answer.equalsIgnoreCase("j") && !answer.equalsIgnoreCase("n"));

		return response;
	}
}
