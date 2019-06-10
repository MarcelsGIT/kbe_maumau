package cardGame;

import java.util.List;

import cardGame.modell.MauMau;
import cards.modell.Card;
import cards.modell.CardDeck;
import cards.CardDeckService;
import cards.modell.Symbol;
import rules.modell.MauMauRules;
import userAdministration.modell.MauMauUser;
import userAdministration.UserService;

public interface MauMauService {
	
	/**
	 * Starts the game
	 * 
	 * @param userList All the players that are in the game
	 * @param RuleSet All specified rules
	 * @param cardDeck The initial card deck
	 * @param graveyard The empty card deck
	 * @return The newly started game
	 */
	void startGame(List<MauMauUser> userList, CardDeck cardDeck, CardDeck graveyard, MauMauRules rules,
			int currentPlayerIndex, boolean endGame, MauMauUser winner, int amountSeven, Symbol userwish, MauMau mauMau);
	
	
	/**
	 * Specifies which user starts the game
	 * 
	 * @param userList All Users that are in the game
	 * @return the user that will begin
	 */
	MauMau chooseWhoStarts(MauMau mauMau);
	
	
	/**
	 * The next player
	 */
	MauMau nextPlayer(MauMau mauMau); // muss in den RulesTest
	
	// Diagramm in PNG oder JPG
	
	
	/** Ends the game
	 * 
	 * @param game The current Mau-Mau game
	 * @return true if game should be ended 
	 */
	MauMau endGame(MauMau mauMau, boolean endGame);
	
	
	/** Save the winner to the database
	 * 
	 * @param user The User that won the game
	 */
	MauMau insertWinner(MauMauUser user, MauMau mauMau);
	
	
	/** Skips the user for this round
	 * 
	 * @param user The user that has to skip the round
	 * @param maumau 
	 */
	 MauMau skipRound(MauMauUser user, MauMau mauMau);

	
	/**
	 * Deal penalty cards to user
	 * 
	 * @return The user's new hand 
	 */
	List<Card> dealPenaltyCards(int amount, MauMau mauMau);

	MauMau dealCardsToPlayers(MauMau maumau, int amountCards, CardDeckService cardDeckService);
	
	MauMau giveCardToUser(MauMau maumau, CardDeckService cardDeckService, UserService userService);
	

}
