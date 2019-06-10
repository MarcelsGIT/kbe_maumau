package cardGame.modell;

import java.util.List;

import org.springframework.stereotype.Component;

import cards.modell.CardDeck;
import cards.modell.Symbol;
import rules.modell.MauMauRules;
import userAdministration.modell.MauMauUser;

@Component
public class MauMau {
	CardDeck deck;
	CardDeck graveyard;
	int currentPlayerIndex;
	boolean penaltyTime;
	boolean endGame;
	MauMauUser winner = null;
	int amountSeven;
	Symbol userwish;
	boolean playAgain = false;
	List<MauMauUser> players;
	MauMauRules ruleSet;
	
	public MauMau(){
		
	}
	
	public MauMau(List<MauMauUser> players, MauMauRules ruleSet, CardDeck deck, CardDeck graveyard,
			int currentPlayerIndex, boolean endGame, MauMauUser winner, int amountSeven, Symbol userwish) {
		super();
		this.players = players;
		this.ruleSet = ruleSet;
		this.deck = deck;
		this.graveyard = graveyard;
		this.currentPlayerIndex = currentPlayerIndex;
		this.endGame = endGame;
		this.winner = winner;
		this.amountSeven = amountSeven;
		this.userwish = userwish;
	}
	
	public boolean isPlayAgain() {
		return playAgain;
	}


	public void setPlayAgain(boolean playAgain) {
		this.playAgain = playAgain;
	}
	
	
	public Symbol getUserwish() {
		return userwish;
	}

	public void setUserwish(Symbol userwish) {
		this.userwish = userwish;
	}

	public int getAmountSeven() {
		return amountSeven;
	}
	public void setAmountSeven(int amountSeven) {
		this.amountSeven = amountSeven;
	}

	public List<MauMauUser> getPlayers() {
		return players;
	}
	public void setPlayers(List<MauMauUser> players) {
		this.players = players;
	}
	public MauMauRules getRuleSet() {
		return ruleSet;
	}
	public void setRuleSet(MauMauRules ruleSet) {
		this.ruleSet = ruleSet;
	}
	public CardDeck getDeck() {
		return deck;
	}
	public void setDeck(CardDeck deck) {
		this.deck = deck;
	}
	public CardDeck getGraveyard() {
		return graveyard;
	}
	public void setGraveyard(CardDeck graveyard) {
		this.graveyard = graveyard;
	}
	public int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}
	public void setCurrentPlayerIndex(int currentPlayer) {
		this.currentPlayerIndex = currentPlayer;
	}

	public boolean isEndGame() {
		return endGame;
	}
	
	public void setEndGame(boolean endGame) {
		this.endGame = endGame;
	} 
	public MauMauUser getWinner() {
		return winner;
	}
	public void setWinner(MauMauUser winner) {
		this.winner = winner;
	}	
	public MauMauUser getCurrentPlayer() {
		return this.players.get(this.currentPlayerIndex);
	}
	public void setCurrentPlayer(MauMauUser player) {
		this.currentPlayerIndex = this.players.indexOf(player);
	}
}
