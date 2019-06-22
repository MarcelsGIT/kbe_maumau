package controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cardGame.modell.MauMau;
import cardGame.MauMauService;
import cards.modell.Card;
import cards.modell.CardDeck;
import cards.CardDeckService;
import cards.modell.Symbol;
import cards.modell.Value;
import persistence.PersistenceService;
import persistence.modell.JPAHandler;
import rules.modell.MauMauRules;
import rules.RulesService;
import userAdministration.modell.MauMauUser;
import util.exceptions.DbConnectionException;
import util.exceptions.LoadGameException;
import util.exceptions.SaveOrUpdateException;
import userAdministration.UserService;
import view.*;
import virtualUserAdministration.VirtualUserService;

@Controller
public class GameController implements GameUI {

	@Autowired
	private MauMauService mauMauService;
	@Autowired
	private RulesService mauMauRules;

	@Autowired
	private UserCommunication userCommunication;
	@Autowired
	private UserInformation userInformation;

	@Autowired
	private UserInputValidation userInputValidation;
	@Autowired
	private WelcomeGame welcomeGame;

	@Autowired
	private MauMau maumau;

	@Autowired
	private CardDeckService cardDeckService;

	@Autowired
	private UserService userService;

	@Autowired
	private RulesService rulesService;

	@Autowired
	private MauMauRules rules;

	private MauMauUser lastPlayer;

	@Autowired
	private JPAHandler handler;

	@Autowired
	private PersistenceService persistenceService;

	@Autowired
	private VirtualUserService virtualUserService;

	private boolean persistGames;

	public void run() {
		this.persistGames = true;
		this.establishDbConnection();

		do {
			welcomeGame.welcomeMsg();
			MauMau selectedGame = null;
			List<String> userNames = welcomeGame.askUserNames(welcomeGame.askHowManyUsers());
			List<MauMau> unfinishedGames = this.loadGames(userNames);
			if (unfinishedGames != null) {
				selectedGame = welcomeGame.askReturnToExistingGame(unfinishedGames);
			}

			if (selectedGame != null) {
				this.maumau = selectedGame;
			} else {
				this.maumau = mauMauService.handleGameStart(userNames, rules, 5);

			}

			while (this.maumau.isEndGame() == false) {

				Card lastCard = cardDeckService.giveMostRecentCard(this.maumau.getGraveyard());

				userInformation.giveCurrentTopCardInfo(lastCard);
				userInformation.giveInfoAboutCurrentPlayer(this.maumau.getCurrentPlayer());

				if (!rulesService.checkIfUserCanPlay(maumau.getAmountSeven(), rules, lastCard,
						maumau.getCurrentPlayer().getHand(), maumau.getUserwish())) {
					userInformation.informAboutCardsThatWereTaken(this.maumau.getAmountSeven());
					this.maumau = mauMauService.giveAllCardsToUserThatUserHasToTake(this.maumau);
					this.persist(this.maumau, this.handler);
					this.maumau = mauMauService.nextPlayer(this.maumau);
					this.persist(this.maumau, this.handler);

				} else {

					if (rulesService.isBube(lastCard, rules)) {
						userInformation.informUserAboutOtherUsersWish(lastPlayer, this.maumau.getUserwish());
					}

					this.maumau = handleMauAndMauMauProcedure(this.maumau, rulesService);
					this.persist(this.maumau, this.handler);

					// User plays card here:
					Card validCard = null;
					if(!maumau.getCurrentPlayer().isVirtualUser()) {
					userInformation.giveCurrentCardDeckInfo(this.maumau.getCurrentPlayer().getHand());
					String playOrTake = userCommunication.askIfPlayCardOrTakeCard();
						if (playOrTake.equalsIgnoreCase("t")) {
							this.maumau = mauMauService.giveAllCardsToUserThatUserHasToTake(maumau);
							this.persist(this.maumau, this.handler);
	
							// Hier virtual Spieler rein
						} else {
	
							validCard = getValidCard(maumau, lastCard, maumau.getRuleSet(), rulesService);
						}
					}else if (maumau.getCurrentPlayer().isVirtualUser()) {
						validCard = virtualUserService.playNextPossibleCardFromHand(maumau.getCurrentPlayer(),
								maumau, lastCard);
						}
						this.maumau = mauMauService.playCardProcedure(maumau, validCard);
						this.persist(this.maumau, this.handler);
					

					// Checks if the user has won:
					if (this.maumau.getCurrentPlayer().getHand().size() == 0
							&& this.maumau.getCurrentPlayer().isMaumau()) {
						this.maumau.setWinner(this.maumau.getCurrentPlayer());
						this.persist(this.maumau, this.handler);
						userInformation.informAboutEndOfGame(this.maumau);
						this.maumau.setEndGame(true);
						this.persist(this.maumau, this.handler);
						boolean playAgain = userCommunication.playAgain(this.maumau);
						this.maumau.setPlayAgain(playAgain);
						this.persist(this.maumau, this.handler);
						break;
					}

					// AFTER Card was played or taken
					this.lastPlayer = this.maumau.getCurrentPlayer();
					Card playedCard = cardDeckService.giveMostRecentCard(this.maumau.getGraveyard());

					// If card was played
					if (playedCard.getSymbol() != lastCard.getSymbol()
							|| lastCard.getValue() != playedCard.getValue()) {
						if (rulesService.isEight(playedCard, rules)) {
							this.maumau = mauMauService.skipRound(lastPlayer, this.maumau);
							this.persist(this.maumau, this.handler);
							MauMauUser userWhoHastToSkipRound = this.maumau.getCurrentPlayer();
							userInformation.giveSkipRoundInfo(userWhoHastToSkipRound);
						} else if (rulesService.isBube(playedCard, rules)) {
							String input = userCommunication.askForUserWish(this.maumau.getCurrentPlayer());
							Symbol symbol = userCommunication.getSymbolFromString(input);
							this.maumau.setUserwish(symbol);
							this.persist(this.maumau, this.handler);
						} else if (rulesService.isSeven(playedCard, rules)) {
							int amountSeven = this.maumau.getAmountSeven() + 1;
							this.maumau.setAmountSeven(amountSeven);
							this.persist(this.maumau, this.handler);
						} else {
							this.maumau.setAmountSeven(0);
							// this.persist(this.maumau, this.handler);
						}
					}
					this.maumau = mauMauService.nextPlayer(this.maumau);
					this.persist(this.maumau, this.handler);
				}
			}

		}while(this.maumau.isPlayAgain());System.out.println("Thanks for playing Maumau, have a nice day!");

	}

