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
import rules.modell.MauMauRules;
import rules.RulesService;
import userAdministration.modell.MauMauUser;
import userAdministration.UserService;
import view.*;

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

	public void run() {

		do {
			welcomeGame.welcomeMsg();
			List<String> userNames = welcomeGame.askUserNames(welcomeGame.askHowManyUsers());
			this.maumau = mauMauService.handleGameStart(userNames, userService, cardDeckService, rules, mauMauRules);

			while (this.maumau.isEndGame() == false) {

				Card lastCard = cardDeckService.giveMostRecentCard(this.maumau.getGraveyard());

				userInformation.giveCurrentTopCardInfo(lastCard);
				userInformation.giveInfoAboutCurrentPlayer(this.maumau.getCurrentPlayer());

				if (!rulesService.checkIfUserCanPlay(maumau.getAmountSeven(), rules, lastCard,
						maumau.getCurrentPlayer().getHand(), maumau.getUserwish())) {
					userInformation.informAboutCardsThatWereTaken(this.maumau.getAmountSeven());
					mauMauService.handleUserHasToTakeCards(this.maumau, cardDeckService, userService);
					this.maumau = mauMauService.nextPlayer(this.maumau);

				} else {

					if (rulesService.isBube(lastCard, rules)) {
						userInformation.informUserAboutOtherUsersWish(lastPlayer, this.maumau.getUserwish());
					}

					handleMauAndMauMauProcedure(this.maumau, rulesService);

					// User plays card here:
					userInformation.giveCurrentCardDeckInfo(this.maumau.getCurrentPlayer().getHand());
					String playOrTake = userCommunication.askIfPlayCardOrTakeCard();
					if (playOrTake.equalsIgnoreCase("t")) {
						maumau = mauMauService.handleUserHasToTakeCards(maumau, cardDeckService, userService);
					} else {
						Card validCard = getValidCard(maumau, lastCard, maumau.getRuleSet(), rulesService);
						maumau = mauMauService.playCardProcedure(maumau,  cardDeckService, validCard);
					}

					// Checks if the user has won:
					if (this.maumau.getCurrentPlayer().getHand().size() == 0
							&& this.maumau.getCurrentPlayer().isMaumau()) {
						this.maumau.setWinner(this.maumau.getCurrentPlayer());
						userInformation.informAboutEndOfGame(this.maumau);
						this.maumau.setEndGame(true);
						boolean playAgain = userCommunication.playAgain(this.maumau);
						this.maumau.setPlayAgain(playAgain);
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
							MauMauUser userWhoHastToSkipRound = this.maumau.getCurrentPlayer();
							userInformation.giveSkipRoundInfo(userWhoHastToSkipRound);
						} else if (rulesService.isBube(playedCard, rules)) {
							String input = userCommunication.askForUserWish(this.maumau.getCurrentPlayer());
							Symbol symbol = userCommunication.getSymbolFromString(input);
							this.maumau.setUserwish(symbol);
						} else if (rulesService.isSeven(playedCard, rules)) {
							int amountSeven = this.maumau.getAmountSeven() + 1;
							this.maumau.setAmountSeven(amountSeven);
						} else {
							this.maumau.setAmountSeven(0);
						}
					}
					this.maumau = mauMauService.nextPlayer(this.maumau);
				}
			}

		} while (this.maumau.isPlayAgain());
		System.out.println("Thanks for playing Maumau, have a nice day!");
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
		}
		if (maumauPossible) {
			boolean shoutMaumau = userCommunication.askIfUserWantsToShoutMauMau();
			this.maumau = mauMauService.shoutMauMauProcedure(this.maumau, shoutMaumau);
		}
		return this.maumau;
	}

}
