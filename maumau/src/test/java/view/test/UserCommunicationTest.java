package view.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import cards.modell.Symbol;
import cards.modell.Value;
import view.UserCommunication;

public class UserCommunicationTest {
private static UserCommunication userCommunication;

	
	@BeforeAll
	public static void init() {
		userCommunication = new UserCommunication();

		
	}
	
	
	@Test
	void testGetSymbolFromString() {
		Symbol symbol = userCommunication.getSymbolFromString("Spade");
		assertEquals(Symbol.SPADE, symbol);
	}
	
	@Test
	void testGetValueFromString() {
		Value value = userCommunication.getValueFromString("nine");
		assertEquals(Value.NINE, value);
	}
}
