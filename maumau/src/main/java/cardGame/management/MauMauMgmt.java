package cardGame.management;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cardGame.MauMauService;
import cardGame.modell.MauMau;
import cards.CardDeckService;
import cards.management.CardDeckImpl;
import cards.modell.Card;
import cards.modell.CardDeck;
import cards.modell.Symbol;
import cards.modell.Value;
import rules.RulesService;
import rules.management.RulesMgmt;
import rules.modell.MauMauRules;
import userAdministration.UserService;
import userAdministration.management.UserMgmt;
import userAdministration.modell.MauMauUser;

@Component
public class MauMauMgmt implements MauMauService {


private CardDeckService cardDeckService;
private UserService userService;
private RulesService rulesService;

	
	

		public MauMau startGame(List<MauMauUser> userList, CardDeck cardDeck, CardDeck graveyard, MauMauRules rules,
				int currentPlayerIndex, boolean endGame, MauMauUser winner, int amountSeven, Symbol userwish, MauMau mauMau) {
			this.ensureServicesAvailability();
			mauMau.setPlayers(userList);
			mauMau.setDeck(cardDeck);
			mauMau.setGraveyard(graveyard);
			mauMau.setRuleSet(rules);
			mauMau.setCurrentPlayerIndex(currentPlayerIndex);
			mauMau.setWinner(winner);
			mauMau.setAmountSeven(amountSeven);
			mauMau.setUserwish(userwish);
			return mauMau;
		}

		// Kaan
		public MauMau chooseWhoStarts(MauMau mauMau) {
			this.ensureServicesAvailability();
			List<MauMauUser> allPlayers = mauMau.getPlayers();
			int randomIndex = (int) Math.random() * mauMau.getPlayers().size();
			MauMauUser starterPlayer = allPlayers.get(randomIndex);
			mauMau.setCurrentPlayer(starterPlayer);
			return mauMau;
		}

		// Marcel
		public MauMau nextPlayer(MauMau mauMau) {
			this.ensureServicesAvailability();
			int nextPlayer = mauMau.getCurrentPlayerIndex() + 1;
			if (mauMau.getPlayers().size() <= nextPlayer) {
				nextPlayer = 0;
			}
			mauMau.setCurrentPlayerIndex(nextPlayer);
			MauMauUser nextUser = mauMau.getPlayers().get(nextPlayer);
			mauMau.setCurrentPlayer(nextUser);
			return mauMau;
		}

		// Kaan
		public MauMau endGame(MauMau mauMau, boolean endGame) {
			this.ensureServicesAvailability();
			mauMau.setEndGame(endGame);
			return mauMau;
		}

		// No need right now
		public MauMau insertWinner(MauMauUser user, MauMau mauMau) {
			this.ensureServicesAvailability();
			mauMau.setWinner(user);
			return mauMau;

		}

		// Marcel
		public MauMau skipRound(MauMauUser user, MauMau mauMau) {
			this.ensureServicesAvailability();
			// TODO Auto-generated method stub
			if(mauMau.getCurrentPlayerIndex() == mauMau.getPlayers().size() -1) {
				mauMau.setCurrentPlayerIndex(0);
			}else {
				mauMau.setCurrentPlayerIndex(mauMau.getCurrentPlayerIndex() + 1);
			}
			
			return mauMau;
		}

		// Marcel
		public List<Card> dealPenaltyCards(int amount, MauMau mauMau) {
			this.ensureServicesAvailability();
			// TODO Auto-generated method stub
			List<Card> deal = new LinkedList<Card>();
			for (int i = 0; i < amount; i++) {

				deal.add(mauMau.getDeck().getCards().get(i));
				mauMau.getDeck().getCards().remove(i);

			}

			return deal;
		}

