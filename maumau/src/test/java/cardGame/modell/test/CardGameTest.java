package cardGame.modell.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import cardGame.MauMauService;
import cardGame.management.MauMauMgmt;
import cardGame.modell.MauMau;
import cards.CardDeckService;
import cards.modell.Card;
import cards.modell.CardDeck;
import cards.modell.Symbol;
import cards.modell.Value;
import rules.RulesService;
import rules.modell.MauMauRules;
import userAdministration.UserService;
import userAdministration.modell.MauMauUser;

@RunWith(MockitoJUnitRunner.class)
public class CardGameTest {

	private static MauMau maumau;
	
	@InjectMocks
	private static MauMauService mauMauMgmt;
	
	@Mock
	private static UserService userService;
	
	@Mock
	private static CardDeckService cardDeckService;

	@Mock
	private static RulesService ruleService;
	
	private static MauMauUser mauMauUser1;
	private static MauMauUser mauMauUser2;
	private static List<Card> cards;
	private static List<Card> cards2;
	private static MauMauUser mauMauUser3;
	private static CardDeck graveyard; 
	private static CardDeck cardDeck;
	private static MauMau maumau2;
	private static List<MauMauUser> players;
	private static MauMauRules ruleset;
	
	@BeforeAll
	public static void init() {
		maumau = new MauMau(null, null, null, null, 0, false, null, 0, null);
		
		userService = mock(UserService.class);
		
		cardDeckService =mock(CardDeckService.class);
		
		ruleService = mock(RulesService.class);
		
		mauMauMgmt = new MauMauMgmt();
		
		cards = new LinkedList<Card>();
		cards.add(new Card(Symbol.CLUB, Value.NINE));
		cards.add(new Card(Symbol.SPADE, Value.KING));
		cards.add(new Card(Symbol.HEART, Value.ACE));
		cards.add(new Card(Symbol.DIAMOND, Value.SEVEN));
		cards.add(new Card(Symbol.CLUB, Value.EIGHT));
		cards.add(new Card(Symbol.CLUB, Value.SEVEN));
		cards.add(new Card(Symbol.HEART, Value.JACK));
		cards.add(new Card(Symbol.SPADE, Value.QUEEN));
		cards.add(new Card(Symbol.DIAMOND, Value.TEN));
		cardDeck = new CardDeck(cards);
		
		graveyard = new CardDeck();
		graveyard.setCards(cards);
		maumau.setDeck(cardDeck);
		maumau.setGraveyard(new CardDeck());
		
		players = new LinkedList<MauMauUser>();
		players.add(new MauMauUser("Kaan", new LinkedList<Card>(), 0));
		players.add(new MauMauUser("Marcel", new LinkedList<Card>(), 0));
		players.add(new MauMauUser("Judith", new LinkedList<Card>(), 0));
		
		maumau.setPlayers(players);
		
		maumau.setRuleSet(new MauMauRules());
		mauMauUser1 = new MauMauUser("John", new LinkedList<Card>(), 0);
		mauMauUser2 = new MauMauUser("Doe", new LinkedList<Card>(), 0);
		
	}
	
	
	@BeforeEach
	public void createCardList() {
		MockitoAnnotations.initMocks(this);
		mauMauUser3 = new MauMauUser("Superman", new LinkedList <Card> (), 0, true, false, false, false);
		cards2 = new LinkedList<Card>();
		cards2.add(new Card(Symbol.CLUB, Value.NINE));
		cards2.add(new Card(Symbol.SPADE, Value.KING));
		cards2.add(new Card(Symbol.HEART, Value.TEN));
		cards2.add(new Card(Symbol.DIAMOND, Value.SEVEN));
		mauMauUser3.setHand(cards2);
		ruleset = new MauMauRules();
		maumau2 = new MauMau(players, ruleset, cardDeck, graveyard,
				0, false, null, 0, null);

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
	
	
	@Test
	public void testHandleUserHasToTakeCardsWithPenaltyCards() {
		maumau2.getPlayers().add(mauMauUser3);
		maumau2.setCurrentPlayer(mauMauUser3);
		maumau2.setAmountSeven(2);
		
		when(cardDeckService.giveCard(cardDeck, graveyard)).thenReturn(new Card(Symbol.CLUB, Value.ACE));
		when(cardDeckService.removeCardFromCardDeckList(cards, new Card(Symbol.CLUB, Value.ACE))).thenReturn(cards);
		when(userService.takeCard(new Card(Symbol.CLUB, Value.ACE), maumau2.getCurrentPlayer())).thenReturn(mauMauUser3);
		
		
		///Komischerweise wird hier was ausgeführt (-> nur zum Test)
		when(userService.playCard(0, mauMauUser3)).thenReturn(new Card(Symbol.CLUB, Value.ACE));
		Card card = userService.playCard(0, mauMauUser3);
		System.out.println("HIIIER " + card.getValue().toString());
		

		//Aber hier wird nichts ausgeführt (auch nur zum Test)
		MauMauUser user = userService.takeCard(new Card(Symbol.CLUB, Value.ACE), maumau2.getCurrentPlayer());
		System.out.println("HIER "+ user.getUsername());
		
		
		maumau2 = mauMauMgmt.handleUserHasToTakeCards(maumau2);
		assertEquals(8, maumau2.getCurrentPlayer().getHand().size());
		
		
	}
	
	
	

	

}
