package cards.modell;

import cards.modell.Card;
import cards.modell.Symbol;
import cards.modell.Value;

public class CardsFactory {

	/**
	 * Create a new Card
	 * 
	 * @param cardSymbol The card symbol, e.g. Hearts / Herz, Diamonds / Karo, Clubs / Kreuz, Spades / Pik
	 * @param cardNumber The card number: 7, 8, 9, 10, Bube, Dame, Koenig, Ass
	 * @return a new Card
	 */
	static public Card createCard(Symbol cardSymbol, Value value) {
		Card card = new Card(cardSymbol, value);
		return card;
	}
	
}
