package cards.modell;

import org.springframework.stereotype.Component;

@Component
public class Card {
	public Card() {
		
	}
	
	public Symbol getSymbol() {
		return symbol;
	}

	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	Symbol symbol;
	Value value;
	
	public Card(Symbol symbol, Value value) {
		this.symbol = symbol;
		this.value = value;
	}
	

	
}
