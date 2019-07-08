package rules;

import java.util.List;

import cards.modell.Card;
import cards.modell.Symbol;
import rules.modell.MauMauRules;
import userAdministration.modell.MauMauUser;


public interface RulesService {
	/**
	 * Checks if the card's value is eight
	 * @param card
	 * @param mauMauRules rulesConfiguration for current game
	 * @return boolean true, if eight, false otherwise
	 */
	boolean isEight(Card card, MauMauRules mauMauRules);
	
	/**
	 * Checks if the card's value is seven
	 * @param card
	 * @param mauMauRules rulesConfiguration for current game
	 * @return boolean true, if seven, false otherwise
	 */
	boolean isSeven(Card card, MauMauRules mauMauRules);
	
	/**
	 * Checks if the card's value is jack
	 * @param card
	 * @param mauMauRules rulesConfiguration for current game
	 * @return boolean true, if jack, false otherwise
	 */
	boolean isBube(Card card, MauMauRules mauMauRules);
	
	

	
	/** 
	 * Checks if card is generally valid
	 * @param lastCard: card on top of graveyard
	 * @param userCard: card for validation
	 * @param userWish: user's wish
	 * @param amountSeven: amount of sevens that were played 
	 * @param mauMauRules rulesConfiguration for current game
	 * @return the card that the user wishes
	 */
	boolean checkIsValid(Card lastCard, Card userCard, Symbol userWish, int amountSeven, MauMauRules mauMauRules);
	
	
	/**
	 * Checks if it is possible for user to shout mau
	 * @param player: player that wants to shout mau
	 * @param mauMauRules rulesConfiguration for current game
	 * @return boolean true if shouting mau is possible, false otherwise
	 */
	boolean checkShoutMauPossible(MauMauUser player, MauMauRules mauMauRules);
	
	/**
	 * Checks if it is possible for user to shout maumau
	 * @param player: player that wants to shout maumau
	 * @param mauMauRules rulesConfiguration for current game
	 * @return boolean true if shouting maumau is possible, false otherwise
	 */
	boolean checkShoutMauMauPossible(MauMauUser player, MauMauRules mauMauRules);
	
	/**
	 * Checks if previous user's wish is fulfilled with new card
	 * @param userCard card which user tries to play 
	 * @param userwish Symbol that previous user wished
	 * @return true if fulfilled, false otherwise
	 */
	boolean checkIfUserWishFulfilled(Card userCard, Symbol userwish);
	
	
	/**
	 * Checks if the user can play a card
	 * @param amountSeven if>0: user has to have a seven
	 * @param rules maumauRules
	 * @param lastCard last Card on the CardDeck
	 * @param userHand user's cards
	 * @return boolean if user could play or not
	 */
	boolean checkIfUserCanPlay(int amountSeven, MauMauRules rules, Card mostRecentCard, List<Card> userHand, Symbol userWish);
	
	/**
	 * Checks if card is special or normal card
	 * @param card
	 * @return boolean - true if special card
	 */
	boolean checkIfSpecialCard(Card card);
	
	/**
	 * Checks if played Card was valid 
	 * @param mostRecentCard
	 * @param card
	 * @param userwish
	 * @param mauMauRules
	 * @return boolean if true if valid, false otherwise
	 */
	boolean validCardOrNotValidCard(Card mostRecentCard, Card card, Symbol userwish, MauMauRules mauMauRules);

	
	
}
