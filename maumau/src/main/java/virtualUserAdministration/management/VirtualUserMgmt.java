package virtualUserAdministration.management;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cardGame.modell.MauMau;
import cards.modell.Card;
import rules.RulesService;
import rules.management.RulesMgmt;
import userAdministration.modell.MauMauUser;
import virtualUserAdministration.VirtualUserService;

@Component
public class VirtualUserMgmt implements VirtualUserService {

	@Autowired
	private RulesService ruleService;


	public Card playNextPossibleCardFromHand(MauMauUser virtualMauMauUser, MauMau mauMau, Card lastPlayedCard) {
		ensureServicesAvailability();
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


	public MauMauUser setMauIfPossible(MauMauUser virtualMauMauUser, MauMau mauMau) {
		ensureServicesAvailability();
		if (ruleService.checkShoutMauPossible(virtualMauMauUser, mauMau.getRuleSet())) {
			virtualMauMauUser.setMau(true);
		} else {
			virtualMauMauUser.setMau(false);
		}
		return virtualMauMauUser;
	}

	
	public MauMauUser setMauMauIfPossible(MauMauUser virtualMauMauUser, MauMau mauMau) {
		ensureServicesAvailability();
		if (ruleService.checkShoutMauMauPossible(virtualMauMauUser, mauMau.getRuleSet())) {
			virtualMauMauUser.setMaumau(true);
		} else {
			virtualMauMauUser.setMaumau(false);
		}
		return virtualMauMauUser;
	}
	
	
	private void ensureServicesAvailability() {

		if(this.ruleService == null) this.ruleService = new RulesMgmt();

	}


}
