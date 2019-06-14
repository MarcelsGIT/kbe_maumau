package cardGame;

import java.util.List;

import cardGame.modell.MauMau;
import cards.modell.Card;
import cards.modell.CardDeck;
import cards.CardDeckService;
import cards.modell.Symbol;
import rules.RulesService;
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
	MauMau startGame(List<MauMauUser> userList, CardDeck cardDeck, CardDeck graveyard, MauMauRules rules,
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
	MauMau nextPlayer(MauMau mauMau); 
	
	
	
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
	
	
	
	////Ab hier Tests!

	
	
	/**
	 * Deals Cards to players
	 * @param maumau
	 * @param amountCards
	 * @param cardDeckService
	 * @return maumau
	 */
	MauMau dealCardsToPlayers(MauMau maumau, int amountCards);
	
	
	
	/**
	 * Gives a card to the user
	 * @param maumau
	 * @param cardDeckService
	 * @param userService
	 * @return MauMau
	 */
	MauMau giveCardToUser(MauMau maumau);
	
	/**
	 * Gives the amount of cards to the user that the user has to take
	 * @param maumau
	 * @param cardDeckService
	 * @param userService
	 * @return MauMau
	 */
	MauMau handleUserHasToTakeCards(MauMau maumau);
	
	/**
	 * Takes the card the user played and removes it from userhand
	 * @param maumau
	 * @param cardDeckService
	 * @param validCard
	 * @return MauMau
	 */
	MauMau playCardProcedure(MauMau maumau, Card validCard);
	
	
	/**
	 *Sets mau or not depending on boolean mau
	 * @param maumau
	 * @param mau
	 * @return maumau
	 */
	MauMau shoutMauProcedure(MauMau maumau, boolean mau);
	
	/**
	 * Sets mauMau or not depending on boolean shoutMaumau
	 * @param maumau
	 * @param shoutMaumau
	 * @return Maumau
	 */
	MauMau shoutMauMauProcedure(MauMau maumau, boolean shoutMaumau);
	
	
	
	/**
	 * Prepares Users, userHands, CardDeck, graveYard and rules for the game
	 * @param userNames
	 * @param userService
	 * @param cardDeckService
	 * @param rules
	 * @param rulesService
	 * @return MauMau
	 */
	MauMau handleGameStart(List<String> userNames, MauMauRules rules);


	//void setServices(CardDeckService cardDeckService, UserService userService, RulesService rulesService);
	
	

}
