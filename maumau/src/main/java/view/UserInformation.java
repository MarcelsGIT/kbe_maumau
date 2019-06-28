package view;

import java.util.List;

import org.springframework.stereotype.Component;

import cardGame.modell.MauMau;
import cards.modell.Card;
import cards.modell.Symbol;
import cards.modell.Value;
import userAdministration.modell.CardGameUser;
import userAdministration.modell.MauMauUser;

// TODO: Auto-generated Javadoc
/**
 * The Class UserInformation.
 */
@Component
public class UserInformation {

	/**
	 * Welcome to game.
	 *
	 * @param maumau the maumau
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
	 * Give info about current player.
	 *
	 * @param nextPlayer the next player
	 */
	public void giveInfoAboutCurrentPlayer(CardGameUser nextPlayer) {
		System.out.println(">>> The next Player is " + nextPlayer.getUsername());
	}

	/**
	 * Give current card deck info.
	 *
	 * @param userCards the user cards
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
	 * Give skip round info.
	 *
	 * @param user the user
	 */
	public void giveSkipRoundInfo(CardGameUser user) {
		System.out.println(user.getUsername() + " has to skip this round");
	}

	/**
	 * Give current top card info.
	 *
	 * @param topCard the top card
	 */
	public void giveCurrentTopCardInfo(Card topCard) {
		System.out.println("The card on top of the graveyard card deck is [ " + topCard.getValue().toString() + " "
				+ topCard.getSymbol().toString() + " ]");
	}

	/**
	 * Give take cards info.
	 *
	 * @param user the user
	 * @param cardsToTake the cards to take
	 */
	public void giveTakeCardsInfo(CardGameUser user, int cardsToTake) {
		System.out.println("Sorry " + user.getUsername() + " you had to take "+ cardsToTake + " cards..");
	}

	/**
	 * Inform user about other users wish.
	 *
	 * @param userWhoWished the user who wished
	 * @param userWish the user wish
	 */
	public void informUserAboutOtherUsersWish(CardGameUser userWhoWished, Symbol userWish) {
		System.out.println("The user wish which has to be fulfilled is " + userWish);
	}
	
	

	/**
	 * Inform about valid symbols.
	 */
	public void informAboutValidSymbols() {
		System.out.println("Valid symbols are: ");
		for (Symbol symbol : Symbol.values()) {
			System.out.print(" " + symbol.toString() + " ");
		}
	}

	/**
	 * Inform about valid values.
	 */
	public void informAboutValidValues() {
		System.out.println("Valid values are: ");
		for (Value value : Value.values()) {
			System.out.print(" " + value.toString() + " ");
		}
	}
	
	/**
	 * Inform about missing permission for users action.
	 */
	public void informAboutMissingPermissionForUsersAction() {
		System.out.println("Sorry, but you are not allowed to do that.");
	}

	/**
	 * Inform about end of game.
	 *
	 * @param maumau the maumau
	 */
	public void informAboutEndOfGame(MauMau maumau) {
		System.out.println("Congratulations "+ maumau.getWinner().getUsername() + ", you won this game!");
	
	}
	
	/**
	 * Inform about cards that were taken.
	 *
	 * @param amountSeven the amount seven
	 */
	public void informAboutCardsThatWereTaken(int amountSeven){
		if (amountSeven == 0) {
			System.out.println("You couldn't play because you had no fitting cards. Thus, you had to take a card");
		}else {
			System.out.println("You couldn't play because you had no 7. Thus you had to take " + amountSeven*2 + " cards");
		}
		
	}
	
}
