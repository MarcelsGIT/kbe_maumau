package rules.modell.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cards.modell.Card;
import cards.modell.Symbol;
import cards.modell.Value;
import rules.RulesService;
import rules.management.RulesMgmt;
import rules.modell.MauMauRules;

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
}
