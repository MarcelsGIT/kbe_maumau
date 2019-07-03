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
import util.exceptions.NoMoreCardsException;

// TODO: Auto-generated Javadoc
/**
 * The Class MauMauMgmt.
 */
@Component
public class MauMauMgmt implements MauMauService {

@Autowired
private CardDeckService cardDeckService;
@Autowired
private UserService userService;
@Autowired
private RulesService rulesService;

	
	

		/**
		 * Start game.
		 *
		 * @param userList the user list
		 * @param cardDeck the card deck
		 * @param graveyard the graveyard
		 * @param rules the rules
		 * @param currentPlayerIndex the current player index
		 * @param endGame the end game
		 * @param winner the winner
		 * @param amountSeven the amount seven
		 * @param userwish the userwish
		 * @param mauMau the mau mau
		 * @return the mau mau
		 */
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

		/**
		 * Choose who starts.
		 *
		 * @param mauMau the mau mau
		 * @return the mau mau
		 */
		// Kaan
		public MauMau chooseWhoStarts(MauMau mauMau) {
			this.ensureServicesAvailability();
			int randomIndex = (int) Math.random() * mauMau.getPlayers().size();
			MauMauUser starterPlayer = mauMau.getPlayers().get(randomIndex);
			mauMau.setCurrentPlayer(starterPlayer);
			return mauMau;
		}
		
		/**
		 * Transfer cards from graveyard to card deck.
		 *
		 * @param maumau the maumau
		 * @return the mau mau
		 * @throws NoMoreCardsException the no more cards exception
		 */
		//Marcel
		public MauMau transferCardsFromGraveyardToCardDeck(MauMau maumau)throws NoMoreCardsException {
			if(maumau.getGraveyard().getCards().size() <= 1) {
				throw new NoMoreCardsException();
			}
			Card lastPlayedCard = maumau.getGraveyard().getCards().get(maumau.getGraveyard().getCards().size() -1);
			for(Card card : maumau.getGraveyard().getCards()) {
				if(card != lastPlayedCard) {
					card.setDeck(maumau.getDeck());
					card.setOwner(null);
				}
				maumau.getDeck().getCards().add(card);
			}
			maumau.getGraveyard().getCards().clear();
			maumau.getGraveyard().getCards().add(lastPlayedCard);
			//graveyard.getCards().remove(lastPlayedCard);
			//cardList.addAll(graveyard.getCards());
			//graveyard.getCards().add(lastPlayedCard);
			//cardDeck.setCards(cardList);
			
			return maumau;
		}

		/**
		 * Next player.
		 *
		 * @param mauMau the mau mau
		 * @return the mau mau
		 */
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

		/**
		 * End game.
		 *
		 * @param mauMau the mau mau
		 * @param endGame the end game
		 * @return the mau mau
		 */
		// Kaan
		public MauMau endGame(MauMau mauMau, boolean endGame) {
			this.ensureServicesAvailability();
			mauMau.setEndGame(endGame);
			return mauMau;
		}

		/**
		 * Insert winner.
		 *
		 * @param user the user
		 * @param mauMau the mau mau
		 * @return the mau mau
		 */
		// No need right now
		public MauMau insertWinner(MauMauUser user, MauMau mauMau) {
			this.ensureServicesAvailability();
			mauMau.setWinner(user);
			return mauMau;

		}

		/**
		 * Skip round.
		 *
		 * @param user the user
		 * @param mauMau the mau mau
		 * @return the mau mau
		 */
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

		/**
		 * Deal penalty cards.
		 *
		 * @param amount the amount
		 * @param mauMau the mau mau
		 * @return the list
		 */
		// Marcel
		public List<Card> dealPenaltyCards(int amount, MauMau mauMau) {
			this.ensureServicesAvailability();
			// TODO Auto-generated method stub
			if(amount > mauMau.getDeck().getCards().size())
				mauMau = this.transferCardsFromGraveyardToCardDeck(mauMau);
			
			List<Card> deal = new LinkedList<Card>();
			for (int i = 0; i < amount; i++) {
				try {
					Card card = mauMau.getDeck().getCards().get(i);
					card.setDeck(null);
					card.setOwner(mauMau.getCurrentPlayer());
					deal.add(card);
					mauMau.getDeck().getCards().remove(i);
				}catch(NoMoreCardsException e) {
					return deal;
				}catch(IndexOutOfBoundsException ie) {
					return deal;
				}

			}

			return deal;
		}

