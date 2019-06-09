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

@Component
public class CardDeckImpl implements CardDeckService {
	
	public CardDeckImpl() {
		super();
	}

	public CardDeck saveCardToCardDeck(CardDeck cardDeck, Card... card) {
		List<Card> cardList = cardDeck.getCards();
		for (Card singleCard: card) {
			cardList.add(singleCard);
		}
		cardDeck.setCards(cardList);
		return cardDeck;
	}

	public List<Card> dealCards(CardDeck cardDeck, int amount, CardDeck graveyard) {
		if (cardDeck.getCards().size()< amount) {
			cardDeck = addCardsFromGraveyard(cardDeck, graveyard);
		}
		List<Card> cardDeckCards = cardDeck.getCards();
		List <Card> dealedCards = new LinkedList<Card>();
		for (int i=0; i<amount; i++) {
			dealedCards.add(cardDeckCards.get(i));
		}
		return dealedCards;
	}
	
	public List<Card> shuffle(CardDeck cardDeck) {
		List <Card> cardList = cardDeck.getCards();
		Collections.shuffle(cardList);
		return cardList;
	}

	public Card giveCard(CardDeck cardDeck, CardDeck graveyard) {
		if (cardDeck.getCards().size()<1) {
			cardDeck = addCardsFromGraveyard(cardDeck, graveyard);
			
		}
		Card card = cardDeck.getCards().get(0);
		return card;
	}

	public Card giveMostRecentCard(CardDeck cardDeck) {
		return cardDeck.getCards().get(cardDeck.getCards().size()-1);
	}
	
	public CardDeck addCardsFromGraveyard(CardDeck cardDeck, CardDeck graveyard) {
		List <Card> cardList = cardDeck.getCards();
		cardList.addAll(graveyard.getCards());
		cardDeck.setCards(cardList);
		return cardDeck;
	}

	public CardDeck removeCardsFromCardDeckList(CardDeck cardDeck, List<Card> cardsToBeRemoved) {
		List<Card> cardList = cardDeck.getCards();
		cardList.removeAll(cardsToBeRemoved);
		cardDeck.setCards(cardList);
		return cardDeck;
	}
	
	public List<Card> removeCardFromCardDeckList(List<Card> cards, Card cardToBeRemoved) {
		for (int i=0; i<cards.size(); i++) {
			if (cards.get(i).getSymbol() == cardToBeRemoved.getSymbol()&& cards.get(i).getValue()==cardToBeRemoved.getValue()) {
				cards.remove(i);
			}
		}
		return cards;
	}

	public List<Card> createCards() {
		List <Card> cards = new LinkedList <Card>();
		for (Symbol symbol : Symbol.values()) {
			for (Value value: Value.values()) {
				cards.add(new Card(symbol, value));
			}
		}
		return cards;
	}

	public CardDeck createCardDeck(List<Card> cardsList) {
		CardDeck newCardDeck = new CardDeck(cardsList);
		return newCardDeck;
	}
	
	}
	

