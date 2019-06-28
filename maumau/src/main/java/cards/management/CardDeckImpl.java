package cards.management;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import cards.CardDeckService;
import cards.modell.Card;
import cards.modell.CardDeck;
import cards.modell.Symbol;
import cards.modell.Value;
import util.exceptions.NoMoreCardsException;

// TODO: Auto-generated Javadoc
/**
 * The Class CardDeckImpl.
 */
@Component
public class CardDeckImpl implements CardDeckService {
	
	/**
	 * Instantiates a new card deck impl.
	 */
	public CardDeckImpl() {
		super();
	}

	/**
	 * Save card to card deck.
	 *
	 * @param cardDeck the card deck
	 * @param card the card
	 * @return the card deck
	 */
	public CardDeck saveCardToCardDeck(CardDeck cardDeck, Card... card) {
		List<Card> cardList = cardDeck.getCards();
		for (Card singleCard: card) {
			cardList.add(singleCard);
			singleCard.setDeck(cardDeck);
		}
		cardDeck.setCards(cardList);
		return cardDeck;
	}

	/**
	 * Deal cards.
	 *
	 * @param cardDeck the card deck
	 * @param amount the amount
	 * @param graveyard the graveyard
	 * @return the list
	 */
	public List<Card> dealCards(CardDeck cardDeck, int amount, CardDeck graveyard) {
		if (cardDeck.getCards().size()< amount) {
			//cardDeck = addCardsFromGraveyard(cardDeck, graveyard);
			throw new NoMoreCardsException();
		}
		List<Card> cardDeckCards = cardDeck.getCards();
		List <Card> dealedCards = new LinkedList<Card>();
		for (int i=0; i<amount; i++) {
			dealedCards.add(cardDeckCards.get(i));
		}
		return dealedCards;
	}
	
	/**
	 * Shuffle.
	 *
	 * @param cardDeck the card deck
	 * @return the list
	 */
	public List<Card> shuffle(CardDeck cardDeck) {
		List <Card> cardList = cardDeck.getCards();
		Collections.shuffle(cardList);
		return cardList;
	}

	/**
	 * Give card.
	 *
	 * @param cardDeck the card deck
	 * @param graveyard the graveyard
	 * @return the card
	 */
	public Card giveCard(CardDeck cardDeck, CardDeck graveyard) {
		if (cardDeck.getCards().size()<1) {
			//cardDeck = addCardsFromGraveyard(cardDeck, graveyard);
			throw new NoMoreCardsException();
		}
		Card card = cardDeck.getCards().get(0);
		return card;
	}

	/**
	 * Give most recent card.
	 *
	 * @param cardDeck the card deck
	 * @return the card
	 */
	public Card giveMostRecentCard(CardDeck cardDeck) {
		return cardDeck.getCards().get(cardDeck.getCards().size()-1);
	}
	
	/**
	 * Adds the cards from graveyard.
	 *
	 * @param cardDeck the card deck
	 * @param graveyard the graveyard
	 * @return the card deck
	 */
	@Deprecated
	public CardDeck addCardsFromGraveyard(CardDeck cardDeck, CardDeck graveyard) {
		//List <Card> cardList = cardDeck.getCards();
		Card lastPlayedCard = graveyard.getCards().get(graveyard.getCards().size() -1);
		for(Card card : graveyard.getCards()) {
			if(card != lastPlayedCard) {
				card.setDeck(cardDeck);
				card.setOwner(null);
			}
			cardDeck.getCards().add(card);
		}
		graveyard.getCards().clear();
		graveyard.getCards().add(lastPlayedCard);
		//graveyard.getCards().remove(lastPlayedCard);
		//cardList.addAll(graveyard.getCards());
		//graveyard.getCards().add(lastPlayedCard);
		//cardDeck.setCards(cardList);
		return cardDeck;
	}

	/**
	 * Removes the cards from card deck list.
	 *
	 * @param cardDeck the card deck
	 * @param cardsToBeRemoved the cards to be removed
	 * @return the card deck
	 */
	public CardDeck removeCardsFromCardDeckList(CardDeck cardDeck, List<Card> cardsToBeRemoved) {
		List<Card> cardList = cardDeck.getCards();
		for(Card card : cardsToBeRemoved) {
			card.setOwner(null);
			card.setDeck(null);	
		}
		cardList.removeAll(cardsToBeRemoved);
		cardDeck.setCards(cardList);
		return cardDeck;
	}
	
	/**
	 * Removes the card from card deck list.
	 *
	 * @param cards the cards
	 * @param cardToBeRemoved the card to be removed
	 * @return the list
	 */
	public List<Card> removeCardFromCardDeckList(List<Card> cards, Card cardToBeRemoved) {
		for (int i=0; i<cards.size(); i++) {
			if (cards.get(i).getSymbol() == cardToBeRemoved.getSymbol()&& cards.get(i).getValue()==cardToBeRemoved.getValue()) {
				cards.remove(i);
			}
		}
		return cards;
	}

	/**
	 * Creates the cards.
	 *
	 * @return the list
	 */
	public List<Card> createCards() {
		List <Card> cards = new LinkedList <Card>();
		for (Symbol symbol : Symbol.values()) {
			for (Value value: Value.values()) {
				cards.add(new Card(symbol, value));
			}
		}
		return cards;
	}

	/**
	 * Creates the card deck.
	 *
	 * @param cardsList the cards list
	 * @return the card deck
	 */
	public CardDeck createCardDeck(List<Card> cardsList) {
		CardDeck newCardDeck = new CardDeck(cardsList);
		return newCardDeck;
	}
	
	}
	