		/**
		 * Deal cards to players.
		 *
		 * @param maumau the maumau
		 * @param amountCard the amount card
		 * @return the mau mau
		 * @throws NoMoreCardsException the no more cards exception
		 */
		public MauMau dealCardsToPlayers(MauMau maumau, int amountCard)throws NoMoreCardsException {
			this.ensureServicesAvailability();
			//List<MauMauUser> userList = maumau.getPlayers();
			//CardDeck cardDeck = maumau.getDeck();
			//CardDeck graveyard = maumau.getGraveyard();
			try {
				for (int i = 0; i < maumau.getPlayers().size(); i++) {
					List<Card> hand = cardDeckService.dealCards(maumau.getDeck(), amountCard, maumau.getGraveyard());
					maumau.getPlayers().get(i).setHand(hand);
					maumau.getDeck().getCards().removeAll(hand);
				
					//userList.set(i, user);
					//cardDeck = cardDeckService.removeCardsFromCardDeckList(cardDeck, hand);
					//userList.set(i, user);
				}
			}catch(NoMoreCardsException e) {
				maumau = this.transferCardsFromGraveyardToCardDeck(maumau);
				for (int i = 0; i < maumau.getPlayers().size(); i++) {
					List<Card> hand = cardDeckService.dealCards(maumau.getDeck(), amountCard, maumau.getGraveyard());
					maumau.getPlayers().get(i).setHand(hand);
					maumau.getDeck().getCards().removeAll(hand);
				
					//userList.set(i, user);
					//cardDeck = cardDeckService.removeCardsFromCardDeckList(cardDeck, hand);
					//userList.set(i, user);
				}
			}
			///maumau.setPlayers(userList);
			//maumau.setDeck(cardDeck);
			//maumau.setGraveyard(graveyard);

			return maumau;
		}

		/**
		 * Give card to user.
		 *
		 * @param maumau the maumau
		 * @return the mau mau
		 * @throws NoMoreCardsException the no more cards exception
		 */
		public MauMau giveCardToUser(MauMau maumau)throws NoMoreCardsException {
			this.ensureServicesAvailability();
			CardDeck cardDeck = maumau.getDeck();
			Card card = null;
			try {
				card = cardDeckService.giveCard(maumau.getDeck(), maumau.getGraveyard());
			}catch(NoMoreCardsException e) {
				maumau = this.transferCardsFromGraveyardToCardDeck(maumau);
				card = cardDeckService.giveCard(maumau.getDeck(), maumau.getGraveyard());
			}
			cardDeck.setCards(cardDeckService.removeCardFromCardDeckList(cardDeck.getCards(), card));
			maumau.setDeck(cardDeck);
			maumau.setCurrentPlayer(userService.takeCard(card, maumau.getCurrentPlayer()));
			return maumau;
		}

		
		/**
		 * Give all cards to user that user has to take.
		 *
		 * @param maumau the maumau
		 * @return the mau mau
		 * @throws NoMoreCardsException the no more cards exception
		 */
		public MauMau giveAllCardsToUserThatUserHasToTake(MauMau maumau)throws NoMoreCardsException {
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
		
		
		
		/**
		 * Play card procedure.
		 *
		 * @param maumau the maumau
		 * @param validCard the valid card
		 * @return the mau mau
		 */
		public MauMau playCardProcedure(MauMau maumau, Card validCard) {
			this.ensureServicesAvailability();
			maumau.getGraveyard().getCards().add(validCard);
			maumau.getCurrentPlayer()
					.setHand(cardDeckService.removeCardFromCardDeckList(maumau.getCurrentPlayer().getHand(), validCard));
			return maumau;
		}
		
		/**
		 * Shout mau procedure.
		 *
		 * @param maumau the maumau
		 * @param mau the mau
		 * @return the mau mau
		 */
		public MauMau shoutMauProcedure(MauMau maumau, boolean mau) {
			this.ensureServicesAvailability();
			if (mau) {
				maumau.getCurrentPlayer().setMau(true);
			} else {
				maumau.getCurrentPlayer().setMau(false);
			}
			return maumau;
		}
		
		/**
		 * Shout mau mau procedure.
		 *
		 * @param maumau the maumau
		 * @param shoutMaumau the shout maumau
		 * @return the mau mau
		 */
		public MauMau shoutMauMauProcedure(MauMau maumau, boolean shoutMaumau) {
			this.ensureServicesAvailability();
			if (shoutMaumau) {
				maumau.getCurrentPlayer().setMaumau(true);
			} else {
				maumau.getCurrentPlayer().setMaumau(false);
			}
			return maumau;
		}
		
		
		/**
		 * Handle game start.
		 *
		 * @param userNames the user names
		 * @param rules the rules
		 * @param amountCardsForUser the amount cards for user
		 * @return the mau mau
		 * @throws NoMoreCardsException the no more cards exception
		 */
		/*
		 * CardDeck enth�lt weiterhin die Karte, die eigentlich als firstGraveyardCard auf den Ablagestapel gelegt wird
		 * */
		public MauMau handleGameStart(List<String> userNames, MauMauRules rules, int amountCardsForUser)throws NoMoreCardsException {
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
			gameCardDeck.getCards().remove(firstGraveyardCard);
			MauMau maumau = new MauMau();
			maumau = startGame(users, gameCardDeck, new CardDeck(graveyardCards), rules, 0, false, null, 0, null,
					maumau);
			maumau.setEndGame(false);
			maumau = dealCardsToPlayers(maumau, amountCardsForUser);
			maumau = chooseWhoStarts(maumau);
			
			return maumau;
		}
		
		/**
		 * Ensure services availability.
		 */
		private void ensureServicesAvailability() {
			if(this.cardDeckService == null) this.cardDeckService = new CardDeckImpl();
			if(this.rulesService == null) this.rulesService = new RulesMgmt();
			if(this.userService == null) this.userService = new UserMgmt();
		}


		
		
		
		
		
}
