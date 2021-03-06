package carddeck.modell.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cards.CardDeckService;
import cards.management.CardDeckImpl;
import cards.modell.Card;
import cards.modell.CardDeck;
import cards.modell.Symbol;
import cards.modell.Value;
import util.exceptions.NoMoreCardsException;

public class CardDeckTest {
	
	private CardDeck cardDeck;
	private static Card[] cardsToSave;
	private static Card card;
	private static int amount;
	private List<Card> cards;
	private CardDeck graveyard;
	private static CardDeckService cardDeckImpl;

	@BeforeAll
	public static void init() {
		cardsToSave = new Card[] { new Card(Symbol.CLUB, Value.EIGHT), new Card(Symbol.SPADE, Value.JACK),
				new Card(Symbol.HEART, Value.KING) };
		card = new Card(Symbol.DIAMOND, Value.NINE);
		amount = 3;
		cardDeckImpl = new CardDeckImpl();

	}

	@BeforeEach
	public void createCardList() {
		this.cards = new LinkedList<Card>();
		cards.add(new Card(Symbol.CLUB, Value.NINE));
		cards.add(new Card(Symbol.SPADE, Value.KING));
		cards.add(new Card(Symbol.HEART, Value.TEN));
		cards.add(new Card(Symbol.DIAMOND, Value.SEVEN));
		this.cardDeck = new CardDeck(cards);
		this.graveyard = new CardDeck(cards);
	}

	@Test
	void testSaveCardArrayToCardDeck() {
		cardDeck = cardDeckImpl.saveCardToCardDeck(cardDeck, cardsToSave);
		assertEquals(7, cardDeck.getCards().size());
	}

	@Test
	void testSaveSingleCardToCardDeck() {
		cardDeckImpl.saveCardToCardDeck(cardDeck, card);
		assertEquals(5, cardDeck.getCards().size());
	}

	@Test
	void testDealCards() {
		cardDeckImpl.dealCards(cardDeck, amount, graveyard);
		assertEquals(cardDeck.getCards().size(), graveyard.getCards().size());
	}

	@Test
	void testDealCardsCardDeckNotHavingEnoughCards() {
		
		assertThrows(NoMoreCardsException.class, () -> {
			cardDeckImpl.dealCards(cardDeck, cardDeck.getCards().size() + 1, graveyard);
		});
	}
	/*
	@Test
	void testShuffle() {
		List <Card> oldList = cardDeck.getCards();
		int sameCard = 0;
		for (int i = 0; i < cardDeck.getCards().size(); i++) {
			if (cardDeck.getCards().get(i).getSymbol() == oldList.get(i).getSymbol() && cardDeck.getCards().get(i).getValue() == oldList.get(i).getValue()) {
				sameCard++;
			}
			assertNotEquals(sameCard, cardDeck.getCards().size());}
		}
	*/	

	
	
	@Test
	void testGiveCard() {
		Card givenCard = cardDeckImpl.giveCard(cardDeck, graveyard);
		assertEquals(cardDeck.getCards().get(0), givenCard);	
	}
	
	
	@Test
	void testGiveCardNotHavingEnoughCards() {
		List<Card> emptyList = new LinkedList<Card>();
		cardDeck.setCards(emptyList);
		//Card givenCard = 
		//assertEquals(givenCard.getValue(), graveyard.getCards().get(0).getValue());
		assertThrows(NoMoreCardsException.class, () -> {
			cardDeckImpl.giveCard(cardDeck, graveyard);
		});
	}
	
	@Test
	void testGiveMostRecentCard() {
		Card lastCard = cardDeck.getCards().get(cardDeck.getCards().size()-1);
		Card givenCard = cardDeckImpl.giveMostRecentCard(cardDeck);
		assertEquals(lastCard.getValue(), givenCard.getValue());	
	}
	
	@Test 
	void addCardsFromGraveyard() {
	List <Card> emptyCardList = new LinkedList <Card>();
	cardDeck.setCards(emptyCardList);
	CardDeck cardDeckWithGraveyardCards = cardDeckImpl.addCardsFromGraveyard(cardDeck, graveyard);
	assertEquals(cardDeckWithGraveyardCards.getCards().isEmpty(), graveyard.getCards().isEmpty());
	
	}
	
	@Test
	void testRemoveCardsFromCardDeckList() {
		CardDeck cardDeckWithoutCards = cardDeckImpl.removeCardsFromCardDeckList(cardDeck, cards);
		assertEquals(0, cardDeckWithoutCards.getCards().size());
	}
	
	@Test
	void testRemoveCardFromCardDeckList() {
		int sizeBefore = cards.size();
		List<Card> cardListWithoutCard = cardDeckImpl.removeCardFromCardDeckList(cards,new Card(Symbol.CLUB, Value.NINE));
		assertEquals(sizeBefore-1, cardListWithoutCard.size());
	}

}
