package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cardGame.modell.MauMau;
import cardGame.MauMauService;
import cards.modell.Card;
import cards.CardDeckService;
import cards.modell.Symbol;
import persistence.PersistenceService;
import persistence.modell.JPAHandler;
import rules.modell.MauMauRules;
import rules.RulesService;
import userAdministration.modell.MauMauUser;
import util.exceptions.DbConnectionException;
import util.exceptions.LoadGameException;
import util.exceptions.NoMoreCardsException;
import util.exceptions.SaveOrUpdateException;
import userAdministration.UserService;
import view.*;
import virtualUserAdministration.VirtualUserService;

// TODO: Auto-generated Javadoc
/**
 * The Class GameController.
 */
@Controller
public class GameController implements GameUI {

	@Autowired
	private MauMau maumau;

	@Autowired
	private JPAHandler handler;
	
	@Autowired
	private MauMauService mauMauService;
	
	@Autowired
	private CardDeckService cardDeckService;

	@Autowired
	private UserService userService;

	@Autowired
	private RulesService rulesService;
	
	@Autowired
	private VirtualUserService virtualUserService;
	
	@Autowired
	private PersistenceService persistenceService;

	@Autowired
	private UserCommunication userCommunication;
	
	@Autowired
	private UserInformation userInformation;
	
	@Autowired
	private WelcomeGame welcomeGame;

	private boolean persistGames;