		public MauMau dealCardsToPlayers(MauMau maumau, int amountCard) {
			this.ensureServicesAvailability();
			List<MauMauUser> userList = maumau.getPlayers();
			CardDeck cardDeck = maumau.getDeck();
			CardDeck graveyard = maumau.getGraveyard();
			for (int i = 0; i < userList.size(); i++) {
				MauMauUser user = userList.get(i);
				List<Card> hand = cardDeckService.dealCards(cardDeck, 5, graveyard);
				user.setHand(hand);
				userList.set(i, user);
				cardDeck.getCards().removeAll(hand);
			}
			maumau.setPlayers(userList);
			maumau.setDeck(cardDeck);
			maumau.setGraveyard(graveyard);

			return maumau;
		}

		public MauMau giveCardToUser(MauMau maumau) {
			this.ensureServicesAvailability();
			CardDeck cardDeck = maumau.getDeck();
			Card card = cardDeckService.giveCard(maumau.getDeck(), maumau.getGraveyard());
			cardDeck.setCards(cardDeckService.removeCardFromCardDeckList(cardDeck.getCards(), card));
			maumau.setDeck(cardDeck);
			maumau.setCurrentPlayer(userService.takeCard(card, maumau.getCurrentPlayer()));
			return maumau;
		}

		
		public MauMau giveAllCardsToUserThatUserHasToTake(MauMau maumau) {
			this.ensureServicesAvailability();
			if (maumau.getAmountSeven() > 0) {
				for (int i = 0; i < 2 * maumau.getAmountSeven(); i++) {
					maumau = giveCardToUser(maumau);
				}
			} else {
				maumau = giveCardToUser(maumau);
			}
			maumau.setAmountSeven(0);
			return maumau;
		}
		
		
		
		public MauMau playCardProcedure(MauMau maumau, Card validCard) {
			this.ensureServicesAvailability();
			maumau.getGraveyard().getCards().add(validCard);
			maumau.getCurrentPlayer()
					.setHand(cardDeckService.removeCardFromCardDeckList(maumau.getCurrentPlayer().getHand(), validCard));
			return maumau;
		}
		
		public MauMau shoutMauProcedure(MauMau maumau, boolean mau) {
			this.ensureServicesAvailability();
			if (mau) {
				maumau.getCurrentPlayer().setMau(true);
			} else {
				maumau.getCurrentPlayer().setMau(false);
			}
			return maumau;
		}
		
		public MauMau shoutMauMauProcedure(MauMau maumau, boolean shoutMaumau) {
			this.ensureServicesAvailability();
			if (shoutMaumau) {
				maumau.getCurrentPlayer().setMaumau(true);
			} else {
				maumau.getCurrentPlayer().setMaumau(false);
			}
			return maumau;
		}
		
		
		
		public MauMau handleGameStart(List<String> userNames, MauMauRules rules, int amountCardsForUser) {
			this.ensureServicesAvailability();
			List<MauMauUser> users = userService.createUsers(userNames);
			CardDeck gameCardDeck = cardDeckService.createCardDeck(cardDeckService.createCards());
			List<Card> shuffledGameCards = cardDeckService.shuffle(gameCardDeck);
			Card firstGraveyardCard = shuffledGameCards.get(0);

			while (rulesService.checkIfSpecialCard(firstGraveyardCard)) {
				shuffledGameCards = cardDeckService.shuffle(gameCardDeck);
				firstGraveyardCard = shuffledGameCards.get(0);
			}

			shuffledGameCards.remove(0);
			List<Card> graveyardCards = new LinkedList<Card>();
			graveyardCards.add(firstGraveyardCard);
			MauMau maumau = new MauMau();
			maumau = startGame(users, gameCardDeck, new CardDeck(graveyardCards), rules, 0, false, null, 0, null,
					maumau);
			maumau.setEndGame(false);
			maumau = dealCardsToPlayers(maumau, amountCardsForUser);
			maumau = chooseWhoStarts(maumau);
			
			return maumau;
		}
		
		private void ensureServicesAvailability() {
			if(this.cardDeckService == null) this.cardDeckService = new CardDeckImpl();
			if(this.rulesService == null) this.rulesService = new RulesMgmt();
			if(this.userService == null) this.userService = new UserMgmt();
		}


		
		
		
		
		
}
