package view;

import java.util.List;

import org.springframework.stereotype.Component;

import cardGame.modell.MauMau;
import cards.modell.Card;
import cards.modell.Symbol;
import cards.modell.Value;
import userAdministration.modell.CardGameUser;
import userAdministration.modell.MauMauUser;

@Component
public class UserInformation {

	/**
	 * Greetings for players at start of game
	 * @param maumau
	 */
	public void welcomeToGame(MauMau maumau) {
		List<MauMauUser> players = maumau.getPlayers();
		if(players != null) {
			for (MauMauUser player : players) {
				System.out.println("Hello " + player.getUsername());
				System.out.println("Welcome to the game and good luck!");
			}
		} else {
			System.out.println("There are no players in the game.");
		}
	}

	/**
	 * Gives informaton about next player
	 * @param nextPlayer
	 */
	public void giveInfoAboutCurrentPlayer(CardGameUser nextPlayer) {
		System.out.println(">>> The next Player is " + nextPlayer.getUsername());
	}

	/**
	 * Shows the user cards to user
	 * @param userCards
	 */
	public void giveCurrentCardDeckInfo(List<Card> userCards) {
		int i = 0;
		System.out.println("Your current hand contains the following cards:");
		for (Card card : userCards) {
			System.out.println("[" + i + "] " + card.getValue().toString() + " " + card.getSymbol().toString());
			i++;
		}
	}

	/**
	 * Gives information about who hast to skip the round
	 * @param user
	 */
	public void giveSkipRoundInfo(CardGameUser user) {
		System.out.println(user.getUsername() + " has to skip this round");
	}

	/**
	 * Gives the info about the last card played - the one on top of graveyard
	 * @param graveyard
	 */
	public void giveCurrentTopCardInfo(Card topCard) {
		System.out.println("The card on top of the graveyard card deck is [ " + topCard.getValue().toString() + " "
				+ topCard.getSymbol().toString() + " ]");
	}

	/**
	 * Gives information to user on how many cards user had to take
	 * @param user
	 * @param cardsToTake
	 */
	public void giveTakeCardsInfo(CardGameUser user, int cardsToTake) {
		System.out.println("Sorry " + user.getUsername() + " you had to take "+ cardsToTake + " cards..");
	}

	/**
	 * Informs user about userWish
	 * @param userWhoWished
	 * @param userWish
	 */
	public void informUserAboutOtherUsersWish(CardGameUser userWhoWished, Symbol userWish) {
		System.out.println("The user wish which has to be fulfilled is " + userWish);
	}
	
	

	/**
	 * Informs about valid card symbol inputs
	 */
	public void informAboutValidSymbols() {
		System.out.println("Valid symbols are: ");
		for (Symbol symbol : Symbol.values()) {
			System.out.print(" " + symbol.toString() + " ");
		}
	}

	/**
	 * Informs about valid card value inputs
	 */
	public void informAboutValidValues() {
		System.out.println("Valid values are: ");
		for (Value value : Value.values()) {
			System.out.print(" " + value.toString() + " ");
		}
	}
	
	public void informAboutMissingPermissionForUsersAction() {
		System.out.println("Sorry, but you are not allowed to do that.");
	}

	public void informAboutEndOfGame(MauMau maumau) {
		System.out.println("Congratulations "+ maumau.getWinner().getUsername() + ", you won this game!");
	
	}
	public void informAboutCardsThatWereTaken(int amountSeven){
		if (amountSeven == 0) {
			System.out.println("You couldn't play because you had no fitting cards. Thus, you had to take a card");
		}else {
			System.out.println("You couldn't play because you had no 7. Thus you had to take " + amountSeven*2 + " cards");
		}
		
	}
	
}
