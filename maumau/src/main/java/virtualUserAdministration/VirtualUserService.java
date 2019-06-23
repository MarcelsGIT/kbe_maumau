package virtualUserAdministration;

import cardGame.modell.MauMau;
import cards.modell.Card;
import cards.modell.Symbol;
import userAdministration.modell.MauMauUser;

public interface VirtualUserService {
	
	
	
	/**
	 * Returns next possible card that can be played
	 * @param virtualMauMauUser
	 * @param mauMau
	 * @param lastPlayedCard
	 * @return card that can be played
	 */
	Card playNextPossibleCardFromHand(MauMauUser virtualMauMauUser, MauMau mauMau, Card lastPlayedCard);
	
	/**
	 * Returns virtual maumau user with Mau set true if possible, false if otherwise
	 * @param virtualMauMauUser
	 * @param mauMau
	 * @return virtualMauMauUser
	 */
	MauMauUser setMauIfPossible(MauMauUser virtualMauMauUser, MauMau mauMau);
	
	/**
	 * Returns virtual maumau user with MauMau set true if possible, false if otherwise
	 * @param virtualMauMauUser
	 * @param mauMau
	 * @return virtualMauMauUser
	 */
	MauMauUser setMauMauIfPossible(MauMauUser virtualMauMauUser, MauMau mauMau);
	
	/**
	 * Returns the Symbol of the first Card of Virtual Player
	 * @param virtualMauMauUser
	 * @return Symbol
	 */
	Symbol makeWhishByTakingFirstCardSymbol(MauMauUser virtualMauMauUser);
	
	
	
	

}
