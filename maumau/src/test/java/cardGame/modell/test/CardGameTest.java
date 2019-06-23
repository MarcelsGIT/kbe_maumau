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

import javax.swing.plaf.synth.SynthScrollPaneUI;

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
import cards.management.CardDeckImpl;
import cards.modell.Card;
import cards.modell.CardDeck;
import cards.modell.Symbol;
import cards.modell.Value;
import rules.RulesService;
import rules.management.RulesMgmt;
import rules.modell.MauMauRules;
import userAdministration.UserService;
import userAdministration.management.UserMgmt;
import userAdministration.modell.MauMauUser;

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
	private static CardDeck graveyard; 
	private static CardDeck cardDeck;
	private  MauMau maumau2;
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
		cards2 = new LinkedList<Card>();
		cards2.add(new Card(Symbol.CLUB, Value.NINE));
		cards2.add(new Card(Symbol.SPADE, Value.KING));
		cards2.add(new Card(Symbol.HEART, Value.TEN));
		cards2.add(new Card(Symbol.DIAMOND, Value.SEVEN));
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
	public void testDealCardsToPlayers() {
		MauMauUser mauMauUser3 = new MauMauUser("Superman", new LinkedList <Card> (), 0, true, false, false, false, false);
		LinkedList <MauMauUser> users = new LinkedList <MauMauUser> ();
		users.add(mauMauUser3);
		MauMau maumau = new MauMau ();
		maumau.setPlayers(users);
		LinkedList <Card>cardGameCards = new LinkedList <Card>();
		cardGameCards.add(new Card(Symbol.CLUB, Value.NINE));
		cardGameCards.add(new Card(Symbol.SPADE, Value.KING));
		cardGameCards.add(new Card(Symbol.HEART, Value.TEN));
		cardGameCards.add(new Card(Symbol.DIAMOND, Value.SEVEN));
		CardDeck cardDeck = new CardDeck();
		cardDeck.setCards(cardGameCards);
		CardDeck graveyard = new CardDeck();
		graveyard.setCards(cardGameCards);
		maumau.setDeck(cardDeck);
		maumau.setGraveyard(graveyard);
		when(cardDeckService.dealCards(cardDeck, 4, graveyard)).thenReturn(cardGameCards);
		when(cardDeckService.removeCardsFromCardDeckList(cardDeck, cardGameCards)).thenReturn(cardDeck);
		maumau = mauMauMgmt.dealCardsToPlayers(maumau, 4);
		assertEquals(4, maumau.getPlayers().get(0).getHand().size());
		
		
	}
	
	
	
	
	
	@Test 
	public void testGiveCardToUser(){
		MauMauUser mauMauUser3 = new MauMauUser("Superman", new LinkedList <Card> (), 0, true, false, false, false, false);
		mauMauUser3.setHand(cards2);
		maumau2.getPlayers().add(mauMauUser3);
		maumau2.setCurrentPlayer(mauMauUser3);
		int cardsSizeBefore = maumau2.getCurrentPlayer().getHand().size();
		Card cardFromDeck = new Card(Symbol.CLUB, Value.ACE);
		when(cardDeckService.giveCard(maumau2.getDeck(), maumau2.getGraveyard())).thenReturn(cardFromDeck);
		when(cardDeckService.removeCardFromCardDeckList(cards, cardFromDeck)).thenReturn(cards);
		mauMauUser3.getHand().add(cardFromDeck);
		when(userService.takeCard(cardFromDeck, maumau2.getCurrentPlayer())).thenReturn(mauMauUser3);
		maumau2 = mauMauMgmt.giveCardToUser(maumau2);
		assertEquals(maumau2.getCurrentPlayer().getHand().size(), cardsSizeBefore+1);
	}
	
	
	
	
	
	@Test
	public void testGiveAllCardsToUserThatUserHasToTake() {
		
		MauMauUser mauMauUser3 = new MauMauUser("Superman", new LinkedList <Card> (), 0, true, false, false, false, false);
		mauMauUser3.setHand(cards2);
		maumau2.getPlayers().add(mauMauUser3);
		maumau2.setCurrentPlayer(mauMauUser3);
		maumau2.setAmountSeven(2);
		
		Card cardFromDeck = new Card(Symbol.CLUB, Value.ACE);
		when(cardDeckService.giveCard(cardDeck, graveyard)).thenReturn(cardFromDeck);
		when(cardDeckService.removeCardFromCardDeckList(cards, cardFromDeck)).thenReturn(cards);
		when(userService.takeCard(cardFromDeck, maumau2.getCurrentPlayer())).thenReturn(mauMauUser3);
				
		maumau2 = mauMauMgmt.giveAllCardsToUserThatUserHasToTake(maumau2);
		assertEquals(0, maumau2.getAmountSeven());
		
		
	}
	
	@Test
	public void testPlayCardProcedureTestNewUserHandSize() {
		MauMauUser mauMauUser3 = new MauMauUser("Superman", new LinkedList <Card> (), 0, true, false, false, false, false);
		mauMauUser3.setHand(cards2);
		maumau2.getPlayers().add(mauMauUser3);
		maumau2.setCurrentPlayer(mauMauUser3);
		maumau2.setCurrentPlayer(mauMauUser3);
		int userCardSizeBefore = maumau2.getCurrentPlayer().getHand().size();
		Card cardFromDeck = new Card(Symbol.CLUB, Value.ACE);
		cards2.add(cardFromDeck);
		when(cardDeckService.removeCardFromCardDeckList(cards,cardFromDeck)).thenReturn(cards2);
		assertEquals(userCardSizeBefore+1, maumau2.getCurrentPlayer().getHand().size());
	}
	
	@Test
	public void testPlayCardProcedureTestNewGraveYardSize() {
		Card cardFromDeck = new Card(Symbol.CLUB, Value.ACE);
		when(cardDeckService.removeCardFromCardDeckList(cards2,cardFromDeck)).thenReturn(cards2);
		int graveyardSizeBefore = maumau2.getGraveyard().getCards().size();
		maumau2 = mauMauMgmt.playCardProcedure(maumau2, cardFromDeck);
		assertEquals(graveyardSizeBefore +1,maumau2.getGraveyard().getCards().size());
	}
	
	
	
	@Test
	public void testShoutMauProcedureTestMauSetTrue() {
		MauMauUser mauMauUser3 = new MauMauUser("Superman", new LinkedList <Card> (), 0, true, false, false, false,false);
		maumau2.getPlayers().add(mauMauUser3);
		maumau2.setCurrentPlayer(mauMauUser3);
		maumau2 = mauMauMgmt.shoutMauProcedure(maumau2, true);
		assertEquals(true, maumau2.getCurrentPlayer().isMau());
	}
	
	@Test
	public void testShoutMauProcedureTestMauSetFalse() {
		MauMauUser mauMauUser3 = new MauMauUser("Superman", new LinkedList <Card> (), 0, true, false, false, false, false);
		maumau2.getPlayers().add(mauMauUser3);
		maumau2.setCurrentPlayer(mauMauUser3);
		maumau2 = mauMauMgmt.shoutMauProcedure(maumau2, false);
		assertEquals(false, maumau2.getCurrentPlayer().isMau());
	}
	
	
	@Test 
	public void testShoutMauMauProcedureMauMauSetTrue() {
		MauMauUser mauMauUser3 = new MauMauUser("Superman", new LinkedList <Card> (), 0, true, false, false, false, false);
		maumau2.getPlayers().add(mauMauUser3);
		maumau2.setCurrentPlayer(mauMauUser3);
		maumau2 = mauMauMgmt.shoutMauMauProcedure(maumau2, true);	
		assertEquals(true, maumau2.getCurrentPlayer().isMaumau());
	}
	
	
	@Test
	public void testShoutMauMauProcedureMauMauSetFalse() {
		MauMauUser mauMauUser3 = new MauMauUser("Superman", new LinkedList <Card> (), 0, true, false, false, false, false);
		maumau2.getPlayers().add(mauMauUser3);
		maumau2.setCurrentPlayer(mauMauUser3);
		maumau2 = mauMauMgmt.shoutMauMauProcedure(maumau2, false);	
		assertEquals(false, maumau2.getCurrentPlayer().isMaumau());
	}
	
	
	@Test
	public void testHandleGameStartUsers() {
		LinkedList <Card> cardGameCards = new LinkedList<Card>();
		cardGameCards.add(new Card(Symbol.CLUB, Value.NINE));
		cardGameCards.add(new Card(Symbol.SPADE, Value.KING));
		cardGameCards.add(new Card(Symbol.HEART, Value.TEN));
		cardGameCards.add(new Card(Symbol.DIAMOND, Value.SEVEN));
		CardDeck cardDeck = new CardDeck();
		cardDeck.setCards(cardGameCards);
		when(cardDeckService.createCards()).thenReturn(cardGameCards);
		when(cardDeckService.createCardDeck(cardGameCards)).thenReturn(cardDeck);
		when(cardDeckService.shuffle(cardDeck)).thenReturn(cardGameCards);
		List <String> userNames = new LinkedList <String>();
		userNames.add("name1");
		userNames.add("name2");
		List<MauMauUser> users = new LinkedList <MauMauUser>();
		users.add(new MauMauUser("name1", new LinkedList<Card>(), 0));
		users.add(new MauMauUser("name2", new LinkedList<Card>(), 0));
		when(userService.createUsers(userNames)).thenReturn(users);
		MauMau maumau = mauMauMgmt.handleGameStart(userNames, new MauMauRules(), 2);
		assertEquals("name1", maumau.getPlayers().get(0).getUsername());
	}
	
	
	@Test
	public void testHandleGameStartUsersFirstGraveYardCard() {
		LinkedList <Card> cardGameCards = new LinkedList<Card>();
		cardGameCards.add(new Card(Symbol.CLUB, Value.NINE));
		cardGameCards.add(new Card(Symbol.SPADE, Value.KING));
		cardGameCards.add(new Card(Symbol.HEART, Value.TEN));
		cardGameCards.add(new Card(Symbol.DIAMOND, Value.SEVEN));
		CardDeck cardDeck = new CardDeck();
		cardDeck.setCards(cardGameCards);
		when(cardDeckService.createCards()).thenReturn(cardGameCards);
		when(cardDeckService.createCardDeck(cardGameCards)).thenReturn(cardDeck);
		when(cardDeckService.shuffle(cardDeck)).thenReturn(cardGameCards);
		List <String> userNames = new LinkedList <String>();
		userNames.add("name1");
		userNames.add("name2");
		List<MauMauUser> users = new LinkedList <MauMauUser>();
		users.add(new MauMauUser("name1", new LinkedList<Card>(), 0));
		users.add(new MauMauUser("name2", new LinkedList<Card>(), 0));
		when(userService.createUsers(userNames)).thenReturn(users);
		MauMau maumau = mauMauMgmt.handleGameStart(userNames, new MauMauRules(), 2);
		assertEquals(Symbol.CLUB, maumau.getGraveyard().getCards().get(0).getSymbol());
	}
	
	@Test
	public void testHandleGameStart() {
		LinkedList <Card> cardGameCards = new LinkedList<Card>();
		cardGameCards.add(new Card(Symbol.CLUB, Value.NINE));
		cardGameCards.add(new Card(Symbol.SPADE, Value.KING));
		cardGameCards.add(new Card(Symbol.HEART, Value.TEN));
		cardGameCards.add(new Card(Symbol.DIAMOND, Value.SEVEN));
		CardDeck cardDeck = new CardDeck();
		cardDeck.setCards(cardGameCards);
		when(cardDeckService.createCards()).thenReturn(cardGameCards);
		when(cardDeckService.createCardDeck(cardGameCards)).thenReturn(cardDeck);
		when(cardDeckService.shuffle(cardDeck)).thenReturn(cardGameCards);
		List <String> userNames = new LinkedList <String>();
		userNames.add("name1");
		userNames.add("name2");
		List<MauMauUser> users = new LinkedList <MauMauUser>();
		users.add(new MauMauUser("name1", new LinkedList<Card>(), 0));
		users.add(new MauMauUser("name2", new LinkedList<Card>(), 0));
		when(userService.createUsers(userNames)).thenReturn(users);
		MauMau maumau = mauMauMgmt.handleGameStart(userNames, new MauMauRules(), 2);
		assertEquals(Symbol.CLUB, maumau.getGraveyard().getCards().get(0).getSymbol());
	}
	
	
	
	


	


	

}
