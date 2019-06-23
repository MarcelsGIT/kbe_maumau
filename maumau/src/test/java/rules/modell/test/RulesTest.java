package rules.modell.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cardGame.modell.MauMau;
import cards.modell.Card;
import cards.modell.Symbol;
import cards.modell.Value;
import rules.RulesService;
import rules.management.RulesMgmt;
import rules.modell.MauMauRules;
import userAdministration.modell.MauMauUser;

public class RulesTest {
	private MauMauRules rules;
	private RulesService rulesMgmt;
	private List<Card> cards;

	
	@BeforeEach
	public void init() {
		this.rules = new MauMauRules();
		this.rules.setJackOnEverything(false);
		this.rules.setJackOnJack(false);
		this.cards = new LinkedList<Card>();
		this.rulesMgmt = new RulesMgmt();
		this.cards.add(new Card(Symbol.CLUB, Value.EIGHT)); //0
		this.cards.add(new Card(Symbol.DIAMOND, Value.SEVEN));//1
		this.cards.add(new Card(Symbol.DIAMOND, Value.JACK));//2
		this.cards.add(new Card(Symbol.CLUB, Value.KING));///3
		this.cards.add(new Card(Symbol.DIAMOND, Value.TEN));//4
		this.cards.add(new Card(Symbol.CLUB, Value.JACK));//5
	}
	
	@Test
	public void testPositiveNextCardValidation() {
		boolean isValid = this.rulesMgmt.checkIsValid(this.cards.get(3), this.cards.get(0), rules);
		assertTrue(isValid);
	}
	
	@Test
	public void testNegativeNextCardValidation() {
		boolean isValid = this.rulesMgmt.checkIsValid(this.cards.get(3), this.cards.get(4), rules);
		assertFalse(isValid);
		
	}
	
	@Test
	public void testJackOnJackPositive() {
		this.rules.setJackOnJack(true);
		boolean isValid = this.rulesMgmt.checkIsValid(this.cards.get(2), this.cards.get(5), rules);
		assertTrue(isValid);
	}
	
	@Test
	public void testJackOnJackNegative() {
		this.rules.setJackOnJack(false);
		boolean isValid = this.rulesMgmt.checkIsValid(this.cards.get(2), this.cards.get(5), rules);
		assertFalse(isValid);
	}
	
	@Test
	public void testJackOnEverythingPositive() {
		this.rules.setJackOnEverything(true);
		this.rules.setJackOnJack(false);
		boolean isValid = this.rulesMgmt.checkIsValid(this.cards.get(4), this.cards.get(5), rules);
		assertTrue(isValid);
	}
	
	@Test
	public void testJackOnEverythingNegative() {
		this.rules.setJackOnEverything(false);
		this.rules.setJackOnJack(false);
		boolean isValid = this.rulesMgmt.checkIsValid(this.cards.get(4), this.cards.get(5), rules);
		assertFalse(isValid);
	}
	
	@Test
	public void testPositiveIsEight() {
		boolean isValid = this.rulesMgmt.isEight(this.cards.get(0), rules);
		assertTrue(isValid);
	}
	
	@Test
	public void testNegativeIsEight() {
		boolean isValid = this.rulesMgmt.isEight(this.cards.get(1), rules);
		assertFalse(isValid);
	}
	
	@Test
	public void testPositiveIsSeven() {
		boolean isValid = this.rulesMgmt.isSeven(this.cards.get(1), rules);
		assertTrue(isValid);
	}
	
	@Test
	public void testNegativeIsSeven() {
		boolean isValid = this.rulesMgmt.isSeven(this.cards.get(2), rules);
		assertFalse(isValid);
	}
	
	@Test
	public void testPositiveIsBube() {
		boolean isValid = this.rulesMgmt.isBube(this.cards.get(2), rules);
		assertTrue(isValid);
	}
	
	@Test
	public void testNegativeIsBube() {
		boolean isValid = this.rulesMgmt.isBube(this.cards.get(3), rules);
		assertFalse(isValid);
	}
	
	@Test
	public void testCheckIsValidCardNotValid() {
		Card lastCard = new Card(Symbol.CLUB, Value.ACE);
		Card userCard = new Card(Symbol.DIAMOND, Value.EIGHT);
		boolean valid = rulesMgmt.checkIsValid(lastCard, userCard, rules);
		assertFalse(valid);
	}
	
