package virtualUserAdministration.management;

import java.util.List;

import org.springframework.stereotype.Component;

import cardGame.modell.MauMau;
import cards.modell.Card;
import cards.modell.Symbol;
import rules.RulesService;
import rules.modell.MauMauRules;
import userAdministration.modell.MauMauUser;
import virtualUserAdministration.VirtualUserService;

@Component
public class VirtualUserMgmt implements VirtualUserService {

	private RulesService ruleService;

	@Override
	public Card playNextPossibleCardFromHand(MauMauUser virtualMauMauUser, MauMau mauMau, Card lastPlayedCard) {
		List<Card> cards = virtualMauMauUser.getHand();
		Card validCard = null;
		for (Card card : cards) {
			if (ruleService.validCardOrNotValidCard(lastPlayedCard, card, mauMau.getUserwish(), mauMau.getRuleSet())) {
				validCard = card;
				break;
			}

		}

		return validCard;
	}

	@Override
	public MauMauUser setMauIfPossible(MauMauUser virtualMauMauUser, MauMau mauMau) {
		if (ruleService.checkShoutMauPossible(virtualMauMauUser, mauMau.getRuleSet())) {
			virtualMauMauUser.setMau(true);
		} else {
			virtualMauMauUser.setMau(false);
		}
		return virtualMauMauUser;
	}

	@Override
	public MauMauUser setMauMauIfPossible(MauMauUser virtualMauMauUser, MauMau mauMau) {
		if (ruleService.checkShoutMauMauPossible(virtualMauMauUser, mauMau.getRuleSet())) {
			virtualMauMauUser.setMaumau(true);
		} else {
			virtualMauMauUser.setMaumau(false);
		}
		return virtualMauMauUser;
	}

}
