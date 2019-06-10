package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import cardGame.modell.MauMau;
import cards.modell.Symbol;
import cards.modell.Value;
import userAdministration.modell.CardGameUser;

@Component
public class UserCommunication {

	private UserInputValidation userInputValidation = new UserInputValidation();
	private UserInformation userInformation = new UserInformation();

	private Scanner input = new Scanner(System.in);
	
	
	/**
	 * asks user if user wants to shout mau
	 * @return boolean whether user wants to shout mau or not
	 */
	public boolean askIfUserWantsToShoutMau() {
		System.out.println("Hey, you have two cards left! Would you like to shout mau?");
		String yesOrNo = getAnwerToYesOrNoQuestion();
		boolean mau = userInputValidation.yesOrNo(yesOrNo);
		return mau;
	}
	
	/**
	 * asks user if user wants to shout maumau
	 * @return boolean whether user wants to shout maumau or not
	 */
	public boolean askIfUserWantsToShoutMauMau() {
		System.out.println("Hey, you have one card left - would you like to shout maumau?");
		String yesOrNo = getAnwerToYesOrNoQuestion();
		boolean maumau = userInputValidation.yesOrNo(yesOrNo);
		return maumau;
		
	}
	
	

	/** gets user wish 
	 * @param wishingUser
	 * @return String that contains symbol
	 */
	public String askForUserWish(CardGameUser wishingUser) {
		System.out.println(wishingUser.getUsername() + ", what's your wish? Please answer by typing the symbol");
		userInformation.informAboutValidSymbols();
		String symbol = "";
		symbol = symbolInputCommunication();
		return symbol;
	}


	/**
	 * asks is user wants to take or play a card
	 * @return playOrTake: "p" ion case of play, "t" in case of take card
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
	 * asks which card user wants top play
	 * @param user
	 * @return card that user wants to play
	 */
	public int askForCardUserWantsToPlay(CardGameUser user) {
		boolean cardInHand = false;
		int index = 1;
		
		System.out.println(user.getUsername() + ", which of your cards would you like to play?");
		userInformation.giveCurrentCardDeckInfo(user.getHand());			
		System.out.println("Please type the Index of the card you would like to play:");
		
		while(!cardInHand) {
			try {
				index = input.nextInt();
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
		}

		return index;
	}

	/**
	 * takes user input for Symbol
	 * @return symbol as String
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
	 * takes user input for Value
	 * @return value as String
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
	 * Converts String to Symbol
	 * @param input symbol String
	 * @return Symbol
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
	 * Converts String to Value
	 * @param input value String
	 * @return value
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