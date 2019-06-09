package cards;

import java.util.List;

import cards.modell.Card;
import cards.modell.CardDeck;

public interface CardDeckService {

	
	
	/**
	 * Creates Cards for the game
	 * @return List with cards
	 */
	 List<Card> createCards();
	 
	 CardDeck createCardDeck(List<Card> cardsList);
	
	/**
	 * Saves the card that was played by the user to the deck
	 * 
	 * @param card The card that was played by the user
	 */
	CardDeck saveCardToCardDeck(CardDeck cardDeck, Card ... card);
	
	/**
	 * Deals a set of cards to the player
	 * 
	 * @param amount Amount of cards that should be dealt
	 * @return A list of cards that are given to the user
	 */
	List<Card> dealCards(CardDeck cardDeck, int amount, CardDeck graveyard);
	
	/**
	 * Shuffle the cards
	 */
	List<Card> shuffle(CardDeck cardDeck);
	
	/**
	 * Give a card from the deck to the user
	 * 
	 * @return top card from deck
	 */
	Card giveCard(CardDeck cardDeck, CardDeck graveyard);
	
	/**
	 * Show, which card was most recently played 
	 * 
	 * @return The last card from the Graveyard / Ablagestapel
	 */
	Card giveMostRecentCard(CardDeck cardDeck);
	
	
	/**
	 * @param cardDeck cardDeck that needs new cards
	 * @param graveyard graveyard to fill up cardDeck cardList
	 * @return cardDeck with cards from graveyard
	 */
	CardDeck addCardsFromGraveyard(CardDeck cardDeck, CardDeck graveyard);
	
	/**
	 * @param cardDeck to remove cards from
	 * @param cardsToBeRemoved cards to be removes from cardDeck cardList
	 * @return cardDeck without the cards
	 */
	CardDeck removeCardsFromCardDeckList(CardDeck cardDeck, List <Card> cardsToBeRemoved);
	
	/**
	 * @param cardDeck 
	 * @param card to be removed
	 * @return cardDeck without card
	 */
	List<Card> removeCardFromCardDeckList(List<Card> cards, Card card);
}
