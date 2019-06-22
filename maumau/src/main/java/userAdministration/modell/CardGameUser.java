package userAdministration.modell;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Column;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import cards.modell.Card;

@Entity

@Table(name="cardGameUser")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn(name="planetype", discriminatorType=DiscriminatorType.STRING )
//@DiscriminatorValue("Plane")

@Component
public class CardGameUser {
	
	@Id
	@GeneratedValue
	@Column(name="id", columnDefinition="INT")
	private Integer id;
	protected String username;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="ownerId", columnDefinition="INT")
	protected List<Card> hand;
	
	public CardGameUser(String username, List<Card> hand, int wins) {
		super();
		this.username = username;
		this.hand = hand;
		this.wins = wins;
	}
	
	public CardGameUser() {
		
	}

	protected int wins;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		for(Card card : hand) {
			card.setOwner(this);
			card.setDeck(null);
		}
		this.hand = hand;
	}
	
	public Card getCardInHand(int index) {
		return hand.get(index);
	}
	
	public void addCardToHand(Card card) {
		card.setOwner(this);
		this.hand.add(card);
	}
}
