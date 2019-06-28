package view;

import cards.modell.Symbol;
import cards.modell.Value;

import java.util.List;

import org.springframework.stereotype.Component;

import cards.modell.Card;

// TODO: Auto-generated Javadoc
/**
 * The Class UserInputValidation.
 */
@Component
public class UserInputValidation {

	/**
	 * Verify symbol input.
	 *
	 * @param input the input
	 * @return true, if successful
	 */
	public boolean verifySymbolInput(String input){

		if (input.toUpperCase().equals(Symbol.CLUB.toString()) || input.toUpperCase().equals(Symbol.DIAMOND.toString()) || input.toUpperCase().equals(Symbol.HEART.toString()) || input.toUpperCase().equals(Symbol.SPADE.toString())) {
			return true;
		}else {
			return false;
		}

	}
	
	/**
	 * Verify value input.
	 *
	 * @param input the input
	 * @return true, if successful
	 */
	public boolean verifyValueInput(String input) {	
		boolean correctInput = false;
		for (Value value: Value.values()) {
			if(input.toUpperCase().equals(value.toString())) {
				correctInput = true;
				break;
			}
		}
		return correctInput;
	}
	
	/**
	 * Verify play or take input.
	 *
	 * @param input the input
	 * @return true, if successful
	 */
	public boolean verifyPlayOrTakeInput(String input) {
		
		if (input.toLowerCase().equals("p") || input.toLowerCase().equals("t")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	/**
	 * Verify if card in user hand.
	 *
	 * @param cardList the card list
	 * @param inputCard the input card
	 * @return true, if successful
	 */
	public boolean verifyIfCardInUserHand(List<Card> cardList, Card inputCard) {
		boolean correctCard = false;
		for (Card card: cardList) {
			if (card.getSymbol() == inputCard.getSymbol() && card.getValue() == inputCard.getValue()){
				correctCard = true;
				break;
			}
		}return correctCard;
		
		
	}
	
	
	/**
	 * Verify yes no input.
	 *
	 * @param input the input
	 * @return true, if successful
	 */
	public boolean verifyYesNoInput(String input) {
		if (input.equalsIgnoreCase("j")|| input.equalsIgnoreCase("n")) {
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * Yes or no.
	 *
	 * @param yesOrNo the yes or no
	 * @return true, if successful
	 */
	public boolean yesOrNo(String yesOrNo) {
		if (yesOrNo.equalsIgnoreCase("j")) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	
	
}
