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
	 * @param mauMau: game with userList
	 * @return mauMau: with the user that starts as current player
	 */
	MauMau chooseWhoStarts(MauMau mauMau);
	
	
	/**
	 * Defines the next player
	 * @param mauMau: mauMau with userlist 
	 * @return mauMau: with next player as current player
	 */
	MauMau nextPlayer(MauMau mauMau); 
	
	
	
	/** 
	 * Ends the game
	 * @param mauMau game that should be or not ended
	 * @return MauMau mauMau with field endGame set true if game should be ended, fals otherwise
	 */
	MauMau endGame(MauMau mauMau, boolean endGame);
	
	
	/** Saves the winner in the mauMau object
	 * @param user: The user that won the game
	 * @return mauMau: mauMau with winner
	 */
	MauMau insertWinner(MauMauUser user, MauMau mauMau);
	
	
	/** 
	 * Skips the user for this round
	 * @param user The user that has to skip the round
	 * @param mauMau with current player the player after the one that had to skip the round
	 */
	 MauMau skipRound(MauMauUser user, MauMau mauMau);

	
	/**
	 * Deals all the penalty cards to the user that the user has to take
	 * @return mauMau: The game with the new amount of cards in the current's players hand
	 */
	MauMau dealPenaltyCards(int amount, MauMau mauMau);
		
	
	/**
	 * Deals Cards to players
	 * @param maumau
	 * @param amountCards
	 * @param cardDeckService
	 * @return mauMau: game with cards that were dealed to players
	 */
	MauMau dealCardsToPlayers(MauMau maumau, int amountCards);
	
	
	
	/**
	 * Gives a card to the user
	 * @param maumau
	 * @param cardDeckService
	 * @param userService
	 * @return mauMau: game with current user hand size plus one card
	 */
	MauMau giveCardToUser(MauMau maumau);
	
	/**
	 * Gives the amount of cards to the user that the user has to take
	 * @param maumau
	 * @param cardDeckService
	 * @param userService
	 * @return MauMau: game with current player's hand that contains the additional cards
	 */
	MauMau giveAllCardsToUserThatUserHasToTake(MauMau maumau);
	
	/**
	 * Takes the card the user played and removes it from userhand
	 * @param maumau
	 * @param cardDeckService
	 * @param validCard
	 * @return mauMau: game with one card less in current user's hand
	 */
	MauMau playCardProcedure(MauMau maumau, Card validCard);
	
	
	/**
	 *Sets mau or not depending on boolean mau
	 * @param maumau
	 * @param mau
	 * @return mauMau: game with current player's mau set or not set 
	 */
	MauMau shoutMauProcedure(MauMau maumau, boolean mau);
	
	/**
	 * Sets mauMau or not depending on boolean shoutMaumau
	 * @param maumau
	 * @param shoutMaumau
	 * @return Maumau: game with current player's mauMau set or not set 
	 */
	MauMau shoutMauMauProcedure(MauMau maumau, boolean shoutMaumau);
	
	
	
	/**
	 * Prepares Users, userHands, CardDeck, graveYard and rules for the game
	 * @param userNames
	 * @param userService
	 * @param cardDeckService
	 * @param rules
	 * @param rulesService
	 * @return MauMau: game prepared for the game start
	 */
	MauMau handleGameStart(List<String> userNames, MauMauRules rules, int amountCardsForUser);



	
	

}
