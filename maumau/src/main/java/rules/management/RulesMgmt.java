package rules.management;

import cards.modell.Card;
import cards.CardDeckService;
import cards.modell.Symbol;
import cards.modell.Value;
import rules.RulesService;
import rules.modell.MauMauRules;
import userAdministration.modell.MauMauUser;

import java.util.List;

import org.springframework.stereotype.Component;

import cardGame.modell.MauMau;

// TODO: Auto-generated Javadoc
/**
 * The Class RulesMgmt.
 */
@Component
public class RulesMgmt implements RulesService {

	/**
	 * Checks if is eight.
	 *
	 * @param card the card
	 * @param mauMauRules the mau mau rules
	 * @return true, if is eight
	 */
	public boolean isEight(Card card, MauMauRules mauMauRules) {
		if(card.getValue() == Value.EIGHT) return true;
		return false;
	}

	/**
	 * Checks if is seven.
	 *
	 * @param card the card
	 * @param mauMauRules the mau mau rules
	 * @return true, if is seven
	 */
	public boolean isSeven(Card card, MauMauRules mauMauRules) {
		if(card.getValue() == Value.SEVEN) return true;
		return false;
	}

	/**
	 * Checks if is bube.
	 *
	 * @param card the card
	 * @param mauMauRules the mau mau rules
	 * @return true, if is bube
	 */
	public boolean isBube(Card card, MauMauRules mauMauRules) {
		if(card.getValue() == Value.JACK) return true;
		return false;
	}
	
	
	//Tests ab hier

	/**
	 * Check is valid.
	 *
	 * @param lastCard the last card
	 * @param userCard the user card
	 * @param rules the rules
	 * @return true, if successful
	 */
	public boolean checkIsValid(Card lastCard, Card userCard, MauMauRules rules) {
		boolean valid = false;
		if( rules.isJackOnJack() && (lastCard.getValue() == Value.JACK && userCard.getValue() == Value.JACK) ) {
			valid = true;
		}else if( rules.isJackOnEverything() && userCard.getValue() == Value.JACK && !(lastCard.getValue() == Value.JACK) ) {
			valid = true;
		}else if( lastCard.getSymbol() == userCard.getSymbol() ) {
			valid = true;
		}else if(lastCard.getValue() == userCard.getValue() && !(lastCard.getValue() == Value.JACK)) {
			valid = true;
		}
		return valid;
	}
	
	/**
	 * Shouted mau.
	 *
	 * @param player the player
	 * @return true, if successful
	 */
	public boolean shoutedMau(MauMauUser player) {
		if(player.getHand().size() == 2 && player.isMau()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Shouted mau mau.
	 *
	 * @param player the player
	 * @return true, if successful
	 */
	public boolean shoutedMauMau(MauMauUser player) {
		if(player.getHand().size() == 1 && player.isMaumau()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Check shout mau possible.
	 *
	 * @param player the player
	 * @param mauMauRules the mau mau rules
	 * @return true, if successful
	 */
	public boolean checkShoutMauPossible(MauMauUser player, MauMauRules mauMauRules){
		if(player.getHand().size() == 2) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Check shout mau mau possible.
	 *
	 * @param player the player
	 * @param mauMauRules the mau mau rules
	 * @return true, if successful
	 */
	public boolean checkShoutMauMauPossible(MauMauUser player, MauMauRules mauMauRules) {
		if (player.getHand().size() == 1 ) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Check if user wish fulfilled.
	 *
	 * @param userCard the user card
	 * @param userwish the userwish
	 * @return true, if successful
	 */
	public boolean checkIfUserWishFulfilled(Card userCard, Symbol userwish) {
		if (userCard.getSymbol() == userwish) {
			return true;
		}else {
			return false;
		}	
	}

	/**
	 * Check if user can play.
	 *
	 * @param amountSeven the amount seven
	 * @param rules the rules
	 * @param lastCard the last card
	 * @param userCards the user cards
	 * @param userWish the user wish
	 * @return true, if successful
	 */
	public boolean checkIfUserCanPlay(int amountSeven, MauMauRules rules, Card lastCard, List<Card> userCards, Symbol userWish) {
		boolean canPlay = false;
		if (isSeven(lastCard, rules) && amountSeven>0) {
			canPlay = checkIfUserHasSeven(userCards);
		} else if (isBube(lastCard, rules)) {
			canPlay = checkIfUserHasWishedSymbol(userCards, userWish);
		} else {
			canPlay = checkIfUserHasFittingCard(userCards, lastCard, rules);
		}
		return canPlay;

	}
	
	
	/**
	 * Check if user has seven.
	 *
	 * @param userCards the user cards
	 * @return true, if successful
	 */
	public boolean checkIfUserHasSeven(List<Card> userCards) {
		boolean hasSeven = false;
		for (Card card : userCards) {
			if (card.getValue() == Value.SEVEN) {
				hasSeven = true;
			}
		}
		return hasSeven;
	}

	/**
	 * Check if user has wished symbol.
	 *
	 * @param userCards the user cards
	 * @param userwish the userwish
	 * @return true, if successful
	 */
	public boolean checkIfUserHasWishedSymbol(List<Card> userCards, Symbol userwish) {
		boolean hasUserWish = false;
		for (Card card : userCards) {
			if (card.getSymbol() == userwish) {
				hasUserWish = true;
			}
		}
		return hasUserWish;
	}

	/**
	 * Check if user has fitting card.
	 *
	 * @param userCards the user cards
	 * @param lastCard the last card
	 * @param rules the rules
	 * @return true, if successful
	 */
	public boolean checkIfUserHasFittingCard(List<Card> userCards, Card lastCard, MauMauRules rules) {
		boolean hasFittingCard = false;
		for (Card card : userCards) {
			if (checkIsValid(lastCard, card, rules)) {
				hasFittingCard = true;
			}
		}
		return hasFittingCard;
	}
	
	
	/**
	 * Check if special card.
	 *
	 * @param card the card
	 * @return true, if successful
	 */
	public boolean checkIfSpecialCard(Card card) {
		if(card.getValue()==Value.EIGHT || card.getValue()==Value.SEVEN|| card.getValue()==Value.JACK) {
			return true;
		}else {
			return false;
		}
	}
	
	
	/**
	 * Valid card or not valid card.
	 *
	 * @param mostRecentCard the most recent card
	 * @param card the card
	 * @param userwish the userwish
	 * @param mauMauRules the mau mau rules
	 * @return true, if successful
	 */
	public boolean validCardOrNotValidCard(Card mostRecentCard, Card card, Symbol userwish, MauMauRules mauMauRules) {
		boolean valid = false;
		if (isBube(mostRecentCard, mauMauRules)) {
			valid = checkIfUserWishFulfilled(card, userwish);
		}else if(isSeven(mostRecentCard, mauMauRules)) {
			if (card.getValue() == Value.SEVEN) {
				valid = true;
				}
			
		}else {
			valid = checkIsValid(mostRecentCard, card, mauMauRules);
		}
		return valid;
	}
	

	
	
	
	
	
	
	}


