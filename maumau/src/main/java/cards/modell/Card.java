package cards.modell;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import userAdministration.modell.CardGameUser;

// TODO: Auto-generated Javadoc
/**
 * The Class Card.
 */
@Entity
@Table(name="Cards")
@Component
public class Card {
	@Id
	@GeneratedValue
	@Column(name="id", columnDefinition="INT")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="ownerId", columnDefinition="INT")
	private CardGameUser owner;
	
	@ManyToOne
	@JoinColumn(name="cardDeckId", columnDefinition="INT")
	private CardDeck deck;
	
	/**
	 * Instantiates a new card.
	 */
	public Card() {
		
	}
	
	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public Symbol getSymbol() {
		return symbol;
	}

	/**
	 * Sets the symbol.
	 *
	 * @param symbol the new symbol
	 */
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public Value getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(Value value) {
		this.value = value;
	}

	Symbol symbol;
	Value value;
	
	/**
	 * Instantiates a new card.
	 *
	 * @param symbol the symbol
	 * @param value the value
	 */
	public Card(Symbol symbol, Value value) {
		this.symbol = symbol;
		this.value = value;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the owner.
	 *
	 * @return the owner
	 */
	public CardGameUser getOwner() {
		return owner;
	}

	/**
	 * Sets the owner.
	 *
	 * @param owner the new owner
	 */
	public void setOwner(CardGameUser owner) {
		this.owner = owner;
	}

	/**
	 * Gets the deck.
	 *
	 * @return the deck
	 */
	public CardDeck getDeck() {
		return deck;
	}

	/**
	 * Sets the deck.
	 *
	 * @param deck the new deck
	 */
	public void setDeck(CardDeck deck) {
		this.deck = deck;
	}
	
	
	

	
}
