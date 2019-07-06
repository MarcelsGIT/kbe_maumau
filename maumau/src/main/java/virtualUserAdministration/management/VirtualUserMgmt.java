package virtualUserAdministration.management;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cardGame.modell.MauMau;
import cards.modell.Card;
import cards.modell.Symbol;
import rules.RulesService;
import rules.management.RulesMgmt;
import userAdministration.modell.MauMauUser;
import virtualUserAdministration.VirtualUserService;

// TODO: Auto-generated Javadoc
/**
 * The Class VirtualUserMgmt.
 */
@Component
public class VirtualUserMgmt implements VirtualUserService {

	@Autowired
	private RulesService ruleService;


	/**
	 * Play next possible card from hand.
	 *
	 * @param virtualMauMauUser the virtual mau mau user
	 * @param mauMau the mau mau
	 * @param lastPlayedCard the last played card
	 * @return the card
	 */
	public Card playNextPossibleCardFromHand(MauMauUser virtualMauMauUser, MauMau mauMau, Card lastPlayedCard) {
		ensureServicesAvailability();
		List<Card> cards = virtualMauMauUser.getHand();
		Card validCard = null;
		for (Card card : cards) {
			//if (ruleService.validCardOrNotValidCard(lastPlayedCard, card, mauMau.getUserwish(), mauMau.getRuleSet())) {
			if(ruleService.checkIsValid(lastPlayedCard, card, mauMau.getUserwish(), mauMau.getAmountSeven(), mauMau.getRuleSet())) {
				validCard = card;
				break;
			}

		}

		return validCard;
	}


	/**
	 * Sets the mau if possible.
	 *
	 * @param virtualMauMauUser the virtual mau mau user
	 * @param mauMau the mau mau
	 * @return the mau mau user
	 */
	public MauMauUser setMauIfPossible(MauMauUser virtualMauMauUser, MauMau mauMau) {
		ensureServicesAvailability();
		if (ruleService.checkShoutMauPossible(virtualMauMauUser, mauMau.getRuleSet())) {
			virtualMauMauUser.setMau(true);
		} else {
			virtualMauMauUser.setMau(false);
		}
		return virtualMauMauUser;
	}

	
	/**
	 * Sets the mau mau if possible.
	 *
	 * @param virtualMauMauUser the virtual mau mau user
	 * @param mauMau the mau mau
	 * @return the mau mau user
	 */
	public MauMauUser setMauMauIfPossible(MauMauUser virtualMauMauUser, MauMau mauMau) {
		ensureServicesAvailability();
		if (ruleService.checkShoutMauMauPossible(virtualMauMauUser, mauMau.getRuleSet())) {
			virtualMauMauUser.setMaumau(true);
		} else {
			virtualMauMauUser.setMaumau(false);
		}
		return virtualMauMauUser;
	}
	
	
	
	/**
	 * Make whish by taking first card symbol.
	 *
	 * @param virtualMauMauUser the virtual mau mau user
	 * @return the symbol
	 */
	public Symbol makeWhishByTakingFirstCardSymbol(MauMauUser virtualMauMauUser) {
		ensureServicesAvailability();
		Symbol symbol = virtualMauMauUser.getCardInHand(0).getSymbol();
		return symbol;
	}
	
	
	
	
	
	/**
	 * Ensure services availability.
	 */
	private void ensureServicesAvailability() {
		if(this.ruleService == null) this.ruleService = new RulesMgmt();
	}





}