	@Test
	public void testCheckIsValidCardValidSameValue() {
		Card lastCard = new Card(Symbol.CLUB, Value.ACE);
		Card userCard = new Card(Symbol.HEART, Value.ACE);
		boolean valid = rulesMgmt.checkIsValid(lastCard, userCard, rules);
		assertTrue(valid);
	}
	
	@Test
	public void testCheckIsValidCardValidSameSymbol() {
		Card lastCard = new Card(Symbol.CLUB, Value.ACE);
		Card userCard = new Card(Symbol.CLUB, Value.EIGHT);
		boolean valid = rulesMgmt.checkIsValid(lastCard, userCard, rules);
		assertTrue(valid);
	}
	
	
	
	@Test
	public void testShoutMauPossibleTrue() {
		LinkedList <Card> userCards = new LinkedList <Card>();
		userCards.add(new Card(Symbol.CLUB, Value.ACE));
		userCards.add(new Card(Symbol.CLUB, Value.EIGHT));
		MauMauUser mauMauUser = new MauMauUser("username",userCards,0,true,false, false, false, false);
		boolean possible = rulesMgmt.checkShoutMauPossible(mauMauUser, rules);
		assertTrue(possible);
	}
	
	@Test
	public void testShoutMauPossibleFalse() {
		LinkedList <Card> userCards = new LinkedList <Card>();
		userCards.add(new Card(Symbol.CLUB, Value.ACE));
		userCards.add(new Card(Symbol.CLUB, Value.EIGHT));
		userCards.add(new Card(Symbol.CLUB, Value.KING));
		MauMauUser mauMauUser = new MauMauUser("username",userCards,0,true,false, false, false, false);
		boolean possible = rulesMgmt.checkShoutMauPossible(mauMauUser, rules);
		assertFalse(possible);
	}
	
	
	@Test 
	public void testCheckShoutMauMauPossibleTrue() {
		LinkedList <Card> userCards = new LinkedList <Card>();
		userCards.add(new Card(Symbol.CLUB, Value.ACE));
		MauMauUser mauMauUser = new MauMauUser("username",userCards,0,true,false, false, false, false);
		boolean possible = rulesMgmt.checkShoutMauMauPossible(mauMauUser, rules);
		assertTrue(possible);
	}
	
	@Test
	public void testCheckShoutMauMauPossibleFalse() {
		LinkedList <Card> userCards = new LinkedList <Card>();
		userCards.add(new Card(Symbol.CLUB, Value.ACE));
		userCards.add(new Card(Symbol.CLUB, Value.EIGHT));
		MauMauUser mauMauUser = new MauMauUser("username",userCards,0,true,false, false, false, false);
		boolean possible = rulesMgmt.checkShoutMauMauPossible(mauMauUser, rules);
		assertFalse(possible);
	}
	
	@Test
	public void testCheckIfUserWishFulfilledTrue() {
		Symbol userwish = Symbol.CLUB;
		Card userCard = new Card(Symbol.CLUB, Value.ACE);
		boolean fulfilled = rulesMgmt.checkIfUserWishFulfilled(userCard, userwish);
		assertTrue(fulfilled);
	}
	
	@Test
	public void testCheckIfUserWishFulfilledFalse() {
		Symbol userwish = Symbol.CLUB;
		Card userCard = new Card(Symbol.DIAMOND, Value.ACE);
		boolean fulfilled = rulesMgmt.checkIfUserWishFulfilled(userCard, userwish);
		assertFalse(fulfilled);
	}
	
	
	@Test
	public void testCheckIfUserCanPlayTrue() {
		Card lastCard = new Card(Symbol.CLUB, Value.ACE);
		LinkedList <Card> userCards = new LinkedList <Card>();
		userCards.add(new Card(Symbol.CLUB, Value.EIGHT));
		boolean canPlay = rulesMgmt.checkIfUserCanPlay(0, rules, lastCard, userCards, Symbol.DIAMOND);
		assertTrue(canPlay);
	}
	
