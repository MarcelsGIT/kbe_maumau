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

import cardGame.MauMauService;
import cardGame.management.MauMauMgmt;
import cardGame.modell.MauMau;
import cards.modell.Card;
import cards.modell.CardDeck;
import cards.modell.Symbol;
import cards.modell.Value;
import rules.modell.MauMauRules;
import userAdministration.modell.MauMauUser;

public class CardGameTest {

	private static MauMau maumau;
	private static MauMauService mauMauMgmt;
	private static MauMauUser mauMauUser1;
	private static MauMauUser mauMauUser2;
	
	@BeforeAll
	public static void init() {
		maumau = new MauMau(null, null, null, null, 0, false, null, 0, null);
		mauMauMgmt = new MauMauMgmt();
		
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
		CardDeck cardDeck = new CardDeck(cards);
		
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
		mauMauMgmt.chooseWhoStarts(maumau);
		assertEquals(maumau.getCurrentPlayer(), maumau.getPlayers().get(0));
	}
	
	@Test
	public void testPositiveNextPlayer() {
		maumau.setCurrentPlayer(maumau.getPlayers().get(0));
		maumau = mauMauMgmt.nextPlayer(maumau);
		assertEquals(maumau.getCurrentPlayerIndex(),1);	
		}
	
	@Test
	public void testNextPlayerStartUserListAgain() {
		maumau.setCurrentPlayer(maumau.getPlayers().get(maumau.getPlayers().size()-1));
		maumau = mauMauMgmt.nextPlayer(maumau);
		assertEquals(maumau.getCurrentPlayerIndex(),0);
	}
	
	@Test
	public void testPositiveEndGame() {
		boolean endGame = true;
		maumau.setEndGame(endGame);
		maumau = mauMauMgmt.endGame(maumau, endGame);
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
		mauMauMgmt.skipRound(mauMauUser2, maumau);
		assertTrue(maumau.getCurrentPlayer() != mauMauUser2);
	}
	
	@Test
	public void testDealPenaltyCardsIsValid() {
		try {
			mauMauMgmt.dealPenaltyCards(4, maumau);
		} catch(Exception e) {
			fail("Penalty cards could not be dealt. " + e.getStackTrace());
		}
	}

}
