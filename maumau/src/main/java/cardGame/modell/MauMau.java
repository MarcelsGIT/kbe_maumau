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

// TODO: Auto-generated Javadoc
/**
 * The Class MauMau.
 */
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
	
	/**
	 * Instantiates a new mau mau.
	 */
	public MauMau(){
		
	}
	
	/**
	 * Instantiates a new mau mau.
	 *
	 * @param players the players
	 * @param ruleSet the rule set
	 * @param deck the deck
	 * @param graveyard the graveyard
	 * @param currentPlayerIndex the current player index
	 * @param endGame the end game
	 * @param winner the winner
	 * @param amountSeven the amount seven
	 * @param userwish the userwish
	 */
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
	
	/**
	 * Checks if is play again.
	 *
	 * @return true, if is play again
	 */
	public boolean isPlayAgain() {
		return playAgain;
	}


	/**
	 * Sets the play again.
	 *
	 * @param playAgain the new play again
	 */
	public void setPlayAgain(boolean playAgain) {
		this.playAgain = playAgain;
	}
	
	
	/**
	 * Gets the userwish.
	 *
	 * @return the userwish
	 */
	public Symbol getUserwish() {
		return userwish;
	}

	/**
	 * Sets the userwish.
	 *
	 * @param userwish the new userwish
	 */
	public void setUserwish(Symbol userwish) {
		this.userwish = userwish;
	}

	/**
	 * Gets the amount seven.
	 *
	 * @return the amount seven
	 */
	public int getAmountSeven() {
		return amountSeven;
	}
	
	/**
	 * Sets the amount seven.
	 *
	 * @param amountSeven the new amount seven
	 */
	public void setAmountSeven(int amountSeven) {
		this.amountSeven = amountSeven;
	}

	/**
	 * Gets the players.
	 *
	 * @return the players
	 */
	public List<MauMauUser> getPlayers() {
		return players;
	}
	
	/**
	 * Sets the players.
	 *
	 * @param players the new players
	 */
	public void setPlayers(List<MauMauUser> players) {
		this.players = players;
	}
	
	/**
	 * Gets the rule set.
	 *
	 * @return the rule set
	 */
	public MauMauRules getRuleSet() {
		return ruleSet;
	}
	
	/**
	 * Sets the rule set.
	 *
	 * @param ruleSet the new rule set
	 */
	public void setRuleSet(MauMauRules ruleSet) {
		this.ruleSet = ruleSet;
	}
	
	/**
	 * Gets the deck.
	 *
	 * @return the deck
	 */
	public CardDeck getDeck() {
		return deck;
	}
	
	/**
	 * Sets the deck.
	 *
	 * @param deck the new deck
	 */
	public void setDeck(CardDeck deck) {
		this.deck = deck;
	}
	
	/**
	 * Gets the graveyard.
	 *
	 * @return the graveyard
	 */
	public CardDeck getGraveyard() {
		return graveyard;
	}
	
	/**
	 * Sets the graveyard.
	 *
	 * @param graveyard the new graveyard
	 */
	public void setGraveyard(CardDeck graveyard) {
		this.graveyard = graveyard;
	}
	
	/**
	 * Gets the current player index.
	 *
	 * @return the current player index
	 */
	public int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}
	
	/**
	 * Sets the current player index.
	 *
	 * @param currentPlayer the new current player index
	 */
	public void setCurrentPlayerIndex(int currentPlayer) {
		this.currentPlayerIndex = currentPlayer;
	}

	/**
	 * Checks if is end game.
	 *
	 * @return true, if is end game
	 */
	public boolean isEndGame() {
		return endGame;
	}
	
	/**
	 * Sets the end game.
	 *
	 * @param endGame the new end game
	 */
	public void setEndGame(boolean endGame) {
		this.endGame = endGame;
	} 
	
	/**
	 * Gets the winner.
	 *
	 * @return the winner
	 */
	public MauMauUser getWinner() {
		return winner;
	}
	
	/**
	 * Sets the winner.
	 *
	 * @param winner the new winner
	 */
	public void setWinner(MauMauUser winner) {
		this.winner = winner;
	}	
	
	/**
	 * Gets the current player.
	 *
	 * @return the current player
	 */
	public MauMauUser getCurrentPlayer() {
		return this.players.get(this.currentPlayerIndex);
	}
	
	/**
	 * Sets the current player.
	 *
	 * @param player the new current player
	 */
	public void setCurrentPlayer(MauMauUser player) {
		this.currentPlayerIndex = this.players.indexOf(player);
	}

	/**
	 * Checks if is penalty time.
	 *
	 * @return true, if is penalty time
	 */
	public boolean isPenaltyTime() {
		return penaltyTime;
	}

	/**
	 * Sets the penalty time.
	 *
	 * @param penaltyTime the new penalty time
	 */
	public void setPenaltyTime(boolean penaltyTime) {
		this.penaltyTime = penaltyTime;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
