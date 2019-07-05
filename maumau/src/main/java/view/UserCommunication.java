package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import cardGame.modell.MauMau;
import cards.modell.Symbol;
import cards.modell.Value;
import userAdministration.modell.CardGameUser;

// TODO: Auto-generated Javadoc
/**
 * The Class UserCommunication.
 */
@Component
public class UserCommunication {

	private UserInputValidation userInputValidation = new UserInputValidation();
	private UserInformation userInformation = new UserInformation();

	private Scanner input = new Scanner(System.in);
	
	
	/**
	 * Ask if user wants to shout mau.
	 *
	 * @return true, if successful
	 */
	public boolean askIfUserWantsToShoutMau() {
		System.out.println("Hey, you have two cards left! Would you like to shout mau?");
		String yesOrNo = getAnwerToYesOrNoQuestion();
		boolean mau = userInputValidation.yesOrNo(yesOrNo);
		return mau;
	}
	
	/**
	 * Ask if user wants to shout mau mau.
	 *
	 * @return true, if successful
	 */
	public boolean askIfUserWantsToShoutMauMau() {
		System.out.println("Hey, you have one card left - would you like to shout maumau?");
		String yesOrNo = getAnwerToYesOrNoQuestion();
		boolean maumau = userInputValidation.yesOrNo(yesOrNo);
		return maumau;
		
	}
	
	

	/**
	 * Ask for user wish.
	 *
	 * @param wishingUser the wishing user
	 * @return the string
	 */
	public String askForUserWish(CardGameUser wishingUser) {
		System.out.println(wishingUser.getUsername() + ", what's your wish? Please answer by typing the symbol");
		userInformation.informAboutValidSymbols();
		String symbol = "";
		symbol = symbolInputCommunication();
		return symbol;
	}


	/**
	 * Ask if play card or take card.
	 *
	 * @return the string
	 */
	public String askIfPlayCardOrTakeCard() {
		System.out.println("Do you want to play a card (PRESS \"P\") or do you want to take a card?(PRESS \"T\")");
		String playOrTake = "";
		boolean correctInput = false;
		while (correctInput == false) {
			playOrTake = input.nextLine();
			correctInput = userInputValidation.verifyPlayOrTakeInput(playOrTake);
			if (correctInput == false) {
				System.out.println("Sorry your input was not valid. Press \"P\" to play or \"T\" to take a card.");
			}
		}
		return playOrTake;
	}

	/**
	 * Ask for card user wants to play.
	 *
	 * @param user the user
	 * @return the int
	 */
	public Integer askForCardUserWantsToPlay(CardGameUser user) {
		boolean cardInHand = false;
		String sIndex = "";
		Integer index = null;
		
		System.out.println(user.getUsername() + ", which of your cards would you like to play?");
		userInformation.giveCurrentCardDeckInfo(user.getHand());			
		
		
		do {			
			System.out.println("\nPlease type in the index of the card you would like to play, press \"t\" to take a card or \"q\" to quit the game:");
			sIndex = input.nextLine();
			if (sIndex.matches("[0-9]+") && (Integer.parseInt(sIndex) >= 0 || Integer.parseInt(sIndex) <user.getHand().size()))
				index = Integer.parseInt(sIndex);
			
			if(sIndex.equalsIgnoreCase("t"))
				index = -1;
			
			if(sIndex.equalsIgnoreCase("q"))
				index = -99;

		} while ( index == null );
		/*while(!cardInHand) {
			try {
				sIndex = input.nextInt();
				input.nextLine();
				if(index >= 0 && index <= user.getHand().size()-1) {
					cardInHand = true;
				} else {
					System.out.println("Invalid index. Please type a correct index:");
				}
			} catch(InputMismatchException e) {
				System.out.println("Invalid input. Please type the index of the card you want to play:");
				input.nextLine();
			}
		}*/

		return index;
	}

	/**
	 * Symbol input communication.
	 *
	 * @return the string
	 */
	public String symbolInputCommunication() {
		String symbol = "";
		boolean correctInput = false;
		while (correctInput == false) {
			symbol = input.nextLine();
			correctInput = userInputValidation.verifySymbolInput(symbol);
			if (correctInput == false) {
				System.out.println("Sorry your input was not valid. Please try again");
			}
		}
		return symbol;

	}

	/**
	 * Value input communication.
	 *
	 * @return the string
	 */
	public String valueInputCommunication() {
		String value = "";
		boolean correctInput = false;
		while (correctInput == false) {
			value = input.nextLine();
			correctInput = userInputValidation.verifyValueInput(value);
			if (correctInput == false) {
				System.out.println("Sorry your input was not valid. Please try again");
			}
		}
		return value;
	}

	/**
	 * Gets the symbol from string.
	 *
	 * @param input the input
	 * @return the symbol from string
	 */
	public Symbol getSymbolFromString(String input) {
		Symbol symbol = null;
		for (Symbol symb : Symbol.values()) {
			if (input.toUpperCase().equals(symb.toString())) {
				symbol = symb;
				break;
			}
		}
		return symbol;
	}

	/**
	 * Gets the value from string.
	 *
	 * @param input the input
	 * @return the value from string
	 */
	public Value getValueFromString(String input) {
		Value value = null;
		for (Value val : Value.values()) {
			if (input.toUpperCase().equals(val.toString())) {
				value = val;
				break;
			}
		}
		return value;
	}

	
	/**
	 * Gets the anwer to yes or no question.
	 *
	 * @return the anwer to yes or no question
	 */
	public String getAnwerToYesOrNoQuestion() {
		System.out.println("For \"yes\" please type \"j\" for \"no\" please type \"n\"");
				String answer = "";
				boolean correctInput = false;
				while (correctInput == false) {
					answer = input.nextLine();
					correctInput = userInputValidation.verifyYesNoInput(answer);
					if (correctInput == false) {
						System.out.println("Sorry your input was not valid. Press \"J\" for yes or \"N\" for no.");
					}
				}return answer;
				
	}
	
	/**
	 * Play again.
	 *
	 * @param mauMau the mau mau
	 * @return true, if successful
	 */
	public boolean playAgain(MauMau mauMau) {
		boolean playAgain = false;
		if(mauMau.getWinner() != null) {
			System.out.println("Do you want to play again? (y/n)");
			String yesOrNo = getAnwerToYesOrNoQuestion();
			playAgain = userInputValidation.yesOrNo(yesOrNo);
		}
		return playAgain;
		
			//mauMau.setEndGame(endGame);
		//return mauMau;
		
	}

}