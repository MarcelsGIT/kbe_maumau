package cards.modell;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="CardDecks")
@Component
public class CardDeck { // entity klasse
	@Id
	@GeneratedValue
	@Column(name="id", columnDefinition="INT")
	private Integer id;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="cardDeckId", columnDefinition="INT")
	private List<Card> cards; // kein privates Feld
	
	public CardDeck() {
		super();
	}

	public CardDeck(List<Card> cards) {
		super();
		for(Card card : cards) {
			card.setOwner(null);
			card.setDeck(this);
		}
		this.cards = cards;
	}

	public List<Card> getCards() {
		return cards;
	}


	public void setCards(List<Card> cards) {
		for(Card card : cards) {
			card.setOwner(null);
			card.setDeck(this);
		}
		this.cards = cards;
	}
	
}
