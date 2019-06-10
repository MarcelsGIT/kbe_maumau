package cardGame.modell.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;


import cardGame.MauMauService;
import cardGame.management.MauMauMgmt;
import cardGame.modell.MauMau;
import cards.CardDeckService;
import cards.management.CardDeckImpl;
import cards.modell.Card;
import cards.modell.CardDeck;
import cards.modell.Symbol;
import cards.modell.Value;
import rules.RulesService;
import rules.modell.MauMauRules;
import userAdministration.UserService;
import userAdministration.modell.MauMauUser;

public class CardGameTest {

	private static MauMau maumau;
	private static MauMauService mauMauService;
	private static MauMauUser mauMauUser1;
	private static MauMauUser mauMauUser2;
	
	private static CardDeckService cardDeckService;
	private static RulesService ruleService;
	private static UserService userService;
	
	private static CardDeck cardDeck;
	private static CardDeck graveyard;
	
	@BeforeAll
	public static void init() {
		maumau = new MauMau(null, null, null, null, 0, false, null, 0, null);
		mauMauService = new MauMauMgmt();
		cardDeck= new CardDeck();
		graveyard = new CardDeck();
		

		
		List<Card> cards = new LinkedList<Card>();
		cards.add(new Card(Symbol.CLUB, Value.NINE));
		cards.add(new Card(Symbol.SPADE, Value.KING));
		cards.add(new Card(Symbol.HEART, Value.ACE));
		cards.add(new Card(Symbol.DIAMOND, Value.SEVEN));
		cards.add(new Card(Symbol.CLUB, Value.EIGHT));
		cards.add(new Card(Symbol.CLUB, Value.SEVEN));
		cards.add(new Card(Symbol.HEART, Value.JACK));
		cards.add(new Card(Symbol.SPADE, Value.QUEEN));
		cards.add(new Card(Symbol.DIAMOND, Value.TEN));
		cardDeck.setCards(cards);
		graveyard.setCards(new LinkedList<Card>());
		
		maumau.setDeck(cardDeck);
		maumau.setGraveyard(new CardDeck());
		
		List<MauMauUser> players = new LinkedList<MauMauUser>();
		players.add(new MauMauUser("Kaan", new LinkedList<Card>(), 0));
		players.add(new MauMauUser("Marcel", new LinkedList<Card>(), 0));
		players.add(new MauMauUser("Judith", new LinkedList<Card>(), 0));
		
		maumau.setPlayers(players);
		
		maumau.setRuleSet(new MauMauRules());
		mauMauUser1 = new MauMauUser("John", new LinkedList<Card>(), 0);
		mauMauUser2 = new MauMauUser("Doe", new LinkedList<Card>(), 0);
		
	}
	
	@Test
	public void testPositiveChooseWhoStarts() {
		mauMauService.chooseWhoStarts(maumau);
		assertEquals(maumau.getCurrentPlayer(), maumau.getPlayers().get(0));
	}
	
	@Test
	public void testPositiveNextPlayer() {
		maumau.setCurrentPlayer(maumau.getPlayers().get(0));
		maumau = mauMauService.nextPlayer(maumau);
		assertEquals(maumau.getCurrentPlayerIndex(),1);	
		}
	
	@Test
	public void testNextPlayerStartUserListAgain() {
		maumau.setCurrentPlayer(maumau.getPlayers().get(maumau.getPlayers().size()-1));
		maumau = mauMauService.nextPlayer(maumau);
		assertEquals(maumau.getCurrentPlayerIndex(),0);
	}
	
	@Test
	public void testPositiveEndGame() {
		boolean endGame = true;
		maumau.setEndGame(endGame);
		maumau = mauMauService.endGame(maumau, endGame);
		assertTrue(maumau.isEndGame());
	}
	
	@Test
	public void testPositiveInsertWinner() {
		maumau.setWinner(maumau.getPlayers().get(0));
		assertNotNull(maumau.getWinner());
	}
	
	@Test
	public void testSkipRoundForWrongUser() {
		maumau.setCurrentPlayer(mauMauUser1);
		mauMauService.skipRound(mauMauUser2, maumau);
		assertTrue(maumau.getCurrentPlayer() != mauMauUser2);
	}
	
	@Test
	public void testDealPenaltyCardsIsValid() {
		try {
			mauMauService.dealPenaltyCards(4, maumau);
		} catch(Exception e) {
			fail("Penalty cards could not be dealt. " + e.getStackTrace());
		}
	}
	
//	@Test
//	public void testDealCardsToPlayers() {
//		//CardDeckService cardDeckService = mock(CardDeckService.class);
//		LinkedList <Card>cardsForUsers = new LinkedList<Card>();
//		cardsForUsers.add(new Card(Symbol.CLUB, Value.NINE));
//		cardsForUsers.add(new Card(Symbol.SPADE, Value.KING));
//		cardsForUsers.add(new Card(Symbol.HEART, Value.ACE));
//		cardsForUsers.add(new Card(Symbol.DIAMOND, Value.SEVEN));
//		cardsForUsers.add(new Card(Symbol.CLUB, Value.EIGHT));
//		 cardDeckService = mock(CardDeckService.class);
//		 when(cardDeckService.dealCards(cardDeck, 5, graveyard)).thenReturn(cardsForUsers);
//		ruleService = mock(RulesService.class);
//		 userService = mock(UserService.class);
//		//mauMauService.setServices(cardDeckService, userService, ruleService);
//
//
////		List <Card> cards = cardDeckService.dealCards(cardDeck, 5, graveyard);
////		for(Card card :cards) {
////			System.out.println(card.getSymbol());
////		}
//		maumau = mauMauService.dealCardsToPlayers(maumau, 5, cardDeckService);
//		for (MauMauUser user : maumau.getPlayers()) {
//			System.out.println(user.getHand().size());
//			assertEquals(5, user.getHand().size());
//		}
//	}
	
	
	

}
