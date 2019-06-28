package virtualUserAdministration;

import cardGame.modell.MauMau;
import cards.modell.Card;
import cards.modell.Symbol;
import userAdministration.modell.MauMauUser;

// TODO: Auto-generated Javadoc
/**
 * The Interface VirtualUserService.
 */
public interface VirtualUserService {
	
	
	
	/**
	 * Play next possible card from hand.
	 *
	 * @param virtualMauMauUser the virtual mau mau user
	 * @param mauMau the mau mau
	 * @param lastPlayedCard the last played card
	 * @return the card
	 */
	Card playNextPossibleCardFromHand(MauMauUser virtualMauMauUser, MauMau mauMau, Card lastPlayedCard);
	
	/**
	 * Sets the mau if possible.
	 *
	 * @param virtualMauMauUser the virtual mau mau user
	 * @param mauMau the mau mau
	 * @return the mau mau user
	 */
	MauMauUser setMauIfPossible(MauMauUser virtualMauMauUser, MauMau mauMau);
	
	/**
	 * Sets the mau mau if possible.
	 *
	 * @param virtualMauMauUser the virtual mau mau user
	 * @param mauMau the mau mau
	 * @return the mau mau user
	 */
	MauMauUser setMauMauIfPossible(MauMauUser virtualMauMauUser, MauMau mauMau);
	
	/**
	 * Make whish by taking first card symbol.
	 *
	 * @param virtualMauMauUser the virtual mau mau user
	 * @return the symbol
	 */
	Symbol makeWhishByTakingFirstCardSymbol(MauMauUser virtualMauMauUser);
	
	
	
	

}