	/**
	 * Run.
	 */
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
				this.maumau.setEndGame(false);
				this.maumau.setPlayAgain(false);
				if (userNames.size() < selectedGame.getPlayers().size()) {
					for (MauMauUser user : selectedGame.getPlayers()) {
						if (!userNames.contains(user.getUsername()))
							user.setVirtualUser(true);
					}
				}
			} else {
				this.maumau = mauMauService.handleGameStart(userNames, new MauMauRules(), 5);

			}

			this.maumau = this.welcomeGame.configureGame(this.maumau);
			this.persist(this.maumau, handler);

			while (this.maumau.isEndGame() == false) {

				Card mostRecentCard = cardDeckService.giveMostRecentCard(this.maumau.getGraveyard());

				userInformation.giveInfoAboutCurrentPlayer(this.maumau.getCurrentPlayer());
				userInformation.giveCurrentTopCardInfo(mostRecentCard);

				if (!rulesService.checkIfUserCanPlay(maumau.getAmountSeven(), this.maumau.getRuleSet(), mostRecentCard,
						maumau.getCurrentPlayer().getHand(), maumau.getUserwish())) {
					userInformation.informAboutCardsThatWereTaken(this.maumau.getAmountSeven());
					try {
						this.maumau = mauMauService.giveAllCardsToUserThatUserHasToTake(this.maumau);
					} catch (NoMoreCardsException e) {
						userInformation.noMoreCardNextRoundWithoutTakingCards();
						maumau.setAmountSeven(0);
						this.maumau = mauMauService.nextPlayer(this.maumau);
						this.persist(this.maumau, this.handler);
						continue;
					}
					this.persist(this.maumau, this.handler);
					this.maumau = mauMauService.nextPlayer(this.maumau);
					this.persist(this.maumau, this.handler);

				} else {

					if (rulesService.isBube(mostRecentCard, this.maumau.getRuleSet())) {
						userInformation.informUserAboutOtherUsersWish(this.maumau.getCurrentPlayer(), this.maumau.getUserwish());
					}

					this.maumau = handleMauAndMauMauProcedure(this.maumau, rulesService);
					this.persist(this.maumau, this.handler);

					// User plays card here:
					try {
						this.getValidCard(mostRecentCard);

					} catch (NoMoreCardsException e) {
						userInformation.noMoreCards();
						// Inform user that the deck is out of cards
						// virtual player plays for human player due to he is a dump asshole and
						// tries to draw cards although all cards are already drawn7
						if (rulesService.checkIfUserCanPlay(maumau.getAmountSeven(), this.maumau.getRuleSet(), mostRecentCard,
								maumau.getCurrentPlayer().getHand(), maumau.getUserwish())) {
							Card validCard = virtualUserService.playNextPossibleCardFromHand(maumau.getCurrentPlayer(),
									maumau, mostRecentCard);

							if (validCard == null) {
								this.maumau = mauMauService.nextPlayer(this.maumau);
								this.persist(this.maumau, this.handler);
								continue;
							}

							this.maumau = mauMauService.playCardProcedure(maumau, validCard);
							this.maumau.setAmountSeven(0);
							this.persist(this.maumau, this.handler);
						}
					}

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

					this.maumau = mauMauService.nextPlayer(this.maumau);
					this.persist(this.maumau, this.handler);
				}
			}

		} while (this.maumau.isPlayAgain());
		System.out.println("Thanks for playing Maumau, have a nice day!");

	}

	/**
	 * Gets the valid card.
	 *
	 * @param maumau         the maumau
	 * @param mostRecentCard the most recent card
	 * @param mauMauRules    the mau mau rules
	 * @param ruleservice    the ruleservice
	 * @return the valid card
	 */
	public void getValidCard(Card mostRecentCard) {
		boolean valid = false;
		Card card = null;
		while (valid == false) {
			if (maumau.getCurrentPlayer().isVirtualUser()) {
				card = virtualUserService.playNextPossibleCardFromHand(maumau.getCurrentPlayer(), maumau,
						mostRecentCard);

				if (card == null) {
					this.maumau = mauMauService.giveAllCardsToUserThatUserHasToTake(maumau);
					this.persist(this.maumau, this.handler);
					break;
				}
			} else {
				Integer index = this.userCommunication.askForCardUserWantsToPlay(this.maumau.getCurrentPlayer());

				if (index != null && index != -1 && index != -99 && index >= 0
						&& index < this.maumau.getCurrentPlayer().getHand().size()) {
					card = this.userService.playCard(index, this.maumau.getCurrentPlayer());

				} else if (index == -99) {
					this.maumau.setEndGame(true);
					this.maumau.setPlayAgain(true);
					break;
				} else if (index == -1) {
					this.maumau = mauMauService.giveAllCardsToUserThatUserHasToTake(maumau);
					this.persist(this.maumau, this.handler);
					break;
				}

			}
			if (card != null && this.rulesService.checkIsValid(mostRecentCard, card, this.maumau.getUserwish(),
					this.maumau.getAmountSeven(), this.maumau.getRuleSet())) {
				valid = true;
				if (this.maumau.getUserwish() != null
						&& this.rulesService.checkIfUserWishFulfilled(card, maumau.getUserwish())) {
					// valid = true;
					this.maumau.setUserwish(null);
				} /*
					 * else if (this.maumau.getUserwish() != null &&
					 * !this.rulesService.checkIfUserWishFulfilled(card, maumau.getUserwish())) {
					 * valid = false; }
					 */else if (maumau.getAmountSeven() > 0
						&& !this.rulesService.isSeven(card, this.maumau.getRuleSet())
						&& mostRecentCard.getSymbol() == card.getSymbol()) {
					// valid = true;
					this.maumau = this.mauMauService.dealPenaltyCards(
							this.maumau.getAmountSeven() * this.maumau.getRuleSet().getPenaltyOnSeven(), this.maumau);
					this.maumau.setAmountSeven(0);
					this.persist(this.maumau, this.handler);

				}

				if (this.rulesService.isBube(card, this.maumau.getRuleSet())) {
					Symbol symbol = null;
					if (!this.maumau.getCurrentPlayer().isVirtualUser()) {
						String input = userCommunication.askForUserWish(this.maumau.getCurrentPlayer());
						symbol = userCommunication.getSymbolFromString(input);
					} else {
						symbol = virtualUserService.makeWhishByTakingFirstCardSymbol(this.maumau.getCurrentPlayer());
					}
					this.maumau.setUserwish(symbol);
					this.persist(this.maumau, this.handler);

				} else if (this.rulesService.isSeven(card, this.maumau.getRuleSet())) {
					this.maumau.setAmountSeven(this.maumau.getAmountSeven() + 1);
					this.persist(this.maumau, this.handler);
				}

				this.maumau = mauMauService.playCardProcedure(this.maumau, card);
				this.persist(this.maumau, this.handler);

				if (card != null && rulesService.isEight(card, this.maumau.getRuleSet())) {
					this.maumau = mauMauService.skipRound(this.maumau.getCurrentPlayer(), this.maumau);
					this.persist(this.maumau, this.handler);
					MauMauUser userWhoHastToSkipRound = this.maumau.getCurrentPlayer();
					userInformation.giveSkipRoundInfo(userWhoHastToSkipRound);
				}
			}

			if (valid == false) {
				userInformation.informAboutMissingPermissionForUsersAction();
				userInformation.giveCurrentTopCardInfo(mostRecentCard);
			}
		}
		// return card;
	}

	/**
	 * Handle mau and mau mau procedure.
	 *
	 * @param maumau      the maumau
	 * @param ruleService the rule service
	 * @return the mau mau
	 */
	public MauMau handleMauAndMauMauProcedure(MauMau maumau, RulesService ruleService) {
		boolean mauPossible = ruleService.checkShoutMauPossible(this.maumau.getCurrentPlayer(),
				this.maumau.getRuleSet());
		boolean maumauPossible = ruleService.checkShoutMauMauPossible(this.maumau.getCurrentPlayer(),
				this.maumau.getRuleSet());

		if (mauPossible) {
			System.out.println("mauPossible");
			if (!this.maumau.getCurrentPlayer().isVirtualUser()) {
				boolean mau = userCommunication.askIfUserWantsToShoutMau();
				this.maumau = mauMauService.shoutMauProcedure(this.maumau, mau);
			} else if (this.maumau.getCurrentPlayer().isVirtualUser()) {
				System.out.println("Virtual User shouts \"Mau!\"");
				this.maumau = mauMauService.shoutMauProcedure(this.maumau, true);
			}
			this.persist(this.maumau, this.handler);
		}

		if (maumauPossible) {
			System.out.println("MauMauPossible");
			if (!this.maumau.getCurrentPlayer().isVirtualUser()) {
				boolean shoutMaumau = userCommunication.askIfUserWantsToShoutMauMau();
				this.maumau = mauMauService.shoutMauMauProcedure(this.maumau, shoutMaumau);
			} else if (this.maumau.getCurrentPlayer().isVirtualUser()) {
				System.out.println("Virtual User shouts \"MauMau!\"");
				this.maumau = mauMauService.shoutMauMauProcedure(this.maumau, true);
			}
			this.persist(this.maumau, this.handler);
		}

		return this.maumau;
	}

	/**
	 * Establish db connection.
	 */
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

	/**
	 * Load games.
	 *
	 * @param userNames the user names
	 * @return the list
	 */
	private List<MauMau> loadGames(List<String> userNames) {
		try {
			if (this.persistGames)
				return this.persistenceService.loadGames(handler, userNames);
		} catch (LoadGameException e) {
			e.printCustomFailureMessages("Cannot load games. Please restart the system or play a new Game.");
		}
		return null;

	}

	/**
	 * Persist.
	 *
	 * @param maumau  the maumau
	 * @param handler the handler
	 */
	private void persist(MauMau maumau, JPAHandler handler) {
		try {
			if (this.persistGames)
				this.persistenceService.persistGame(maumau, handler);

		} catch (SaveOrUpdateException e) {
			e.printCustomFailureMessages("Game cannot be saved. Try again on next action.");
		}
	}

}
