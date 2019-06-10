package cardGame.management;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import cardGame.MauMauService;
import cardGame.modell.MauMau;
import cards.CardDeckService;
import cards.modell.Card;
import cards.modell.CardDeck;
import cards.modell.Symbol;
import rules.modell.MauMauRules;
import userAdministration.UserService;
import userAdministration.modell.MauMauUser;

@Component
public class MauMauMgmt implements MauMauService {

	// Kaan
		public void startGame(List<MauMauUser> userList, CardDeck cardDeck, CardDeck graveyard, MauMauRules rules,
				int currentPlayerIndex, boolean endGame, MauMauUser winner, int amountSeven, Symbol userwish, MauMau mauMau) {
			mauMau.setPlayers(userList);
			mauMau.setDeck(cardDeck);
			mauMau.setGraveyard(graveyard);
			mauMau.setRuleSet(rules);
			mauMau.setCurrentPlayerIndex(currentPlayerIndex);
			mauMau.setWinner(winner);
			mauMau.setAmountSeven(amountSeven);
			mauMau.setUserwish(userwish);
			
		}

		// Kaan
		public MauMau chooseWhoStarts(MauMau mauMau) {
			List<MauMauUser> allPlayers = mauMau.getPlayers();
			int randomIndex = (int) Math.random() * mauMau.getPlayers().size();
			MauMauUser starterPlayer = allPlayers.get(randomIndex);
			mauMau.setCurrentPlayer(starterPlayer);
			return mauMau;
		}

		// Marcel
		public MauMau nextPlayer(MauMau mauMau) {
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
			mauMau.setEndGame(endGame);
			return mauMau;
		}

		// No need right now
		public MauMau insertWinner(MauMauUser user, MauMau mauMau) {
			mauMau.setWinner(user);
			return mauMau;

		}

		// Marcel
		public MauMau skipRound(MauMauUser user, MauMau mauMau) {
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
			// TODO Auto-generated method stub
			List<Card> deal = new LinkedList<Card>();
			for (int i = 0; i < amount; i++) {

				deal.add(mauMau.getDeck().getCards().get(i));
				mauMau.getDeck().getCards().remove(i);

			}

			return deal;
		}

		public MauMau dealCardsToPlayers(MauMau maumau, int amountCards, CardDeckService cardDeckService) {
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

		public MauMau giveCardToUser(MauMau maumau, CardDeckService cardDeckService, UserService userService) {
			CardDeck cardDeck = maumau.getDeck();
			Card card = cardDeckService.giveCard(maumau.getDeck(), maumau.getGraveyard());
			cardDeck.setCards(cardDeckService.removeCardFromCardDeckList(cardDeck.getCards(), card));
			maumau.setDeck(cardDeck);
			maumau.setCurrentPlayer(userService.takeCard(card, maumau.getCurrentPlayer()));
			return maumau;
		}

}
