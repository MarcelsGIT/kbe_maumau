package cards.modell;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CardDeck { // entity klasse
	private List<Card> cards; // kein privates Feld
	
	public CardDeck() {
		super();
	}

	public CardDeck(List<Card> cards) {
		super();
		this.cards = cards;
	}

	public List<Card> getCards() {
		return cards;
	}


	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
}
