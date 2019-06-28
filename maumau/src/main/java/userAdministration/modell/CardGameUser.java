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

// TODO: Auto-generated Javadoc
/**
 * The Class CardGameUser.
 */
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
	
	/**
	 * Instantiates a new card game user.
	 *
	 * @param username the username
	 * @param hand the hand
	 * @param wins the wins
	 */
	public CardGameUser(String username, List<Card> hand, int wins) {
		super();
		this.username = username;
		this.hand = hand;
		this.wins = wins;
	}
	
	/**
	 * Instantiates a new card game user.
	 */
	public CardGameUser() {
		
	}

	protected int wins;
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the wins.
	 *
	 * @return the wins
	 */
	public int getWins() {
		return wins;
	}

	/**
	 * Sets the wins.
	 *
	 * @param wins the new wins
	 */
	public void setWins(int wins) {
		this.wins = wins;
	}

	/**
	 * Gets the hand.
	 *
	 * @return the hand
	 */
	public List<Card> getHand() {
		return hand;
	}

	/**
	 * Sets the hand.
	 *
	 * @param hand the new hand
	 */
	public void setHand(List<Card> hand) {
		for(Card card : hand) {
			card.setOwner(this);
			card.setDeck(null);
		}
		this.hand = hand;
	}
	
	/**
	 * Gets the card in hand.
	 *
	 * @param index the index
	 * @return the card in hand
	 */
	public Card getCardInHand(int index) {
		return hand.get(index);
	}
	
	/**
	 * Adds the card to hand.
	 *
	 * @param card the card
	 */
	public void addCardToHand(Card card) {
		card.setOwner(this);
		this.hand.add(card);
	}
}
