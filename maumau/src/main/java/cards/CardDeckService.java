package cards;

import java.util.List;

import cards.modell.Card;
import cards.modell.CardDeck;

public interface CardDeckService {

	/**
	 * Creates Cards
	 * 
	 * @return List with cards
	 */
	List<Card> createCards();

	/**
	 * Creates a CardDeck
	 * 
	 * @param cardsList
	 * @return CardDeck
	 */
	CardDeck createCardDeck(List<Card> cardsList);

	/**
	 * Saves a card to the deck
	 * 
	 * @param card: Card to be saved
	 * @return CardDeck: cardDeck with additional card
	 */
	CardDeck saveCardToCardDeck(CardDeck cardDeck, Card... card);

	/**
	 * Deals a the specified amounts of cards from the CardDeck, if amount in
	 * CardDeck not enough, then cards from graveyard are used
	 * @param amount Amount of cards that should be dealt
	 * @param cardDeck: cardDeck to deal cards from
	 * @param graveyard: backup cards if cardDeck cards are not enough
	 * @return cardList: List of cards that were dealt
	 */
	List<Card> dealCards(CardDeck cardDeck, int amount, CardDeck graveyard);


	/**
	 * Shuffles the cards in the cardDeck
	 * @param cardDeck
	 * @return cardList: shuffled list
	 */
	List<Card> shuffle(CardDeck cardDeck);

	/**
	 * Gives a card from the cardDeck
	 * @param cardDeck: cardDeck to give card from
	 * @param graveyard backup cards if cardDeck cards are not enough
	 * @return card: top card from deck
	 */
	Card giveCard(CardDeck cardDeck, CardDeck graveyard);

	/**
	 * Gives the top card on CardDeck
	 * @param cardDeck: carDeck to give top card from
	 * @return card: top card of cardDeck
	 */
	Card giveMostRecentCard(CardDeck cardDeck);

	/**
	 * Adds cards from other cardDeck to cardDeck
	 * @param cardDeck:  cardDeck that needs new cards
	 * @param graveyard: CardDeck graveyard to fill up cardDeck cardList
	 * @return cardDeck: CardDeck with cards from graveyard
	 */
	CardDeck addCardsFromGraveyard(CardDeck cardDeck, CardDeck graveyard);

	/**
	 * Removes specified cards from cardDeck
	 * @param cardDeck: cardDeck to remove cards from
	 * @param cardsToBeRemoved cards to be removed from cardDeck cardList
	 * @return cardDeck without the cards that were reomved
	 */
	CardDeck removeCardsFromCardDeckList(CardDeck cardDeck, List<Card> cardsToBeRemoved);

	/**
	 * Removes specified card from CardDeck
	 * @param cardDeck
	 * @param card to be removed
	 * @return cardDeck without card
	 */
	List<Card> removeCardFromCardDeckList(List<Card> cards, Card card);
}
