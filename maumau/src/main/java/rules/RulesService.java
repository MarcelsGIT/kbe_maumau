package rules;

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
	
	/** 
	 * Determine if the card that is placed is valid (can be placed on the deck)
	 * 
	 * @param user The User that wishes a card
	 * @param maumau
	 * @return the card that the user wishes
	 */
	boolean checkIsValid(Card lastCard, Card userCard, MauMauRules mauMauRules);
	
	
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
	
	
	boolean checkIfUserCanPlay(MauMau maummau, MauMauRules mauMauRules, Card lastCard);
	
	boolean checkIfSpecialCard(Card card);
	

	
	
}
