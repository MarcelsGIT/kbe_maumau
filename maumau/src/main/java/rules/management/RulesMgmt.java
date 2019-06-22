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

@Component
public class RulesMgmt implements RulesService {

	public boolean isEight(Card card, MauMauRules mauMauRules) {
		if(card.getValue() == Value.EIGHT) return true;
		return false;
	}

	public boolean isSeven(Card card, MauMauRules mauMauRules) {
		if(card.getValue() == Value.SEVEN) return true;
		return false;
	}

	public boolean isBube(Card card, MauMauRules mauMauRules) {
		if(card.getValue() == Value.JACK) return true;
		return false;
	}
	
	
	//Tests ab hier

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
	
	public boolean shoutedMau(MauMauUser player) {
		if(player.getHand().size() == 2 && player.isMau()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean shoutedMauMau(MauMauUser player) {
		if(player.getHand().size() == 1 && player.isMaumau()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkShoutMauPossible(MauMauUser player, MauMauRules mauMauRules){
		if(player.getHand().size() == 2) {
			return true;
		}else {
			return false;
		}
	}

	public boolean checkShoutMauMauPossible(MauMauUser player, MauMauRules mauMauRules) {
		if (player.getHand().size() == 1 ) {
			return true;
		}else {
			return false;
		}
	}

	public boolean checkIfUserWishFulfilled(Card userCard, Symbol userwish) {
		if (userCard.getSymbol() == userwish) {
			return true;
		}else {
			return false;
		}	
	}

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
	
	
	public boolean checkIfUserHasSeven(List<Card> userCards) {
		boolean hasSeven = false;
		for (Card card : userCards) {
			if (card.getValue() == Value.SEVEN) {
				hasSeven = true;
			}
		}
		return hasSeven;
	}

	public boolean checkIfUserHasWishedSymbol(List<Card> userCards, Symbol userwish) {
		boolean hasUserWish = false;
		for (Card card : userCards) {
			if (card.getSymbol() == userwish) {
				hasUserWish = true;
			}
		}
		return hasUserWish;
	}

	public boolean checkIfUserHasFittingCard(List<Card> userCards, Card lastCard, MauMauRules rules) {
		boolean hasFittingCard = false;
		for (Card card : userCards) {
			if (checkIsValid(lastCard, card, rules)) {
				hasFittingCard = true;
			}
		}
		return hasFittingCard;
	}
	
	
	public boolean checkIfSpecialCard(Card card) {
		if(card.getValue()==Value.EIGHT || card.getValue()==Value.SEVEN|| card.getValue()==Value.JACK) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public boolean validCardOrNotValidCard(Card mostRecentCard, Card card, Symbol userwish, MauMauRules mauMauRules) {
		boolean valid = false;
		if (isBube(mostRecentCard, mauMauRules)) {
			valid = checkIfUserWishFulfilled(card, userwish);
		} else {
			valid = checkIsValid(mostRecentCard, card, mauMauRules);
		}
		return valid;
	}
	

	
	
	
	
	
	
	}


