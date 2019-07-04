package rules;

import java.util.List;

import cardGame.modell.MauMau;
import cards.modell.Card;
import cards.modell.Symbol;
import rules.modell.MauMauRules;
import userAdministration.modell.MauMauUser;


public interface RulesService {
	/**
	 * 
	 * @param card
	 * @return
	 */
	boolean isEight(Card card, MauMauRules mauMauRules);
	
	/**
	 * 
	 * @param card
	 * @return
	 */
	boolean isSeven(Card card, MauMauRules mauMauRules);
	
	/**
	 * 
	 * @param card
	 * @return
	 */
	boolean isBube(Card card, MauMauRules mauMauRules);
	
	
	
	//Tests ab hier
	
	/** 
	 * Determine if the card that is placed is valid (can be placed on the deck)
	 * 
	 * @param user The User that wishes a card
	 * @param maumau
	 * @return the card that the user wishes
	 */
	boolean checkIsValid(Card lastCard, Card userCard, Symbol userWish, MauMauRules mauMauRules);
	
	
	/**
	 * @param player
	 * @return boolean if shouting mau is possible or not
	 */
	boolean checkShoutMauPossible(MauMauUser player, MauMauRules mauMauRules);
	
	/**
	 * @param player
	 * @return boolean if shouting mau is possible or not
	 */
	boolean checkShoutMauMauPossible(MauMauUser player, MauMauRules mauMauRules);
	
	/**
	 * check if previous user's wish is fulfilled with new card
	 * @param userCard card which user tries to play 
	 * @param userwish Symbol that previous user wished
	 * @return true if valid action or false if not
	 */
	boolean checkIfUserWishFulfilled(Card userCard, Symbol userwish);
	
	
	/**
	 * checks if the user can play a card
	 * @param amountSeven if>0: user has to have a seven
	 * @param rules maumauRules
	 * @param lastCard last Card on the CardDeck
	 * @param userHand user's cards
	 * @return bollean if user could play or not
	 */
	boolean checkIfUserCanPlay(int amountSeven, MauMauRules rules, Card mostRecentCard, List<Card> userHand, Symbol userWish);
	
	/**
	 * checks if card is special or normal card
	 * @param card
	 * @return boolean - true if special card
	 */
	boolean checkIfSpecialCard(Card card);
	
	/**
	 * check if played Card was valid 
	 * @param mostRecentCard
	 * @param card
	 * @param userwish
	 * @param mauMauRules
	 * @return 
	 */
	boolean validCardOrNotValidCard(Card mostRecentCard, Card card, Symbol userwish, MauMauRules mauMauRules);

	
	
}
