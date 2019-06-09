package userAdministration.modell;

import java.util.List;

import org.springframework.stereotype.Component;

import cards.modell.Card;

@Component
public class CardGameUser {
	protected String username;
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
		this.hand = hand;
	}
	
	public Card getCardInHand(int index) {
		return hand.get(index);
	}
	
	public void addCardToHand(Card card) {
		this.hand.add(card);
	}
}
