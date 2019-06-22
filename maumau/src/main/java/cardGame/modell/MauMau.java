package cardGame.modell;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import cards.CardDeckService;
import cards.modell.CardDeck;
import cards.modell.Symbol;
import rules.RulesService;
import rules.modell.MauMauRules;
import userAdministration.UserService;
import userAdministration.modell.MauMauUser;

@Entity
@Table(name="Games")
@Component
public class MauMau {
	@Id
	@GeneratedValue
	@Column(name="id", columnDefinition="INT")
	private Integer id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cardDeckId")
	CardDeck deck;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="graveyardId")
	CardDeck graveyard;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="winnerId", columnDefinition="INT")
	MauMauUser winner = null;
	
	@Enumerated(EnumType.STRING)
	Symbol userwish;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="gameId")
	List<MauMauUser> players;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="rulesId")
	MauMauRules ruleSet;
	
	int currentPlayerIndex;
	boolean penaltyTime;
	boolean endGame;
	int amountSeven;
	boolean playAgain = false;
	
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

	public boolean isPenaltyTime() {
		return penaltyTime;
	}

	public void setPenaltyTime(boolean penaltyTime) {
		this.penaltyTime = penaltyTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
