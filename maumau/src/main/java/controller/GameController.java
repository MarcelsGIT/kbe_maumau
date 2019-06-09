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
			System.out.println("GAME START!");
			handleGameStart();
			System.out.println(this.maumau);
			while (this.maumau.isEndGame() == false) {

				Card lastCard = cardDeckService.giveMostRecentCard(this.maumau.getGraveyard());
				
				userInformation.giveCurrentTopCardInfo(lastCard);
				userInformation.giveInfoAboutCurrentPlayer(this.maumau.getCurrentPlayer());

				if (!rulesService.checkIfUserCanPlay(this.maumau, rules, lastCard)) {
					userInformation.informAboutCardsThatWereTaken(this.maumau.getAmountSeven());
					handleUserHasToTakeCards(this.maumau);
					this.maumau = mauMauService.nextPlayer(this.maumau);

				} else {
					
					if (rulesService.isBube(lastCard, rules)) {
						userInformation.informUserAboutOtherUsersWish(lastPlayer, this.maumau.getUserwish());
					}
					
					handleMauAndMauMauProcedure(this.maumau, rulesService);
					// User plays card here:
					handleUserTurn(this.maumau, lastCard, rules, rulesService);
					
					// Checks if the user has won:
					if (this.maumau.getCurrentPlayer().getHand().size() == 0 && this.maumau.getCurrentPlayer().isMaumau()) {
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
					if (playedCard.getSymbol() != lastCard.getSymbol() || lastCard.getValue() != playedCard.getValue()) {
						if (rulesService.isEight(playedCard, rules)) {
							this.maumau = mauMauService.skipRound(lastPlayer, this.maumau);
							MauMauUser userWhoHastToSkipRound = this.maumau.getCurrentPlayer();
							userInformation.giveSkipRoundInfo(userWhoHastToSkipRound);
							} 
						else if (rulesService.isBube(playedCard, rules)) {
							String input = userCommunication.askForUserWish(this.maumau.getCurrentPlayer());
							Symbol symbol = userCommunication.getSymbolFromString(input);
							this.maumau.setUserwish(symbol);
							} 
						else if (rulesService.isSeven(playedCard, rules)) {
							int amountSeven = this.maumau.getAmountSeven() + 1;
							this.maumau.setAmountSeven(amountSeven);
							} 
						else {
							this.maumau.setAmountSeven(0);
						}
					}
					this.maumau = mauMauService.nextPlayer(this.maumau);
				}
			}
			
		} while(this.maumau.isPlayAgain());
		System.out.println("Thanks for playing Maumau, have a nice day!");
	}

	public MauMau handleGameStart() {
		welcomeGame.welcomeMsg();
		this.maumau.setEndGame(false);
		System.out.println(this.maumau.isEndGame());
		List<String> userNames = welcomeGame.askUserNames(welcomeGame.askHowManyUsers());
		List<MauMauUser> users = userService.createUsers(userNames);

		CardDeck gameCardDeck = cardDeckService.createCardDeck(cardDeckService.createCards());
		
		List<Card> shuffledGameCards = cardDeckService.shuffle(gameCardDeck);
		Card firstGraveyardCard = shuffledGameCards.get(0);
		
		while(firstGraveyardCard.getValue() == Value.JACK || firstGraveyardCard.getValue() == Value.SEVEN) {
			shuffledGameCards = cardDeckService.shuffle(gameCardDeck);
			firstGraveyardCard = shuffledGameCards.get(0);
		}
		
		shuffledGameCards.remove(0);
		List<Card> graveyardCards = new LinkedList<Card>();
		graveyardCards.add(firstGraveyardCard);
		
		mauMauService.startGame(users, gameCardDeck, new CardDeck(graveyardCards), rules, 0, false, null, 0, null, this.maumau);
		this.maumau = mauMauService.dealCardsToPlayers(this.maumau, 5, cardDeckService);
		this.maumau = mauMauService.chooseWhoStarts(this.maumau);
		return this.maumau;
	}
	
	public MauMau handleUserHasToTakeCards(MauMau maumau) {
		if (this.maumau.getAmountSeven() > 0) {
			for (int i = 0; i < 2 * this.maumau.getAmountSeven(); i++) {
				this.maumau = mauMauService.giveCardToUser(this.maumau, cardDeckService, userService);
			}
		} else {
			this.maumau = mauMauService.giveCardToUser(this.maumau, cardDeckService, userService);
		}
		this.maumau.setAmountSeven(0);
		return this.maumau;
	}

	public MauMau handleUserTurn(MauMau maumau, Card lastCard, MauMauRules rules, RulesService ruleservice) {
		userInformation.giveCurrentCardDeckInfo(this.maumau.getCurrentPlayer().getHand());
		String playOrTake = userCommunication.askIfPlayCardOrTakeCard();
		if (playOrTake.equalsIgnoreCase("t")) {
			this.maumau = handleUserHasToTakeCards(this.maumau);
		} else {
			this.maumau = playCardProcedure(this.maumau, lastCard, rules, ruleservice);
		}
		return this.maumau;

	}

	public MauMau playCardProcedure(MauMau maumau, Card lastCard, MauMauRules rules, RulesService ruleservice) {
		Card card = getValidCard(this.maumau, lastCard, this.maumau.getRuleSet(), ruleservice);
		this.maumau.getGraveyard().getCards().add(card);
		this.maumau.getCurrentPlayer()
				.setHand(cardDeckService.removeCardFromCardDeckList(this.maumau.getCurrentPlayer().getHand(), card));
		return this.maumau;
	}

	public boolean validCardOrNotValidCard(Card mostRecentCard, Card card, Symbol userwish, MauMauRules mauMauRules,
			RulesService ruleservice) {
		boolean valid = false;
		if (ruleservice.isBube(mostRecentCard, mauMauRules)) {
			valid = ruleservice.checkIfUserWishFulfilled(card, userwish);
		} else {
			valid = ruleservice.checkIsValid(mostRecentCard, card, mauMauRules);
		}
		if (valid == false) {
			userInformation.informAboutMissingPermissionForUsersAction();
		}
		return valid;
	}

	public Card getValidCard(MauMau maumau, Card mostRecentCard, MauMauRules mauMauRules, RulesService ruleservice) {
		boolean valid = false;
		Card card = null;
		while (valid == false) {
			int index = userCommunication.askForCardUserWantsToPlay(this.maumau.getCurrentPlayer());
			card = userService.playCard(index, this.maumau.getCurrentPlayer());
			valid = validCardOrNotValidCard(mostRecentCard, card, this.maumau.getUserwish(), mauMauRules, ruleservice);
		}
		return card;
	}

	public MauMau handleMauAndMauMauProcedure(MauMau maumau, RulesService ruleService) {
		boolean mauPossible = ruleService.checkShoutMauPossible(this.maumau.getCurrentPlayer(), this.maumau.getRuleSet());
		boolean maumauPossible = ruleService.checkShoutMauMauPossible(this.maumau.getCurrentPlayer(), this.maumau.getRuleSet());
		if (mauPossible) {
			this.maumau = shoutMauProcedure(this.maumau, userCommunication);
		}
		if (maumauPossible) {
			this.maumau = shoutMauMauProcedure(this.maumau, userCommunication);
		}
		return this.maumau;
	}

	public MauMau shoutMauProcedure(MauMau maumau, UserCommunication userCommunication) {
		boolean mau = userCommunication.askIfUserWantsToShoutMau();
		if (mau) {
			this.maumau.getCurrentPlayer().setMau(true);
		} else {
			this.maumau.getCurrentPlayer().setMau(false);
		}
		return this.maumau;
	}

	public MauMau shoutMauMauProcedure(MauMau maumau, UserCommunication userCommunication) {
		boolean shoutMaumau = userCommunication.askIfUserWantsToShoutMauMau();
		if (shoutMaumau) {
			this.maumau.getCurrentPlayer().setMaumau(true);
		} else {
			this.maumau.getCurrentPlayer().setMaumau(false);
		}
		return this.maumau;
	}

}
