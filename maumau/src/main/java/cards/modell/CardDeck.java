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

// TODO: Auto-generated Javadoc
/**
 * The Class CardDeck.
 */
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
	
	/**
	 * Instantiates a new card deck.
	 */
	public CardDeck() {
		super();
	}

	/**
	 * Instantiates a new card deck.
	 *
	 * @param cards the cards
	 */
	public CardDeck(List<Card> cards) {
		super();
		for(Card card : cards) {
			card.setOwner(null);
			card.setDeck(this);
		}
		this.cards = cards;
	}

	/**
	 * Gets the cards.
	 *
	 * @return the cards
	 */
	public List<Card> getCards() {
		return cards;
	}


	/**
	 * Sets the cards.
	 *
	 * @param cards the new cards
	 */
	public void setCards(List<Card> cards) {
		for(Card card : cards) {
			card.setOwner(null);
			card.setDeck(this);
		}
		this.cards = cards;
	}
	
}