	public Card getValidCard(MauMau maumau, Card mostRecentCard, MauMauRules mauMauRules, RulesService ruleservice) {
		boolean valid = false;
		Card card = null;
		while (valid == false) {
			int index = userCommunication.askForCardUserWantsToPlay(this.maumau.getCurrentPlayer());
			card = userService.playCard(index, this.maumau.getCurrentPlayer());
			valid = ruleservice.validCardOrNotValidCard(mostRecentCard, card, this.maumau.getUserwish(), mauMauRules);
			if (valid == false) {
				userInformation.informAboutMissingPermissionForUsersAction();
			}
		}
		return card;
	}

	public MauMau handleMauAndMauMauProcedure(MauMau maumau, RulesService ruleService) {
		boolean mauPossible = ruleService.checkShoutMauPossible(this.maumau.getCurrentPlayer(),
				this.maumau.getRuleSet());
		boolean maumauPossible = ruleService.checkShoutMauMauPossible(this.maumau.getCurrentPlayer(),
				this.maumau.getRuleSet());
		if (mauPossible) {
			boolean mau = userCommunication.askIfUserWantsToShoutMau();
			this.maumau = mauMauService.shoutMauProcedure(this.maumau, mau);
			this.persist(this.maumau, this.handler);
		}
		if (maumauPossible) {
			boolean shoutMaumau = userCommunication.askIfUserWantsToShoutMauMau();
			this.maumau = mauMauService.shoutMauMauProcedure(this.maumau, shoutMaumau);
			this.persist(this.maumau, this.handler);
		}
		return this.maumau;
	}

	private void establishDbConnection() {
		try {
			this.persistenceService.establishConnection("maumau", handler);
		} catch (DbConnectionException e) {
			persistGames = false;
			e.printCustomFailureMessages("Cannot Connect to Server. Game runs locally.",
					"WARNING!!! Your game won�t be saved, you cannot load games.",
					"Please restart to load and save games.");
		}
	}

	private List<MauMau> loadGames(List<String> userNames) {
		try {
			if (this.persistGames)
				return this.persistenceService.loadGames(handler, userNames);
		} catch (LoadGameException e) {
			e.printCustomFailureMessages("Cannot load games. Please restart the system or play a new Game.");
		}
		return null;

	}

	private void persist(MauMau maumau, JPAHandler handler) {
		try {
			if (this.persistGames)
				this.persistenceService.persistGame(maumau, handler);

		} catch (SaveOrUpdateException e) {
			e.printCustomFailureMessages("Game cannot be saved. Try again on next action.");
		}
	}

}
