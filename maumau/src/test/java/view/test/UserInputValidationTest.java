package view.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import cards.modell.Card;
import cards.modell.Symbol;
import cards.modell.Value;
import view.UserInputValidation;

public class UserInputValidationTest {
	private static UserInputValidation userInputValidation;
	private static List<Card> cards;
	private static Card cardInHand;
	private static Card cardNotInHand;
	
	@BeforeAll
	public static void init() {
		userInputValidation = new UserInputValidation();
		cards = new LinkedList<Card>();
		cards.add(new Card(Symbol.CLUB, Value.EIGHT));
		cards.add(new Card(Symbol.SPADE, Value.KING));
		cards.add(new Card(Symbol.HEART, Value.TEN));
		cards.add(new Card(Symbol.DIAMOND, Value.SEVEN));
		cardInHand = new Card(Symbol.SPADE, Value.KING);
		cardNotInHand = new Card(Symbol.CLUB, Value.JACK);
		
	}
	
	
	@Test
	void testVerifySymbolInputCorrectInput(){	
	boolean correctSymbolInput = userInputValidation.verifySymbolInput("club");
	assertEquals(true, correctSymbolInput);
	}
	
	
	@Test
	void testVerifySymbolInputFalseInput(){	
	boolean correctSymbolInput = userInputValidation.verifySymbolInput("hort");
	assertEquals(false, correctSymbolInput);
	}
	
	@Test 
	void testVerifyValueInputCorrectInput() {
	boolean correctInput = userInputValidation.verifyValueInput("nine");
	assertEquals(true, correctInput);
	}
	
	
	@Test 
	void testVerifyValueInputWrongInput() {
	boolean correctInput = userInputValidation.verifyValueInput("nö");
	assertEquals(false, correctInput);
	}
	
	
	
	@Test
	void testVerifyPlayOrTakeInputCorrectInput() {
	boolean correctInput = userInputValidation.verifyPlayOrTakeInput("p");
	assertEquals(true, correctInput);
	}
	
	
	@Test
	void testVerifyPlayOrTakeInputWrongInput() {
	boolean correctInput = userInputValidation.verifyPlayOrTakeInput(" p");
	assertEquals(false, correctInput);
	}
	
	@Test 
	 void testVerifyIfCardInUserHandInHand() {
	 boolean inHand = userInputValidation.verifyIfCardInUserHand(cards, cardInHand);
	 assertEquals(true, inHand);
	
	}
	
	@Test 
	 void testVerifyIfCardInUserHandNotInHand() {
	 boolean inHand = userInputValidation.verifyIfCardInUserHand(cards, cardNotInHand);
	 assertEquals(false, inHand);
	
	}
	
	@Test
	void checkYesNoValidationCorrectWithYes() {
		boolean correct = userInputValidation.verifyYesNoInput("j");
		assertEquals(true, correct);
	}
	
	@Test
	void checkYesNoValidationCorrectWithNo() {
		boolean correct = userInputValidation.verifyYesNoInput("n");
		assertEquals(true, correct);
	}
	
	@Test
	void checkYesNoValidationWrongWithNo() {
		boolean correct = userInputValidation.verifyYesNoInput("nee");
		assertEquals(false, correct);
	}
	
	@Test
	void checkYesNoValidationWrongWithYes() {
		boolean correct = userInputValidation.verifyYesNoInput("si");
		assertEquals(false, correct);
	}
	
	@Test void testYesOrNoWithYes(){
		boolean yes = userInputValidation.yesOrNo("j");
		assertEquals(true, yes);
	}
	
	@Test void testYesOrNoWithNo(){
		boolean no = userInputValidation.yesOrNo("n");
		assertEquals(false, no);
	}
}