	@Test
	public void testCheckIfUserCanPlayFalse() {
		Card lastCard = new Card(Symbol.CLUB, Value.ACE);
		LinkedList <Card> userCards = new LinkedList <Card>();
		userCards.add(new Card(Symbol.DIAMOND, Value.EIGHT));
		boolean canPlay = rulesMgmt.checkIfUserCanPlay(0, rules, lastCard, userCards, Symbol.DIAMOND);
		assertFalse(canPlay);
	}
	
	@Test
	public void testCheckIfUserCanPlayWithUserWishTrue() {
		Card lastCard = new Card(Symbol.CLUB, Value.JACK);
		LinkedList <Card> userCards = new LinkedList <Card>();
		userCards.add(new Card(Symbol.DIAMOND, Value.EIGHT));
		boolean canPlay = rulesMgmt.checkIfUserCanPlay(0, rules, lastCard, userCards, Symbol.DIAMOND);
		assertTrue(canPlay);
	}
	
	
	@Test
	public void testCheckIfUserCanPlayWithUserWishFalse() {
		Card lastCard = new Card(Symbol.CLUB, Value.JACK);
		LinkedList <Card> userCards = new LinkedList <Card>();
		userCards.add(new Card(Symbol.DIAMOND, Value.EIGHT));
		boolean canPlay = rulesMgmt.checkIfUserCanPlay(0, rules, lastCard, userCards, Symbol.CLUB);
		assertFalse(canPlay);
	}
	
	@Test
	public void testCheckIfUserCanPlayWithSevenTrue() {
		Card lastCard = new Card(Symbol.CLUB, Value.SEVEN);
		LinkedList <Card> userCards = new LinkedList <Card>();
		userCards.add(new Card(Symbol.DIAMOND, Value.SEVEN));
		boolean canPlay = rulesMgmt.checkIfUserCanPlay(0, rules, lastCard, userCards, Symbol.CLUB);
		assertTrue(canPlay);
	}
	
	@Test
	public void testCheckIfUserCanPlayWithSevenFalse() {
		Card lastCard = new Card(Symbol.CLUB, Value.SEVEN);
		LinkedList <Card> userCards = new LinkedList <Card>();
		userCards.add(new Card(Symbol.DIAMOND, Value.EIGHT));
		boolean canPlay = rulesMgmt.checkIfUserCanPlay(0, rules, lastCard, userCards, Symbol.CLUB);
		assertFalse(canPlay);
	}
	
	
	
	@Test
	public void testCheckIfSpecialCardSeven() {
		boolean speacialCard = rulesMgmt.checkIfSpecialCard(new Card(Symbol.CLUB, Value.SEVEN));
		assertTrue(speacialCard);	
	}
	
	@Test
	public void testCheckIfSpecialCardEight() {
		boolean speacialCard = rulesMgmt.checkIfSpecialCard(new Card(Symbol.CLUB, Value.EIGHT));
		assertTrue(speacialCard);	
	}
	
	@Test
	public void testCheckIfSpecialCardJack() {
		boolean speacialCard = rulesMgmt.checkIfSpecialCard(new Card(Symbol.CLUB, Value.JACK));
		assertTrue(speacialCard);	
	}
	
	@Test
	public void testCheckIfSpecialCardNotSpecial() {
		boolean speacialCard = rulesMgmt.checkIfSpecialCard(new Card(Symbol.CLUB, Value.ACE));
		assertFalse(speacialCard);	
	}
	
	@Test
	public void testValidOrNotValidCardTrue(){
	Card lastCard = new Card(Symbol.CLUB, Value.ACE);
	Card playedCard = new Card(Symbol.DIAMOND, Value.ACE);
	boolean validCard = rulesMgmt.validCardOrNotValidCard(lastCard, playedCard,  Symbol.CLUB, rules);
	assertTrue(validCard);
	}
	
	@Test
	public void testValidOrNotValidCardFalse(){
	Card lastCard = new Card(Symbol.CLUB, Value.ACE);
	Card playedCard = new Card(Symbol.DIAMOND, Value.EIGHT);
	boolean validCard = rulesMgmt.validCardOrNotValidCard(lastCard, playedCard,  Symbol.CLUB, rules);
	assertFalse(validCard);
	}
	
	

}
