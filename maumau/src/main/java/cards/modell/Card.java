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
	
	public Card() {
		
	}
	
	public Symbol getSymbol() {
		return symbol;
	}

	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	Symbol symbol;
	Value value;
	
	public Card(Symbol symbol, Value value) {
		this.symbol = symbol;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CardGameUser getOwner() {
		return owner;
	}

	public void setOwner(CardGameUser owner) {
		this.owner = owner;
	}

	public CardDeck getDeck() {
		return deck;
	}

	public void setDeck(CardDeck deck) {
		this.deck = deck;
	}
	
	
	

	
}
