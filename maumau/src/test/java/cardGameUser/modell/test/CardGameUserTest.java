package cardGameUser.modell.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cards.modell.Card;
import cards.modell.Symbol;
import cards.modell.Value;
import userAdministration.UserService;
import userAdministration.management.UserMgmt;
import userAdministration.modell.MauMauUser;

public class CardGameUserTest {
	static private MauMauUser mauMauUser;
	static public List <Card> cardHand;
	static private UserService userMgmt;

	@Before
	public void setUpBeforeClass() throws Exception {
		userMgmt = new UserMgmt();
		mauMauUser = new MauMauUser("Kaan", cardHand, 0, false, false, false, false, false);
		cardHand = new LinkedList<Card>();
		cardHand.add(new Card(Symbol.CLUB, Value.EIGHT));
		cardHand.add(new Card(Symbol.DIAMOND, Value.ACE));
		cardHand.add(new Card(Symbol.HEART, Value.TEN));
		mauMauUser.setHand(cardHand);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testPlayCardWithNegativeIndex() {
		// Arrange
		int index = -1;
		// Act
		userMgmt.playCard(index, mauMauUser);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testPlayCardWithIndexTooHigh() {
		// Arrange
		int index = 3;
		// Act
		userMgmt.playCard(index, mauMauUser);
	}

	@Test
	public void testTakeCardIsValid() {
		// Arrange
		Card card = new Card(Symbol.HEART, Value.KING);
		// Act
		userMgmt.takeCard(card, mauMauUser);
		// Assert
		assertTrue(card instanceof Card);
	}

	@Test
	public void testShoutMauWithMoreThanTwoCards() {
		assertEquals(userMgmt.shoutMau(mauMauUser), "I can't shout Mau yet!");
	}
	
	@Test
	public void testShoutMauWithOneCard() {
		List<Card>cardHandOneCard = new LinkedList<>();
		cardHandOneCard.add(new Card(Symbol.CLUB, Value.EIGHT));
		mauMauUser.setHand(cardHandOneCard);
		userMgmt.shoutMau(mauMauUser);
		Assert.assertTrue("You cannot shout 'Mau' with only one card in your hand!", mauMauUser.getHand().size() < 2);
	}

	@Test
	public void testShoutMauMauWithMoreThanOneCard() {
		int cardsInHand = mauMauUser.getHand().size();
		userMgmt.shoutMauMau(mauMauUser);
		Assert.assertTrue("You have more than one card in your hand!", cardsInHand > 1);
	}
	
	@Test
	public void testShoutMauMauIsValid() {
		int cardsInHand = 1;
		userMgmt.shoutMauMau(mauMauUser);
		Assert.assertTrue(cardsInHand == 1);
	}
	
}
