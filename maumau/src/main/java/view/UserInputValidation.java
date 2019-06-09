package view;

import cards.modell.Symbol;
import cards.modell.Value;

import java.util.List;

import org.springframework.stereotype.Component;

import cards.modell.Card;

@Component
public class UserInputValidation {

	/**
	 * verifies if symbol input was correct
	 * @param input
	 * @return true if correct, false otherwise
	 */
	public boolean verifySymbolInput(String input){

		if (input.toUpperCase().equals(Symbol.CLUB.toString()) || input.toUpperCase().equals(Symbol.DIAMOND.toString()) || input.toUpperCase().equals(Symbol.HEART.toString()) || input.toUpperCase().equals(Symbol.SPADE.toString())) {
			return true;
		}else {
			return false;
		}

	}
	
	/**
	 *  verifies if value input was correct
	 * @param input
	 * @return true if correct, false otherwise
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
	 * verifies if input about taking or playing card("t"/"p") was correct
	 * @param input
	 * @return true if correct, false otherwise
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
	 * Verifies if card that user chose is one of the cards the user has on his/her hand
	 * @param cardList
	 * @param inputCard
	 * @return true if correct, false otherwise
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
	 * tests if input correct
	 * @param input
	 * @return boolean true if user input correct, wrong otherwise
	 */
	public boolean verifyYesNoInput(String input) {
		if (input.equalsIgnoreCase("j")|| input.equalsIgnoreCase("n")) {
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * test if answer was yes or no
	 * @param yesOrNo
	 * @return true if yes, no otherwise
	 */
	public boolean yesOrNo(String yesOrNo) {
		if (yesOrNo.equalsIgnoreCase("j")) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	
	
}
